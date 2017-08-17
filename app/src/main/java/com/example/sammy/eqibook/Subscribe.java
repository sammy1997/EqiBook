package com.example.sammy.eqibook;

/**
 * Created by sammy on 16/8/17.
 */

public class Subscribe {
    String paperName;

    public Subscribe() {
    }

    public Subscribe(String paperName) {
        this.paperName = paperName;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }
}
