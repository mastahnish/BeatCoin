package pl.myosolutions.coinbe;

import android.app.Application;

import pl.myosolutions.coinbe.utils.fonts.FontManager;

/**
 * Created by Jacek on 2017-10-18.
 */

public class CoinbeApp extends Application{


    private static CoinbeApp instance;

    public static CoinbeApp getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        FontManager.getInstance().initialize(this, R.xml.fonts);

    }



}
