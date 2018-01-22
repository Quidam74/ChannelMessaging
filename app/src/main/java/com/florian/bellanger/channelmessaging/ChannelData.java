package com.florian.bellanger.channelmessaging;

import java.util.List;

/**
 * Created by bellangf on 22/01/2018.
 */
public class ChannelData {
    private List<UnChannel> channels;

    @Override
    public String toString() {
        return "ChannelData{" +
                "listeDesChanel=" + channels +
                '}';
    }

    public List<UnChannel> getListeDesChanel() {
        return channels;
    }

    public void setListeDesChanel(int id, String name, int nbuser) {

        channels.add(new UnChannel(id,name,nbuser));
    }
}
