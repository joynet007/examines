/**
 * 2017-2017年11月30日-上午11:26:07
 * liang
 * TODO
 */
package com.excomm.helper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 2017年11月30日-上午11:26:07
 * liang
 * TODO
 */
public class UpdateSqlHelper {
	
	/**
	 * main
	 * 2017-2017年11月25日-下午2:22:46
	 * liang
	 * TODO
	 */
	public static void main(String[] args) {
		UpdateSqlHelper ish = new UpdateSqlHelper();
		Connection con = null;
		try {
			con = JdbcHelper.getCon();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ish.updateSql(con, "tb_choicequestion","questionid","ruankao");

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateSql(Connection con, String tableName , String pkid ,String dbName) {
		TableHelper th = new TableHelper();
		List<SqlVo> list = th.getList(con, tableName,dbName);

		StringBuffer sb = new StringBuffer("update " + tableName + " set ");

		int i = 0;

		for (SqlVo sqlVo : list) {
			if (i == list.size() - 1) {
				
				sb.append(" " + sqlVo.getColumnName() +" = #{" + sqlVo.getColumnName() + "}");
				
				sb.append(" where "+ pkid +"= #{"+pkid+"}" );
			
			} else {
				sb.append(" " + sqlVo.getColumnName() +" = #{" + sqlVo.getColumnName() + "} , ");
			}

			i++;
		}


		System.out.println(sb.toString());

	}

}
