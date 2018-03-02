package com.florian.bellanger.channelmessaging.Activity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.florian.bellanger.channelmessaging.CalledInformation;
import com.florian.bellanger.channelmessaging.HttpPostHandler;
import com.florian.bellanger.channelmessaging.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by bellangf on 26/02/2018.
 */
public class MessageFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView tvExample;

    private TextView chanelName;
    private ListView listeMessage;
    private Button bouttonSendNudes;
    private EditText msgbox;
    private Handler handler;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_droite_mesg_list,container);
        tvExample = (TextView)v.findViewById(R.id.textView);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final SharedPreferences settings = this.getActivity().getSharedPreferences(PREFS_NAME, 0);
        final Bundle extras = this.getActivity().getIntent().getExtras();
        chanelName.setText("channel" + extras.getString("ChannelID"));

        handler = new Handler();


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final HttpPostHandler getAllMessages = new HttpPostHandler();

                final CalledInformation messages = new CalledInformation();


                //  getAllMessages.addOnDownloadListener((ChannelActivity)getActivity());
                messages.setCoupleIDPWD("accesstoken", settings.getString("MyToken", "error"));
                messages.setCoupleIDPWD("accesstoken", settings.getString("MyToken", "error"));
                if(! (extras.getString("ChannelID")==null))
                    messages.setCoupleIDPWD("channelid", extras.getString("ChannelID"));
                else {
                    String a ="1";
                    messages.setCoupleIDPWD("channelid", a);
                }

                messages.setUrl("http://www.raphaelbischof.fr/messaging/?function=getmessages");
                getAllMessages.execute(messages);

            }
        }, 50, 1000);
    }

    @Override
    public void onClick(View view) {
    }


        public void fillTextView(String listItem) {
        tvExample.setText(listItem);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    }