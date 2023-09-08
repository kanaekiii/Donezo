package com.codsoft.todo;


public class DataClass {

    private String dataTitle;
    private String dataDesc;
    private String dataTime;
    private String key;

    private boolean isChecked;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public String getDataTime() {
        return dataTime;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public DataClass(String dataTitle, String dataDesc, String dataTime) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataTime = dataTime;
    }

    public DataClass(){

    }
}