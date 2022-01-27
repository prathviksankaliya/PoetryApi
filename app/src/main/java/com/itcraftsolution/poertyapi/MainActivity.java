package com.itcraftsolution.poertyapi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itcraftsolution.poertyapi.Api.ApiPostData;
import com.itcraftsolution.poertyapi.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private EditText nameEdt, jobEdt ,edtid ;
    private Button postDataBtn , btnUpdate , btnDelete ,btnSelect;
    private TextView responseTV;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nameEdt = findViewById(R.id.idEdtName);
        jobEdt = findViewById(R.id.idEdtJob);
        edtid = findViewById(R.id.idEdtId);
        postDataBtn = findViewById(R.id.idBtnPost);
        btnUpdate = findViewById(R.id.idBtnUpdate);
        btnSelect = findViewById(R.id.idBtnselect);
        btnDelete = findViewById(R.id.idBtndelete);
        responseTV = findViewById(R.id.idTVResponse);
        loadingPB = findViewById(R.id.idLoadingPB);

        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (nameEdt.getText().toString().isEmpty() && jobEdt.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                ApiPostData apiPostData = new ApiPostData();
                apiPostData.postDatausingVolley(MainActivity.this , nameEdt.getText().toString(), jobEdt.getText().toString());
//                postDatausingVolley(nameEdt.getText().toString(), jobEdt.getText().toString());
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                edtid.setVisibility(View.VISIBLE);
                if (jobEdt.getText().toString().isEmpty() && edtid.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postUpdate(edtid.getText().toString(), jobEdt.getText().toString());
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                edtid.setVisibility(View.VISIBLE);
                if (edtid.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postDelete(edtid.getText().toString());
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue ;
        requestQueue = Volley.newRequestQueue(MainActivity.this);

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("http://192.168.0.102:80/poetryapi/readpoetry.php", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                   try {
                       for (int i=0; i< response.length(); i++)
                       {
                       JSONObject obj = response.getJSONObject(i);
                       Log.d("myapp", "onResponse: "+obj.get("poet_name"));
                       responseTV.setText(String.valueOf(obj.get("poet_name")));
                       }
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("myapp" , "Somthing went wrong");
            }
        });

        requestQueue.add(jsonArrayRequest);

            }
        });

//
//        RequestQueue requestQueue ;
//        requestQueue = Volley.newRequestQueue(this);
//
//                        json Object with volley librery
//
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://192.168.0.102:80/poetryapi/readpoetry.php", new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("myapp" , "responce is : "+response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.d("myapp" , "Somthing went wrong");
//            }
//        });
//
//        requestQueue.add(jsonObjectRequest);



                    //        json Array with volley librery

//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://jsonplaceholder.typicode.com/todos/", new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//
//                   try {
//                       for (int i=0; i< response.length(); i++)
//                       {
//                       JSONObject obj = response.getJSONObject(i);
//                       Log.d("myapp", "onResponse: "+obj.get("title"));
//                       }
//                   } catch (JSONException e) {
//                       e.printStackTrace();
//                   }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Log.d("myapp" , "Somthing went wrong");
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);


    }

//    private void postDatausingVolley(String name , String job)
//    {
//
//        String url = "http://192.168.0.102:80/poetryapi/addpoetry.php";
//        loadingPB.setVisibility(View.VISIBLE);
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                loadingPB.setVisibility(View.GONE);
//                nameEdt.setText("");
//                jobEdt.setText("");
//                Toast.makeText(MainActivity.this, "Data added to Api ", Toast.LENGTH_SHORT).show();
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    String Name = obj.getString("poetry_data");
//                    String Job = obj.getString("poet_name");
//
//                    responseTV.setText("Name : "+Name +"\n"+"Job : "+Job);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//
//
//            }
//        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        loadingPB.setVisibility(View.GONE);
//                        Toast.makeText(MainActivity.this, "Somthing went wrong !  "+error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//        })
//        {
//
//            @Override
//            protected Map<String, String> getParams()  {
//                Map<String , String> params = new HashMap<String , String>();
//                params.put("poetry" , name);
//                params.put("poet_name" , job);
//
//
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
//
//    }
    private void postUpdate(String id , String updatepoetry)
    {

        String url = "http://192.168.0.102:80/poetryapi/updatepoetry.php";
        loadingPB.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadingPB.setVisibility(View.GONE);
                nameEdt.setText("");
                jobEdt.setText("");
                edtid.setText("");
                Toast.makeText(MainActivity.this, "Data Updated to Api ", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){

            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<String , String>();
                params.put("id" , id);
                params.put("poetry_data" , updatepoetry);


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void postDelete(String id)
    {

        String url = "http://192.168.0.102:80/poetryapi/deletepoetry.php";
        loadingPB.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loadingPB.setVisibility(View.GONE);
                nameEdt.setText("");
                jobEdt.setText("");
                edtid.setText("");
                Toast.makeText(MainActivity.this, "Data Deleted to Api ", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){

            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<String , String>();
                params.put("poetry_id" , id);


                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}