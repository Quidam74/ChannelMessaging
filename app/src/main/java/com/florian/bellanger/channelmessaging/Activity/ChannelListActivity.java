package com.florian.bellanger.channelmessaging.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.florian.bellanger.channelmessaging.CalledInformation;
import com.florian.bellanger.channelmessaging.ClasseMetier.ChannelData;
import com.florian.bellanger.channelmessaging.HttpPostHandler;
import com.florian.bellanger.channelmessaging.mesArrayAdapter.MySimpleArrayAdapter;
import com.florian.bellanger.channelmessaging.OnDownloadListener;
import com.florian.bellanger.channelmessaging.R;
import com.google.gson.Gson;

/**
 * Created by bellangf on 22/01/2018.
 */
public class ChannelListActivity extends AppCompatActivity
implements AdapterView.OnItemClickListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    private ListView mainListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list_activity);

        getIntent().putExtra("ChannelID", "1");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ChannelListFragment fragA = (ChannelListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentA_ID);
        MessageFragment fragB = (MessageFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentB_ID);
        getIntent().putExtra("ChannelID", String.valueOf(id));

        if(fragB == null|| !fragB.isInLayout()){
            Intent i = new Intent(getApplicationContext(),ChannelActivity.class);
            startActivity(i);

        } else {
            fragB.fillTextView(String.valueOf(id));



        }
    }
    }




