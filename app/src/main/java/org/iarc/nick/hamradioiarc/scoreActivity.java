package org.iarc.nick.hamradioiarc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class scoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        Integer correct = intent.getIntExtra("correct_answers",10);
        Integer numQ = intent.getIntExtra("numberQ",10);
        TextView textView = (TextView) findViewById(R.id.scoretext);
        textView.setText("You have answered "+String.valueOf(correct)+
        " question correct from "+ String.valueOf(numQ));

    }

    public void gohome(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

}
