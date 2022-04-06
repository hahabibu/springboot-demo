package com.design.sm.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.design.sm.model.Accounts;
import com.design.sm.service.PurchaseNoteService;
import com.design.sm.service.SoldNoteService;
import com.design.sm.service.impl.PurchaseNoteServiceImpl;
import com.design.sm.service.impl.SoldNoteServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;
import com.eltima.components.ui.DatePicker;

public class BalanceJPanel extends JFrame implements ItemListener,
		MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel;
	DatePicker start_time, end_time;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JLabel tool_export, tool_count;
	JTable table;
	JScrollPane jScrollPane;
	// ������Ӧ���ı�����ϰ�ť
	ButtonGroup state;
	JRadioButton income, pay;
	// ������Ӧ��service
	SoldNoteService soldNoteService = new SoldNoteServiceImpl();
	PurchaseNoteService purchaseNoteService = new PurchaseNoteServiceImpl();

	Accounts loginUser;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public BalanceJPanel(Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.loginUser = loginUser;
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initTablePanel();// ��ʼ����ʾ�ı������
	}

	/**
	 * ��ʼ�������Ĳ˵���
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// ��ʼ�����������
		initToolPanel();
		// ��ʼ���������
		initSearchPanel();
		// �������˵������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ�����������
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();
		Icon export_icon = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(export_icon);
		tool_export.setToolTipText("���ݵ���");
		tool_export.addMouseListener(this);

		Icon count_icon = new ImageIcon("icons/toolImage/count.png");
		tool_count = new JLabel(count_icon);
		tool_count.setToolTipText("����ͳ��");
		tool_count.addMouseListener(this);

		state = new ButtonGroup();
		Icon income_icon = new ImageIcon("icons/toolImage/income.png");
		income = new JRadioButton(income_icon);
		income.setToolTipText("�����¼");
		income.addItemListener(this);

		Icon pay_icon = new ImageIcon("icons/toolImage/pay.png");
		pay = new JRadioButton(pay_icon);
		pay.setToolTipText("֧����¼");
		pay.addItemListener(this);

		state.add(income);
		state.add(pay);
		toolPanel.add(tool_export);
		toolPanel.add(tool_count);
		toolPanel.add(income);
		toolPanel.add(pay);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ�� ���ݶ���ʱ����в���
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();

		Icon start_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_start = new JLabel(start_icon);
		start_time = new DatePicker();
		Icon end_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_end = new JLabel(end_icon);
		end_time = new DatePicker();

		searchPanel.add(label_start);
		searchPanel.add(start_time);
		searchPanel.add(Box.createHorizontalStrut(50));
		searchPanel.add(label_end);
		searchPanel.add(end_time);
		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// Ҫ�����������ѡ�����ɸѡ����(Ҫ����productService�з��ص��н������ã����ѡ��Ҫ���ص���Ŀ����)
		String[] params = { "�������", "����ʱ��", "ʵ�ʽ��", "֧����ʽ���", "֧����ʽ" };
		Vector<Vector> vec = new Vector<>();
		// ��ȡʱ����ѡ��
		String start_time_string = start_time.getText();
		String end_time_string = end_time.getText();
		if (income.isSelected()) {
			if (start_time_string.equals("") && end_time_string.equals("")) {
				try {
					vec = soldNoteService.pack(soldNoteService
							.findAllSoldNote());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				// ����ʱ��ɸѡ������

			}
		} else if (pay.isSelected()) {
			if (start_time_string.equals("") && end_time_string.equals("")) {
				try {
					vec = purchaseNoteService.pack(purchaseNoteService
							.findAllPurchaseNote());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				// ����ʱ��ɸѡ������

			}
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
		// ���أ�0 1 6 7 8 12 13 14 16 17 18 19 21
		// dcm.getColumn(0).setMinWidth(0);
		// dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(3).setMinWidth(0);
		dcm.getColumn(3).setMaxWidth(0);

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
	 * ˢ���������
	 */
	public void refreshTablePanelBySearch() {
		// �Ƴ���ǰ��������е���������
		backgroundPanel.removeAll();
		initTopPanel();
		initTablePanel();
		backgroundPanel.validate();// ��֤
	}

	// /**
	// * ����ʱ��ѡ��������ɸѡ������Ϣ
	// */
	// public Vector<Vector> getSelectContentByTime(Vector<Vector> vec){
	// Vector<Vector> newRows = new Vector<>();
	// String start_time_string = start_time.getText();
	// String end_time_string = end_time.getText();
	// for(int i=0;i<)
	// if(start_time_string.equals("")){
	//
	// }
	// return vec;
	// }

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (income.isSelected()) {
				income.setBackground(new Color(192, 190, 204));
				pay.setBackground(new Color(250, 250, 250));
				backgroundPanel.remove(tablePanel);
				initTablePanel();
				backgroundPanel.validate();// ��֤
			} else if (pay.isSelected()) {
				pay.setBackground(new Color(192, 190, 204));
				income.setBackground(new Color(250, 250, 250));
				backgroundPanel.remove(tablePanel);
				initTablePanel();
				backgroundPanel.validate();// ��֤
			}
		}
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
			// �������ݵ�ͳ��
			if (income.isSelected()) {
				JOptionPane.showMessageDialog(null, "�����룺"+str);
			} else if (pay.isSelected()) {
				JOptionPane.showMessageDialog(null, "��֧����"+str);
			}
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
			cell.setCellValue("����ʱ��");
			cell = row.createCell((short) 2);
			cell.setCellValue("ʵ�ʽ��");
			cell = row.createCell((short) 3);
			cell.setCellValue("֧����ʽ���");
			cell = row.createCell((short) 4);
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
//		String[] ids;
//		ArrayList id_list = new ArrayList<>();
//		for (int rowindex : table.getSelectedRows()) {
//			Object obj = table.getValueAt(rowindex, 0);
//			id_list.add(obj);
//		}
//		// ����ת����
//		ids = (String[]) id_list.toArray(new String[id_list.size()]);
		String str = "";
		double amount = 0.00;
		int[] rows = table.getSelectedRows();
		if(rows.length!=0){
			//��ʾѡ��������
			for(int i=0;i<rows.length;i++){
				amount += Double.valueOf(table.getValueAt(rows[i], 2).toString());
			}
			str= "��ǰѡ�еļ�¼����ĿΪ��"+rows.length+"--�ܶ�ƣ�"+amount;
		}else{
			// Ĭ��ͳ�Ʊ������
			for(int i=0;i<table.getRowCount();i++){
				amount += Double.valueOf(table.getValueAt(i, 2).toString());
			}
			str= "��ǰ�������еļ�¼��ĿΪ��"+table.getRowCount()+"--�ܶ�ƣ�"+amount;
		}
		return str;
	}
}
