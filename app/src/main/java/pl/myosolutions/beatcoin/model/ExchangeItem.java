package pl.myosolutions.beatcoin.model;

import com.google.gson.annotations.SerializedName;

import static pl.myosolutions.beatcoin.workflow.main.list.ResponseHelper.getExchangeItemNameBasedOnAcronym;

/**
 * Created by Jacek on 2017-10-02.
 */

public class ExchangeItem {

    @SerializedName("baseCurrency")
    private String baseCurrency; //walor
    @SerializedName("conversionCurrency")
    private String conversionCurrency; //waluta wartości waloru
    @SerializedName("baseCurrencyName")
    private String baseCurrencyName;
    @SerializedName("details")
    private ExchangeItemDetails details;

    public ExchangeItem() {
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getConversionCurrency() {
        return conversionCurrency;
    }

    public ExchangeItemDetails getDetails() {
        return details;
    }

    public void setDetails(ExchangeItemDetails details) {
        this.details = details;
    }

    public String getBaseCurrencyName() {
        return baseCurrencyName;
    }


    @Override
    public String toString() {
        return "ExchangeItem{" +
                "baseCurrency='" + baseCurrency + '\'' +
                ", conversionCurrency='" + conversionCurrency + '\'' +
                ", baseCurrencyName='" + baseCurrencyName + '\'' +
                ", details=" + details +
                '}';
    }

    public static ExchangeItem makeExchangeItem(String baseCurrency, String conversionCurrency, ExchangeItemDetails details){
        ExchangeItem exchangeItem = new ExchangeItem();
        exchangeItem.baseCurrency = baseCurrency;
        exchangeItem.baseCurrencyName = getExchangeItemNameBasedOnAcronym(baseCurrency);
        exchangeItem.conversionCurrency = conversionCurrency;
        exchangeItem.details = details;
        return exchangeItem;
    }
}
