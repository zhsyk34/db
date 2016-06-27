package com.cat.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class JDBCUtil {

	private final static String CLASSNAME = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8";
	private final static String USER = "root";
	private final static String PASSWORD = "root";

	public static Connection getConnection() {
		try {
			Class.forName(CLASSNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static DataSource getDatasource() {
		try {
			Class.forName(CLASSNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl(URL);
		dataSource.setUser(USER);
		dataSource.setPassword(PASSWORD);

		return dataSource;
	}

	public static void main(String[] args) {
		System.out.println(getConnection());
		System.out.println(getDatasource());
	}
}
