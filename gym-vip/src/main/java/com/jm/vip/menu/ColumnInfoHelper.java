package com.jm.vip.menu;

import java.util.ArrayList;
import java.util.List;

import com.jm.base.tool.MenuItem;
import com.jm.vip.entity.ColumnInfo;

/**
 * 板块菜单帮助类
 */
public class ColumnInfoHelper
{
	/**
	 * 加载列表菜单
	 * @return
	 */
	public List<MenuItem> getListMenu()
	{
		List<MenuItem> menuList = new ArrayList<MenuItem>();

		// 菜单构造
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
	 * 获取添加页面菜单
	 * @return
	 */
	public List<MenuItem> getAddMenu()
	{
		List<MenuItem> menuList = new ArrayList<MenuItem>();

		// 菜单构造
		MenuItem menu = new MenuItem();
		menu.setDisplayName("返回");
		menu.setBclass("icon-back");
		menu.setItemClick("doBack()");
		menu.setId("back");
		menuList.add(menu);

		menu = new MenuItem();
		menu.setDisplayName("保存");
		menu.setBclass("icon-save");
		menu.setItemClick("addColumn()");
		menu.setId("save");
		menuList.add(menu);

		return menuList;
	}

	/**
	 * 获取修改页面菜单
	 * @param guid
	 * @return
	 */
	public List<MenuItem> getEditMenu(String guid)
	{
		List<MenuItem> menuList = new ArrayList<MenuItem>();

		// 菜单构造
		MenuItem menu = new MenuItem();
		menu.setDisplayName("返回");
		menu.setBclass("icon-back");
		menu.setItemClick("changeToView('" + guid + "')");
		menu.setId("back");
		menuList.add(menu);

		menu = new MenuItem();
		menu.setDisplayName("保存");
		menu.setBclass("icon-save");
		menu.setItemClick("editColumn('" + guid + "')");
		menu.setId("save");
		menuList.add(menu);

		return menuList;
	}

	/**
	 * 获取查看页面菜单
	 * @param guid
	 * @param columnInfo
	 * @return
	 */
	public List<MenuItem> getViewMenu(String guid, ColumnInfo columnInfo)
	{
		short status = columnInfo.getStatus();
		String title = columnInfo.getTitle();

		List<MenuItem> menuList = new ArrayList<MenuItem>();

		MenuItem menu = new MenuItem();
		menu.setDisplayName("返回");
		menu.setBclass("icon-back");
		menu.setItemClick("viewBack()");
		menu.setId("back");
		menuList.add(menu);

		int statusInt = (int) status;
		switch (statusInt)
		{
		case 0:
			menu = new MenuItem();
			menu.setDisplayName("修改");
			menu.setBclass("menu-modify");
			menu.setItemClick("openEdit('" + guid + "')");
			menu.setId("save");
			menuList.add(menu);

			menu = new MenuItem();
			menu.setDisplayName("删除");
			menu.setBclass("menu-delete");
			menu.setItemClick("deleteOne('" + guid + "', '" + title + "')");
			menu.setId("deleteOne");
			menuList.add(menu);

			menu = new MenuItem();
			menu.setDisplayName("发布");
			menu.setBclass("menu-finished");
			menu.setItemClick("updateToFinish('" + guid + "')");
			menu.setId("finished");
			menuList.add(menu);

			break;
		case 1:
			menu = new MenuItem();
			menu.setDisplayName("撤回");
			menu.setBclass("menu-zhuanzaiban");
			menu.setItemClick("updateToDeal('" + guid + "')");
			menu.setId("zhuanzaiban");
			menuList.add(menu);

			break;
		}

		return menuList;
	}

}
