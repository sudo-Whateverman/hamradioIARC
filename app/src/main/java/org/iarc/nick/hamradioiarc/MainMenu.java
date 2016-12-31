package org.iarc.nick.hamradioiarc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    private static final Integer NUM_OF_QUESTIONS_BASIC = 3; // actual number of pages - 1
    public static final String JSON_URL = "https://pure-crag-14295.herokuapp.com/qs/";
    private List<String> question_queue;
    private List<String> answered1_queue;
    private List<String> answered2_queue;
    private List<String> answered3_queue;
    private List<String> Correctanswered_queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        this.question_queue = new ArrayList<>();
        this.answered1_queue = new LinkedList<>();
        this.answered2_queue = new LinkedList<>();
        this.answered3_queue = new LinkedList<>();
        this.Correctanswered_queue = new LinkedList<>();

        sendRequest();
    }

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            showJSON(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainMenu.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) throws JSONException, UnsupportedEncodingException {
        JSONArray jsonarray = new JSONArray(json);
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            Log.e("message is", jsonobject.getString("Question"));
            this.question_queue.add(new String(jsonobject.getString("Question").getBytes("ISO-8859-1"),"UTF-8"));
            this.answered1_queue.add(new String(jsonobject.getString("AdditionalAnswer1").getBytes("ISO-8859-1"),"UTF-8"));
            this.answered2_queue.add(new String(jsonobject.getString("AdditionalAnswer2").getBytes("ISO-8859-1"),"UTF-8"));
            this.answered3_queue.add(new String(jsonobject.getString("AdditionalAnswer3").getBytes("ISO-8859-1"),"UTF-8"));
            this.Correctanswered_queue.add(new String(jsonobject.getString("CorrectAnswer").getBytes("ISO-8859-1"),"UTF-8"));
        }
    }

    public void startBasicExam(View view){
        Intent i = new Intent(this, questionaire.class);
        Exam exam= new Exam("BasicExam",NUM_OF_QUESTIONS_BASIC);
        // TODO : this is not the most pleasant view to pass intent. but this will do, pig.
        i.putExtra("number_of_questions",NUM_OF_QUESTIONS_BASIC);
        for (int n = 0; n <= NUM_OF_QUESTIONS_BASIC; n++) {
            i.putExtra("question_" + String.valueOf(n) + "id_in_sql", exam.question_ids.get(n));
            i.putExtra("question_" + String.valueOf(n) + "Question", exam.questionPool.get(n).Question);
            i.putExtra("question_" + String.valueOf(n) + "Answer1", exam.questionPool.get(n).Answer1);
            i.putExtra("question_" + String.valueOf(n) + "Answer2", exam.questionPool.get(n).Answer2);
            i.putExtra("question_" + String.valueOf(n) + "Answer3", exam.questionPool.get(n).Answer3);
            i.putExtra("question_" + String.valueOf(n) + "Answer4", exam.questionPool.get(n).Answer4);
            i.putExtra("question_" + String.valueOf(n) + "Correct_Answer", exam.questionPool.get(n).CorrectAnswer);
        }
        startActivity(i);
    }

    public void startadvanced(View view){
        /*
        TODO:
        1.populate more questions.
        2.start randomizing it.
        3.save versioned api result.
        4.fix the little ident in the view.
        5.track progress
        6.make more api for different exam levels.
        7.design some more.
         */
        Intent i = new Intent(this, questionaire.class);
        i.putExtra("number_of_questions",NUM_OF_QUESTIONS_BASIC);
        for (int n = 0; n < question_queue.size(); n++) {
            i.putExtra("question_" + String.valueOf(n) + "id_in_sql", n);
            Log.e("startAdvanced", String.valueOf(n));
            i.putExtra("question_" + String.valueOf(n) + "Question",this.question_queue.get(n));
            i.putExtra("question_" + String.valueOf(n) + "Answer1", this.answered1_queue.get(n));
            i.putExtra("question_" + String.valueOf(n) + "Answer2", this.answered2_queue.get(n));
            i.putExtra("question_" + String.valueOf(n) + "Answer3", this.answered3_queue.get(n));
            i.putExtra("question_" + String.valueOf(n) + "Answer4", this.Correctanswered_queue.get(n));
            i.putExtra("question_" + String.valueOf(n) + "Correct_Answer", this.Correctanswered_queue.get(n));
        }
        startActivity(i);
    }

}

