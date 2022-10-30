package com.example.part2.managementwithsmartwearables.ui.workerdetail;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.R;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.data.model.WorkDetailItem;
import com.example.part2.managementwithsmartwearables.databinding.ItemWorkerDetailBinding;

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

public class WorkerDetailAdapter extends RecyclerView.Adapter<WorkerDetailAdapter.WorkerDetailViewHolder> {

    private ArrayList<WorkDetailItem> workDetailList;

    public WorkerDetailAdapter(ArrayList<WorkDetailItem> workDetailList) {
        this.workDetailList = workDetailList;
    }

    @NonNull
    @Override
    public WorkerDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_worker_detail, parent, false) ;
        WorkerDetailViewHolder viewHolder = new WorkerDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerDetailViewHolder holder, int position) {
        holder.bind(workDetailList.get(position));
    }

    @Override
    public int getItemCount() {
        return workDetailList.size();
    }

    public class WorkerDetailViewHolder extends RecyclerView.ViewHolder {

        private ItemWorkerDetailBinding itemBinding;

        private TextView workerName;
        private TextView workName;
        private Button status;
        private Button camera;
        private Button approve;
        private ConstraintLayout layout;
        private ConstraintLayout buttonLayout;

        public WorkerDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = ItemWorkerDetailBinding.bind(itemView);
            workerName = itemBinding.workIndex;
            workName = itemBinding.workName;
            status = itemBinding.status;
            camera = itemBinding.camera;
            approve = itemBinding.approve;
            layout = itemBinding.entireLayout;
            buttonLayout = itemBinding.buttonLayout;
        }

        void bind(WorkDetailItem workDetailItem) {
            workerName.setText(String.valueOf(workDetailItem.getIdx()));
            workName.setText(workDetailItem.getWorkDetail());

            switch (workDetailItem.getWorkStatus()) {
                case 1:
                    status.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2fa5cb"))); // blue
                    status.setText("작업완료");
                    buttonLayout.setVisibility(View.GONE);
                    break;
                case 2:
                    status.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f4554b"))); // red
                    status.setText("작업중");
                    buttonLayout.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    status.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8d8d8d"))); // gray
                    status.setText("작업대기");
                    buttonLayout.setVisibility(View.GONE);
                    break;
            }

//            layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (workDetailItem.getExpended()) {
//                        workDetailItem.setExpended(false);
//                    }
//                    else {
//                        workDetailItem.setExpended(true);
//                    }
//                    notifyDataSetChanged();
//                }
//            });

            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO CAMERA : 실시간 모니터링 activity로 인텐트
                }
            });
            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new HttpAsyncTask().execute("http://renewal.kiotcom.co.kr/index.php/input/Gdstar_process_c/w_a_reject", String.valueOf(workDetailItem.getIdx()), "1");

                    Handler handler = new Handler();
                    ProgressDialog progressDialog = new ProgressDialog(itemView.getContext());
                    progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    progressDialog.getWindow().setGravity(Gravity.CENTER);
                    progressDialog.show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            workDetailItem.setWorkStatus(1);
                            notifyDataSetChanged();
                        }
                    }, 300);
                }
            });
        }
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, Boolean> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Boolean doInBackground(String... params) {
            ArrayList<Work> workList = new ArrayList<>();
            String strUrl = params[0];
            try {
                JSONObject input = new JSONObject();
                input.put("work_idx", params[1]);
                input.put("approve", params[2]);
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(input.toString(), JSON);
                Request request = new Request.Builder()
                        .url(strUrl)
                        .post(body)
                        .build();

                Response response = client.newCall(request).execute();

                JSONObject jsonObject = new JSONObject(response.body().string());
                if (jsonObject.getString("result").equals("false")) {
                    //Toast.makeText(getApplicationContext(), jsonObject.getString("content"), Toast.LENGTH_LONG).show();
                    return false;
                } else {
                    JSONArray jsonArray = new JSONArray(jsonObject.getString("content"));
                    JSONObject workObject = jsonArray.getJSONObject(0);
                    int workStatus = workObject.getInt("work_status");
                    if (workStatus == 1)
                        return true;
                    else return false;
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean r) {
            super.onPostExecute(r);
            if (r != null) {
                // TODO : 실제로 동작하게 해야함..
            }
        }
    }

}
