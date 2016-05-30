package com.ciacavus.jsonparsing;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class MainActivity extends AppCompatActivity {

    ArrayList<Actors> listItem;

    ActorAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create new actors array
        listItem = new ArrayList<Actors>();

        new JSONAsyncTask().execute("http://microblogging.wingity.com/JSONParsingTutorial/jonActors");

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

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog pd;

        protected void OnPreExecute() {
            super.onPreExecute();

            //performing logic before execution of thread
            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please Wait");
            pd.setTitle("Connecting...");
            pd.show();
            pd.setCancelable(false);
        }

        //perform in the background
        @Override
        protected Boolean doInBackground(String... urls) {

            try {
                //perform a HTTP request to get the data
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                int status = response.getStatusLine().getStatusCode();

                //if the status returned is successful
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    JSONObject jsonObj = new JSONObject(data);
                    JSONArray jsonArr = jsonObj.getJSONArray("actors");

                    //for each entity in the array list, populate the data
                    for (int i = 0; i < jsonArr.length(); i++) {
                        //get an object from the corresponding array value in the JSON Array
                        JSONObject obj = jsonArr.getJSONObject(i);

                        //create a new actor
                        Actors actor = new Actors();

                        actor.setName(obj.getString("name"));
                        actor.setDesc(obj.getString("description"));
                        actor.setDob(obj.getString("dob"));
                        actor.setCountry(obj.getString("country"));
                        actor.setHeight(obj.getString("height"));
                        actor.setSpouse(obj.getString("spouse"));
                        actor.setChildren(obj.getString("children"));
                        actor.setImage(obj.getString("image"));

                        //add actors to the list
                        listItem.add(actor);

                    }

                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        protected void onPostExecute(Boolean result)
        {
            pd.cancel();
            adp.notifyDataSetChanged();
            if(!result)
            {
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
            }
        }
    }
}
