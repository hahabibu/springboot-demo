package com.design.sm.back.ui.control;

import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Accounts;
import com.design.sm.model.Employee;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.utils.ImagePanel;
import com.design.sm.utils.MyFont;

public class BackLoginJFrame extends JFrame implements MouseListener,FocusListener{
	//����ȫ�����
	private JPanel backgroundPanel = null;
	private JTextField username = null;
	private JPasswordField password = null;
	private JButton login_button = null;
	private JButton reset_button = null;
	//������Ӧ��service
	private AccountsService accountsService = new AccountsServiceImpl();
	private EmployeeService employeeService = new EmployeeServiceImpl();
	//�ڹ��췽���г�ʼ����������ò���
	public BackLoginJFrame()
	{
		//���ô�����⡢ͼ��
		try {
			Image logoImage = null;
			logoImage = ImageIO.read(new File("icons/loginImage/logo.png"));
			this.setIconImage(logoImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//���ر���ͼƬ
		Image backgroungImage = null;
		try {
			backgroungImage = ImageIO.read(new File("icons/loginImage/loginBackgroundImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ʼ��������岢���ò��ֹ�����Ϊnull
		backgroundPanel = new ImagePanel(backgroungImage);
		backgroundPanel.setLayout(null);
		//����û����������ı���
		username = new JTextField(15);
		username.setFont(MyFont.JTableFont);
		username.setText("**");
		username.setBounds(455, 240, 135, 30);
		password = new JPasswordField(15);
		password.setFont(MyFont.JTableFont);
		password.setText("����");
		password.setBounds(455, 290, 135, 30);
		
		//��¼�����ð�ť�趨
		login_button = new JButton();
		login_button.setText("Login");
		login_button.setFont(MyFont.JButtonFont);
		login_button.setBounds(355, 340, 90, 30);
		login_button.setFocusable(false);
		login_button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
		reset_button = new JButton();
		reset_button.setText("Reset");
		reset_button.setFont(MyFont.JButtonFont);
		reset_button.setBounds(490, 340, 90, 30);
		reset_button.setFocusable(false);
		reset_button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		
		//Ϊ�����Ӽ����¼����ı�����ӽ�������¼�����ť�������������¼�
		username.addFocusListener(this);
		password.addFocusListener(this);
		login_button.addMouseListener(this);
		reset_button.addMouseListener(this);
		
		//�����е�������ص��������
		backgroundPanel.add(username);
		backgroundPanel.add(password);
		backgroundPanel.add(login_button);
		backgroundPanel.add(reset_button);
		//���������װ�ص������У������ô��������
		this.add(backgroundPanel);
		//���ô�����������
		this.setTitle("��̨����");//���ô������
		this.setSize(1000,550);//���ô����С
		this.setVisible(true);//���ÿɼ���
		this.setLocationRelativeTo(null);//����������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���ùرշ�ʽ
		this.setResizable(false);//���ý�ֹ���
		this.requestFocus();//���ø�ʽ������
	}
	/**
	 * ��ȡ�����¼�
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource()==username)
		{
			if(username.getText().equals("**"))
				username.setText("");
		}else if(e.getSource()==password)
		{
			if(password.getText().equals("����"))
			{
				password.setText("");
				password.setEchoChar('*');//���������ַ�
			}
		}
	}
	/**
	 * ʧȥ�����¼�
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource()==username)
		{
			if(username.getText().equals(""))
				username.setText("**");
		}else if(e.getSource()==password)
		{
			if(password.getText().equals(""))
			{
				password.setText("����");
				password.setEchoChar('\0');//�����ַ���
			}
		}
	}
	/**
	 * ���������¼�
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==login_button)
		{
			if(username.getText().equals("**"))
			{
				JOptionPane.showMessageDialog(null, "�û���/�˺Ų���Ϊ�գ�");
			}else if(password.getText().equals("����")){
				JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
			}else{
				//��ȡ���ݽ��з�װ
				String username_string = username.getText();
				String password_string = password.getText();
				Accounts user = new Accounts();
				user.setUsername(username_string);
				user.setPassword(password_string);
				Accounts findUser = null;
				try {
					findUser = accountsService.loginAccounts(user);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//�ж��Ƿ��¼�ɹ������뵽��ҳ��
				if(findUser!=null){
					//�жϵ�ǰ�˺��Ƿ񼤻�(0��ʾ�˺�δ�����ʹ��)
					int limit = 0;
					try {
						limit = Integer.valueOf(String.valueOf(accountsService.getAccountsLimits(findUser.getAccount_id())));
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
					if(limit==0){
						JOptionPane.showMessageDialog(null, "��Ǹ����ǰ�˺���δ�������ʹ�ã�");
					}else{
						/**
						 *  �ȸ��ݵ�ǰ��¼���˺Ż�ȡʹ�ø��˺ŵ�Ա����Ϣ
						 *  ���������Ȩ���ж�
						 *  ����������²������񲿡�ϵͳά����Ա���ܵ�¼��̨�������
						 */
						Employee emp = null;
						try {
							emp = employeeService.getEmployeeById(employeeService.getEmployeeIdByAccountId(findUser.getAccount_id()));
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						if(emp!=null){
							String dept_id =  emp.getDepartment_id();
							if(dept_id.equals("8132456543")||dept_id.equals("7168462722")||dept_id.equals("0653000153"))
							{
								JOptionPane.showMessageDialog(null, "�û���¼�ɹ�����ӭ����"+emp.getEmployee_name()+"!");
								//���ص�ǰ��ҳ�棬���뵽��ҳ��
								this.setVisible(false);
								new BackIndexJFrame(findUser);
							}else{
								// ����û��ָ����ӦԱ�����˺����޷�ʹ�õ�
								JOptionPane.showMessageDialog(null, "��Ǹ����ǰ��Ա���˺Ų��ܲ�����̨����ģ�飡");
							}
						}else{
							// ����û��ָ����ӦԱ�����˺����޷�ʹ�õ�
							JOptionPane.showMessageDialog(null, "��ǰ��Ա���˺ŷǷ�������ʹ�ã�");
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "�û������������");
				}
			}
		}else if(e.getSource()==reset_button){
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
}
