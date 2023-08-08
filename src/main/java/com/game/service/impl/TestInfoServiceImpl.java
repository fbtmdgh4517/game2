package com.game.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.common.MybatisSqlSessionFactory;
import com.game.mapper.TestInfoMapper;
import com.game.service.TestInfoService;
import com.game.vo.TestInfoVO;

public class TestInfoServiceImpl implements TestInfoService {
	private SqlSessionFactory ssf = MybatisSqlSessionFactory.getSqlSessionFactory();
	
	@Override
	public List<TestInfoVO> selectTestInfoList(TestInfoVO test) {
		try(SqlSession session = ssf.openSession()) {
			TestInfoMapper tiMapper = session.getMapper(TestInfoMapper.class);
			return tiMapper.selectTestInfoList(test);
		} catch(Exception e) {
			throw e;
		}
	}

}
