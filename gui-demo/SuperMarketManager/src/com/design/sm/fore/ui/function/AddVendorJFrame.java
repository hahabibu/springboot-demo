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
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Vendor;
import com.design.sm.service.VendorService;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class AddVendorJFrame extends JFrame implements MouseListener,
		FocusListener {
	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_phone, label_email, label_fax, label_address;
	JTextField name, phone, email, fax, address;
	JButton save_button, reset_button;

	VendorService vendorService = new VendorServiceImpl();

	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ���常����
	VendorManagerJFrame parentPanel;

	// ͨ�����췽����ʼ������
	public AddVendorJFrame(VendorManagerJFrame parentPanel) {
		this.parentPanel = parentPanel;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("��ӹ�Ӧ����Ϣ");
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
		JLabel title = new JLabel("��ӹ�Ӧ����Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		/**
		 * ��ӹ�Ӧ�̵���Ϣ�� ��Ӧ��id��������ɵ�10λ���ַ����� ��Ӧ�����ƣ���Ϊ�� �����ˡ���ϵ��ʽ�����䡢���桢��ַ��ѡ��
		 * ֮���ٴ�����ص�����
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("��˾����");
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
		phone.setText("��ѡ��");
		phone.addFocusListener(this);
		jp2.add(label_phone);
		jp2.add(phone);

		JPanel jp3 = new JPanel();
		label_email = new JLabel("��������");
		email = new JTextField(15);
		email.setFont(MyFont.TipFont);
		email.setForeground(MyColor.TipColor);
		email.setText("��ѡ��");
		email.addFocusListener(this);
		jp3.add(label_email);
		jp3.add(email);

		JPanel jp4 = new JPanel();
		label_fax = new JLabel("�������");
		fax = new JTextField(15);
		fax.setFont(MyFont.TipFont);
		fax.setForeground(MyColor.TipColor);
		fax.setText("��ѡ��");
		fax.addFocusListener(this);
		jp4.add(label_fax);
		jp4.add(fax);

		JPanel jp5 = new JPanel();
		label_address = new JLabel("��˾��ַ");
		address = new JTextField(15);
		address.setFont(MyFont.TipFont);
		address.setForeground(MyColor.TipColor);
		address.setText("��ѡ��");
		address.addFocusListener(this);
		jp5.add(label_address);
		jp5.add(address);

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
			if (phone_string.equals("��ѡ��")) {
				phone_string = "��";
			}
			if (email_string.equals("��ѡ��")) {
				email_string = "��";
			}
			if (fax_string.equals("��ѡ��")) {
				fax_string = "��";
			}
			if (address_string.equals("��ѡ��")) {
				address_string = "��";
			}
			if (name_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "��Ӧ�����Ʋ���Ϊ��");
			} else {
				// ����Vendor���󣬽����ݱ��浽���ݿ���
				// �û�id��������ɵ�32λ�ַ�ƴ�ӵ�����
				String id = RandomGeneration.getRandom32charSeq();
				Vendor vendor = new Vendor(id, name_string, phone_string,
						email_string, fax_string, address_string);
				int choose = JOptionPane.showConfirmDialog(null, "ȷ����ӹ�Ӧ����Ϣ��");
				if (choose == 0) {
					try {
						vendorService.addVendor(vendor);
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
			// ������е�����
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("������");
			phone.setFont(MyFont.TipFont);
			phone.setForeground(MyColor.TipColor);
			phone.setText("��ѡ��");
			email.setFont(MyFont.TipFont);
			email.setForeground(MyColor.TipColor);
			email.setText("��ѡ��");
			fax.setFont(MyFont.TipFont);
			fax.setForeground(MyColor.TipColor);
			fax.setText("��ѡ��");
			address.setFont(MyFont.TipFont);
			address.setForeground(MyColor.TipColor);
			address.setText("��ѡ��");
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
			if (phone.getText().equals("��ѡ��")) {
				phone.setForeground(MyColor.JTextFieldColor);
				phone.setFont(MyFont.JTextFieldFont);
				phone.setText("");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("��ѡ��")) {
				email.setForeground(MyColor.JTextFieldColor);
				email.setFont(MyFont.JTextFieldFont);
				email.setText("");
			}
		} else if (e.getSource() == fax) {
			if (fax.getText().equals("��ѡ��")) {
				fax.setForeground(MyColor.JTextFieldColor);
				fax.setFont(MyFont.JTextFieldFont);
				fax.setText("");
			}
		} else if (e.getSource() == address) {
			if (address.getText().equals("��ѡ��")) {
				address.setForeground(MyColor.JTextFieldColor);
				address.setFont(MyFont.JTextFieldFont);
				address.setText("");
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
				phone.setText("��ѡ��");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("")) {
				email.setFont(MyFont.TipFont);
				email.setForeground(MyColor.TipColor);
				email.setText("��ѡ��");
			}
		} else if (e.getSource() == fax) {
			if (fax.getText().equals("")) {
				fax.setFont(MyFont.TipFont);
				fax.setForeground(MyColor.TipColor);
				fax.setText("��ѡ��");
			}
		} else if (e.getSource() == address) {
			if (address.getText().equals("")) {
				address.setFont(MyFont.TipFont);
				address.setForeground(MyColor.TipColor);
				address.setText("��ѡ��");
			}
		}
	}
}
