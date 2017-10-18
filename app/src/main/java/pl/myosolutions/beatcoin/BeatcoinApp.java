package pl.myosolutions.beatcoin;

import android.app.Application;

import pl.myosolutions.beatcoin.utils.fonts.FontManager;

/**
 * Created by Jacek on 2017-10-18.
 */

public class BeatcoinApp extends Application{


    private static BeatcoinApp instance;

    public static BeatcoinApp getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        FontManager.getInstance().initialize(this, R.xml.fonts);

    }



}
