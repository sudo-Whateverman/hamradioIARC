package org.iarc.nick.hamradioiarc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;

public class MainMenu extends AppCompatActivity {

    private static final Integer NUM_OF_QUESTIONS_BASIC = 19; // actual number of pages - 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void startBasicExam(View view){
        Intent i = new Intent(this, questionaire.class);
        Exam exam= new Exam("BasicExam",NUM_OF_QUESTIONS_BASIC);
        for (int n = 0; n <= NUM_OF_QUESTIONS_BASIC; n++) {
            Log.d("ids", String.valueOf(n)+":"+String.valueOf(exam.question_ids.get(n)));
            Log.d("questions",exam.questionPool.get(n).Question);
        }
        //i.putExtra("questionaire", exam);
        startActivity(i);
    }

}

