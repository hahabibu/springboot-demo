package com.guigu.library.dao;

import java.sql.SQLException;
import java.util.List;

import com.guigu.library.model.Reader;

public interface ReaderDAO extends BaseDAO<Reader>{
	
	// ��Ӷ�����Ϣ
	public void addReader(Reader r)throws SQLException;
	
	// �޸Ķ�����Ϣ
	public void updateReader(Reader r)throws SQLException;
	
	// ����idɾ��������Ϣ
	public void deleteReader(String id)throws SQLException;
	
	// �������ж�����Ϣ
	public List<Reader> findAllReader()throws SQLException;
	
	// ���¶�����ݶ��ַ�ʽ��ȡ������Ϣ
	// ����id��ȡ������Ϣ
	public Reader getReaderById(String id)throws SQLException;
	
	// ����������ؼ��ֻ�ȡ������Ϣ
	public List<Reader> findReaderByBarcode(String kw_barcode)throws SQLException;
	
	// ����ѧ����Źؼ��ֻ�ȡ������Ϣ
	public List<Reader> findReaderByAcademicNum(String kw_academic_num)throws SQLException;
	
	// �������֤�Źؼ��ֻ�ȡ������Ϣ
	public List<Reader> findReaderByIdCard(String kw_idCard)throws SQLException;
	
	// ���ݶ����˺����ƹؼ��ֻ�ȡ������Ϣ
	public List<Reader> findReaderByAccount(String kw_user_name)throws SQLException;
	
	// ���ݶ��߽���֤��Źؼ��ֻ�ȡ������Ϣ
	public List<Reader> findReaderByCardId(String kw_card_num)throws SQLException;
	
	// ���ݵ�ǰ��¼���˺�id��ȡ��Ӧ�Ķ��������Ϣ
	public Reader getReaderByUserId(String user_id)throws SQLException;
	
	// ���ݶ��ߵ��������ȡ���ߵ������Ϣ
	public Reader getReaderByBarcode(String barcode)throws SQLException;
	
}
