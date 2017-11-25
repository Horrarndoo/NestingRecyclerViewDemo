package com.zyw.horrarndoo.nestingrecyclerviewdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.zyw.horrarndoo.nestingrecyclerviewdemo.R;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.adapter.DemoAdapter;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.adapter.OnCheckChangeListener;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.ChildItemBean;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.DemoItemBean;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.GroupItemBean;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.helper.ParseHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNestDemo;
    private DemoAdapter mDemoAdapter;
    private List<DemoItemBean> mDatas;
    private Button btnDelete, btnAddGroup, btnAddNormal, btnChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mDatas = ParseHelper.getParseDatas();
    }

    private void initView() {
        rvNestDemo = (RecyclerView) findViewById(R.id.rv_nest_demo);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnAddGroup = (Button) findViewById(R.id.btn_add_group);
        btnAddNormal = (Button) findViewById(R.id.btn_add_normal);
        btnChild = (Button) findViewById(R.id.btn_add_child);

        rvNestDemo.setLayoutManager(new LinearLayoutManager(this));
        mDemoAdapter = new DemoAdapter(this, mDatas);
        mDemoAdapter.setOnCheckChangeListener(new OnCheckChangeListener() {
            @Override
            public void onCheckedChanged(List<DemoItemBean> beans, int position, boolean
                    isChecked, int itemType) {
                switch (itemType) {
                    case DemoItemBean.TYPE_NORMAL:
                        normalCheckChange(beans, position, isChecked);
                        break;
                    case DemoItemBean.TYPE_GROUP:
                        groupCheckChange(beans, position, isChecked);
                        break;
                    case DemoItemBean.TYPE_CHILD:
                        childCheckChange(beans, position, isChecked);
                        break;
                }
            }
        });
        rvNestDemo.setAdapter(mDemoAdapter);

        btnDelete.setOnClickListener(new OnClickListener());
        btnAddGroup.setOnClickListener(new OnClickListener());
        btnAddNormal.setOnClickListener(new OnClickListener());
        btnChild.setOnClickListener(new OnClickListener());
    }

    /**
     * normal选中状态变化
     *
     * @param beans     数据
     * @param position  group position
     * @param isChecked 选中状态
     */
    private void normalCheckChange(List<DemoItemBean> beans, int position, boolean isChecked) {
        if (rvNestDemo.getScrollState() == RecyclerView.SCROLL_STATE_IDLE
                && !rvNestDemo.isComputingLayout()) {//避免滑动时刷新数据
            beans.get(position).setChecked(isChecked);
        }
    }

    /**
     * group选中状态变化
     *
     * @param beans     数据
     * @param position  group position
     * @param isChecked 选中状态
     */
    private void groupCheckChange(List<DemoItemBean> beans, int position, boolean isChecked) {
        if (rvNestDemo.getScrollState() == RecyclerView.SCROLL_STATE_IDLE
                && !rvNestDemo.isComputingLayout()) {//避免滑动时刷新数据
            beans.get(position).setChecked(isChecked);
            setChildCheck(beans, position, isChecked);
        }
    }

    /**
     * child选中状态变化
     *
     * @param beans     数据
     * @param position  child position
     * @param isChecked 选中状态
     */
    private void childCheckChange(List<DemoItemBean> beans, int position, boolean isChecked) {
        int itemId = beans.get(position).getItemId();

        if (rvNestDemo.getScrollState() == RecyclerView.SCROLL_STATE_IDLE
                && !rvNestDemo.isComputingLayout()) {//避免滑动时刷新数据

            beans.get(position).setChecked(isChecked);

            GroupItemBean groupBean = ParseHelper.getGroupBean(beans, itemId);

            List<ChildItemBean> childList = ParseHelper.getChildList(beans, position);
            for (int i = 0; i < childList.size(); i++) {
                if (!childList.get(i).isChecked()) {//只要有一个child没有选中，group就不是选中
                    if (groupBean.isChecked() && !isChecked) {//group为选中状态
                        setGroupCheck(beans, itemId, false);
                        mDemoAdapter.notifyItemChanged(ParseHelper.getGroupPosition(beans,
                                itemId));
                    }
                    return;
                }
            }

            //child全部选中，group设置选中
            setGroupCheck(beans, itemId, true);
            mDemoAdapter.notifyItemChanged(ParseHelper.getGroupPosition(beans, itemId));
        }
    }

    /**
     * 一次设置group下所有child item选中状态
     *
     * @param beans     整个数据list
     * @param position  group position
     * @param isChecked 设置选中状态
     */
    private void setChildCheck(List<DemoItemBean> beans, int position, boolean isChecked) {
        for (int i = 0; i < beans.size(); i++) {
            //item id不相同直接跳过
            if (beans.get(i).getItemId() != beans.get(position).getItemId())
                continue;

            if (beans.get(i).getItemType() == DemoItemBean.TYPE_CHILD) {//让group下的所有child选中
                if (beans.get(i).isChecked() != isChecked) {
                    beans.get(i).setChecked(isChecked);
                    mDemoAdapter.notifyItemChanged(i);
                }
            }
        }
    }

    /**
     * 设置group item选中状态
     *
     * @param beans     整个数据list
     * @param itemId    child的itemId
     * @param isChecked 设置选中状态
     */
    private void setGroupCheck(List<DemoItemBean> beans, int itemId, boolean isChecked) {
        for (DemoItemBean bean : beans) {
            if (bean.getItemType() == DemoItemBean.TYPE_GROUP
                    && bean.getItemId() == itemId) {
                bean.setChecked(isChecked);
            }
        }
    }

    private class OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_add_normal:
                    mDemoAdapter.addNormal();
                    rvNestDemo.smoothScrollToPosition(mDemoAdapter.getLastNormalItemPosition());
                    break;
                case R.id.btn_add_group:
                    mDemoAdapter.addGroup();
                    rvNestDemo.smoothScrollToPosition(mDemoAdapter.getLastItemPosition());
                    break;
                case R.id.btn_add_child:
                    mDemoAdapter.addChild();
                    rvNestDemo.smoothScrollToPosition(mDemoAdapter.getLastItemPosition());
                    break;
                case R.id.btn_delete:
                    mDemoAdapter.removeChecked();
                    break;
            }
        }
    }
}
