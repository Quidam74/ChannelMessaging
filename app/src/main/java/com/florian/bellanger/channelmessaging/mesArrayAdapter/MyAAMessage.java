package com.florian.bellanger.channelmessaging.mesArrayAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.florian.bellanger.channelmessaging.ClasseMetier.ChannelData;
import com.florian.bellanger.channelmessaging.ClasseMetier.ListMessage;
import com.florian.bellanger.channelmessaging.ClasseMetier.Message;
import com.florian.bellanger.channelmessaging.ClasseMetier.UnChannel;
import com.florian.bellanger.channelmessaging.R;

/**
 * Created by bellangf on 05/02/2018.
 */
public class MyAAMessage extends ArrayAdapter<Message> {

    private final Context context;
    private final ListMessage values;

    public MyAAMessage(Context context, ListMessage values) {
        super(context, R.layout.ma_ligne, values.getListeMessage());
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView = inflater.inflate(R.layout.a_message, parent, false);
        TextView posteur = (TextView) rowView.findViewById(R.id.idPoseur);
        TextView msg = (TextView) rowView.findViewById(R.id.msg);


        posteur.setText(values.getListeMessage().get(position).getUsername());
        msg.setText(values.getListeMessage().get(position).getMessage());



        return rowView;
    }


}
