package com.friendzone.farmr.utils;

import android.os.Handler;

import com.dd.processbutton.ProcessButton;

/**
 * Created by marylourdessabio on 08/08/15.
 */
public class ProgressGenerator {

    public interface OnCompleteListener {

        public void onComplete(int code);
    }

    private OnCompleteListener mListener;
    private int mProgress;

    public ProgressGenerator(OnCompleteListener listener) {
        mListener = listener;
    }

    public void start(final ProcessButton button, final int code) {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgress += 10;
                button.setProgress(mProgress);
                if (mProgress < 100) {
                    handler.postDelayed(this, 100);
                } else {
                    mListener.onComplete(code);
                }
            }
        }, 100);
    }

}