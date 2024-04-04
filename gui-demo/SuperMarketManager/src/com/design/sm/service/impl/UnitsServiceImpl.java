package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.UnitsDAO;
import com.design.sm.dao.impl.UnitsDAOImpl;
import com.design.sm.model.Category;
import com.design.sm.model.Units;
import com.design.sm.service.UnitsService;

public class UnitsServiceImpl implements UnitsService {
	private UnitsDAO unitsDAO = new UnitsDAOImpl();

	// ���ݵ�λid��ȡ��λ����
	@Override
	public Object getUnitsName(String id) throws SQLException {
		return unitsDAO.getUnitsName(id);
	}

	// �������еĵ�λ��Ϣ�б�
	@Override
	public List<Units> findAllUnitsList() throws SQLException {
		return unitsDAO.findAllUnits();
	}

	// �������еĵ�λ��Ϣ��¼
	@Override
	public Vector<Vector> findAllUnitsVector() throws SQLException {
		return this.pack(unitsDAO.findAllUnits());
	}

	// ��ӵ�λ��Ϣ
	@Override
	public void addUnits(Units u) throws SQLException {
		unitsDAO.addUnits(u);
	}

	// �޸ĵ�λ��Ϣ
	@Override
	public void updateUnits(Units u) throws SQLException {
		unitsDAO.updateUnits(u);
	}

	// ɾ����λ��Ϣ
	@Override
	public void deleteUnits(String id) throws SQLException {
		unitsDAO.deleteUnits(id);
	}

	// ͨ����λ���ƹؼ��ֲ�����Ϣ
	@Override
	public Vector<Vector> getUnitsByNameKeyword(String keyword)
			throws SQLException {
		return this.pack(unitsDAO.getUnitsByNameKeyword(keyword));
	}

	// ��List<Units>��װΪVector<Vector>����
	public Vector<Vector> pack(List<Units> list) {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Units obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 3; i++) {
					temp.add(obj.getUnits_id());// ��λid
					temp.add(obj.getUnits_name());// ��λ����
					temp.add(obj.getDelete_flag());// ɾ����ʶ
				}
				rows.add(temp);
			}
		}
		return rows;
	}
}
