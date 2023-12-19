package com.example.demoworkmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // làm việc với worker

        // Tạo ràng buộc
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        // 1.
//        WorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class)
//                .setConstraints(constraints)
//                .build();

        // 2.
        // Tạo request để thực hiện công việc lặp lại nhiều lân
        // Ít nhất khoảng cách giữa 2 lần thực hiện là 15 phút, để nhỏ hơn sẽ khó thực hiện
        // Khoảng cách thời gian giữa 2 lần workermanager sẽ tự thay đổi cho phù hợp với hiệu suất của điện thoại
        WorkRequest request = new PeriodicWorkRequest
                .Builder(MyWorker.class, 16, TimeUnit.MINUTES)
                .setConstraints(constraints)
                        .build();

        // Gửi lên hệ thống
        WorkManager.getInstance(getApplicationContext()).enqueue(request);
    }
}