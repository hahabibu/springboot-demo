package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.PurchaseNote;

public interface PurchaseNoteService {

	// �������ۼ�¼
	public void addPurchaseNote(PurchaseNote pn) throws SQLException;

	// ��ȡ���е����ۼ�¼
	public List<PurchaseNote> findAllPurchaseNote() throws SQLException;

	// �����ۼ�¼���з�װ
	public Vector<Vector> pack(List<PurchaseNote> list) throws SQLException;

	// ͨ��������Ż�ȡ��Ӧ�����ۼ�¼
	public PurchaseNote getPurchaseNoteByNum(String orderNum) throws SQLException;

}
