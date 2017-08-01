package yswl.com.testmvp.Util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * �򿪻�ر������s
 *
 * @author nixn@yunhetong.net
 */
public class MKeyBoardUtils {
    /**
     * �������
     *
     * @param mEditText �����
     * @param mContext  ������
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * �ر������
     *
     * @param mEditText �����
     * @param mContext  ������
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }


    public static void hideSoftKeyboard(Activity activity) {
        try {
            View view = activity.getCurrentFocus();
            if (null != view) {
                InputMethodManager inputMethodManager = (InputMethodManager) activity
                        .getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception e) {
        }

    }
}
