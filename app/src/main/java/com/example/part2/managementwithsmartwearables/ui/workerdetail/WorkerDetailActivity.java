package com.example.part2.managementwithsmartwearables.ui.workerdetail;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.data.model.WorkDetailItem;
import com.example.part2.managementwithsmartwearables.databinding.ActivityWorkerDetailBinding;

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

public class WorkerDetailActivity extends AppCompatActivity {

    private ActivityWorkerDetailBinding binding;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkerDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final ImageButton backButton = binding.backButton;
        recyclerView = binding.workerDetailList;

        new HttpAsyncTask().execute("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_a_WorkList", "2");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, ArrayList<WorkDetailItem>> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected ArrayList<WorkDetailItem> doInBackground(String... params) {
            ArrayList<WorkDetailItem> workList = new ArrayList<>();
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
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject workObject = jsonArray.getJSONObject(i);
                        int idx = workObject.getInt("idx");
                        String name = workObject.getString("name");
                        String profile = workObject.getString("profile");
                        String workDetail = workObject.getString("work_detail");
                        int workStatus = workObject.getInt("work_status");
                        int approve = workObject.getInt("approve");
                        workList.add(new WorkDetailItem(idx, new User(1, name, name, profile), workDetail, approve, workStatus));
                    }
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return workList;
        }

        @Override
        protected void onPostExecute(ArrayList<WorkDetailItem> workList) {
            super.onPostExecute(workList);
            if (workList != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(WorkerDetailActivity.this));
                recyclerView.setAdapter(new WorkerDetailAdapter(workList));
            }
        }
    }
}