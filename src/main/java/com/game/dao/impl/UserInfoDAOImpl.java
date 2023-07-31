package com.game.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.game.common.DBCon;
import com.game.dao.UserInfoDAO;

public class UserInfoDAOImpl implements UserInfoDAO {

	@Override
	public List<Map<String, String>> selectUserInfoList(Map<String, String> userInfo) {
		List<Map<String, String>> userInfoList = new ArrayList<>();
		String sql = "SELECT UI_NUM, UI_NAME, UI_ID, UI_PWD, UI_IMG_PATH, UI_DESC, UI_BIRTH, CREDAT, CRETIM, LMODAT, LMOTIM, ACTIVE FROM user_info";
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				try(ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						Map<String, String> ui = new HashMap<>();
						ui.put("uiNum", rs.getString("UI_NUM"));
						ui.put("uiName", rs.getString("UI_NAME"));
						ui.put("uiId", rs.getString("UI_ID"));
						ui.put("uiPwd", rs.getString("UI_PWD"));
						ui.put("uiImgPath", rs.getString("UI_IMG_PATH"));
						ui.put("uiDesc", rs.getString("UI_DESC"));
						ui.put("uiBirth", rs.getString("UI_BIRTH"));
						ui.put("creDat", rs.getString("CREDAT"));
						ui.put("creTim", rs.getString("CRETIM"));
						ui.put("lmoDat", rs.getString("LMODAT"));
						ui.put("lmoTim", rs.getString("LMOTIM"));
						ui.put("active", rs.getString("ACTIVE"));
						userInfoList.add(ui);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userInfoList;
	}

	@Override
	public Map<String, String> selectUserInfo(String uiNum) {
		String sql = "SELECT UI_NUM, UI_NAME, UI_ID, UI_PWD, \r\n"
				+ "UI_IMG_PATH, UI_DESC, DATE_FORMAT(NOW(), '%Y-%m-%d') UI_BIRTH, CREDAT, \r\n"
				+ "CRETIM, LMODAT, LMOTIM, ACTIVE \r\n"
				+ "FROM user_info\r\n"
				+ "WHERE UI_NUM=?";
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, uiNum);
				try(ResultSet rs = ps.executeQuery()) {
					if(rs.next()) {
						Map<String, String> ui = new HashMap<>();
						ui.put("uiNum", rs.getString("UI_NUM"));
						ui.put("uiName", rs.getString("UI_NAME"));
						ui.put("uiId", rs.getString("UI_ID"));
						ui.put("uiPwd", rs.getString("UI_PWD"));
						ui.put("uiImgPath", rs.getString("UI_IMG_PATH"));
						ui.put("uiDesc", rs.getString("UI_DESC"));
						ui.put("uiBirth", rs.getString("UI_BIRTH"));
						ui.put("creDat", rs.getString("CREDAT"));
						ui.put("creTim", rs.getString("CRETIM"));
						ui.put("lmoDat", rs.getString("LMODAT"));
						ui.put("lmoTim", rs.getString("LMOTIM"));
						ui.put("active", rs.getString("ACTIVE"));
						return ui;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertUserInfo(Map<String, String> userInfo) {
		String sql = "INSERT INTO user_info(\r\n"
				+ "UI_NAME, UI_ID, UI_PWD, UI_IMG_PATH, \r\n"
				+ "UI_DESC, UI_BIRTH, CREDAT, CRETIM, \r\n"
				+ "LMODAT, LMOTIM) \r\n"
				+ "VALUES (\r\n"
				+ "?, ?, ?, ?, \r\n"
				+ "?, ?, DATE_FORMAT(NOW(), '%Y%m%d'), DATE_FORMAT(NOW(), '%H%i%s'), \r\n"
				+ "DATE_FORMAT(NOW(), '%Y%m%d'), DATE_FORMAT(NOW(), '%H%i%s'))";
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, userInfo.get("uiName"));
				ps.setString(2, userInfo.get("uiId"));
				ps.setString(3, userInfo.get("uiPwd"));
				ps.setString(4, userInfo.get("uiImgPath"));
				ps.setString(5, userInfo.get("uiDesc"));
				ps.setString(6, userInfo.get("uiBirth"));
				return ps.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateUserInfo(Map<String, String> userInfo) {
		String sql = "UPDATE user_info \r\n"
				+ "SET UI_NAME=?, \r\n"
				+ "UI_ID=?, \r\n"
				+ "UI_PWD=?, \r\n"
				+ "UI_IMG_PATH=?, \r\n"
				+ "UI_DESC=?, \r\n"
				+ "UI_BIRTH=?,\r\n"
				+ "LMODAT=DATE_FORMAT(NOW(), '%Y%m%d'),\r\n"
				+ "LMOTIM= DATE_FORMAT(NOW(), '%H%i%s')\r\n"
				+ "WHERE UI_NUM = ?";
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, userInfo.get("uiName"));
				ps.setString(2, userInfo.get("uiId"));
				ps.setString(3, userInfo.get("uiPwd"));
				ps.setString(4, userInfo.get("uiImgPath"));
				ps.setString(5, userInfo.get("uiDesc"));
				ps.setString(6, userInfo.get("uiBirth"));
				ps.setString(7, userInfo.get("uiNum"));
				return ps.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteUserInfo(String uiNum) {
		String sql = "DELETE FROM user_info WHERE UI_NUM = ?";
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, uiNum);
				return ps.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Map<String, String> selectUserInfoById(String uiId) {
		String sql = "SELECT UI_NUM, UI_NAME, UI_ID, UI_PWD, \r\n"
				+ "UI_IMG_PATH, UI_DESC, UI_BIRTH, CREDAT, \r\n"
				+ "CRETIM, LMODAT, LMOTIM, ACTIVE \r\n"
				+ "FROM user_info\r\n"
				+ "WHERE UI_ID=?";
		try(Connection con = DBCon.getCon()) {
			try(PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, uiId);
				try(ResultSet rs = ps.executeQuery()) {
					if(rs.next()) {
						Map<String, String> ui = new HashMap<>();
						ui.put("uiNum", rs.getString("UI_NUM"));
						ui.put("uiName", rs.getString("UI_NAME"));
						ui.put("uiId", rs.getString("UI_ID"));
						ui.put("uiPwd", rs.getString("UI_PWD"));
						ui.put("uiImgPath", rs.getString("UI_IMG_PATH"));
						ui.put("uiDesc", rs.getString("UI_DESC"));
						ui.put("uiBirth", rs.getString("UI_BIRTH"));
						ui.put("creDat", rs.getString("CREDAT"));
						ui.put("creTim", rs.getString("CRETIM"));
						ui.put("lmoDat", rs.getString("LMODAT"));
						ui.put("lmoTim", rs.getString("LMOTIM"));
						ui.put("active", rs.getString("ACTIVE"));
						return ui;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static void main(String[] args) {
//		UserInfoDAO uiDao = new UserInfoDAOImpl();
////		System.out.println(uiDao.selectUserInfoList(null));
////		System.out.println(uiDao.selectUserInfo("1"));
//		
////		Map<String, String> ui = new HashMap<>();
////		ui.put("uiName", "name");
////		ui.put("uiId", "test1");
////		ui.put("uiPwd", "test1");
////		ui.put("uiDesc", "desc");
////		ui.put("uiBirth", "19990714");
////		System.out.println(uiDao.insertUserInfo(ui));
////		
////		ui.put("uiNum", "6");
////		System.out.println(uiDao.updateUserInfo(ui));
//		
////		System.out.println(uiDao.deleteUserInfo("7"));
//	}
}
