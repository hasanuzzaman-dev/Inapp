package com.test.in.app;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

public class Main2Activity extends AppCompatActivity implements BillingProcessor.IBillingHandler{

    public BillingProcessor billingProcessor;
    public  final String parched = "android.test.purchased";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        billingProcessor = new BillingProcessor(Main2Activity.this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvRM/LNQJCDM8BfwrcAPETpVwOqx4Kyj/vUywbCMP7neBglC75aP029MA7H9q5mh5SPH7mP1s5cuyvwnbwSCQubzxnniEt64GlLvYazyzh0loEQQ4wEXPmZUkOQaaanw0KoV3CP9ulNn+L53UkH3jowKBU17XMFLKZ86trCJSnUMbf806f1zUz+I+I3uGKZFv4k0K0xEE+2mTuaUyGMHpoi64LMPFe+rieQH/dsv/6Y7uXnUPu", this);

        if (billingProcessor.isPurchased(parched)==true){
            Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }
}
