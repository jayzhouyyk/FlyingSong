package com.cn.flyingsong.download.vkey;

import java.util.List;

/**
 * desc:
 * date: 2018/3/28
 *
 * @author: YangYuKun 04118
 */

public class Vkey {

    /**
     * code : 0
     * cid : 205361747
     * userip : 58.248.225.5
     * data : {"expiration":80400,"items":[{"subcode":0,"songmid":"000YU69H3N55rZ","filename":"C400000YU69H3N55rZ.m4a","vkey":"6E1B1D46A5A11DEAAA85BD2FABE5B82E2FE510D31D11E21CDD895C0B8596C36B82462E809B0EAF35ED15D8F24A5E4036EFA07169906B9FB9"}]}
     */

    private int code;
    private int cid;
    private String userip;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * expiration : 80400
         * items : [{"subcode":0,"songmid":"000YU69H3N55rZ","filename":"C400000YU69H3N55rZ.m4a","vkey":"6E1B1D46A5A11DEAAA85BD2FABE5B82E2FE510D31D11E21CDD895C0B8596C36B82462E809B0EAF35ED15D8F24A5E4036EFA07169906B9FB9"}]
         */

        private int expiration;
        private List<ItemsBean> items;

        public int getExpiration() {
            return expiration;
        }

        public void setExpiration(int expiration) {
            this.expiration = expiration;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * subcode : 0
             * songmid : 000YU69H3N55rZ
             * filename : C400000YU69H3N55rZ.m4a
             * vkey : 6E1B1D46A5A11DEAAA85BD2FABE5B82E2FE510D31D11E21CDD895C0B8596C36B82462E809B0EAF35ED15D8F24A5E4036EFA07169906B9FB9
             */

            private int subcode;
            private String songmid;
            private String filename;
            private String vkey;

            public int getSubcode() {
                return subcode;
            }

            public void setSubcode(int subcode) {
                this.subcode = subcode;
            }

            public String getSongmid() {
                return songmid;
            }

            public void setSongmid(String songmid) {
                this.songmid = songmid;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getVkey() {
                return vkey;
            }

            public void setVkey(String vkey) {
                this.vkey = vkey;
            }
        }
    }
}
