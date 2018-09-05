package com.example.dell.login_pnmb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "preferences";
    private static final String PREF_UNAME = "Username";

    private Button login;
    private EditText pswd, uname;
    private CheckBox checkBox;
    private SessionManager session;
    private DbHandler db;
    public static String LOGIN_ID = "loginID";
    public static String LOGIN_PASSWORD = "password";
    public static String SESSION_ID = "session_id";
    private final String DefaultUnameValue = "";
    private String UnameValue, sessionId, loginID;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        if (savedInstanceState != null) {
            if (session.isLoggedIn()) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.putExtra(LOGIN_ID, savedInstanceState.getString(LOGIN_ID));
                intent.putExtra(SESSION_ID, savedInstanceState.getString(SESSION_ID));
                startActivity(intent);
                finish();
            }
        }
    }

    private void init() {
        uname = (EditText) findViewById(R.id.uname);
        pswd = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        checkBox = (CheckBox) findViewById(R.id.checkbox_saveuser);
        login.setOnClickListener(new C03301());
        db = new DbHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());
    }

    /* renamed from: com.example.dell.pmnb_demo.HomeActivity$1 */
    class C03301 implements OnClickListener {
        C03301() {
        }

        public void onClick(View v) {
            String name = uname.getText().toString();
            String password = pswd.getText().toString();
            if (name.equalsIgnoreCase("") && password.equalsIgnoreCase("")) {
                Toast.makeText(HomeActivity.this, "User Does Not Complate Fields", Toast.LENGTH_SHORT).show();
            } else {
                setLogin(name, password);
            }
        }
    }

    private void setLogin(final String name, final String password) {
        final String str = name;
        final String str2 = password;
        savePreferences();
        StringRequest stringRequest = new StringRequest(1, Api.LOGING_URL, new C04462(), new C04473()) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap();
                params.put(LOGIN_ID, str);
                params.put(LOGIN_PASSWORD, str2);
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, 2, 1.0f));
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    /* renamed from: com.example.dell.pmnb_demo.HomeActivity$2 */
    class C04462 implements Listener<String> {
        C04462() {
        }

        public void onResponse(String response) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                if (jsonArray.length() > 0) {
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    if (jsonObject.getString("Status").equalsIgnoreCase("True")) {
                        loginID = jsonObject.getString("LoginID");
                        sessionId = jsonObject.getString("sessionId");
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        session.setLogin(true);
                        intent.putExtra(LOGIN_ID, loginID);
                        intent.putExtra(SESSION_ID, sessionId);
                        startActivity(intent);
                        finish();
                    } else {
                        try {
                            Toast.makeText(HomeActivity.this, "Login info is not correct", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(HomeActivity.this, "Something wrong during login", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                Toast.makeText(HomeActivity.this, "Something wrong in login response", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /* renamed from: com.example.dell.pmnb_demo.HomeActivity$3 */
    class C04473 implements ErrorListener {
        C04473() {
        }

        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            Toast.makeText(HomeActivity.this, "Error during login", Toast.LENGTH_SHORT).show();
        }
    }

    public int checkUser(User user) {
        return this.db.checkUser(user);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(LOGIN_ID, loginID);
        outState.putString(SESSION_ID, sessionId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        savePreferences();

    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }

    private void savePreferences() {
        if (checkBox.isChecked()) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            // Edit and commit username value
            UnameValue = uname.getText().toString();
            editor.putString(PREF_UNAME, UnameValue);
            editor.commit();
        }
    }

    private void loadPreferences() {
        if (checkBox.isChecked()) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);
            // Get user name value
            UnameValue = settings.getString(PREF_UNAME, DefaultUnameValue);
            uname.setText(UnameValue);
        }
    }
}