package aa.bb.cc.ggtesting;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.greedygame.core.adview.GGAdview;
import com.greedygame.core.adview.interfaces.AdLoadCallback;
import com.greedygame.core.adview.modals.AdRequestErrors;
import com.greedygame.core.interstitial.general.GGInterstitialAd;
import com.greedygame.core.interstitial.general.GGInterstitialEventsListener;

import org.jetbrains.annotations.NotNull;

public class MyAdsManager {

    private final Context context;
    private GGInterstitialAd mInterstitialAd;

    private InternalAdListener mInternalListener = null;
    private boolean reloadNewInterstitial = true;

    public MyAdsManager(Context context, boolean loadInterstitial) {
        this.context = context;

        MyApp.adModel = new AdModel();
        if (loadInterstitial)
            loadInterstitial();
    }

    public void getBannerAd(Context context, RelativeLayout ll_banner) {

        GGAdview ggAdView = new GGAdview(context);
        ggAdView.setUnitId(MyApp.adModel.adMobBanner);  //Replace with your Ad Unit ID here
        ggAdView.setAdsMaxHeight(250); //Value is in pixels, not in dp

        long diff = System.currentTimeMillis() - MyApp.lastBannerImpressionLoaded;
        if (BuildConfig.DEBUG)
            Log.e("TEST", "Banner Difference = " + diff);


        RelativeLayout.LayoutParams lParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lParam.addRule(RelativeLayout.CENTER_IN_PARENT);
        lParam.setMargins(5, 5, 5, 5);
        MyApp.lastBannerImpressionLoaded = System.currentTimeMillis();
        ll_banner.removeAllViews();
        ll_banner.addView(ggAdView, lParam);

        ggAdView.loadAd(new AdLoadCallback() {

            @Override
            public void onReadyForRefresh() {

            }

            @Override
            public void onUiiClosed() {

            }

            @Override
            public void onUiiOpened() {

            }

            @Override
            public void onAdLoadFailed(@NotNull AdRequestErrors cause) {

            }

            @Override
            public void onAdLoaded() {
                //new Handler(Looper.getMainLooper()).postDelayed(() -> ((AppCompatActivity) context).runOnUiThread(() -> {
//                    ll_banner.removeAllViews();
//                    ll_banner.addView(ggAdView, lParam);
                //}), 1000);
            }
        });
    }

    public void getNativeAd(Context context, final FrameLayout frameLayout) {

        GGAdview ggAdView = new GGAdview(context);
        ggAdView.setUnitId(MyApp.adModel.adMobNative);  //Replace with your Ad Unit ID here
        ggAdView.setAdsMaxHeight(1000); //Value is in pixels, not in dp

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 1000);
        frameLayout.removeAllViews();
        frameLayout.addView(ggAdView, layoutParams);

        ggAdView.loadAd(new AdLoadCallback() {
            @Override
            public void onReadyForRefresh() {

            }

            @Override
            public void onUiiClosed() {

            }

            @Override
            public void onUiiOpened() {

            }

            @Override
            public void onAdLoadFailed(@NotNull AdRequestErrors cause) {

            }

            @Override
            public void onAdLoaded() {
                //new Handler(Looper.getMainLooper()).postDelayed(() -> ((AppCompatActivity)context).runOnUiThread(() -> {
//                frameLayout.removeAllViews();
//                frameLayout.addView(ggAdView, layoutParams);
                //}), 1000);
            }
        });
    }

    private void loadInterstitial() {

        if (mInterstitialAd != null)
            mInterstitialAd.destroy();

        mInterstitialAd = new GGInterstitialAd(context, MyApp.adModel.adMobInter);
        mInterstitialAd.setListener(mInterstitialListener);
        mInterstitialAd.loadAd();
    }

    public boolean canShowDialogbox(String key) {

        return true;
    }

    public void showInterstitial(Context context, String key, InternalAdListener mInternalListener) {

        showInterstitial(key, true, mInternalListener);
    }

    public void showInterstitial(String key, boolean reloadNewInterstitial, InternalAdListener mInternalListener) {

        this.mInternalListener = mInternalListener;
        this.reloadNewInterstitial = reloadNewInterstitial;

        if (mInterstitialAd != null && mInterstitialAd.isAdLoaded()) {
            MyApp.lastInterstitialImpressionLoaded = System.currentTimeMillis();
            mInterstitialAd.show();
        } else {
            if (mInternalListener != null)
                mInternalListener.onAdClosed();
            if (mInterstitialAd != null && !mInterstitialAd.isAdLoaded())
                loadInterstitial();
        }
    }

    public interface InternalAdListener {
        void onAdClosed();
    }

    private final GGInterstitialEventsListener mInterstitialListener = new GGInterstitialEventsListener() {

        @Override
        public void onAdLoaded() {
        }

        @Override
        public void onAdLeftApplication() {

        }

        @Override
        public void onAdClosed() {
            if (mInternalListener != null)
                mInternalListener.onAdClosed();

            if (mInterstitialAd != null)
                mInterstitialAd.destroy();

            if (reloadNewInterstitial)
                loadInterstitial();
        }

        @Override
        public void onAdOpened() {

        }

        @Override
        public void onAdLoadFailed(@NotNull AdRequestErrors cause) {

        }
    };
}
