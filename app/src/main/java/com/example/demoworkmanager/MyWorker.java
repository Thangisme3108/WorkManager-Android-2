package com.example.demoworkmanager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {
    String TAG = "zzzzzzzzzzzzzz";
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        Log.d(TAG, "MyWorker: Bắt đầu tạo worker...");
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: Bắt đầu làm việc ...");
        // Các công việc cần thực hiện ngầm thì viết ở đây: download, update, ...

        // Tương tác giao diện: Giống service vì là background
        // Cần có handler
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Worker đang chạy", Toast.LENGTH_SHORT).show();
            }
        });

        return Result.success();
    }
}
