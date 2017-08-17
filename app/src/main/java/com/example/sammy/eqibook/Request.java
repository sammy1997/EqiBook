package com.example.sammy.eqibook;

/**
 * Created by sammy on 16/8/17.
 */

public class Request {
    String item;
    String from;
    String to;
    String date;
    String  amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Request() {
    }

    public Request(String item, String from, String to, String date,String Amount) {
        this.item = item;
        this.from = from;
        this.to = to;
        this.date = date;
        this.amount=Amount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
