package com.ciacavus.jsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Actors> listItem;

    ActorAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create new actors array
        listItem = new ArrayList<Actors>();

        //link XML with Java Code
        ListView lv = (ListView)findViewById(R.id.list);

        adp = new ActorAdapter(getApplicationContext(),R.layout.row,listItem);

        lv.setAdapter(null);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //on click
                Toast.makeText(getApplicationContext(),listItem.get(position).getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
