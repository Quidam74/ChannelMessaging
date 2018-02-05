package com.florian.bellanger.channelmessaging.ClasseMetier;

import java.util.List;

/**
 * Created by bellangf on 05/02/2018.
 */
public class ListMessage {
    private List<Message> messages;

    @Override
    public String toString() {
        return "ListMessage{" +
                "listeMessage=" + messages +
                '}';
    }

    public void setListeMessage(Message listeMessage) {
        this.messages.add(listeMessage);
    }

    public List<Message> getListeMessage() {
        return messages;
    }
}
