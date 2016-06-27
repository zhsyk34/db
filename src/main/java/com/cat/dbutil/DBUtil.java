package com.cat.dbutil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.cat.model.User;

public class DBUtil {

	public static void main(String[] args) throws SQLException {

		Connection conn = JDBCUtil.getConnection();
		// 创建SQL执行工具
		QueryRunner qRunner = new QueryRunner();

		// or
		DataSource dataSource = JDBCUtil.getDatasource();
		QueryRunner qRunner2 = new QueryRunner(dataSource);

		// save(qRunner, conn);
		// update(qRunner, conn);
		// find(qRunner2);
		// find2(qRunner2);
		// find3(qRunner2);
		update2(qRunner2);

		// 关闭数据库连接
		DbUtils.closeQuietly(conn);
	}

	public static void save(QueryRunner qRunner, Connection conn) throws SQLException {
		String sql = "insert into user(name, birthday) values('yzg', '1985-11-24 21:13:14')";
		int n = qRunner.update(conn, sql);
		System.out.println("成功插入" + n + "条数据！");
	}

	public static void update(QueryRunner qRunner, Connection conn) throws SQLException {

		String sql = "update user set name = 'haha' where id = 1";
		int n = qRunner.update(conn, sql);
		System.out.println("成功修改" + n + "条数据！");

	}

	public static void update2(QueryRunner qRunner) throws SQLException {

		String sql = "update user set name = ?, birthday = ? where id = ?";
		int n = qRunner.update(sql, "xyz", new Date(), 4);
		System.out.println("成功修改" + n + "条数据！");

	}

	public static void find(QueryRunner qRunner) throws SQLException {

		String sql = "select * from user";
		List<User> users = qRunner.query(sql, new BeanListHandler<>(User.class));
		users.forEach(user -> System.out.println(user));

	}

	public static void find2(QueryRunner qRunner) throws SQLException {

		String sql = "select * from user where id = ?";
		User user = qRunner.query(sql, new BeanHandler<>(User.class), 1);
		System.out.println(user);

	}

	public static void find3(QueryRunner qRunner) throws SQLException {

		String sql = "select count(*) from user";
		Long count = qRunner.query(sql, new ResultSetHandler<Long>() {
			@Override
			public Long handle(ResultSet rs) throws SQLException {
				return rs.next() ? rs.getLong(1) : 0;
			}
		});
		System.out.println(count);

	}
}
