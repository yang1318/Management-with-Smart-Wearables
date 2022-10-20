package com.example.part2.managementwithsmartwearables.ui.workdetail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.databinding.ActivityWorkDetailBinding;

import java.util.ArrayList;

public class WorkDetailActivity extends AppCompatActivity {

    private ActivityWorkDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final ImageButton backButton = binding.backButton;
        final RecyclerView recyclerView = binding.workDetailList;
        ArrayList<Work> workList = new ArrayList<>(); // TODO API
        workList.add(new Work(1, new User(1, "user1", "테스트", "profile.jpg"), "작업내용1", 1, 1));
        workList.add(new Work(2, new User(1, "user1", "테스트", "profile.jpg"), "작업내용2", 1, 2));
        workList.add(new Work(3, new User(1, "user1", "테스트", "profile.jpg"), "작업내용3", 1, 3));
        workList.add(new Work(4, new User(1, "user1", "테스트", "profile.jpg"), "작업내용4", 1, 4));
        workList.add(new Work(5, new User(1, "user1", "테스트", "profile.jpg"), "작업내용5", 1, 2));
        workList.add(new Work(6, new User(1, "user1", "테스트", "profile.jpg"), "작업내용6", 1, 1));
        workList.add(new Work(7, new User(1, "user1", "테스트", "profile.jpg"), "작업내용7", 1, 2));
        workList.add(new Work(8, new User(1, "user1", "테스트", "profile.jpg"), "작업내용8", 1, 3));
        workList.add(new Work(9, new User(1, "user3", "김가스", "profile.jpg"), "작업내용9", 1, 2));
        workList.add(new Work(10, new User(1, "user3", "김가스", "profile.jpg"), "작업내용10", 1, 4));
        workList.add(new Work(11, new User(1, "user5", "김철수", "profile.jpg"), "작업내용11", 1, 2));
        workList.add(new Work(12, new User(1, "user6", "양선아", "profile.jpg"), "작업내용12", 1, 2));
        workList.add(new Work(13, new User(1, "user1", "테스트", "profile.jpg"), "작업내용13", 1, 1));
        workList.add(new Work(14, new User(1, "user3", "김가스", "profile.jpg"), "작업내용14", 1, 2));
        workList.add(new Work(15, new User(1, "user5", "김철수", "profile.jpg"), "작업내용15", 1, 3));
        recyclerView.setLayoutManager(new LinearLayoutManager(WorkDetailActivity.this));
        recyclerView.setAdapter(new WorkDetailAdapter(workList));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}