package pl.myosolutions.beatcoin.workflow.main.list;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import pl.myosolutions.beatcoin.model.BaseCurrency;
import pl.myosolutions.beatcoin.model.ExchangeItem;
import pl.myosolutions.beatcoin.model.ExchangeItemDetails;
import pl.myosolutions.beatcoin.utils.StringUtils;

/**
 * Created by Jacek on 2017-09-27.
 */

public class ResponseHelper {

    private static final String TAG = ResponseHelper.class.getSimpleName();

    public static List<ExchangeItem> convertResponseToExchangeItemlist(Map<String, ExchangeItemDetails> map){
        List<ExchangeItem> list = new ArrayList<>();

        for(Map.Entry<String, ExchangeItemDetails> item : map.entrySet()){
            list.add(ExchangeItem.makeExchangeItem(StringUtils.getBaseCurrency(item.getKey()), StringUtils.getConversionCurrency(item.getKey()), item.getValue()));
        }

        Log.d(TAG, "convertResponseToExchangeItemlist, list: " + list.toString());

        return list;
    }

    public static List<ExchangeItem> getListFilteredByConversionCurrency(List<ExchangeItem> listFromResponse, String currency) {
        List<ExchangeItem> list = new ArrayList<>();

        for(ExchangeItem item : listFromResponse){
            if(StringUtils.containsIgnoreCase(item.getConversionCurrency(), currency)){
                list.add(item);
            }
        }

        return list;

    }

    public static List<ExchangeItem> getListFilteredByBaseCurrency(List<ExchangeItem> listFromResponse, String currency) {
        List<ExchangeItem> list = new ArrayList<>();

        if(listFromResponse == null){
            return Collections.EMPTY_LIST;

        }

        for(ExchangeItem item : listFromResponse){
            if(StringUtils.containsIgnoreCase(item.getBaseCurrency(), currency)){
                list.add(item);
            }
        }

        return list;

    }

    public static String getExchangeItemNameBasedOnAcronym(String acronym){
        for (BaseCurrency bCurrency : BaseCurrency.LYNX.getDeclaringClass().getEnumConstants()) {
            if(StringUtils.containsIgnoreCase(bCurrency.getId(), acronym)){
                return bCurrency.getName();
            }
        }

        return null;
    }


}
