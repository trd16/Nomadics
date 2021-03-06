package edu.fsu.cs.nomadics;

//retrieved background from: https://www.designbolts.com/2015/07/25/40-best-iphone-6-wallpapers-backgrounds-in-hd-quality/
//retrieved back arrow from: https://www.pikpng.com/pngvi/iimiwib_go-back-button-comments-back-button-png-icon/

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements
        MainFragment.OnFragmentInteractionListener, PlacesFragment.OnPlacesInteractionListener,
        BookmarkFragment.OnBookmarkInteractionListener,
        WeatherFragment.OnWeatherInteractionListener, MapsFragment.OnMapsInteractionListener,
        PlacesFragment.OnAddressClickListener, RecyclerViewAdapter.OnBookmarkClickListener {
    MainFragment mainfragment;
    WeatherFragment weatherfragment;
    PlacesFragment placesfragment;
    BookmarkFragment bookmarkfragment;
    MapsFragment mapsfragment;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1346;

    //this function is used to startup the activity and sets the screen to automatically be fullscreen; it also calls onMain to set the first view as the main fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        onMain();

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
    }

    //this function is used to set the view to the mainfragment
    public void onMain() {
        mainfragment = new MainFragment();
        String tag = MainFragment.class.getCanonicalName();
        FrameLayout fl = (FrameLayout) findViewById(R.id.frame);
        fl.removeAllViews();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, mainfragment, tag).commit();
    }

    //this function is utilized when the weather button is pressed to transition to the weather fragment
    @Override
    public void onStartWeather() {
        weatherfragment = new WeatherFragment();
        String tag = WeatherFragment.class.getCanonicalName();
        FrameLayout fl = (FrameLayout) findViewById(R.id.frame);
        fl.removeAllViews();

        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, weatherfragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    //this fragment is utilized when the places button is pressed to transition to the places fragment
    @Override
    public void onStartPlaces() {
        placesfragment = new PlacesFragment();
        String tag = PlacesFragment.class.getCanonicalName();
        FrameLayout fl = (FrameLayout) findViewById(R.id.frame);
        fl.removeAllViews();

        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, placesfragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    //this function is utilized when the bookmarks button is pressed to transition to that fragment
    @Override
    public void onStartBookmarks() {
        bookmarkfragment = new BookmarkFragment();
        String tag = BookmarkFragment.class.getCanonicalName();
        FrameLayout fl = (FrameLayout) findViewById(R.id.frame);
        fl.removeAllViews();

        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, bookmarkfragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    
    //this function is used in the other fragments when the button is pressed to return to the main fragment
    @Override
    public void onStartMaps() {
        mapsfragment = new MapsFragment();
        String tag = MapsFragment.class.getCanonicalName();
        FrameLayout f1 = (FrameLayout) findViewById(R.id.frame);
        f1.removeAllViews();

        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, mapsfragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onReturnHome(){
        mainfragment = new MainFragment();
        String tag = BookmarkFragment.class.getCanonicalName();
        FrameLayout fl = (FrameLayout) findViewById(R.id.frame);
        fl.removeAllViews();

        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame, mainfragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onAddressClickListener(String name, double lat, double lon) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putDouble("lat", lat);
        bundle.putDouble("long", lon);

        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onBookmarkClickListener(String name, double lat, double lon) {
        onAddressClickListener(name, lat, lon);
    }
}
