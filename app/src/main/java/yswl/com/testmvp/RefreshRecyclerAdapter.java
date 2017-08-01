package yswl.com.testmvp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;

import java.util.List;

import yswl.com.testmvp.base.BaseRecyclerAdapter;
import yswl.com.testmvp.base.BaseRecyclerHolder;
import yswl.com.testmvp.bean.BannerBean;

/**
 * Created by kangpAdministrator on 2017/5/3 0003.
 * Emial kangpeng@yunhetong.net
 */

public class RefreshRecyclerAdapter extends BaseRecyclerAdapter<BannerBean> {
    private static final String TAG = "RefreshRecyclerAdapter";
    private Context context;


    public RefreshRecyclerAdapter(Context context, List<BannerBean> data) {
        super(context, data);
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType position:" + position);
//        if (position == 0) {
//            return TYPE_TOP;
//        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder viewType:" + viewType);
        View view = null;
//        if (viewType == TYPE_TOP) {
//            view = getInflater().inflate(R.layout.recycle_item_top, parent, false);
//            return BaseRecyclerHolder.getRecyclerHolder(context, view);
//        }
        return super.onCreateViewHolder(parent, viewType);
    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        if (getItemViewType(position) == TYPE_NORMAL) {
//            holder.name.setText(lis.get(position - 1).title);
//            Glide.with(context).load(mTitles.get(position - 1).bannerUrl).into(holder.img);
//        } else {
//            BannerUtil banner = new BannerUtil();
//            banner.setConvenientBanner(holder.convenientBanner);
//            holder.convenientBanner.startTurning(5000);
//            banner.loadPic(mTitles);
//        }
//    }


    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder position:" + position);
//        if (getItemViewType(position) == TYPE_TOP) {
//            BannerUtil banner = new BannerUtil();
//            banner.setConvenientBanner((ConvenientBanner) holder.findView(R.id.convenientBanner));
//            banner.setLocalPic(getList());
//            ((ConvenientBanner) holder.findView(R.id.convenientBanner)).startTurning(5000);
//        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, int position) {
        Log.e(TAG, "convert position:" + position);
        holder.setText(R.id.name, getList().get(position).title);
        holder.setImageByUrl(R.id.image, getList().get(position).bannerUrl);
    }

}
