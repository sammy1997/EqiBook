package com.example.sammy.eqibook;

/**
 * Created by sammy on 16/8/17.
 */

public class Requested {
    String itemName;
    String status;
    String amount;
    String date;

    public Requested() {
    }

    public Requested(String itemName, String status, String amount, String date) {
        this.itemName = itemName;
        this.status = status;
        this.amount = amount;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
