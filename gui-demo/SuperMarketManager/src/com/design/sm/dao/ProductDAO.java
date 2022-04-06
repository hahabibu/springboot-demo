package com.design.sm.dao;

import java.sql.SQLException;
import java.util.List;

import com.design.sm.model.Product;

public interface ProductDAO extends BaseDAO<Product> {

	// �������е���Ʒ��Ϣ
	public List<Product> findAllProduct() throws SQLException;

	// ������Ʒ��������id������Ʒ��Ϣ
	public List<Product> findAllProductByCategoryId(String cid)
			throws SQLException;

	// ������Ʒ�����ֿ�id������Ʒ��Ϣ
	public List<Product> findAllProductByWarehouseId(String wid)
			throws SQLException;

	// ������Ʒ��������id����Ʒ�����ֿ�id���ϲ�����Ʒ��Ϣ
	public List<Product> findAllProductUnion(String cid, String wid)
			throws SQLException;
	
	// ������Ʒ���ƹؼ��ֲ�����Ʒ��Ϣ
	public List<Product> findAllProductByNameKeyword(String keyword)throws SQLException;
	
	// ������Ʒ����״̬������Ʒ��Ϣ
	public List<Product> findAllProductByPromotionState(int promotion)throws SQLException;
	
	// ������Ʒ�ؼ��ֺʹ���״̬���ϲ�����Ʒ��Ϣ
	public List<Product> findAllProductionUnionKP(String keyword,int promotion)throws SQLException;

	// ������Ʒ������ϸ���ݣ������Ա����ά����
	public void saveProductDetail(Product prod) throws SQLException;

	// ������Ʒ���ݼ�����Ϣ���ɲֿ����Ա����ά����
	public void saveProductSimple(Product prod) throws SQLException;

	// ������Ʒidɾ����Ʒ����
	public void deleteProduct(String id) throws SQLException;

	// ������Ʒ����
	public void updateProduct(Product prod) throws SQLException;

	// ������Ʒid��ȡ��Ʒ����
	public Object getProductName(String id) throws SQLException;

	// ������Ʒid��ȡ��ǰ��Ʒ����
	public Product getProduct(String id) throws SQLException;

	// ���ݹ�Ӧ��id��ȡ��Ӧ����Ʒ��Ϣ
	public List<Product> getProductByVendorId(String vendorId)
			throws SQLException;
	
	// ��ҳ��������
	public List<Product> getAllProductByPage(int page)throws SQLException;
	
	// ��ȡ��Ʒ�����Ӷ��ó���Ӧ�ķ�ҳ��
	public Object getProductCount()throws SQLException;
	
	// ������Ʒ�����������Ʒ�ؼ��ֹ�ͬ������Ʒ��Ϣ
	public List<Product> getProductByFlowIdUnionName(String keyword)throws SQLException;
	
}
