package com.example.neeraj.videostreaminganddownload;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by neeraj on 2/6/2017.
 */

public class VideoDownloader extends AsyncTask {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        int count;
        try{

        URL url = new URL(params[0].toString());
        URLConnection conection = url.openConnection();
        conection.connect();

        // this will be useful so that you can show a tipical 0-100%
        // progress bar
        int lenghtOfFile = conection.getContentLength();

        // download the file
        InputStream input = new BufferedInputStream(url.openStream(),
                8192);

        // Output stream
            String dir = Environment.getExternalStorageDirectory().toString();
        OutputStream output = new FileOutputStream(dir
                + "/video.mp4");
            Log.d("directory******** ",dir);

        byte data[] = new byte[1024];

        long total = 0;

        while ((count = input.read(data)) != -1) {
            total += count;
            // publishing the progress....
            // After this onProgressUpdate will be called
            publishProgress("" + (int) ((total * 100) / lenghtOfFile));

            // writing data to file
            output.write(data, 0, count);
        }

        // flushing output
        output.flush();

        // closing streams
        output.close();
        input.close();

    } catch (Exception e) {
        Log.e("Error: ", e.getMessage());
    }

    return null;
    }
}
