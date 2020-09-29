package com.example.final_3;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class AlbumActivity extends AppCompatActivity {
    VideoView videoView;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView.isPlaying()) {
            videoView.stopPlayback();  //실행되고 있다면 멈추기
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.vturn));  //비디오 자원이 어디에 있는지
        MediaController mc = new MediaController(this); // this 는 현재 activity 의미
        videoView.setMediaController(mc);
        videoView.start();

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
