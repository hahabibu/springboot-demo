package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.SaleTemp;

public interface SaleTempDAO extends BaseDAO<SaleTemp>{
	
	// ������ʱ��¼
	public void addSaleTemp(SaleTemp t)throws SQLException;
	
	// �޸���ʱ��¼
	public void update(SaleTemp t)throws SQLException;
	
	// ɾ����ʱ��¼
	public void deleteSaleTemp(String id)throws SQLException;
	
	// ��ȡ���е���ʱ��¼
	public List<SaleTemp> findAllSaleTempList()throws SQLException;
	
	// ͨ����Ʒid��ȡ��ʱ��¼��Ϣ
	public SaleTemp getSaleTempByProductId(String id)throws SQLException;
	
	// ͳ�Ƶ�ǰ�б�������в�Ʒ���ܶ�
	public Object getAllAmount()throws SQLException;
	
	// ��յ�ǰ��ʱ�б�����м�¼
	public void truncateAllSaleTemp()throws SQLException;

}
