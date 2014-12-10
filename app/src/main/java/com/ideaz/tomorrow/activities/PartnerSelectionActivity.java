package com.ideaz.tomorrow.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ideaz.tomorrow.R;
import com.ideaz.tomorrow.rest.model.User;
import com.ideaz.tomorrow.rest.service.ITomorrowService;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Stack;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;


public class PartnerSelectionActivity extends Activity {
    @Inject ITomorrowService service;
    private ImageView userImage;
    private Stack<User> nearbyUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_selector);
        userImage = (ImageView) findViewById(R.id.userImage);
        nearbyUsers = new Stack<User>();
        attachListeners();
        loadNextUserIntoImageView();
        Timber.tag("LifeCycles");
    }

    private void attachListeners() {
        setPassButtonListener();
        setSelectButtonListener();
        setInfoButtonListener();
    }

    private void loadNextUserIntoImageView() {
        Timber.i("network", "retrieving nearby users");
        if(nearbyUsers.isEmpty()){
            service.getUsers(new Callback<List<User>>() {
                @Override
                public void success(List<User> users, Response response) {
                    Timber.i("network:success", "retrieved nearby users");
                    nearbyUsers.addAll(users);
                    displayNextUser();
                }

                @Override
                public void failure(RetrofitError retrofitError) {
                    Timber.i("network:failure", "failed call, unable to retrieve nearby users");
                    loadDefaultProfilePic();
                }
            });
        } else {
            displayNextUser();
        }
    }

    private void displayNextUser() {
        if(!nearbyUsers.isEmpty()) {
            Timber.i("loadNextUserIntoImageView", "loading from retrieved list");
            User nextUser = nearbyUsers.pop();
            String profilePicUrl = nextUser.getProfilePicUrl();
            Picasso.with(getApplicationContext()).load(profilePicUrl).into(userImage);
        }else{
            Timber.i("loadNextUserIntoImageView", "no users retrieved from server");
        }
    }

    private void loadDefaultProfilePic() {
        Picasso.with(getApplicationContext()).load(R.drawable.user_image_default).into(userImage);
    }

    private void setSelectButtonListener() {
        ImageButton selectButton = (ImageButton) findViewById(R.id.selectButton);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNextUserIntoImageView();
            }
        });
    }

    private void setPassButtonListener() {
        ImageButton passButton = (ImageButton) findViewById(R.id.passButton);
        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNextUserIntoImageView();
            }
        });
    }


    private void setInfoButtonListener() {
        ImageButton passButton = (ImageButton) findViewById(R.id.infoButton);
        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Timber.i("network", "nearbyUsers: " + nearbyUsers.toString());
            }
        });
    }

}
