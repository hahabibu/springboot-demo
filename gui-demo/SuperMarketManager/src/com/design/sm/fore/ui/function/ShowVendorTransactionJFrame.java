package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.design.sm.model.PurchaseNote;
import com.design.sm.model.StockMaster;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.PurchaseNoteService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.PurchaseNoteServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.service.impl.VendorContactServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;

public class ShowVendorTransactionJFrame extends JFrame implements
		MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, tablePanel, topPanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	JLabel tool_export, tool_count;
	// ������Ӧ��service
	StockMasterService stockMasterService = new StockMasterServiceImpl();
	StockOrderService stockOrderService = new StockOrderServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	VendorContactService vendorContactService = new VendorContactServiceImpl();
	PurchaseNoteService purchaseNoteService = new PurchaseNoteServiceImpl();

	// ���常���
	VendorManagerJFrame parentPanel;
	JTable parentTable;
	int selectedRow;

	public ShowVendorTransactionJFrame(VendorManagerJFrame parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		backgroundPanel = new JPanel(new BorderLayout());
		initTopPanel();
		initTablePanel();
		this.add(backgroundPanel);
		// ���ô����С
		this.setTitle("���׼�¼");
		this.setSize(1000, 550);
		this.setLocationRelativeTo(null);// ����������
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	/**
	 * ��ʼ��������
	 */
	public void initTopPanel() {
		topPanel = new JPanel();
		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("��������");// ��������ƶ�ʱ����ʾ����
		tool_export.addMouseListener(this);// ���������

		Icon icon_count = new ImageIcon("icons/toolImage/count.png");
		tool_count = new JLabel(icon_count);
		tool_count.setToolTipText("��Ϣͳ��");// ��������ƶ�ʱ����ʾ����
		tool_count.addMouseListener(this);// ���������
		topPanel.add(tool_export);
		topPanel.add(tool_count);
		backgroundPanel.add(topPanel,BorderLayout.NORTH);
		// ��ˢ�����ݵ�ʱ��ı䴰���С���������ˢ��
		backgroundPanel.validate();
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ������������ɸѡָ����Χ�ڵ����ж�����Ϣ,ˢ���������
		// ��������ݽ��в���
		String[] params = { "�������", "������", "��Ӧ��", "��ϵ��", "����ʱ��", "ʵ�����","֧����ʽ" };
		Vector<Vector> vec = new Vector<>();
		// �˴���Ĭ�ϲ������е�����
		try {
			vec = this.pack(this.getSMTransactionByVendor(stockMasterService
					.findAllStockInList()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ����ѯ�������ݷ�װ��BbaseTableModule��
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// ��Ⱦ��0�У�������ʾΪ��ѡ�������ʾ
		table.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						JCheckBox ck = new JCheckBox();
						ck.setSelected(isSelected);
						ck.setHorizontalAlignment((int) 0.5f);
						return ck;
					}
				});
		// �����ṩ��Tools���������
		Tools.setTableStyle(table);
		// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// ���ù�����
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// ����͸����
		tablePanel.add(jScrollPane);
		// ��������ص�������
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		// ��ˢ�����ݵ�ʱ��ı䴰���С���������ˢ��
		backgroundPanel.validate();
	}

	/**
	 * ����ɸѡ��Ӧ��Ӧ�̵Ķ�����¼ 1.�����Ǿ�����˺�Ķ��� 2.��������Ĺ�Ӧ��id�뵱ǰѡ�еĹ�Ӧ��id��ͬ
	 */
	public List<StockMaster> getSMTransactionByVendor(List<StockMaster> list) {
		List<StockMaster> findList = new ArrayList<StockMaster>();
		String vendorId = parentTable.getValueAt(selectedRow, 0).toString();
		for (StockMaster sm : list) {
			if (sm.getState() == 1 && sm.getVendor_id().equals(vendorId)) {
				findList.add(sm);
			}
		}
		return findList;
	}

	/**
	 * �����ֶ������װ����
	 */
	public Vector<Vector> pack(List<StockMaster> list) throws SQLException {
		Vector<Vector> rows = new Vector<>();
		if (!list.isEmpty()) {
			for (StockMaster obj : list) {
				Vector temp = new Vector<String>();
				for (int i = 0; i < 7; i++) {
					temp.add(obj.getOrder_num());// �������
					temp.add(employeeService.getEmployeeName(obj
							.getHandler_id()));// ����������
					temp.add(parentTable.getValueAt(selectedRow, 1).toString());// ��Ӧ������
					temp.add(vendorContactService.getVendorContactName(obj
							.getContact_id()));// ��Ӧ����ϵ������
					temp.add(obj.getHandle_time());// ����ʱ��
					PurchaseNote purchaseNote = purchaseNoteService
							.getPurchaseNoteByNum(obj.getOrder_num());
					temp.add(purchaseNote.getActual_amount());// ʵ�����
					if(purchaseNote.getPayment()==1){
						temp.add("�ֽ�֧��");// ֧����ʽ
					}else if(purchaseNote.getPayment()==2){
						temp.add("ת��֧��");
					}
				}
				rows.add(temp);
			}
		}
		return rows;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_export) {
			int result = this.exportData();
			if (result == 0) {
				JOptionPane.showMessageDialog(null, "�û�ȡ���˲�����");
			} else if (result == 1) {
				JOptionPane.showMessageDialog(null, "���ݵ����ɹ�");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "��Ǹ�����ݵ���ʧ�ܣ������Կ��ɣ�");
			}
		} else if (e.getSource() == tool_count) {
			String str = this.getAmount();
				JOptionPane.showMessageDialog(null, "�����룺"+str);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	/**
	 * �Զ��嵼������
	 */
	public int exportData() {
		try {
			// ��������Ŀ¼
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// ����������
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;
			// ������һ�б�ͷ����
			cell = row.createCell((short) 0);
			cell.setCellValue("�������");
			cell = row.createCell((short) 1);
			cell.setCellValue("������");
			cell = row.createCell((short) 2);
			cell.setCellValue("��Ӧ��");
			cell = row.createCell((short) 3);
			cell.setCellValue("��ϵ��");
			cell = row.createCell((short) 4);
			cell.setCellValue("����ʱ��");
			cell = row.createCell((short) 5);
			cell.setCellValue("ʵ�����");
			cell = row.createCell((short) 6);
			cell.setCellValue("֧����ʽ");
			/**
			 * Ĭ���ǽ�ȫ�����ݵ��� ����ͨ���û�ѡ����Ӧ�����ݽ��е���
			 */ 
			for (int i = 0; i < table.getRowCount(); i++) {
				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(table.getValueAt(i, 0).toString());
				cell = row.createCell(1);
				cell.setCellValue(table.getValueAt(i, 1).toString());
				cell = row.createCell(2);
				cell.setCellValue(table.getValueAt(i, 2).toString());
				cell = row.createCell(3);
				cell.setCellValue(table.getValueAt(i, 3).toString());
				cell = row.createCell(4);
				cell.setCellValue(table.getValueAt(i, 4).toString());
				cell = row.createCell(5);
				cell.setCellValue(table.getValueAt(i, 5).toString());
				cell = row.createCell(6);
				cell.setCellValue(table.getValueAt(i, 6).toString());
			}
			// �����ļ�ѡ���
			JFileChooser chooser = new JFileChooser();
			// ��׺��������
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"����ļ�(*.xlsx)", "xlsx");
			chooser.setFileFilter(filter);
			// ����ķ�����������ֱ�����û����±��水ť�ҡ��ļ������ı���Ϊ�ա����û�����ȡ����ť��
			int option = chooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) { // �����û�ѡ���˱���
				File file = chooser.getSelectedFile();
				String fname = chooser.getName(file); // ���ļ���������л�ȡ�ļ���
				// �����û���д���ļ������������ƶ��ĺ�׺������ô���Ǹ������Ϻ�׺
				if (fname.indexOf(".xlsx") == -1) {
					file = new File(chooser.getCurrentDirectory(), fname
							+ ".xlsx");
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

	/**
	 * ��������ͳ�Ʒ�����ͳ�Ƶ�ǰѡ�е����ݵ��������ݵ��ܶ ���û��ѡ�е����ݣ���Ĭ��ͳ�Ƶ�ǰ������е�����
	 */
	public String getAmount() {
		// ��ȡ��ǰѡ�е�����
		String str = "";
		double amount = 0.00;
		int[] rows = table.getSelectedRows();
		if(rows.length!=0){
			//��ʾѡ��������
			for(int i=0;i<rows.length;i++){
				amount += Double.valueOf(table.getValueAt(rows[i],5).toString());
			}
			str= "��ǰѡ�еĽ��׼�¼����ĿΪ��"+rows.length+"--�ܶ�ƣ�"+amount;
		}else{
			// Ĭ��ͳ�Ʊ������
			for(int i=0;i<table.getRowCount();i++){
				amount += Double.valueOf(table.getValueAt(i, 5).toString());
			}
			str= "��ǰ��Ӧ�̹���"+table.getRowCount()+"������--�ܶ�ƣ�"+amount;
		}
		return str;
	}
}