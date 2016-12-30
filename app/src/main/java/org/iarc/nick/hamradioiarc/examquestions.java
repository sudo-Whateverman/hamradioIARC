package org.iarc.nick.hamradioiarc;

import java.io.Serializable;

/**
 * Created by all on 29.12.2016.
 */

public class examquestions implements Serializable {
    public final String Question;
    public final String Answer1;
    public final String Answer2;
    public final String Answer3;
    public final String Answer4;
    public final String CorrectAnswer;

    public examquestions(Integer id, String examlevel){
        this.Question = "What is your favourite color?";
        this.Answer1 = "Red";
        this.Answer2 = "Blue";
        this.Answer3 = "Yellow";
        this.Answer4 = "Cake";
        this.CorrectAnswer = this.Answer3;
    }
}
