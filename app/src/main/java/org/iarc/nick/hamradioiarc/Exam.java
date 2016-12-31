package org.iarc.nick.hamradioiarc;

import android.util.Range;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by all on 29.12.2016.
 */

public class Exam implements Serializable {
    private final String Exam_Level;
    private final Integer Items;
    public final List<examquestions> questionPool;
    public final List<Integer> question_ids;

    public Exam(String exam_level, Integer num_questions){
        this.Exam_Level = exam_level;
        this.Items = num_questions;
        this.question_ids = getrandom_ids(get_exam_questions_total(this.Exam_Level),num_questions);
        this.questionPool = get_questions_by_id(this.question_ids, this.Exam_Level);
    }

    public Integer get_exam_questions_total(String exam_level){
        return 3;
    }

    public List<Integer> getrandom_ids(Integer numberofquestions,Integer num_questions){
        List<Integer> range= new ArrayList<>();
        for (int i = 0; i <= numberofquestions; i++) {
            range.add(i);
        }
        Collections.shuffle(range);
        return range.subList(0, num_questions+1);
    }

    public List<Integer> getnotrandom_ids(Integer numberofquestions){
        List<Integer> range = new LinkedList<Integer>();
        for (int i = 0; i <= numberofquestions; i++) {
            range.add(i);
        }
        return range;
    }

    public List<examquestions> get_questions_by_id(List<Integer> question_ids, String exam_Level){
        List<examquestions> questionPool = new LinkedList<examquestions>();
        for (int i = 0; i <= this.Items; i++) {
            questionPool.add(new examquestions(question_ids.get(i),exam_Level));
        }
        return questionPool;
    }

}
