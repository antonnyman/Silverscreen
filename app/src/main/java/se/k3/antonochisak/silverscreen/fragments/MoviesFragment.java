package se.k3.antonochisak.silverscreen.fragments;

import android.app.Fragment;
import android.widget.GridView;

import se.k3.antonochisak.silverscreen.models.Movie;

/**
 * Created by isak on 2015-04-22.
 */
public abstract class MoviesFragment extends Fragment implements GridView.OnItemClickListener {
    abstract void updateVotes();
    abstract void initVoteTimer();
}
