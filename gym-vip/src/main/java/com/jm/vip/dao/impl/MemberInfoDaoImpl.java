package com.jm.vip.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.jm.page.PageSearch;
import com.jm.vip.dao.MemberInfoDao;
import com.jm.vip.entity.MemberInfo;

/**
 * 
 */
@Repository("memberInfoDao")
public class MemberInfoDaoImpl extends BaseDaoImpl<MemberInfo>
		implements MemberInfoDao
{
	@Override
	public String getMapperId()
	{
		return "MemberInfoMapper";
	}

	@Override
	public List<Object> getListByPage(PageSearch pageSearch, Integer pageNum,
			Integer pageSize)
	{
		RowBounds rowBounds = new RowBounds(pageNum, pageSize);
		return this.readSession.selectList(getMapperId() + ".selectListByPage",
				pageSearch, rowBounds);
	}

	@Override
	public MemberInfo getMemberByCardNumber(String cardNumber)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cardnumber", cardNumber);

		return this.session.selectOne(getMapperId() + ".selectByCardNumber",
				param);
	}

	@Override
	public MemberInfo getMemberByCardNumber(String cardNumber,
			String withoutGuid)
	{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cardnumber", cardNumber);
		param.put("withoutguid", withoutGuid);

		return this.session.selectOne(getMapperId() + ".selectByCardNumber",
				param);
	}

	@Override
	public int charge(MemberInfo memberInfo)
	{
		return this.session.update(getMapperId() + ".charge", memberInfo);
	}

	@Override
	public int buyCard(MemberInfo memberInfo)
	{
		return this.session.update(getMapperId() + ".buyCard", memberInfo);
	}

	@Override
	public int spend(MemberInfo memberInfo)
	{
		return this.session.update(getMapperId() + ".spend", memberInfo);
	}

	@Override
	public int activeCard(MemberInfo memberInfo)
	{
		return this.session.update(getMapperId() + ".activeCard", memberInfo);
	}

	@Override
	public int continueCard(MemberInfo memberInfo)
	{
		return this.session.update(getMapperId() + ".continueCard", memberInfo);
	}

	@Override
	public int buyCardPoints(MemberInfo memberInfo)
	{
		return this.session.update(getMapperId() + ".buyCardPoints",
				memberInfo);
	}

	@Override
	public int pointSignRecord(MemberInfo memberInfo)
	{
		return this.session.update(getMapperId() + ".pointSignRecord",
				memberInfo);
	}
}
