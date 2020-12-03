package com.example.test03;

public class listDataFormat {
    private String mDate, mCC, mPlayerName, mHole;
    private int mPar, mScore;

    public listDataFormat(String mDate, String mCC, String mPlayerName, String mHole, int mPar, int mScore) {
        this.mDate = mDate;
        this.mCC = mCC;
        this.mPlayerName = mPlayerName;
        this.mHole = mHole;
        this.mPar = mPar;
        this.mScore = mScore;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmCC() {
        return mCC;
    }

    public String getmPlayerName() {
        return mPlayerName;
    }

    public String getmHole() {
        return mHole;
    }

    public int getmPar() {
        return mPar;
    }

    public int getmScore() {
        return mScore;
    }
}
