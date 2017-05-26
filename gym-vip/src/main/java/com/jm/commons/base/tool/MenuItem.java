package com.jm.commons.base.tool;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 菜单属性类
 */
public class MenuItem
{
	/**
	 * 菜单显示名
	 */
	protected String displayName;
	/**
	 * 菜单样式
	 */
	protected String bclass;
	/**
	 * 菜单点击js
	 */
	protected String itemClick;
	/**
	 * 菜单控件id
	 */
	protected String id;
	/**
	 * 菜单类型。 linkbutton：默认按钮，menubutton：菜单按钮，subbutton：子按钮
	 */
	protected String menuType;
	/**
	 * 子菜单ID
	 */
	protected String subMenuId;
	/**
	 * 子菜单列表
	 */
	protected List<MenuItem> subMenuList;
	/** 
	 * 功能按钮是否公开，公开（不需判断授权）-0，不公开（需判断授权）-1
	 */
	private String openflag;

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public String getBclass()
	{
		return bclass;
	}

	public void setBclass(String bclass)
	{
		this.bclass = bclass;
	}

	public String getItemClick()
	{
		return itemClick;
	}

	public void setItemClick(String itemClick)
	{
		if (!itemClick.endsWith(";"))
		{
			itemClick = itemClick + ";";
		}
		this.itemClick = itemClick;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getMenuType()
	{
		return StringUtils.isNotEmpty(menuType) ? menuType : "linkbutton";
	}

	public void setMenuType(String menuType)
	{
		this.menuType = menuType;
	}

	public String getSubMenuId()
	{
		return subMenuId;
	}

	public void setSubMenuId(String subMenuId)
	{
		this.subMenuId = subMenuId;
	}

	public List<MenuItem> getSubMenuList()
	{
		return subMenuList;
	}

	public void setSubMenuList(List<MenuItem> subMenuList)
	{
		this.subMenuList = subMenuList;
	}
	/**
	 * 公开（不需判断授权）-0，不公开（需判断授权）-1
	 * @return
	 */
	public String getOpenflag()
	{
		return openflag;
	}
	/**
	 * 公开（不需判断授权）-0，不公开（需判断授权）-1
	 * @param openflag
	 */
	public void setOpenflag(String openflag)
	{
		this.openflag = openflag;
	}

}
