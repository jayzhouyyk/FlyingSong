package com.cn.flyingsong.bean;

public enum SongType {
    MP3_128k("M500", ".mp3"),
    MP3_320k("M800", ".mp3"),
    FLAC("F000", ".flac"),
    APE("A000", ".ape");

    private String head;
    private String suffix;

    SongType(String head, String suffix) {
        this.head = head;
        this.suffix = suffix;
    }

    public String getHead() {
        return head;
    }

    public String getSuffix() {
        return suffix;
    }

    public interface Type {
        int MP3_128k = 0;
        int MP3_320k = 1;
        int FLAC = 2;
        int APE = 3;
    }

    public static SongType getType(int typeIn) {
        SongType type = null;
        switch (typeIn) {
            case Type.MP3_128k:
                type = MP3_128k;
                break;
            case Type.MP3_320k:
                type = MP3_320k;
                break;
            case Type.FLAC:
                type = FLAC;
                break;
            case Type.APE:
                type = APE;
                break;
            default:
                type = MP3_128k;
        }
        return type;
    }

    public static int getId(SongType songType) {
        int id = 0;
        switch (SongType.MP3_128k) {
            case MP3_128k:
                id = Type.MP3_128k;
                break;
            case MP3_320k:
                id = Type.MP3_320k;
                break;
            case FLAC:
                id = Type.FLAC;
                break;
            case APE:
                id = Type.APE;
                break;
        }
        return id;

    }

}
