package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import com.design.sm.model.Accounts;
import com.design.sm.model.ConsumeClass;
import com.design.sm.model.Customer;
import com.design.sm.model.Product;
import com.design.sm.model.SaleMaster;
import com.design.sm.model.SaleOrder;
import com.design.sm.model.SaleTemp;
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
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.service.impl.CustomerServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.SaleMasterServiceImpl;
import com.design.sm.service.impl.SaleTempServiceImpl;
import com.design.sm.service.impl.SoldNoteServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.RandomGeneration;
import com.design.sm.utils.Tools;

public class EnsureSaleOrderJFrame extends JFrame implements ItemListener,
		MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, buttonPanel, tablePanel, ensurePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_master_id, label_order_num, label_handle, label_cust,
			label_contact, label_time, label_concession, label_sum;
	JTextField master_id, order_num, handle, cust, time, concession;
	JButton commit, cancel;
	double actual_sum;
	// ����һ�鵥ѡ��ť���Դ�Ÿ��ʽ
	ButtonGroup payment;
	JRadioButton cash, vipCard, thirdPart, creditCard;
	// ������Ӧ��service
	ProductService productService = new ProductServiceImpl();
	SaleTempService saleTempService = new SaleTempServiceImpl();
	SaleMasterService saleMasterService = new SaleMasterServiceImpl();
	SaleOrderService saleOrderService = new SaleOrderServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();
	SoldNoteService soldNoteService = new SoldNoteServiceImpl();

	Accounts loginUser;
	CashierJFrame parentPanel;
	Customer customer;

	// ����ȫ�ֱ���
	private String master_id_string, order_num_string, handle_id_string,
			cust_id_string;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public EnsureSaleOrderJFrame(CashierJFrame parentPanel, Accounts loginUser,
			Customer customer) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.customer = customer;
		backgroundPanel = new JPanel(new BorderLayout());
		// ��ʼ������
		initButtonPanel();// ��ʼ��֧��ѡ��ť��
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
	 * ��ʼ��֧��ѡ��ť��
	 */
	public void initButtonPanel() {
		buttonPanel = new JPanel();
		payment = new ButtonGroup();
		Icon cash_icon = new ImageIcon("icons/toolImage/cash.png");
		cash = new JRadioButton(cash_icon);
		cash.setToolTipText("�ֽ�֧��");
		cash.addMouseListener(this);
		cash.addItemListener(this);

		Icon vipCard_icon = new ImageIcon("icons/toolImage/vipCard.png");
		vipCard = new JRadioButton(vipCard_icon);
		vipCard.setToolTipText("vip���֧��");
		vipCard.addMouseListener(this);
		vipCard.addItemListener(this);

		Icon thirdPart_icon = new ImageIcon("icons/toolImage/thirdPart.png");
		thirdPart = new JRadioButton(thirdPart_icon);
		thirdPart.setToolTipText("������֧��");
		thirdPart.addMouseListener(this);
		thirdPart.addItemListener(this);

		Icon creditCard_icon = new ImageIcon("icons/toolImage/creditCard.png");
		creditCard = new JRadioButton(creditCard_icon);
		creditCard.setToolTipText("���ÿ�֧��");
		creditCard.addMouseListener(this);
		creditCard.addItemListener(this);

		payment.add(cash);
		payment.add(vipCard);
		payment.add(thirdPart);
		payment.add(creditCard);

		buttonPanel.add(cash);
		buttonPanel.add(vipCard);
		buttonPanel.add(thirdPart);
		buttonPanel.add(creditCard);
		backgroundPanel.add(buttonPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		String[] params = { "��Ʒid", "��Ʒ����", "����", "�ۼ�", "С��", "��Ӧ��id", "��Ӧ��" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = saleTempService.pack(saleTempService.findAllSaleTempList());
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
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);

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
		master_id = new JTextField(25);
		master_id.setEditable(false);

		label_handle = new JLabel("������  ");
		handle = new JTextField(25);
		handle.setEditable(false);

		jp1.add(label_master_id);
		jp1.add(Box.createHorizontalStrut(10));
		jp1.add(master_id);
		jp1.add(Box.createHorizontalStrut(10));
		jp1.add(label_handle);
		jp1.add(Box.createHorizontalStrut(10));
		jp1.add(handle);

		JPanel jp2 = new JPanel();
		label_order_num = new JLabel("�������");
		order_num = new JTextField(25);
		order_num.setEditable(false);

		label_cust = new JLabel("�˿�����");
		cust = new JTextField(25);
		cust.setEditable(false);

		jp2.add(label_order_num);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(order_num);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(label_cust);
		jp2.add(Box.createHorizontalStrut(10));
		jp2.add(cust);

		JPanel jp3 = new JPanel();
		label_time = new JLabel("����ʱ��");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		time = new JTextField(25);
		time.setText(df.format(new Date()));
		label_concession = new JLabel("�Żݺϼ�");
		concession = new JTextField(25);
		concession.setEditable(false);

		jp3.add(label_time);
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(time);
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(label_concession);
		jp3.add(Box.createHorizontalStrut(10));
		jp3.add(concession);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

		JPanel east = new JPanel(new GridLayout(3, 1));
		label_sum = new JLabel();

		commit = new JButton("ȷ��");
		commit.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		commit.setSize(50, 30);
		commit.addMouseListener(this);

		cancel = new JButton("ȡ��");
		cancel.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));
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
			// �жϵ�ǰ�����ܶ��Ƿ�ﵽ1000������жϹ˿��Ƿ�Ϊ�ѿ����ͻ������ѹ˿ͽ��п���
			double amount = this.actual_sum;
			if (this.customer == null && amount >= 1000) {
				JOptionPane.showMessageDialog(null,
						"��ǰ��������1000����ƾСƱ���ͷ����ļ�����ֿ�Ŷ��");
			}
			try {
				/**
				 * ���뱣֤�Ƕ�Ӧ�˺ŵ���ӦԱ����¼���в��������������Ӧ�˺ŵ�Ա����Ϣ�����ھͻ���Ӧ��
				 * ���쳣����Ҫ�������ݿ�������ݲ��Ե�ʱ��û�п���ȫ��
				 */
				/**
				 * �����ύ�� 1.�������۶������� 2.�������۶�����ϸ 3.�޸���Ʒ���ϼ���Ŀ 4.�Ƴ���ʱ���������� 5.������ۼ�¼
				 * 6.ˢ�¸�������� �������ɣ��ж��û����Ժ��ַ�ʽ����֧���ģ�֧���ɹ��������Ӧ����֧��Ϣ
				 */
				int flag = 0;// ���������ʶ
				if (vipCard.isSelected()) {
					// ���ж��Ƿ�Ϊvip�û�
					if (this.customer == null) {
						JOptionPane.showMessageDialog(null, "��ǰ�˿Ͳ�֧�ָ���֧����ʽ��");
					} else {
						// ͨ����ȡ�˿͵�ǰ�������ж��Ƿ���Խ���֧����������ģ�⣬����֧����ʽ��Ĭ��ֱ�ӿ��Խ��У�ֻ�Ǽ�¼���ݣ�
						double balance_double = this.customer.getBalance();
						if (actual_sum > balance_double) {
							JOptionPane.showMessageDialog(null,
									"��ǰ�������㣬�뻻�ַ�ʽ���в����ɣ�");
						} else {
							// �ı����ۼƻ���
							this.customer.setBalance(balance_double
									- actual_sum);
							this.customer.setIntegrate(this.customer
									.getIntegrate() + (int) actual_sum);
							customerService.updateCustomer(this.customer);
							flag = 1;
						}
					}
				} else {
					if (this.customer != null) {
						this.customer.setIntegrate(this.customer.getIntegrate()
								+ (int) actual_sum);
						customerService.updateCustomer(this.customer);
					}
					flag = 1;
				}
				if (flag == 1) {
					String handle_time_string = time.getText();
					// �ύ�����ɶ�����������ʱ���е�����������Ӧ�Ķ������������ʱ���е����ύ�Ķ���
					// 1.������һ����������(0��⡢ɾ����ʶ����������״̬)
					SaleMaster sm = new SaleMaster(master_id_string,
							order_num_string, handle_id_string, cust_id_string,
							handle_time_string, 0, 0);
					saleMasterService.addSaleMaster(sm);
					// 2.�������ɵĶ������붩����ϸ(��ȡ�������)
					for (int i = 0; i < table.getRowCount(); i++) {
						String prod_id_string = table.getValueAt(i, 0)
								.toString();
						int quantity_int = Integer.valueOf(table.getValueAt(i,
								2).toString());
						double unit_price_double = Double.valueOf(table
								.getValueAt(i, 3).toString());
						double amount_double = Double.valueOf(table.getValueAt(
								i, 4).toString());
						SaleOrder so = new SaleOrder(master_id_string,
								prod_id_string, quantity_int,
								unit_price_double, amount_double, 0, 0);
						saleOrderService.addSaleOrder(so);
					}
					// 3.�����޸���Ʒ��Ӧ�����ϼ���Ŀ
					List<SaleTemp> list_temp = saleTempService
							.findAllSaleTempList();
					for (SaleTemp st : list_temp) {
						// ��ȡ��Ʒid����������
						String prod_id = st.getProduct_id();
						int quantity = st.getQuantity();
						Product p = productService.getProduct(prod_id);
						// ��ǰ�ϼ���Ŀ��ȥ��������
						p.setPutaway_stock(p.getPutaway_stock() - quantity);
						productService.updateProduct(p);
					}
					// 4.�����Ƴ���ʱ���۶����Ķ�������
					saleTempService.truncateAllSaleTemp();
					// 5.������ۼ�¼
					SoldNote sn = new SoldNote(order_num_string, actual_sum,
							this.getPaymentInt());
					soldNoteService.addSoldNote(sn);
					// 6.����ҳ�棬�����ʾ
					this.setVisible(false);
					JOptionPane.showMessageDialog(null, "���׳ɹ���");
					parentPanel.refreshBackgroundPanel();
				}
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
			order_num_string = saleMasterService.getSaleNextSeq().toString();
			// ����idΪ��ǰ��¼��Ա���˺ŵ�Ա��id(ͨ����ǰԱ���˺Ż�ȡ)
			String accountId = this.loginUser.getAccount_id();
			handle_id_string = employeeService
					.getEmployeeIdByAccountId(accountId);

			// �����ı�����
			master_id.setText(master_id_string);
			master_id.setToolTipText(master_id_string);
			order_num.setText(order_num_string);
			order_num.setToolTipText(order_num_string);
			handle.setText(String.valueOf(employeeService
					.getEmployeeName(handle_id_string)));
			handle.setToolTipText(String.valueOf(employeeService
					.getEmployeeName(handle_id_string)));
			// �˿�id�ɵ�ǰ����Ĺ˿ͽ��в���
			if (this.customer != null) {
				cust.setText(customer.getCustomer_name());
				cust.setToolTipText(customer.getCustomer_name());
				cust_id_string = customer.getCustomer_id();
				// ͳ�Ƶ�ǰ������������Ʒ�ܶ�
				double sum = 0;
				for (int i = 0; i < table.getRowCount(); i++) {
					sum += Double.valueOf(table.getValueAt(i, 4).toString());
				}
				// ��ȡ�����Żݵļ۸�
				/**
				 * �Զ������ ����ǻ�Ա�ɹ�������Ӧ�����������Ż� 1.ÿ�ι����ܹ�����Ӧ���ۿ۽��вɹ�
				 * 2.ÿ�ι������Ѿ��ۺ���1000�����ܹ�������Ӧ�������Ż�
				 */
				ConsumeClass cc = consumeClassService
						.getConsumeClassById(customer.getClass_id());
				double discountAmount = ((int) (sum * cc.getClass_discount() * 100)) / 100;
				double concession_dobule = discountAmount;
				if ((sum - discountAmount) > 1000) {
					concession_dobule += cc.getClass_off();
				}
				concession.setText(concession_dobule + "");
				// ���յ��ܽ��Ϊ��ǰ������Ʒ�ܶ��ȥ�Żݺϼ�
				actual_sum = sum - concession_dobule;
				label_sum.setText("�ܽ�" + (sum - concession_dobule));
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
				label_sum.setText("�ܽ�" + (sum));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ݵ�ѡ��ѡ���ȡ�û�ѡ���֧����ʽ
	 */
	public int getPaymentInt() {
		int i = 0;
		if (cash.isSelected()) {
			i = 1;
		} else if (vipCard.isSelected()) {
			i = 2;
		} else if (thirdPart.isSelected()) {
			i = 3;
		} else if (creditCard.isSelected()) {
			i = 4;
		}
		return i;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			if (cash.isSelected()) {
				cash.setBackground(new Color(192, 190, 204));
				vipCard.setBackground(new Color(250, 250, 250));
				thirdPart.setBackground(new Color(250, 250, 250));
				creditCard.setBackground(new Color(250, 250, 250));
			} else if (vipCard.isSelected()) {
				vipCard.setBackground(new Color(192, 190, 204));
				cash.setBackground(new Color(250, 250, 250));
				thirdPart.setBackground(new Color(250, 250, 250));
				creditCard.setBackground(new Color(250, 250, 250));
			} else if (thirdPart.isSelected()) {
				thirdPart.setBackground(new Color(192, 190, 204));
				cash.setBackground(new Color(250, 250, 250));
				vipCard.setBackground(new Color(250, 250, 250));
				creditCard.setBackground(new Color(250, 250, 250));
			} else if (creditCard.isSelected()) {
				creditCard.setBackground(new Color(192, 190, 204));
				cash.setBackground(new Color(250, 250, 250));
				vipCard.setBackground(new Color(250, 250, 250));
				thirdPart.setBackground(new Color(250, 250, 250));
			}
		}
	}
}
