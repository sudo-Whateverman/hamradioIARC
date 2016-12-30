package org.iarc.nick.hamradioiarc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.Serializable;

public class MainMenu extends AppCompatActivity {

    private static final Integer NUM_OF_QUESTIONS_BASIC = 3; // actual number of pages - 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void startBasicExam(View view){
        Intent i = new Intent(this, questionaire.class);
        Exam exam= new Exam("BasicExam",NUM_OF_QUESTIONS_BASIC);
        // TODO : this is not the most pleasant view to pass intent. but this will do, pig.
        i.putExtra("number_of_questions",NUM_OF_QUESTIONS_BASIC);
        for (int n = 0; n <= NUM_OF_QUESTIONS_BASIC; n++) {
            i.putExtra("question_" + String.valueOf(n) + "id_in_sql", String.valueOf(exam.question_ids.get(n)));
            i.putExtra("question_" + String.valueOf(n), exam.questionPool.get(n).Question);
            i.putExtra("question_" + String.valueOf(n) + "Question", exam.questionPool.get(n).Question);
            i.putExtra("question_" + String.valueOf(n) + "Answer1", exam.questionPool.get(n).Answer1);
            i.putExtra("question_" + String.valueOf(n) + "Answer2", exam.questionPool.get(n).Answer2);
            i.putExtra("question_" + String.valueOf(n) + "Answer3", exam.questionPool.get(n).Answer3);
            i.putExtra("question_" + String.valueOf(n) + "Answer4", exam.questionPool.get(n).Answer4);
            i.putExtra("question_" + String.valueOf(n) + "Correct_Answer", exam.questionPool.get(n).CorrectAnswer);
        }
        startActivity(i);
    }

}

