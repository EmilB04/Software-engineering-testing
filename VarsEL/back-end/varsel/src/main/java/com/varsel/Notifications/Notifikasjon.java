package com.varsel.Notifications;

public class Notifikasjon {
    private String token;
    private String title;
    private String body;

    public Notifikasjon(String token, String title, String body) {
        this.token = token;
        this.title = title;
        this.body = body;
    }

    // Gettere
    public String getToken() {
        return token;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    // Settere
    public void setToken(String token) {
        this.token = token;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

}