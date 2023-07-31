package com.game.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.common.DBCon;
import com.game.dao.BoardInfoDAO;

public class BoardInfoDAOImpl implements BoardInfoDAO {

	@Override
	public List<Map<String, String>> selectBoardInfoList(Map<String, String> boardInfo) {
		List<Map<String, String>> boardInfoList = new ArrayList<>();
		String sql = "SELECT BI.*, UI.UI_NAME FROM board_info BI \r\n"
				+ "INNER JOIN user_info UI \r\n"
				+ "ON BI.UI_NUM = UI.UI_NUM WHERE 1=1";
		if(boardInfo != null) {
			String key = boardInfo.get("key");
			if("1".equals(key)) {
				sql += " AND BI_TITLE LIKE CONCAT('%', ?, '%')";
			} else if("2".equals(key)) {
				sql += " AND UI_NAME LIKE CONCAT('%', ?, '%')";
			} else if("3".equals(key)) {
				sql += " AND BI_CONTENT LIKE CONCAT('%', ?, '%')";
			} else if("4".equals(key)) {
				sql += " AND (BI_TITLE LIKE CONCAT('%', ?, '%') OR BI_CONTENT LIKE CONCAT('%', ?, '%'))";
			} else if("5".equals(key)) {
				sql += " AND (UI_NAME LIKE CONCAT('%', ?, '%') OR BI_CONTENT LIKE CONCAT('%', ?, '%'))";
			} else if("6".equals(key)) {
				sql += " AND (BI_TITLE LIKE CONCAT('%', ?, '%') OR UI_NAME LIKE CONCAT('%', ?, '%'))";
			} else if("7".equals(key)) {
				sql += " AND (BI_TITLE LIKE CONCAT('%', ?, '%')";
				sql += " OR UI_NAME LIKE CONCAT('%', ?, '%')";
				sql += " OR BI_CONTENT LIKE CONCAT('%', ?, '%'))";
			}
		}
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				if(boardInfo != null) {
					String key = boardInfo.get("key");
					if("1".equals(key) || "2".equals(key) || "3".equals(key)) {
						ps.setString(1, boardInfo.get("value"));						
					} else if("4".equals(key) || "5".equals(key) || "6".equals(key)) {
						ps.setString(1, boardInfo.get("value"));
						ps.setString(2, boardInfo.get("value"));
					} else if("7".equals(key)) {
						ps.setString(1, boardInfo.get("value"));
						ps.setString(2, boardInfo.get("value"));
						ps.setString(3, boardInfo.get("value"));
					}
				}
				try(ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						Map<String, String> bi = new HashMap<>();
						bi.put("biNum", rs.getString("BI_NUM"));
						bi.put("biTitle", rs.getString("BI_TITLE"));
						bi.put("biContent", rs.getString("BI_CONTENT"));
						bi.put("uiNum", rs.getString("UI_NUM"));
						bi.put("uiName", rs.getString("UI_NAME"));
						bi.put("creDat", rs.getString("CREDAT"));
						bi.put("creTim", rs.getString("CRETIM"));
						bi.put("lmoDat", rs.getString("LMODAT"));
						bi.put("lmoTim", rs.getString("LMOTIM"));
						bi.put("active", rs.getString("ACTIVE"));
						boardInfoList.add(bi);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return boardInfoList;
	}

	@Override
	public Map<String, String> selectBoardInfo(String biNum) {
		String sql = "SELECT BI_NUM, BI_TITLE, BI_CONTENT, UI_NUM, CREDAT,\r\n"
				+ "CRETIM, LMODAT, LMOTIM, ACTIVE FROM board_info WHERE BI_NUM=?";
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, biNum);
				try(ResultSet rs = ps.executeQuery()) {
					if(rs.next()) {
						Map<String, String> bi = new HashMap<>();
						bi.put("biNum", rs.getString("BI_NUM"));
						bi.put("biTitle", rs.getString("BI_TITLE"));
						bi.put("biContent", rs.getString("BI_CONTENT"));
						bi.put("uiNum", rs.getString("UI_NUM"));
						bi.put("creDat", rs.getString("CREDAT"));
						bi.put("creTim", rs.getString("CRETIM"));
						bi.put("lmoDat", rs.getString("LMODAT"));
						bi.put("lmoTim", rs.getString("LMOTIM"));
						bi.put("active", rs.getString("ACTIVE"));
						return bi;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertBoardInfo(Map<String, String> boardInfo) {
		String sql = "INSERT INTO BOARD_INFO(BI_TITLE, BI_CONTENT, UI_NUM, CREDAT,\r\n"
				+ "CRETIM, LMODAT, LMOTIM) \r\n"
				+ "VALUES(?, ?, ?, DATE_FORMAT(NOW(), '%Y%m%d'), \r\n"
				+ "DATE_FORMAT(NOW(), '%H%i%s'), DATE_FORMAT(NOW(), '%Y%m%d'), DATE_FORMAT(NOW(), '%H%i%s')\r\n"
				+ ")";
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, boardInfo.get("biTitle"));
				ps.setString(2, boardInfo.get("biContent"));
				ps.setString(3, boardInfo.get("uiNum"));
				return ps.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateBoardInfo(Map<String, String> boardInfo) {
		String sql = "UPDATE BOARD_INFO\r\n"
				+ "SET BI_TITLE=?,\r\n"
				+ "BI_CONTENT=?,\r\n"
				+ "LMODAT=DATE_FORMAT(NOW(), '%Y%m%d'),\r\n"
				+ "LMOTIM=DATE_FORMAT(NOW(), '%H%i%s')\r\n"
				+ "WHERE BI_NUM=?";
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, boardInfo.get("biTitle"));
				ps.setString(2, boardInfo.get("biContent"));
				ps.setString(3, boardInfo.get("biNum"));
				return ps.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteBoardInfo(String biNum) {
		String sql = "DELETE FROM board_info WHERE BI_NUM=?";
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, biNum);
				return ps.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		BoardInfoDAO biDao = new BoardInfoDAOImpl();
//		System.out.println(biDao.selectBoardInfoList(null));
//		System.out.println(biDao.selectBoardInfo("3"));
		
//		Map<String, String> bi = new HashMap<>();
//		bi.put("biTitle", "test2");
//		bi.put("biContent", "test2");
//		bi.put("uiNum", "1");
//		System.out.println(biDao.insertBoardInfo(bi));
		
//		Map<String, String> bi = new HashMap<>();
//		bi.put("biTitle", "test4");
//		bi.put("biContent", "test4");
//		bi.put("biNum", "8");
//		System.out.println(biDao.updateBoardInfo(bi));
		
//		System.out.println(biDao.deleteBoardInfo("9"));
	}
}
