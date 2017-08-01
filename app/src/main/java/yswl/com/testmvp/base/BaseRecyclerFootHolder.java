package yswl.com.testmvp.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 */
public class BaseRecyclerFootHolder extends RecyclerView.ViewHolder {


    ProgressBar progressBar;
    TextView textView;

    BaseRecyclerFootHolder(View itemView) {
        super(itemView);
    }


}