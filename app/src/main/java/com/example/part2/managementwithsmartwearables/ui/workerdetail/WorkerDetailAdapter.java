package com.example.part2.managementwithsmartwearables.ui.workerdetail;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.R;
import com.example.part2.managementwithsmartwearables.data.model.WorkDetailItem;
import com.example.part2.managementwithsmartwearables.databinding.ItemWorkerDetailBinding;

import java.util.ArrayList;

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

            if (workDetailItem.getExpended())
                buttonLayout.setVisibility(View.VISIBLE);
            else
                buttonLayout.setVisibility(View.GONE);

            switch (workDetailItem.getWorkStatus()) {
                case 1:
                    status.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#f4554b"))); // red
                    status.setText("작업중");
                    break;
                case 2:
                    status.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2fcb80"))); // green
                    status.setText("작업시작");
                    break;
                case 3:
                    status.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#2fa5cb"))); // blue
                    status.setText("작업완료");
                    break;
                case 4:
                    status.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8d8d8d"))); // gray
                    status.setText("작업대기");
                    break;
            }

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (workDetailItem.getExpended()) {
                        workDetailItem.setExpended(false);
                    }
                    else {
                        workDetailItem.setExpended(true);
                    }
                    notifyDataSetChanged();
                }
            });

            camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO CAMERA : 실시간 모니터링 activity로 인텐트
                }
            });
            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO API : 승인 api 사용
                }
            });
        }
    }

}
