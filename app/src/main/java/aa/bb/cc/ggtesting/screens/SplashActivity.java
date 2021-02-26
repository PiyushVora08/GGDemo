package aa.bb.cc.ggtesting.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.greedygame.core.adview.modals.AdRequestErrors;
import com.greedygame.core.interstitial.general.GGInterstitialAd;
import com.greedygame.core.interstitial.general.GGInterstitialEventsListener;

import aa.bb.cc.ggtesting.AdModel;
import aa.bb.cc.ggtesting.MyAdsManager;
import aa.bb.cc.ggtesting.MyApp;
import aa.bb.cc.ggtesting.R;

public class SplashActivity extends AppCompatActivity {

    private GGInterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MyApp.adModel = new AdModel();
//        MyApp.adsManager.showInterstitial(this, "one", new MyAdsManager.InternalAdListener() {
//            @Override
//            public void onAdClosed() {
//                startActivity(new Intent(SplashActivity.this, MainActivityTwo.class));
//                finish();
//            }
//        });

        loadInterstitial();
    }

    private void loadInterstitial() {
        mInterstitialAd = new GGInterstitialAd(this, MyApp.adModel.adMobSplash);
        mInterstitialAd.setListener(new GGInterstitialEventsListener() {
            @Override
            public void onAdLoaded() {
                Log.e("GGADS", "Ad Loaded");
                if (mInterstitialAd.isAdLoaded()) {
                    mInterstitialAd.show(); //Displays the ad in a full screen activity
                }
            }

            @Override
            public void onAdLeftApplication() {
                Log.e("GGADS", "Ad Left Application");
            }

            @Override
            public void onAdClosed() {
                Log.e("GGADS", "Ad Closed");

                startNextScreen();
            }

            @Override
            public void onAdOpened() {
                Log.e("GGADS", "Ad Opened");
            }

            @Override
            public void onAdLoadFailed(AdRequestErrors cause) {
                Log.e("GGADS", "Ad Load Failed " + cause);
                startNextScreen();
            }
        });
        mInterstitialAd.loadAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        if (mInterstitialAd != null)
//            mInterstitialAd.destroy();
    }

    private void startNextScreen() {
        startActivity(new Intent(this, MainActivityOne.class));
        finish();
    }
}