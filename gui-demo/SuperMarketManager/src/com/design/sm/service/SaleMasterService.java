package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.SaleMaster;
import com.design.sm.model.StockMaster;

public interface SaleMasterService {

	// �������ۼ�¼
	public void addSaleMaster(SaleMaster sm) throws SQLException;

	// �޸����ۼ�¼
	public void updateSaleMaster(SaleMaster sm) throws SQLException;

	// ��������id����ɾ�����ۼ�¼
	public void deleteSaleMaster(String id) throws SQLException;

	// ��������id����¼�������վ���޸�ɾ����ʶ��
	public void joinRecycleBin(String id) throws SQLException;

	// ��������id����¼�ӻ���վ�г��أ��޸�ɾ����ʶ��
	public void revokeRecycleBin(String id) throws SQLException;

	// ��ѯ���е����ۼ�¼�б�
	public List<SaleMaster> findAllSaleList() throws SQLException;

	// ��ȡ��ǰ������ŵ�����
	public Object getSaleNextSeq() throws SQLException;

	// ���ݶ���id��ȡ�������
	public Object getSOOrderNumById(String id) throws SQLException;

	// ������Ʒ�Ķ�������ʱ���Լ���Ӧ�Ķ�������״̬��ȡ������Ϣ
	public List<SaleMaster> findAllStockListByTimeUnionState(String start,
			String end, int state) throws SQLException;

	// ��ȡ��Ʒ���۵����˻����Ļ���վ�Ķ�����Ϣ��ɾ����ʶΪ1��
	public List<SaleMaster> findAllSaleListRecycleBin() throws SQLException;

	// ��List<SaleMaster>��Ϣ��װΪVector<Vector>��¼
	public Vector<Vector> pack(List<SaleMaster> list) throws SQLException;

}
