package com.zyw.horrarndoo.nestingrecyclerviewdemo.adapter;

import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.DemoItemBean;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/11/24.
 * <p>
 */

public interface OnCheckChangeListener {
    void onCheckedChanged(List<DemoItemBean>beans, int position, boolean isChecked, int itemType);
}
