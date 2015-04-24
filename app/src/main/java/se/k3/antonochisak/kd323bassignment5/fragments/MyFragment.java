package se.k3.antonochisak.kd323bassignment5.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;
import se.k3.antonochisak.kd323bassignment5.R;
import se.k3.antonochisak.kd323bassignment5.adapters.MyAdapter;
import se.k3.antonochisak.kd323bassignment5.api.RestClient;
import se.k3.antonochisak.kd323bassignment5.api.model.RootApiResponse;
import se.k3.antonochisak.kd323bassignment5.helpers.StaticHelpers;
import se.k3.antonochisak.kd323bassignment5.models.movie.Movie;

/**
 * Created by isak on 2015-04-24.
 */
public class MyFragment extends MoviesFragment implements AdapterView.OnItemClickListener{

    private static final String TAG = MyFragment.class.getSimpleName();

    CountDownTimer mVoteTimer;
    boolean mIsVoteTimerRunning = false;

    ArrayList<Movie> mMovies;
    String mCurrentClickedMovie = "";
    HashMap<String, Object> mMovieMap;

    RestClient mRestClient;
    Firebase mFirebase;
    Firebase mRef;

    MyAdapter mAdapter;
    ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovies = new ArrayList<>();
        mMovieMap = new HashMap<>();
        mRestClient = new RestClient();
        mFirebase = new Firebase(StaticHelpers.FIREBASE_URL);
        mRef = mFirebase.child(StaticHelpers.FIREBASE_CHILD);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_fragment_layout, container, false);
        mListView = (ListView) v.findViewById(R.id.my_listview);

        mAdapter = new MyAdapter(mMovies, getActivity().getLayoutInflater());

        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRestClient.getApiService().getTrending("full,images", this);
        initVoteTimer();
    }

    @Override
    void initVoteTimer() {
        mVoteTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mIsVoteTimerRunning = false;
            }
        };
    }

    @Override
    void voteOnMovie(final int i) {
        Movie movie = mMovies.get(i);

        // Very important
        mCurrentClickedMovie = movie.getSlugline();

        mMovieMap.put("title", movie.getTitle());
        mMovieMap.put("year", movie.getYear());
        mMovieMap.put("slugline", movie.getSlugline());
        mMovieMap.put("poster", movie.getPoster());
        mMovieMap.put("fanart", movie.getFanArt());

        mRef.child(mCurrentClickedMovie).updateChildren(mMovieMap, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                Toast.makeText(getActivity(), "Gillade " + mMovies.get(i).getTitle(), Toast.LENGTH_SHORT).show();
                updateVotes();
            }
        });
    }

    @Override
    void updateVotes() {
        mRef.child(mCurrentClickedMovie + "/votes").runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                if (mutableData.getValue() == null) {
                    mutableData.setValue(1);
                } else {
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

    @Override
    public void success(List<RootApiResponse> rootApiResponses, Response response) {
        for (RootApiResponse r : rootApiResponses) {
            Movie movie = new Movie.Builder()
                    .title(r.apiResponse.title)
                    .slugLine(r.apiResponse.ids.getSlug())
                    .poster(r.apiResponse.image.getPoster().getMediumPoster())
                    .fanArt(r.apiResponse.image.getFanArt().getFullFanArt())
                    .year(r.apiResponse.year)
                    .overview(r.apiResponse.overview)
                    .tagline(r.apiResponse.tagline)
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!mIsVoteTimerRunning) {
            voteOnMovie(position);
            mVoteTimer.start();
            mIsVoteTimerRunning = true;
        }
    }
}
