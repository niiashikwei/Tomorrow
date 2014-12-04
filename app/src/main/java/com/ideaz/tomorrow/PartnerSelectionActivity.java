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
import java.util.Stack;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class PartnerSelectionActivity extends Activity {
    private ITomorrowService service;
    private ImageView userImage;
    private Stack<User> nearbyUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_selector);
        service = new RestClient().getApiService();
        nearbyUsers = new Stack<User>();
        userImage = (ImageView) findViewById(R.id.userImage);
        attachListeners();
        loadNextUserIntoImageView();
    }

    @Override
    protected void onStart(){
        super.onStart();
        service = new RestClient().getApiService();
    }

    private void attachListeners() {
        setPassButtonListener();
        setSelectButtonListener();
        setInfoButtonListener();
    }

    private void loadNextUserIntoImageView() {
        Log.i("network", "retrieving nearby users");
        if(nearbyUsers.isEmpty()){
            service.getUsers(new Callback<List<User>>() {
                @Override
                public void success(List<User> users, Response response) {
                    Log.i("network:success", "retrieved nearby users");
                    nearbyUsers.addAll(users);
                    displayNextUser();
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Log.i("network:failure", "failed call, unable to retrieve nearby users");
                    loadDefaultProfilePic();
                }
            });
        } else {
            displayNextUser();
        }
    }

    private void displayNextUser() {
        if(!nearbyUsers.isEmpty()) {
            Log.i("loadNextUserIntoImageView", "loading from retrieved list");
            User nextUser = nearbyUsers.pop();
            String profilePicUrl = nextUser.getProfilePicUrl();
            Picasso.with(getApplicationContext()).load(profilePicUrl).into(userImage);
        }else{
            Log.i("loadNextUserIntoImageView", "no users retrieved from server");
        }
    }

    private void loadDefaultProfilePic() {
        Picasso.with(getApplicationContext()).load(R.drawable.user_image_default).into(userImage);
    }

    private void setSelectButtonListener() {
        ImageButton selectButton = (ImageButton) findViewById(R.id.selectButton);
        selectButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                loadNextUserIntoImageView();
                return true;
            }
        });
    }

    private void setPassButtonListener() {
        ImageButton passButton = (ImageButton) findViewById(R.id.passButton);
        passButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                loadNextUserIntoImageView();
                return true;
            }
        });
    }


    private void setInfoButtonListener() {
        ImageButton passButton = (ImageButton) findViewById(R.id.infoButton);
        passButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.i("network", "nearbyUsers: "+ nearbyUsers.toString());
                return true;
            }
        });
    }

}
