package yswl.com.testmvp;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;

import yswl.com.testmvp.bean.BannerBean;

/**
 * 本地图片加载
 * 是没有点击事件的
 */
public class LocalImageHolderView implements Holder<BannerBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    /**
     * 加载本地图片
     *
     * @param context
     * @param position
     * @param data
     */
    @Override
    public void UpdateUI(final Context context, final int position, final BannerBean data) {
        if (data != null) {
            if (!data.bannerUrl.startsWith("http")) {
                try {
                    //加载resId
                    Glide.with(context).load(Integer.valueOf(data.bannerUrl)).into(imageView);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else {
                Glide.with(context).load(data.bannerUrl).into(imageView);
            }
        }
    }
}
