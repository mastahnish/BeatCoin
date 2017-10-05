package pl.myosolutions.beatcoin.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;

public class ViewUtils {

    public static void setStatusBarColor(final Activity activity, final int color) {
        if(activity == null || color < 0) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(activity, color));
        }
    }

    public static void setDrawableColor(final Context context, final Drawable drawable, final int color) {
        if(context == null || drawable == null || color < 0) {
            return;
        }
        drawable.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP);
    }

    public static int getAttrColor(Context context, int resId) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(resId, typedValue, true);
        return typedValue.data;
    }

}
