package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Vendor;
import com.design.sm.service.VendorService;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class UpdateVendorJFrame extends JFrame implements MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_phone, label_email, label_fax, label_address;
	JTextField name, phone, email, fax, address;
	JButton save_button, reset_button;

	VendorService vendorService = new VendorServiceImpl();

	// ���常����
	VendorManagerJFrame parentPanel;
	JTable table;
	int selectedRow;

	// ͨ�����췽����ʼ������
	public UpdateVendorJFrame(VendorManagerJFrame parentPanel,JTable table,int selectRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectedRow = selectRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�޸Ĺ�Ӧ����Ϣ");
		this.setSize(420, 370);
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
		JLabel title = new JLabel("�޸Ĺ�Ӧ����Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		/**
		 * ��ӹ�Ӧ�̵���Ϣ�� ��Ӧ��id��������ɵ�32λ���ַ����� ��Ӧ�����ƣ���Ϊ�� �����ˡ���ϵ��ʽ�����䡢���桢��ַ��ѡ��
		 * ֮���ٴ�����ص�����
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("��˾����");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_phone = new JLabel("��ϵ��ʽ");
		phone = new JTextField(15);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_phone);
		jp2.add(phone);

		JPanel jp3 = new JPanel();
		label_email = new JLabel("��������");
		email = new JTextField(15);
		email.setFont(MyFont.JTextFieldFont);
		email.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_email);
		jp3.add(email);

		JPanel jp4 = new JPanel();
		label_fax = new JLabel("�������");
		fax = new JTextField(15);
		fax.setFont(MyFont.JTextFieldFont);
		fax.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_fax);
		jp4.add(fax);

		JPanel jp5 = new JPanel();
		label_address = new JLabel("��˾��ַ");
		address = new JTextField(15);
		address.setFont(MyFont.JTextFieldFont);
		address.setForeground(MyColor.JTextFieldColor);
		jp5.add(label_address);
		jp5.add(address);

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
			String phone_string = phone.getText();
			String email_string = email.getText();
			String fax_string = fax.getText();
			String address_string = address.getText();

			// ���ѡ��ֵ����ݾ�Ϊ�գ���Ӧ������Ϊ���ַ����������ڲ������ݵ����ݿ��ʱ����ֲ��
			if (phone_string.equals("")) {
				phone_string = "��";
			}
			if (email_string.equals("")) {
				email_string = "��";
			}
			if (fax_string.equals("")) {
				fax_string = "��";
			}
			if (address_string.equals("")) {
				address_string = "��";
			}
			if (name_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "��Ӧ�����Ʋ���Ϊ��");
			} else {
				// ����Vendor���󣬽����ݱ��浽���ݿ���
				// �û�id�ǳ��ν������γɣ����ܹ��������
				String id = table.getValueAt(selectedRow, 0).toString();
				Vendor vendor = new Vendor(id, name_string, phone_string,
						email_string, fax_string, address_string);
				int choose = JOptionPane.showConfirmDialog(null, "ȷ���޸Ĺ�Ӧ����Ϣ��");
				if (choose == 0) {
					try {
						vendorService.updateVendor(vendor);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
					JOptionPane.showMessageDialog(null, "��Ӧ����Ϣ����ɹ�");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
				} else {
					this.setVisible(false);
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
	public void echoData(){
		// �ӵ�ǰѡ�е����ݻ�ȡ��Ӧ����ϸ��Ϣ
		String vendor_name_string = table.getValueAt(selectedRow, 1).toString();
		String vendor_phone_string = table.getValueAt(selectedRow, 2).toString();
		String vendor_email_string = table.getValueAt(selectedRow, 3).toString();
		String vendor_fax_string = table.getValueAt(selectedRow, 4).toString();
		String vendor_address_string = table.getValueAt(selectedRow, 5).toString();
		// ���ݻ���
		name.setText(vendor_name_string);
		name.setToolTipText(vendor_name_string);
		phone.setText(vendor_phone_string);
		phone.setToolTipText(vendor_phone_string);
		email.setText(vendor_email_string);
		email.setToolTipText(vendor_email_string);
		fax.setText(vendor_fax_string);
		fax.setToolTipText(vendor_fax_string);
		address.setText(vendor_address_string);
		address.setToolTipText(vendor_address_string);
	}
}
