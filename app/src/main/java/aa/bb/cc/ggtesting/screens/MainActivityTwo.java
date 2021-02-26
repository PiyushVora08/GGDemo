package aa.bb.cc.ggtesting.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import aa.bb.cc.ggtesting.MyAdsManager;
import aa.bb.cc.ggtesting.MyApp;
import aa.bb.cc.ggtesting.R;

public class MainActivityTwo extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);

        findViewById(R.id.btnOne).setOnClickListener(this);
        findViewById(R.id.btnTwo).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                MyApp.adsManager.showInterstitial(this, "one", new MyAdsManager.InternalAdListener() {
                    @Override
                    public void onAdClosed() {
                        startActivity(new Intent(MainActivityTwo.this, MainActivityThree.class));
                        finish();
                    }
                });
                break;
            case R.id.btnTwo:
                MyApp.adsManager.showInterstitial(this, "one", new MyAdsManager.InternalAdListener() {
                    @Override
                    public void onAdClosed() {
                        startActivity(new Intent(MainActivityTwo.this, MainActivityOne.class));
                        finish();
                    }
                });
                break;
        }
    }
}