package se.k3.antonochisak.kd323bassignment5.fragments;

import android.app.Fragment;

import java.util.List;

import retrofit.Callback;
import se.k3.antonochisak.kd323bassignment5.api.model.RootApiResponse;

/**
 * Created by isak on 2015-04-24.
 */

// keep abstract
public abstract class MoviesFragment extends Fragment implements Callback<List<RootApiResponse>> {
    abstract void initVoteTimer();

    abstract void voteOnMovie(int i);

    abstract void updateVotes();
}