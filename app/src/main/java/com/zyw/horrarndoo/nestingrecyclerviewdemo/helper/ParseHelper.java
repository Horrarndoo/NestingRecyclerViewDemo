package com.zyw.horrarndoo.nestingrecyclerviewdemo.helper;

import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.ChildItemBean;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.DemoItemBean;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.GroupItemBean;
import com.zyw.horrarndoo.nestingrecyclerviewdemo.bean.NormalItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horrarndoo on 2017/11/23.
 * <p>
 */

public class ParseHelper {
    //=========== 这里模拟服务器返回的数据 ==========
    private static List<NormalItemBean> getNormalDatas() {
        List<NormalItemBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NormalItemBean bean = new NormalItemBean();
            bean.setItemId(i);
            bean.setChecked(false);
            bean.setItemType(DemoItemBean.TYPE_NORMAL);
            bean.setTitle("Normal: " + i);
            list.add(bean);
        }
        return list;
    }

    private static List<GroupItemBean> getGroupDatas() {
        List<GroupItemBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<ChildItemBean> childList = new ArrayList<>();
            GroupItemBean bean = new GroupItemBean();
            bean.setItemId(i);
            bean.setItemType(DemoItemBean.TYPE_GROUP);
            bean.setTitle("Group: " + i);
            bean.setChecked(false);

            for (int j = 0; j < 3; j++) {
                ChildItemBean bean1 = new ChildItemBean();
                bean1.setTitle("group: " + i + " child: " + j);
                bean1.setChecked(false);
                bean1.setItemType(DemoItemBean.TYPE_CHILD);
                bean1.setGroupId(i);//child的groupId对应当前
                bean1.setItemId(bean.getItemId());//child的itemId和其父group的itemId一致
                childList.add(bean1);
            }
            bean.setChilds(childList);
            list.add(bean);
        }
        return list;
    }
    //===============================================

    public static List<DemoItemBean> getParseDatas() {
        List<DemoItemBean> list = new ArrayList<>();

        for (NormalItemBean bean : getNormalDatas()) {
            list.add(bean);//normal
        }

        for (GroupItemBean bean : getGroupDatas()) {
            list.add(bean);//group

            for (ChildItemBean bean1 : bean.getChilds()) {
                list.add(bean1);//child
            }
        }
        return list;
    }

    /**
     * 获取group下的child list
     *
     * @param beans    整个数据list
     * @param position 当前group的position
     */
    public static List<ChildItemBean> getChildList(List<DemoItemBean> beans, int position) {
        List<ChildItemBean> childList = new ArrayList<>();
        for (DemoItemBean bean : beans) {
            //item id不相同直接跳过
            if (bean.getItemId() != beans.get(position).getItemId())
                continue;

            if (bean.getItemType() == DemoItemBean.TYPE_CHILD) {
                childList.add((ChildItemBean) bean);
            }
        }
        return childList;
    }

    /**
     * 取出list中的groupBean
     *
     * @param beans
     * @param itemId
     * @return
     */
    public static GroupItemBean getGroupBean(List<DemoItemBean> beans, int itemId) {
        for (DemoItemBean bean : beans) {
            if (bean.getItemType() == DemoItemBean.TYPE_GROUP && bean.getItemId() == itemId)
                return (GroupItemBean) bean;
        }
        return null;
    }

    /**
     * 根据itemId获取child所在的group的position
     *
     * @param beans  整个数据list
     * @param itemId child的itemId
     * @return group的position
     */
    public static int getGroupPosition(List<DemoItemBean> beans, int itemId) {
        for (int i = 0; i < beans.size(); i++) {
            if (beans.get(i).getItemType() == DemoItemBean.TYPE_GROUP
                    && beans.get(i).getItemId() == itemId)
                return i;
        }
        return 0;
    }

    /**
     * new一个normal item数据
     *
     * @param itemId position
     * @return normal item数据
     */
    public static NormalItemBean newNormalItem(int itemId) {
        NormalItemBean bean = new NormalItemBean();
        bean.setItemId(itemId);
        bean.setChecked(false);
        bean.setTitle("Normal: " + itemId);
        bean.setItemType(DemoItemBean.TYPE_NORMAL);
        return bean;
    }

    public static GroupItemBean newGroupItem(int itemId) {
        List<ChildItemBean> childList = new ArrayList<>();
        GroupItemBean bean = new GroupItemBean();
        bean.setItemId(itemId);
        bean.setItemType(DemoItemBean.TYPE_GROUP);
        bean.setTitle("Group: " + itemId);
        bean.setChilds(childList);
        bean.setChecked(false);
        return bean;
    }

    public static ChildItemBean newChildItem(List<DemoItemBean> beans, int itemId, int childId) {
        GroupItemBean groupItemBean = getGroupBean(beans, itemId);
        ChildItemBean bean = new ChildItemBean();
        bean.setGroupId(itemId);
        bean.setItemId(itemId);
        bean.setItemType(DemoItemBean.TYPE_CHILD);
        bean.setTitle("group: " + itemId + " child: " + childId);
        bean.setChecked(false);
        if (groupItemBean != null)
            groupItemBean.getChilds().add(bean);
        return bean;
    }
}
