package com.guigu.library.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.guigu.library.fore.ui.function.UpdateForeUserJFrame;
import com.guigu.library.model.Users;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyFont;

public class ForeIndexJFrame extends JFrame implements MouseListener {
	/**
	 * 1.������ص�����
	 */
	// �����û�����(��ǰ��¼���û�)
	private Users user;
	// �����Ļ�Ĵ�С
	public static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int height = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	// ����ȫ�����
	JPanel backgroundPanel, topPanel, topMenu, topPrompt, centerPanel,
			subPanel, subMenu;
	// ����JLabel��ǩ
	JLabel home, booksLoan, infoManager, label_user, switchUsers;
	// ����JTabbedPane
	JTabbedPane jTabbedPane;
	// ������Ӧ��service
	private UsersService userService = new UsersServiceImpl();

	/**
	 * 2.��ʼ�����������ò���
	 */
	public ForeIndexJFrame(Users user) {
		this.user = user;
		// ����JTabbedPane������
		UIManager.put("TabbedPane.tabAreaInsets",
				new javax.swing.plaf.InsetsUIResource(3, 20, 2, 20));
		// ����logo�ͱ���ͼƬ
		Image logoImage = null;
		try {
			logoImage = ImageIO.read(new File("icons/loginImage/logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.setIconImage(logoImage);
		initBackgroundPanel();
		// ���ô����С
		this.setTitle("ǰ̨����");
		this.setSize((int) (width * 0.9), (int) (height * 0.9));
		this.setLocationRelativeTo(null);// ����������
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * ��ʼ���������
	 */
	private void initBackgroundPanel() {
		/**
		 * ���������Է�Ϊ���������ֽ��в��� topPanel�����˵� centerPanel�м�ı���ͼƬ
		 */
		backgroundPanel = new JPanel(new BorderLayout());
		initTopPanel();// ��ʼ���Ϸ��ı�ǩ���
		initCenterPanel();// ��ʼ���·���ͼƬ�������
		// ����ʼ����ɵ������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);
		this.add(backgroundPanel);
	}

	/**
	 * ��ʼ�������˵�����Ϊ���˵����Ҳಿ����Ϣ��ʾ��
	 */
	private void initTopPanel() {
		initTopMenu();// ��ʼ�����˵�
		initTopPrompt();// ��ʼ���Ҳ����Ϣ��ʾ
		topPanel = new JPanel(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(width, 40));
		topPanel.add(topMenu, BorderLayout.WEST);
		topPanel.add(topPrompt, BorderLayout.EAST);
	}

	/**
	 * initTopMenu
	 */
	private void initTopMenu() {
		topMenu = new JPanel();
		topMenu.setPreferredSize(new Dimension(625, 40));
		// ����͸��
		topMenu.setOpaque(false);
		// ���ñ�ǩ���֡�������������¼�
		String[] nameStrings = { "��ҳ", "ͼ��軹", "��Ϣ����" };
		home = createMenuJLabel(home, nameStrings[0], "home", topMenu);
		home.setName("home");
		home.addMouseListener(this);

		booksLoan = createMenuJLabel(booksLoan, nameStrings[1], "booksLoan",
				topMenu);
		booksLoan.setName("booksLoan");
		booksLoan.addMouseListener(this);

		infoManager = createMenuJLabel(infoManager, nameStrings[2],
				"infoManager", topMenu);
		infoManager.setName("infoManager");
		infoManager.addMouseListener(this);
	}

	// ���������˵���JLabel��ǩ
	public JLabel createMenuJLabel(JLabel jl, String text, String name,
			JPanel who) {
		// ������Ӧ��ǩ���á���ǩ�ı�����ǩͼ�����ơ�Ҫ��ӵ��ĸ����
		// ���÷ָ���
		JLabel line = new JLabel(
				"<html>&nbsp;<font color='#D2D2D2'></font>&nbsp;</html>");
		Icon icon = new ImageIcon("icons/indexImage/" + name + ".png");
		jl = new JLabel(icon);
		jl.setText("<html><font color='balck'>" + text + "</font></html>");
		jl.addMouseListener(this);// ��Ӽ����¼�
		jl.setFont(MyFont.JLabelFont);
		who.add(jl);
		// ������Ǳ�ǩ�����һ�����������Ӧ������
		if (!"infoManager".equals(name)) {
			who.add(line);
		}
		return jl;
	}

	// ��ʼ����������Ϣ��ʾ���
	private void initTopPrompt() {
		Icon icon = new ImageIcon("icons/indexImage/backuser.png");
		label_user = new JLabel(icon);
		switchUsers = new JLabel();
		if (user != null) {
			switchUsers.setText("�л��û�");
			label_user.setText("<html><font color='balck'>"
					+ "��ӭ��,</font><font color='red'><b>"
					+ this.user.getUser_name() + "</b></font></html>");
		} else {
			switchUsers.setText("��¼");
			label_user.setText("<html><font color='balck'>"
					+ "��û�е�¼��</font></html>");
		}
		// ��Ӽ��������֮���ܹ��鿴�Լ����˻���Ϣ
		label_user.addMouseListener(this);
		// �ṩ�л��û�����
		switchUsers.addMouseListener(this);
		// ���ñ�ǩ����
		label_user.setFont(MyFont.JLabelFont);
		topPrompt = new JPanel();
		topPrompt.setPreferredSize(new Dimension(225, 40));
		topPrompt.add(label_user);
		topPrompt.add(switchUsers);
	}

	private void initCenterPanel() {
		centerPanel = new JPanel(new BorderLayout());
		// ������ҳ�����
		createHomeTab();
		// ���ÿռ��Ƿ�Ϊ͸����
		centerPanel.setOpaque(false);
	}

	/**
	 * ������ҳ��壺 ͼ���Ƽ���ͼ���ѯ
	 */
	private void createHomeTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ�ͼ���Ƽ���ͼ���ѯ��
		 jTabbedPane.addTab("ͼ���Ƽ�", new BooksRecommendJPanel(this.user).backgroundPanel);// ���ͼ���Ƽ�ҳ��
		jTabbedPane.addTab("ͼ���ѯ",
				new BooksSearchJPanel(this.user).backgroundPanel);// ���ͼ���ѯҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * ����ͼ��軹��壺 ͼ��ԤԼ��ͼ����ġ�ͼ�����衢ͼ��黹
	 */
	public void createBooksLoanTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ�ͼ��ԤԼ��ͼ����ġ�ͼ�����衢ͼ��黹��
		// jTabbedPane.addTab("ͼ��ԤԼ",
		// new BooksOrderJPanel(this.user).backgroundPanel);// ���ͼ��ԤԼҳ��
		jTabbedPane.addTab("ͼ�����",
				new BooksBorrowJPanel(this.user).backgroundPanel);// ���ͼ�����ҳ��
		jTabbedPane.addTab("ͼ������/�黹",
				new BooksRenewJPanel(this.user).backgroundPanel);// ���ͼ��ͼ������ҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * ������Ϣ������壺 �����˺Ź���������Ϣ��ѯ
	 */
	public void createInfoManagerTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ������˺Ź���������Ϣ��ѯ��
		jTabbedPane.addTab("�����˺�",
				new PersonalAccountJPanel(user).backgroundPanel);// ��Ӹ����˺Ź���ҳ��
		jTabbedPane.addTab("������Ϣ", new BRSearchJPanel(user).backgroundPanel);// ��ӽ�����Ϣ��ѯҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// �����Ӧ�Ĳ���Ҫ����ҳ��ˢ��
		if (e.getSource() == home) {
			createHomeTab();
			home.setText("<html><font color='#336699' style='font-weight:bold'>��ҳ</font>&nbsp;</html>");
			booksLoan
					.setText("<html><font color='black'>ͼ��軹</font>&nbsp;</html>");
			infoManager
					.setText("<html><font color='black'>��Ϣ����</font>&nbsp;</html>");

		} else if (e.getSource() == booksLoan) {
			createBooksLoanTab();
			home.setText("<html><font color='black'>��ҳ</font>&nbsp;</html>");
			booksLoan
					.setText("<html><font color='#336699' style='font-weight:bold'>ͼ��軹</font>&nbsp;</html>");
			infoManager
					.setText("<html><font color='black'>��Ϣ����</font>&nbsp;</html>");

		} else if (e.getSource() == infoManager) {
			createInfoManagerTab();
			home.setText("<html><font color='black''>��ҳ</font>&nbsp;</html>");
			booksLoan
					.setText("<html><font color='black'>ͼ��軹</font>&nbsp;</html>");
			infoManager
					.setText("<html><font color='#336699' style='font-weight:bold'>��Ϣ����</font>&nbsp;</html>");

		} else if (e.getSource() == switchUsers) {
			// ����л��û������ص�ǰҳ�棬�����¼ҳ��
			this.setVisible(false);
			new ForeLoginJFrame();
		} else if (e.getSource() == label_user) {
			// ����û�������ʾ��ǰ��¼�û����û������û���ǰ��Ȩ�ޣ��û�������Ӧ���޸��û���������
			new UpdateForeUserJFrame(this, this.user);
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

}
