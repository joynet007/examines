package com.excomm.helper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TableHelper {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static  String sql=" select  column_name, column_comment  from Information_schema.columns  where table_Name = ? and  TABLE_SCHEMA= ?";
	

	public List<SqlVo> getList (Connection con , String tableName , String dbName){
		if(null == con){
			System.out.println(TableHelper.class.getName()+"  con is null !!!!");
			return null;
		}
		List<SqlVo> list = new ArrayList<SqlVo>();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			
			if(null == pstmt){
				System.out.println(TableHelper.class.getName()+"  pstmt is null !!!!");
				return null;
			}
			
			pstmt.setString(1, tableName);
			pstmt.setString(2, dbName);
			rs=pstmt.executeQuery();
			int i=0;
			while(rs.next()){
				i++;
				SqlVo sv = new SqlVo();
				sv.setColumnComment(rs.getString("column_comment"));
				sv.setColumnName(rs.getString("column_name"));

//				System.out.println(rs.getString("column_comment") + "--" + rs.getString("column_name"));
				
				list.add(sv);
			}
			System.out.println(i+"---i的长度");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
	

}
