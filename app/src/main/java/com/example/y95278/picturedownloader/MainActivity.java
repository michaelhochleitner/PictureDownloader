package com.example.y95278.picturedownloader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String UIID = "com.example.picturedownloader.UIID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter progressIntentFilter = new IntentFilter(Constants.BROADCAST_PROGRESS);
        DownloadStatusReceiver downloadStatusReceiver = new DownloadStatusReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(downloadStatusReceiver,
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

    public void startDownload(View view){
        // A uiID is an id for the combination of a progressBar and a ImageView in the UI.
        // The urls in pictureUrls can also be identified with an uiID because the picture on the
        // website with the first url fills the first imageView, the second url fills the second
        // imageView etc.
        String[] pictureUrls = {
        "https://image.ibb.co/cskzO6/ic_launcher_black.png",
        "https://image.ibb.co/jqGZqm/ic_launcher_blue.png",
        "https://image.ibb.co/hNYfVm/ic_launcher_red.png",
        "https://image.ibb.co/g8mZqm/ic_launcher_turquoise.png",
        "https://image.ibb.co/jKqzO6/ic_launcher_blue.png",
        };
        for (int uiId = 0; uiId < 5; uiId++){
            Intent downloadIntent = new Intent(this, DownloadService.class);
            downloadIntent.putExtra(UIID,uiId);
            downloadIntent.setData(Uri.parse(pictureUrls[uiId]));
            Log.i(TAG,"Start download intent sent.");
            startService(downloadIntent);
        }
    }

    private class DownloadStatusReceiver extends BroadcastReceiver{

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
                    byte pictureData0[] = intent.getByteArrayExtra(Constants.PICTURE_DATA);
                    Bitmap bMap0 = BitmapFactory.decodeByteArray(pictureData0, 0, pictureData0.length);
                    imageView0.setImageBitmap(bMap0);
                    imageView0.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
                    progressBar1.setVisibility(View.INVISIBLE);
                    ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
                    byte pictureData1[] = intent.getByteArrayExtra(Constants.PICTURE_DATA);
                    Bitmap bMap1 = BitmapFactory.decodeByteArray(pictureData1, 0, pictureData1.length);
                    imageView1.setImageBitmap(bMap1);
                    imageView1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
                    progressBar2.setVisibility(View.INVISIBLE);
                    ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
                    byte pictureData2[] = intent.getByteArrayExtra(Constants.PICTURE_DATA);
                    Bitmap bMap2 = BitmapFactory.decodeByteArray(pictureData2, 0, pictureData2.length);
                    imageView2.setImageBitmap(bMap2);
                    imageView2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    ProgressBar progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
                    progressBar3.setVisibility(View.INVISIBLE);
                    ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
                    byte pictureData3[] = intent.getByteArrayExtra(Constants.PICTURE_DATA);
                    Bitmap bMap3 = BitmapFactory.decodeByteArray(pictureData3, 0, pictureData3.length);
                    imageView3.setImageBitmap(bMap3);
                    imageView3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    ProgressBar progressBar4 = (ProgressBar) findViewById(R.id.progressBar4);
                    progressBar4.setVisibility(View.INVISIBLE);
                    ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
                    byte pictureData4[] = intent.getByteArrayExtra(Constants.PICTURE_DATA);
                    Bitmap bMap4 = BitmapFactory.decodeByteArray(pictureData4, 0, pictureData4.length);
                    imageView4.setImageBitmap(bMap4);
                    imageView4.setVisibility(View.VISIBLE);
                    break;
            }
        }



    }
}
