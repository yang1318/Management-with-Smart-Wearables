package com.example.part2.managementwithsmartwearables.ui.main;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.R;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.databinding.ItemWorkerMainBinding;

import java.util.ArrayList;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder> {

    private ArrayList<Work> workList;

    public WorkerAdapter(ArrayList<Work> workList) {
        this.workList = workList;
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_worker_main, parent, false) ;
        WorkerViewHolder viewHolder = new WorkerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        holder.bind(workList.get(position));
    }

    @Override
    public int getItemCount() {
        return workList.size();
    }

    public class WorkerViewHolder extends RecyclerView.ViewHolder {

        private ItemWorkerMainBinding itemBinding;

        private TextView workerIndex;
        private TextView workName;
        private Button status;

        public WorkerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = ItemWorkerMainBinding.bind(itemView);
            workerIndex = itemBinding.workIndex;
            workName = itemBinding.workName;
            status = itemBinding.status;
        }
        void bind(Work work) {
            workerIndex.setText(String.valueOf(work.getIdx()));
            workName.setText(work.getWorkDetail());
            switch (work.getWorkStatus()) { // TODO API
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
        }
    }

}
