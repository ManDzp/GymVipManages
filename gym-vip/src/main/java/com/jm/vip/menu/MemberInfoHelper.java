package com.jm.vip.menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jm.base.tool.MenuItem;
import com.jm.utils.DateHelper;
import com.jm.vip.entity.MemberHistoryInfo;
import com.jm.vip.entity.MemberInfo;

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
	 * @param memberInfo
	 * @return
	 */
	public List<MenuItem> getViewMenu(MemberInfo memberInfo)
	{
		String guid = memberInfo.getGuid();
		Integer status = memberInfo.getStatus();
		String cardtype = memberInfo.getCardtype();// 会员类型，0：时间卡，1：次卡

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

		// 时间卡才可以进行以下操作
		if ("0".equals(cardtype))
		{
			// 初始姿态，需要先买卡
			if (status == 0 || status == 1)
			{
				menu = new MenuItem();
				menu.setDisplayName("买卡");
				menu.setBclass("icon-add");
				menu.setItemClick("openBuyCard('" + guid + "')");
				menu.setId("activeCard");
				menuList.add(menu);
			}

			// 待开卡状态，需要开卡才会正常使用
			if (status == 1)
			{
				String nowExpireTime = DateHelper.getCurrentStrDate();

				menu = new MenuItem();
				menu.setDisplayName("开卡");
				menu.setBclass("icon-add");
				menu.setItemClick("openActiveCard('" + guid + "', '"
						+ nowExpireTime + "')");
				menu.setId("activeCard");
				menuList.add(menu);
			}

			// 正常状态、到期状态，可以进行续卡
			if (status == 2 || status == 4)
			{
				Date expiretime = memberInfo.getExpiretime();
				String nowExpireTime = DateHelper.getDateToShort(expiretime);

				menu = new MenuItem();
				menu.setDisplayName("续卡");
				menu.setBclass("icon-add");
				menu.setItemClick("openContinueCard('" + guid + "', '"
						+ nowExpireTime + "')");
				menu.setId("continueCard");
				menuList.add(menu);
			}

			// 正常状态，可以进行签到
			if (status == 2)
			{
				menu = new MenuItem();
				menu.setDisplayName("签到");
				menu.setBclass("icon-add");
				menu.setItemClick("signRecord('" + guid + "')");
				menu.setId("signRecord");
				menuList.add(menu);
			}
		}

		return menuList;
	}

	/**
	 * 查看页菜单
	 * @param memberInfo
	 * @return
	 */
	public List<MenuItem> getHistoryViewMenu(MemberHistoryInfo memberInfo)
	{
		List<MenuItem> menuList = new ArrayList<MenuItem>();

		MenuItem menu = new MenuItem();
		menu.setDisplayName("返回");
		menu.setBclass("icon-back");
		menu.setItemClick("doBack()");
		menu.setId("back");
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
