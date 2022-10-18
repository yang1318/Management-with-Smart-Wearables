package com.example.part2.managementwithsmartwearables.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.R;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.databinding.ItemAdministratorMainBinding;

import java.util.ArrayList;

public class AdministratorAdapter extends RecyclerView.Adapter<AdministratorAdapter.AdministratorViewHolder> {

    private ArrayList<Work> administrationList;

    public AdministratorAdapter(ArrayList<Work> administrationList) {
        this.administrationList = administrationList;
    }

    @NonNull
    @Override
    public AdministratorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_administrator_main, parent, false) ;
        AdministratorViewHolder viewHolder = new AdministratorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdministratorViewHolder holder, int position) {
        holder.bind(administrationList.get(position));
    }

    @Override
    public int getItemCount() {
        return administrationList.size();
    }

    public class AdministratorViewHolder extends RecyclerView.ViewHolder {

        private ItemAdministratorMainBinding itemBinding;

        private TextView workerName;
        private TextView workName;
        private Button status;

        public AdministratorViewHolder(@NonNull View itemView) {
            super(itemView);
            itemBinding = ItemAdministratorMainBinding.bind(itemView);
            workerName = itemBinding.workerName;
            workName = itemBinding.workName;
            status = itemBinding.status;
        }

        void bind(Work administration) {
            workerName.setText(administration.getWorker().getName());
            workName.setText(administration.getWorkDetail());
            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO : 버튼 클릭시 동작, intent하기
                }
            });
        }
    }

}
