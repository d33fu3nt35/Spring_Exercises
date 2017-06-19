package com.example.demo.models;

/**
 * Created by daniel on 6/19/17.
 */

public class Post {

    private String title;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
