package yswl.com.testmvp.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class BannerBean {

    public String bannerUrl;
    public String title;

    public BannerBean(String url, String d) {
        this.bannerUrl = url;
        title = d;
    }

    public static String[] urls = {"http://ww1.sinaimg.cn/large/61e74233ly1feuogwvg27j20p00zkqe7.jpg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/2017-05-02-926821_1453024764952889_775781470_n.jpg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-28-18094719_120129648541065_8356500748640452608_n.jpg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-27-17934080_117414798808566_8957027985114791936_n.jpg?imageslim",
            "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-25-13651793_897557617014845_571817176_n.jpg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-24-18013547_1532023163498554_215541963087151104_n.jpg",
            "http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-24-18094714_158946097967074_5909424912493182976_n.jpg"
    };

    public static List<BannerBean> getData() {
        List<BannerBean> result = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            result.add(new BannerBean(urls[i], "菜鸟" + i));
        }
        return result;
    }
}
