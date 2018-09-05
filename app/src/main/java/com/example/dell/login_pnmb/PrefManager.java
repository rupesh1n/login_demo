package com.example.dell.login_pnmb;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    // Sharedpref file name
    private static final String PREF_NAME = "user_preferences";

    private static final String LoginID="loginid";
    private  static final String sessionId="sessionid";



    // Shared Preferences
    private SharedPreferences pref;
    //Editor
    private SharedPreferences.Editor editor;
    // Context
    private Context _context;
    // Shared pref mode
    private int PRIVATE_MODE = 0;


    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();

    }

    private PrefManager(Context context, String PrefName) {
        this._context = context;
        pref = _context.getSharedPreferences(PrefName, PRIVATE_MODE);
        editor = pref.edit();

    }

    public static PrefManager getInstance(Context ctx) {
        return new PrefManager(ctx);
    }

    public String getPrefName() {
        return pref.getString(PREF_NAME, "");
    }


    public String getLoginID() {
        return pref.getString(LoginID, "");
    }

    public void setLoginID(String loginid) {
        editor.putString(LoginID, loginid);
        editor.commit();
    }

    public String getSessionId() {
        return pref.getString(sessionId, "");
    }

    public void setSessionId(String sessionid) {
        editor.putString(sessionId, sessionid);
        editor.commit();
    }


}

