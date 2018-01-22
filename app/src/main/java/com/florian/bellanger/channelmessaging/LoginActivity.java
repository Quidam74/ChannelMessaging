package com.florian.bellanger.channelmessaging;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;


/**
 * Created by bellangf on 19/01/2018.
 */
public class LoginActivity extends Activity implements
    View.OnClickListener, OnDownloadListener {
        private Button loginBtp;
        private EditText pwdBox;
        private EditText idBox;
    public static final String PREFS_NAME = "MyPrefsFile";

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtp = (Button) findViewById(R.id.loginBtn);
        pwdBox = (EditText) findViewById(R.id.pwdBox);
        idBox = (EditText) findViewById(R.id.idBox);

        loginBtp.setOnClickListener(this);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.loginBtn) {
           HttpPostHandler maRequetCo = new HttpPostHandler();
            maRequetCo.addOnDownloadListener(this);
            maRequetCo.execute(idBox.getText().toString(),pwdBox.getText().toString());
        }


    }

    @Override //a mettre autrepart que dans l'activité, creer une classe pour
    public void onDownloadComplete(String downloadedContent) {

        Log.i("zae",downloadedContent);
        Gson gson = new Gson();
        InfoConnection suisjeLog = gson.fromJson(downloadedContent,InfoConnection.class);
        Log.i("zae",suisjeLog.toString());

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.commit();
    }

    @Override
    public void onDownloadError(String error) {

    }
}