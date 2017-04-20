package com.jm.vip.menu;

import java.util.ArrayList;
import java.util.List;

import com.jm.base.tool.MenuItem;

/**
 * 
 */
public class MemberInfoHelper
{
	/**
	 * 会员资料列表菜单
	 * @return
	 */
	public List<MenuItem> getListMenu()
	{
		List<MenuItem> menuList = new ArrayList<MenuItem>();

		MenuItem menu = new MenuItem();

		menu.setDisplayName("发卡");
		menu.setBclass("icon-add");
		menu.setItemClick("openAdd()");
		menu.setId("create");
		menuList.add(menu);

		menu = new MenuItem();
		menu.setDisplayName("封存");
		menu.setBclass("menu-delete");
		menu.setItemClick("deleteList()");
		menu.setId("delete");
		menuList.add(menu);

		return menuList;
	}

	/**
	 * 发卡页面菜单
	 * @return
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

	/**
	 * 查看页菜单
	 * @param guid
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
		menu.setDisplayName("封存");
		menu.setBclass("menu-delete");
		menu.setItemClick("deleteOne('" + guid + "')");
		menu.setId("delete");
		menuList.add(menu);

		menu = new MenuItem();
		menu.setDisplayName("充值");
		menu.setBclass("icon-add");
		menu.setItemClick("openCharge('" + guid + "')");
		menu.setId("charge");
		menuList.add(menu);

		menu = new MenuItem();
		menu.setDisplayName("开卡");
		menu.setBclass("icon-add");
		menu.setItemClick("openActiveCard('" + guid + "')");
		menu.setId("activeCard");
		menuList.add(menu);

		menu = new MenuItem();
		menu.setDisplayName("续卡");
		menu.setBclass("icon-add");
		menu.setItemClick("openContinueCard('" + guid + "')");
		menu.setId("continueCard");
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
		menu.setItemClick("changeToView('" + userguid + "')");
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
