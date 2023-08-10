package com.game.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.game.mapper.BoardInfoMapper;
import com.game.mapper.TestInfoMapper;
import com.game.mapper.UserInfoMapper;
import com.game.vo.BoardInfoVO;
import com.game.vo.UserInfoVO;

public class MybatisSqlSessionFactory {

	private static SqlSessionFactory SSF;
	private final static String CONFIG_PATH = "config/mybatis-config.xml";
	static {
		try {
			InputStream is = Resources.getResourceAsStream(CONFIG_PATH);	// 경로가 src/main/java부터 시작한다는 뜻이라고함
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SSF = ssfb.build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return SSF;
	}
	
	public static void main(String[] args) {
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
//		TestInfoMapper tiMapper = session.getMapper(TestInfoMapper.class);
		UserInfoMapper uiMapper = session.getMapper(UserInfoMapper.class);
//		System.out.println(tiMapper.selectTestInfoList(null));
		UserInfoVO ui = new UserInfoVO();
		ui.setUiName("asd");
		ui.setUiId("asd123");
		ui.setUiPwd("asd123");
		ui.setUiDesc("asd123");
		ui.setUiBirth("20220811");
		System.out.println(uiMapper.insertUserInfoList(ui));
//		오픈 세션이 false면 아래것까지 실행해야함
//		session.commit();
//		session.close();
	}
}
