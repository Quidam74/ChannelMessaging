package com.florian.bellanger.channelmessaging.Activity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import android.provider.MediaStore;
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
import com.florian.bellanger.channelmessaging.ClasseMetier.ListMessage;
import com.florian.bellanger.channelmessaging.HttpPostHandler;
import com.florian.bellanger.channelmessaging.OnDownloadListener;
import com.florian.bellanger.channelmessaging.R;
import com.florian.bellanger.channelmessaging.mesArrayAdapter.MyAAMessage;
import com.google.gson.Gson;

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
    private Button bouttonSendphoto;
    private EditText msgbox;
    private Handler handler;
    public static final String PREFS_NAME = "MyPrefsFile";
    private Timer t;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_droite_mesg_list, container);

        bouttonSendphoto  = (Button) v.findViewById(R.id.sendphoto);
        msgbox = (EditText) v.findViewById(R.id.msgbox);
        chanelName = (TextView) v.findViewById(R.id.nameOfChanel);
        listeMessage = (ListView) v.findViewById(R.id.ListeMessage);
        bouttonSendNudes =  (Button) v.findViewById(R.id.sendNudes);
        bouttonSendNudes.setOnClickListener(this);

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
        final Bundle extras = getActivity().getIntent().getExtras();

        handler = new Handler();


        t = new Timer();
                t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                final HttpPostHandler getAllMessages = new HttpPostHandler();


                final CalledInformation messages = new CalledInformation();
                messages.setCoupleIDPWD("accesstoken", settings.getString("MyToken", "error"));

                // extras.getString("ChannelID")
                //getActivity().getIntent().getStringExtra("ChannelID")
                messages.setCoupleIDPWD("channelid",  getActivity().getIntent().getStringExtra("ChannelID"));



                messages.setUrl("http://www.raphaelbischof.fr/messaging/?function=getmessages");
                getAllMessages.execute(messages);

                getAllMessages.addOnDownloadListener(new OnDownloadListener() {


                                                         @Override

                                                         public void onDownloadComplete(String downloadedContent) {
                                                             if (downloadedContent.length() > 200&&getActivity()!=null) {
                                                                 Gson gson = new Gson();
                                                                 ListMessage lesMessages = gson.fromJson(downloadedContent, ListMessage.class);



                                                                 listeMessage.setAdapter(new MyAAMessage(getActivity(), lesMessages));
                                                             }

                                                         }

                                                         @Override
                                                         public void onDownloadError(String error) {

                                                         }
                                                     }

                );


            }
        }, 50, 1000);
    }


    public void fillTextView(String channel) {

        Log.i("a", channel);
        chanelName.setText("channel " + channel);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendNudes) {
            SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
            final Bundle extras = getActivity().getIntent().getExtras();
            HttpPostHandler postMSG = new HttpPostHandler();
            CalledInformation message = new CalledInformation();
            message.setCoupleIDPWD("accesstoken",settings.getString("MyToken", "error"));
            message.setCoupleIDPWD("channelid",extras.getString("ChannelID"));
            message.setCoupleIDPWD("message",msgbox.getText().toString() );
            message.setUrl("http://www.raphaelbischof.fr/messaging/?function=sendmessage");
            postMSG.execute(message);
            Log.i("zeazea","test");
        }
        if (v.getId() == R.id.sendphoto)
            envoyerPhoto();
    }

    private void envoyerPhoto(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //Création de l’appel  à l’application appareil photo pour récupérer une image
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, uri); //Emplacement de l’image stockée
        //startActivityForResult(intent, PICTURE_REQUEST_CODE);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        t.cancel();
    }
}