package com.guigu.library.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
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

import com.guigu.library.fore.ui.control.ForeIndexJFrame;
import com.guigu.library.fore.ui.control.ForeLoginJFrame;
import com.guigu.library.model.Users;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;

public class UpdateForeUserJFrame extends JFrame implements FocusListener,
		MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_pwd_old, label_pwd_new, label_limits;
	JTextField name, pwd_old, pwd_new, limits;
	JButton save_button, reset_button;
	UsersService userService = new UsersServiceImpl();

	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ���常���󡢵�ǰ��¼�û�
	ForeIndexJFrame parentPanel;
	Users loginUser;

	// ͨ�����췽����ʼ������
	public UpdateForeUserJFrame(ForeIndexJFrame parentPanel, Users loginUser) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�����˺��޸�");
		this.setSize(425, 325);
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
		JLabel title = new JLabel("�����˺��޸�");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		/**
		 * �޸��˺ŵ����ݣ� �˺�id���������10λ���ַ����ݣ� ���ɸĶ� �û������û����롢�û�Ȩ��
		 */
		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_name = new JLabel("�˺�����");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_pwd_old = new JLabel("ԭʼ����");
		pwd_old = new JTextField(15);
		pwd_old.setFont(MyFont.TipFont);
		pwd_old.setForeground(MyColor.TipColor);
		pwd_old.setText("������ԭʼ�������ȷ��");
		pwd_old.addFocusListener(this);
		jp2.add(label_pwd_old);
		jp2.add(pwd_old);

		JPanel jp3 = new JPanel();
		label_pwd_new = new JLabel("�µ�����");
		pwd_new = new JTextField(15);
		pwd_new.setFont(MyFont.TipFont);
		pwd_new.setForeground(MyColor.TipColor);
		pwd_new.setText("�������µ�����");
		pwd_new.addFocusListener(this);
		jp3.add(label_pwd_new);
		jp3.add(pwd_new);

		JPanel jp4 = new JPanel();
		label_limits = new JLabel("�û�Ȩ��");
		limits = new JTextField(15);
		limits.setFont(MyFont.JTextFieldFont);
		limits.setForeground(MyColor.JTextFieldColor);
		limits.setEditable(false);
		jp4.add(label_limits);
		jp4.add(limits);

		// ����������Ϣ
		this.echoData();

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);

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
			String pwd_old_string = pwd_old.getText();
			String pwd_new_string = pwd_new.getText();
			if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "�û��ǳƲ���Ϊ��");
			} else if (pwd_old_string.equals("������ԭʼ�������ȷ��")) {
				JOptionPane.showMessageDialog(null, "ԭʼ���벻��Ϊ��");
			} else if (pwd_new_string.equals("�������µ�����")) {
				JOptionPane.showMessageDialog(null, "�µ����벻��Ϊ��");
			} else {
				// �ж��û�����������Ƿ���ԭʼ������ͬ���������ͬ����������ʧ����ʾ
				String confirm_pwd = this.loginUser.getUser_password();
				if (!pwd_old_string.equals(confirm_pwd)) {
					JOptionPane.showMessageDialog(null, "ԭʼ��������������ȷ�Ϻ����²�����");
				} else {
					try {
						if (userService.isValidUsersname(name_string)
								|| name_string.equals(this.loginUser
										.getUser_name())) {
							// ����Users���󣬽����ݱ��浽���ݿ���
							// �û�id���û�Ȩ�޲������׸ı�
							String id = this.loginUser.getUser_id();
							int limit = this.loginUser.getUser_limits();
							Users user = new Users(id, name_string,
									pwd_new_string, limit);
							int choose = JOptionPane.showConfirmDialog(null,
									"ȷ���޸��˺���Ϣ��");
							if (choose == 0) {
								try {
									userService.updateUsers(user);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
								JOptionPane
										.showMessageDialog(null, "�˺���Ϣ�޸ĳɹ�!");
								this.setVisible(false);
							} else {
								this.setVisible(false);
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"��ǰ�û��ǳ��Ѵ��ڣ���һ�����԰ɣ�");
						}
					} catch (HeadlessException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == reset_button) {
			// ������е����ݣ�����������Ϣ
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
	 * �������ݵķ���
	 */
	public void echoData() {
		// �����û����˺�id��ȡ��ǰ�û����˺���Ϣ
		String name_string = this.loginUser.getUser_name();
		int limit = this.loginUser.getUser_limits();
		name.setText(name_string);
		if (limit == 1) {
			limits.setText("��������Ա");
		} else if (limit == 2) {
			limits.setText("ͼ��ݹ���Ա");
		} else if (limit == 3) {
			limits.setText("����");
		} else if (limit == 0) {
			limits.setText("�����˻�");
		}
		pwd_old.setText("������ԭʼ�������ȷ��");
		pwd_new.setText("�������µ�����");
	}

	/**
	 * �۽��¼�
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == pwd_old) {
			if (pwd_old.getText().equals("������ԭʼ�������ȷ��")) {
				pwd_old.setFont(MyFont.JTextFieldFont);
				pwd_old.setForeground(MyColor.JTextFieldColor);
				pwd_old.setText("");
			}
		} else if (e.getSource() == pwd_new) {
			if (pwd_new.getText().equals("�������µ�����")) {
				pwd_new.setFont(MyFont.JTextFieldFont);
				pwd_new.setForeground(MyColor.JTextFieldColor);
				pwd_new.setText("");
			}
		}
	}

	/**
	 * ʧȥ�����¼�
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == pwd_old) {
			if (pwd_old.getText().equals("")) {
				pwd_old.setFont(MyFont.TipFont);
				pwd_old.setForeground(MyColor.TipColor);
				pwd_old.setText("������ԭʼ�������ȷ��");
			}
		} else if (e.getSource() == pwd_new) {
			if (pwd_new.getText().equals("")) {
				pwd_new.setFont(MyFont.TipFont);
				pwd_new.setForeground(MyColor.TipColor);
				pwd_new.setText("�������µ�����");
			}
		}
	}
}
