package com.example.lenovo.codepractice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lenovo on 25-Mar-18.
 */

public class Problem implements Parcelable {

    private String title;
    private String problemKey;

    public Problem() {
    }

    public Problem(String title, String key) {
        this.title = title;
        this.problemKey = key;
    }

    public String getTitle() {
        return title;
    }

    public String getProblemKey() {
        return problemKey;
    }

    protected Problem(Parcel in) {
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Problem> CREATOR = new Parcelable.Creator<Problem>() {
        @Override
        public Problem createFromParcel(Parcel in) {
            return new Problem(in);
        }

        @Override
        public Problem[] newArray(int size) {
            return new Problem[size];
        }
    };
}
