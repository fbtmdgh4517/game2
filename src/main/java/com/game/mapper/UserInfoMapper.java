package com.game.mapper;

import java.util.List;

import com.game.vo.UserInfoVO;

public interface UserInfoMapper {
	List<UserInfoVO> selectUserInfoList(UserInfoVO user);
	int insertUserInfoList(UserInfoVO user);
}
