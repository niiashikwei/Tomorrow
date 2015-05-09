package com.ideaz.tomorrow.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.ideaz.tomorrow.R;
import com.ideaz.tomorrow.TomorrowApp;
import com.ideaz.tomorrow.rest.model.TomorrowActivity;
import com.ideaz.tomorrow.rest.service.ITomorrowService;

import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

public class ActivitySelectorActivity extends Activity {

    @Inject ITomorrowService service;
    private ListView listview;
    private ImageButton addActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TomorrowApp) getApplication()).inject(this);
        setContentView(R.layout.activity_type);
        listview = (ListView) findViewById(R.id.activity_listview);
        addActivityButton = (ImageButton) findViewById(R.id.add_activity_button);
        setUpListeners();
        updateAndGetActivitiesList();
    }

    @Override
    protected void onStart(){
        super.onStart();
        updateAndGetActivitiesList();
    }

    private void setUpAddActivityButton() {
        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked on new activity, launching activity creator!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ActivityCreatorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setUpListeners() {
        setupListEventHandler();
        setUpAddActivityButton();
    }

    private void setupListEventHandler() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Clicked on item, launching partner selection!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), PartnerSelectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateAndGetActivitiesList() {
        service.getActivities(new Callback<List<TomorrowActivity>>() {

            @Override
            public void success(List<TomorrowActivity> serverActivities, Response response) {
                Timber.i("network:success", "successful call, able to retrieve activity list");
                List<String> listOfActivities = TomorrowActivity.getActivityNameList(serverActivities);
                ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_list, listOfActivities);
                listview.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Timber.i("network:failure", "failed call, unable to retrieve activity list");
                Toast.makeText(getApplicationContext(), "Unable to retrieve activity list!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
