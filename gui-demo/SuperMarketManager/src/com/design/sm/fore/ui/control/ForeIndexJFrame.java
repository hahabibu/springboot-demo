package com.design.sm.fore.ui.control;

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

import com.design.sm.fore.ui.function.UpdateForeUserJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.model.Employee;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.utils.ImagePanel;
import com.design.sm.utils.MyFont;

public class ForeIndexJFrame extends JFrame implements MouseListener {
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
	JLabel home, salesDept, logisticsDept, customerServiceDept, label_user,switchAccounts;
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
	public ForeIndexJFrame(Accounts user) {
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
		 *  �ȸ��ݵ�ǰ��¼���˺Ż�ȡʹ�ø��˺ŵ�Ա����Ϣ
		 *  ���������Ȩ���ж�
		 *  ��ְͬλ��Ա��ֻ�ܹ�������Ӧ���ŵĹ������
		 */
		// ��ʼ����ǰʹ�ø��˺ŵ�Ա����Ϣ
		emp = null;
		try {
			emp = employeeService.getEmployeeById(employeeService.getEmployeeIdByAccountId(this.user.getAccount_id()));
		} catch (SQLException e1) {
			e1.printStackTrace();
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
		String[] nameStrings = { "��ҳ", "Ӫ����", "������", "�ͷ���" };
		home = createMenuJLabel(home, nameStrings[0], "home", topMenu);
		home.setName("home");
		home.addMouseListener(this);

		salesDept = createMenuJLabel(salesDept, nameStrings[1], "salesDept",
				topMenu);
		salesDept.setName("salesDept");
		salesDept.addMouseListener(this);

		logisticsDept = createMenuJLabel(logisticsDept,
				nameStrings[2], "logisticsDept", topMenu);
		logisticsDept.setName("logisticsDept");
		logisticsDept.addMouseListener(this);

		customerServiceDept = createMenuJLabel(customerServiceDept, nameStrings[3],
				"customerServiceDept", topMenu);
		customerServiceDept.setName("customerServiceDept");
		customerServiceDept.addMouseListener(this);
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
		if (!"customerServiceDept".equals(name)) {
			who.add(line);
		}
		return jl;
	}

	// ��ʼ����������Ϣ��ʾ���
	private void initTopPrompt() {
		Icon icon = new ImageIcon("icons/indexImage/foreuser.png");
		label_user = new JLabel(icon);
		switchAccounts = new JLabel();
		if (user != null) {
			switchAccounts.setText("�л��û�");
			label_user.setText("<html><font color='balck'>"
					+ "��ӭ��,</font><font color='red'><b>"
					+ this.user.getUsername() + "</b></font></html>");
		} else {
			switchAccounts.setText("��¼");
			label_user.setText("<html><font color='balck'>" + "��û�е�¼��</font></html>");
		}
		// �ṩ�л��û�����
		switchAccounts.addMouseListener(this);
		// ���ñ�ǩ����
		label_user.setFont(MyFont.JLabelFont);
		label_user.addMouseListener(this);
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
	 * ������ҳ��� ��ӿ��ڴ򿨡��������ӡ��л��û������ھ������ƣ�
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
	 * ����Ӫ������� Ӫ�����ĺ��Ļ���ҵ�������Ʒ���ۡ����۵��Ĺ�������վ
	 */
	public void createSalesDeptTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ���Ʒ���ۡ����۵�������վ��
		jTabbedPane.addTab("��Ʒ����", new ProductSaleJPanel(this.user).backgroundPanel);// �����Ʒ����ҳ��
		jTabbedPane.addTab("���۵�", new SaleMasterJPanel(this.user).backgroundPanel);// ������۵�ҳ��
		jTabbedPane.addTab("����վ", new SaleMasterRecycleJPanel(this.user).backgroundPanel);// ������۵�����վҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * ������������� �������ĺ��Ļ���ҵ����Ҫ�����������ݡ���Ʒ���������� ����ҵ�������Ա�Ͳɹ�Աʵ��
	 */
	public void createLogisticsDeptTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ���������--��Ʒ��Ϣ����Ʒ����������
		 jTabbedPane.addTab("��Ʒ��Ϣ",new BaseDataJPanel(user).backgroundPanel);// ��ӻ�������ҳ��
		 jTabbedPane.addTab("��Ʒ����", new ProductManagerJPanel(user).backgroundPanel);// �����Ʒ����ҳ��
		 jTabbedPane.addTab("������", new StockManagerJPanel(user).backgroundPanel);// ��ӿ�����ҳ��
		 centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		 backgroundPanel.validate();
	}

	/**
	 * �����ͷ������ �ͷ����ĺ���ҵ���ǣ���Ʒ�˻����˿͹����ۺ����
	 */
	public void createCustomerServiceDeptTab() {
		// �Ƴ���ǰ�м����е����
		centerPanel.removeAll();
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		// �����ص����ԣ���Ʒ�˻����˿͹����ȼ������ۺ����
		// jTabbedPane.addTab("��Ʒ�˻�", new
		// ProductRejectionJPanel(user).backgroundPanel);// �����Ʒ�˻�ҳ��
		 jTabbedPane.addTab("�˿͹���", new CustomerManagerJPanel(user).backgroundPanel);// ��ӹ˿͹���ҳ��
		 jTabbedPane.addTab("�ȼ�����", new ConsumeClassManagerJPanel(user).backgroundPanel);// ������ѵȼ�ҳ��
		centerPanel.add(jTabbedPane, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * �ڵ����ҳ��ʱ�����Ȩ���ж� 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// ���ݵ�ǰ��¼���û���ȡ�û��������Ϣ�������䵱ǰ�Ĳ��š�ְλ���Բ�ͬ��Ȩ�޷��ʲ�ͬ��ģ��
		if (e.getSource() == home) {
			// ��ҳ
			createHome();
			home.setText("<html><font color='#336699' style='font-weight:bold'>��ҳ</font>&nbsp;</html>");
			salesDept.setText("<html><font color='black' style='font-weight:bold'>Ӫ����</font>&nbsp;</html>");
			logisticsDept
					.setText("<html><font color='black' style='font-weight:bold'>������</font>&nbsp;</html>");
			customerServiceDept
					.setText("<html><font color='black' style='font-weight:bold'>�ͷ���</font>&nbsp;</html>");
		} else if (e.getSource() == salesDept) {
			if(emp!=null){
				String dept_id =  emp.getDepartment_id();
				if(dept_id.equals("3153961019")||dept_id.equals("0653000153"))
				{
					// Ӫ����Ա����¼��ϵͳά��Ա����¼
					createSalesDeptTab();
					home.setText("<html><font color='#336699' style='font-weight:bold'>��ҳ</font>&nbsp;</html>");
					salesDept.setText("<html><font color='black' style='font-weight:bold'>Ӫ����</font>&nbsp;</html>");
					logisticsDept
							.setText("<html><font color='black' style='font-weight:bold'>������</font>&nbsp;</html>");
					customerServiceDept
							.setText("<html><font color='black' style='font-weight:bold'>�ͷ���</font>&nbsp;</html>");
				}
				else{
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޷��ʸ�ģ�飡");
				}
			}
		} else if (e.getSource() == logisticsDept) {
			if(emp!=null){
				String dept_id =  emp.getDepartment_id();
				if(dept_id.equals("1674836044")||dept_id.equals("0653000153"))
				{
					// ������Ա����¼��ϵͳά��Ա����¼
					createLogisticsDeptTab();
					home.setText("<html><font color='#336699' style='font-weight:bold'>��ҳ</font>&nbsp;</html>");
					salesDept.setText("<html><font color='black' style='font-weight:bold'>Ӫ����</font>&nbsp;</html>");
					logisticsDept
							.setText("<html><font color='black' style='font-weight:bold'>������</font>&nbsp;</html>");
					customerServiceDept
							.setText("<html><font color='black' style='font-weight:bold'>�ͷ���</font>&nbsp;</html>");
				}else{
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޷��ʸ�ģ�飡");
				}
			}
		} else if (e.getSource() == customerServiceDept) {
			if(emp!=null){
				String dept_id =  emp.getDepartment_id();
				if(dept_id.equals("2249164713")||dept_id.equals("0653000153"))
				{
					// �ͷ���Ա����¼��ϵͳά��Ա����¼
					createCustomerServiceDeptTab();
					home.setText("<html><font color='#336699' style='font-weight:bold'>��ҳ</font>&nbsp;</html>");
					salesDept.setText("<html><font color='black' style='font-weight:bold'>Ӫ����</font>&nbsp;</html>");
					logisticsDept
							.setText("<html><font color='black' style='font-weight:bold'>������</font>&nbsp;</html>");
					customerServiceDept
							.setText("<html><font color='black' style='font-weight:bold'>�ͷ���</font>&nbsp;</html>");
				}else{
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޷��ʸ�ģ�飡");
				}
			}
		} else if (e.getSource() == switchAccounts) {
			// ����л��û������ص�ǰҳ�棬�����¼ҳ��
			this.setVisible(false);
			new ForeLoginJFrame();
		}else if(e.getSource()==label_user){
			// �޸��˻���Ϣ
			new UpdateForeUserJFrame(this,this.user);
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
