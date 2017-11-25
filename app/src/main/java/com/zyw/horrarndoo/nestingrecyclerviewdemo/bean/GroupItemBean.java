package com.zyw.horrarndoo.nestingrecyclerviewdemo.bean;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/11/22.
 * <p>
 */

public class GroupItemBean extends DemoItemBean{
    private List<ChildItemBean> childs;

    public List<ChildItemBean> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildItemBean> childs) {
        this.childs = childs;
    }
}
