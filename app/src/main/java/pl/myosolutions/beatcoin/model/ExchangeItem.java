package pl.myosolutions.beatcoin.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import pl.myosolutions.beatcoin.R;

import static pl.myosolutions.beatcoin.utils.ResourceUtils.getResIdFromString;
import static pl.myosolutions.beatcoin.utils.StringUtils.getResourceIdByCurrency;
import static pl.myosolutions.beatcoin.workflow.main.list.ResponseHelper.getExchangeItemNameBasedOnAcronym;

/**
 * Created by Jacek on 2017-10-02.
 */

public class ExchangeItem extends BaseObservable{

    @SerializedName("baseCurrency")
    private String baseCurrency; //walor
    @SerializedName("conversionCurrency")
    private String conversionCurrency; //waluta wartości waloru
    @SerializedName("baseCurrencyName")
    private String baseCurrencyName;
    @SerializedName("details")
    private ExchangeItemDetails details;
    @SerializedName("icon")
    @Bindable
    private int icon;

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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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
        exchangeItem.icon = getResIdFromString(getResourceIdByCurrency(baseCurrency), R.drawable.class);
        return exchangeItem;
    }
}
