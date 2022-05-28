package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Temp;

public interface TempDAO extends BaseDAO<Temp>{
	
	// ����ղؼ�¼
	public void addTemp(Temp t)throws SQLException;
	
	// ���ݼ�¼idɾ���ղؼ�¼
	public void deleteTemp(String id)throws SQLException;
	
	// ����ָ���Ķ���id�����ղؼ�¼
	public List<Temp> findTempByReaderId(String reader_id)throws SQLException;
	
	// ����ISBN�Ų����ղؼ�¼(�����ظ�����ղؼ�¼)
	public Temp getTempByISBN(String isbn,String reader_id)throws SQLException;
	
}
