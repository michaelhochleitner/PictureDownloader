package com.example.y95278.picturedownloader;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;


public class SleepingService extends IntentService{

    public SleepingService() {
        super("SleepingService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int uiId = intent.getIntExtra(Constants.UUID, -1);
        for (int seconds = 0; seconds<100; seconds++){
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent progressIntent = new Intent(Constants.BROADCAST_PROGRESS);
            progressIntent.putExtra(Constants.PROGRESS, seconds);
            progressIntent.putExtra(Constants.UUID, uiId);
            LocalBroadcastManager.getInstance(this).sendBroadcast(progressIntent);
        }

        Intent downloadFinishedIntent = new Intent(Constants.BROADCAST_DOWNLOAD_FINISHED)
                .putExtra(Constants.UUID, uiId);
        LocalBroadcastManager.getInstance(this).sendBroadcast(downloadFinishedIntent);

    }
}
