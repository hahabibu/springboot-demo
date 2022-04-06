package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.SoldNote;

public interface SoldNoteDAO extends BaseDAO<SoldNote>{
	
	// �������ۼ�¼
	public void addSoldNote(SoldNote sn)throws SQLException;
	
	// ��ȡ���е����ۼ�¼
	public List<SoldNote> findAllSoldNote()throws SQLException;
	
	// ͨ��������Ż�ȡ��Ӧ�����ۼ�¼
	public SoldNote getSoldNoteByNum(String orderNum)throws SQLException;

}
