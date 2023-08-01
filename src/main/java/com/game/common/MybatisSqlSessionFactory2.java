package com.game.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.game.mapper.BoardInfoMapper2;
import com.game.vo.BoardInfoVO2;

public class MybatisSqlSessionFactory2 {

	private static SqlSessionFactory SSF;
	private static String CONFIG_PATH = "config/mybatis-config.xml";
	static {
		try {
			InputStream is = Resources.getResourceAsStream(CONFIG_PATH);
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SSF = ssfb.build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static SqlSessionFactory getSessionFactory() {
		return SSF;
	}

	public static void main(String[] args) {
		SqlSessionFactory ssf = getSessionFactory();
		SqlSession session = ssf.openSession(true);
		BoardInfoMapper2 biMapper = session.getMapper(BoardInfoMapper2.class);
//		List<BoardInfoVO2> list = biMapper.selectBoardInfoList(null);
//		System.out.println(list);
		
//		BoardInfoVO2 board = new BoardInfoVO2();
//		board.setBiNum(3);
//		System.out.println(biMapper.selectBoardInfo(board));
		
//		BoardInfoVO2 board = new BoardInfoVO2();
//		board.setBiTitle("컹스1234");
//		board.setBiContent("컹스12345");
//		board.setUiNum(1);
//		int result = biMapper.insertBoardInfo(board);
//		System.out.println(result);
		
//		BoardInfoVO2 board = new BoardInfoVO2();
//		board.setBiTitle("update123");
//		board.setBiContent("update1234");
//		board.setBiNum(7);
//		int result = biMapper.updateBoardInfo(board);
//		System.out.println(result);
		
//		BoardInfoVO2 board = new BoardInfoVO2();
//		board.setBiNum(15);
//		int result = biMapper.deleteBoardInfo(board);
//		System.out.println(result);
	}
}
