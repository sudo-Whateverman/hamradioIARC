package org.iarc.nick.hamradioiarc;

/**
 * Created by all on 30.12.2016.
 */
public class restAPI {
    private String answer1 = "I am the one asking the questions here!";
    private String answer2 = "its definitely bigger than 3 and less than 5. pi?!";
    private String answer3 = "4";
    private String answer4 = "depends...";
    private String question = "what is 2+2?";

    public restAPI() {
    }


    public String getAnswer3() {
        return answer3;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer4() {
        return answer4;
    }

    public String getQuestion() {
        return question;
    }
}
