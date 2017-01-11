package org.iarc.nick.hamradioiarc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class MainMenu extends AppCompatActivity {

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
                            SharedPreferences prefs = getSharedPreferences(
                                    "org.iarc.nick.hamradioiarc", Context.MODE_PRIVATE);
                            prefs.edit().putString("json", response).apply();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(MainMenu.this, e.toString(),Toast.LENGTH_LONG).show();
                            }
                        Toast.makeText(MainMenu.this,"Loaded questions from server",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainMenu.this,"Internet connection is not available",Toast.LENGTH_LONG).show();
                        SharedPreferences prefs = getSharedPreferences(
                                "org.iarc.nick.hamradioiarc", Context.MODE_PRIVATE);
                        String response = prefs.getString("json", "");
                        try {
                            showJSON(response);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainMenu.this, e.toString(),Toast.LENGTH_LONG).show();
                        }
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


    public void startAdvanced(View view){
        /*
        TODO:
        1.populate more questions.
        3.save versioned api result.
        4.fix the little ident in the view.
        5.track progress
        6.make more api for different exam levels.
        7.design some more.
         */
        Intent i = new Intent(this, questionaire.class);
        i.putExtra("number_of_questions", question_queue.size());
        Log.e("startAdvanced", "there is " + question_queue.size() + " questions!!!!!!");
        List<Integer> list_of_ids = getrandom_ids(question_queue.size(),
                questionsToPopulate(question_queue.size()));
        for (int n = 0; n < list_of_ids.size(); n++) {
            i.putExtra("question_" + String.valueOf(n) + "id_in_sql", list_of_ids.get(n));
            Log.e("startAdvanced", String.valueOf(list_of_ids.get(n)));
            i.putExtra("question_" + String.valueOf(n) + "Question",
                    this.question_queue.get(list_of_ids.get(n)));
            i.putExtra("question_" + String.valueOf(n) + "Answer1",
                    this.answered1_queue.get(list_of_ids.get(n)));
            i.putExtra("question_" + String.valueOf(n) + "Answer2",
                    this.answered2_queue.get(list_of_ids.get(n)));
            i.putExtra("question_" + String.valueOf(n) + "Answer3",
                    this.answered3_queue.get(list_of_ids.get(n)));
            i.putExtra("question_" + String.valueOf(n) + "Answer4",
                    this.Correctanswered_queue.get(list_of_ids.get(n)));
            i.putExtra("question_" + String.valueOf(n) + "Correct_Answer",
                    this.Correctanswered_queue.get(list_of_ids.get(n)));
        }
        startActivity(i);
    }

    public List<Integer> getrandom_ids(Integer numberofquestions, int sizePopulated){
        List<Integer> range= new ArrayList<>();
        for (int i = 0; i < numberofquestions; i++) {
            range.add(i);
        }
        Collections.shuffle(range);
        return range.subList(0, sizePopulated);
    }

    private Integer questionsToPopulate(int _size){
        if (_size < 20){
            return _size;
        }
        else
            return 20;
    }

    public void launchAbout(View view){
        Intent i = new Intent(this, aboutactivity.class);
        startActivity(i);
    }

    public void howTo(View view){
        Intent i = new Intent(this, addQuestion.class);
        startActivity(i);
    }

}

