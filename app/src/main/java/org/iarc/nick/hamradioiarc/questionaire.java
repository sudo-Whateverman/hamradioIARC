package org.iarc.nick.hamradioiarc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

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
            List<String> answer_list = new LinkedList<String>();
            answer_list.add(Answer1);
            answer_list.add(Answer2);
            answer_list.add(Answer3);
            answer_list.add(Answer4);
            return new question(Question, answer_list, correctAnswer, id);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}

