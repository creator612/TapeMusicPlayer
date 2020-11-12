package com.example.musicplayer;

public class DrawableFiles {
    private String fileName;
    private int resourseID;

    public DrawableFiles(String fileName, int resourseID) {
        this.fileName = fileName;
        this.resourseID = resourseID;
    }

    public DrawableFiles(){

    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public int getResourseID() {
        return resourseID;
    }

    public void setResourseID(int resourseID) {
        this.resourseID = resourseID;
    }


}
