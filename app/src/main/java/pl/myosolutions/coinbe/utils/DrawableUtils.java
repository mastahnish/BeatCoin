package pl.myosolutions.coinbe.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

/**
 * Created by Jacek on 2017-10-09.
 */

public class DrawableUtils {

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static void drawDrawableToImageView(Context context, String coinName, ImageView placeholder){

        TextDrawable d = new TextDrawable(context);
        d.setText(coinName);
        d.setTextColor(Color.BLACK);
        d.setTextSize(32);



   /*     Path p = new Path();
        int origin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, context.getResources().getDisplayMetrics());
        int radius = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        int bound = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, context.getResources().getDisplayMetrics());
        p.addCircle(origin, origin, radius, Path.Direction.CW);

        d.setTextPath(p);
//Must call setBounds() since we are using a Path
        d.setBounds(0, 0, bound, bound);*/

        placeholder.setImageDrawable(d);
    }



}

