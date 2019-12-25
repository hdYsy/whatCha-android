package com.lianjie.andorid.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.lianjie.andorid.rnui.RNApplication;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Android Studio.
 * User: XYGG
 * Date: 2019/6/12
 * Time: 15:23
 */
public class KeyBarUtils {
    static KeyBarUtils instance;

    private KeyBarUtils() {
        // construct
    }

    public static KeyBarUtils getInstance() {
        if (instance == null) {
            instance = new KeyBarUtils();
        }
        return instance;
    }

    /**
     * 隐藏软键盘
     * @param editText
     */
    public void hideKeyBoard(EditText editText){
        InputMethodManager imm=
                (InputMethodManager)
                        RNApplication.myApp
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }

    /**
     * 弹起软键盘
     * @param editText
     */
    public void openKeyBoard(final EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm=
                        (InputMethodManager)
                                RNApplication.myApp
                                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText,0);
                editText.setSelection(editText.getText().length());
            }
        },200);


    }
}
