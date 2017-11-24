package com.zyw.horrarndoo.nestingrecyclerviewdemo.bean;

/**
 * Created by Horrarndoo on 2017/11/22.
 * <p>
 */

public class ChildItemBean {
    private String title;
    private boolean isChecked;
    private int groupId;
    private int itemId;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
