package com.cn.flyingsong.bean;

/**
 * desc:
 * date: 2018/4/11
 *
 * @author: YangYuKun 04118
 */

public class FlySong implements Cloneable {
    SongType mSongType;
    String songName;
    String singgerName;
    int id;
    int downloadId;
    String mediaMid;
    String songNameWithSuffix;

    private int size128;
    private int size320;
    private int sizeape;

    public int getSize128() {
        return size128;
    }

    public void setSize128(int size128) {
        this.size128 = size128;
    }

    public int getSize320() {
        return size320;
    }

    public void setSize320(int size320) {
        this.size320 = size320;
    }

    public int getSizeape() {
        return sizeape;
    }

    public void setSizeape(int sizeape) {
        this.sizeape = sizeape;
    }

    public int getSizeflac() {
        return sizeflac;
    }

    public void setSizeflac(int sizeflac) {
        this.sizeflac = sizeflac;
    }

    private int sizeflac;

    public FlySong(TopList.SonglistBean bean) {
        songName = bean.getData().getSongname();
        singgerName = bean.getData().getSinger().get(0).getName();
        id = bean.getData().getSongid();
        mediaMid = bean.getData().getStrMediaMid();
        setSize128(bean.getData().getSize128());
        setSize320(bean.getData().getSize320());
        setSizeape(bean.getData().getSizeape());
        setSizeflac(bean.getData().getSizeflac());
    }

    public FlySong(SearchResult.DataBean.SongBean.ListBean bean) {
        songName = bean.getName();
        singgerName = bean.getSinger().get(0).getName();
        id = bean.getId();
        mediaMid = bean.getMid();
        setSize128(bean.getFile().getSize_128());
        setSize320(bean.getFile().getSize_320());
        setSizeape(bean.getFile().getSize_ape());
        setSizeflac(bean.getFile().getSize_flac());
    }


    public FlySong getClone( SongType songType) {
        FlySong flySong1 = null;
        try {
            flySong1 = (FlySong) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        flySong1.setSongType(songType);
        return flySong1;
    }

    public String getSongNameWithSuffix() {
        return songName + mSongType.getSuffix();
    }

    public int getDownloadId() {
        return id << 2 + SongType.getId(getSongType());
    }

    public SongType getSongType() {
        return mSongType;
    }

    public void setSongType(SongType songType) {
        mSongType = songType;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinggerName() {
        return singgerName;
    }

    public void setSinggerName(String singgerName) {
        this.singgerName = singgerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMediaMid() {
        return mediaMid;
    }

    public void setMediaMid(String mediaMid) {
        this.mediaMid = mediaMid;
    }

}
