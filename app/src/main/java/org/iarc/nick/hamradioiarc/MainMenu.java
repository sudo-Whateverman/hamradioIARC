package org.iarc.nick.hamradioiarc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void startExam(View view){
        Intent i = new Intent(this, questionaire.class);
        exam Exam = new exam();
        //i.putExtra("questionaire", Exam);
        startActivity(i);
    }

    class exam implements Serializable {
    }
}
