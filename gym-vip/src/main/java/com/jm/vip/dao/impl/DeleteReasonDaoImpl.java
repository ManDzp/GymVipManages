package com.jm.vip.dao.impl;

import org.springframework.stereotype.Repository;

import com.jm.vip.dao.DeleteReasonDao;
import com.jm.vip.entity.DeleteReason;

@Repository("deletereasonDao")
public class DeleteReasonDaoImpl extends BaseDaoImpl<DeleteReason>
implements DeleteReasonDao
{

	@Override
	public String getMapperId()
	{
		return "DeleteReasonMapper";
	}

}
