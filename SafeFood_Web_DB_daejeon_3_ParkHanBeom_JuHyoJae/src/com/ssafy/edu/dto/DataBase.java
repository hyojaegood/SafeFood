package com.ssafy.edu.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public abstract class DataBase {
	private boolean isLog=true;
	private String DRVIER="com.mysql.cj.jdbc.Driver";
	private String UURL="jdbc:mysql://localhost:3306/ssafyhrm?useUniCode=yes&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	private String USER="ssafy";
	private String PWD="ssafy";
	
	public DataBase() {
		init();
	}

	private void init() {
		try {
			Class.forName(DRVIER);
			log("1/6 S");
		} catch (ClassNotFoundException e) {
			log("1/6 F",e);
		}
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn=null;
		conn=DriverManager.getConnection(UURL, USER, PWD);
		return conn;
	}
	
	public void close(ResultSet rs, Statement psmt, Connection conn) {
		if(rs!=null) {try {
			rs.close();
		} catch (SQLException e) {
		}}
		if(psmt!=null) {try {
			psmt.close();
		} catch (SQLException e) {
		}}
		if(conn!=null) {try {
			conn.close();
			log("6/6 S");
		} catch (SQLException e) {
			log("6/6 F",e);
		}}
	}
	
	
	public void log(String msg) {
		if(isLog) {
			System.out.println(this.getClass()+":"+msg+":"+new Date());
		}
	}
	public void log(String msg,Exception e) {
		if(isLog) {
			System.out.println(this.getClass()+":"+msg+":"+new Date());
			System.out.println("["+e.getMessage()+"]");
		}
	}
}
