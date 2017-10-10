package pl.myosolutions.beatcoin.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/**
 Created by gyatsina on 6/22/2017.
 */

public class ConvertUtils {

    private ConvertUtils() {
    }

//    public static int getAttrColor(int resId, Resources.Theme theme) {
//        TypedValue typedValue = new TypedValue();
//        theme.resolveAttribute(resId, typedValue, true);
//        return typedValue.data;
//    }

    public static int convertSpToPx(float sp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }


    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
