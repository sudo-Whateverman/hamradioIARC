package org.iarc.nick.hamradioiarc;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class question extends Fragment {

    private final String Question;
    private final String Answer1;
    private final String Answer2;
    private final String Answer3;
    private final String Answer4;
    private final String correct_answer;
    private boolean answered;
    private boolean correctly;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.question_fragment, container, false);
        Button AnswerButton1 = (Button)rootView.findViewById(R.id.answer1);
        Button AnswerButton2 = (Button)rootView.findViewById(R.id.answer2);
        Button AnswerButton3 = (Button)rootView.findViewById(R.id.answer3);
        Button AnswerButton4 = (Button)rootView.findViewById(R.id.answer4);
        TextView questionField = (TextView) rootView.findViewById(R.id.questionfield);
        AnswerButton1.setText(this.Answer1);
        AnswerButton2.setText(this.Answer2);
        AnswerButton3.setText(this.Answer3);
        AnswerButton4.setText(this.Answer4);
        questionField.setText(this.Question);
        return rootView;
    }

    public question(String Question,List<String> answer_list,String correct_answer, Integer id){
        this.Question = Question;
        Collections.shuffle(answer_list);
        this.Answer1 = answer_list.get(0);
        this.Answer2 = answer_list.get(1);
        this.Answer3 = answer_list.get(2);
        this.Answer4 = answer_list.get(3);
        this.correct_answer = correct_answer;
        this.answered = false;
        this.correctly = false;

    }



}