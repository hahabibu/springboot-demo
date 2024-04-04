package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.SaleMaster;
import com.design.sm.model.SaleMaster;

public interface SaleMasterDAO extends BaseDAO<SaleMaster> {

	// �������ۼ�¼
	public void addSaleMaster(SaleMaster sm) throws SQLException;

	// �޸����ۼ�¼
	public void updateSaleMaster(SaleMaster sm) throws SQLException;

	// ��������id����ɾ�����ۼ�¼
	public void deleteSaleMaster(String id) throws SQLException;

	// ��������id����¼�������վ�򽫼�¼�ӻ���վ�г��أ��޸�ɾ����ʶ��
	public void setDeleteFlag(String id, int delete_flag) throws SQLException;

	// ��ѯ���е����ۼ�¼
	public List<SaleMaster> findAllSaleList() throws SQLException;

	// ��ȡ��ǰ������ŵ�����
	public Object getSaleNextSeq() throws SQLException;

	// ���ݶ���id��ȡ�������
	public Object getSOOrderNumById(String id) throws SQLException;

	// ������Ʒ�Ķ�������ʱ���Լ���Ӧ�Ķ�������״̬��ȡ������Ϣ
	public List<SaleMaster> findAllSaleListByTimeUnionState(String start,
			String end, int state) throws SQLException;

	// ��ȡ��Ʒ���۵��Ļ���վ�Ķ�����Ϣ��ɾ����ʶΪ1��
	public List<SaleMaster> findAllSaleListRecycleBin()
			throws SQLException;
	
	// ���ݶ�����Ż�ȡ����������Ϣ
	public SaleMaster getSaleMasterByOrderNum(String orderNum)throws SQLException;

}
