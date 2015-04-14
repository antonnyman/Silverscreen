package se.k3.antonochisak.silverscreen.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import se.k3.antonochisak.silverscreen.MainActivity;
import se.k3.antonochisak.silverscreen.R;
import se.k3.antonochisak.silverscreen.adapters.MovieAdapter;
import se.k3.antonochisak.silverscreen.api.model.ApiResponse;
import se.k3.antonochisak.silverscreen.models.Fanart;
import se.k3.antonochisak.silverscreen.models.Movie;
import se.k3.antonochisak.silverscreen.models.Poster;

/**
 * Created by anton on 2015-04-13.
 */
public class MoviesFragment extends Fragment implements GridView.OnItemClickListener {

    private static final String TAG = MoviesFragment.class.getSimpleName();

    @InjectView(R.id.gridView)
    GridView mMoviesList;

    List<Poster> mPosters;
    List<Movie> mMovies;

    Map<String, Object> mMovieMap;

    MovieAdapter mAdapter;

    Firebase firebase;
    Firebase ref;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.inject(this, view);

        mPosters = new ArrayList<>();
        mMovies = new ArrayList<>();
        mMovieMap = new HashMap<>();

        mAdapter = new MovieAdapter(mPosters, getActivity().getLayoutInflater());
        mMoviesList.setAdapter(mAdapter);
        mMoviesList.setOnItemClickListener(this);

        firebase = new Firebase("https://klara.firebaseio.com/");
        ref = firebase.child("top_movies");

        getMovies();

        return view;
    }



    private void getMovies() {
        MainActivity.restClient.getApiService().getPopular("images", new Callback<List<ApiResponse>>() {
            @Override
            public void success(List<ApiResponse> apiResponses, Response response) {
                for (int i = 0; i < apiResponses.size(); i++) {

                    mPosters.add(new Poster(
                            apiResponses.get(i).getImage().getPoster().getFullPoster(),
                            apiResponses.get(i).getImage().getPoster().getMediumPoster(),
                            apiResponses.get(i).getImage().getPoster().getThumbPoster()
                            )
                    );

                    mMovies.add(new Movie(
                                    apiResponses.get(i).getTitle(),
                                    apiResponses.get(i).getSlugline(),
                                    apiResponses.get(i).getImage().getPoster().getMediumPoster(),
                                    apiResponses.get(i).getImage().getFanart().getFullFanart(),
                                    apiResponses.get(i).getYear()
                            )
                    );

                    mAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        mMovieMap.put("title", mMovies.get(i).getTitle());
        mMovieMap.put("year", String.valueOf(mMovies.get(i).getYear()));
        mMovieMap.put("slugline", mMovies.get(i).getSlugline());
        mMovieMap.put("poster", mMovies.get(i).getPoster());
        mMovieMap.put("fanart", mMovies.get(i).getFanart());
        ref.push().setValue(mMovieMap, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Toast.makeText(getActivity(), "Gillade " + mMovies.get(i).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
