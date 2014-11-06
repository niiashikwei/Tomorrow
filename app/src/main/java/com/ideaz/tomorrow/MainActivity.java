package com.ideaz.tomorrow;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;


public class MainActivity extends Activity {
    protected AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f);
    protected AlphaAnimation fadeOut = new AlphaAnimation( 1.0f , 0.0f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kickOffAnimation();

    }

    private void kickOffAnimation() {
        View splashTextView = findViewById(R.id.splashText);
        splashTextView.startAnimation(fadeIn);
        splashTextView.startAnimation(fadeOut);
        fadeIn.setDuration(2000);
        fadeIn.setFillAfter(true);
        fadeOut.setDuration(4000);
        fadeOut.setFillAfter(true);
        fadeOut.setStartOffset(3000+fadeIn.getStartOffset());
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
