package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Accounts;
import com.design.sm.model.StockMaster;
import com.design.sm.model.StockOrder;
import com.design.sm.model.VendorContact;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.ProductService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.TempService;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.service.impl.TempServiceImpl;
import com.design.sm.service.impl.VendorContactServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;
import com.design.sm.utils.Tools;
import com.eltima.components.ui.DatePicker;

public class ShowStockInMasterDetail extends JFrame {
	// ����ȫ�����
	JPanel backgroundPanel, tablePanel, ensurePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_master_id, label_order_num, label_handle, label_vendor,
			label_contact, label_time, label_sum;
	JTextField master_id, order_num, handle, vendor, contact, time;
	// ������Ӧ��service
	ProductService productService = new ProductServiceImpl();
	StockMasterService stockMasterService = new StockMasterServiceImpl();
	StockOrderService stockOrderService = new StockOrderServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	VendorContactService vendorContactService = new VendorContactServiceImpl();

	Accounts loginUser;
	PurchaseRecordJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public ShowStockInMasterDetail(PurchaseRecordJPanel parentPanel,
			Accounts loginUser, JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.parentTable = parentTable;
		backgroundPanel = new JPanel(new BorderLayout());
		// ��ʼ������
		initTablePanel();// ��ʼ����ʾ�ı������
		initEnsurePanel();// ��ʼ��ȷ�����
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("��������");
		this.setSize(725, 500);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// ����������
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// ���ùرշ�ʽ
		// ��ǰ�������أ���Ӱ������ݵ�ʹ�ã������ǹر���������

	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// ��������ݽ��в���
		String[] params = { "����id", "�������", "��Ʒid", "��Ʒ����", "��������", "��Ʒ����",
				"�ܶ�", "��Ӧ��id", "��Ӧ��" };
		Vector<Vector> vec = new Vector<>();
		// ��ȡ��ǰ����id
		String smId = parentTable.getValueAt(selectedRow, 0).toString();
		try {
			vec = stockOrderService.pack(stockOrderService
					.getStockOrderBySMId(smId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ����ѯ�������ݷ�װ��BbaseTableModule��
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// �����ṩ��Tools���������
		Tools.setTableStyle(table);
		// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// ���أ�5
		dcm.getColumn(0).setMinWidth(0);
		dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(2).setMinWidth(0);
		dcm.getColumn(2).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);

		// ���ù�����
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// ����͸����
		tablePanel.add(jScrollPane);
		// ��������ص�������
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * ��ʼ��ȷ�����
	 */
	private void initEnsurePanel() {
		ensurePanel = new JPanel(new BorderLayout());

		JPanel jp1 = new JPanel();
		label_master_id = new JLabel("����id  ");
		master_id = new JTextField(28);
		master_id.setEditable(false);

		label_handle = new JLabel("������  ");
		handle = new JTextField(28);
		handle.setEditable(false);

		jp1.add(label_master_id);
		jp1.add(master_id);
		jp1.add(label_handle);
		jp1.add(handle);

		JPanel jp2 = new JPanel();
		label_order_num = new JLabel("�������");
		order_num = new JTextField(28);
		order_num.setEditable(false);

		label_vendor = new JLabel("��Ӧ��  ");
		vendor = new JTextField(28);
		vendor.setEditable(false);

		jp2.add(label_order_num);
		jp2.add(order_num);
		jp2.add(label_vendor);
		jp2.add(vendor);

		JPanel jp3 = new JPanel();
		label_time = new JLabel("����ʱ��");
		time = new JTextField(28);
		time.setEditable(false);

		label_contact = new JLabel("������  ");
		contact = new JTextField(28);
		contact.setEditable(false);

		jp3.add(label_time);
		jp3.add(time);
		jp3.add(label_contact);
		jp3.add(contact);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

		JPanel east = new JPanel(new GridLayout(3, 1));
		label_sum = new JLabel();

		east.add(label_sum);

		// ��ͳ�ƺ����Ϣ���л���
		this.echoData();
		// �������ӵ����������
		ensurePanel.add(ver, BorderLayout.WEST);
		ensurePanel.add(east, BorderLayout.CENTER);
		backgroundPanel.add(ensurePanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}

	/**
	 * ͳ����Ϣ��������Ϣ���ԣ�
	 */
	public void echoData() {
		// ���ݱ�����ݻ�ȡ��Ӧ����Ϣ
		String master_id_string = parentTable.getValueAt(selectedRow, 0)
				.toString();
		String order_num_string = parentTable.getValueAt(selectedRow, 1)
				.toString();
		String handle_string = parentTable.getValueAt(selectedRow, 3)
				.toString();
		String vendor_string = parentTable.getValueAt(selectedRow, 5)
				.toString();
		String contact_string = parentTable.getValueAt(selectedRow, 7)
				.toString();
		String time_string = parentTable.getValueAt(selectedRow, 8).toString();

		// �����ı�����
		master_id.setText(master_id_string);
		master_id.setToolTipText(master_id_string);
		order_num.setText(order_num_string);
		order_num.setToolTipText(order_num_string);
		handle.setText(handle_string);
		handle.setToolTipText(handle_string);
		vendor.setText(vendor_string);
		vendor.setToolTipText(vendor_string);
		contact.setText(contact_string);
		contact.setToolTipText(contact_string);
		time.setText(time_string);
		time.setToolTipText(time_string);
		// ͳ�Ƶ�ǰ������������Ʒ�ܶ�
		double sum = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			sum += Double.valueOf(table.getValueAt(i, 6).toString());
		}
		label_sum.setText("�ܽ�" + sum);
	}
}
