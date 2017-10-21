package pl.myosolutions.beatcoin.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Jacek on 2017-10-05.
 */

public class DigitsUtils {

    private static final String TAG = DigitsUtils.class.getSimpleName();

    private static final String PATTERN_DECIMAL = "0.00";

    public static String formatWithDelimiters(String strValue){
        if(StringUtils.isEmpty(strValue)){
            return StringUtils.Blank;
        }


        double value = DigitsUtils.parseStringToDouble(strValue);

        return formatWithDelimiters(value);
    }


    public static String formatWithDelimiters(double doubleValue){
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat formatter = (DecimalFormat) nf;
        formatter.applyPattern(PATTERN_DECIMAL);

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


}
