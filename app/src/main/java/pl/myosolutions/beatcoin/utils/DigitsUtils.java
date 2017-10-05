package pl.myosolutions.beatcoin.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Jacek on 2017-10-05.
 */

public class DigitsUtils {

    public static String formatWithDelimiters(String strValue){
        if(StringUtils.isEmpty(strValue)){
            return StringUtils.Blank;
        }


        double value = Double.parseDouble(strValue);

        return formatWithDelimiters(value);
    }


    public static String formatWithDelimiters(double doubleValue){
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat formatter = (DecimalFormat) nf;
        formatter.applyPattern("#.##");

        return formatter.format(doubleValue);
    }



}
