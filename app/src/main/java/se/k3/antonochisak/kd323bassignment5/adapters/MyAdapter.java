package se.k3.antonochisak.kd323bassignment5.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.k3.antonochisak.kd323bassignment5.R;
import se.k3.antonochisak.kd323bassignment5.helpers.StaticHelpers;
import se.k3.antonochisak.kd323bassignment5.models.movie.Movie;

/**
 * Created by isak on 2015-04-24.
 */
public class MyAdapter extends BaseAdapter {

    ArrayList<Movie> mMovies;
    LayoutInflater mLayoutInflater;

    public MyAdapter(ArrayList<Movie> mMovies, LayoutInflater mLayoutInflater) {
        this.mMovies = mMovies;
        this.mLayoutInflater = mLayoutInflater;
    }

    @Override
    public int getCount() {
        Log.i("isak", "size: " + String.valueOf(mMovies.size()));
        return mMovies.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder {
        ImageView poster;
        TextView title;
        TextView year;
        TextView overview;
        TextView tagline;
        public ViewHolder(View view) {
            poster = (ImageView) view.findViewById(R.id.my_poster);
            title = (TextView) view.findViewById(R.id.my_title);
            year = (TextView) view.findViewById(R.id.my_year);
            overview = (TextView) view.findViewById(R.id.my_overview);
            tagline = (TextView) view.findViewById(R.id.my_tagline);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.my_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Movie movie = mMovies.get(position);

        holder.title.setText(movie.getTitle());
        holder.year.setText(String.valueOf(movie.getYear()));

        //holder.overview.setText(movie.getOverview());
        holder.tagline.setText(movie.getTagline());

        // Load pictures with picasso
        Picasso.with(convertView.getContext())
                .load(movie.getPoster())
                .into(holder.poster);
        return convertView;
    }
}
