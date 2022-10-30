package com.example.part2.managementwithsmartwearables.ui.workdetail;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.databinding.ActivityWorkDetailBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WorkDetailActivity extends AppCompatActivity {

    private ActivityWorkDetailBinding binding;
    RecyclerView recyclerView;
    String adminIndex;
    //String workerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final ImageButton backButton = binding.backButton;
        //final TextView workerText = binding.workerText;
        recyclerView = binding.workDetailList;

        Intent intent = getIntent();
        adminIndex = intent.getStringExtra("index");
        //workerName = intent.getStringExtra("userName");

        new HttpAsyncTask().execute("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_a_WorkerList", adminIndex);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, ArrayList<Work>> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected ArrayList<Work> doInBackground(String... params) {
            ArrayList<Work> workList = new ArrayList<>();
            String strUrl = params[0];
            try {
                JSONObject input = new JSONObject();
                input.put("member_idx", params[1]);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(input.toString(), JSON);
                Request request = new Request.Builder()
                        .url(strUrl)
                        .post(body)
                        .build();

                Response response = client.newCall(request).execute();

                JSONObject jsonObject = new JSONObject(response.body().string());
                if (jsonObject.getString("result").equals("false")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    String errorMessage = jsonObject.getString("content");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                        }
                    }, 0);
                } else {
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("content"));
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject workObject = jsonArray.getJSONObject(i);
                        int idx = workObject.getInt("idx");
                        String name = workObject.getString("name");
                        String profile = workObject.getString("profile");
                        String workDetail = workObject.getString("work_detail");
                        int workStatus = workObject.getInt("work_status");
                        int memberIdx = workObject.getInt("member_idx");
                        workList.add(new Work(idx, new User(1, name, name, profile), workDetail, memberIdx, workStatus));
                    }
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return workList;
        }

        @Override
        protected void onPostExecute(ArrayList<Work> workList) {
            super.onPostExecute(workList);
            if (workList != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(WorkDetailActivity.this));
                recyclerView.setAdapter(new WorkDetailAdapter(workList));
            }
        }
    }
}