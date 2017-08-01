package yswl.com.testmvp;


import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import yswl.com.testmvp.bean.BannerBean;

/**
 * author  : kangpeng on 2016/4/8 0008.
 * email   : kangpeng@yunhetong.net
 */
public class BannerUtil {

    private ConvenientBanner<BannerBean> convenientBanner;//轮播图

    public void setConvenientBanner(ConvenientBanner<BannerBean> convenientBanner) {
        this.convenientBanner = convenientBanner;
    }

    public void setLocalPic(List<BannerBean> localImages) {
        convenientBanner.setPages(new CBViewHolderCreator<LocalImageHolderView>() {
            @Override
            public LocalImageHolderView createHolder() {
                return new LocalImageHolderView();
            }
        }, localImages);
        convenientBanner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
    }

    /**
     * 在act 屏幕唤醒时 onResume运行
     */
    public void startTurning() {
        if (null != convenientBanner)
            convenientBanner.startTurning(5000);
    }

    /**
     * act 不属于前台进程时 停止轮播
     * onPause
     */
    public void stopTurning() {
        if (null != convenientBanner)
            convenientBanner.stopTurning();
    }


}
