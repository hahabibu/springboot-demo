package com.guigu.library.back.ui.control;

import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.guigu.library.model.Users;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.ImagePanel;
import com.guigu.library.utils.MyFont;

public class BackLoginJFrame extends JFrame implements MouseListener,
		FocusListener, KeyListener {
	// ����ȫ�����
	private JPanel backgroundPanel = null;
	private JTextField username = null;
	private JPasswordField password = null;
	private JLabel login_label = null;
	private JLabel reset_label = null;
	// ������Ӧ��service
	private UsersService usersService = new UsersServiceImpl();

	// �ڹ��췽���г�ʼ����������ò���
	public BackLoginJFrame() {
		// ���ô�����⡢ͼ��
		try {
			Image logoImage = null;
			logoImage = ImageIO.read(new File("icons/loginImage/logo.png"));
			this.setIconImage(logoImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ���ر���ͼƬ
		Image backgroungImage = null;
		try {
			backgroungImage = ImageIO.read(new File(
					"icons/loginImage/loginBackgroundImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// ��ʼ��������岢���ò��ֹ�����Ϊnull
		backgroundPanel = new ImagePanel(backgroungImage);
		backgroundPanel.setLayout(null);
		// ����û����������ı���
		username = new JTextField(15);
		username.setFont(MyFont.JTableFont);
		username.setText("**");
		username.setBounds(460, 231, 245, 45);
		password = new JPasswordField(15);
		password.setFont(MyFont.JTableFont);
		password.setText("����");
		password.setBounds(460, 300, 245, 45);

		// ��¼ע�ᰴť
		Icon login_image = new ImageIcon("icons/loginImage/loginImage.png");
		login_label = new JLabel(login_image);
		login_label.setToolTipText("��¼");
		login_label.setBounds(400, 375, 64, 64);

		Icon reset_image = new ImageIcon("icons/loginImage/resetImage.png");
		reset_label = new JLabel(reset_image);
		reset_label.setToolTipText("����");
		reset_label.setBounds(550, 375, 64, 64);

		// Ϊ�����Ӽ����¼����ı�����ӽ�������¼�����ť�������������¼�
		username.addFocusListener(this);
		password.addFocusListener(this);
		login_label.addMouseListener(this);
		reset_label.addMouseListener(this);

		// �����е�������ص��������
		backgroundPanel.add(username);
		backgroundPanel.add(password);
		backgroundPanel.add(login_label);
		backgroundPanel.add(reset_label);

		// ѡ��һ���������Ӽ��̼���,���������������޷�Ӧ����������ڴ����ʵ��ĵط�
		// ���ý��㣬���xxx.setFocusable(true)��伴��
		this.setFocusable(true);
		this.addKeyListener(this);
		username.addKeyListener(this);
		password.addKeyListener(this);
		
		// ���������װ�ص������У������ô��������
		this.add(backgroundPanel);
		// ���ô�����������
		this.setTitle("��̨����");// ���ô������
		this.setSize(1060, 600);// ���ô����С
		this.setVisible(true);// ���ÿɼ���
		this.setLocationRelativeTo(null);// ����������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرշ�ʽ
		this.setResizable(false);// ���ý�ֹ���
		this.requestFocus();// ���ø�ʽ������
	}

	/**
	 * ��ȡ�����¼�
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == username) {
			if (username.getText().equals("**"))
				username.setText("");
		} else if (e.getSource() == password) {
			if (password.getText().equals("����")) {
				password.setText("");
				password.setEchoChar('*');// ���������ַ�
			}
		}
	}

	/**
	 * ʧȥ�����¼�
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == username) {
			if (username.getText().equals(""))
				username.setText("**");
		} else if (e.getSource() == password) {
			if (password.getText().equals("")) {
				password.setText("����");
				password.setEchoChar('\0');// �����ַ���
			}
		}
	}

	/**
	 * ���������¼�
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == login_label) {
			if (username.getText().equals("**")) {
				JOptionPane.showMessageDialog(null, "�û���/�˺Ų���Ϊ�գ�");
			} else if (password.getText().equals("����")) {
				JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
			} else {
				// ��ȡ���ݽ��з�װ
				String username_string = username.getText();
				String password_string = password.getText();
				Users user = new Users();
				user.setUser_name(username_string);
				user.setUser_password(password_string);
				Users findUser = null;
				try {
					findUser = usersService.loginUsers(user);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// �ж��Ƿ��¼�ɹ������뵽��ҳ��
				if (findUser != null) {
					// �жϵ�ǰ�˺��Ƿ񼤻�(0��ʾ�˺�δ�����ʹ��)
					int limit = 0;
					try {
						limit = Integer.valueOf(String.valueOf(usersService
								.getUsersLimits(findUser.getUser_id())));
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
					if (limit == 0) {
						JOptionPane.showMessageDialog(null,
								"��Ǹ����ǰ�˺���δ������ڽ���״̬������ʹ�ã�");
					} else if (limit == 3) {
						JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޷��ʸ�ģ�飡");
					} else {
						// ���ص�ǰ��ҳ�棬���뵽��ҳ��
						this.setVisible(false);
						new BackIndexJFrame(findUser);
					}
				} else {
					JOptionPane.showMessageDialog(null, "�û������������");
				}
			}
		} else if (e.getSource() == reset_label) {
			username.setText("**");
			password.setText("����");
			password.setEchoChar('\0');
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

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			// ���������enter������¼
			if (username.getText().equals("**")) {
				JOptionPane.showMessageDialog(null, "�û���/�˺Ų���Ϊ�գ�");
			} else if (password.getText().equals("����")) {
				JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
			} else {
				// ��ȡ���ݽ��з�װ
				String username_string = username.getText();
				String password_string = password.getText();
				Users user = new Users();
				user.setUser_name(username_string);
				user.setUser_password(password_string);
				Users findUser = null;
				try {
					findUser = usersService.loginUsers(user);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// �ж��Ƿ��¼�ɹ������뵽��ҳ��
				if (findUser != null) {
					// �жϵ�ǰ�˺��Ƿ񼤻�(0��ʾ�˺�δ�����ʹ��)
					int limit = 0;
					try {
						limit = Integer.valueOf(String.valueOf(usersService
								.getUsersLimits(findUser.getUser_id())));
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
					if (limit == 0) {
						JOptionPane.showMessageDialog(null,
								"��Ǹ����ǰ�˺���δ������ڽ���״̬������ʹ�ã�");
					} else if (limit == 3) {
						JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޷��ʸ�ģ�飡");
					} else {
						// ���ص�ǰ��ҳ�棬���뵽��ҳ��
						this.setVisible(false);
						new BackIndexJFrame(findUser);
					}
				} else {
					JOptionPane.showMessageDialog(null, "�û������������");
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
