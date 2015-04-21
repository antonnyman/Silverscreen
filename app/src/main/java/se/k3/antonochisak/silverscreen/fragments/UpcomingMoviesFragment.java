package se.k3.antonochisak.silverscreen.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
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
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import se.k3.antonochisak.silverscreen.R;
import se.k3.antonochisak.silverscreen.adapters.MovieAdapter;
import se.k3.antonochisak.silverscreen.api.RestClient;
import se.k3.antonochisak.silverscreen.api.model.RootApiResponse;
import se.k3.antonochisak.silverscreen.models.Movie;
import se.k3.antonochisak.silverscreen.models.Poster;

/**
 * Created by anton on 2015-04-18.
 */
public class UpcomingMoviesFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = UpcomingMoviesFragment.class.getSimpleName();

    @InjectView(R.id.gridView)
    GridView mMoviesList;

    List<Poster> mPosters;
    List<Movie> mMovies;

    Map<String, Object> mMovieMap;

    MovieAdapter mAdapter;

    RestClient restClient;
    Firebase firebase;
    Firebase ref;

    String mCurrentClickedMovie = "";
    Date mTodaysDate;
    DateFormat mDateFormat = new SimpleDateFormat("dd-MM-yyyy");



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.inject(this, view);

        mPosters = new ArrayList<>();
        mMovies = new ArrayList<>();
        mMovieMap = new HashMap<>();

        mTodaysDate = new Date();

        mAdapter = new MovieAdapter(mMovies, getActivity().getLayoutInflater());
        mMoviesList.setAdapter(mAdapter);
        mMoviesList.setOnItemClickListener(this);

        restClient = new RestClient();
        firebase = new Firebase("https://klara.firebaseio.com/");
        ref = firebase.child("top_movies");

        getMovies();

        return view;
    }



    private void getMovies() {
        restClient.getApiService().getGetUpcoming("images", mDateFormat.format(mTodaysDate), 30, new Callback<List<RootApiResponse>>() {
            @Override
            public void success(List<RootApiResponse> apiResponses, Response response) {
                for (int i = 0; i < apiResponses.size(); i++) {
                    try {
                        mMovies.add(new Movie(
                                        apiResponses.get(i).apiResponse.getTitle(),
                                        apiResponses.get(i).apiResponse.getIds().getSlug(),
                                        apiResponses.get(i).apiResponse.getImage().getPoster().getMediumPoster(),
                                        apiResponses.get(i).apiResponse.getImage().getFanart().getMediumFanart(),
                                        apiResponses.get(i).apiResponse.getYear(),
                                        0
                                )
                        );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    mAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

    }

    private void getVotes() {
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

        mCurrentClickedMovie = mMovies.get(i).getSlugline();

        mMovieMap.put("title", mMovies.get(i).getTitle());
        mMovieMap.put("year", String.valueOf(mMovies.get(i).getYear()));
        mMovieMap.put("slugline", mMovies.get(i).getSlugline());
        mMovieMap.put("poster", mMovies.get(i).getPoster());
        mMovieMap.put("fanart", mMovies.get(i).getFanart());
        ref.child(mCurrentClickedMovie).updateChildren(mMovieMap, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Toast.makeText(getActivity(), "Gillade " + mMovies.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, mCurrentClickedMovie);
                updateVotes();
            }
        });


    }

    private void updateVotes() {
        ref.child(mCurrentClickedMovie + "/votes").runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                if (mutableData.getValue() == null) {
                    mutableData.setValue(1);
                    Log.d(TAG + " mutableValue:" + mutableData, mutableData.getValue().toString());
                } else {
                    mutableData.setValue((Long) mutableData.getValue() + 1);

                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(FirebaseError firebaseError, boolean b, DataSnapshot dataSnapshot) {
                if(firebaseError != null) {
                    Log.d(TAG + " Error bro", firebaseError.getMessage());
                }
            }
        });
    }
}
