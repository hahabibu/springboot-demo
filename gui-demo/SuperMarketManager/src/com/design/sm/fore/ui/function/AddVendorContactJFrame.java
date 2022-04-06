package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Vendor;
import com.design.sm.model.VendorContact;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.VendorService;
import com.design.sm.service.impl.VendorContactServiceImpl;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class AddVendorContactJFrame extends JFrame implements MouseListener,
		FocusListener {
	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_phone, label_email, label_vendor, label_identity;
	JTextField name, phone, email ;
	JComboBox vendor,identity;
	JButton save_button, reset_button;

	VendorService vendorService = new VendorServiceImpl();
	VendorContactService vendorContactService = new VendorContactServiceImpl();

	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ���常����
	VendorContactManagerJFrame parentPanel;

	// ͨ�����췽����ʼ������
	public AddVendorContactJFrame(VendorContactManagerJFrame parentPanel) {
		this.parentPanel = parentPanel;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�����������Ϣ");
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
		JLabel title = new JLabel("�����������Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		/**
		 * ��������˵���Ϣ�� 
		 * ������id��������ɵ�32λ���ַ����� 
		 * ���������ơ���ϵ��ʽ�����䡢������Ӧ�̡���ݱ�ʶ
		 * ֮���ٴ�����ص�����
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("����    ");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("������");
		name.addFocusListener(this);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_phone = new JLabel("��ϵ��ʽ");
		phone = new JTextField(15);
		phone.setFont(MyFont.TipFont);
		phone.setForeground(MyColor.TipColor);
		phone.setText("������");
		phone.addFocusListener(this);
		jp2.add(label_phone);
		jp2.add(phone);

		JPanel jp3 = new JPanel();
		label_email = new JLabel("��������");
		email = new JTextField(15);
		email.setFont(MyFont.TipFont);
		email.setForeground(MyColor.TipColor);
		email.setText("������");
		email.addFocusListener(this);
		jp3.add(label_email);
		jp3.add(email);

		JPanel jp4 = new JPanel();
		label_vendor = new JLabel("����˾");
		vendor = new JComboBox();
		vendor.setPreferredSize(new Dimension(175, 30));
		// ��ʼ����Ӧ��������ѡ��
		List<Vendor> vendor_list = null;
		try {
			vendor_list = vendorService.findAllVendorList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Vendor v : vendor_list){
			String vid = v.getVendor_id();
			String vname = v.getVendor_name();
			Item item = new Item(vid,vname);
			vendor.addItem(item);
		}
		jp4.add(label_vendor);
		jp4.add(vendor);

		JPanel jp5 = new JPanel();
		label_identity = new JLabel("��ݱ�ʶ");
		identity = new JComboBox();
		identity.setPreferredSize(new Dimension(175, 30));
		identity.addItem("����Ա");
		identity.addItem("������");
		jp5.add(label_identity);
		jp5.add(identity);

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
			if (name_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "���������Ʋ���Ϊ��");
			} else if (phone_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "���緽ʽ����Ϊ��");
			}else if (email_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "�������䲻��Ϊ��");
			}
			else {
				// ��ȡ������ѡ��
				Item vendor_select = (Item)vendor.getSelectedItem();
				Object identity_select = identity.getSelectedItem();
				// ����VendorContact���󣬽����ݱ��浽���ݿ���
				// ��ϵ��id��������ɵ�32λ�ַ�ƴ�ӵ�����
				String id = RandomGeneration.getRandom32charSeq();
				String vendorId = vendor_select.getKey();
				int identity_int = 0;
				if(String.valueOf(identity_select).equals("������")){
					identity_int = 1;
				}
				VendorContact vc = new VendorContact(id, name_string, phone_string,
						email_string, vendorId, identity_int);
				int choose = JOptionPane.showConfirmDialog(null, "ȷ�������������Ϣ��");
				if (choose == 0) {
					try {
						vendorContactService.addVendorContact(vc);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
					JOptionPane.showMessageDialog(null, "��������Ϣ����ɹ�");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
				} else {
					this.setVisible(false);
				}
			}
		} else if (e.getSource() == reset_button) {
			// ������е�����
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("������");
			phone.setFont(MyFont.TipFont);
			phone.setForeground(MyColor.TipColor);
			phone.setText("������");
			email.setFont(MyFont.TipFont);
			email.setForeground(MyColor.TipColor);
			email.setText("������");
			vendor.setSelectedIndex(0);
			identity.setSelectedIndex(0);
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

	// �۽��¼�
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == name) {
			if (name.getText().equals("������")) {
				name.setForeground(MyColor.JTextFieldColor);
				name.setFont(MyFont.JTextFieldFont);
				name.setText("");
			}
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("������")) {
				phone.setForeground(MyColor.JTextFieldColor);
				phone.setFont(MyFont.JTextFieldFont);
				phone.setText("");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("������")) {
				email.setForeground(MyColor.JTextFieldColor);
				email.setFont(MyFont.JTextFieldFont);
				email.setText("");
			}
		}
	}

	// ʧȥ�����¼�
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == name) {
			if (name.getText().equals("")) {
				name.setFont(MyFont.TipFont);
				name.setForeground(MyColor.TipColor);
				name.setText("������");
			}
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("")) {
				phone.setFont(MyFont.TipFont);
				phone.setForeground(MyColor.TipColor);
				phone.setText("������");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("")) {
				email.setFont(MyFont.TipFont);
				email.setForeground(MyColor.TipColor);
				email.setText("������");
			}
		}
	}
}
