package com.guigu.library.service;

import java.sql.SQLException;

import com.guigu.library.model.LibraryCard;

public interface LibraryCardService {

	// ��ӽ���֤��Ϣ
	public void addLibraryCard(LibraryCard lc) throws SQLException;

	// �޸Ľ���֤��Ϣ
	public void updateLibraryCard(LibraryCard lc) throws SQLException;

	// ���ݽ���֤idɾ������֤��Ϣ
	public void deleteLibraryCard(String card_id) throws SQLException;

	// ���ݽ���֤id��ȡ����֤��Ϣ
	public LibraryCard getLibraryCardById(String card_id) throws SQLException;

	// ���ݽ���֤��Ż�ȡ����֤��Ϣ
	public LibraryCard getLibraryCardByNum(String card_num) throws SQLException;

	// ���ݽ���֤id��ȡ����֤���
	public String getLibraryCardNumById(String card_id) throws SQLException;

	// ��ȡ��ǰ����֤������
	public String getLibraryCardSeq() throws SQLException;

}
