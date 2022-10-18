package com.example.part2.managementwithsmartwearables.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.data.model.Worker;
import com.example.part2.managementwithsmartwearables.databinding.ActivityWorkerMainBinding;

import java.util.ArrayList;

public class WorkerMainActivity extends AppCompatActivity {

    private ActivityWorkerMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWorkerMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button qrcodeButton = binding.qrcode;
        final RecyclerView recyclerView = binding.workList;

        ArrayList<Work> workList = new ArrayList<>();
        workList.add(new Work(1, new User(1, "user1", "양선아", "profile.jpg"), "질소 주입", 1, 3));
        recyclerView.setLayoutManager(new LinearLayoutManager(WorkerMainActivity.this));
        recyclerView.setAdapter(new WorkerAdapter(workList));

        qrcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : QR코드 액티비티와 연결
            }
        });

    }
}