package se.k3.antonochisak.silverscreen.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

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
import se.k3.antonochisak.silverscreen.models.Poster;

/**
 * Created by anton on 2015-04-13.
 */
public class MoviesFragment extends Fragment {

    private static final String TAG = MoviesFragment.class.getSimpleName();

    @InjectView(R.id.gridView)
    GridView mMoviesList;

    @InjectView(R.id.update_button) Button button;

    List<Poster> mPosters;
    MovieAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.inject(this, view);

        mPosters = getPosters();
        mAdapter = new MovieAdapter(mPosters, getActivity().getLayoutInflater());
        mMoviesList.setAdapter(mAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, String.valueOf(mAdapter.getCount()));
                mAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }



    private List<Poster> getPosters() {
        final List<Poster> posters = new ArrayList<>();
        MainActivity.restClient.getApiService().getPopular("images", new Callback<List<ApiResponse>>() {
            @Override
            public void success(List<ApiResponse> apiResponses, Response response) {
                for (int i = 0; i < apiResponses.size(); i++) {

                    posters.add(new Poster(
                            apiResponses.get(i).getImage().getPoster().getFullPoster(),
                            apiResponses.get(i).getImage().getPoster().getMediumPoster(),
                            apiResponses.get(i).getImage().getPoster().getThumbPoster()));
                    mAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });


        return posters;
    }


}
