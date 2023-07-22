package com.example.myapplication.NotificationSetup;

public class PurchaseNotification {
    private int id;
    private String title;

    public PurchaseNotification(int id, String title) {
        this.id = id;
        this.title = title;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
}