package org.iarc.nick.hamradioiarc;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


/**
 * Created by all on 29.12.2016.
 */

public class examquestions extends FragmentActivity {
    public  String Question;
    public  String Answer1;
    public  String Answer2;
    public  String Answer3;
    public  String Answer4;
    public  String CorrectAnswer;

    public examquestions(Integer id, String examlevel){
        exampopulate(id);
    }

    public void default_values(){
        restAPI rest = new restAPI();
        this.Question = rest.getQuestion();
        this.Answer1 = rest.getAnswer1();
        this.Answer2 = rest.getAnswer2();
        this.Answer3 = rest.getAnswer3();
        this.Answer4 = rest.getAnswer4();
        this.CorrectAnswer = this.Answer3;
    }

    private String TAG = examquestions.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    private static String url = "https://pure-crag-14295.herokuapp.com/qs/";


    public void exampopulate(Integer id) {
        default_values();
        //volleypopulate(id);
    }

    public void volleypopulate(Integer id){
        String url_req = url + String.valueOf(id) + "/";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_req,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObj = null;
                        try {
                            jsonObj = new JSONObject(response);
                            Question = jsonObj.getString("id");
                            Answer1 = jsonObj.getString("CorrectAnswer");
                            Answer2 = jsonObj.getString("AdditionalAnswer1");
                            Answer3 = jsonObj.getString("AdditionalAnswer2");
                            Answer4 = jsonObj.getString("AdditionalAnswer3");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                default_values();
            }
        });
        queue.add(stringRequest);
    }
}
