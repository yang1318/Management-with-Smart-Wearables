package com.example.part2.managementwithsmartwearables.ui.workdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.databinding.ActivitySplashBinding;
import com.example.part2.managementwithsmartwearables.databinding.ActivityWorkDetailBinding;
import com.example.part2.managementwithsmartwearables.ui.main.AdministratorAdapter;
import com.example.part2.managementwithsmartwearables.ui.main.AdministratorMainActivity;

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
        ArrayList<Work> workList = new ArrayList<>();
        workList.add(new Work(1, new User(1, "user1", "테스트", "profile.jpg"), "가스 주입 및 누설 확인~~~~~~~~~~~~~!!!~!~!~!`1`1`1", 1, 2));
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