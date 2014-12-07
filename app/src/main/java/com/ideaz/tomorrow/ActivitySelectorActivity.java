package com.ideaz.tomorrow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ideaz.tomorrow.rest.model.TomorrowActivity;
import com.ideaz.tomorrow.rest.service.ITomorrowService;
import com.ideaz.tomorrow.rest.service.RestClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import timber.log.Timber;

import static org.parceler.guava.collect.Lists.newArrayList;

public class ActivitySelectorActivity extends Activity {

    private ListView listview;
    private ITomorrowService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        service = new RestClient().getApiService();
        listview = (ListView) findViewById(R.id.activity_listview);
        setUpListeners();
        updateAndGetActivitiesList();
    }

    @Override
    protected void onStart(){
        super.onStart();
        updateAndGetActivitiesList();
    }

    private void setUpAddActivityButton() {
        View addActivityButton = findViewById(R.id.add_activity_button);
        addActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAddActivity();
            }
        });
    }

    private void setUpListeners() {
        setupListEvenHandler();
        setUpAddActivityButton();
    }

    private void setupListEvenHandler() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                launchPartnerSelection();
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

    private void launchPartnerSelection() {
        Toast.makeText(getApplicationContext(), "Clicked on item, launching partner selection!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PartnerSelectionActivity.class);
        startActivity(intent);
    }


    private void launchAddActivity() {
        Toast.makeText(getApplicationContext(), "Launching Activity!", Toast.LENGTH_SHORT).show();
    }

}
