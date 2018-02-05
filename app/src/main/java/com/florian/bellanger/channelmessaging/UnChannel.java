package com.florian.bellanger.channelmessaging;

/**
 * Created by bellangf on 22/01/2018.
 */
public class UnChannel {

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public UnChannel(int channelID, String name, int connectedusers) {
        this.setChannelID(channelID);
        this.setName(name);
        this.setConnectedusers(connectedusers);
    }

    @Override
    public String toString() {
        return "UnChannel{" +
                "channelID=" + channelID +
                ", name='" + name + '\'' +
                ", connectedusers=" + connectedusers +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConnectedusers(int connectedusers) {
        this.connectedusers = connectedusers;
    }

    private int channelID;
    private String name;
    private int connectedusers;

    public int getChannelID() {
        return channelID;
    }

    public String getName() {
        return name;
    }

    public String getConnectedusers() {
        return String.valueOf(connectedusers);
    }
}

