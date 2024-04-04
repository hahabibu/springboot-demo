package com.design.sm.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.design.sm.dao.VendorDAO;
import com.design.sm.dao.impl.VendorDAOImpl;
import com.design.sm.model.Product;
import com.design.sm.model.Vendor;
import com.design.sm.service.VendorService;


public class VendorServiceImpl implements VendorService{

	private VendorDAO dao = new VendorDAOImpl();
	@Override
	public Object getVendorName(String id) throws SQLException {
		return dao.getVendorName(id);
	}

	@Override
	public Vector<Vector> findAllVendorVector() throws SQLException {
		List<Vendor> list = dao.findAllVendor();
		return this.pack(list);
	}

	@Override
	public List<Vendor> findAllVendorList() throws SQLException {
		return dao.findAllVendor();
	}
	
	public Vector<Vector> pack(List<Vendor> list) throws SQLException{
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Vendor obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 7; i++) {
					/**
					 * ��ʾ vendor_id��vendor_name��vendor_phone��
					 * vendor_email��vendor_fax��vendor_address	
					 */
					temp.add(obj.getVendor_id());//��Ӧ��id
					temp.add(obj.getVendor_name());//��Ӧ������
					temp.add(obj.getVendor_phone());//��Ӧ����ϵ��ʽ
					temp.add(obj.getVendor_email());//����
					temp.add(obj.getVendor_fax());//�����
					temp.add(obj.getVendor_address());//��ַ
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public void addVendor(Vendor v) throws SQLException {
		dao.addVendor(v);
	}

	@Override
	public void updateVendor(Vendor v) throws SQLException {
		dao.updateVendor(v);
	}

	@Override
	public void deleteVendor(String id) throws SQLException {
		dao.deleteVendor(id);
	}

	@Override
	public Vector<Vector> getVendorByNameKeyword(String keyword)
			throws SQLException {
		List<Vendor> list = dao.getVendorByNameKeyword(keyword);
		return this.pack(list);
	}

	@Override
	public int exportData(String[] vendorIds) throws SQLException {
		try {
			// ��������Ŀ¼
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// ����������
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// ������һ�б�ͷ����
			cell = row.createCell((short) 0);
			cell.setCellValue("��Ӧ��");
			cell = row.createCell((short) 1);
			cell.setCellValue("��ϵ��ʽ");
			cell = row.createCell((short) 2);
			cell.setCellValue("��������");
			cell = row.createCell((short) 3);
			cell.setCellValue("����");
			cell = row.createCell((short) 4);
			cell.setCellValue("��˾��ַ");
			cell = row.createCell((short) 5);
			/**
			 * Ĭ���ǽ�ȫ�����ݵ���
			 * ����ͨ���û�ѡ����Ӧ�����ݽ��е���
			 */
			List<Vendor> list = null;
			if(vendorIds!=null){
				list = this.findAllVendorByIds(vendorIds);
			}else{
				list = dao.findAllVendor();
			}
			for (int i = 0; i < list.size(); i++) {
				Vendor v = list.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(v.getVendor_name());
				cell = row.createCell(1);
				cell.setCellValue(v.getVendor_phone());
				cell = row.createCell(2);
				cell.setCellValue(v.getVendor_email());
				cell = row.createCell(3);
				cell.setCellValue(v.getVendor_fax());
				cell = row.createCell(4);
				cell.setCellValue(v.getVendor_address());
			}
		//�����ļ�ѡ���  
	    JFileChooser chooser = new JFileChooser();  
	    //��׺��������  
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(  
	            "����ļ�(*.xlsx)", "xlsx");  
	    chooser.setFileFilter(filter);  
	    //����ķ�����������ֱ�����û����±��水ť�ҡ��ļ������ı���Ϊ�ա����û�����ȡ����ť��  
	    int option = chooser.showSaveDialog(null);  
	    if(option==JFileChooser.APPROVE_OPTION){    //�����û�ѡ���˱���  
	        File file = chooser.getSelectedFile();  
	        String fname = chooser.getName(file);   //���ļ���������л�ȡ�ļ���  
	        //�����û���д���ļ������������ƶ��ĺ�׺������ô���Ǹ������Ϻ�׺  
	        if(fname.indexOf(".xlsx")==-1){  
	            file=new File(chooser.getCurrentDirectory(),fname+".xlsx");  
	        }  
	        try {  
	            FileOutputStream FOut = new FileOutputStream(file);
	            workbook.write(FOut);
	            FOut.flush();
	            FOut.close();
				workbook.close();
				return 1;
	        } catch (IOException e) {  
	            System.err.println("IO�쳣");  
	            e.printStackTrace();  
	        }     
	    }  
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public List<Vendor> findAllVendorByIds(String[] vendorIds)
			throws SQLException {
		List<Vendor> list = new ArrayList<Vendor>();
		for(String id : vendorIds){
			Vendor v = dao.getVendorById(id);
			if(v!=null){
				list.add(v);
			}
		}
		return list;
	}
}
