package com.guigu.library.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.guigu.library.dao.LibraryCardDAO;
import com.guigu.library.dao.ReaderClassifyDAO;
import com.guigu.library.dao.ReaderDAO;
import com.guigu.library.dao.UsersDAO;
import com.guigu.library.dao.impl.LibraryCardDAOImpl;
import com.guigu.library.dao.impl.ReaderClassifyDAOImpl;
import com.guigu.library.dao.impl.ReaderDAOImpl;
import com.guigu.library.dao.impl.UsersDAOImpl;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderService;

public class ReaderServiceImpl implements ReaderService {

	private ReaderDAO readerDAO = new ReaderDAOImpl();
	private UsersDAO usersDAO = new UsersDAOImpl();
	private ReaderClassifyDAO readerClassifyDAO = new ReaderClassifyDAOImpl();
	private LibraryCardDAO libraryCardDAO = new LibraryCardDAOImpl();

	@Override
	public void addReader(Reader r) throws SQLException {
		readerDAO.addReader(r);
	}

	@Override
	public void updateReader(Reader r) throws SQLException {
		readerDAO.updateReader(r);
	}

	@Override
	public void deleteReader(String id) throws SQLException {
		readerDAO.deleteReader(id);
	}

	@Override
	public Reader getReaderById(String id) throws SQLException {
		return readerDAO.getReaderById(id);
	}

	@Override
	public List<Reader> findReaderUnion(int choose, Object... args)
			throws SQLException {
		// 0.�������� 1.������ 2.ѧ����� 3.���֤�� 4.�����˺� 5.����֤���
		String keyword;
		String text;
		if (choose == 0) {
			// �������е�����
			return readerDAO.findAllReader();
		} else if (choose == 1) {
			// ����������ؼ��ֲ��Ҷ�����Ϣ
			keyword = args[0] + "";
			text = "%" + keyword + "%";
			return readerDAO.findReaderByBarcode(text);
		} else if (choose == 2) {
			// ����ѧ����Źؼ��ֲ�������Ϣ
			keyword = args[0] + "";
			text = "%" + keyword + "%";
			return readerDAO.findReaderByAcademicNum(text);
		} else if (choose == 3) {
			// �������֤�Źؼ��ֲ��Ҷ�����Ϣ
			keyword = args[0] + "";
			text = "%" + keyword + "%";
			return readerDAO.findReaderByIdCard(text);
		} else if (choose == 4) {
			// ���ݶ����˺����ƹؼ��ֲ��Ҷ�����Ϣ
			keyword = args[0] + "";
			text = "%" + keyword + "%";
			return readerDAO.findReaderByAccount(text);
		} else if (choose == 5) {
			// ���ݶ��߽���֤��Ų��Ҷ�����Ϣ
			keyword = args[0] + "";
			text = "%" + keyword + "%";
			return readerDAO.findReaderByCardId(text);
		}
		return null;
	}

	@Override
	public Vector<Vector> pack(List<Reader> list) throws SQLException {
		// �ֶ���װ���ҵ���������Ϣ
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Reader obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 16; i++) {
					temp.add(obj.getReader_id());// ����id
					temp.add(obj.getBarcode());// ������
					temp.add(obj.getAcademic_num());// ѧ�����
					temp.add(obj.getReader_name());// ����
					temp.add(obj.getReader_sex());// �Ա�
					temp.add(obj.getReader_birthday());// ��������
					temp.add(obj.getReader_idCard());// ���֤��
					temp.add(obj.getReader_phone());// ��ϵ��ʽ
					temp.add(obj.getReader_email());// ��������
					temp.add(obj.getReader_descr());// ��ע
					temp.add(obj.getUser_id());// �����˺�id
					temp.add(usersDAO.getUsersName(obj.getUser_id()));// �����˺�
					temp.add(obj.getClassify_num());// ���߷�����
					temp.add(readerClassifyDAO.getClassifyNameByum(obj
							.getClassify_num()));// ��������
					temp.add(obj.getCard_id());// ����֤id
					temp.add(libraryCardDAO.getLibraryCardNumById(obj.getCard_id()));// ����֤
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public Reader getReaderByUserId(String user_id) throws SQLException {
		return readerDAO.getReaderByUserId(user_id);
	}

	@Override
	public Reader getReaderByBarcode(String barcode) throws SQLException {
		return readerDAO.getReaderByBarcode(barcode);
	}
}
