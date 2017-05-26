package com.jm.commons.page;

import java.util.List;

public class DataGridSource
{
	List<?> rows;
	Long total;

	public List<?> getRows()
	{
		return rows;
	}

	public void setRows(List<?> rows)
	{
		this.rows = rows;
	}

	public Long getTotal()
	{
		return total;
	}

	public void setTotal(Long total)
	{
		this.total = total;
	}
}