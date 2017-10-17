package pl.myosolutions.beatcoin.utils;

/**
 * Created by Jacek on 2017-10-05.
 */

public class CalculationUtils {

    private static final String TWO_DIGITS = "%.2f";

    public static String calculateSpread(String bid, String ask){
        if(StringUtils.isEmpty(bid) || StringUtils.isEmpty(ask)){
            return StringUtils.Blank;
        }


        double bidDouble = Double.parseDouble(bid);
        double askDouble = Double.parseDouble(ask);
        return String.valueOf(Math.abs(bidDouble-askDouble));
    }

   public static String calculateChange(String last, String beforelast){
       if(StringUtils.isEmpty(last) || StringUtils.isEmpty(beforelast)){
           return StringUtils.Blank;
       }

       double lastDouble = Double.parseDouble(last);
       double beforelastDouble = Double.parseDouble(beforelast);

       double change =
               (lastDouble == 0 && beforelastDouble == 0)
                       || beforelastDouble == 0 ?
                       0 : ((lastDouble - beforelastDouble)/beforelastDouble)*100;
       return String.format(TWO_DIGITS, change) + StringUtils.Percent;
   }
}
