package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Temp;

public interface TempDAO extends BaseDAO<Temp>{
	
	// ������ʱ��¼
	public void addTemp(Temp t)throws SQLException;
	
	// �޸���ʱ��¼
	public void update(Temp t)throws SQLException;
	
	// ɾ����ʱ��¼
	public void deleteTemp(String id)throws SQLException;
	
	// ��ȡ���е���ʱ��¼
	public List<Temp> findAllTempList()throws SQLException;
	
	// ͨ����Ʒid��ȡ��ʱ��¼��Ϣ
	public Temp getTempByProductId(String id)throws SQLException;

}
