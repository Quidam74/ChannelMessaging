package com.florian.bellanger.channelmessaging.Activity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.florian.bellanger.channelmessaging.CalledInformation;
import com.florian.bellanger.channelmessaging.HttpPostHandler;
import com.florian.bellanger.channelmessaging.InfoConnection;
import com.florian.bellanger.channelmessaging.OnDownloadListener;
import com.florian.bellanger.channelmessaging.R;
import com.google.gson.Gson;

import java.util.Random;


/**
 * Created by bellangf on 19/01/2018.
 */
public class LoginActivity extends Activity implements
        View.OnClickListener, OnDownloadListener {
    private Button loginBtp;
    private EditText pwdBox;
    private EditText idBox;
    private ImageView mIvLogo;
    private TextView textView2;

    public static final String PREFS_NAME = "MyPrefsFile";
    private static final String[] explainStringArray = {
            "Connecte toi pour chatter avec tes amis",
            "Ne met pas des doight dans la porte",
            "teacupe ?"
};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_login);
        textView2 = (TextView) findViewById(R.id.textView2);
        loginBtp = (Button) findViewById(R.id.loginBtn);
        pwdBox = (EditText) findViewById(R.id.pwdBox);
        idBox = (EditText) findViewById(R.id.idBox);
        mIvLogo= (ImageView) findViewById(R.id.mIvLogo);

        idBox.setText("fbell");
        pwdBox.setText("florianbellanger");

        final Handler mHandlerTada = new Handler(); // android.os.handler
        final int mShortDelay = 4000; //milliseconds

        mHandlerTada.postDelayed(new Runnable(){
            public void run(){
                // Your code here
                YoYo.with(Techniques.Tada).duration(2000).playOn(mIvLogo);


                mHandlerTada.postDelayed(this, mShortDelay);
            }
        }, mShortDelay);

        mHandlerTada.postDelayed(new Runnable(){
            public void run(){
                // Your code here
                textView2.setText( explainStringArray[new Random().nextInt(explainStringArray.length)]);

                YoYo.with(Techniques.SlideInLeft).duration(3000).playOn(textView2);

                YoYo.with(Techniques.SlideOutRight).delay(3000).duration(3000).playOn(textView2);
                mHandlerTada.postDelayed(this, 6000);
            }
        }, mShortDelay);



        loginBtp.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.loginBtn) {
            //Animation animSlideLeft = AnimationUtils.loadAnimation(this, R.anim.slide_left);
            //v.startAnimation(animSlideLeft);
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

            Gson gson = new Gson();
            InfoConnection suisjeLog = gson.fromJson(downloadedContent, InfoConnection.class);

            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("MyToken",suisjeLog.getAccesstoken());
            editor.commit();

            //Intent lesChanels =new Intent(getApplicationContext(),ChannelListActivity.class);
            //startActivity(lesChanels);
            Intent loginIntent = new Intent(LoginActivity.this, ChannelListActivity.class);

            startActivity(loginIntent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, mIvLogo, "logo").toBundle());



        }
        else
        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadError(String error) {


    }
}