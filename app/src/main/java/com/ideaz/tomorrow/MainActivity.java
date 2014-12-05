package com.ideaz.tomorrow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;


public class MainActivity extends Activity {
    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f);
    protected AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kickOffAnimation();
    }

    @Override
    protected void onStart(){
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
                if(!validProfile()){
                    launchProfileSetup();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    private void launchProfileSetup() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void launchPreferencesSetup() {
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }

    private boolean preferencesValid() {
        return true;
    }

    private boolean validProfile() {
        return false;
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
