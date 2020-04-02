package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.classinfo;

public class classinfodao {
	private Dbhelper helper;
	public classinfodao(){
		this.helper = new Dbhelper();
	}
	public List<classinfo> getcalssInfos() throws Exception{
		String sql = String.format("select * from class");		
		List<classinfo> list = new ArrayList<classinfo>();		
		this.helper.createConn();
		ResultSet rs = this.helper.query(sql,null);
	
		while(rs.next()){
			classinfo c = new classinfo();
			c.setId(rs.getInt("id"));
			c.setNo(rs.getString("no"));
			c.setName(rs.getString("name"));
			list.add(c);
		}	
		rs.close();
		this.helper.closeConnection();
		return list;
	}
}
