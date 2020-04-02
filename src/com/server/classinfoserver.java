package com.server;

import java.util.List;

import com.dao.classinfodao;
import com.model.classinfo;

public class classinfoserver {
private classinfodao dao;
	
	public classinfoserver(){
		this.dao = new classinfodao();
	}
	public List<classinfo> getclassinfos() throws Exception{
		List<classinfo> list = this.dao.getcalssInfos();
		return list;
	}
}
