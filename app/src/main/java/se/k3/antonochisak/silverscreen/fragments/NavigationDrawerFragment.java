package se.k3.antonochisak.silverscreen.fragments;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.k3.antonochisak.silverscreen.R;
import se.k3.antonochisak.silverscreen.adapters.SimpleBaseAdapter;

/**
 * Created by isak on 2015-04-10.
 */
public class NavigationDrawerFragment implements AdapterView.OnItemClickListener {

    ActionBar mActionBar;

    CharSequence mTitle;
    CharSequence mDrawerTitle;

    public String[] mDrawerItems;
    public ActionBarDrawerToggle drawerToggle;
    public ValueAnimator anim;

    @InjectView(R.id.drawer_list)
    ListView mDrawerList;
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    public NavigationDrawerFragment(FragmentManager fm, Activity activity) {
        ButterKnife.inject(this, activity);
        mTitle = activity.getResources().getString(R.string.movie_fragment);

        mDrawerItems = activity.getResources().getStringArray(R.array.drawer_items);
        setupSupportActionBar(activity);

        setupDrawerList(activity);
        initDrawerToggle(activity);
        setupAnim();
    }

    private void setupAnim() {
        anim = ValueAnimator.ofFloat(1, 0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                drawerToggle.onDrawerSlide(mDrawerLayout, slideOffset);
            }
        });
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(300);
    }

    void initDrawerToggle(final Activity activity) {
        drawerToggle = new ActionBarDrawerToggle(activity,
                mDrawerLayout,
                R.string.drawer_opened,
                R.string.drawer_closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                mDrawerTitle = mActionBar.getTitle();
                mTitle = mDrawerTitle;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                mActionBar.setTitle(mTitle);
                activity.invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    void setupSupportActionBar(Activity activity) {
        mActionBar = ((ActionBarActivity) activity).getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
    }

    void setupDrawerList(Activity activity) {
        SimpleBaseAdapter adapter = new SimpleBaseAdapter(
                (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE),
                new ArrayList<>(Arrays.asList(mDrawerItems)));

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tag = mDrawerItems[position];
        switch(position) {
            case 0:
                break;
            case 1:
                break;
        }
        mTitle = tag;
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}

