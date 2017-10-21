package pl.myosolutions.beatcoin.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import pl.myosolutions.beatcoin.R;

import static pl.myosolutions.beatcoin.R.color.green;
import static pl.myosolutions.beatcoin.utils.StringUtils.Blank;

/**
 * Created by Jacek on 2017-10-10.
 */

public class ChangeTextView extends AppCompatTextView {

    private int BUFFER_X = 18;
    private int BUFFER_Y = 5;

    private Context mContext;
    private Paint mPaint = new Paint();
    private float mStringWidth;
    private float mStringHeight;
    private String mString;
    private double mChangeValue = 0d;

    public ChangeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void calculateStringDimensions(){
        Rect bounds = new Rect();
        mPaint.getTextBounds(mString, 0, mString.length(), bounds);
        mStringWidth = bounds.width();
        mStringHeight = bounds.height();
    }

    private void getStringAsDouble(){
        String changeText = mString.replace(StringUtils.Percent, Blank).trim();
        if(!StringUtils.isEmpty(changeText)) mChangeValue = DigitsUtils.parseStringToDouble(changeText);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

     /*   int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        int px = width / 2;
        int py = height / 2;*/

        this.mString = getText().toString();
        getStringAsDouble();
        calculateStringDimensions();

        mPaint.setTextSize(ConvertUtils.convertSpToPx(16f, mContext));
        mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));


        Bitmap indicatorBitmap = DrawableUtils.getBitmapFromVectorDrawable(mContext,  mChangeValue > 0 ?  R.drawable.arrow_upward_svg : mChangeValue == 0 ? R.drawable.equal_svg : R.drawable.arrow_downward_svg);
     /*   int arrowHeight = indicatorBitmap.getHeight();
        int arrowWidth = indicatorBitmap.getWidth();*/

        canvas.drawBitmap(indicatorBitmap
               ,(getWidth() - mStringWidth - BUFFER_X), ((getHeight()/2)-((mStringHeight/2) + BUFFER_Y)), mPaint );

        setTextColor(mChangeValue > 0 ? ContextCompat.getColor(mContext, green) : mChangeValue == 0 ? ContextCompat.getColor(mContext, R.color.colorPrimary) : ContextCompat.getColor(mContext, R.color.red));

    }

}
