package edu.fsu.cs.nomadics;


//retrieved background from: https://www.designbolts.com/2015/07/25/40-best-iphone-6-wallpapers-backgrounds-in-hd-quality/
//retrieved back arrow from: https://www.pikpng.com/pngvi/iimiwib_go-back-button-comments-back-button-png-icon/


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.FrameLayout;


public class MainActivity extends AppCompatActivity implements PlacesDialog.OnFragmentInteractionListener, MainFragment.OnFragmentInteractionListener, PlacesFragment.OnPlacesInteractionListener, BookmarkFragment.OnBookmarkInteractionListener, WeatherFragment.OnWeatherInteractionListener {
    MainFragment mainfragment;
    WeatherFragment weatherfragment;
    PlacesFragment placesfragment;
    BookmarkFragment bookmarkfragment;

    FrameLayout fl;
    FragmentTransaction trans;

    //this function is used to startup the activity and sets the screen to automatically be fullscreen; it also calls onMain to set the first view as the main fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        onMain();
    }

    //this function is used to set the view to the mainfragment
    public void onMain() {

        mainfragment = new MainFragment();
        String tag = MainFragment.class.getCanonicalName();
        FrameLayout fl = (FrameLayout) findViewById(R.id.frame);
        fl.removeAllViews();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, mainfragment, tag).commit();



    }

    @Override
    public void onStartMaps(){


    }

    //function used to open phone application after button clicked in places dialog
    @Override
    public void onStartPhone(){


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

}
