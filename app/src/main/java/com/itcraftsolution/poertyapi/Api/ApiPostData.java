package com.itcraftsolution.poertyapi.Api;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itcraftsolution.poertyapi.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiPostData {

    public void postDatausingVolley(Context context , String name , String job)
    {

        String url = "http://192.168.0.102:80/poetryapi/addpoetry.php";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, "Data added to Api ", Toast.LENGTH_SHORT).show();
                try {
                    JSONObject obj = new JSONObject(response);
                    String Name = obj.getString("poetry_data");
                    String Job = obj.getString("poet_name");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Somthing went wrong !  "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        })
        {

            @Override
            protected Map<String, String> getParams()  {
                Map<String , String> params = new HashMap<String , String>();
                params.put("poetry" , name);
                params.put("poet_name" , job);

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

}



