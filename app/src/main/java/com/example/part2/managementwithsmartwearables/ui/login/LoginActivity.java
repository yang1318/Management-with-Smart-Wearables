package com.example.part2.managementwithsmartwearables.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.example.part2.managementwithsmartwearables.R;
import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.databinding.ActivityLoginBinding;
import com.example.part2.managementwithsmartwearables.ui.main.AdministratorMainActivity;
import com.example.part2.managementwithsmartwearables.ui.main.WorkerMainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final AppCompatButton loginButton = (AppCompatButton) binding.login;
        final ProgressBar loadingProgressBar = binding.loading;


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (passwordEditText.getText().toString().trim().length() < 2 && usernameEditText.getText().toString() != null) {
                    loginButton.setEnabled(false);
                    loginButton.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.light_gray1));
                    loginButton.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.light_gray2));
                    passwordEditText.setError("비밀번호는 세자리 이상이어야 합니다.");
                }
                else {
                    loginButton.setBackgroundColor(ContextCompat.getColor(LoginActivity.this, R.color.skyblue2));
                    loginButton.setTextColor(Color.WHITE);
                    loginButton.setEnabled(true);
                }
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                new HttpAsyncTask().execute("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_Login", usernameEditText.getText().toString(),passwordEditText.getText().toString());
            }
        });
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, User> {

        OkHttpClient client = new OkHttpClient();
        int admin = 2;

        @Override
        protected User doInBackground(String... params) {
            User falseUser = new User();
            falseUser.setUserIdx(-1);
            String strUrl = params[0];
            try {
                JSONObject input = new JSONObject();
                input.put("id", params[1]);
                input.put("password", params[2]);
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
                    return falseUser;
                } else {
                    JSONObject workObject = new JSONObject(jsonObject.getString("content"));

                    int idx = workObject.getInt("user_idx");
                    String name = workObject.getString("name");
                    admin = workObject.getInt("author");
                    User user = new User(idx, name, name, "profile.jpg");
                    return user;

                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return falseUser;
        }

        @Override
        protected void onPostExecute(User result) {
            super.onPostExecute(result);
            if (result.getUserIdx() >= 0) {
                if (admin == 1) {
                    Intent intent = new Intent(getApplicationContext(), AdministratorMainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), WorkerMainActivity.class);
                    startActivity(intent);
                    finish();
                }
                String welcome = "환영합니다, " + result.getName() + "님!";
                Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
            }
        }
    }
}