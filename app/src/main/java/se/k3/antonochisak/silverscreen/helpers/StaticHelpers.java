package se.k3.antonochisak.silverscreen.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by anton on 2015-04-14.
 */
public class StaticHelpers {

    public static final String FIREBASE_TOP_MOVIES = "top_movies";
    public static final String FIREBASE_URL = "https://klara.firebaseio.com/";

    public static final String TRAKT_CONTENT_TYPE = "Content-type: application/json";
    public static final String TRAKT_API_KEY = "trakt-api-key: 6669f7e61b6df49bc8faf9fdaf3f3ddc0185c60d5d18e8305cd2ddc18e10238c";
    public static final String TRAKT_API_VERSION = "trakt-api-version: 2";

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }
    public static int getPixelsFromDp(Context context, int dp) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

}
