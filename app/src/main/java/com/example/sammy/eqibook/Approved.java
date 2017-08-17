package com.example.sammy.eqibook;

/**
 * Created by sammy on 16/8/17.
 */

public class Approved {
    String itemName;
    String status;
    String date;

    public Approved() {

    }

    public Approved(String itemName, String status, String date) {
        this.itemName = itemName;
        this.status = status;
        this.date = date;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
