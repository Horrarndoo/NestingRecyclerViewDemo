package com.zyw.horrarndoo.nestingrecyclerviewdemo.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyw.horrarndoo.nestingrecyclerviewdemo.R;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.NormalItemBean;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.utils.ToastUtils;

/**
 * Created by Horrarndoo on 2017/11/24.
 * <p>
 */

public class NormalViewHolder extends RecyclerView.ViewHolder {
    private NormalItemBean bean;
    public TextView tvNormal;
    public LinearLayout llNormal;
    public CheckBox cbNormal;

    public NormalViewHolder(View itemView) {
        super(itemView);
        tvNormal = (TextView) itemView.findViewById(R.id.tv_normal);
        llNormal = (LinearLayout) itemView.findViewById(R.id.ll_normal);
        cbNormal = (CheckBox) itemView.findViewById(R.id.cb_normal);
        llNormal.setOnClickListener(new OnClickListener());
    }

    /**
     * 绑定item数据
     * @param bean item数据
     */
    public void bindData(NormalItemBean bean){
        this.bean = bean;
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_normal:
                    ToastUtils.showToast(bean.getTitle() + " is clicked.");
                    break;
            }
        }
    }
}