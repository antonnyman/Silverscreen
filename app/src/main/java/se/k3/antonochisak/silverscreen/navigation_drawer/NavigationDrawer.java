package se.k3.antonochisak.silverscreen.navigation_drawer;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Fragment;
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

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.k3.antonochisak.silverscreen.R;
import se.k3.antonochisak.silverscreen.fragments.PopularMoviesFragment;
import se.k3.antonochisak.silverscreen.fragments.StudentFragment;

/**
 * Created by isak on 2015-04-10.
 */
public class NavigationDrawer implements AdapterView.OnItemClickListener {

    ActionBar mActionBar;
    CharSequence mTitle;
    CharSequence mDrawerTitle;

    private FragmentManager fm;

    public String[] mDrawerItems;
    public ActionBarDrawerToggle drawerToggle;
    public ValueAnimator anim;

    @InjectView(R.id.drawer_list)
    ListView mDrawerList;
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    public NavigationDrawer(FragmentManager fm, Activity activity) {
        ButterKnife.inject(this, activity);
        mTitle = activity.getResources().getString(R.string.app_name);
        this.fm = fm;

        // This string array represents the different items in the drawer.
        // The strings in this array should come in the same order as
        // the onItemClick method use.
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
        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(
                (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE),
                new ArrayList<>(Arrays.asList(mDrawerItems)));

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String title = mDrawerItems[position];

        Fragment fragment = null;
        switch(position) {
            case 0:
                fragment = new PopularMoviesFragment();
                break;
            case 1:
                fragment = new StudentFragment();
                break;
        }
        fm.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(title).commit();

        mTitle = title;
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}

