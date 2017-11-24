package com.zyw.horrarndoo.nestingrecyclerviewdemo.bean;

/**
 * Created by Horrarndoo on 2017/11/22.
 * <p>
 */

public class DemoItemBean {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_GROUP = 1;
    public static final int TYPE_CHILD = 2;

    private String title;
    private boolean isChecked;
    private int itemType;
    private int itemId;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

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

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
