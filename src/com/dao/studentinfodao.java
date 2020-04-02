package com.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.model.studentinfo;
/**
 * 
 * @author HP
 *
 */
public class studentinfodao {
	private Dbhelper helper;
	public studentinfodao() {
		this.helper = new Dbhelper();
	}
	public void addstudentinfo(studentinfo s) throws Exception {
		String sql = "insert into student values(?,?,?,?,?,?)";
		Map<String,Object> param = new LinkedHashMap<String,Object>();
		param.put("Id", s.getId());
		param.put("No", s.getNo());
		param.put("Classid", s.getClassid());
		param.put("Name",s.getName());
		param.put("Sex", s.getSex());
		param.put("Age", s.getAge());
		this.helper.createConn();
		this.helper.execute(sql, param);
	}
	public void update(int age,String name) throws Exception {
		String sql = "update student set age = ? where name = ?";		
		Map<String,Object> param = new LinkedHashMap<String,Object>();
		param.put("age",age);
		param.put("name",name);
		this.helper.createConn();
		this.helper.execute(sql, param);
	}
	public void delete(int id) throws Exception {
		String sql = "delete from student where id = ?";
		Map<String,Object> param = new LinkedHashMap<String,Object>();
		param.put("id", id);
		this.helper.createConn();
		this.helper.execute(sql, param);
	}
	public List<studentinfo> getStudentInfosByName(String name) throws Exception{
		String sql = String.format("select * from student where Name like '%%%s%%'",name);		
		List<studentinfo> list = new ArrayList<studentinfo>();		
		this.helper.createConn();
		ResultSet rs = this.helper.query(sql,null);
	
		while(rs.next()){
			studentinfo s = new studentinfo();
			s.setId(rs.getInt("id"));
			s.setNo(rs.getString("no"));
			s.setClassid(rs.getString("classid"));
			s.setName(rs.getString("name"));
			s.setSex(rs.getString("sex"));
			s.setAge(rs.getInt("age"));
			list.add(s);
		}	
		rs.close();
		this.helper.closeConnection();
		return list;
	}
	
}
