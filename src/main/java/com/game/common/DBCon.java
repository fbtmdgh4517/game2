package com.game.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {

	private static String DRIVER = "org.mariadb.jdbc.Driver";
	private static String URL = "jdbc:mariadb://localhost:3306/game";
	private static String USER = "KDTEST";
	private static String PWD = "kd1824java";
	
	static {
		try {
			Class.forName(DRIVER);
//			System.out.println("ㅋㅋ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getCon() {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PWD);
//			System.out.println("ㅋㅋ");
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		DBCon.getCon();
//	}
}
