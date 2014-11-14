package com.ideaz.tomorrow;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ideaz.tomorrow.rest.model.User;
import com.ideaz.tomorrow.rest.service.ITomorrowService;
import com.ideaz.tomorrow.rest.service.RestClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PartnerSelectionActivity extends Activity {
    ITomorrowService service;
    ImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_selector);
        service = new RestClient().getApiService();
        userImage = (ImageView) findViewById(R.id.userImage);
        attachListeners();
    }

    private void attachListeners() {
        setPassButtonListener();
        setSelectButtonListener();
        setInfoButtonListener();
    }

    private void setInfoButtonListener() {
        ImageButton infoButton = (ImageButton) findViewById(R.id.infoButton);
        infoButton.setOnTouchListener(new View.OnTouchListener() {
            Callback<List<User>> getUsersCallback = new Callback<List<User>>() {
                @Override
                public void success(List<User> users, Response response) {
                    Log.i("network:success", "successful call: "+ response.getBody() + "\n users: " + users);
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.i("network:failure", "failed call: "+ retrofitError.getBody());
                }
            };
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                service.getUsers(getUsersCallback);
                return true;
            }
        });
    }

    private void setSelectButtonListener() {
        ImageButton selectButton = (ImageButton) findViewById(R.id.selectButton);
        selectButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Picasso.with(PartnerSelectionActivity.this).load(R.drawable.user1).into(userImage);
                return true;
            }
        });
    }

    private void setPassButtonListener() {
        ImageButton passButton = (ImageButton) findViewById(R.id.passButton);
        passButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Picasso.with(PartnerSelectionActivity.this).load(R.drawable.user2).into(userImage);
                return true;
            }
        });
    }

}
