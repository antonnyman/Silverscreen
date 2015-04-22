package se.k3.antonochisak.silverscreen.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import se.k3.antonochisak.silverscreen.R;
import se.k3.antonochisak.silverscreen.adapters.MoviesAdapter;
import se.k3.antonochisak.silverscreen.api.RestClient;
import se.k3.antonochisak.silverscreen.api.model.RootApiResponse;
import se.k3.antonochisak.silverscreen.models.Movie;

import static se.k3.antonochisak.silverscreen.helpers.StaticHelpers.FIREBASE_TOP_MOVIES;
import static se.k3.antonochisak.silverscreen.helpers.StaticHelpers.FIREBASE_URL;

/**
 * Created by anton on 2015-04-18.
 */

// this one needs to implement another interface
public class UpcomingMoviesFragment extends MoviesFragment implements Callback<List<RootApiResponse>> {

    // Tag for logging
    private static final String TAG = UpcomingMoviesFragment.class.getSimpleName();

    // List of movies
    ArrayList<Movie> mMovies;

    // This is pushed to firebase
    HashMap<String, Object> mMovieMap;

    RestClient restClient;
    Firebase firebase;
    Firebase ref;

    String mCurrentClickedMovie = "";
    MoviesAdapter mAdapter;

    DateFormat mDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @InjectView(R.id.gridView) GridView mMoviesGrid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovies = new ArrayList<>();
        mMovieMap = new HashMap<>();

        restClient = new RestClient();
        firebase = new Firebase(FIREBASE_URL);
        ref = firebase.child(FIREBASE_TOP_MOVIES);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        // Inject views
        ButterKnife.inject(this, view);

        // Create adapter
        mAdapter = new MoviesAdapter(mMovies, getActivity().getLayoutInflater());
        mMoviesGrid.setAdapter(mAdapter);

        // listener= GridView.OnItemClickListener
        mMoviesGrid.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // method to get popular movies from Trakt using Retrofit libary.
        // You should do your own method inside of ApiService
        // listener = Callback<List<ApiResponse>>
        restClient.getApiService().getGetUpcoming("images", mDateFormat.format(new Date()), 30, this);
    }

    @Override
    public void success(List<RootApiResponse> apiResponses, Response response) {
        for(RootApiResponse r : apiResponses) {
            Movie movie = new Movie.Builder()
                    .title(r.apiResponse.title)
                    .slugLine(r.apiResponse.ids.getSlug())
                    .poster(r.apiResponse.image.getPoster().getMediumPoster())
                    .fanart(r.apiResponse.image.getFanart().getFullFanart())
                    .year(r.apiResponse.year)
                    .votes(0)
                    .build();

            mMovies.add(movie);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        error.printStackTrace();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        Movie movie = mMovies.get(i);

        // Very important
        mCurrentClickedMovie = movie.getSlugline();

        mMovieMap.put("title", movie.getTitle());
        mMovieMap.put("year", movie.getYear());
        mMovieMap.put("slugline", movie.getSlugline());
        mMovieMap.put("poster", movie.getPoster());
        mMovieMap.put("fanart", movie.getFanart());

        ref.child(mCurrentClickedMovie).updateChildren(mMovieMap, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Toast.makeText(getActivity(), "Gillade " + mMovies.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                updateVotes();
            }
        });
    }

    void updateVotes() {
        ref.child(mCurrentClickedMovie + "/votes").runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                if (mutableData.getValue() != null) {
                    mutableData.setValue((Long) mutableData.getValue() + 1);
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {
                if (firebaseError != null) {
                    Log.d(TAG + " Error", firebaseError.getMessage());
                }
            }
        });
    }
}
