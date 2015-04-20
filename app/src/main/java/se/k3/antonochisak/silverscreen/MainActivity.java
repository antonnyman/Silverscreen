package se.k3.antonochisak.silverscreen;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.Firebase;

import butterknife.ButterKnife;
import butterknife.InjectView;
import se.k3.antonochisak.silverscreen.api.RestClient;
import se.k3.antonochisak.silverscreen.fragments.MoviesFragment;
import se.k3.antonochisak.silverscreen.fragments.NavigationDrawer;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static RestClient restClient;
    NavigationDrawer mNavigationDrawer;

    @InjectView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        restClient = new RestClient();
        Firebase.setAndroidContext(this);

        setSupportActionBar(mToolbar);
        mNavigationDrawer = new NavigationDrawer(getFragmentManager(), this);

        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.content_frame, new MoviesFragment()).commit();
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
        if(mNavigationDrawer.drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
