package com.suleiman.techkriti.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.suleiman.techkriti.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Login extends AppCompatActivity {
    Button login;EditText inputName,inputpwd;


    private ProgressDialog pDialog;
    String pid,aid,username,id,phone,college,email,facebook;
  String event[];
    String competition[];
    String comp_members[];
    String team[];
    String approved_comp[];
    String comp_names[];
    String workshop[];
    String work_members[];
    String work_names[];
    String approved_work[];
    String pay[];


    JSONParser jParser = new JSONParser();
    JSONParser1 jParser1 = new JSONParser1();
    JSONParser3 jParser3 = new JSONParser3();

    int counter=0;
    String url[]=new String[4];

    ArrayList<HashMap<String, String>> productsList;

    String url_all_products ;
    String url_all_products1;
    String url_all_products2;
    String url_all_products3;
    JSONArray  abc=null;


    private static final String TAG_SUCCESS = "login";
    private static final String TAG_ANS = "log";
    private static final String TAG_C = "C";

    private static final String TAG_NAME = "name";
    private static final String TAG_PWD = "password";
    static java.net.CookieManager msCookieManager = new java.net.CookieManager();


    String name;String pwd;
    TextView signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        productsList = new ArrayList<HashMap<String, String>>();


        inputName = (EditText) findViewById(R.id.name);
        inputpwd = (EditText) findViewById(R.id.pwd);
        login = (Button) findViewById(R.id.login);
        signup=(TextView)findViewById(R.id.signUpTextView);








        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name = inputName.getText().toString();
                pwd = inputpwd.getText().toString();
                url_all_products = "http://www.techkriti.org/app/login.php";
                url_all_products1 = "http://www.techkriti.org/registration/php/profile_user.php";
               url_all_products2="http://www.techkriti.org/registration/php/profile.php";
                url_all_products3="http://www.techkriti.org/registration/php/profile_cawork.php";
                url[0] = url_all_products;
                url[1] = url_all_products1;
                url[2]=url_all_products2;
                url[3]=url_all_products3;
                ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

                Boolean isInternetPresent = cd.isConnectingToInternet();
                if(isInternetPresent==true)
                {
                new abc().execute();}
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent =new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });


    }

    class abc extends AsyncTask<String, String, String > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Logging in. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }


        protected String doInBackground(String... args) {


            HashMap<String, String> params = new HashMap<>();
            Log.d("FFFGGG", url_all_products);
            HashMap<String, String>  fin1 = new HashMap<>();
            fin1.put("gender","Male");


            fin1.put("pass",pwd);
            fin1.put("username",name);

            JSONObject json = jParser.makeHttpRequest(url[0], "GET", fin1);

            msCookieManager=jParser.cookme();
            JSONArray jsons = jParser3.makeHttpRequest(url[1], "GET", params,msCookieManager);
            JSONArray jsonss = jParser3.makeHttpRequest(url[2], "GET", params,msCookieManager);
            JSONArray jsonsss = jParser3.makeHttpRequest(url[3], "GET", params,msCookieManager);


            try {

                String success = json.getString("success");

                if (success !="false" ) {





                    competition=new String[jsonss.length()];
                    comp_members=new String[jsonss.length()];
                    team =new String[jsonss.length()];
                    approved_comp=new String[jsonss.length()];
                    comp_names=new String[jsonss.length()];
                    workshop=new String[jsonsss.length()];
                    work_members=new String[jsonsss.length()];
                    work_names=new String[jsonsss.length()];
                    approved_work=new String[jsonsss.length()];
                    pay=new String[jsonsss.length()];
                    for(int i=0;i<jsons.length();i++) {
                        JSONObject products = jsons.getJSONObject(i);

                        id = products.getString("techid");
                        username = products.getString("username");
                        name = products.getString("name");
                        phone = products.getString("phone");
                        college = products.getString("college");
                    }
                    for(int i=0;i<jsonss.length();i++) {
                        JSONObject products = jsonss.getJSONObject(i);

                        competition[i] = products.getString("competition");
                        comp_members[i] = products.getString("members");
                        team[i] = products.getString("team");
                        approved_comp[i] = products.getString("approved");
                        comp_names[i] = products.getString("names");
                    }
                    for(int i=0;i<jsonsss.length();i++) {
                        JSONObject products = jsonsss.getJSONObject(i);

                        workshop[i] = products.getString("competition");
                        work_members[i] = products.getString("members");
                        pay[i] = products.getString("pay");
                        approved_work[i] = products.getString("approved");
                        work_names[i] = products.getString("names");
                    }
                    Intent intent=new Intent(Login.this,profile.class);
                    intent.putExtra("id",id);


                    intent.putExtra("username",username);
                    intent.putExtra("name",name);

                    intent.putExtra("email",phone);


                    intent.putExtra("college",college);
                    intent.putExtra("email",email);
                    intent.putExtra("facebook",facebook);
                    intent.putExtra("pwd",pwd);
                    intent.putExtra("competition",competition);
                    intent.putExtra("comp_members",comp_members);
                    intent.putExtra("team",team);
                    intent.putExtra("approved_comp",approved_comp);
                    intent.putExtra("comp_names",comp_names);

                    intent.putExtra("workshop",workshop);
                    intent.putExtra("work_members",work_members);
                    intent.putExtra("pay",pay);
                    intent.putExtra("approved_work",approved_work);
                    intent.putExtra("work_name",work_names);







                    startActivity(intent);


                }
                else
                {}





            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {

            pDialog.dismiss();



        }


    }



}
