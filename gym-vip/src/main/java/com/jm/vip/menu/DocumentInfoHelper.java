package com.jm.vip.menu;

import java.util.ArrayList;
import java.util.List;

import com.jm.base.tool.MenuItem;
import com.jm.vip.entity.DocumentInfo;

/**
 * 帖子菜单帮助类
 */
public class DocumentInfoHelper
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

		menu.setDisplayName("删除");
		menu.setBclass("menu-delete");
		menu.setItemClick("viewListReason()");
		menu.setId("delete");
		menuList.add(menu);

		return menuList;
	}

	/**
	 * 获取修改页面菜单
	 * @param guid 帖子唯一标识
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
		menu.setItemClick("editDocumentInfo('" + guid + "')");
		menu.setId("save");
		menuList.add(menu);

		return menuList;
	}

	/**
	 * 获取查看页面菜单
	 * @param guid 帖子唯一标识
	 * @param documentInfo 帖子信息
	 * @return
	 */
	public List<MenuItem> getViewMenu(String guid, DocumentInfo documentInfo)
	{
		Short istop = documentInfo.getIstop();
		Short iscream = documentInfo.getIscream();

		List<MenuItem> menuList = new ArrayList<MenuItem>();

		MenuItem menu = new MenuItem();
		menu.setDisplayName("返回");
		menu.setBclass("icon-back");
		menu.setItemClick("viewBack()");
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
		menu.setItemClick("viewReason('" + guid + "')");
		menu.setId("deleteOne");
		menuList.add(menu);

		if (istop == 0)
		{
			menu = new MenuItem();
			menu.setDisplayName("置顶");
			menu.setBclass("menu-finished");
			menu.setItemClick("updateIsTop('" + guid + "')");
			menu.setId("finished");
			menuList.add(menu);
		}
		else
		{
			menu = new MenuItem();
			menu.setDisplayName("取消置顶");
			menu.setBclass("menu-zhuanzaiban");
			menu.setItemClick("updateNotIsTop('" + guid + "')");
			menu.setId("zhuanzaiban");
			menuList.add(menu);
		}

		if (iscream == 0)
		{
			menu = new MenuItem();
			menu.setDisplayName("精华");
			menu.setBclass("menu-qiyong");
			menu.setItemClick("updateIsCream('" + guid + "')");
			menu.setId("finished");
			menuList.add(menu);
		}
		else
		{
			menu = new MenuItem();
			menu.setDisplayName("取消精华");
			menu.setBclass("menu-tingyong");
			menu.setItemClick("updateNotIsCream('" + guid + "')");
			menu.setId("zhuanzaiban");
			menuList.add(menu);
		}

		menu = new MenuItem();
		menu.setDisplayName("回复");
		menu.setBclass("menu-reply");
		menu.setItemClick("changeToReply('" + guid + "')");
		menu.setId("reply");
		menuList.add(menu);

		menu = new MenuItem();
		menu.setDisplayName("转移");
		menu.setBclass("menu-send1");
		menu.setItemClick("viewcolumn('" + guid + "')");
		menu.setId("send");
		menuList.add(menu);

		return menuList;
	}

}
