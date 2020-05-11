package com.example.myownbusiness;

public class Model {

    private String title, desc;
    private int img;

    //SETTER -------------------------------------------------------------------------------------->
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImg(int img) {
        this.img = img;
    }

    //GETTER -------------------------------------------------------------------------------------->
    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getImg() {
        return img;
    }
}
