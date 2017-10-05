package pl.myosolutions.beatcoin.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;

public class StringUtils {
    private StringUtils() {
    }

    public static String Blank = "";
    private static String Underline ="_";
    public static String Not_applicable = "n/a";

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
}
