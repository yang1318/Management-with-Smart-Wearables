package com.example.part2.managementwithsmartwearables.ui.workdetail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.R;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.databinding.ItemWorkDetailBinding;
import com.example.part2.managementwithsmartwearables.ui.workerdetail.WorkerDetailActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class WorkDetailAdapter extends RecyclerView.Adapter<WorkDetailAdapter.WorkDetailViewHolder> {

    private ArrayList<Work> workList;

    public WorkDetailAdapter(ArrayList<Work> workList) {
        this.workList = workList;
    }

    @NonNull
    @Override
    public WorkDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_work_detail, parent, false) ;
        WorkDetailViewHolder viewHolder = new WorkDetailViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkDetailViewHolder holder, int position) {
        holder.bind(workList.get(position));
    }

    @Override
    public int getItemCount() {
        return workList.size();
    }

    public class WorkDetailViewHolder extends RecyclerView.ViewHolder {

        private ItemWorkDetailBinding itemBinding;

        private TextView workerName;
        private TextView workName;
        private CircleImageView profileImage;
        private ConstraintLayout layout;

        public WorkDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = ItemWorkDetailBinding.bind(itemView);
            workerName = itemBinding.workerName;
            workName = itemBinding.workName;
            profileImage = itemBinding.profileImage;
            layout = itemBinding.entireLayout;
        }

        void bind(Work work) {
            workerName.setText(work.getWorker().getName());
            workName.setText(work.getWorkDetail());
            profileImage.setImageResource(R.drawable.character_man); // TODO API : ????????? ???????????????
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), WorkerDetailActivity.class);
                    intent.putExtra("userIndex", String.valueOf(work.getWorker().getUserIdx()));
                    intent.putExtra("userName", work.getWorker().getName());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}
