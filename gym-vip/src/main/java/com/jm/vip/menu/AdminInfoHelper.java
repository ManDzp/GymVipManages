package com.jm.vip.menu;

import java.util.ArrayList;
import java.util.List;

import com.jm.commons.base.tool.MenuItem;

/**
 * 用户菜单帮助类
 */
public class AdminInfoHelper
{

	public List<MenuItem> getListMenu()
	{
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
	 * 获取新建页面菜单
	 */
	public List<MenuItem> getAddMenu()
	{
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
		menu.setItemClick("saveAddUser()");
		menu.setId("save");
		menuList.add(menu);

		return menuList;
	}

	public List<MenuItem> getViewMenu(String userguid)
	{
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
		menu.setItemClick("openEdit('" + userguid + "')");
		menu.setId("save");
		menuList.add(menu);

		menu = new MenuItem();
		menu.setDisplayName("删除");
		menu.setBclass("menu-delete");
		menu.setItemClick("deleteOne('" + userguid + "')");
		menu.setId("delete");
		menuList.add(menu);

		return menuList;
	}

	/**
	 * 获取修改页面菜单
	 */
	public List<MenuItem> getEditMenu(String userguid)
	{
		List<MenuItem> menuList = new ArrayList<MenuItem>();

		MenuItem menu = new MenuItem();
		menu.setDisplayName("返回");
		menu.setBclass("icon-back");
		menu.setItemClick("backToView('" + userguid + "')");
		menu.setId("back");
		menuList.add(menu);

		menu = new MenuItem();
		menu.setDisplayName("保存");
		menu.setBclass("icon-save");
		menu.setItemClick("saveUpdateUser('" + userguid + "')");
		menu.setId("save");
		menuList.add(menu);

		return menuList;
	}
}
