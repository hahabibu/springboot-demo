package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Temp;

public interface TempService {
	// ������ʱ��¼
	public void addTemp(Temp t) throws SQLException;

	// �޸���ʱ��¼
	public void update(Temp t) throws SQLException;

	// ɾ����ʱ��¼
	public void deleteTemp(String id) throws SQLException;
	
	// ��ȡ���е���ʱ��¼
	public List<Temp> findAllTempList()throws SQLException;
	
	// �����ҵ��ļ�¼��װΪVector<Vector>����
	public Vector<Vector> pack(List<Temp> list)throws SQLException;
	
	// ͨ���ṩ�Ĳ�Ʒid�����ȡ��ʱ��Ӧ�ļ�¼��Ϣ
	public List<Temp> getTempListByProductId(String[] ids)throws SQLException;
}
