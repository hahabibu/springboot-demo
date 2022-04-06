package com.design.sm.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.ProductDAO;
import com.design.sm.dao.SaleMasterDAO;
import com.design.sm.dao.SaleOrderDAO;
import com.design.sm.dao.StockMasterDAO;
import com.design.sm.dao.StockOrderDAO;
import com.design.sm.dao.VendorDAO;
import com.design.sm.model.Product;
import com.design.sm.model.SaleOrder;
import com.design.sm.model.StockOrder;
import com.design.sm.service.SaleOrderService;

public class SaleOrderServiceImpl implements SaleOrderService{

	private ProductDAO productDAO = new ProductDAOImpl();
	private VendorDAO vendorDAO = new VendorDAOImpl();
	private SaleOrderDAO saleOrderDAO = new SaleOrderDAOImpl();
	private SaleMasterDAO saleMasterDAO = new SaleMasterDAOImpl();
	
	@Override
	public void addSaleOrder(SaleOrder t) throws SQLException {
		saleOrderDAO.addSaleOrder(t);
	}

	@Override
	public List<SaleOrder> getSaleOrderBySMId(String smId) throws SQLException {
		return saleOrderDAO.getSaleOrderBySMId(smId);
	}

	@Override
	public Vector<Vector> pack(List<SaleOrder> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (SaleOrder obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 9; i++) {
					temp.add(obj.getSale_master_id());//����id
					temp.add(saleMasterDAO.getSOOrderNumById(obj.getSale_master_id()));//�������
					temp.add(obj.getProduct_id());//��Ʒid
					temp.add(productDAO.getProductName(obj.getProduct_id()));//��Ʒ����
					temp.add(obj.getQuantity());//��������
					temp.add(obj.getUnit_price());//����
					temp.add(obj.getAmount());//�ܶ�
					Product prod = productDAO.getProduct(obj.getProduct_id());
					temp.add(prod.getVendor_id());//��Ӧ��id
					temp.add(vendorDAO.getVendorName(prod.getVendor_id()));//��Ӧ������
//					temp.add(obj.getState());//ɾ����ʶ���˴�������ʱ�����ǣ�
				}
				rows.add(temp);
			}
		}
		return rows;
	}
}
