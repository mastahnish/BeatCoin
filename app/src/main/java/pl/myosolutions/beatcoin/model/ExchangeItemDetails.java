package pl.myosolutions.beatcoin.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jacek on 2017-09-27.
 */

public class ExchangeItemDetails {

    @SerializedName("last")
    private String last;
    @SerializedName("high")
    private String high;
    @SerializedName("low")
    private String low;
    @SerializedName("volume")
    private String volume;
    @SerializedName("bid")
    private String bid;
    @SerializedName("ask")
    private String ask;
    @SerializedName("beforelast")
    private String beforelast;

    public ExchangeItemDetails() {
    }

    public ExchangeItemDetails(String last, String high, String low, String volume, String bid, String ask, String beforelast) {
        this.last = last;
        this.high = high;
        this.low = low;
        this.volume = volume;
        this.bid = bid;
        this.ask = ask;
        this.beforelast = beforelast;
    }

    public String getLast() {
        return last;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

    public String getVolume() {
        return volume;
    }

    public String getBid() {
        return bid;
    }

    public String getAsk() {
        return ask;
    }

    public String getBeforelast() {
        return beforelast;
    }

    @Override
    public String toString() {
        return "ExchangeItemDetails{" +
                "last='" + last + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", volume='" + volume + '\'' +
                ", bid='" + bid + '\'' +
                ", ask='" + ask + '\'' +
                ", beforelast='" + beforelast + '\'' +
                '}';
    }
}
