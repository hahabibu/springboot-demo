package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.ConsumeClassDAO;
import com.design.sm.dao.impl.ConsumeClassDAOImpl;
import com.design.sm.model.Accounts;
import com.design.sm.model.ConsumeClass;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.utils.SecretKey;

public class ConsumeClassServiceImpl implements ConsumeClassService {

	private ConsumeClassDAO dao = new ConsumeClassDAOImpl();
	@Override
	public List<ConsumeClass> findAllConsumeClassList() throws SQLException {
		return dao.findAllConsumeClass();
	}

	@Override
	public Vector<Vector> pack(List<ConsumeClass> list) throws SQLException {
		// �ֶ���װ���ҵ���������Ϣ
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (ConsumeClass obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 4; i++) {
					temp.add(obj.getClass_id());// �ȼ�id
					temp.add(obj.getClass_name());//�ȼ�����
					temp.add(obj.getClass_off());//�ȼ��Ż�
					temp.add(obj.getClass_discount());//�ȼ��ۿ�
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public void deleteConsumeClass(String id) throws SQLException {
		dao.deleteConsumeClass(id);
	}

	@Override
	public void updateConsumeClass(ConsumeClass cc) throws SQLException {
		dao.updateConsumeClass(cc);
	}

	@Override
	public Object getConsumeClassNameById(int id) throws SQLException {
		return dao.getConsumeClassNameById(id);
	}

	@Override
	public ConsumeClass getConsumeClassById(int id) throws SQLException {
		return dao.getConsumeClassById(id);
	}

}
