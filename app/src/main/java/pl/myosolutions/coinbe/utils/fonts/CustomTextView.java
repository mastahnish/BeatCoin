package pl.myosolutions.coinbe.utils.fonts;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Jacek on 2017-10-18.
 */

public class CustomTextView extends AppCompatTextView {

    public CustomTextView(Context context, AttributeSet attrs) {

        super(context, attrs);
        FontManager.setTypefaceFromAttrs(this, context, attrs);
    }
}