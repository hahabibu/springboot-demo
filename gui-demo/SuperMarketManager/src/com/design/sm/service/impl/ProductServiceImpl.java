package com.design.sm.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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

import com.design.sm.dao.CategoryDAO;
import com.design.sm.dao.ProductDAO;
import com.design.sm.dao.UnitsDAO;
import com.design.sm.dao.VendorDAO;
import com.design.sm.dao.WarehouseDAO;
import com.design.sm.dao.impl.CategoryDAOImpl;
import com.design.sm.dao.impl.ProductDAOImpl;
import com.design.sm.dao.impl.UnitsDAOImpl;
import com.design.sm.dao.impl.VendorDAOImpl;
import com.design.sm.dao.impl.WarehouseDAOImpl;
import com.design.sm.model.Product;
import com.design.sm.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private UnitsDAO unitsDAO = new UnitsDAOImpl();
	private CategoryDAO categoryDAO = new CategoryDAOImpl();
	private WarehouseDAO warehouseDAO = new WarehouseDAOImpl();
	private VendorDAO vendorDAO = new VendorDAOImpl();
	private ProductDAO dao = new ProductDAOImpl();

	@Override
	public Vector<Vector> findAllProductVector() throws SQLException {
		return this.pack(dao.findAllProduct());
	}

	@Override
	public List<Product> findAllProductList() throws SQLException {
		return dao.findAllProduct();
	}
	
	@Override
	public Vector<Vector> findAllProductByCategoryId(String cid)
			throws SQLException {
		return this.pack(dao.findAllProductByCategoryId(cid));
	}

	@Override
	public Vector<Vector> findAllProductByWarehouseId(String wid)
			throws SQLException {
		return this.pack(dao.findAllProductByWarehouseId(wid));
	}

	@Override
	public Vector<Vector> findAllProductUnion(String cid, String wid)
			throws SQLException {
		return this.pack(dao.findAllProductUnion(cid, wid));
	}

	@Override
	public void saveProductDetail(Product prod) throws SQLException {
		dao.saveProductDetail(prod);
	}

	@Override
	public void saveProductSimple(Product prod) throws SQLException {
		dao.saveProductSimple(prod);
	}

	@Override
	public void deleteProduct(String id) throws SQLException {
		dao.deleteProduct(id);
	}

	@Override
	public void updateProduct(Product prod) throws SQLException {
		dao.updateProduct(prod);
	}

	@Override
	public Object getProductName(String id) throws SQLException {
		return dao.getProductName(id);
	}

	// ����һ�����з�����List<Product> ���ͷ�װΪ Vendor<Vendor>����
	public Vector<Vector> pack(List<Product> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (Product obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 23; i++) {
					temp.add(obj.getProd_id());//��Ʒid
					temp.add(obj.getFlow_id());//������
					temp.add(obj.getProd_name());//��Ʒ����
					temp.add(obj.getProd_cost());//��Ʒ�ɱ�
					temp.add(obj.getProd_price());//��Ʒ�ۼ�
					temp.add(obj.getPutaway_stock());//���ϼܿ��
					temp.add(obj.getCurrent_stock());//��ǰ�ֿ���
					temp.add(obj.getSafe_stock());//��ȫ���
					temp.add(obj.getProd_unit());//��λid
					temp.add(unitsDAO.getUnitsName(obj.getProd_unit()));//��λ����
					temp.add(obj.getProd_origin());//����
					temp.add(obj.getProd_date());//��������
					temp.add(obj.getProd_descr());//��Ʒ����
					temp.add(obj.getProd_discount());//��Ʒ�ۿ�
					temp.add(obj.getPromotion_flag());//������ʶ
					temp.add(this.getPromotionState(obj.getPromotion_flag()));//����״̬
					temp.add(obj.getPromotion_price());//�����۸�
					temp.add(obj.getDelete_flag());//ɾ����ʶ	
					temp.add(obj.getCategory_id());//��Ʒ����id
					temp.add(categoryDAO.getCategoryName(obj.getCategory_id()));//��������
					temp.add(obj.getVendor_id());//��Ӧ��id
					temp.add(vendorDAO.getVendorName(obj.getVendor_id()));//��Ӧ������
					temp.add(obj.getWarehouse_id());//�ֿ�id
					temp.add(warehouseDAO.getWarehouseName(obj.getWarehouse_id()));//�ֿ�����
					/**
					 * ���Ա�鿴��Ϣ
					 * ��ʾ��Ϣ�����ơ��ɱ����ۼۡ����ϼܿ�桢����״̬����λ���ơ����ء��������ڡ��������ơ���Ӧ�����ơ��ֿ�����
					 * �����أ�0 1 6 7 8 12 13 14 16 17 18 20 22
					 * ���ң�������Ʒ���ƹؼ��֡����ࡢ�ֿ⡢����״̬��ϲ���
					 */
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public Product getProduct(String id) throws SQLException {
		return dao.getProduct(id);
	}

	@Override
	public List<Product> getProductByVendorId(String vendorId)
			throws SQLException {
		return dao.getProductByVendorId(vendorId);
	}
	
	public String getPromotionState(int PromotionFlag){
		String state = "";
		if(PromotionFlag==0){
			state = "���";
		}else if(PromotionFlag==1){
			state = "����";
		}
		return state;
	}

	@Override
	public Vector<Vector> findAllProductByNameKeyword(String keyword)
			throws SQLException {
		return this.pack(dao.findAllProductByNameKeyword(keyword));
	}

	@Override
	public Vector<Vector> findAllProductionUnionKP(String keyword, int promotion)
			throws SQLException {
		return this.pack(dao.findAllProductionUnionKP(keyword, promotion));
	}

	@Override
	public Vector<Vector> findAllProductByPromotionState(int promotion)
			throws SQLException {
		return this.pack(dao.findAllProductByPromotionState(promotion));
	}
	
	/**
	 * �Զ��嵼������
	 */
	public int exportData(String[] prodIds){
		try {
			// ��������Ŀ¼
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// ����������
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// ������һ�б�ͷ����
			cell = row.createCell((short) 0);
			cell.setCellValue("��Ʒ����");
			cell = row.createCell((short) 1);
			cell.setCellValue("��Ʒ�ɱ�");
			cell = row.createCell((short) 2);
			cell.setCellValue("��Ʒ�ۼ�");
			cell = row.createCell((short) 3);
			cell.setCellValue("���ϼܿ��");
			cell = row.createCell((short) 4);
			cell.setCellValue("��ǰ�ֿ���");
			cell = row.createCell((short) 5);
			cell.setCellValue("��Ʒ����");
			cell = row.createCell((short) 6);
			cell.setCellValue("��������");
			cell = row.createCell((short) 7);
			cell.setCellValue("����״̬");
			cell = row.createCell((short) 8);
			cell.setCellValue("�����۸�");
			cell = row.createCell((short) 9);
			cell.setCellValue("��λ");
			cell = row.createCell((short) 10);
			cell.setCellValue("��������");
			cell = row.createCell((short) 11);
			cell.setCellValue("�����ֿ�");
			cell = row.createCell((short) 12);
			cell.setCellValue("��Ӧ��");
			/**
			 * Ĭ���ǽ�ȫ�����ݵ���
			 * ����ͨ���û�ѡ����Ӧ�����ݽ��е���
			 */
			List<Product> list = null;
			if(prodIds!=null){
				list = this.findAllProductByIds(prodIds);
			}else{
				list = dao.findAllProduct();
			}
			for (int i = 0; i < list.size(); i++) {
				Product prod = list.get(i);
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(prod.getProd_name());
				cell = row.createCell(1);
				cell.setCellValue(prod.getProd_cost());
				cell = row.createCell(2);
				cell.setCellValue(prod.getProd_price());
				cell = row.createCell(3);
				cell.setCellValue(prod.getPutaway_stock());
				cell = row.createCell(4);
				cell.setCellValue(prod.getCurrent_stock());
				cell = row.createCell(5);
				cell.setCellValue(prod.getProd_origin());
				cell = row.createCell(6);
				cell.setCellValue(prod.getProd_date());
				cell = row.createCell(7);
				cell.setCellValue(this.getPromotionState(prod.getPromotion_flag()));
				cell = row.createCell(8);
				cell.setCellValue(prod.getPromotion_price());
				cell = row.createCell(9);
				cell.setCellValue(String.valueOf(unitsDAO.getUnitsName(prod.getProd_unit())));
				cell = row.createCell(10);
				cell.setCellValue(String.valueOf(categoryDAO.getCategoryName(prod.getCategory_id())));
				cell = row.createCell(11);
				cell.setCellValue(String.valueOf(warehouseDAO.getWarehouseName(prod.getWarehouse_id())));
				cell = row.createCell(12);
				cell.setCellValue(String.valueOf(vendorDAO.getVendorName(prod.getVendor_id())));
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
	public List<Product> findAllProductByIds(String[] ids) throws SQLException {
		// ѭ������id��Ϣ
		List<Product> list = new ArrayList<Product>();
		for(String id : ids){
			Product prod = dao.getProduct(id);
			list.add(prod);
		}
		return list;
	}

	@Override
	public Vector<Vector> getAllProductByPage(int page) throws SQLException {
		return this.pack(dao.getAllProductByPage(page));
	}

	@Override
	public int getProductMaxPage() throws SQLException {
		// ����ҳ����
		BigDecimal i = BigDecimal.valueOf(Integer.valueOf(dao.getProductCount().toString()));
		BigDecimal i2 = BigDecimal.valueOf(10);
		return i.divide(i2).intValue() + 1;
	}

	@Override
	public List<Product> findAllProductListByCategoryId(String cid)
			throws SQLException {
		return dao.findAllProductByCategoryId(cid);
	}

	@Override
	public List<Product> findAllProductListByWarehouseId(String wid)
			throws SQLException {
		return dao.findAllProductByWarehouseId(wid);
	}

	@Override
	public List<Product> findAllProductListUnion(String cid, String wid)
			throws SQLException {
		return dao.findAllProductUnion(cid, wid);
	}

	@Override
	public List<Product> findAllProductListByNameKeyword(String keyword)
			throws SQLException {
		return dao.findAllProductByNameKeyword(keyword);
	}

	@Override
	public List<Product> findAllProductListByPromotionState(int promotion)
			throws SQLException {
		return dao.findAllProductByPromotionState(promotion);
	}

	@Override
	public List<Product> findAllProductionListUnionKP(String keyword,
			int promotion) throws SQLException {
		return dao.findAllProductionUnionKP(keyword, promotion);
	}

	@Override
	public List<Product> getProductByFlowIdUnionName(String keyword)
			throws SQLException {
		return dao.getProductByFlowIdUnionName(keyword);
	}
}
