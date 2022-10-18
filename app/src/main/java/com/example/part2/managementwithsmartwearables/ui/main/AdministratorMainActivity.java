package com.example.part2.managementwithsmartwearables.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.part2.managementwithsmartwearables.data.model.User;
import com.example.part2.managementwithsmartwearables.data.model.Work;
import com.example.part2.managementwithsmartwearables.data.model.Worker;
import com.example.part2.managementwithsmartwearables.databinding.ActivityAdministratorMainBinding;

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
        administrations.add(new Work(1, new User(1, "user1", "양선아", "profile.jpg"), "질소 주입", 1, 2));
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