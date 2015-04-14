package se.k3.antonochisak.silverscreen.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.k3.antonochisak.silverscreen.R;
import se.k3.antonochisak.silverscreen.models.Image;
import se.k3.antonochisak.silverscreen.models.Poster;

/**
 * Created by anton on 2015-04-13.
 */
public class MovieAdapter extends BaseAdapter {

    List<Poster> mMovies;
    LayoutInflater mLayoutInflater;

    public MovieAdapter(List<Poster> mMovies, LayoutInflater mLayoutInflater) {
        this.mMovies = mMovies;
        this.mLayoutInflater = mLayoutInflater;
    }

    class ViewHolder {
        @InjectView(R.id.poster)
        ImageView poster;
        @InjectView(R.id.title)
        TextView title;

        public ViewHolder(View view) { ButterKnife.inject(this, view); }
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

        //holder.title.setText("");
        Picasso.with(view.getContext()).load(mMovies.get(i).getMediumPoster()).into(holder.poster);
        return view;
    }
}
