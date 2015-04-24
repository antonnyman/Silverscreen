package se.k3.antonochisak.kd323bassignment5;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.k3.antonochisak.kd323bassignment5.fragments.PopularMoviesFragment;
import se.k3.antonochisak.kd323bassignment5.navigation.drawer.NavigationDrawer;


public class MainActivity extends ActionBarActivity {

    NavigationDrawer mNavigationDrawer;
    FragmentManager mFragmentManager;

    String mTitle;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mFragmentManager = getFragmentManager();

        // Do not touch
        Firebase.setAndroidContext(this);

        // Set toolbar. See activity_main.xml. To retrieve the toolbar after this
        // use getSupportActionBar which returns an ActionBar item but is in fact this toolbar
        setSupportActionBar(mToolbar);
        mTitle = getResources().getString(R.string.popular_movies_fragment);
        setTitle(mTitle);

        // Create navigationDrawer
        mNavigationDrawer = new NavigationDrawer(mFragmentManager, this);

        if (savedInstanceState == null) {
            mFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_frame, new PopularMoviesFragment(), mTitle)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mNavigationDrawer.drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        int backStackEntryCount = mFragmentManager.getBackStackEntryCount();

        if (mNavigationDrawer.mDrawerLayout.isDrawerOpen(mNavigationDrawer.mDrawerList)) {
            mNavigationDrawer.mDrawerLayout.closeDrawers();
        } else if (backStackEntryCount > 0) {
            mFragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
