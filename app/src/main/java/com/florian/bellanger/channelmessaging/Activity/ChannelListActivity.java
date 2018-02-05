package com.florian.bellanger.channelmessaging.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
public class ChannelListActivity extends Activity
implements OnDownloadListener, AdapterView.OnItemClickListener {
    public static final String PREFS_NAME = "MyPrefsFile";
    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list_activity);

        mainListView = (ListView) findViewById(R.id.mainlistview);

        HttpPostHandler maRequete = new HttpPostHandler();
        maRequete.addOnDownloadListener(this);

        CalledInformation getAllChannel = new CalledInformation();
        getAllChannel.setUrl("http://www.raphaelbischof.fr/messaging/?function=getchannels");

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String jeton = settings.getString("MyToken", "0");
        getAllChannel.setCoupleIDPWD("accesstoken", jeton);

        mainListView.setOnItemClickListener(this);

        maRequete.execute(getAllChannel);




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent unChanel =new Intent(this,ChannelActivity.class);

        unChanel.putExtra("ChannelID",String.valueOf(id));

        startActivity(unChanel);

    }



    @Override
    public void onDownloadComplete(String downloadedContent) {
        Gson gson = new Gson();

        ChannelData lesChanel = gson.fromJson(downloadedContent,ChannelData.class);
        Log.i("ezaeaz",lesChanel.toString());

        mainListView.setAdapter(
                new MySimpleArrayAdapter(ChannelListActivity.this, lesChanel ));

    }

    @Override
    public void onDownloadError(String error) {

    }


}
