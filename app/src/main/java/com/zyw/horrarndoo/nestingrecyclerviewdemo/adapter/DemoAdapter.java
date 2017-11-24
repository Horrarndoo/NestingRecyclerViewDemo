package com.zyw.horrarndoo.nestingrecyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.zyw.horrarndoo.nestingrecyclerviewdemo.R;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.adapter.ViewHolder.ChildViewHolder;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.adapter.ViewHolder.GroupViewHolder;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.adapter.ViewHolder.NormalViewHolder;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.DemoItemBean;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.helper.ParseHelper;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/11/22.
 * <p>
 */

public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DemoItemBean> mDatas;
    private Context mContext;
    private OnCheckChangeListener onCheckChangeListener;

    public void setOnCheckChangeListener(OnCheckChangeListener l) {
        onCheckChangeListener = l;
    }

    public DemoAdapter(Context context, List<DemoItemBean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.w("tag", "onCreateViewHolder");
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case DemoItemBean.TYPE_NORMAL:
                View v = mInflater.inflate(R.layout.item_normal, parent, false);
                holder = new NormalViewHolder(v);
                break;
            case DemoItemBean.TYPE_GROUP:
                View v1 = mInflater.inflate(R.layout.item_group, parent, false);
                holder = new GroupViewHolder(v1);
                break;
            case DemoItemBean.TYPE_CHILD:
                View v2 = mInflater.inflate(R.layout.item_child, parent, false);
                holder = new ChildViewHolder(v2);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.w("tag", "onBindViewHolder");
        if (holder instanceof NormalViewHolder) {
            NormalViewHolder nHolder = (NormalViewHolder) holder;
            nHolder.bindData(mDatas.get(position));
            nHolder.tvNormal.setText(mDatas.get(position).getTitle());
            nHolder.cbNormal.setOnCheckedChangeListener(new OnCheckedChangeListener(position,
                    DemoItemBean.TYPE_NORMAL));
            nHolder.cbNormal.setChecked(mDatas.get(position).isChecked());
        } else if (holder instanceof GroupViewHolder) {
            GroupViewHolder gHolder = (GroupViewHolder) holder;
            gHolder.bindData(mDatas.get(position));
            gHolder.tvGroup.setText(mDatas.get(position).getTitle());
            gHolder.cbGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(position,
                    DemoItemBean.TYPE_GROUP));
            gHolder.cbGroup.setChecked(mDatas.get(position).isChecked());
        } else if (holder instanceof ChildViewHolder) {
            ChildViewHolder cHolder = (ChildViewHolder) holder;
            cHolder.bindData(mDatas.get(position));
            cHolder.tvChild.setText(mDatas.get(position).getTitle());
            cHolder.cbChild.setOnCheckedChangeListener(new OnCheckedChangeListener(position,
                    DemoItemBean.TYPE_CHILD));
            cHolder.cbChild.setChecked(mDatas.get(position).isChecked());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * CheckBox CheckedChangeListener
     */
    private class OnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
        int mPosition, mItemType;

        public OnCheckedChangeListener(int position, int itemType) {
            mPosition = position;
            mItemType = itemType;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (onCheckChangeListener != null)
                onCheckChangeListener.onCheckedChanged(mDatas, mPosition, isChecked, mItemType);
        }
    }

    /**
     * 删除选中item
     */
    public void removeChecked() {
        int iMax = mDatas.size() - 1;
        //这里要倒序，因为要删除mDatas中的数据，mDatas的长度会变化
        for (int i = iMax; i >= 0; i--) {
            if (mDatas.get(i).isChecked()) {
                mDatas.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(0, mDatas.size());
            }
        }
    }

    /**
     * 添加 Normal item
     */
    public void addNormal() {
        int addPosition = 0;
        int itemId = 0;
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getItemType() == DemoItemBean.TYPE_GROUP) {
                addPosition = i;//得到要插入的position
                break;
            }
        }

        if (!isHaveGroup()) {//如果列表中没有group，直接在list末尾添加item
            if (addPosition == 0) {
                addPosition = mDatas.size();
            }
        }

        if (addPosition > 0) {
            itemId = mDatas.get(addPosition - 1).getItemId() + 1;
        }

        mDatas.add(addPosition, ParseHelper.newNormalItem(itemId));
        notifyItemInserted(addPosition);//通知演示插入动画
        notifyItemRangeChanged(addPosition, mDatas.size() - addPosition);//通知数据与界面重新绑定
    }

    /**
     * 添加 Group item
     */
    public void addGroup() {
        int addPosition = mDatas.size();
        int itemId = 0;

        if (isHaveGroup()) {
            for (int i = 0; i < mDatas.size(); i++) {
                if (mDatas.get(i).getItemType() == DemoItemBean.TYPE_GROUP) {
                    itemId = mDatas.get(i).getItemId() + 1;
                }
            }
        }

        mDatas.add(addPosition, ParseHelper.newGroupItem(itemId));
        notifyItemInserted(addPosition);//通知演示插入动画
        notifyItemRangeChanged(addPosition, mDatas.size() - addPosition);//通知数据与界面重新绑定
    }

    /**
     * 添加 Child item
     * <p>
     * 添加位置为最后一个Group item
     */
    public void addChild() {
        int addPosition = 0;
        int itemId = 0;
        int childId = 0;

        if (!isHaveGroup() || mDatas.get(mDatas.size() - 1).getItemType() == DemoItemBean
                .TYPE_NORMAL) {
            addGroup();
        }

        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getItemType() == DemoItemBean.TYPE_GROUP) {
                itemId = mDatas.get(i).getItemId();
            }
        }

        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getItemId() == itemId && mDatas.get(i).getItemType() ==
                    DemoItemBean.TYPE_CHILD) {
                childId++;
            }
        }

        addPosition = mDatas.size();
        mDatas.add(addPosition, ParseHelper.newChildItem(itemId, childId));
        notifyItemInserted(addPosition);//通知演示插入动画
        notifyItemRangeChanged(addPosition, mDatas.size() - addPosition);//通知数据与界面重新绑定
    }

    /**
     * 当前list是否含有group
     *
     * @return 当前list是否含有group
     */
    private boolean isHaveGroup() {
        boolean isHaveGroup = false;//当前列表是否包含group

        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getItemType() == DemoItemBean.TYPE_GROUP) {
                isHaveGroup = true;
                break;
            }
        }
        return isHaveGroup;
    }

    /**
     * 获取最后一个Normal item的position
     *
     * @return 最后一个Normal item的position
     */
    public int getLastNormalItemPosition() {
        int addPosition = 0;
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).getItemType() == DemoItemBean.TYPE_GROUP) {
                addPosition = i;
                break;
            }
        }

        if (addPosition == 0) {
            addPosition = mDatas.size();
        }

        return addPosition - 1;
    }

    /**
     * 获取最后一个item的position
     *
     * @return 最后一个item的position
     */
    public int getLastItemPosition() {
        return mDatas.size();
    }
}
