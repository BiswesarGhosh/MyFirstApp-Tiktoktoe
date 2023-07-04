package com.example.katakuti;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
//    0=X;    1=O;  2=null;

    MediaPlayer sound_win;
    MediaPlayer sound_tap1;
    MediaPlayer sound_tap2;
    MediaPlayer sound_wrongtap;
    MediaPlayer sound_draw;
    int turn;
    boolean isactiv;
    public void chooseone(View view){
        ImageView color = (ImageView) view;
        ImageView wintext1 = (ImageView) findViewById(R.id.imageView11);
        ImageView wintext2 = (ImageView) findViewById(R.id.imageView12);
        TextView wintext = (TextView) findViewById(R.id.wintext);
        wintext.setVisibility(View.INVISIBLE);
        wintext1.setVisibility(View.INVISIBLE);
        wintext2.setVisibility(View.INVISIBLE);
        sound_tap2.start();

        if(Integer.parseInt(color.getTag().toString())==0){

            turn=1;
            isactiv=true;
        }
        else if(Integer.parseInt(color.getTag().toString())==1){
            turn=0;
            isactiv=true;
        }
        else{
            turn=0;
            isactiv=false;
        }
    }
    int[] target={2,2,2,2,2,2,2,2,2};
    int[][] winpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void clickk(View view){
        if (isactiv) {
            ImageView tap = (ImageView) view;
            int tag = Integer.parseInt(tap.getTag().toString());
            String msg="";
            if (target[tag] == 2) {
                target[tag] = turn;
                if (turn == 0) {
                    sound_tap1.start();
                    tap.setImageResource(R.drawable.circle);
                    turn = 1;
                } else {
                    sound_tap1.start();
                    tap.setImageResource(R.drawable.cross);
                    turn = 0;
                }
            }

            int count=0;
            for(int i=0; i<target.length; i++){
                if(target[i]!=2){
                    count+=1;
                }
            }
            if(count>=9){
                TextView wintext = (TextView) findViewById(R.id.wintext);
                    Button btn=(Button) findViewById(R.id.btn);
                wintext.setVisibility(View.VISIBLE);
                btn.setVisibility(View.VISIBLE);
                msg = "Game draw";
                sound_draw.start();
            }

            for (int[] winer : winpos) {
                if (target[winer[0]] == target[winer[1]] && target[winer[1]] == target[winer[2]] && target[winer[0]] == target[winer[2]] && target[winer[0]] != 2) {
                    if(target[winer[0]]==1){
                        msg="Cross won the game";
                        sound_win.start();

                    }else{
                        sound_win.start();
                        msg = "Circle won the game";
                    }
                    isactiv=false;
                    TextView wintext = (TextView) findViewById(R.id.wintext);
                    Button btn=(Button) findViewById(R.id.btn);
                    wintext.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);
                }
            }
            TextView wintext = (TextView) findViewById(R.id.wintext);
            wintext.setText(msg);
        }
    }
    public void btnclick(View view){
        TextView wintext = (TextView) findViewById(R.id.wintext);
        Button btn=(Button) findViewById(R.id.btn);
        ImageView wintext1 = (ImageView) findViewById(R.id.imageView11);
        ImageView wintext2 = (ImageView) findViewById(R.id.imageView12);
        wintext.setVisibility(View.VISIBLE);
        wintext.setText("Choose One of them");
        btn.setVisibility(View.INVISIBLE);
        wintext1.setVisibility(View.VISIBLE);
        wintext2.setVisibility(View.VISIBLE);
        isactiv=false;
        turn = 0;
        for(int i=0; i<target.length; i++){
            target[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound_draw = MediaPlayer.create(this, R.raw.wrong_sound);
        sound_win = MediaPlayer.create(this,R.raw.win_sound);
        sound_wrongtap = MediaPlayer.create(this,R.raw.fart_sound);
        sound_tap1 = MediaPlayer.create(this,R.raw.pop_sound);
        sound_tap2 = MediaPlayer.create(this,R.raw.pop_sound2);
    }
}