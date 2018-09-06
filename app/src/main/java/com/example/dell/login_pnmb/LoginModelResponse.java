package com.example.dell.login_pnmb;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModelResponse {

    @SerializedName("LoginID")
    @Expose
    private String loginID;
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("Longitute")
    @Expose
    private String longitute;
    @SerializedName("Latitute")
    @Expose
    private String latitute;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("IP")
    @Expose
    private String iP;
    @SerializedName("Comment")
    @Expose
    private String comment;

    public LoginModelResponse(String loginID, String sessionId, String longitute, String latitute, String location, String address, String iP, String comment) {
        super();
        this.loginID = loginID;
        this.sessionId = sessionId;
        this.longitute = longitute;
        this.latitute = latitute;
        this.location = location;
        this.address = address;
        this.iP = iP;
        this.comment = comment;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLongitute() {
        return longitute;
    }

    public void setLongitute(String longitute) {
        this.longitute = longitute;
    }

    public String getLatitute() {
        return latitute;
    }

    public void setLatitute(String latitute) {
        this.latitute = latitute;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIP() {
        return iP;
    }

    public void setIP(String iP) {
        this.iP = iP;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "LoginModelResponse { " +
                " LoginID = " + loginID  +
                ", sessionId = " + sessionId +
                ", Longitute = " + latitute +
                ", Latitute = " + longitute +
                "}";
    }
}