package com.test.in.app;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {

    BillingProcessor bp;
    public AdView adView;

    public TextView textView;
    public  final String parched = "android.test.purchased";
    public boolean ad = false;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adView = (AdView) findViewById(R.id.adView1);

        textView = (TextView) findViewById(R.id.test);
        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , Main2Activity.class);
                startActivity(intent);
            }
        });

        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvRM/LNQJCDM8BfwrcAPETpVwOqx4Kyj/vUywbCMP7neBglC75aP029MA7H9q5mh5SPH7mP1s5cuyvwnbwSCQubzxnniEt64GlLvYazyzh0loEQQ4wEXPmZUkOQaaanw0KoV3CP9ulNn+L53UkH3jowKBU17XMFLKZ86trCJSnUMbf806f1zUz+I+I3uGKZFv4k0K0xEE+2mTuaUyGMHpoi64LMPFe+rieQH/dsv/6Y7uXnUPu", this);

        if (bp.isPurchased(parched)){
            adDiseable();
        }
        else {
            showBannerAd();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bp.purchase(MainActivity.this, "android.test.purchased");
                //bp.purchase(MainActivity.this , "com.inappp.test.techdrive");
            }
        });

    }

    public void showBannerAd(){
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setVisibility(View.GONE);
        adView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {

                if (adView.getVisibility() == View.GONE) {
                    adView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdOpened() {

            }

            @Override
            public void onAdClosed() {

            }

            @Override
            public void onAdLeftApplication() {

            }
        });
    }

    public void adDiseable(){
        adView.setVisibility(View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        Log.d("productId" , ""+productId);
        if (productId.contains(parched)){
            adDiseable();
        }else {
        }

        Log.d("purchaseInfo" , ""+details.purchaseInfo);
        Log.d("productId" , ""+details.productId);
        Log.d("orderId" , ""+details.orderId);
        Log.d("purchaseToken" , ""+details.purchaseToken);
        Log.d("purchaseTime" , ""+details.purchaseTime);

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {
        Log.d("parches_error" , ""+errorCode);
    }

    @Override
    public void onBillingInitialized() {

    }
}
