package com.itheima.googleplaydemo.bean;

import java.util.List;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/25 22:11
 * 描述： TODO
 */
public class CategoryBean {


    /**
     * title : 游戏
     * infos : [{"url1":"image/category_game_0.jpg","url2":"image/category_game_1.jpg","url3":"image/category_game_2.jpg","name1":"休闲","name2":"棋牌","name3":"益智"},{"url1":"image/category_game_3.jpg","url2":"image/category_game_4.jpg","url3":"image/category_game_5.jpg","name1":"射击","name2":"体育","name3":"儿童"},{"url1":"image/category_game_6.jpg","url2":"image/category_game_7.jpg","url3":"image/category_game_8.jpg","name1":"网游","name2":"角色","name3":"策略"},{"url1":"image/category_game_9.jpg","url2":"image/category_game_10.jpg","url3":"","name1":"经营","name2":"竞速","name3":""}]
     */

    private String title;
    /**
     * url1 : image/category_game_0.jpg
     * url2 : image/category_game_1.jpg
     * url3 : image/category_game_2.jpg
     * name1 : 休闲
     * name2 : 棋牌
     * name3 : 益智
     */

    private List<InfosBean> infos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InfosBean> getInfos() {
        return infos;
    }

    public void setInfos(List<InfosBean> infos) {
        this.infos = infos;
    }

    public static class InfosBean {
        private String url1;
        private String url2;
        private String url3;
        private String name1;
        private String name2;
        private String name3;

        public String getUrl1() {
            return url1;
        }

        public void setUrl1(String url1) {
            this.url1 = url1;
        }

        public String getUrl2() {
            return url2;
        }

        public void setUrl2(String url2) {
            this.url2 = url2;
        }

        public String getUrl3() {
            return url3;
        }

        public void setUrl3(String url3) {
            this.url3 = url3;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public String getName3() {
            return name3;
        }

        public void setName3(String name3) {
            this.name3 = name3;
        }
    }
}
