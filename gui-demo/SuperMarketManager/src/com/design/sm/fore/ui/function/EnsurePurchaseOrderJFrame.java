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
import com.design.sm.model.PurchaseNote;
import com.design.sm.model.StockMaster;
import com.design.sm.model.StockOrder;
import com.design.sm.model.VendorContact;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.ProductService;
import com.design.sm.service.PurchaseNoteService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.TempService;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.PurchaseNoteServiceImpl;
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

public class EnsurePurchaseOrderJFrame extends JFrame implements MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, tablePanel, ensurePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_master_id, label_order_num, label_handle, label_vendor,
			label_contact, label_time,label_sum;
	JTextField master_id, order_num, handle, vendor;
	JComboBox contact;
	DatePicker time;
	JButton commit, cancel;
	// ������Ӧ��service
	ProductService productService = new ProductServiceImpl();
	TempService tempService = new TempServiceImpl();
	StockMasterService stockMasterService = new StockMasterServiceImpl();
	StockOrderService stockOrderService = new StockOrderServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	VendorContactService vendorContactService = new VendorContactServiceImpl();
	
	Accounts loginUser;
	PurchaseListJPanel parentPanel;
	JTable parentTable;
	int[] selectedRow;
	// ����ȫ�ֱ���
	private String master_id_string, order_num_string, handle_id_string,
			vendor_id_string;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public EnsurePurchaseOrderJFrame(PurchaseListJPanel parentPanel,
			Accounts loginUser, JTable parentTable, int[] selectedRow) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.parentTable = parentTable;
		backgroundPanel = new JPanel(new BorderLayout());
		// ��ʼ������
		initTablePanel();// ��ʼ����ʾ�ı������
		initEnsurePanel();// ��ʼ��ȷ�����
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("����ȷ��");
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
		String[] params = { "��Ʒid", "��Ʒ����", "��������", "��Ʒ����", "�ܶ�", "��Ӧ��id",
				"��Ӧ��" };
		Vector<Vector> vec = new Vector<>();
		// ��ȡ��ǰѡ�е�����
		String[] ids;
		ArrayList id_list = new ArrayList<>();
		for (int rowindex : parentTable.getSelectedRows()) {
			Object obj = parentTable.getValueAt(rowindex, 0);
			id_list.add(obj);
		}
		// ����ת����
		ids = (String[]) id_list.toArray(new String[id_list.size()]);
		try {
			vec = tempService.pack(tempService.getTempListByProductId(ids));
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
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);

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
		String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		// ��ǰʱ��
		time = new DatePicker(new Date(), DefaultFormat,
				MyFont.JTextFieldFont, new Dimension(177, 24));
//		time.setSize(new Dimension(150, 50));
		
		label_contact = new JLabel("������  ");
		contact = new JComboBox();
		contact.setPreferredSize(new Dimension(175, 30));
		
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
		
		commit = new JButton("ȷ��");
		commit.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
		commit.setSize(50, 30);
		commit.addMouseListener(this);

		cancel = new JButton("ȡ��");
		cancel.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		cancel.setSize(50, 30);
		cancel.addMouseListener(this);
		east.add(label_sum);
		east.add(commit);
		east.add(cancel);
		
		// ��ͳ�ƺ����Ϣ���л���
		this.echoData();
		// �������ӵ����������
		ensurePanel.add(ver, BorderLayout.WEST);
		ensurePanel.add(east, BorderLayout.CENTER);
		backgroundPanel.add(ensurePanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == commit) {
			try {
				/**
				 * ���뱣֤�Ƕ�Ӧ�˺ŵ���ӦԱ����¼���в��������������Ӧ�˺ŵ�Ա����Ϣ�����ھͻ���Ӧ��
				 * ���쳣����Ҫ�������ݿ�������ݲ��Ե�ʱ��û�п���ȫ��
				 */
				/**
				 * �����ύ��
				 * 1.������������
				 * 2.���붩����ϸ
				 * 3.�Ƴ���ʱ����������
				 * 4.ˢ���������
				 */
				// ��ȡ��Ӧ����Ϣ
				String contact_id =( (Item)contact.getSelectedItem()).getKey();
				String handle_time_string = time.getText();
				// �ύ�����ɶ�����������ʱ���е�����������Ӧ�Ķ������������ʱ���е����ύ�Ķ���
				// ������һ����������(0��⡢ɾ����ʶ����������״̬)
				StockMaster sm = new StockMaster(master_id_string, order_num_string, 
						handle_id_string, vendor_id_string, contact_id, handle_time_string, 0, 0, 0);
					stockMasterService.addStockMaster(sm);
				// �������ɵĶ������붩����ϸ(��ȡ�������)
				for(int i=0;i<table.getRowCount();i++){
					String prod_id_string = table.getValueAt(i, 0).toString();
					int quantity_int = Integer.valueOf(table.getValueAt(i, 2).toString());
					double unit_price_double = Double.valueOf(table.getValueAt(i, 3).toString());
					double amount_double = Double.valueOf(table.getValueAt(i, 4).toString());
					StockOrder so = new StockOrder(master_id_string, prod_id_string, quantity_int, unit_price_double, amount_double, 0);
					stockOrderService.addStockOrder(so);
				}
				// �����Ƴ���ʱ�ɹ������Ķ�������
				for(int i=0;i<table.getRowCount();i++){
					String prod_id_string = table.getValueAt(i, 0).toString();
					tempService.deleteTemp(prod_id_string);
				}
				// ����ҳ�棬�����ʾ
				this.setVisible(false);
				JOptionPane.showMessageDialog(null, "������Ϣ�ύ�ɹ���");
				parentPanel.refreshTablePanel();
			} catch (NumberFormatException | SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == cancel) {
			// ȡ�����������ص�ǰ���
			this.setVisible(false);
		}
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
	 * ͳ����Ϣ��������Ϣ���ԣ�
	 */
	public void echoData() {
		try {
			// ����id�������32char��id���С�һ��ȷ�����ܹ��������
			master_id_string = RandomGeneration.getRandom32charSeq();
			// ������Ÿ��ݵ�ǰ���е���(�����ݿ��л�ȡ)
			order_num_string = stockMasterService.getStockInNextSeq();
			// ����idΪ��ǰ��¼��Ա���˺ŵ�Ա��id(ͨ����ǰԱ���˺Ż�ȡ)
			String accountId = this.loginUser.getAccount_id();
			handle_id_string = employeeService
					.getEmployeeIdByAccountId(accountId);
			// ��Ӧ��id��Ӧ�����ɶ����Ĺ�Ӧ��id
			vendor_id_string = table.getValueAt(0, 5).toString();// ÿһ�����ݶ��޶�ͬһ����Ӧ�̲������ɶ���

			// �����ı�����
			master_id.setText(master_id_string);
			master_id.setToolTipText(master_id_string);
			order_num.setText(order_num_string);
			order_num.setToolTipText(order_num_string);
			handle.setText(String.valueOf(employeeService
					.getEmployeeName(handle_id_string)));
			handle.setToolTipText(String.valueOf(employeeService
					.getEmployeeName(handle_id_string)));
			vendor.setText(table.getValueAt(0, 6).toString());
			vendor.setToolTipText(table.getValueAt(0, 6).toString());
			// ���ݵ�ǰ�Ĺ�Ӧ�̼�����Ӧ����ϵ����Ϣ
			List<VendorContact> list_contact = vendorContactService
					.getVendorContactByVendorId(vendor_id_string);
			for (VendorContact vc : list_contact) {
				String vcId = vc.getContact_id();
				String vcName = vc.getContact_name();
				Item item = new Item(vcId, vcName);
				contact.addItem(item);
			}
			
			// ͳ�Ƶ�ǰ������������Ʒ�ܶ�
			double sum = 0;
			for(int i=0;i<table.getRowCount();i++){
				sum += Double.valueOf(table.getValueAt(i, 4).toString());
			}
			label_sum.setText("�ܽ�"+sum);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
