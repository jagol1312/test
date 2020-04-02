package com.server;

import java.util.List;

import com.dao.studentinfodao;
import com.model.studentinfo;

public class studentinfoserver {
private studentinfodao dao;
	
	public studentinfoserver(){
		this.dao = new studentinfodao();
	}
	public void addstudentinfo(int id,String no,String classid,String name,String sex,int age) throws Exception {
		 if(id==0||name.isEmpty())throw new Exception("id或name为空");
		 studentinfo s = new studentinfo();
		 s.setId(id);
		 s.setNo(no);
		 s.setClassid(classid);
		 s.setName(name);
		 s.setSex(sex);
		 s.setAge(age);
		 this.dao.addstudentinfo(s);
	}
	public void updatestudentagebyname(int age,String name) throws Exception {
		if(age==0||name.isEmpty())throw new Exception("age或name为空");
		this.dao.update(age, name);
	}
	public void deletestudentinfobyid(int id) throws Exception {
		if(id==0)throw new Exception("id为空");
		this.dao.delete(id);
	}
	public List<studentinfo> getStudentInfosByName(String name) throws Exception{
		List<studentinfo> list = this.dao.getStudentInfosByName(name);
		return list;
	}
}
