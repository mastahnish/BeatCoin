package pl.myosolutions.beatcoin.utils;

/**
 * Created by Jacek on 2017-10-05.
 */

public class CalculationUtils {


    public static String calculateSpread(String bid, String ask){
        if(StringUtils.isEmpty(bid) || StringUtils.isEmpty(ask)){
            return StringUtils.Blank;
        }


        double bidDouble = Double.parseDouble(bid);
        double askDouble = Double.parseDouble(ask);
        return String.valueOf(Math.abs(bidDouble-askDouble));
    }

}
