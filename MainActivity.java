package com.example.final_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp1, mp2;
    Button btnPlayer1, btnPlayer2;

    public void onClickAlbum(View v) {
        if(mp1 != null && mp1.isPlaying()) {
            mp1.stop();
            btnPlayer1.setText("듣기");
        }
        if(mp2 != null && mp2.isPlaying()) {
            mp2.stop();
            btnPlayer2.setText("듣기");
        }
        Intent it = new Intent(getApplicationContext(), AlbumActivity.class);  //전달할 데이터가 없기 때문에 putextra는 사용하지 않음
        startActivity(it);
    }

    public void onClickSong(View v) {
        String str = "";

        if(v.getId() == R.id.buttonSong1) {
            str = getString(R.string.song_turn);
        }
        else {
            str = getString(R.string.song_liquor);
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {  // 실행 도중에 중간 시키기 위해
        super.onDestroy();
        if(mp1 != null) {
            if(mp1.isPlaying()) {
                mp1.stop();  // 노래 중지시켜서 다시 사용가능
            }
            mp1.release();  // 노래를 아예 끝내서 다시 들을려면 create를 다시 해주어야함
        }
        if(mp2 != null) {
            if(mp2.isPlaying()) {
                mp2.stop();
            }
            mp2.release();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlayer1 = findViewById(R.id.buttonPlay1);
        btnPlayer2 = findViewById(R.id.buttonPlay2);

        btnPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp2 != null && mp2.isPlaying()) {   //첫번째 노래를 듣다가 다음 곡으로 넘겼을 때
                    mp2.stop();
                    btnPlayer2.setText("듣기");
                }
                if(mp1 != null && mp1.isPlaying()) {   //첫번째 노래를 듣다가 스탑했을 때
                    mp1.stop();
                    btnPlayer1.setText("듣기");
                    return;  //곡이 다음곡으로 넘어가지 않고 그냥 스톱
                }
                mp1 = MediaPlayer.create(getApplicationContext(), R.raw.turn);
                mp1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {  //컴플리션은 start 전에 사용해주어야 함
                    @Override
                    public void onCompletion(MediaPlayer mp) {  // 노래가 다 끝나고 Toast 창 뜸
                        btnPlayer1.setText("듣기");
                        Toast.makeText(getApplicationContext(), "재생 종료", Toast.LENGTH_SHORT).show();
                    }
                });
                btnPlayer1.setText("Stop");
                mp1.start();
            }
        });

        btnPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp1 != null && mp1.isPlaying()) {
                    mp1.stop();
                    btnPlayer1.setText("듣기");
                }
                if(mp2 != null && mp2.isPlaying()) {
                    mp2.stop();
                    btnPlayer2.setText("듣기");
                    return;
                }
                mp2 = MediaPlayer.create(getApplicationContext(), R.raw.liquor);
                mp2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btnPlayer2.setText("듣기");
                        Toast.makeText(getApplicationContext(), "재생 종료", Toast.LENGTH_SHORT).show();
                    }
                });
                btnPlayer2.setText("Stop");
                mp2.start();
            }
        });
    }
}
