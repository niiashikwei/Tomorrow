package com.ideaz.tomorrow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        if(!activityMatch()){
            launchActivityPartnerSelect();
        }else{
            launchActivityConfirm();
        }
    }

    private boolean activityMatch() {
        return false;
    }

    private void launchActivityConfirm() {
        Intent intent = new Intent(this, ConfirmActivity.class);
        startActivity(intent);

    }

    private void launchActivityPartnerSelect() {
        Intent intent = new Intent(this, PartnerSelectionActivity.class);
        startActivity(intent);
    }

}
