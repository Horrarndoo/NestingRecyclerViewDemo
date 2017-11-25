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
            bean.setTitle("Group: " + i);
            bean.setChecked(false);

            for (int j = 0; j < 3; j++) {
                ChildItemBean bean1 = new ChildItemBean();
                bean1.setTitle("group: " + i + " child: " + j);
                bean1.setChecked(false);
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

        for (int i = 0; i < getNormalDatas().size(); i++) {
            DemoItemBean bean = new DemoItemBean();
            bean.setItemId(getNormalDatas().get(i).getItemId());
            bean.setItemType(DemoItemBean.TYPE_NORMAL);
            bean.setChecked(getNormalDatas().get(i).isChecked());
            bean.setTitle(getNormalDatas().get(i).getTitle());
            list.add(bean);//normal
        }

        for (int i = 0; i < getGroupDatas().size(); i++) {
            DemoItemBean bean = new DemoItemBean();
            bean.setItemId(getGroupDatas().get(i).getItemId());
            bean.setItemType(DemoItemBean.TYPE_GROUP);
            bean.setTitle(getGroupDatas().get(i).getTitle());
            bean.setChecked(getGroupDatas().get(i).isChecked());
            list.add(bean);//group

            List<ChildItemBean> childs = getGroupDatas().get(i).getChilds();
            for (int j = 0; j < childs.size(); j++) {
                DemoItemBean bean1 = new DemoItemBean();
                bean1.setItemId(childs.get(j).getItemId());
                bean1.setItemType(DemoItemBean.TYPE_CHILD);
                bean1.setTitle(childs.get(j).getTitle());
                bean1.setChecked(childs.get(j).isChecked());
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
    public static List<DemoItemBean> getChildList(List<DemoItemBean> beans, int position) {
        List<DemoItemBean> childList = new ArrayList<>();
        for (DemoItemBean bean : beans) {
            //item id不相同直接跳过
            if (bean.getItemId() != beans.get(position).getItemId())
                continue;

            if (bean.getItemType() == DemoItemBean.TYPE_CHILD) {
                childList.add(bean);
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
    public static DemoItemBean getGroupBean(List<DemoItemBean> beans, int itemId) {
        for (DemoItemBean bean : beans) {
            if (bean.getItemType() == DemoItemBean.TYPE_GROUP && bean.getItemId() == itemId)
                return bean;
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
    public static DemoItemBean newNormalItem(int itemId) {
        DemoItemBean bean = new DemoItemBean();
        bean.setItemId(itemId);
        bean.setChecked(false);
        bean.setTitle("Normal: " + itemId);
        bean.setItemType(DemoItemBean.TYPE_NORMAL);
        return bean;
    }

    public static DemoItemBean newGroupItem(int itemId) {
        List<ChildItemBean> childList = new ArrayList<>();
        DemoItemBean bean = new DemoItemBean();
        bean.setItemId(itemId);
        bean.setItemType(DemoItemBean.TYPE_GROUP);
        bean.setTitle("Group: " + itemId);
        bean.setChecked(false);
        return bean;
    }

    public static DemoItemBean newChildItem(int itemId, int childId) {
        DemoItemBean bean = new DemoItemBean();
        bean.setItemId(itemId);
        bean.setItemType(DemoItemBean.TYPE_CHILD);
        bean.setTitle("group: " + itemId + " child: " + childId);
        bean.setChecked(false);
        return bean;
    }
}
