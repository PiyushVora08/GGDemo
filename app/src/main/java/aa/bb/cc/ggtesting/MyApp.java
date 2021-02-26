package aa.bb.cc.ggtesting;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.greedygame.core.AppConfig;
import com.greedygame.core.GreedyGameAds;

public class MyApp extends MultiDexApplication {

    public static MyAdsManager adsManager;
    public static AdModel adModel;

    boolean mRatingBoxRecentlyVisible = false;

    public static long lastInterstitialImpressionLoaded = 0L;
    public static long lastBannerImpressionLoaded = 0L;

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);

        AppConfig appConfig = new AppConfig.Builder(this)
                .withAppId("36639239")  //Replace the app ID with your app's ID
                .build();
        GreedyGameAds.initWith(appConfig, null);

        adsManager = new MyAdsManager(getApplicationContext(), true);
    }
}
