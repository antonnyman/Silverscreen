package se.k3.antonochisak.kd323bassignment5.navigation.drawer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.k3.antonochisak.kd323bassignment5.R;
import se.k3.antonochisak.kd323bassignment5.fragments.MyFragment;
import se.k3.antonochisak.kd323bassignment5.fragments.PopularMoviesFragment;
import se.k3.antonochisak.kd323bassignment5.helpers.StaticHelpers;

/**
 * Created by isak on 2015-04-24.
 */
public class NavigationDrawer implements AdapterView.OnItemClickListener {

    public ActionBarDrawerToggle drawerToggle;
    public String[] mDrawerItems;

    private ActionBar mActionBar;
    private CharSequence mTitle;
    private CharSequence mDrawerTitle;
    private FragmentManager mFragmentManager;

    public @InjectView(R.id.drawer_list)
    ListView mDrawerList;
    public @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    public NavigationDrawer(FragmentManager mFragmentManager, Activity activity) {
        ButterKnife.inject(this, activity);
        mTitle = activity.getResources().getString(R.string.app_name);
        this.mFragmentManager = mFragmentManager;

        // This string array represents the different items in the drawer.
        // The strings in this array should come in the same order as
        // the onItemClick method use.
        mDrawerItems = activity.getResources().getStringArray(R.array.drawer_items);

        setupSupportActionBar(activity);
        setupDrawerList(activity);
        initDrawerToggle(activity);
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
        mActionBar = ((AppCompatActivity) activity).getSupportActionBar();
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
        switch (position) {
            case 0:
                fragment = new PopularMoviesFragment();
                break;
            case 1:
                fragment = new MyFragment();
                break;
            default:
                break;
        }

        // only replace the current fragment if the chosen fragment isn't already visible
        if (!StaticHelpers.isFragmentVisible(mFragmentManager, title)) {
            mFragmentManager.beginTransaction().replace(R.id.content_frame, fragment, title).addToBackStack(null).commit();
        }
        mTitle = title;
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
