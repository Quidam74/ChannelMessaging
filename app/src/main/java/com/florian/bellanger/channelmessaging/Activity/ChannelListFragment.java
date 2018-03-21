package com.florian.bellanger.channelmessaging.Activity;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.florian.bellanger.channelmessaging.CalledInformation;
import com.florian.bellanger.channelmessaging.ClasseMetier.ChannelData;
import com.florian.bellanger.channelmessaging.HttpPostHandler;
import com.florian.bellanger.channelmessaging.OnDownloadListener;
import com.florian.bellanger.channelmessaging.R;
import com.florian.bellanger.channelmessaging.mesArrayAdapter.MySimpleArrayAdapter;
import com.google.gson.Gson;

public class ChannelListFragment extends Fragment implements OnDownloadListener, View.OnClickListener {
    private ListView lvFragment;
    private Button sendNudes;

    public static final String PREFS_NAME = "MyPrefsFile";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_gauche_channel_list, container);
        lvFragment = (ListView) v.findViewById(R.id.mainlistview);





        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        HttpPostHandler maRequete = new HttpPostHandler();
        maRequete.addOnDownloadListener(this);

        CalledInformation getAllChannel = new CalledInformation();
        getAllChannel.setUrl("http://www.raphaelbischof.fr/messaging/?function=getchannels");

        SharedPreferences settings = this.getActivity().getSharedPreferences(PREFS_NAME, 0);
        String jeton = settings.getString("MyToken", "0");
        getAllChannel.setCoupleIDPWD("accesstoken", jeton);

       lvFragment.setOnItemClickListener((ChannelListActivity)getActivity());

        maRequete.execute(getAllChannel);


        lvFragment.setOnItemClickListener((ChannelListActivity)getActivity());
    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
        Gson gson = new Gson();

        ChannelData lesChanel = gson.fromJson(downloadedContent,ChannelData.class);


        lvFragment.setAdapter(new MySimpleArrayAdapter(ChannelListFragment.this.getActivity(), lesChanel ));

    }

    @Override
    public void onDownloadError(String error) {

    }


    @Override
    public void onClick(View v) {

    }
}