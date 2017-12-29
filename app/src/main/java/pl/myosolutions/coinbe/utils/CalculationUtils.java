package pl.myosolutions.coinbe.utils;

import static pl.myosolutions.coinbe.utils.DigitsUtils.PATTERN_DECIMAL_2;

/**
 * Created by Jacek on 2017-10-05.
 */

public class CalculationUtils {

    private static final String TAG = CalculationUtils.class.getSimpleName();

    private static final String TWO_DIGITS = "%.2f";

    public static String calculateSpread(String bid, String ask){
        if(StringUtils.isEmpty(bid) || StringUtils.isEmpty(ask)){
            return StringUtils.Blank;
        }


        double bidDouble = DigitsUtils.parseStringToDouble(bid);
        double askDouble = DigitsUtils.parseStringToDouble(ask);
        return String.valueOf(Math.abs(bidDouble-askDouble));
    }

   public static String calculateChange(String last, String beforelast){
       if(StringUtils.isEmpty(last) || StringUtils.isEmpty(beforelast)){
           return StringUtils.Blank;
       }

       double lastDouble = DigitsUtils.parseStringToDouble(last);
       double beforelastDouble = DigitsUtils.parseStringToDouble(beforelast);

       double change =
               (lastDouble == 0 && beforelastDouble == 0)
                       || beforelastDouble == 0 ?
                       0 : ((lastDouble - beforelastDouble)/beforelastDouble)*100;

       StringBuilder builder = new StringBuilder();
       builder.append(DigitsUtils.formatWithDelimiters(change, PATTERN_DECIMAL_2));
       builder.append(StringUtils.Percent);
       String result = builder.toString();

       return result;
   }
}
