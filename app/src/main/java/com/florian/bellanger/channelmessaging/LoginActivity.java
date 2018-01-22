package com.florian.bellanger.channelmessaging;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    protected void onCreate(Bundle savedInstanceState) {
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
            CalledInformation logMoi = new CalledInformation();
            logMoi.setCoupleIDPWD("username", idBox.getText().toString());
            logMoi.setCoupleIDPWD("password", pwdBox.getText().toString());
            logMoi.setUrl("http://www.raphaelbischof.fr/messaging/?function=connect");
            maRequetCo.execute(logMoi);
        }


    }

    @Override //a mettre autrepart que dans l'activité, creer une classe pour
    public void onDownloadComplete(String downloadedContent) {
        if (downloadedContent.contains("Ok"))
        {
            Log.i("zae", downloadedContent);
            Gson gson = new Gson();
            InfoConnection suisjeLog = gson.fromJson(downloadedContent, InfoConnection.class);
            Log.i("zae", suisjeLog.toString());
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("MyToken",suisjeLog.getAccesstoken());
            editor.commit();

            Intent lesChanels =new Intent(getApplicationContext(),ChannelListActivity.class);

            startActivity(lesChanels);
        }
        else
        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadError(String error) {


    }
}