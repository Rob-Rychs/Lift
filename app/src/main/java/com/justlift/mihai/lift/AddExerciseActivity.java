package com.justlift.mihai.lift;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mihai on 16-04-21.
 */
public class AddExerciseActivity extends AppCompatActivity {
    private DatabaseHelper myDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_exercise);

        myDbHelper = DatabaseHelper.getInstance(this);

        ListView listView = (ListView) findViewById(R.id.list);

        ArrayList<String> categoryList = new ArrayList<String>();
        categoryList = myDbHelper.getCategories();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, categoryList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String catClicked = (String)parent.getItemAtPosition(position);
                Log.e("AddExerciseActivity","Category selected: " + catClicked);
                Intent intent = new Intent(MainActivity.getInstance(), ExerciseListActivity.class);
                intent.putExtra("category", catClicked);
                startActivityForResult(intent, 1);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String exerciseClicked = data.getStringExtra("exercise");
                Intent intent = new Intent();
                intent.putExtra("exercise", exerciseClicked);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
