package com.example.y95278.picturedownloader;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class DownloadService extends IntentService{

    private static final String TAG = "DownloadService";

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int uiId = intent.getIntExtra(Constants.UUID, -1);
        Log.i(TAG,"onHandleIntent called");
        String localUrlString = intent.getDataString();
        Log.i("URL",localUrlString.toString());
        byte[] pictureData = download(localUrlString, uiId);
        Intent downloadFinishedIntent = new Intent (Constants.BROADCAST_DOWNLOAD_FINISHED)
                .putExtra(Constants.PICTURE_DATA,pictureData);
        downloadFinishedIntent.putExtra(Constants.UUID, uiId);
        LocalBroadcastManager.getInstance(this).sendBroadcast(downloadFinishedIntent);
        Log.i(TAG,"downloadFinishedIntent sent");
    }
    private byte[] download(String urlString, int uiId){
        URL url = null;

        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            is = url.openStream ();
            URL sizeUrl = new URL(urlString);
            URLConnection conn = url.openConnection();
            int size = conn.getContentLength();
            Log.i(TAG,"Size "+Integer.toString(size));

            byte[] byteChunk = new byte[1024]; // Or whatever size you want to read in at a time.
            int n;

            while ( (n = is.read(byteChunk)) > 0 ) {
                baos.write(byteChunk, 0, n);
                float sizeFloat = (float) size;
                float percentage = (baos.size()/sizeFloat) * 100;
                int intPercentage = (int) percentage;
                Log.i(TAG,"Percentage"+Float.toString(percentage));
                Log.i(TAG,"IntPercentage"+Integer.toString(intPercentage));
                Intent progressIntent = new Intent(Constants.BROADCAST_PROGRESS);
                progressIntent.putExtra(Constants.PROGRESS, intPercentage);
                progressIntent.putExtra(Constants.UUID, uiId);
                LocalBroadcastManager.getInstance(this).sendBroadcast(progressIntent);
            }
        }
        catch (IOException e) {
            System.err.printf ("Failed while reading bytes from %s: %s", url.toExternalForm(), e.getMessage());
            e.printStackTrace ();
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return baos.toByteArray();
    }
}
