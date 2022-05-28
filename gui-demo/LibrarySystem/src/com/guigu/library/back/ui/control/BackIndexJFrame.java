package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.guigu.library.back.ui.function.UpdateBackUserJFrame;
import com.guigu.library.model.Setting;
import com.guigu.library.model.Users;
import com.guigu.library.service.SettingService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.SettingServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyFont;

public class BackIndexJFrame extends JFrame implements MouseListener {
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
	JLabel infoSearch, booksManager, readerManager, systemSetup, label_user,
			switchUsers;
	// ����JTabbedPane
	JTabbedPane jTabbedPane;
	// ������Ӧ��service
	private UsersService userService = new UsersServiceImpl();
	private SettingService settingService = new SettingServiceImpl();

	/**
	 * 2.��ʼ�����������ò���
	 */
	public BackIndexJFrame(Users user) {
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
		this.setTitle("��̨����");
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
		topMenu.setPreferredSize(new Dimension(800, 40));
		// ����͸��
		topMenu.setOpaque(false);
		// ���ñ�ǩ���֡�������������¼�
		String[] nameStrings = { "��Ϣ��ѯ", "ͼ�����", "���߹���", "ϵͳ����" };
		infoSearch = createMenuJLabel(infoSearch, nameStrings[0], "infoSearch",
				topMenu);
		infoSearch.setName("infoSearch");
		infoSearch.addMouseListener(this);

		booksManager = createMenuJLabel(booksManager, nameStrings[1],
				"booksManager", topMenu);
		booksManager.setName("booksManager");
		booksManager.addMouseListener(this);

		readerManager = createMenuJLabel(readerManager, nameStrings[2],
				"readerManager", topMenu);
		readerManager.setName("readerManager");
		readerManager.addMouseListener(this);

		systemSetup = createMenuJLabel(systemSetup, nameStrings[3],
				"systemSetup", topMenu);
		systemSetup.setName("systemSetup");
		systemSetup.addMouseListener(this);
	}

	// ���������˵���JLabel��ǩ
	public JLabel createMenuJLabel(JLabel jl, String text, String name,
			JPanel who) {
		// ������Ӧ��ǩ���á���ǩ�ı�����ǩͼ�����ơ�Ҫ��ӵ��ĸ����
		// ���÷ָ���
		JLabel line = new JLabel(
				"<html>&nbsp;<font color='#D2D2D2' ></font>&nbsp;</html>");
		Icon icon = new ImageIcon("icons/indexImage/" + name + ".png");
		jl = new JLabel(icon);
		jl.setText("<html><font color='balck'>" + text + "</font></html>");
		jl.addMouseListener(this);// ��Ӽ����¼�
		jl.setFont(MyFont.JLabelFont);
		who.add(jl);
		// ������Ǳ�ǩ�����һ�����������Ӧ������
		if (!"systemSetup".equals(name)) {
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
		// ������Ϣ��ѯ�����
		createInfoSearchTab();
		// ���ÿռ��Ƿ�Ϊ͸����
		centerPanel.setOpaque(false);
	}

	/**
	 * ������Ϣ��ѯ��壺 ��������ͼ����Ĳ�ѯ��������Ϣͳ�ơ�ͼ��ݵ�����ѯ
	 */
	private void createInfoSearchTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ���������ͼ����Ĳ�ѯ��������Ϣͳ�ơ�ͼ�鵵����ѯ��
		// jTabbedPane.addTab("������",
		// new NoticeBoardJPanel(this.user).backgroundPanel);// ��ӹ�����ҳ��
		 jTabbedPane.addTab("ͼ�����",new BooksBRSearchJPanel(this.user).backgroundPanel);// ���ͼ����Ĳ�ѯҳ��
		 jTabbedPane.addTab("��Ϣͳ��", new InfoStatisticsJPanel(this.user).backgroundPanel);// ���ͼ�������Ϣͳ��ҳ��
		 jTabbedPane.addTab("ͼ�鵵��",new BooksArchivesJPanel(this.user).backgroundPanel);// ���ͼ�鵵��ҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * ����ͼ�������壺ͼ�����͹���ͼ�鵵������ͼ��軹����
	 */
	public void createBooksManagerTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ�ͼ�����͹���ͼ�鵵������ͼ��軹������
		jTabbedPane.addTab("ͼ�����͹���",
				new BooksClassifyManagerJPanel(this.user).backgroundPanel);
		// ���ͼ�鵵������ҳ��
		jTabbedPane.addTab("ͼ�鵵������",
				new BooksArchivesManagerJPanel(this.user).backgroundPanel);
		// ���ͼ��軹����ҳ��
		// jTabbedPane.addTab("ͼ��軹����",
		// new BooksLoanJPanel(this.user).backgroundPanel);// ���ͼ��軹����ҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * �������߹�����壺�������͹������ߵ�������
	 */
	public void createReaderManagerTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ��������͹������ߵ�������
		jTabbedPane.addTab("�������͹���",
				new ReaderClassifyManagerJPanel(user).backgroundPanel);// ��Ӷ������͹���ҳ��
		jTabbedPane.addTab("���ߵ�������",
				new ReaderArchivesManagerJPanel(user).backgroundPanel);// ��Ӷ��ߵ�������ҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * ����ϵͳ������壺ͼ�����Ϣ������Ա����
	 */
	public void createSystemSetupTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ�ͼ�����Ϣ������Ա���ã�
		jTabbedPane
				.addTab("ͼ�����Ϣ", new LibraryInfoJPanel(user).backgroundPanel);
		jTabbedPane.addTab("����Ա����", new AdminSetJPanel(user).backgroundPanel);// ��ӹ���Ա����ҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * �ڵ����ҳ��ʱ�����Ȩ���ж�
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// ��ȡ��ص�������Ϣ���жϵ�ǰ��¼���û��Ƿ�����Ӧ��Ȩ��
		Setting s = null;
		try {
			s = settingService.getUsersSettingById(this.user.getUser_id());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (e.getSource() == switchUsers) {
			// ����л��û������ص�ǰҳ�棬�����¼ҳ��
			this.setVisible(false);
			new BackLoginJFrame();
		} else if (e.getSource() == label_user) {
			// ����û�������ʾ��ǰ��¼�û����û������û���ǰ��Ȩ�ޣ��û�������Ӧ���޸��û���������
			new UpdateBackUserJFrame(this, this.user);
		} else if (s != null) {
			// �����Ӧ�Ĳ���Ҫ����ҳ��ˢ��
			if (e.getSource() == infoSearch) {
				if (s.getInfoSearch() == 1) {
					createInfoSearchTab();
					infoSearch
							.setText("<html><font color='#336699' style='font-weight:bold'>��Ϣ��ѯ</font>&nbsp;</html>");
					booksManager
							.setText("<html><font color='black'>ͼ�����</font>&nbsp;</html>");
					readerManager
							.setText("<html><font color='black'>���߹���</font>&nbsp;</html>");
					systemSetup
							.setText("<html><font color='black'>ϵͳ����</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޹����ģ�飡");
				}
			} else if (e.getSource() == booksManager) {
				if (s.getBooksManager() == 1) {
					createBooksManagerTab();
					infoSearch
							.setText("<html><font color='black'>��Ϣ��ѯ</font>&nbsp;</html>");
					booksManager
							.setText("<html><font color='#336699' style='font-weight:bold'>ͼ�����</font>&nbsp;</html>");
					readerManager
							.setText("<html><font color='black'>���߹���</font>&nbsp;</html>");
					systemSetup
							.setText("<html><font color='black'>ϵͳ����</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޹����ģ�飡");
				}
			} else if (e.getSource() == readerManager) {
				if (s.getReaderManager() == 1) {
					createReaderManagerTab();
					infoSearch
							.setText("<html><font color='black'>��Ϣ��ѯ</font>&nbsp;</html>");
					booksManager
							.setText("<html><font color='black'>ͼ�����</font>&nbsp;</html>");
					readerManager
							.setText("<html><font color='#336699' style='font-weight:bold'>���߹���</font>&nbsp;</html>");
					systemSetup
							.setText("<html><font color='black'>ϵͳ����</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޹����ģ�飡");
				}
			} else if (e.getSource() == systemSetup) {
				if (s.getSystemSetup() == 1) {
					createSystemSetupTab();
					infoSearch
							.setText("<html><font color='black'>��Ϣ��ѯ</font>&nbsp;</html>");
					booksManager
							.setText("<html><font color='black'>ͼ�����</font>&nbsp;</html>");
					readerManager
							.setText("<html><font color='black'>���߹���</font>&nbsp;</html>");
					systemSetup
							.setText("<html><font color='#336699' style='font-weight:bold'>ϵͳ����</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޹����ģ�飡");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"��Ǹ������ǰû���κ�Ȩ�޽���ģ�����������ϵ��������Ա����Ȩ�����ã�");
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
