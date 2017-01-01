package org.iarc.nick.hamradioiarc;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class questionaire extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private int NUM_PAGES;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private int correct = 0;
    private LinkedList answered_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionaire);
        Intent intent = getIntent();
        this.NUM_PAGES = intent.getIntExtra("number_of_questions", 20);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.Pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        this.answered_list = new LinkedList();
        for (int i=0; i<=NUM_PAGES; i++){
            this.answered_list.add(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // TODO : here goes the question building. I should rewrite this to do my stuff.
            Intent intent = getIntent();
            String Question = intent.getStringExtra("question_" + String.valueOf(position) + "Question");
            String Answer1 = intent.getStringExtra("question_" + String.valueOf(position) + "Answer1");
            String Answer2 = intent.getStringExtra("question_" + String.valueOf(position) + "Answer2");
            String Answer3 = intent.getStringExtra("question_" + String.valueOf(position) + "Answer3");
            String Answer4 = intent.getStringExtra("question_" + String.valueOf(position) + "Answer4");
            String correctAnswer = intent.getStringExtra("question_" + String.valueOf(position) + "Correct_Answer");
            Integer id = intent.getIntExtra("question_" + String.valueOf(position) + "id_in_sql",0);
            ArrayList<String> answer_list = new ArrayList<>();
            answer_list.add(Answer1);
            answer_list.add(Answer2);
            answer_list.add(Answer3);
            answer_list.add(Answer4);
            Fragment question_fragment = new question();
            Bundle bundle = new Bundle();
            bundle.putString("Question",Question);
            bundle.putStringArrayList("Answers", (ArrayList<String>) answer_list);
            question_fragment.setArguments(bundle);
            return question_fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    public void checkAnswer(View view){
        Button button_pressed = (Button) view;
        Intent intent = getIntent();
        String correctAnswer = intent.getStringExtra(
                "question_" + String.valueOf(mPager.getCurrentItem()) + "Correct_Answer");
        if (button_pressed.getText().toString().equals(correctAnswer)){
            button_pressed.setBackgroundColor(Color.GREEN);

            if (checkifAnswered(mPager.getCurrentItem())){
            }
            else
            {
                this.correct++;
            }
            if (mPager.getCurrentItem()< NUM_PAGES-1) {
                mPager.setCurrentItem(mPager.getCurrentItem() + 1);
            }
            else {
                // retun to score activity. Pass the correct and number of questions in intent.
                Intent i = new Intent(this, scoreActivity.class);
                i.putExtra("correct_answers", this.correct);
                i.putExtra("numberQ", this.NUM_PAGES);
                startActivity(i);
            }
        }
        else {
            button_pressed.setBackgroundColor(Color.RED);
            checkifAnswered(mPager.getCurrentItem());
        }
    }

    public boolean checkifAnswered(Integer id){
        if ((boolean)this.answered_list.get(id)){
            return true;
        }
        else {
            this.answered_list.set(id, true);
            return false;
        }
    }
}

