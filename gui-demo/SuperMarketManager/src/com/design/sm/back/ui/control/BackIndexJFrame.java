package com.design.sm.back.ui.control;

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

import com.design.sm.back.ui.funciton.UpdateBackUserJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.model.Employee;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.utils.ImagePanel;
import com.design.sm.utils.MyFont;
import com.eltima.components.ui.DatePicker;

public class BackIndexJFrame extends JFrame implements MouseListener {
	/**
	 * 1.������ص�����
	 */
	// �����û�����(��ǰ��¼���û�)
	private Accounts user;
	// �����Ļ�Ĵ�С
	public static final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int height = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	// ����ȫ�����
	JPanel backgroundPanel, topPanel, topMenu, topPrompt, centerPanel,
			subPanel, subMenu;
	// ����JLabel��ǩ
	JLabel home, personnelDept, financialDept, usermanager, label_user,
			switchAccounts;
	// ����JTabbedPane
	JTabbedPane jTabbedPane;

	// ������Ӧ��service
	private AccountsService userService = new AccountsServiceImpl();
	private EmployeeService employeeService = new EmployeeServiceImpl();

	// ���嵱ǰʹ�ø��˺ŵ�Ա��
	Employee emp;

	/**
	 * 2.��ʼ�����������ò���
	 */
	public BackIndexJFrame(Accounts user) {
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
		/**
		 * �ȸ��ݵ�ǰ��¼���˺Ż�ȡʹ�ø��˺ŵ�Ա����Ϣ ���������Ȩ���ж� ��ְͬλ��Ա��ֻ�ܹ�������Ӧ���ŵĹ������
		 */
		// ��ʼ����ǰʹ�ø��˺ŵ�Ա����Ϣ
		emp = null;
		try {
			emp = employeeService.getEmployeeById(employeeService
					.getEmployeeIdByAccountId(this.user.getAccount_id()));
		} catch (SQLException e1) {
			e1.printStackTrace();
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
		topMenu.setPreferredSize(new Dimension(625, 40));
		// ����͸��
		topMenu.setOpaque(false);
		// ���ñ�ǩ���֡�������������¼�
		String[] nameStrings = { "��ҳ", "���²�", "����", "�û�����" };
		home = createMenuJLabel(home, nameStrings[0], "home", topMenu);
		home.setName("home");
		home.addMouseListener(this);

		personnelDept = createMenuJLabel(personnelDept, nameStrings[1],
				"personnelDept", topMenu);
		personnelDept.setName("personnelDept");
		personnelDept.addMouseListener(this);

		financialDept = createMenuJLabel(financialDept, nameStrings[2],
				"financialDept", topMenu);
		financialDept.setName("financialDept");
		financialDept.addMouseListener(this);

		usermanager = createMenuJLabel(usermanager, nameStrings[3],
				"usermanager", topMenu);
		usermanager.setName("usermanager");
		usermanager.addMouseListener(this);
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
		if (!"usermanager".equals(name)) {
			who.add(line);
		}
		return jl;
	}

	// ��ʼ����������Ϣ��ʾ���
	private void initTopPrompt() {
		Icon icon = new ImageIcon("icons/indexImage/backuser.png");
		label_user = new JLabel(icon);
		switchAccounts = new JLabel();
		if (user != null) {
			switchAccounts.setText("�л��û�");
			label_user.setText("<html><font color='balck'>"
					+ "��ӭ��,</font><font color='red'><b>"
					+ this.user.getUsername() + "</b></font></html>");
		} else {
			switchAccounts.setText("��¼");
			label_user.setText("<html><font color='balck'>"
					+ "��û�е�¼��</font></html>");
		}
		// ��Ӽ��������֮���ܹ��鿴�Լ����˻���Ϣ
		label_user.addMouseListener(this);
		// �ṩ�л��û�����
		switchAccounts.addMouseListener(this);
		// ���ñ�ǩ����
		label_user.setFont(MyFont.JLabelFont);
		topPrompt = new JPanel();
		topPrompt.setPreferredSize(new Dimension(225, 40));
		topPrompt.add(label_user);
		topPrompt.add(switchAccounts);
	}

	private void initCenterPanel() {
		centerPanel = new JPanel(new BorderLayout());
		// ������ҳ�����
		createHome();
		// ���ÿռ��Ƿ�Ϊ͸����
		centerPanel.setOpaque(false);
	}

	/**
	 * ������ҳ��� ��ӿ��ڴ򿨡��л��û������ھ������ƣ�
	 */
	private void createHome() {
		// �Ƴ����е������������Ӧ�����
		centerPanel.removeAll();
		try {
			Image backgroundImage = ImageIO.read(new File(
					"icons/indexImage/indexbackground.png"));
			ImagePanel centerBackground = new ImagePanel(backgroundImage);
			centerPanel.add(centerBackground, BorderLayout.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
		backgroundPanel.validate();
	}

	/**
	 * �������²���� ���²��ĺ��Ļ���ҵ�����Ա���������Ź���������Ӧ��ְλ��������Ч����
	 */
	public void createPersonnelDeptTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ�Ա���������Ź�����Ч���ˣ�
		jTabbedPane.addTab("Ա������",
				new EmployeeManagerJPanel(this.user).backgroundPanel);// ���Ա������ҳ��
		jTabbedPane.addTab("���Ź���",
				new DepartmentManagerJPanel(this.user).backgroundPanel);// ��Ӳ��Ź���ҳ��
		// jTabbedPane.addTab("��Ч����", new
		// CheckingJPanel(this.user).backgroundPanel);// ��Ӽ�Ч����ҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * ����������� ���񲿵ĺ��Ļ���ҵ����Ҫ�������ʽ��㡢��֧���㡢����ͳ�ơ������ѯ
	 */
	public void createFinancialDeptTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ����ʽ��㡢��֧���㡢����ͳ�ơ������ѯ��
		jTabbedPane.addTab("���ʽ���",
				new WageSettlementJPanel(user).backgroundPanel);// ��ӹ��ʽ���ҳ��
		jTabbedPane.addTab("��֧����", new BalanceJPanel(user).backgroundPanel);// �����֧����ҳ��
		// jTabbedPane.addTab("����ͳ��", new
		// StockManagerJPanel(user).backgroundPanel);// ��Ӳ���ͳ��ҳ��
		// jTabbedPane.addTab("�����ѯ", new
		// StockManagerJPanel(user).backgroundPanel);// ��Ӳ����ѯҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * �����û�������� �û�����ĺ���ҵ���ǣ��˺Ź��� �ɳ�������Աʵ��
	 */
	public void createAccountsManagerTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ��˺Ź���
		jTabbedPane.addTab("�˺Ź���",
				new AccountsMangerJPanel(user).backgroundPanel);// ����˺Ź���ҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * �ڵ����ҳ��ʱ�����Ȩ���ж�
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// ���ݵ�ǰ��¼���û���ȡ�û��������Ϣ�������䵱ǰ�Ĳ��š�ְλ���Բ�ͬ��Ȩ�޷��ʲ�ͬ��ģ��
		// �����Ӧ�Ĳ���Ҫ����ҳ��ˢ��
		if (e.getSource() == home) {
			// ��ҳ
			createHome();
			home.setText("<html><font color='#336699' style='font-weight:bold'>��ҳ</font>&nbsp;</html>");
			personnelDept
					.setText("<html><font color='black' style='font-weight:bold'>���²�</font>&nbsp;</html>");
			financialDept
					.setText("<html><font color='black' style='font-weight:bold'>����</font>&nbsp;</html>");
			usermanager
					.setText("<html><font color='black' style='font-weight:bold'>�û�����</font>&nbsp;</html>");
		} else if (e.getSource() == personnelDept) {
			if (emp != null) {
				String dept_id = emp.getDepartment_id();
				if (dept_id.equals("8132456543")
						|| dept_id.equals("0653000153")) {
					// ���²�Ա����¼��ϵͳά��Ա����¼
					createPersonnelDeptTab();
					home.setText("<html><font color='#336699' style='font-weight:bold'>��ҳ</font>&nbsp;</html>");
					personnelDept
							.setText("<html><font color='black' style='font-weight:bold'>���²�</font>&nbsp;</html>");
					financialDept
							.setText("<html><font color='black' style='font-weight:bold'>����</font>&nbsp;</html>");
					usermanager
							.setText("<html><font color='black' style='font-weight:bold'>�û�����</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޷��ʸ�ģ�飡");
				}
			}
		} else if (e.getSource() == financialDept) {
			if (emp != null) {
				String dept_id = emp.getDepartment_id();
				if (dept_id.equals("7168462722")
						|| dept_id.equals("0653000153")) {
					// ����Ա����¼��ϵͳά��Ա����¼
					createFinancialDeptTab();
					home.setText("<html><font color='#336699' style='font-weight:bold'>��ҳ</font>&nbsp;</html>");
					personnelDept
							.setText("<html><font color='black' style='font-weight:bold'>���²�</font>&nbsp;</html>");
					financialDept
							.setText("<html><font color='black' style='font-weight:bold'>����</font>&nbsp;</html>");
					usermanager
							.setText("<html><font color='black' style='font-weight:bold'>�û�����</font>&nbsp;</html>");
				}else {
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޷��ʸ�ģ�飡");
				}
			} 
		} else if (e.getSource() == usermanager) {
			if (emp != null) {
				String dept_id = emp.getDepartment_id();
				if (dept_id.equals("0653000153")) {
					// �û�����--ϵͳά��Ա����¼
					createAccountsManagerTab();
					home.setText("<html><font color='#336699' style='font-weight:bold'>��ҳ</font>&nbsp;</html>");
					personnelDept
							.setText("<html><font color='black' style='font-weight:bold'>���²�</font>&nbsp;</html>");
					financialDept
							.setText("<html><font color='black' style='font-weight:bold'>����</font>&nbsp;</html>");
					usermanager
							.setText("<html><font color='black' style='font-weight:bold'>�û�����</font>&nbsp;</html>");
				} else {
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޷��ʸ�ģ�飡");
				}
			}
		} else if (e.getSource() == switchAccounts) {
			// ����л��û������ص�ǰҳ�棬�����¼ҳ��
			this.setVisible(false);
			new BackLoginJFrame();
		} else if (e.getSource() == label_user) {
			// ����û�������ʾ��ǰ��¼�û����û������û���ǰ��Ȩ�ޣ��û�������Ӧ���޸��û���������
			new UpdateBackUserJFrame(this, this.user);
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
