package com.ideaz.tomorrow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ActivitySelectorActivity extends Activity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        listview = (ListView) findViewById(R.id.activity_listview);
        setUpActivityList();
        setUpAddActivityButton();
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

    private void setUpActivityList() {
        String[] values = getAvailableActivities();
        final ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_list, values);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                launchPartnerSelection();
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

    private String[] getAvailableActivities() {
        return new String[] { "Tennis", "Basketball", "Karaoke" };
    }
}
