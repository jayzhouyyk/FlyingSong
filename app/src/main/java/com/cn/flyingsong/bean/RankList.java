package com.cn.flyingsong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * desc:
 * date: 2018/4/3
 *
 * @author: YangYuKun 04118
 */

public class RankList implements Serializable{


    private List<ListBean> List;

    public List<ListBean> getList() {
        return List;
    }

    public void setList(List<ListBean> List) {
        this.List = List;
    }

    public static class ListBean implements Serializable{

        private String ListName;
        private String pic;
        private List<SonglistBean> songlist;
        private int topID;
        private String update_key;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdate_key() {
            return update_key;
        }

        public void setUpdate_key(String update_key) {
            this.update_key = update_key;
        }

        public int getTopID() {
            return topID;
        }

        public void setTopID(int topID) {
            this.topID = topID;
        }

        public String getListName() {
            return ListName;
        }

        public void setListName(String ListName) {
            this.ListName = ListName;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public List<SonglistBean> getSonglist() {
            return songlist;
        }

        public void setSonglist(List<SonglistBean> songlist) {
            this.songlist = songlist;
        }

        public static class SonglistBean implements Serializable {

            private String singername;
            private String songname;

            public String getSingername() {
                return singername;
            }

            public void setSingername(String singername) {
                this.singername = singername;
            }

            public String getSongname() {
                return songname;
            }

            public void setSongname(String songname) {
                this.songname = songname;
            }
        }
    }
}
