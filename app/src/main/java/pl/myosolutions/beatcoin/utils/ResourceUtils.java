package pl.myosolutions.beatcoin.utils;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by Jacek on 2017-10-09.
 */

public class ResourceUtils {

    private static final String TAG = ResourceUtils.class.getSimpleName();

    public static int getResIdFromString(String resName, Class<?> c){
        Log.d(TAG, "resName: " + resName);
            try {
                Field idField = c.getField(resName);
                return idField.getInt(idField);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG,  "Failure to get drawable id.", e);
                return -1;
            }
    }
}
