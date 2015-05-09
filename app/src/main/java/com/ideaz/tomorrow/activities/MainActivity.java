package com.ideaz.tomorrow.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.ideaz.tomorrow.R;
import com.ideaz.tomorrow.TomorrowApp;
import com.ideaz.tomorrow.rest.model.User;
import com.ideaz.tomorrow.rest.service.ITomorrowService;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {
    @Inject ITomorrowService service;
    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f);
    protected AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TomorrowApp) getApplication()).inject(this);
        setContentView(R.layout.activity_main);
        kickOffAnimation();
    }

    @Override
    protected void onStart(){
        super.onStart();
        kickOffAnimation();
    }

    @Override
    protected void onResume(){
        super.onResume();
        kickOffAnimation();
    }

    private void kickOffAnimation() {
        View splashTextView = findViewById(R.id.splashText);
        splashTextView.startAnimation(fadeIn);
        splashTextView.startAnimation(fadeOut);
        fadeIn.setDuration(2000);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(2000);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(fadeIn.getDuration() + fadeIn.getStartOffset());
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                SharedPreferences settings = getSharedPreferences(TomorrowApp.SETTINGS, 0);
                Integer userId = settings.getInt(User.USER_ID, User.NO_LOCAL_USER);
                if (userId != User.NO_LOCAL_USER){
                    verifyCurrentUserOnServer(userId);

                }else{
                    launchProfileSetup();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    private void verifyCurrentUserOnServer(Integer userId) {
        service.getUser(userId, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Toast.makeText(getApplicationContext(), "Welcome back " + user.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ActivitySelectorActivity.class);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "User doesn't exist on server", Toast.LENGTH_SHORT).show();
                launchProfileSetup();
            }
        });
    }

    private void launchProfileSetup() {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
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
}
