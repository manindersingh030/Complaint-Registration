package com.example.pawan.complaintregistration.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.pawan.complaintregistration.adapters.RecyclerViewAdapter;
import com.example.pawan.complaintregistration.R;
import com.example.pawan.complaintregistration.models.FeedItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 26/10/16.
 */
public class ShowData extends AppCompatActivity {
    private static final String TAG = "RecyclerViewExample";
    private List<FeedItem> feedsList;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        parseResult(result);
        adapter = new RecyclerViewAdapter(ShowData.this, feedsList);
        mRecyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
//
//        String url = "http://stacktips.com/?json=get_category_posts&slug=news&count=30";
//        new DownloadTask().execute(url);
    }
//
//    public class DownloadTask extends AsyncTask<String, Void, Integer> {
//
//        @Override
//        protected void onPreExecute() {
//            progressBar.setVisibility(View.VISIBLE);
//        }
//
//        @Override
//        protected Integer doInBackground(String... params) {
//            Integer result = 0;
//            HttpURLConnection urlConnection;
//            try {
//                URL url = new URL(params[0]);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                int statusCode = urlConnection.getResponseCode();
//
//                // 200 represents HTTP OK
//                if (statusCode == 200) {
//                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while ((line = r.readLine()) != null) {
//                        response.append(line);
//                    }
//                    parseResult(response.toString());
//                    result = 1; // Successful
//                } else {
//                    result = 0; //"Failed to fetch data!";
//                }
//            } catch (Exception e) {
//                Log.d(TAG, e.getLocalizedMessage());
//            }
//            return result; //"Failed to fetch data!";
//        }
//
//        @Override
//        protected void onPostExecute(Integer result) {
//            progressBar.setVisibility(View.GONE);
//
//            if (result == 1) {
//                adapter = new RecyclerViewAdapter(MainActivity.this, feedsList);
//                mRecyclerView.setAdapter(adapter);
//            } else {
//                Toast.makeText(MainActivity.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    private void parseResult(String result) {
        Log.d(TAG, "parseResult() called with: result = [" + result + "]");
        try {
            JSONObject jsonObject = new JSONObject(result);
            feedsList = new ArrayList<>();
            Log.d("abc", "login() called with: " +jsonObject.get("count")+ "");
            for(int i=0;i<Integer.parseInt(jsonObject.get("count").toString());i++){
                JSONObject inside=jsonObject.getJSONObject(String.valueOf(i));
                FeedItem item = new FeedItem();
                Log.d("abc", inside.getString("street") + " " + inside.getString("colony") + " " + inside.getString("city") + " " + inside.getString("zipcode") + " " +
                        inside.getString("phoneno") + " " + inside.getString("complaintdetails")  + " "+ inside.getString("status")  + " " + inside.getString("image"));
                String title =  "Phone:-" + inside.getString("phoneno") + ", " + "ComplaintDetails:-" + inside.getString("complaintdetails")+", " + "Complaint Status:-" + inside.getString("status");
                item.setTitle(title);
                Log.d(TAG, "parseResult() called with: result = [" + inside.getString("image") + "]");
                item.setThumbnail(inside.getString("image"));
                feedsList.add(item);
            }

//            JSONObject response = new JSONObject(result);
//            JSONArray posts = response.optJSONArray("posts");
//            feedsList = new ArrayList<>();
//
//            for (int i = 0; i < posts.length(); i++) {
//                JSONObject post = posts.optJSONObject(i);
//                FeedItem item = new FeedItem();
//                String title = post.optString("street") + ", " + post.optString("colony") + ", " + post.optString("city") + ", " + post.optString("zipcode");
//                item.setTitle(title);
//                item.setThumbnail(post.optString("image"));
//                feedsList.add(item);
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
///}
