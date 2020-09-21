package com.example.tic_tac_toe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import static android.R.*;

public class Game extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    int[] a = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int turn_counter = 1; // odd for "O" & even for "X"
    boolean isGameActive = true;
    String p1="O",p2="X";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       t1 = findViewById(R.id.textView3);
       t2 = findViewById(R.id.textView4);
       t3 = findViewById(R.id.textView5);
       t4 = findViewById(R.id.textView6);
       t5 = findViewById(R.id.textView7);
       t6 = findViewById(R.id.textView8);
       t7 = findViewById(R.id.textView9);
       t8 = findViewById(R.id.textView10);
       t9 = findViewById(R.id.textView11);
       t10 = findViewById(R.id.textView12);

        p1 = getIntent().getStringExtra("Player_1");
        p2 = getIntent().getStringExtra("Player_2");

        t10.setText(p1+"'s turn");

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("UseCompatLoadingForDrawables")
    public void change(View view) {
        if(isGameActive == false)
        {
            return;
        }
        TextView t = findViewById(view.getId());
        int tag = Integer.parseInt(view.getTag().toString());
        System.out.println(tag);
        if(a[tag] == -1)
        {

            if(turn_counter%2 == 0)
            {
                a[tag] = 1;
                t.setText("X");
                t.setTextColor(Color.parseColor("#FF000000"));
                t10.setText(p1+"'s turn ");
            }
            else
            {
                a[tag] = 0;
                t.setText("O");
                t.setTextColor(Color.parseColor("#FF0CDF14"));
                t10.setText(p2+"'s turn ");
            }

            if(turn_counter > 4)
            {
                checkWinner();
            }
            turn_counter += 1;
        }
    }


    private void checkWinner() {
        int flag = 1;
        for(int i=0;i<9;i++)
        {
            System.out.print(a[i] + " ,");
        }
        int[][] winpos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        int p=0,q=0,r=0;
        for(int i=0;i<8;i++)
        {
         p = a[winpos[i][0]];
         q = a[winpos[i][1]];
         r = a[winpos[i][2]];

         if(p == q & q == r)
         {
             if(p == 0)
             {
                 t10.setText(p1+" is winner ");
                 showDialog(p1+" is winner");
                 isGameActive = false;
             }
             if(p==1)
             {
                 t10.setText(p2+" is winner ");
                 showDialog(p2+" is winner");
                 isGameActive = false;
             }
         }

        }
        for(int i=0;i<9;i++)
        {
            if(a[i] == -1)
                flag = 0;
        }
        if(flag == 1) {
            t10.setText("Its, a Draw");
            showDialog("Its, is Draw");
            isGameActive = false;
        }

    }
    private void showDialog(String winner)
    {
        new AlertDialog.Builder(this)
                .setTitle("Game Over. "+winner)
                .setCancelable(false)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restart();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Game.this.finish();
                    }
                })
                .show();
    }

    private void restart() {
        isGameActive = true;
        a = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
        turn_counter = 1;
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
        t8.setText("");
        t9.setText("");
        t10.setText(p1+"'s turn ");
    }
}
