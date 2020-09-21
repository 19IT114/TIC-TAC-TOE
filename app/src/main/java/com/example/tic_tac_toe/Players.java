package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Players extends AppCompatActivity {
    TextInputLayout t1,t2;
    Button nextbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        t1 = findViewById(R.id.player1);
        t2 = findViewById(R.id.player2);
    }
    public void gotonext(View view) {
        boolean check=true;
        String play_1 = t1.getEditText().getText().toString().trim();
        if(play_1.isEmpty())
        {
            t1.setError("This Field cant be empty");
            check = false;
        }
        else
        {
            t1.setError(null);
            check = true;
        }
        String play_2 = t2.getEditText().getText().toString().trim();
        if(play_2.isEmpty())
        {
            t2.setError("This Field cant be empty");
            check = false;
        }
        else
        {
            t2.setError(null);
            check = true;
        }

        if(check == false)
        {
            return;
        }
        String info = "Player 1 = "+play_1+"\n"+"Player 2 = "+play_2;
        Toast.makeText(this,info,Toast.LENGTH_SHORT).show();

        System.out.println(play_1+" "+play_2);
        Intent a = new Intent(this,Game.class);
        a.putExtra("Player_1",play_1);
        a.putExtra("Player_2",play_2);
        startActivity(a);
        finish();

    }
}
