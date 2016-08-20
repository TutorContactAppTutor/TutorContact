package com.example.drmsoul.appestudiante;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;

import junit.framework.Test;

/**
 * Created by Soul on 8/19/2016.
 */
public class LoginService extends IntentService {
    public String TestUserData = "Rafael";
    public String TestUserPassword = "123";
    public LoginService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle Extras = intent.getExtras();
        String username = Extras.getString("User");
        String password = Extras.getString("password");

        if (username == TestUserData && password ==TestUserPassword){


        }
    }
}
