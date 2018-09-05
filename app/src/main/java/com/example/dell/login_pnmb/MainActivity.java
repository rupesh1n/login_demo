package com.example.dell.login_pnmb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static com.example.dell.login_pnmb.HomeActivity.LOGIN_ID;
import static com.example.dell.login_pnmb.HomeActivity.SESSION_ID;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button btnLogout,submitBtn;
    private DbHandler db;
    private SessionManager session;
    private TextView mResponseTv;
    private APIService mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        final EditText titleEt = (EditText) findViewById(R.id.textview3);
        final EditText bodyEt = (EditText) findViewById(R.id.textview4);
        final EditText titleEt1 = (EditText) findViewById(R.id.textview5);
        final EditText bodyEt1 = (EditText) findViewById(R.id.textview6);
        final EditText titleEt2 = (EditText) findViewById(R.id.textview7);
        final EditText bodyEt2 = (EditText) findViewById(R.id.textview8);
        submitBtn = (Button) findViewById(R.id.btn_send);
        mResponseTv = (TextView) findViewById(R.id.tv_response);
        btnLogout = (Button) findViewById(R.id.logout);
        final TextView tv1=(TextView)findViewById(R.id.textview1);
        final  TextView tv2=(TextView)findViewById(R.id.textview2);

        mAPIService = ApiUtils.getAPIService();

// start on Click listner for validation
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LoginID =tv1.getText().toString().trim();
                String sessionId=tv2.getText().toString().trim();;
                String Longitute = titleEt.getText().toString().trim();
                String Latitute = bodyEt.getText().toString().trim();
                String location=titleEt1.getText().toString().trim();
                String Address=bodyEt1.getText().toString().trim();
                String IP=titleEt2.getText().toString().trim();
                String Comment=bodyEt2.getText().toString().trim();
                //if (!TextUtils.isEmpty(Latitute) && !TextUtils.isEmpty(Longitute) && !TextUtils.isEmpty(location) && !TextUtils.isEmpty(Address) && !TextUtils.isEmpty(IP) && !TextUtils.isEmpty(Comment)) {
                    sendPost(LoginID,sessionId,Latitute, Longitute,location,Address,IP,Comment);
               // }
            }
        });



        String nameFromIntent = getIntent().getStringExtra(LOGIN_ID);
        String sessionIntent=getIntent().getStringExtra(SESSION_ID);
        tv2.setText("" + sessionIntent);
        tv1.setText(nameFromIntent);

        db = new DbHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }
//        HashMap<String, String> user = db.getUserDetails();






        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });
    }

    public void sendPost(String LoginID, String sessionId,String Longitute,String Latitute,String Location,String Address,String IP,String Comment) {
        mAPIService.savePost(LoginID, sessionId,Longitute,Latitute,Location,Address,IP,Comment).enqueue(new Callback<LoginModelResponse>()

        {
            @Override
            public void onResponse(Call<LoginModelResponse> call, Response<LoginModelResponse> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                     Log.d(TAG, "post submitted to API." + response.body().toString());
                     Log.e(TAG, "POST Submitted to API" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<LoginModelResponse> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");

            }
        });
    }

    private void logoutUser() {
        session.setLogin(false);
        db.deleteUsers();
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }


}
