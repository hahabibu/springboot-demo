package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.SoldNote;

public interface SoldNoteService {

	// �������ۼ�¼
	public void addSoldNote(SoldNote sn) throws SQLException;

	// ��ȡ���е����ۼ�¼
	public List<SoldNote> findAllSoldNote() throws SQLException;

	// �����ۼ�¼���з�װ
	public Vector<Vector> pack(List<SoldNote> list) throws SQLException;

	// ͨ��������Ż�ȡ��Ӧ�����ۼ�¼
	public SoldNote getSoldNoteByNum(String orderNum) throws SQLException;

}
