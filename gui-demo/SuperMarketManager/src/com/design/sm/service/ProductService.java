package com.design.sm.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.model.Product;

public interface ProductService {

	// �������е���Ʒ��Ϣ��¼
	public Vector<Vector> findAllProductVector() throws SQLException;

	// �������е���Ʒ��Ϣ�б�
	public List<Product> findAllProductList() throws SQLException;

	// ������Ʒ��������id������Ʒ��Ϣ
	public Vector<Vector> findAllProductByCategoryId(String cid)
			throws SQLException;

	// ������Ʒ�����ֿ�id������Ʒ��Ϣ
	public Vector<Vector> findAllProductByWarehouseId(String wid)
			throws SQLException;

	// ������Ʒ��������id����Ʒ�����ֿ�id���ϲ�����Ʒ��Ϣ
	public Vector<Vector> findAllProductUnion(String cid, String wid)
			throws SQLException;

	// ������Ʒ��������id������Ʒ��Ϣ�б�
	public List<Product> findAllProductListByCategoryId(String cid)
			throws SQLException;

	// ������Ʒ�����ֿ�id������Ʒ��Ϣ�б�
	public List<Product> findAllProductListByWarehouseId(String wid)
			throws SQLException;

	// ������Ʒ��������id����Ʒ�����ֿ�id���ϲ�����Ʒ��Ϣ�б�
	public List<Product> findAllProductListUnion(String cid, String wid)
			throws SQLException;

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

	// ������Ʒid��ȡ��Ʒ��Ϣ
	public Product getProduct(String id) throws SQLException;

	// ���ݹ�Ӧ��id��ȡ��Ӧ�Ĺ�Ӧ����Ϣ
	public List<Product> getProductByVendorId(String vendorId)
			throws SQLException;

	// ������Ʒ���ƹؼ��ֲ�����Ʒ��Ϣ
	public Vector<Vector> findAllProductByNameKeyword(String keyword)
			throws SQLException;

	// ������Ʒ����״̬������Ʒ��Ϣ
	public Vector<Vector> findAllProductByPromotionState(int promotion)
			throws SQLException;

	// ������Ʒ�ؼ��ֺʹ���״̬���ϲ�����Ʒ��Ϣ
	public Vector<Vector> findAllProductionUnionKP(String keyword, int promotion)
			throws SQLException;

	// ������Ʒ���ƹؼ��ֲ�����Ʒ��Ϣ
	public List<Product> findAllProductListByNameKeyword(String keyword)
			throws SQLException;

	// ������Ʒ����״̬������Ʒ��Ϣ
	public List<Product> findAllProductListByPromotionState(int promotion)
			throws SQLException;

	// ������Ʒ�ؼ��ֺʹ���״̬���ϲ�����Ʒ��Ϣ
	public List<Product> findAllProductionListUnionKP(String keyword,
			int promotion) throws SQLException;

	// ���ݸ�������Ʒid�б��ȡ��Ʒ��Ϣ
	public List<Product> findAllProductByIds(String[] ids) throws SQLException;

	// ����������Ʒ����
	public int exportData(String[] ids);

	// ��ҳ��������
	public Vector<Vector> getAllProductByPage(int page) throws SQLException;

	// ������Ʒ������ȡ��Ӧ������ҳ��
	public int getProductMaxPage() throws SQLException;

	// ����һ�����з�����List<Product> ���ͷ�װΪ Vendor<Vendor>����
	public Vector<Vector> pack(List<Product> list) throws SQLException;

	// ������Ʒ�����������Ʒ�ؼ��ֹ�ͬ������Ʒ��Ϣ
	public List<Product> getProductByFlowIdUnionName(String keyword)
			throws SQLException;
}
