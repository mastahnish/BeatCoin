package pl.myosolutions.beatcoin.workflow.main.list;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * Created by Jacek on 2017-10-09.
 */

public class BindingAdapters {

    @BindingAdapter("app:srcVector")
    public static void setSrcVector(ImageView view, @DrawableRes int drawable) {
        if(drawable==-1) {
            return;
        }

        view.setImageResource(drawable);
    }

}
