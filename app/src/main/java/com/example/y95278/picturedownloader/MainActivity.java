package com.example.y95278.picturedownloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    // Bahnbrechende Änderung.
    private static final String TAG = "MainActivity";
    private static final String UIID = "com.example.picturedownloader.UIID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter progressIntentFilter = new IntentFilter(Constants.BROADCAST_PROGRESS);
        SleepingStatusReceiver sleepingStatusReceiver = new SleepingStatusReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(sleepingStatusReceiver,
                progressIntentFilter);

        IntentFilter downloadFinishedIntentFilter = new IntentFilter(
                Constants.BROADCAST_DOWNLOAD_FINISHED);
        DownloadFinishReceiver downloadFinishReceiver = new DownloadFinishReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(downloadFinishReceiver,
                downloadFinishedIntentFilter);
    }

    public void shutdownApp(View view){
        System.exit(9);
    }

    public void startSleeping(View view){
        // A uiID is an id for the combination of a progressBar and a ImageView in the UI.
        for (int uiId = 0; uiId < 5; uiId++){
            Intent intent = new Intent(this, SleepingService.class);
            intent.putExtra(UIID,uiId);
            Log.i(TAG,"start sleeping intent sent");
            startService(intent);
        }
    }

    private class SleepingStatusReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG,"Sleeping Status Receiver onReceive called");
            int uiId = intent.getIntExtra(Constants.UUID,-1);
            int progress = intent.getIntExtra(Constants.PROGRESS,-1);
            switch (uiId) {
                case 0: ProgressBar progressBar0 = (ProgressBar) findViewById(R.id.progressBar0);
                    progressBar0.setProgress(progress);
                    break;
                case 1: ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
                    progressBar1.setProgress(progress);
                    break;
                case 2: ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
                    progressBar2.setProgress(progress);
                    break;
                case 3: ProgressBar progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
                    progressBar3.setProgress(progress);
                    break;
                case 4: ProgressBar progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);
                    progressBar4.setProgress(progress);
                    break;
            }

        }
    }

    private class DownloadFinishReceiver extends BroadcastReceiver{
        private final String TAG = "DownloadFinishReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive called");
            int uiId = intent.getIntExtra(Constants.UUID,-1);
            switch (uiId) {
                case 0:
                    ProgressBar progressBar0 = (ProgressBar) findViewById(R.id.progressBar0);
                    progressBar0.setVisibility(View.INVISIBLE);
                    ImageView imageView0 = (ImageView) findViewById(R.id.imageView0);
                    Picasso.with(context)
                            .load("https://image.ibb.co/cskzO6/ic_launcher_black.png")
                            .into(imageView0);
                    imageView0.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
                    progressBar1.setVisibility(View.INVISIBLE);
                    ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
                    Picasso.with(context)
                            .load("https://image.ibb.co/jqGZqm/ic_launcher_blue.png")
                            .into(imageView1);
                    imageView1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
                    progressBar2.setVisibility(View.INVISIBLE);
                    ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
                    Picasso.with(context)
                            .load("https://image.ibb.co/hNYfVm/ic_launcher_red.png")
                            .into(imageView2);
                    imageView2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    ProgressBar progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
                    progressBar3.setVisibility(View.INVISIBLE);
                    ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
                    Picasso.with(context)
                            .load("https://image.ibb.co/g8mZqm/ic_launcher_turquoise.png")
                            .into(imageView3);
                    imageView3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    ProgressBar progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);
                    progressBar4.setVisibility(View.INVISIBLE);
                    ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
                    Picasso.with(context)
                            .load("https://image.ibb.co/jKqzO6/ic_launcher_blue.png")
                            .into(imageView4);
                    imageView4.setVisibility(View.VISIBLE);
                    break;
            }
        }



    }
}
