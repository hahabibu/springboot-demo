package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.dao.impl.SaleOrderServiceImpl;
import com.design.sm.fore.ui.control.SaleMasterJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.ConsumeClass;
import com.design.sm.model.Customer;
import com.design.sm.model.SaleMaster;
import com.design.sm.model.SaleOrder;
import com.design.sm.model.SoldNote;
import com.design.sm.service.AccountsService;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.service.CustomerService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.ProductService;
import com.design.sm.service.SaleMasterService;
import com.design.sm.service.SaleOrderService;
import com.design.sm.service.SaleTempService;
import com.design.sm.service.SoldNoteService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.service.impl.CustomerServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.SaleMasterServiceImpl;
import com.design.sm.service.impl.SaleTempServiceImpl;
import com.design.sm.service.impl.SoldNoteServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.service.impl.VendorContactServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.RandomGeneration;
import com.design.sm.utils.Tools;

public class ShowSaleMasterDetail extends JFrame{
	// ����ȫ�����
	JPanel backgroundPanel, buttonPanel,tablePanel, ensurePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_master_id, label_order_num, label_handle, label_cust,
			label_contact, label_time, label_concession,label_actual_sum,label_sum;
	JTextField master_id, order_num, handle, cust, time, concession;
	JButton commit, cancel;
	double actual_sum;
	// ������Ӧ��service
	SaleMasterService saleMasterService = new SaleMasterServiceImpl();
	SaleOrderService saleOrderService = new SaleOrderServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();
	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();
	SoldNoteService soldNoteService = new SoldNoteServiceImpl();
	
	Accounts loginUser;
	SaleMasterJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// ����ȫ�ֱ���
	private String master_id_string, order_num_string, handle_id_string,
			cust_id_string;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public ShowSaleMasterDetail(SaleMasterJPanel parentPanel, Accounts loginUser,
			JTable parentTable,int selectedRow) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
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
		String[] params = { "��Ʒid", "�������","��Ʒid","��Ʒ����", "����", "�ۼ�", "С��", "��Ӧ��id", "��Ӧ��" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = saleOrderService.pack(saleOrderService.getSaleOrderBySMId(parentTable.getValueAt(selectedRow, 0).toString()));
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
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);

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

		label_cust = new JLabel("�˿�����");
		cust = new JTextField(28);
		cust.setEditable(false);

		jp2.add(label_order_num);
		jp2.add(order_num);
		jp2.add(label_cust);
		jp2.add(cust);

		JPanel jp3 = new JPanel();
		label_time = new JLabel("����ʱ��");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		time = new JTextField(28);
		time.setText(df.format(new Date()));
		label_concession = new JLabel("�Żݺϼ�");
		concession = new JTextField(28);
		concession.setEditable(false);

		jp3.add(label_time);
		jp3.add(time);
		jp3.add(label_concession);
		jp3.add(concession);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

		JPanel east = new JPanel(new GridLayout(2, 1));
		label_sum = new JLabel();
		label_actual_sum = new JLabel();
		east.add(label_sum);
		east.add(label_actual_sum);
		

		// ��ͳ�ƺ����Ϣ���л���
		this.echoData();
		// �������ӵ����������
		ensurePanel.add(ver, BorderLayout.WEST);
		ensurePanel.add(east, BorderLayout.CENTER);
		backgroundPanel.add(ensurePanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}


	/**
	 * ˢ���������
	 */
	public void refreshTablePanel() {
		// �Ƴ���ǰ��������е���������
		backgroundPanel.removeAll();
		initTablePanel();
		initEnsurePanel();
		backgroundPanel.validate();// ��֤
	}

	/**
	 * ͳ����Ϣ��������Ϣ���ԣ�
	 */
	public void echoData() {
		try {
			// ����id�������32char��id���С�һ��ȷ�����ܹ��������
			master_id_string = parentTable.getValueAt(selectedRow, 0).toString();
			// ������Ÿ��ݵ�ǰ���е���(�����ݿ��л�ȡ)
			order_num_string = parentTable.getValueAt(selectedRow, 1).toString();
			String handle_string = parentTable.getValueAt(selectedRow, 3).toString();
			String customer_string = parentTable.getValueAt(selectedRow, 4).toString();
			// �����ı�����
			master_id.setText(master_id_string);
			master_id.setToolTipText(master_id_string);
			order_num.setText(order_num_string);
			order_num.setToolTipText(order_num_string);
			handle.setText(handle_string);
			handle.setToolTipText(handle_string);
			Customer c = customerService.getCustomerById(parentTable.getValueAt(selectedRow, 4).toString());
			if (c != null) {
				cust.setText(c.getCustomer_name());
				cust.setToolTipText(c.getCustomer_name());
				cust_id_string = c.getCustomer_id();
				// ͳ�Ƶ�ǰ������������Ʒ�ܶ�
				double sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					sum += Double.valueOf(table.getValueAt(i, 6).toString());
				}
				/**
				 *  ���ݲ�Ʒ�Ķ����Ż�ȡ����ʵ��֧���Ľ�Ȼ����ʵ���ܶ�-ʵ�ʽ���ȡ�Żݼ۸�
				 *  �����ٴμ��㣬��Ϊ����ٴ�ͨ����ȡ���ݿ����Ϣ���м��㣬�п��ܻ���ֹ˿��˺�
				 *  �ȼ����������в�ͬ���Ż��������Ӷ����������ݵ�ʱ������Ĵ���
				 */
				SoldNote sn = soldNoteService.getSoldNoteByNum(order_num_string);
				double actual_sum = sn.getActual_amount();
				// ���յ��ܽ��Ϊ��ǰ������Ʒ�ܶ��ȥ�Żݺϼ�
				double concession_dobule = sum-actual_sum;
				concession.setText(concession_dobule+"");
				label_sum.setText("ʵ����" + actual_sum);
				label_actual_sum.setText("�ܽ�"+sum);
			} else {
				// ����vip�˿�
				cust.setText("��");
				cust.setToolTipText("��");
				cust_id_string = null;
				// ͳ�Ƶ�ǰ������������Ʒ�ܶ�
				double sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					sum += Double.valueOf(table.getValueAt(i, 4).toString());
				}
				concession.setText("0.00");
				actual_sum = sum;
				label_sum.setText("ʵ����" + actual_sum);
				label_actual_sum.setText("�ܽ�"+sum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}