package pl.myosolutions.coinbe.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import pl.myosolutions.coinbe.model.BaseCurrency;

import static pl.myosolutions.coinbe.workflow.main.list.IExchangeValues.Currencies.BTC;

/**
 * Created by Jacek on 2017-10-05.
 */

public class DigitsUtils {

    private static final String TAG = DigitsUtils.class.getSimpleName();

    public static final String PATTERN_DECIMAL_2 = "0.00";
    public static final String PATTERN_DECIMAL_6 = "0.000000";
    public static final String PATTERN_DECIMAL_8 = "0.00000000";

    public static String formatWithDelimiters(String strValue, String pattern){
        if(StringUtils.isEmpty(strValue)){
            return StringUtils.Blank;
        }


        double value = DigitsUtils.parseStringToDouble(strValue);

        return formatWithDelimiters(value, pattern);
    }


    public static String formatWithDelimiters(double doubleValue, String pattern){
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat formatter = (DecimalFormat) nf;
        formatter.applyPattern(pattern);

        return formatter.format(doubleValue);
    }



    public static double parseStringToDouble(String value){
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Number number = null;
        try {
            number = format.parse(value);

        } catch (ParseException e) {
            e.printStackTrace();
            number = Double.NaN;
        }
        return number.doubleValue();
    }


    public static String displayPrice(String value, String baseCurrency, String conversionCurrency){

        StringBuilder builder = new StringBuilder();

        builder.append(displayValue(value, baseCurrency, conversionCurrency));

        builder.append(StringUtils.Space);

        builder.append(conversionCurrency);

        return builder.toString();
    }

    public static String displayValue(String value, String baseCurrency, String conversionCurrency){

        switch (conversionCurrency){
            case BTC:
                return formatWithDelimiters(value, PATTERN_DECIMAL_8);
            default:

                if(StringUtils.containsIgnoreCase(baseCurrency, BaseCurrency.DOGE.getId())){
                   return formatWithDelimiters(value, PATTERN_DECIMAL_6);
                }

                if(StringUtils.containsIgnoreCase(baseCurrency, BaseCurrency.BCN.getId()) ||
                        StringUtils.containsIgnoreCase(baseCurrency, BaseCurrency.XEC.getId()) ||
                        StringUtils.containsIgnoreCase(baseCurrency, BaseCurrency.XEM.getId()) ){
                    return formatWithDelimiters(value, PATTERN_DECIMAL_8);
                }

                return formatWithDelimiters(value, PATTERN_DECIMAL_2);
        }

    }

    public static String displayVolume(String value){
        return formatWithDelimiters(value, PATTERN_DECIMAL_8);
    }

}
