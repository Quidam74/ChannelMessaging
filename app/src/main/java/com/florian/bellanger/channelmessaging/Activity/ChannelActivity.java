package com.florian.bellanger.channelmessaging.Activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.florian.bellanger.channelmessaging.CalledInformation;
import com.florian.bellanger.channelmessaging.HttpPostHandler;
import com.florian.bellanger.channelmessaging.ClasseMetier.ListMessage;
import com.florian.bellanger.channelmessaging.OnDownloadListener;
import com.florian.bellanger.channelmessaging.R;
import com.florian.bellanger.channelmessaging.mesArrayAdapter.MyAAMessage;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by bellangf on 29/01/2018.
 */
public class ChannelActivity extends Activity
        implements View.OnClickListener, OnDownloadListener {

    private TextView chanelName;
    private ListView listeMessage;
    private Button bouttonSendNudes;
    private EditText msgbox;
    private Handler handler;
    public static final String PREFS_NAME = "MyPrefsFile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("aaa","ChannelActivity est bien lancé");
        setContentView(R.layout.a_channel);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        chanelName = (TextView) findViewById(R.id.nameOfChanel);
        listeMessage = (ListView) findViewById(R.id.ListeMessage);
        bouttonSendNudes = (Button) findViewById(R.id.sendNudes);
        msgbox = (EditText) findViewById(R.id.msgbox);
        bouttonSendNudes.setOnClickListener(this);


        chanelName.setText("channel" + getIntent().getStringExtra("ChannelID"));
        if(getIntent().getStringExtra("ChannelID")==null)
            chanelName.setText("channel" + String.valueOf(settings.getInt("ChannelID", 1)));
        handler = new Handler();


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final HttpPostHandler getAllMessages = new HttpPostHandler();

                final CalledInformation messages = new CalledInformation();
                getAllMessages.addOnDownloadListener(ChannelActivity.this);
                messages.setCoupleIDPWD("accesstoken", settings.getString("MyToken", "error"));
                messages.setCoupleIDPWD("channelid", getIntent().getStringExtra("ChannelID"));

                if(getIntent().getStringExtra("ChannelID")==null)
                    messages.setCoupleIDPWD("channelid", String.valueOf(settings.getInt("ChannelID", 1)));



                messages.setUrl("http://www.raphaelbischof.fr/messaging/?function=getmessages");
                getAllMessages.execute(messages);

            }
        }, 50, 1000);

    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
    if(downloadedContent.length()>200) {
        Gson gson = new Gson();
        ListMessage lesMessages = gson.fromJson(downloadedContent, ListMessage.class);


        listeMessage.setAdapter(new MyAAMessage(ChannelActivity.this, lesMessages));
    }


    }

    @Override
    public void onDownloadError(String error) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendNudes) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            final Bundle extras = getIntent().getExtras();
            HttpPostHandler postMSG = new HttpPostHandler();
            CalledInformation message = new CalledInformation();
            message.setCoupleIDPWD("accesstoken",settings.getString("MyToken", "error"));

            if(getIntent().getStringExtra("ChannelID")==null)
                message.setCoupleIDPWD("channelid",String.valueOf(settings.getInt("ChannelID", 1)));
            else
                message.setCoupleIDPWD("channelid",extras.getString("ChannelID"));

            message.setCoupleIDPWD("message",msgbox.getText().toString() );
            message.setUrl("http://www.raphaelbischof.fr/messaging/?function=sendmessage");
            postMSG.execute(message);

            msgbox.setText("");
        }
    }
}
