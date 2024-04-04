package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.PurchaseNote;

public interface PurchaseNoteDAO extends BaseDAO<PurchaseNote>{
	
	// ���ӽ�����¼
	public void addPurchaseNote(PurchaseNote pn)throws SQLException;
	
	// ��ȡ���еĽ�����¼
	public List<PurchaseNote> findAllPurchaseNote()throws SQLException;
	
	// ͨ��������Ż�ȡ��Ӧ�Ľ�����¼
	public PurchaseNote getPurchaseNoteByNum(String orderNum)throws SQLException;

}
