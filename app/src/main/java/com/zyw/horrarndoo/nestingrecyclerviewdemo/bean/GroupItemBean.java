package com.zyw.horrarndoo.nestingrecyclerviewdemo.bean;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/11/22.
 * <p>
 */

public class GroupItemBean {
    private String title;
    private boolean isChecked;
    private int itemId;
    private List<ChildItemBean> childs;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public List<ChildItemBean> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildItemBean> childs) {
        this.childs = childs;
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
