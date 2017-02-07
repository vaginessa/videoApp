package com.example.neeraj.videostreaminganddownload;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.lang.reflect.Array;

/**
 * Created by neeraj on 2/3/2017.
 */

public class VideoPlayer extends Activity {

    VideoView view;
    Uri uri;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);
        url = "https://socialcops.com/images/spec/home/header-img-background_video-1920-480.mp4";
        uri= Uri.parse(url);
        VideoDownloader downloader = new VideoDownloader();
        String[] params= new String[1];
        params[0]=url;
        downloader.execute(params);
        view= (VideoView)findViewById(R.id.videoView);
        try{
            MediaController controller = new MediaController(VideoPlayer.this);
            controller.setAnchorView(view);

            view.setMediaController(controller);
            view.setVideoURI(uri);

        }catch (Exception e){

        }

        view.requestFocus();
        view.start();
        view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
//                view.start();
            }
        });

//        view.setBackgroundColor(Color.BLUE);
    }
}
