package com.example.part2.managementwithsmartwearables.ui.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.databinding.ActivityAdministratorMainBinding;
import com.example.part2.managementwithsmartwearables.ui.workdetail.WorkDetailActivity;

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

public class AdministratorMainActivity extends AppCompatActivity {

    private ActivityAdministratorMainBinding binding;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdministratorMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button qrcodeButton = binding.qrcode;
        final Button workListButton = binding.workList;
        final Button monitoring = binding.monitoring;
        recyclerView = binding.administrationList;


        new HttpAsyncTask().execute("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_a_MainList", "1","1");

        qrcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO QR : QR코드 액티비티와 연결
            }
        });

        workListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkDetailActivity.class);
                // TODO API
                startActivity(intent);
            }
        });

        monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO CAMERA : 실기간 모니터링 액티비티와 연결
            }
        });
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, ArrayList<Work>> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected ArrayList<Work> doInBackground(String... params) {
            ArrayList<Work> administrations = new ArrayList<>();
            String strUrl = params[0];
            try {
                JSONObject input = new JSONObject();
                input.put("author", params[1]);
                input.put("member_idx", params[2]);
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
                        int approve = workObject.getInt("approve");
                        administrations.add(new Work(idx, new User(1, name, name, profile), workDetail, approve, 0));
                    }
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return administrations;
        }

        @Override
        protected void onPostExecute(ArrayList<Work> administrations) {
            super.onPostExecute(administrations);
            if (administrations != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(AdministratorMainActivity.this));
                recyclerView.setAdapter(new AdministratorAdapter(new ArrayList<Work>(administrations)));
            }
        }
    }
}