package com.design.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.design.sm.dao.PurchaseNoteDAO;
import com.design.sm.dao.StockMasterDAO;
import com.design.sm.dao.impl.PurchaseNoteDAOImpl;
import com.design.sm.dao.impl.StockMasterDAOImpl;
import com.design.sm.model.PurchaseNote;
import com.design.sm.model.StockMaster;
import com.design.sm.service.PurchaseNoteService;

public class PurchaseNoteServiceImpl implements PurchaseNoteService{

	private PurchaseNoteDAO soldNoteDAO = new PurchaseNoteDAOImpl();
	private StockMasterDAO stockMasterDAO = new StockMasterDAOImpl();
	
	@Override
	public void addPurchaseNote(PurchaseNote pn) throws SQLException {
		soldNoteDAO.addPurchaseNote(pn);
	}

	@Override
	public List<PurchaseNote> findAllPurchaseNote() throws SQLException {
		return soldNoteDAO.findAllPurchaseNote();
	}

	@Override
	public Vector<Vector> pack(List<PurchaseNote> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (PurchaseNote obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 5; i++) {
					temp.add(obj.getOrder_num());// �������
					StockMaster sm = stockMasterDAO.getStockMasterByOrderNum(obj.getOrder_num());
					temp.add(sm.getHandle_time());//��������ʱ��
					temp.add(obj.getActual_amount());// ����ʵ�ʽ��
					temp.add(obj.getPayment());// ֧����ʽ
					temp.add(this.getPaymentString(obj.getPayment()));// ֧����ʽ
				}
				rows.add(temp);
			}
		}
		return rows;
	}
	/**
	 * ����֧����ʽ��Ż�ȡ��Ӧ������
	 */
	public String getPaymentString(int payment){
		String str = "";
		if(payment==1){
			str = "�ֽ�֧��";
		}else if(payment==2){
			str = "ת��֧��";
		}
		return str;
	}

	@Override
	public PurchaseNote getPurchaseNoteByNum(String orderNum) throws SQLException {
		return soldNoteDAO.getPurchaseNoteByNum(orderNum);
	}
}
