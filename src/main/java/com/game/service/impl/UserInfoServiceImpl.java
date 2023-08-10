package com.game.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.common.MybatisSqlSessionFactory;
import com.game.dao.UserInfoDAO;
import com.game.dao.impl.UserInfoDAOImpl;
import com.game.mapper.UserInfoMapper;
import com.game.service.UserInfoService;
import com.game.vo.UserInfoVO;

public class UserInfoServiceImpl implements UserInfoService {
	private UserInfoDAO uiDao = new UserInfoDAOImpl();
	private SqlSessionFactory ssf = MybatisSqlSessionFactory.getSqlSessionFactory();

	@Override
	public List<UserInfoVO> selectUserInfoList(UserInfoVO userInfo) {
		try(SqlSession session = ssf.openSession()) {
			UserInfoMapper uiMapper = session.getMapper(UserInfoMapper.class);
			return uiMapper.selectUserInfoList(userInfo);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public Map<String, String> selectUserInfo(String uiNum) {
		return uiDao.selectUserInfo(uiNum);
	}

	@Override
	public int insertUserInfo(UserInfoVO user) {
		try(SqlSession session = ssf.openSession(true)) {
			UserInfoMapper uiMapper = session.getMapper(UserInfoMapper.class);
			return uiMapper.insertUserInfoList(user);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public int updateUserInfo(Map<String, String> userInfo) {
		return uiDao.updateUserInfo(userInfo);
	}

	@Override
	public int deleteUserInfo(String uiNum) {
		return uiDao.deleteUserInfo(uiNum);
	}

	@Override
	public Map<String, String> selectUserInfoById(String uiId) {
		return uiDao.selectUserInfoById(uiId);
	}

}
