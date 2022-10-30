package com.example.part2.managementwithsmartwearables.ui.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.databinding.ActivityWorkerMainBinding;

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

public class WorkerMainActivity extends AppCompatActivity {

    private ActivityWorkerMainBinding binding;
    private String workerName;
    private String workerIndex;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWorkerMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button qrcodeButton = binding.qrcode;
        final TextView nameTextView = binding.workerNameText;
        recyclerView = binding.workList;

        Intent intent = getIntent();
        workerName = intent.getStringExtra("name");
        workerIndex = intent.getStringExtra("index");
        nameTextView.setText(workerName);

        new HttpAsyncTask().execute("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_w_WorkList", workerIndex);

        qrcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO QR : QR코드 액티비티와 연결
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
                input.put("user_idx", params[1]);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(input.toString(), JSON);
                Request request = new Request.Builder()
                        .url(strUrl)
                        .post(body)
                        .build();

                Response response = client.newCall(request).execute();

                JSONObject jsonObject = new JSONObject(response.body().string());
                if (jsonObject.getString("result").equals("false")) {
                    Toast.makeText(getApplicationContext(), jsonObject.getString("content"), Toast.LENGTH_LONG).show();
                } else {
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("content"));
                    for (int i = 0; i < jsonArray.length() ; i++) {
                        JSONObject workObject = jsonArray.getJSONObject(i);
                        int idx = workObject.getInt("idx");
                        String name = workObject.getString("name");
                        String profile = workObject.getString("profile");
                        String workDetail = workObject.getString("work_detail");
                        int workStatus = workObject.getInt("work_status");
                        workList.add(new Work(idx, new User(1, name, name, profile), workDetail, 0, workStatus));
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
                recyclerView.setLayoutManager(new LinearLayoutManager(WorkerMainActivity.this));
                recyclerView.setAdapter(new WorkerAdapter(workList));
            }
        }
    }
}