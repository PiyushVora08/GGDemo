package aa.bb.cc.ggtesting.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import aa.bb.cc.ggtesting.MyAdsManager;
import aa.bb.cc.ggtesting.MyApp;
import aa.bb.cc.ggtesting.R;

public class MainActivityThree extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_three);

        findViewById(R.id.btnOne).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOne:
                MyApp.adsManager.showInterstitial(this, "one", new MyAdsManager.InternalAdListener() {
                    @Override
                    public void onAdClosed() {
                        startActivity(new Intent(MainActivityThree.this, MainActivityTwo.class));
                        finish();
                    }
                });
                break;
        }
    }
}