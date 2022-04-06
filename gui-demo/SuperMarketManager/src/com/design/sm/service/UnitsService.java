package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Units;

public interface UnitsService {

	// ͨ����λid��ȡ��λ����
	public Object getUnitsName(String id) throws SQLException;

	// ��ȡ������Ʒ��λ��Ϣ�б�
	public List<Units> findAllUnitsList() throws SQLException;

	// ��ȡ������Ʒ��λ��Ϣ�б�
	public Vector<Vector> findAllUnitsVector() throws SQLException;

	// ������λ��Ϣ
	public void addUnits(Units u) throws SQLException;

	// �޸ĵ�λ��Ϣ
	public void updateUnits(Units u) throws SQLException;

	// ����ɾ����λ��Ϣ
	public void deleteUnits(String id) throws SQLException;

	// ����������λ���ƹؼ��ֻ�ȡ��λ��Ϣ
	public Vector<Vector> getUnitsByNameKeyword(String keyword)
			throws SQLException;

}
