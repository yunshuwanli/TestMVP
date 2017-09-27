package yswl.com.testmvp.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import yswl.com.testmvp.R;


 public class CommonEmptyView extends FrameLayout implements
        View.OnClickListener {

    public CommonEmptyView(Context context) {
        super(context);
        init(context);
    }

    public CommonEmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommonEmptyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    TextView noticeTV;
    ImageView noticeIM;

    private void init(Context context) {
        View ui = LayoutInflater.from(context).inflate(
                R.layout.common_list_emptyview, null);
        noticeTV = (TextView) ui.findViewById(R.id.common_empty_view_notice);
        noticeIM = (ImageView) ui.findViewById(R.id.common_empty_view_icon);
        this.addView(ui);
        this.setOnClickListener(this);
    }

    public CommonEmptyView setMNoticeAndIcon(int notice, int icon) {
        noticeTV.setText(notice);
        if (-1 != icon)
            noticeIM.setImageResource(icon);
        return this;
    }

    public static CommonEmptyView setCommonEmptyView(Context context,
                                                     AbsListView listview, EmptyViewOnClickLister lister) {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        CommonEmptyView ev = new CommonEmptyView(context);
        ev.setmEmptyViewOnClickLister(lister);
        ((ViewGroup) listview.getParent()).addView(ev, lp);
        listview.setEmptyView(ev);
        return ev;
    }

    public static CommonEmptyView setCommonEmptyView(Context context,
                                                     MRecyclerView rcView, EmptyViewOnClickLister lister) {
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        CommonEmptyView ev = new CommonEmptyView(context);
        ev.setmEmptyViewOnClickLister(lister);
        ((ViewGroup) rcView.getParent()).addView(ev, lp);
        rcView.setEmptyView(ev);
        return ev;
    }

    private EmptyViewOnClickLister mEmptyViewOnClickLister;

    public void setmEmptyViewOnClickLister(
            EmptyViewOnClickLister mEmptyViewOnClickLister) {
        this.mEmptyViewOnClickLister = mEmptyViewOnClickLister;
    }

    public interface EmptyViewOnClickLister {
        void onEmptyViewClick(View v);
    }

    @Override
    public void onClick(View v) {
        if (mEmptyViewOnClickLister != null) {
            mEmptyViewOnClickLister.onEmptyViewClick(v);
        }
    }
}
