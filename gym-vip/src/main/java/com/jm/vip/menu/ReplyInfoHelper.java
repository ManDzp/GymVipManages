package com.jm.vip.menu;

import java.util.ArrayList;
import java.util.List;

import com.jm.base.tool.MenuItem;

/**
 * 回复菜单帮助类
 */
public class ReplyInfoHelper
{

	/**
	 * 获取列表页菜单
	 * @param docguid 帖子唯一标识
	 * @return
	 */
	public List<MenuItem> getListMenu(String docguid)
	{
		List<MenuItem> menuList = new ArrayList<MenuItem>();

		MenuItem menu = new MenuItem();

		menu = new MenuItem();
		menu.setDisplayName("返回");
		menu.setBclass("icon-back");
		menu.setItemClick("listBack('" + docguid + "')");
		menu.setId("back");
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
	 * 获取查看页菜单
	 * @param guid 回复唯一标识
	 * @return
	 */
	public List<MenuItem> getViewMenu(String guid)
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
	 * 获取修改页面菜单
	 * @param guid 回复唯一标识
	 * @return
	 */
	public List<MenuItem> getEditMenu(String guid)
	{
		List<MenuItem> menuList = new ArrayList<MenuItem>();

		MenuItem menu = new MenuItem();
		menu.setDisplayName("返回");
		menu.setBclass("icon-back");
		menu.setItemClick("backToView('" + guid + "')");
		menu.setId("back");
		menuList.add(menu);

		menu = new MenuItem();
		menu.setDisplayName("保存");
		menu.setBclass("icon-save");
		menu.setItemClick("saveupdateType('" + guid + "')");
		menu.setId("save");
		menuList.add(menu);

		return menuList;
	}

}
