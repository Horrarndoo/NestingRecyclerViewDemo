package com.zyw.horrarndoo.nestingrecyclerviewdemo.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyw.horrarndoo.nestingrecyclerviewdemo.R;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.DemoItemBean;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.utils.ToastUtils;

/**
 * Created by Horrarndoo on 2017/11/24.
 * <p>
 */

public class ChildViewHolder extends RecyclerView.ViewHolder {
    private DemoItemBean bean;
    public TextView tvChild;
    public CheckBox cbChild;
    public LinearLayout llChild;

    public ChildViewHolder(View itemView) {
        super(itemView);

        tvChild = (TextView) itemView.findViewById(R.id.tv_child);
        cbChild = (CheckBox) itemView.findViewById(R.id.cb_child);
        llChild = (LinearLayout) itemView.findViewById(R.id.ll_child);

        llChild.setOnClickListener(new OnClickListener());
    }

    /**
     * 绑定item数据
     *
     * @param bean item数据
     */
    public void bindData(DemoItemBean bean) {
        this.bean = bean;
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_child:
                    ToastUtils.showToast(bean.getTitle() + " is clicked.");
                    break;
            }
        }
    }
}