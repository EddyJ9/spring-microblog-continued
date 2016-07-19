package com.theironyard;

import javax.persistence.*;

/**
 * Created by EddyJ on 7/19/16.
 */
@Entity
@Table(name = "messages")
public class Message {

    @Column(nullable = false)
    private String text;

    @Id
    @GeneratedValue
    private int id;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

