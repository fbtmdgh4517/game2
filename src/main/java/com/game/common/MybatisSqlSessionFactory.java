package com.game.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.game.mapper.BoardInfoMapper;
import com.game.vo.BoardInfoVO;

public class MybatisSqlSessionFactory {

	private static SqlSessionFactory SSF;
	private final static String CONFIG_PATH = "config/mybatis-config.xml";
	static {
		try {
			InputStream is = Resources.getResourceAsStream(CONFIG_PATH);
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
		SqlSession session = ssf.openSession(true);	// 오토커밋? 디폴트는 false
		BoardInfoMapper biMapper = session.getMapper(BoardInfoMapper.class);
		BoardInfoVO bi = new BoardInfoVO();
		bi.setBiNum(3);
		bi.setBiTitle("kungs");
		bi.setBiContent("kungs");
		bi.setUiNum(6);
		int result = biMapper.insertBoardInfo(bi);
		System.out.println(result);
		bi = biMapper.selectBoardInfo(bi);
		
		bi.setBiTitle("test11");
		bi.setBiContent("test11");
		bi.setBiNum(6);
		result = biMapper.updateBoardInfo(bi);
		System.out.println(result);
		
		List<BoardInfoVO> list = biMapper.selectBoardInfoList(null);
		for(BoardInfoVO board:list) {
			System.out.println(board);
		}
//		오픈 세션이 false면 아래것까지 실행해야함
//		session.commit();
//		session.close();
	}
}
