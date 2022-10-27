package com.example.part2.managementwithsmartwearables.data;

import android.util.Log;

import com.example.part2.managementwithsmartwearables.data.model.User;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpConnection {
    private OkHttpClient okClient;
    private static HttpConnection instance = new HttpConnection();
    public static HttpConnection getInstance() {
        return instance;
    }

    private HttpConnection(){
        this.okClient = new OkHttpClient();
    }

    public User loginRequest(String id, String password) {
        // 로그인
        RequestBody body = new FormBody.Builder()
                .add("id", id)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_Login")
                .post(body)
                .build();
        try (Response response = okClient.newCall(request).execute()) {
            Log.d("TAG", "loginRequest: " + response.body().string());
            User user = new User();
            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User mainListRequest(String id, String password) {
        // 승인 대기 작업자 리스트
        RequestBody body = new FormBody.Builder()
                .add("author", id)
                .add("member_id", password)
                .build();
        Request request = new Request.Builder()
                .url("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_a_MainList")
                .post(body)
                .build();
        try (Response response = okClient.newCall(request).execute()) {
            response.body();
            User user = new User();
            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User workerListRequest(String id, String password) {
        // 작업자 리스트 및 현재 작업 중 작업전달
        RequestBody body = new FormBody.Builder()
                .add("id", id)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_Login")
                .post(body)
                .build();
        try (Response response = okClient.newCall(request).execute()) {
            response.body();
            User user = new User();
            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User workerDetailList(String id, String password) {
        // 해당 작업자 상세리스트(관리자)
        RequestBody body = new FormBody.Builder()
                .add("id", id)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_Login")
                .post(body)
                .build();
        try (Response response = okClient.newCall(request).execute()) {
            response.body();
            User user = new User();
            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User workDetailList(String id, String password) {
        // 작업자 상세 리스트(작업자)
        RequestBody body = new FormBody.Builder()
                .add("id", id)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_Login")
                .post(body)
                .build();
        try (Response response = okClient.newCall(request).execute()) {
            response.body();
            User user = new User();
            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User detailWorkerList(String id, String password) {
        // 작업 승인 처리
        RequestBody body = new FormBody.Builder()
                .add("id", id)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_Login")
                .post(body)
                .build();
        try (Response response = okClient.newCall(request).execute()) {
            response.body();
            User user = new User();
            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
