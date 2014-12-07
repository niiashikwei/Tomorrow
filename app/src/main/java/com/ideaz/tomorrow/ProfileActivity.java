package com.ideaz.tomorrow;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ideaz.tomorrow.rest.model.User;
import com.ideaz.tomorrow.rest.service.ITomorrowService;
import com.ideaz.tomorrow.rest.service.RestClient;
import com.squareup.picasso.Picasso;


public class ProfileActivity extends Activity {
    private ITomorrowService service;
    private static final int RESULT_LOAD_IMAGE = 1;
    private ImageView profilePic;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        service = new RestClient().getApiService();
        profilePic = (ImageView) findViewById(R.id.profile_picture);
        currentUser = loadUserProfile();
        setNextButtonListener();
        setProfilePictureListener();
    }

    private void setProfilePictureListener() {
        View viewById = findViewById(R.id.profile_picture);
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

            Picasso.with(getApplicationContext()).load(picturePath).into(profilePic);
        }
    }

    private void setNextButtonListener() {
        View nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkForValidName() && checkForValidAge()){
                    saveProfile();
                    launchActivitySelector();
                }
            }
        });
    }

    private void saveProfile() {
        Toast.makeText(getApplicationContext(), "Saving profile information", Toast.LENGTH_SHORT).show();
        service.saveProfile();
    }


    private User loadUserProfile() {
        return new User("Tony", 20, "some/url/here");
    }

    private boolean checkForValidName() {
        boolean isNameValid = false;
        EditText viewById = (EditText) findViewById(R.id.user_name);
        String name = viewById.getText().toString();
        if(!name.isEmpty()){
            if (name.length() > 2){
                isNameValid = true;
                Toast.makeText(getApplicationContext(), "Valid Name, good job!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Sorry, your name is too short!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "Please enter a valid name!", Toast.LENGTH_SHORT).show();
        }
        return isNameValid;
    }

    private boolean checkForValidAge() {
        boolean isValidAge = false;
        EditText viewById = (EditText) findViewById(R.id.user_age);
        String userAge = viewById.getText().toString();
        if(!userAge.isEmpty()){
            Integer age = Integer.parseInt(userAge);
            if (age < 18){
                Toast.makeText(getApplicationContext(), "Sorry, you're not old enough to use this app!!", Toast.LENGTH_SHORT).show();
            } else if (age >= 100){
                Toast.makeText(getApplicationContext(), "How can you be older than 100 years??", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "You're just the right age! :-)", Toast.LENGTH_SHORT).show();
                isValidAge = true;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Please enter valid age", Toast.LENGTH_SHORT).show();
        }
        return isValidAge;
    }

    private void launchActivitySelector() {
        Intent intent = new Intent(this, ActivitySelectorActivity.class);
        startActivity(intent);
    }


}
