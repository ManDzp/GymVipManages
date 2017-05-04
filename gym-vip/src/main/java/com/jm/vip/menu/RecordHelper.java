package com.jm.vip.menu;

import java.util.ArrayList;
import java.util.List;

import com.jm.base.tool.MenuItem;

/**
 * 查看记录菜单帮助类
 */
public class RecordHelper
{
	/**
	 * 查看记录列表菜单
	 * @return
	 */
	public List<MenuItem> getListMenu()
	{
		List<MenuItem> menuList = new ArrayList<MenuItem>();

		MenuItem menu = new MenuItem();
		menu = new MenuItem();
		menu.setDisplayName("返回");
		menu.setBclass("icon-back");
		menu.setItemClick("doBack()");
		menu.setId("back");
		menuList.add(menu);

		return menuList;
	}
}
