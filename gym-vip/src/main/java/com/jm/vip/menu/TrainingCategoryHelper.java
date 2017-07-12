package com.jm.vip.menu;

import java.util.ArrayList;
import java.util.List;

import com.jm.commons.base.tool.MenuItem;
import com.jm.vip.entity.TrainingCategory;

/**
 * 私教信息菜单帮助类
 */
public class TrainingCategoryHelper {

    /**
     * 私教信息列表菜单
     * 
     * @return
     */
    public List<MenuItem> getListMenu() {
        List<MenuItem> menuList = new ArrayList<MenuItem>();

        MenuItem menu = new MenuItem();

        menu.setDisplayName("新建");
        menu.setBclass("icon-add");
        menu.setItemClick("openAdd()");
        menu.setId("create");
        menuList.add(menu);

        menu = new MenuItem();
        menu.setDisplayName("删除");
        menu.setBclass("menu-delete");
        menu.setItemClick("deleteList()");
        menu.setId("delete");
        menuList.add(menu);

        return menuList;
    }

    /**
     * 新建页面菜单
     * 
     * @return
     */
    public List<MenuItem> getAddMenu() {
        List<MenuItem> menuList = new ArrayList<MenuItem>();

        MenuItem menu = new MenuItem();
        menu.setDisplayName("返回");
        menu.setBclass("icon-back");
        menu.setItemClick("doBack()");
        menu.setId("back");
        menuList.add(menu);

        menu = new MenuItem();
        menu.setDisplayName("保存");
        menu.setBclass("icon-save");
        menu.setItemClick("saveAddInfo()");
        menu.setId("save");
        menuList.add(menu);

        return menuList;
    }

    /**
     * 查看页菜单
     * 
     * @param prize
     * @return
     */
    public List<MenuItem> getViewMenu(TrainingCategory prize) {
        String guid = prize.getId();

        List<MenuItem> menuList = new ArrayList<MenuItem>();

        MenuItem menu = new MenuItem();
        menu.setDisplayName("返回");
        menu.setBclass("icon-back");
        menu.setItemClick("doBack()");
        menu.setId("back");
        menuList.add(menu);

        menu = new MenuItem();
        menu.setDisplayName("修改");
        menu.setBclass("menu-modify");
        menu.setItemClick("openEdit('" + guid + "')");
        menu.setId("save");
        menuList.add(menu);

        menu = new MenuItem();
        menu.setDisplayName("删除");
        menu.setBclass("menu-delete");
        menu.setItemClick("deleteOne('" + guid + "')");
        menu.setId("delete");
        menuList.add(menu);

        return menuList;
    }

    /**
     * 修改页面菜单
     * 
     * @param guid
     * @return
     */
    public List<MenuItem> getEditMenu(String guid) {
        List<MenuItem> menuList = new ArrayList<MenuItem>();

        MenuItem menu = new MenuItem();
        menu.setDisplayName("返回");
        menu.setBclass("icon-back");
        menu.setItemClick("changeToView('" + guid + "')");
        menu.setId("back");
        menuList.add(menu);

        menu = new MenuItem();
        menu.setDisplayName("保存");
        menu.setBclass("icon-save");
        menu.setItemClick("saveUpdateInfo('" + guid + "')");
        menu.setId("save");
        menuList.add(menu);

        return menuList;
    }

}
