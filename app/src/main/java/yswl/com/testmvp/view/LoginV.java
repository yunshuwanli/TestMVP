package yswl.com.testmvp.view;

import android.content.Context;

/**
 * Created by kangpAdministrator on 2017/4/27 0027.
 * Emial kangpeng@yunhetong.net
 */

public interface LoginV {
    String getName();

    Context getContext();

    void hideKeybord();

    String getPwd();

    void setProgress(boolean boo);

    void next();
}
