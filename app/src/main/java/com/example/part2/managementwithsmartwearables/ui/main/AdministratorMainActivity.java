package com.example.part2.managementwithsmartwearables.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.databinding.ActivityAdministratorMainBinding;
import com.example.part2.managementwithsmartwearables.ui.workdetail.WorkDetailActivity;

import java.util.ArrayList;

public class AdministratorMainActivity extends AppCompatActivity {

    private ActivityAdministratorMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAdministratorMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button qrcodeButton = binding.qrcode;
        final Button workListButton = binding.workList;
        final Button monitoring = binding.monitoring;
        final RecyclerView recyclerView = binding.administrationList;

        ArrayList<Work> administrations = new ArrayList<>();
        administrations.add(new Work(1, new User(1, "user1", "작업자1", "profile.jpg"), "작업내용1", 1, 1));
        administrations.add(new Work(2, new User(1, "user2", "작업자2", "profile.jpg"), "작업내용2", 1, 2));
        administrations.add(new Work(3, new User(1, "user3", "작업자2", "profile.jpg"), "작업내용3", 1, 3));
        administrations.add(new Work(4, new User(1, "user4", "작업자3", "profile.jpg"), "작업내용4", 1, 4));
        administrations.add(new Work(5, new User(1, "user5", "작업자4", "profile.jpg"), "작업내용5", 1, 2));
        administrations.add(new Work(6, new User(1, "user6", "테스트", "profile.jpg"), "작업내용6", 1, 1));
        administrations.add(new Work(7, new User(1, "user7", "테스트", "profile.jpg"), "작업내용7", 1, 2));
        administrations.add(new Work(8, new User(1, "user1", "테스트", "profile.jpg"), "작업내용8", 1, 3));
        administrations.add(new Work(9, new User(1, "user1", "테스트", "profile.jpg"), "작업내용9", 1, 2));
        administrations.add(new Work(10, new User(1, "user1", "테스트", "profile.jpg"), "작업내용10", 1, 4));
        administrations.add(new Work(11, new User(1, "user1", "테스트", "profile.jpg"), "작업내용11", 1, 2));
        administrations.add(new Work(12, new User(1, "user1", "테스트", "profile.jpg"), "작업내용12", 1, 2));
        administrations.add(new Work(13, new User(1, "user1", "테스트", "profile.jpg"), "작업내용13", 1, 1));
        administrations.add(new Work(14, new User(1, "user1", "테스트", "profile.jpg"), "작업내용14", 1, 2));
        administrations.add(new Work(15, new User(1, "user1", "테스트", "profile.jpg"), "작업내용15", 1, 3));
        recyclerView.setLayoutManager(new LinearLayoutManager(AdministratorMainActivity.this));
        recyclerView.setAdapter(new AdministratorAdapter(administrations));

        qrcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : QR코드 액티비티와 연결
            }
        });

        workListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WorkDetailActivity.class);
                startActivity(intent);
            }
        });

        monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : 실기간 모니터링 액티비티와 연결
            }
        });


    }
}