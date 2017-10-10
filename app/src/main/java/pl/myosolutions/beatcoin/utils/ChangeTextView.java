package pl.myosolutions.beatcoin.utils;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import pl.myosolutions.beatcoin.R;

import static pl.myosolutions.beatcoin.R.color.green;

/**
 * Created by Jacek on 2017-10-10.
 */

public class ChangeTextView extends AppCompatTextView {

    private Context mContext;
    private Paint mPaint = new Paint();

    public ChangeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        int px = width / 2;
        int py = height / 2;

        String changeText = String.valueOf(getText()).replace("%", "");
        double changeValue = Double.parseDouble(changeText);

        mPaint.setTextSize(ConvertUtils.convertSpToPx(16f, mContext));
        mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
                changeValue >= 0 ?  R.drawable.ic_arrow_upward_black_12dp : R.drawable.ic_arrow_downward_black_12dp), 0 , py/2, mPaint );

        setTextColor( changeValue >= 0 ? ContextCompat.getColor(mContext, green) : ContextCompat.getColor(mContext, R.color.red));

    }

}
