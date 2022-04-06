package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;
import com.design.sm.model.Units;

public interface UnitsDAO extends BaseDAO<Units> {
	// ͨ��������λid��ȡ��λ����
	public Object getUnitsName(String id) throws SQLException;

	// ����������λ���ƹؼ��ֻ�ȡ��λ��Ϣ
	public List<Units> getUnitsByNameKeyword(String keyword)
			throws SQLException;

	// ����������Ʒ��λ��Ϣ
	public List<Units> findAllUnits() throws SQLException;

	// ������λ��Ϣ
	public void addUnits(Units u) throws SQLException;

	// �޸ĵ�λ��Ϣ
	public void updateUnits(Units u) throws SQLException;

	// ����ɾ����λ��Ϣ
	public void deleteUnits(String id) throws SQLException;

}
