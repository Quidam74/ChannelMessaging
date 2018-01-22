package com.florian.bellanger.channelmessaging;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

/**
 * Created by bellangf on 22/01/2018.
 */
public class ChannelListActivity extends Activity
implements OnDownloadListener{
    public static final String PREFS_NAME = "MyPrefsFile";
    private ListView mainListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_list_activity);

        ListView mainListView = (ListView) findViewById(R.id.mainlistview);

        HttpPostHandler maRequete = new HttpPostHandler();
        maRequete.addOnDownloadListener(this);
        CalledInformation getAllChannel = new CalledInformation();
        getAllChannel.setUrl("http://www.raphaelbischof.fr/messaging/?function=getchannels");
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String jeton = settings.getString("MyToken", "0");
        getAllChannel.setCoupleIDPWD("accesstoken", jeton);

        maRequete.execute(getAllChannel);


    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
        Gson gson = new Gson();
        //Log.i("ezaeaz",downloadedContent);
        ChannelData lesChanel = gson.fromJson(downloadedContent,ChannelData.class);
        Log.i("ezaeaz",lesChanel.toString());

//        mainListView.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,  ));
    }

    @Override
    public void onDownloadError(String error) {

    }
}
