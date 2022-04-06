package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.ProductDAO;
import com.design.sm.dao.TempDAO;
import com.design.sm.dao.VendorDAO;
import com.design.sm.dao.impl.ProductDAOImpl;
import com.design.sm.dao.impl.TempDAOImpl;
import com.design.sm.dao.impl.VendorDAOImpl;
import com.design.sm.model.Product;
import com.design.sm.model.Temp;
import com.design.sm.model.Warehouse;
import com.design.sm.service.TempService;

public class TempServiceImpl implements TempService{

	private TempDAO tempDAO = new TempDAOImpl();
	private ProductDAO productDAO = new ProductDAOImpl();
	private VendorDAO vendorDAO = new VendorDAOImpl();
	@Override
	public void addTemp(Temp t) throws SQLException {
		tempDAO.addTemp(t);
	}

	@Override
	public void update(Temp t) throws SQLException {
		tempDAO.update(t);
	}

	@Override
	public void deleteTemp(String id) throws SQLException {
		tempDAO.deleteTemp(id);
	}

	@Override
	public List<Temp> findAllTempList() throws SQLException {
		return tempDAO.findAllTempList();
	}

	@Override
	public Vector<Vector> pack(List<Temp> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Temp obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 8; i++) {
					temp.add(obj.getProduct_id());//��Ʒid
					temp.add(productDAO.getProductName(obj.getProduct_id()));//��Ʒ����
					temp.add(obj.getQuantity());//��������
					temp.add(obj.getUnit_price());//����
					temp.add(obj.getAmount());//�ܶ�
					Product prod = productDAO.getProduct(obj.getProduct_id());
					temp.add(prod.getVendor_id());//��Ӧ��id
					temp.add(vendorDAO.getVendorName(prod.getVendor_id()));//��Ӧ������
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public List<Temp> getTempListByProductId(String[] ids) throws SQLException {
		List<Temp> list = new ArrayList<Temp>();
		// ���α���
		for(String id : ids){
			Temp temp = tempDAO.getTempByProductId(id);
			list.add(temp);
		}
		return list;
	}
}
