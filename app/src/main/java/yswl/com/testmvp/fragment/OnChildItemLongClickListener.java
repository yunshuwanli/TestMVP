package yswl.com.testmvp.fragment;

import android.view.View;

/**
 * Created by aspsine on 16/8/9.
 */

public interface OnChildItemLongClickListener<C> {
    boolean onClickItemLongClick(int groupPosition, int childPosition, C c, View view);
}