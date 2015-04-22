package se.k3.antonochisak.silverscreen.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.k3.antonochisak.silverscreen.R;
import se.k3.antonochisak.silverscreen.helpers.StaticHelpers;
import se.k3.antonochisak.silverscreen.models.Movie;

/**
 * Created by anton on 2015-04-13.
 */
public class MoviesAdapter extends BaseAdapter {

    ArrayList<Movie> mMovies;
    LayoutInflater mLayoutInflater;
    private int mItemWidth, mItemHeight, mMargin;

    public MoviesAdapter(ArrayList<Movie> mMovies, LayoutInflater mLayoutInflater) {
        this.mMovies = mMovies;
        this.mLayoutInflater = mLayoutInflater;
    }

    // We always use a viewholder pattern on listviews!
    class ViewHolder {
        @InjectView(R.id.poster)
        ImageView poster;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);

            int screenWidth = StaticHelpers.getScreenWidth(view.getContext());
            mItemWidth = (screenWidth / 2);
            mItemHeight = (int) ((double) mItemWidth / 0.677);
            mMargin = StaticHelpers.getPixelsFromDp(view.getContext(), 2);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.list_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // Load pictures with picasso
        Picasso.with(view.getContext())
                .load(mMovies.get(i).getPoster())
                .resize(mItemWidth, mItemHeight)
                .into(holder.poster);
        return view;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public Object getItem(int i) {
        return mMovies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
}
