package com.excomm.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 
 * 2017年11月25日-下午2:23:02
 * liang
 * TODO 此类可以自动生成 inser 的语句
 */
public class InserSqlHelper {

	/**
	 * main
	 * 2017-2017年11月25日-下午2:22:46
	 * liang
	 * TODO
	 */
	public static void main(String[] args) {
		InserSqlHelper ish = new InserSqlHelper();
		Connection con = null;
		try {
			con = JdbcHelper.getCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ish.inserSql(con, "tb_learncurrent","ruankao");

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void inserSql(Connection con, String tableName,String dbName) {
		TableHelper th = new TableHelper();
		List<SqlVo> list = th.getList(con, tableName,dbName);

		StringBuffer sb = new StringBuffer("insert into " + tableName + "(");

		int i = 0;

		for (SqlVo sqlVo : list) {
			if (i == list.size() - 1) {
				sb.append(" " + sqlVo.getColumnName());
				sb.append(" )");
			} else {
				sb.append(" " + sqlVo.getColumnName() + " ,");
			}

			i++;
		}

		sb.append(" values ( ");
		int n = 0;
		for (SqlVo sqlVo : list) {
			// #{}
			if (n == list.size() - 1) {
				sb.append(" #{" + sqlVo.getColumnName() + "}");
				sb.append(" )");
			} else {
				sb.append(" #{" + sqlVo.getColumnName() + "} ,");
			}

			n++;
		}

		System.out.println("请你将我拷贝走：：");
		System.out.println(" "+sb.toString());

	}

}
