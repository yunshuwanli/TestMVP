package yswl.com.testmvp.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 没有数据时的显示
 */
public class MRecyclerView extends RecyclerView {
    private View emptyView;

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public MRecyclerView(Context context) {
        super(context);

    }

    public MRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyAdapterDataObserver);
        }
        emptyAdapterDataObserver.onChanged();
    }

    private AdapterDataObserver emptyAdapterDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter != null && emptyView != null) {
                if (adapter.getItemCount() == 0) {
                    emptyView.setVisibility(View.VISIBLE);
                    setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    setVisibility(View.VISIBLE);
                }
            }

        }
    };

}
