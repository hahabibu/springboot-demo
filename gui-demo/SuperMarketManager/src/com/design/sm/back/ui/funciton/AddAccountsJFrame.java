package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.back.ui.control.AccountsMangerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.service.AccountsService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class AddAccountsJFrame extends JFrame implements MouseListener,
		FocusListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_password, label_limits;
	JTextField name, password;
	JComboBox limits;
	JButton save_button, reset_button;

	int user_limits = 0;
	AccountsService userService = new AccountsServiceImpl();

	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ���常����
	AccountsMangerJPanel parentPanel;

	// ͨ�����췽����ʼ������
	public AddAccountsJFrame(AccountsMangerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("����˺���Ϣ");
		this.setSize(425, 290);
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
		JLabel title = new JLabel("����˺���Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		/**
		 * ����˺ŵ����ݣ� �˺�id���������10λ���ַ����ݣ� �û������û����롢�û�Ȩ��
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("�û��ǳ�");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("������");
		name.addFocusListener(this);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_password = new JLabel("�û�����");
		password = new JTextField(15);
		password.setFont(MyFont.TipFont);
		password.setForeground(MyColor.TipColor);
		password.setText("������");
		password.addFocusListener(this);
		jp2.add(label_password);
		jp2.add(password);

		JPanel jp3 = new JPanel();
		label_limits = new JLabel("�û�Ȩ��");
		limits = new JComboBox();
		limits.setPreferredSize(new Dimension(175, 30));
		// ��ʼ��������
		limits.addItem("����");
		limits.addItem("��������Ա");
		limits.addItem("���������");
		limits.addItem("��ͨԱ��");
		limits.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == limits) {
					if (limits.getSelectedIndex() == 0) {
						user_limits = 0;
					} else if (limits.getSelectedIndex() == 1) {
						user_limits = 1;
					} else if (limits.getSelectedIndex() == 2) {
						user_limits = 2;
					} else if (limits.getSelectedIndex() == 3) {
						user_limits = 3;
					}
				}
			}
		});
		jp3.add(label_limits);
		jp3.add(limits);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

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
			String password_string = password.getText();
			boolean isValid = false;
			try {
				//�ж��û��ǳ��Ƿ���Ч
				isValid = userService.isValidAccountsname(name_string);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			if(!isValid){
				JOptionPane.showMessageDialog(null, "�û��ǳ��Ѵ��ڣ�");
			}else if (name_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "�û��ǳƲ���Ϊ��");
			} else if (password_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "�û����벻��Ϊ��");
			} else {
				// ����Accounts���󣬽����ݱ��浽���ݿ���
				// �û�id��������ɵ�10λ�ַ�ƴ�ӵ�����
				String id = RandomGeneration.getRandom10charSeq();
				Accounts user = new Accounts(id,name_string,password_string,user_limits);
				int choose = JOptionPane.showConfirmDialog(null, "ȷ������û���Ϣ��");
				if (choose == 0) {
					try {
						userService.addAccounts(user);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
					JOptionPane.showMessageDialog(null, "��Ʒ��Ϣ����ɹ�");
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
			password.setFont(MyFont.TipFont);
			password.setForeground(MyColor.TipColor);
			password.setText("������");
			limits.setSelectedIndex(0);
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
				name.setText("");
			}
		} else if (e.getSource() == password) {
			if (password.getText().equals("������")) {
				password.setText("");
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
			} else {
				name.setForeground(MyColor.JTextFieldColor);
				name.setFont(MyFont.JTextFieldFont);
			}
		} else if (e.getSource() == password) {
			if (password.getText().equals("")) {
				password.setFont(MyFont.TipFont);
				password.setForeground(MyColor.TipColor);
				password.setText("������");
			} else {
				password.setForeground(MyColor.JTextFieldColor);
				password.setFont(MyFont.JTextFieldFont);
			}
		}
	}
}
