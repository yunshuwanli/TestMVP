package yswl.com.klibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class MFragment extends BaseFragment {
    public static String TAG = "MFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
    }
}
