package com.itheima.googleplaydemo.bean;

import java.util.List;

/**
 * Created by Leon on 2017/1/3.
 */

public class AppDetailBean {

    /**
     * id : 1631363
     * name : 黑马程序员
     * packageName : com.itheima.www
     * iconUrl : app/com.itheima.www/icon.jpg
     * stars : 5.0
     * downloadNum : 40万+
     * version : 1.1.0605.0
     * date : 2015-06-10
     * size : 91767
     * downloadUrl : app/com.itheima.www/com.itheima.www.apk
     * des : 黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员黑马程序员
     * author : 黑马程序员
     * screen : ["app/com.itheima.www/screen0.jpg","app/com.itheima.www/screen1.jpg","app/com.itheima.www/screen2.jpg","app/com.itheima.www/screen3.jpg","app/com.itheima.www/screen4.jpg"]
     * safe : [{"safeUrl":"app/com.itheima.www/safeIcon0.jpg","safeDesUrl":"app/com.itheima.www/safeDesUrl0.jpg","safeDes":"已通过安智市场安全检测，请放心使用","safeDesColor":0},{"safeUrl":"app/com.itheima.www/safeIcon1.jpg","safeDesUrl":"app/com.itheima.www/safeDesUrl1.jpg","safeDes":"无任何形式的广告","safeDesColor":0}]
     */

    private int id;
    private String name;
    private String packageName;
    private String iconUrl;
    private double stars;
    private String downloadNum;
    private String version;
    private String date;
    private int size;
    private String downloadUrl;
    private String des;
    private String author;
    private List<String> screen;
    /**
     * safeUrl : app/com.itheima.www/safeIcon0.jpg
     * safeDesUrl : app/com.itheima.www/safeDesUrl0.jpg
     * safeDes : 已通过安智市场安全检测，请放心使用
     * safeDesColor : 0
     */

    private List<SafeBean> safe;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public String getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(String downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getScreen() {
        return screen;
    }

    public void setScreen(List<String> screen) {
        this.screen = screen;
    }

    public List<SafeBean> getSafe() {
        return safe;
    }

    public void setSafe(List<SafeBean> safe) {
        this.safe = safe;
    }

    public static class SafeBean {
        private String safeUrl;
        private String safeDesUrl;
        private String safeDes;
        private int safeDesColor;

        public String getSafeUrl() {
            return safeUrl;
        }

        public void setSafeUrl(String safeUrl) {
            this.safeUrl = safeUrl;
        }

        public String getSafeDesUrl() {
            return safeDesUrl;
        }

        public void setSafeDesUrl(String safeDesUrl) {
            this.safeDesUrl = safeDesUrl;
        }

        public String getSafeDes() {
            return safeDes;
        }

        public void setSafeDes(String safeDes) {
            this.safeDes = safeDes;
        }

        public int getSafeDesColor() {
            return safeDesColor;
        }

        public void setSafeDesColor(int safeDesColor) {
            this.safeDesColor = safeDesColor;
        }
    }
}
