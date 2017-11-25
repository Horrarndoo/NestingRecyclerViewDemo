package com.zyw.horrarndoo.nestingrecyclerviewdemo.bean;

/**
 * Created by Horrarndoo on 2017/11/22.
 * <p>
 */

public class NormalItemBean {
    private String title;
    private int itemId;
    private boolean isChecked;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
