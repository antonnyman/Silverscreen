package se.k3.antonochisak.silverscreen.fragments;

import android.app.Fragment;

import java.util.List;

import retrofit.Callback;
import se.k3.antonochisak.silverscreen.api.model.RootApiResponse;


/**
 * Created by isak on 2015-04-23.
 */
public abstract class MoviesFragment extends Fragment implements Callback<List<RootApiResponse>> {
    abstract void initVoteTimer();

    abstract void voteOnMovie(int i);

    abstract void updateVotes();
}
