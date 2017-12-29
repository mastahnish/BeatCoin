package pl.myosolutions.coinbe.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;

import static pl.myosolutions.coinbe.model.BaseCurrency.WAP;

public class StringUtils {

    private static final String TAG = StringUtils.class.getSimpleName() ;

    private StringUtils() {
    }

    public static String Blank = "";
    private static String Underline ="_";
    public static String Not_applicable = "n/a";
    private static String Svg = "svg";
    public static String Png = "png";
    public static String Percent = "%";
    public static String Space = " ";

    public static boolean isEmpty(String str) {
        return str == null || str.length() <= 0;
    }

    public static String notNull(String str) {
        return str == null ? Blank : str;
    }

    public static boolean containsIgnoreCase(CharSequence searchIn, CharSequence searchFor) {
        if (searchIn == null || searchFor == null) {
            return false;
        }
        return searchIn.toString().toLowerCase().contains(searchFor.toString().toLowerCase());
    }

    public static SpannableString getScaledSpannableString(String input, float scale, int start, int end) {
        SpannableString ss1 = new SpannableString(input);
        ss1.setSpan(new RelativeSizeSpan(scale), start, end, 0);
        return ss1;
    }

    public static SpannableStringBuilder getBoldSpannableString(String text, int start, int end) {
        SpannableStringBuilder str = new SpannableStringBuilder(text);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }

    public static boolean comparePasswords(String pass1, String pass2) {
        return !TextUtils.isEmpty(pass2) && pass1 != null && pass1.equals(pass2);
    }

    public static String getBaseCurrency(String ticker){
        return ticker.split(Underline)[1];
    }

    public static String getConversionCurrency(String ticker){
        return ticker.split(Underline)[0];
    }

    public static String getResourceIdByCurrency(String currency){
        if(isEmpty(currency)){
            return Blank;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(currency.toLowerCase());
        sb.append(Underline);

        if(currency.equals(WAP.getId())){
            sb.append(Png);
            return sb.toString();
        }

        sb.append(Svg);
        return sb.toString();
    }

}
