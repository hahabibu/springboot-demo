package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.fore.ui.control.ProductSaleJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.ConsumeClass;
import com.design.sm.model.Customer;
import com.design.sm.model.Employee;
import com.design.sm.model.Product;
import com.design.sm.model.SaleTemp;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.service.CustomerService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.ProductService;
import com.design.sm.service.SaleTempService;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.service.impl.CustomerServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.SaleTempServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class CashierJFrame extends JFrame implements MouseListener,
		DocumentListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, centerPanel, tempTablePanel,
			searchPanel, downPanel, recordPanel, buttonPanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule tempTableModule, searchTableModule;
	// ������ʱ��¼�����Ʒ���ұ��
	JTable tempTable, searchTable;
	JScrollPane tempJScrollPane, searchJScrollPane;
	// ����topPanelʹ�õ��ı�ǩ
	JLabel label_unit_price, label_quantity, label_subtotal, label_total,
			unit_price, quantity, subtotal, total;
	// ����recordPanelʹ�õ��ı�ǩ
	JLabel label_time, label_cashier_num, label_cashier, label_customer_sign,
			label_customer, label_consume_class, label_discount, label_off,
			label_concession;
	// ����������ʹ�õ��ı�ǩ
	JLabel tool_add, tool_sub, tool_delete, tool_truncate,barcode;

	// �����������ı���(���������������������Ʒ����)
	JTextField keyword;
	// ���尴ť����
	// ���ý𡢻�Ա���ش򡢽���
	JButton petty_cash, vip, reset, balance;
	// �����Ա
	double petty_cash_double=0.00;
	Customer customer = null ;

	// ������Ӧ��service
	// AccountsService accountsService = new AccountsServiceImpl();
	ProductService productService = new ProductServiceImpl();
	SaleTempService saleTempService = new SaleTempServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();
	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();
	
	Accounts loginUser;
	ProductSaleJPanel parentPanel;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public CashierJFrame(ProductSaleJPanel parentPanel, Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵�����������
		initCenterPanel();// ��ʼ���м���������
		initDownPanel();// ��ʼ���·��ļ�¼�������Ͱ�ť���
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("����̨");
		this.pack();
		this.setResizable(false);// �������
		this.setVisible(true);
		this.setLocationRelativeTo(null);// ����������
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// ���ùرշ�ʽ
		// ��ǰ�������أ���Ӱ������ݵ�ʹ�ã������ǹر���������
	}

	/**
	 * ��ʼ�������Ĳ˵���
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());

		label_unit_price = new JLabel("����");
		label_unit_price.setForeground(MyColor.TipColor);
		unit_price = new JLabel("0.00");
		unit_price.setForeground(MyColor.JTextFieldColor);
		unit_price.setFont(MyFont.JTableFont);

		label_quantity = new JLabel("����");
		label_quantity.setForeground(MyColor.TipColor);
		quantity = new JLabel("0");
		quantity.setForeground(MyColor.JTextFieldColor);
		quantity.setFont(MyFont.JTableFont);

		label_subtotal = new JLabel("С��");
		label_subtotal.setForeground(MyColor.TipColor);
		subtotal = new JLabel("0.00");
		subtotal.setForeground(MyColor.JTextFieldColor);
		subtotal.setFont(MyFont.JTableFont);

		label_total = new JLabel("�ܼ�");
		label_total.setForeground(MyColor.TipColor);
		total = new JLabel("0.00");
		total.setForeground(MyColor.JTextFieldColor);
		total.setFont(MyFont.JTableFont);

		Box hor1 = Box.createHorizontalBox();
		hor1.add(Box.createHorizontalStrut(100));
		hor1.add(label_unit_price);
		hor1.add(Box.createHorizontalStrut(100));
		hor1.add(label_quantity);
		hor1.add(Box.createHorizontalStrut(100));
		hor1.add(label_subtotal);
		hor1.add(Box.createHorizontalStrut(100));
		hor1.add(label_total);

		Box hor2 = Box.createHorizontalBox();
		hor2.add(Box.createHorizontalStrut(100));
		hor2.add(unit_price);
		hor2.add(Box.createHorizontalStrut(100));
		hor2.add(quantity);
		hor2.add(Box.createHorizontalStrut(100));
		hor2.add(subtotal);
		hor2.add(Box.createHorizontalStrut(100));
		hor2.add(total);

		toolPanel = new JPanel(new BorderLayout());
		// �����ɾ�Ĺ���
		Icon icon_add = new ImageIcon("icons/toolImage/increase.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("����+1");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_sub = new ImageIcon("icons/toolImage/sub.png");
		tool_sub = new JLabel(icon_sub);
		tool_sub.setToolTipText("����-1");// ��������ƶ�ʱ����ʾ����
		tool_sub.addMouseListener(this);// ���������

		Icon icon_slash = new ImageIcon("icons/toolImage/slash.png");
		tool_delete = new JLabel(icon_slash);
		tool_delete.setToolTipText("ɾ����¼");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		Icon icon_truncate = new ImageIcon("icons/toolImage/truncate.png");
		tool_truncate = new JLabel(icon_truncate);
		tool_truncate.setToolTipText("��ռ�¼");// ��������ƶ�ʱ����ʾ����
		tool_truncate.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		JPanel west = new JPanel();
		west.add(tool_add);
		west.add(tool_sub);
		west.add(tool_delete);
		west.add(tool_truncate);
		JPanel east = new JPanel();
		barcode = new JLabel();
		Icon barcode_icon = new ImageIcon("icons/toolImage/barcode.png");
		barcode.setIcon(barcode_icon);
		keyword = new JTextField(20);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("������|");
		keyword.setPreferredSize(new Dimension(150,30));
		keyword.addMouseListener(this);
		// �ı�������ʵʱ���ݼ�ػ�ȡ��Ʒ��Ϣ
		keyword.getDocument().addDocumentListener(this);
		east.add(barcode);
		east.add(keyword);

		toolPanel.add(west, BorderLayout.WEST);
		toolPanel.add(east, BorderLayout.EAST);

		topPanel.add(hor1, BorderLayout.NORTH);
		topPanel.add(hor2, BorderLayout.CENTER);
		topPanel.add(toolPanel, BorderLayout.SOUTH);

		// �����������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ���м���������
	 */
	private void initCenterPanel() {
		centerPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		// ��ʼ����ʱ���ݱ�������Ʒ¼���¼��
		initTempTablePanel();
		// ��ʼ����Ʒ���ұ�
		initSearchPanel();
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);

	}

	/**
	 * ��ʼ���·����������
	 */
	private void initDownPanel() {
		downPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		// ��ʼ�����·���������ʾ��¼��
		initRecordPanel();
		// ��ʼ����ť���
		initButtonPanel();
		backgroundPanel.add(downPanel, BorderLayout.SOUTH);
	}

	/**
	 * ��ʼ����ʱ��Ʒ���ۼ�¼��
	 */
	private void initTempTablePanel() {
		tempTablePanel = new JPanel();
		String[] params = { "��Ʒid", "��Ʒ����", "����", "�ۼ�", "С��", "��Ӧ��id", "��Ӧ��" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = saleTempService.pack(saleTempService.findAllSaleTempList());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ����ѯ�������ݷ�װ��BbaseTableModule��
		tempTableModule = new BaseTableModule(params, vec);
		tempTable = new JTable(tempTableModule);
		// ��Ⱦ��0�У�������ʾΪ��ѡ�������ʾ
		tempTable.getColumnModel().getColumn(0)
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
		Tools.setTableStyle(tempTable);
		// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) tempTable
				.getColumnModel();
		// ���أ�5
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);
		// ���ù�����
		tempJScrollPane = new JScrollPane(tempTable);
		Tools.setJspStyle(tempJScrollPane);

		tempTablePanel.setOpaque(false);// ����͸����
		tempTablePanel.add(tempJScrollPane);
		centerPanel.add(tempTablePanel, BorderLayout.WEST);
		// ��������ص�������
		backgroundPanel.validate();
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ�� ��Ʒ����������Ʒ�ؼ��ֲ���
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		// Ҫ�����������ѡ�����ɸѡ����(Ҫ����productService�з��ص��н������ã����ѡ��Ҫ���ص���Ŀ����)
		String[] params = { "��Ʒid", "������", "��Ʒ����", "�ɱ�", "�ۼ�", "���ϼ�", "�ֿ���",
				"��ȫ���", "��λid", "��λ", "����", "��������", "��Ʒ����", "�ۿ�", "������ʶ",
				"����״̬", "�����۸�", "ɾ����ʶ", "����id", "����", "��Ӧ��id", "��Ӧ��", "�ֿ�id",
				"�ֿ�" };
		Vector<Vector> vec = new Vector<>();
		if (keyword.getText().equals("������|")) {
			// ͨ���ؼ���������Ʒ��Ϣ����ȡ����������������Ʒ�б�
			try {
				// �������е���Ʒ��Ϣ
				List<Product> prod_list = productService.findAllProductList();
				// ����ɸѡ�����������������ϼܵĸ���Ʒ��Ϣ
				vec = productService.pack(getProductPutOn(prod_list));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			String text = "%" + keyword.getText() + "%";
			// ͨ���ؼ���������Ʒ��Ϣ����ȡ����������������Ʒ�б�
			try {
				List<Product> prod_list = productService
						.getProductByFlowIdUnionName(text);
				// ����ɸѡ�����������������ϼܵĸ���Ʒ��Ϣ
				vec = productService.pack(getProductPutOn(prod_list));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		// ����ѯ�������ݷ�װ��BbaseTableModule��
		searchTableModule = new BaseTableModule(params, vec);
		searchTable = new JTable(searchTableModule);
		// ��Ⱦ��0�У�������ʾΪ��ѡ�������ʾ
		searchTable.getColumnModel().getColumn(0)
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
		Tools.setTableStyle(searchTable);
		// Ϊ������������
		searchTable.addMouseListener(this);
		// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) searchTable
				.getColumnModel();
		// ���أ�0 1 6 7 8 12 13 14 16 17 18 19 21
		dcm.getColumn(0).setMinWidth(0);
		dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(3).setMinWidth(0);
		dcm.getColumn(3).setMaxWidth(0);
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(9).setMinWidth(0);
		dcm.getColumn(9).setMaxWidth(0);
		dcm.getColumn(10).setMinWidth(0);
		dcm.getColumn(10).setMaxWidth(0);
		dcm.getColumn(11).setMinWidth(0);
		dcm.getColumn(11).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(13).setMinWidth(0);
		dcm.getColumn(13).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);
		dcm.getColumn(17).setMinWidth(0);
		dcm.getColumn(17).setMaxWidth(0);
		dcm.getColumn(18).setMinWidth(0);
		dcm.getColumn(18).setMaxWidth(0);
		dcm.getColumn(19).setMinWidth(0);
		dcm.getColumn(19).setMaxWidth(0);
		dcm.getColumn(20).setMinWidth(0);
		dcm.getColumn(20).setMaxWidth(0);
		dcm.getColumn(21).setMinWidth(0);
		dcm.getColumn(21).setMaxWidth(0);
		dcm.getColumn(22).setMinWidth(0);
		dcm.getColumn(22).setMaxWidth(0);
		dcm.getColumn(23).setMinWidth(0);
		dcm.getColumn(23).setMaxWidth(0);

		// ���ù�����
		searchJScrollPane = new JScrollPane(searchTable);
		Tools.setJspStyle(searchJScrollPane);

		searchPanel.setOpaque(false);// ����͸����
		searchPanel.add(searchJScrollPane);
		// ��ˢ�����ݵ�ʱ��ı䴰���С���������ˢ��
		centerPanel.add(searchPanel, BorderLayout.EAST);
		backgroundPanel.validate();

	}

	/**
	 * ��ʼ����Ϣ��¼��
	 */
	private void initRecordPanel() {
		recordPanel = new JPanel(new GridLayout(1, 3));

		JPanel jp1 = new JPanel(new GridLayout(3, 1));
		// ��ȡ��ǰϵͳʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		label_time = new JLabel("ʱ��:"+df.format(new Date()));
		Employee findemp = null;
		try {
			findemp = employeeService.getEmployeeById(employeeService.getEmployeeIdByAccountId(this.loginUser.getAccount_id()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		label_cashier_num = new JLabel("����:"+findemp.getEmployee_num());
		label_cashier = new JLabel("����Ա:"+findemp.getEmployee_name());
		jp1.add(label_time);
		jp1.add(label_cashier_num);
		jp1.add(label_cashier);

		JPanel jp2 = new JPanel(new GridLayout(3, 1));
		label_customer_sign = new JLabel("��Ա��ʶ:�ǻ�Ա");
		label_customer = new JLabel("��Ա����");
		label_consume_class = new JLabel("��Ա�ȼ�");
		jp2.add(label_customer_sign);
		jp2.add(label_customer);
		jp2.add(label_consume_class);

		JPanel jp3 = new JPanel(new GridLayout(3, 1));
		label_discount = new JLabel("�ۿ�");
		label_off = new JLabel("�Ż�");
		label_concession = new JLabel("�Żݺϼ�");
		jp3.add(label_discount);
		jp3.add(label_off);
		jp3.add(label_concession);

		recordPanel.add(jp1);
		recordPanel.add(jp2);
		recordPanel.add(jp3);

		downPanel.add(recordPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ����ť���
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		// ��Ӱ�ť����
		petty_cash = new JButton("���ý�");
		petty_cash.setSize(75, 30);
		petty_cash.addMouseListener(this);// ���������
		petty_cash.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));

		vip = new JButton("��Ա");
		vip.setSize(75, 30);
		vip.addMouseListener(this);// ���������
		vip.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));

		reset = new JButton("�ش�");
		reset.setSize(75, 30);
		reset.addMouseListener(this);// ���������
		reset.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));

		balance = new JButton("����");
		balance.setSize(75, 30);
		balance.addMouseListener(this);// ���������
		balance.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));

		// ����ʼ����ɵĹ��߼��ص������������
		buttonPanel.add(petty_cash);
		buttonPanel.add(vip);
		buttonPanel.add(reset);
		buttonPanel.add(balance);
		// ���ս������������ص������˵�����������
		downPanel.add(buttonPanel, BorderLayout.EAST);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == searchTable) {
			try {
				// ���˫���˱��������ָ����Ʒ��ӵ���ʱ��¼���嵥��
				if (e.getClickCount() == 2) {
					// ����ѡ�еı�����ݻ�ȡ��Ʒ����Ϣ
					int row = searchTable.getSelectedRow();
					if (row >= 0) {
						/**
						 * ��Ҫ�жϵ�ǰ��Ʒ�Ƿ�Ϊ����״̬�����ߴ���״̬�� �������۰����ۼ�*��Ӧ���ۿ۽��г���
						 * ���Ϊ����״̬���մ����ļ۸���г���
						 * �������Ʒ��ʱ����Ҫ�жϵ�ǰ����Ʒ�Ѵ�������ʱ�嵥�У����������ֻ��Ҫ�޸���Ӧ��
						 * �������ܼۣ��������������ݵ�ǰ��Ʒ�ĳ���״̬�����Ʒ��Ϣ
						 * ������Ҫע����Ǳ����Ǵ����ϼܵ���Ʒ�в�����Ʒ��Ϣ �ҹ������Ʒ��Ŀ���ܹ�������Ʒ�����ϼܿ��
						 */
						String prod_id = searchTable.getValueAt(row, 0)
								.toString();
						double unit_price = Double.valueOf(searchTable
								.getValueAt(row, 4).toString());
						int putaway_stock = Integer.valueOf(searchTable
								.getValueAt(row, 5).toString());
						double prod_discount = Double.valueOf(searchTable
								.getValueAt(row, 13).toString());
						int promotion_flag = Integer.valueOf(searchTable
								.getValueAt(row, 14).toString());
						double promotion_price = Double.valueOf(searchTable
								.getValueAt(row, 16).toString());
						double sale_price = 0.00;
						if (promotion_flag == 0) {
							// �������� �������Ӧ���ۿ���Ϊ���յ��ۼ�
							sale_price = ((int) (unit_price * prod_discount * 100)) / 100;
						} else if (promotion_flag == 1) {
							// �������մ����۸��������
							sale_price = promotion_price;
						}
						// �жϵ�ǰ��ʱ�����Ƿ���ڸ���Ʒ��Ϣ�����������ֻ���޸�����������������������Ʒ��ˢ�����
						SaleTemp st = saleTempService
								.getSaleTempByProductId(prod_id);
						if (st != null) {
							// �жϵ�ǰ����Ʒ��Ŀ��1֮���Ƿ񳬳����ϼ���Ŀ�����������������Ӧ����ʾ
							if ((st.getQuantity() + 1) > putaway_stock) {
								JOptionPane.showMessageDialog(null,
										"��ǰ�ӹ���Ŀ������Ʒ���ϼܿ�棬���������");
							} else {
								// ������ҵ���Ʒ��Ϊ�գ���ֻ��Ҫ��Ӧ���޸���Ŀ����Ŀ��1�����ܶ��
								st.setQuantity(st.getQuantity() + 1);
								double amount = ((int) (st.getQuantity()
										* sale_price * 100)) / 100;
								st.setAmount(amount);
								saleTempService.update(st);
							}
						} else {
							// ������ҵ���ƷΪ�գ�����Ӹ��ݵ�ǰ��Ʒ��״̬�����Ʒ��Ϣ
							SaleTemp st1 = new SaleTemp(prod_id, 1, sale_price,
									sale_price);
							saleTempService.addSaleTemp(st1);
						}
						// �Ϸ����������ˢ����ʾ�����ۡ�������С�ơ��ܼ�
						this.refreshTopPanel(sale_price);
						// ˢ��������Ϣ
						this.refreshCenterPanel();
						this.refreshDownPanel();
					}
				}
			} catch (NumberFormatException | HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==tool_add){
			try {
				int row = tempTable.getSelectedRow();
				if(row>=0){
					SaleTemp findst = saleTempService.getSaleTempByProductId(tempTable.getValueAt(row, 0).toString());
					Product findprod = productService.getProduct(tempTable.getValueAt(row, 0).toString());
					if(findst.getQuantity()+1>findprod.getPutaway_stock()){
						// �ӹ���Ŀ������ǰ��Ʒ�ϼܿ�棬����г�����ʾ
						JOptionPane.showMessageDialog(null, "��ǰ�ӹ���Ŀ������Ʒ���ϼܿ�棬���������");
					}else{
						// ������ҵ���Ʒ��Ϊ�գ���ֻ��Ҫ��Ӧ���޸���Ŀ����Ŀ��1�����ܶ��
						findst.setQuantity(findst.getQuantity() + 1);
						double amount = ((int) (findst.getQuantity()
								* findst.getUnit_price() * 100)) / 100;
						findst.setAmount(amount);
						saleTempService.update(findst);
					}
					// ˢ��������Ϣ
					this.refreshCenterPanel();
					this.refreshTopPanel(0);
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==tool_sub){
			try {
				int row = tempTable.getSelectedRow();
				if(row>=0){
					SaleTemp findst = saleTempService.getSaleTempByProductId(tempTable.getValueAt(row, 0).toString());
					Product findprod = productService.getProduct(tempTable.getValueAt(row, 0).toString());
					if(findst.getQuantity()-1<=0){
						findst.setQuantity(1);
						double amount = ((int) (findst.getQuantity()
								* findst.getUnit_price() * 100)) / 100;
						findst.setAmount(amount);
						saleTempService.update(findst);
					}else{
						findst.setQuantity(findst.getQuantity() - 1);
						double amount = ((int) (findst.getQuantity()
								* findst.getUnit_price() * 100)) / 100;
						findst.setAmount(amount);
						saleTempService.update(findst);
					}
					// ˢ��������Ϣ
					this.refreshCenterPanel();
					this.refreshTopPanel(0);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==tool_delete){
			try {
				int row = tempTable.getSelectedRow();
				if(row>=0){
					int choose = JOptionPane.showConfirmDialog(null, "ȷ���Ƴ��ü�¼��");
					if(choose==0){
						saleTempService.deleteSaleTemp(tempTable.getValueAt(row, 0).toString());
						JOptionPane.showMessageDialog(null, "��Ʒ��¼���Ƴ���");
						// ˢ���������
						this.refreshCenterPanel();
						if(tempTable.getRowCount()==0){
							backgroundPanel.remove(topPanel);
							initTopPanel();
							backgroundPanel.validate();
						}else{
							// �����ָ���쳣
							this.refreshTopPanel(0);
						}
					}
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==tool_truncate){
			try {
				int row = tempTable.getSelectedRow();
				if(row>=0){
					int choose = JOptionPane.showConfirmDialog(null, "ȷ���Ƴ�������Ʒ��¼��");
					if(choose==0){
						saleTempService.truncateAllSaleTemp();
						JOptionPane.showMessageDialog(null, "������Ʒ��¼���Ƴ���");
						// ˢ���������
						this.refreshCenterPanel();
						backgroundPanel.remove(topPanel);
						initTopPanel();
						backgroundPanel.validate();
					}
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==petty_cash){
			// ���뱸�ý�
			String cash = JOptionPane.showInputDialog(null, "�����뱸�ý�");
			if(cash!=null){
				if(DataValidation.isBigDecimal(cash)){
					petty_cash_double = Double.valueOf(cash);
				}else{
					JOptionPane.showMessageDialog(null, "�����ʽ������������ȷ�����֣��ɱ�����λС����");
				}
			}
		}else if(e.getSource()==vip){
			try {
				// ����������û���Ϣ������Ӧ�Ŀͻ������ݿͻ���ϵ��ʽ���в��ң�
				String phone = JOptionPane.showInputDialog(null, "������˿��ֻ��ţ�");
				if(phone!=null){
					Customer findcust = customerService.getCustomerByPhone(phone);
					if(findcust==null){
						JOptionPane.showMessageDialog(null, "��Ǹ��û���ҵ���Ӧ�Ĺ˿���Ϣ����ȷ�Ϻ��ٴ����룡");
					}else{
						customer = findcust;
						// ˢ�¹˿���Ϣ
						this.refreshDownPanel();
					}
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==reset){
			keyword.setFont(MyFont.TipFont);
			keyword.setForeground(MyColor.TipColor);
			keyword.setText("������|");
		}else if(e.getSource()==balance){
			// �������ȷ�Ͻ���
			new EnsureSaleOrderJFrame(this,this.loginUser,this.customer);
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
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("������|")) {
				keyword.setText("");
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setText("������|");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}

	/**
	 * ͨ�����뾭������ɸѡ�����Ʒ���ж��ι��� ɸѡ�ϼ���Ʒ���ϼܿ�����0����Ʒ��Ϊ���ϼܣ� ��Ʒ�ϼ��ǽ���Ʒ�Ӳֿ��з��볬��
	 */
	public List<Product> getProductPutOn(List<Product> list) {
		List<Product> put_on_list = new ArrayList<Product>();
		for (Product p : list) {
			if (p.getPutaway_stock() > 0) {
				put_on_list.add(p);
			}
		}
		return put_on_list;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// ˢ�±���������
		this.refreshCenterPanel();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {

	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	// ˢ���Ϸ����������
	public void refreshTopPanel(double sale_price) {
		try {
			unit_price.setText(sale_price + "");
			quantity.setText("1.0");
			double subamount = ((int) sale_price * 1 * 100) / 100;
			subtotal.setText(subamount + "");
			// �����ǰ�б����Ʒ��Ϣ��Ϊ�գ����ȡ��ǰ�б��������Ʒ���ܶ�(�����ָ���쳣)
			List<SaleTemp> st_list = saleTempService.findAllSaleTempList();
			double allamount = 0.00;
			if(st_list!=null){
				allamount = saleTempService.getAllAmount();
			}
			total.setText(allamount+"");
			// ˢ���������
			backgroundPanel.validate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ����ˢ���м�ı��������������
	public void refreshCenterPanel() {
		backgroundPanel.remove(centerPanel);
		initCenterPanel();
		backgroundPanel.validate();
	}
	
	// ˢ���·������ݼ�¼���
	public void refreshDownPanel() {
		try {
			if(customer!=null){
				// ˵���ǻ�Ա�ɹ���������������Ϣ����������ʾ
				label_customer_sign.setText("��Ա��ʶ����Ա");
				label_customer.setText("��Ա���ƣ�"+customer.getCustomer_name());
				ConsumeClass cc = consumeClassService.getConsumeClassById(customer.getClass_id());
				label_consume_class.setText("��Ա�ȼ���"+cc.getClass_name());
				label_discount.setText("�ۿۣ�"+cc.getClass_discount());
				label_off.setText("�Żݣ�"+cc.getClass_off());
				/**
				 * �Զ������
				 * ����ǻ�Ա�ɹ�������Ӧ�����������Ż�
				 * 1.ÿ�ι����ܹ�����Ӧ���ۿ۽��вɹ�
				 * 2.ÿ�ι������Ѿ��ۺ���1000�����ܹ�������Ӧ�������Ż�
				 */
				double totalAmount = Double.valueOf(total.getText());
				// ��ȡ�����Żݵļ۸�
				double discountAmount = ((int)(totalAmount*cc.getClass_discount()*100))/100;
				double concession = discountAmount;
				if((totalAmount-discountAmount)>1000){
					concession += cc.getClass_off();
				}
				label_concession.setText("�Żݺϼƣ�"+concession);
			}
			backgroundPanel.validate();
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ˢ�������������
	public void refreshBackgroundPanel(){
		backgroundPanel.removeAll();
		initTopPanel();
		initCenterPanel();
		initDownPanel();
		backgroundPanel.validate();
	}
}
