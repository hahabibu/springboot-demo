package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.SaleTemp;

public interface SaleTempService {
	// ������ʱ��¼
	public void addSaleTemp(SaleTemp t) throws SQLException;

	// �޸���ʱ��¼
	public void update(SaleTemp t) throws SQLException;

	// ɾ����ʱ��¼
	public void deleteSaleTemp(String id) throws SQLException;

	// ��ȡ���е���ʱ��¼
	public List<SaleTemp> findAllSaleTempList() throws SQLException;

	// �����ҵ��ļ�¼��װΪVector<Vector>����
	public Vector<Vector> pack(List<SaleTemp> list) throws SQLException;

	// ͨ���ṩ�Ĳ�Ʒid�����ȡ��ʱ��Ӧ�ļ�¼��Ϣ
	public List<SaleTemp> getSaleTempListByProductId(String[] ids)
			throws SQLException;

	// ������Ʒid��ȡ��ʱ�ļ�¼��Ϣ��������
	public SaleTemp getSaleTempByProductId(String id) throws SQLException;

	// ͳ�Ƶ�ǰ�б�������в�Ʒ���ܶ�
	public double getAllAmount() throws SQLException;

	// ��յ�ǰ��ʱ�б�����м�¼
	public void truncateAllSaleTemp() throws SQLException;

}
