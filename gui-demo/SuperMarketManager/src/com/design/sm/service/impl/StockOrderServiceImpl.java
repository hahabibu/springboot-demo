package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.ProductDAO;
import com.design.sm.dao.StockMasterDAO;
import com.design.sm.dao.StockOrderDAO;
import com.design.sm.dao.VendorDAO;
import com.design.sm.dao.impl.ProductDAOImpl;
import com.design.sm.dao.impl.StockMasterDAOImpl;
import com.design.sm.dao.impl.StockOrderDAOImpl;
import com.design.sm.dao.impl.VendorDAOImpl;
import com.design.sm.model.Product;
import com.design.sm.model.StockOrder;
import com.design.sm.model.Temp;
import com.design.sm.service.StockOrderService;

public class StockOrderServiceImpl implements StockOrderService{
	private StockOrderDAO stockOrderDAO = new StockOrderDAOImpl();
	private StockMasterDAO stockMasterDAO = new StockMasterDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();
	private VendorDAO vendorDAO = new VendorDAOImpl();

	@Override
	public void addStockOrder(StockOrder t) throws SQLException {
		stockOrderDAO.addStockOrder(t);
	}

	@Override
	public List<StockOrder> getStockOrderBySMId(String smId)
			throws SQLException {
		return stockOrderDAO.getStockOrderBySMId(smId);
	}
	
	@Override
	public Vector<Vector> pack(List<StockOrder> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (StockOrder obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 9; i++) {
					temp.add(obj.getStock_master_id());//����id
					temp.add(stockMasterDAO.getSMOrderNumById(obj.getStock_master_id()));//�������
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
