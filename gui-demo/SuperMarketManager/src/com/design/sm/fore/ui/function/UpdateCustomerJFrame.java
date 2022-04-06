package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.fore.ui.control.CustomerManagerJPanel;
import com.design.sm.model.ConsumeClass;
import com.design.sm.model.Customer;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.service.CustomerService;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.service.impl.CustomerServiceImpl;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class UpdateCustomerJFrame extends JFrame implements MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_address, label_phone, label_email,
			label_integrate, label_balance, label_consume_class;
	JTextField name, address, phone, email, integrate, balance;
	JComboBox consume_class;

	JButton save_button, reset_button;

	CustomerService customerService = new CustomerServiceImpl();
	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();

	// ���常����
	CustomerManagerJPanel parentPanel;
	JTable table;
	int selectedRow;

	// ͨ�����췽����ʼ������
	public UpdateCustomerJFrame(CustomerManagerJPanel parentPanel,
			JTable table, int selectedRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�޸Ĺ˿���Ϣ");
		this.setSize(420, 450);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// ����������
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// ���ùرշ�ʽ
		// ��ǰ�������أ���Ӱ������ݵ�ʹ�ã������ǹر���������
	}

	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		initTitlePanel();
		initContentPanel();
		initButtonPanel();
		backgroundPanel.add(titlePanel, BorderLayout.NORTH);
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * ��ʼ���������
	 */
	private void initTitlePanel() {
		titlePanel = new JPanel();
		JLabel title = new JLabel("�޸Ĺ˿���Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		/**
		 * �޸Ĺ˿͵���Ϣ�� �˿�id�����32char���˿͵�id���ܹ�������ģ� �˿͵�ַ����ϵ��ʽ�����䡢��ǰ���֡���ǰ�����ѵȼ�
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("�˿�����");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_address = new JLabel("�˿͵�ַ");
		address = new JTextField(15);
		address.setFont(MyFont.JTextFieldFont);
		address.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_address);
		jp2.add(address);

		JPanel jp3 = new JPanel();
		label_phone = new JLabel("��ϵ��ʽ");
		phone = new JTextField(15);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_phone);
		jp3.add(phone);

		JPanel jp4 = new JPanel();
		label_email = new JLabel("��������");
		email = new JTextField(15);
		email.setFont(MyFont.JTextFieldFont);
		email.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_email);
		jp4.add(email);

		JPanel jp5 = new JPanel();
		label_integrate = new JLabel("���ѻ���");
		integrate = new JTextField(15);
		integrate.setFont(MyFont.JTextFieldFont);
		integrate.setForeground(MyColor.JTextFieldColor);
		jp5.add(label_integrate);
		jp5.add(integrate);

		JPanel jp6 = new JPanel();
		label_balance = new JLabel("�������");
		balance = new JTextField(15);
		balance.setFont(MyFont.JTextFieldFont);
		balance.setForeground(MyColor.JTextFieldColor);
		jp6.add(label_balance);
		jp6.add(balance);

		JPanel jp7 = new JPanel();
		label_consume_class = new JLabel("�˿͵ȼ�");
		consume_class = new JComboBox();
		consume_class.setPreferredSize(new Dimension(175, 30));
		jp7.add(label_consume_class);
		jp7.add(consume_class);

		// ��������
		this.echoData();
		
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp5);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp6);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp7);
		// ��������ص�contentPanel�����
		contentPanel.add(ver);
	}

	/**
	 * ��ʼ����ť���
	 */
	private void initButtonPanel() {
		Box hor = Box.createHorizontalBox();
		buttonPanel = new JPanel();
		save_button = new JButton("����");
		save_button.setForeground(Color.white);
		save_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.blue));

		reset_button = new JButton("����");
		reset_button.setForeground(Color.white);
		reset_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));

		// ��Ӱ�ť����
		save_button.addMouseListener(this);
		reset_button.addMouseListener(this);

		// ����ť���ص������
		hor.add(save_button);
		hor.add(Box.createHorizontalStrut(20));
		hor.add(reset_button);
		buttonPanel.add(hor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == save_button) {
			// ��ȡ��ǰ�ı����������Ϣ
			String name_string = name.getText();
			String address_string = address.getText();
			String phone_string = phone.getText();
			String email_string = email.getText();
			String integrate_string = integrate.getText();
			String balance_string = balance.getText();

			// ���ѡ��ֵ����ݾ�Ϊ�գ���Ӧ������Ϊ���ַ����������ڲ������ݵ����ݿ��ʱ����ֲ��
			if (address_string.equals("")) {
				address_string = "��";
			}
			if (email_string.equals("")) {
				email_string = "��";
			}
			if (integrate_string.equals("")) {
				integrate_string = "0";
			}
			if (balance_string.equals("")) {
				balance_string = "0.00";
			}
			if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "�˿����Ʋ���Ϊ��");
			} else if (phone_string.equals("")) {
				JOptionPane.showMessageDialog(null, "�˿���ϵ��ʽ����Ϊ��");
			} else {
				// ��֤���ݵĺϷ���
				if (!DataValidation.isSignlessInteger(integrate_string)) {
					JOptionPane.showMessageDialog(null, "���ѻ��������ʽ������������������");
				} else if (!DataValidation.isBigDecimal(balance_string)) {
					JOptionPane.showMessageDialog(null,
							"������������ʽ�����������������ɱ�����λС����");
				} else {
					int integrate_int = Integer.valueOf(integrate_string);
					double balance_double = Double.valueOf(balance_string);
					// ��ȡ���ѵȼ���������ѡ��
					Item item = (Item) consume_class.getSelectedItem();
					int ccId = Integer.valueOf(item.getKey());
					// ����Customer���󣬽����ݱ��浽���ݿ���
					// �û�id��������ɵ�32λ�ַ�ƴ�ӵ�����,���޸ĵ�ʱ���ܹ��������
					String id = table.getValueAt(selectedRow,0).toString();
					Customer customer = new Customer(id, name_string,
							address_string, phone_string, email_string,
							integrate_int, balance_double, ccId);
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ���޸Ĺ˿���Ϣ��");
					if (choose == 0) {
						try {
							customerService.updateCustomer(customer);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
						JOptionPane.showMessageDialog(null, "�˿���Ϣ����ɹ�");
						this.setVisible(false);
						parentPanel.refreshTablePanel();
					}
				}
			}
		} else if (e.getSource() == reset_button) {
			// ��������
			this.echoData();
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
	 * �������ݻ��Է���
	 */
	public void echoData() {
		// ��ѡ�еı�������л�ȡ��Ӧ�Ĺ˿���Ϣ
		String name_string = table.getValueAt(selectedRow, 1).toString();
		String address_string = table.getValueAt(selectedRow, 2).toString();
		String phone_string = table.getValueAt(selectedRow, 3).toString();
		String email_string = table.getValueAt(selectedRow, 4).toString();
		String integrate_string = table.getValueAt(selectedRow, 5).toString();
		String balance_string = table.getValueAt(selectedRow, 6).toString();
		String class_id_string = table.getValueAt(selectedRow, 7).toString();

		name.setText(name_string);
		address.setText(address_string);
		phone.setText(phone_string);
		email.setText(email_string);
		integrate.setText(integrate_string);
		balance.setText(balance_string);
		// ���¼������ѵȼ�
		consume_class.removeAllItems();
		List<ConsumeClass> list_class = null;
		try {
			list_class = consumeClassService.findAllConsumeClassList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list_class.size(); i++) {
			int sign = 0;
			int cid = list_class.get(i).getClass_id();
			String cname = list_class.get(i).getClass_name();
			Item item = new Item(cid + "", cname);
			consume_class.addItem(item);
			if (class_id_string.equals(cid + "")) {
				sign = i;
				consume_class.setSelectedIndex(sign);
			}
		}
	}
}