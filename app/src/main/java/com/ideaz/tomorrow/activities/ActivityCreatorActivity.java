package com.ideaz.tomorrow.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ideaz.tomorrow.R;
import com.ideaz.tomorrow.TomorrowApp;
import com.ideaz.tomorrow.rest.model.TomorrowActivity;
import com.ideaz.tomorrow.rest.service.ITomorrowService;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivityCreatorActivity extends Activity {
    @Inject
    ITomorrowService service;
    private static final int RESULT_LOAD_IMAGE = 1;
    private ImageView activityPicView;
    private EditText activityNameView;
    private EditText minTeamSizeView;
    private EditText maxTeamSizeView;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.create_activity);
        ((TomorrowApp) getApplication()).inject(this);
        activityPicView = (ImageView) findViewById(R.id.activity_picture);
        activityNameView = (EditText) findViewById(R.id.activity_name);
        minTeamSizeView = (EditText) findViewById(R.id.min_participants);
        maxTeamSizeView = (EditText) findViewById(R.id.max_participants);
        setNextButtonListener();
        setActivityPictureListener();
    }

    private void setActivityPictureListener() {
        View viewById = findViewById(R.id.activity_picture);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Picasso.with(getApplicationContext()).load(picturePath).into(activityPicView);
        }
    }

    private void setNextButtonListener() {
        View nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = activityNameView.getText().toString();
                Integer minTeamSize = Integer.valueOf(minTeamSizeView.getText().toString());
                Integer maxTeamSize = Integer.valueOf(maxTeamSizeView.getText().toString());
                TomorrowActivity newActivity = new TomorrowActivity(name, minTeamSize, maxTeamSize);

                service.createActivity(newActivity, new Callback<TomorrowActivity>() {
                    @Override
                    public void success(TomorrowActivity tomorrowActivity, Response response) {
                        Toast.makeText(getApplicationContext(), "activity created on server: " + tomorrowActivity.getName(), Toast.LENGTH_SHORT).show();
                        SharedPreferences settings = getSharedPreferences(TomorrowApp.SETTINGS, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putInt(TomorrowActivity.Tomorrow_Activity_ID, tomorrowActivity.getId());
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), ActivitySelectorActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplicationContext(), "activity creation failed: " + error.getResponse(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
