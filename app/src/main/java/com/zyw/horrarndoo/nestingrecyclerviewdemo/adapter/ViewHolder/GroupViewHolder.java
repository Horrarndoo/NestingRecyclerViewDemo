package com.zyw.horrarndoo.nestingrecyclerviewdemo.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyw.horrarndoo.nestingrecyclerviewdemo.R;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.GroupItemBean;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.utils.ToastUtils;

/**
 * Created by Horrarndoo on 2017/11/24.
 * <p>
 */

public class GroupViewHolder extends RecyclerView.ViewHolder {
    private GroupItemBean bean;
    public TextView tvGroup, tvSub1, tvSub2, tvSub3;
    public CheckBox cbGroup;
    public LinearLayout llGroup, subEdit;

    public GroupViewHolder(View itemView) {
        super(itemView);
        tvGroup = (TextView) itemView.findViewById(R.id.tv_group);
        cbGroup = (CheckBox) itemView.findViewById(R.id.cb_group);
        llGroup = (LinearLayout) itemView.findViewById(R.id.ll_group);
        subEdit = (LinearLayout) itemView.findViewById(R.id.sub_edit);
        tvSub1 = (TextView) itemView.findViewById(R.id.tv_sub_1);
        tvSub2 = (TextView) itemView.findViewById(R.id.tv_sub_2);
        tvSub3 = (TextView) itemView.findViewById(R.id.tv_sub_3);

        llGroup.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                subEdit.setVisibility(View.VISIBLE);
                return true;
            }
        });

        llGroup.setOnClickListener(new OnClickListener());
        tvSub1.setOnClickListener(new OnClickListener());
        tvSub2.setOnClickListener(new OnClickListener());
        tvSub3.setOnClickListener(new OnClickListener());
    }

    /**
     * 绑定item数据
     * @param bean item数据
     */
    public void bindData(GroupItemBean bean){
        this.bean = bean;
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            subEdit.setVisibility(View.GONE);
            switch (v.getId()) {
                case R.id.ll_group:
                    ToastUtils.showToast(bean.getTitle() + " is clicked.");
                    break;
                case R.id.tv_sub_1:
                    ToastUtils.showToast(bean.getTitle() + " subItem 1 is clicked.");
                    break;
                case R.id.tv_sub_2:
                    ToastUtils.showToast(bean.getTitle() + " subItem 2 is clicked.");
                    break;
                case R.id.tv_sub_3:
                    ToastUtils.showToast(bean.getTitle() + " subItem 3 is clicked.");
                    break;
            }
        }
    }
}
