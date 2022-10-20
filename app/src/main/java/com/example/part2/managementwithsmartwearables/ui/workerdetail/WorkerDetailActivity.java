package com.example.part2.managementwithsmartwearables.ui.workerdetail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.data.model.WorkDetailItem;
import com.example.part2.managementwithsmartwearables.databinding.ActivityWorkerDetailBinding;

import java.util.ArrayList;

public class WorkerDetailActivity extends AppCompatActivity {

    private ActivityWorkerDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkerDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final ImageButton backButton = binding.backButton;
        final RecyclerView recyclerView = binding.workerDetailList;
        ArrayList<WorkDetailItem> workList = new ArrayList<>(); // TODO API
        workList.add(new WorkDetailItem(1, new User(1, "user1", "테스트", "profile.jpg"), "작업내용1", 1, 1));
        workList.add(new WorkDetailItem(2, new User(1, "user1", "테스트", "profile.jpg"), "작업내용2", 1, 2));
        workList.add(new WorkDetailItem(3, new User(1, "user1", "테스트", "profile.jpg"), "작업내용3", 1, 3));
        workList.add(new WorkDetailItem(4, new User(1, "user1", "테스트", "profile.jpg"), "작업내용4", 1, 4));
        workList.add(new WorkDetailItem(5, new User(1, "user1", "테스트", "profile.jpg"), "작업내용5", 1, 2));
        workList.add(new WorkDetailItem(6, new User(1, "user1", "테스트", "profile.jpg"), "작업내용6", 1, 1));
        workList.add(new WorkDetailItem(7, new User(1, "user1", "테스트", "profile.jpg"), "작업내용7", 1, 2));
        workList.add(new WorkDetailItem(8, new User(1, "user1", "테스트", "profile.jpg"), "작업내용8", 1, 3));
        workList.add(new WorkDetailItem(9, new User(1, "user1", "테스트", "profile.jpg"), "작업내용9", 1, 2));
        workList.add(new WorkDetailItem(10, new User(1, "user1", "테스트", "profile.jpg"), "작업내용10", 1, 4));
        workList.add(new WorkDetailItem(11, new User(1, "user1", "테스트", "profile.jpg"), "작업내용11", 1, 2));
        workList.add(new WorkDetailItem(12, new User(1, "user1", "테스트", "profile.jpg"), "작업내용12", 1, 2));
        workList.add(new WorkDetailItem(13, new User(1, "user1", "테스트", "profile.jpg"), "작업내용13", 1, 1));
        workList.add(new WorkDetailItem(14, new User(1, "user1", "테스트", "profile.jpg"), "작업내용14", 1, 2));
        workList.add(new WorkDetailItem(15, new User(1, "user1", "테스트", "profile.jpg"), "작업내용15", 1, 3));
        recyclerView.setLayoutManager(new LinearLayoutManager(WorkerDetailActivity.this));
        recyclerView.setAdapter(new WorkerDetailAdapter(workList));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}