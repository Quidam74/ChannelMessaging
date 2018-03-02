package com.florian.bellanger.channelmessaging.mesArrayAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.florian.bellanger.channelmessaging.ClasseMetier.ChannelData;
import com.florian.bellanger.channelmessaging.ClasseMetier.UnChannel;
import com.florian.bellanger.channelmessaging.R;

/**
 * Created by bellangf on 22/01/2018.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<UnChannel>{
    private final Context context;
    private final ChannelData values;

    public MySimpleArrayAdapter(Context context, ChannelData values) {
        super(context, R.layout.ma_ligne, values.getListeDesChanel());
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View rowView = inflater.inflate(R.layout.ma_ligne, parent, false);
        TextView NomChan = (TextView) rowView.findViewById(R.id.nomChanel);
        TextView nbSurChan = (TextView) rowView.findViewById(R.id.nombrePersonnes);

        NomChan.setText(values.getListeDesChanel().get(position).getName());
        nbSurChan.setText(values.getListeDesChanel().get(position).getConnectedusers());

        Log.i("Mange",values.toString());

        return rowView;
    }

    @Override
    public long getItemId(int position) {
        return values.getListeDesChanel().get(position).getChannelID();
    }
}