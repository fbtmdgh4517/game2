package com.game.service;

import java.util.List;
import java.util.Map;

import com.game.vo.UserInfoVO;

public interface UserInfoService {

	List<UserInfoVO> selectUserInfoList(UserInfoVO userInfo);
	Map<String, String> selectUserInfo(String uiNum);
	int insertUserInfo(UserInfoVO user);
	int updateUserInfo(Map<String, String> userInfo);
	int deleteUserInfo(String uiNum);
	Map<String, String> selectUserInfoById(String uiId);
}
