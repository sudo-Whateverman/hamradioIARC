package org.iarc.nick.hamradioiarc;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class question extends Fragment {

    private String Question;
    private String Answer1;
    private String Answer2;
    private String Answer3;
    private String Answer4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.question_fragment, container, false);

        try{
            this.Question = getArguments().getString("Question");
            List<String> answer_list = getArguments().getStringArrayList("Answers");
            Collections.shuffle(answer_list);
            this.Answer1 = answer_list.get(0);
            this.Answer2 = answer_list.get(1);
            this.Answer3 = answer_list.get(2);
            this.Answer4 = answer_list.get(3);
        }
        catch (NullPointerException e){
            this.Answer1 = "No internet connection";
            this.Answer2 = "private String answer3 is probably right ";
            this.Answer3 = "probably a glitch in the matrix";
            this.Answer4 = "I don't know";
            this.Question = "The default constructor has run. why?";}

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



    public question(){

    }


}