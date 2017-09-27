package yswl.com.testmvp.bean;

import java.util.ArrayList;
import java.util.List;

import yswl.com.testmvp.refresh.Character;

/**
 * Created by kangpAdministrator on 2017/6/8 0008.
 * Emial kangpeng@yunhetong.net
 */

public class Pic {
    /**
     * desc : 9.29
     * ganhuo_id : 56cc6d1d421aa95caa7078dc
     * publishedAt : 2015-09-29T04:15:26.443000
     * readability :
     * type : 福利
     * url : http://ww3.sinaimg.cn/large/7a8aed7bgw1ewgtohwi0qj20gy0pbwhi.jpg
     * who : 张涵宇
     */

    private String desc;
    private String ganhuo_id;
    private String publishedAt;
    private String readability;
    private String type;
    private String url;
    private String who;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGanhuo_id() {
        return ganhuo_id;
    }

    public void setGanhuo_id(String ganhuo_id) {
        this.ganhuo_id = ganhuo_id;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getReadability() {
        return readability;
    }

    public void setReadability(String readability) {
        this.readability = readability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }


    public static List<Character> p2c(List<Pic> pics) {
        List<Character> lisc = new ArrayList<>();
        for (int i = 0; i < pics.size(); i++) {
            Character c = new Character();
            c.setName(pics.get(i).getWho());
            c.setAvatar(pics.get(i).getUrl());
            lisc.add(c);
        }
        return lisc;
    }
}
