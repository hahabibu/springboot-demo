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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.back.ui.control.EmployeeManagerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.Department;
import com.design.sm.model.Employee;
import com.design.sm.model.Job;
import com.design.sm.service.AccountsService;
import com.design.sm.service.DepartmentService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.JobService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.DepartmentServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.JobServiceImpl;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;
import com.eltima.components.ui.DatePicker;

public class AddEmployeeJFrame extends JFrame implements FocusListener,
		MouseListener ,ItemListener{

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_employee_name, label_id_card, label_age, label_gender,
			label_phone, label_email, label_address, label_hire_date,
			label_job, label_department;
	JTextField employee_name, id_card, age, phone, email, address;
	ButtonGroup gender;
	JRadioButton nan, nv;
	DatePicker hire_date;
	JComboBox department, job;
	JButton save, reset;
	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ����service
	EmployeeService employeeService = new EmployeeServiceImpl();
	DepartmentService departmentService = new DepartmentServiceImpl();
	JobService jobService = new JobServiceImpl();
	AccountsService accountsService = new AccountsServiceImpl();

	// ���常���󡢵�ǰ��¼Ա�������ѡ����
	Accounts loginUser;
	EmployeeManagerJPanel parentPanel;

	// ͨ�����췽����ʼ������
	public AddEmployeeJFrame(EmployeeManagerJPanel parentPanel,
			Accounts loginUser) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("���Ա����Ϣ");
		this.setSize(600, 580);
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
		JLabel title = new JLabel("���Ա����Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_employee_name = new JLabel("Ա������");
		employee_name = new JTextField(15);
		employee_name.setFont(MyFont.TipFont);
		employee_name.setForeground(MyColor.TipColor);
		employee_name.setText("������");
		employee_name.addFocusListener(this);
		jp1.add(label_employee_name);
		jp1.add(employee_name);

		JPanel jp2 = new JPanel();
		label_id_card = new JLabel("���֤��");
		id_card = new JTextField(15);
		id_card.setFont(MyFont.TipFont);
		id_card.setForeground(MyColor.TipColor);
		id_card.setText("������");
		id_card.addFocusListener(this);
		jp2.add(label_id_card);
		jp2.add(id_card);

		JPanel jp3 = new JPanel();
		label_age = new JLabel("Ա������");
		age = new JTextField(15);
		age.setFont(MyFont.TipFont);
		age.setForeground(MyColor.TipColor);
		age.setText("��ѡ��");
		age.addFocusListener(this);
		jp3.add(label_age);
		jp3.add(age);

		JPanel jp4 = new JPanel();
		label_gender = new JLabel("Ա���Ա�");
		gender = new ButtonGroup();
		nan = new JRadioButton("��");
		nv = new JRadioButton("Ů");
		gender.add(nan);
		gender.add(nv);
		nan.setSelected(true);// Ĭ��ѡ����
		jp4.add(label_gender);
		jp4.add(nan);
		jp4.add(nv);

		JPanel jp5 = new JPanel();
		label_phone = new JLabel("��ϵ��ʽ");
		phone = new JTextField(15);
		phone.setFont(MyFont.TipFont);
		phone.setForeground(MyColor.TipColor);
		phone.setText("��ѡ��");
		phone.addFocusListener(this);
		jp5.add(label_phone);
		jp5.add(phone);

		JPanel jp6 = new JPanel();
		label_email = new JLabel("��������");
		email = new JTextField(15);
		email.setFont(MyFont.TipFont);
		email.setForeground(MyColor.TipColor);
		email.setText("��ѡ��");
		email.addFocusListener(this);
		jp6.add(label_email);
		jp6.add(email);

		JPanel jp7 = new JPanel();
		label_address = new JLabel("��ͥסַ");
		address = new JTextField(15);
		address.setFont(MyFont.TipFont);
		address.setForeground(MyColor.TipColor);
		address.setText("��ѡ��");
		address.addFocusListener(this);
		jp7.add(label_address);
		jp7.add(address);

		JPanel jp8 = new JPanel();
		label_hire_date = new JLabel("��ְ����");
		String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		// ��ǰʱ��
		hire_date = new DatePicker(new Date(), DefaultFormat,
				MyFont.JTextFieldFont, new Dimension(177, 24));
		hire_date.setSize(new Dimension(180, 50));
		jp8.add(label_hire_date);
		jp8.add(hire_date);

		JPanel jp9 = new JPanel();
		label_department = new JLabel("��������");
		department = new JComboBox();
		department.setPreferredSize(new Dimension(175, 30));
		// ���ز�����Ϣ
		List<Department> dept_list = null;
		try {
			dept_list = departmentService.findAllDepartment();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Department d : dept_list) {
			String did = d.getDepartment_id();
			String dname = d.getDepartment_name();
			Item item = new Item(did, dname);
			department.addItem(item);
		}
		department.addItemListener(this);
		jp9.add(label_department);
		jp9.add(department);

		JPanel jp10 = new JPanel();
		label_job = new JLabel("��ְ��λ");
		job = new JComboBox();
		job.setPreferredSize(new Dimension(175, 30));
		job.addItem("��");
		// �����û�ѡ��Ĳ��ŵı仯��������ʾ��Ӧ��ְλ��Ϣ
		jp10.add(label_job);
		jp10.add(job);

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
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp6);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp7);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp8);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp9);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp10);
		// ��������ص�contentPanel�����
		contentPanel.add(ver);
	}

	/**
	 * ��ʼ����ť���
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		save = new JButton("����");
		save.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		save.setForeground(Color.white);
		save.setFont(MyFont.JButtonFont);
		save.addMouseListener(this);

		reset = new JButton("����");
		reset.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		reset.setForeground(Color.white);
		reset.setFont(MyFont.JButtonFont);
		reset.addMouseListener(this);

		buttonPanel.add(save);
		buttonPanel.add(reset);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// ��ȡ��ǰ�ı�������ݡ������û���������������ݽ��д���
		String employee_name_string = employee_name.getText();
		String id_card_string = id_card.getText();
		String age_string = age.getText();
		String phone_string = phone.getText();
		String email_string = email.getText();
		String address_string = address.getText();
		if (e.getSource() == save) {
			if (employee_name_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "Ա�����Ʋ���Ϊ�գ�");
			} else if (id_card_string.equals("������")) {
				// �ṩĬ��ֵ
				JOptionPane.showMessageDialog(null, "���֤�Ų���Ϊ�գ�");
			} 
			// Ҫע���߼��ж�
			if (age_string.equals("��ѡ��")) {
				age_string = "0";
			}
			if (phone_string.equals("��ѡ��")) {
				phone_string = "��";
			} 
			if (email_string.equals("��ѡ��")) {
				email_string = "��";
			} 
			if (address_string.equals("��ѡ��")) {
				address_string = "��";
			} 
			int age_int;
			// �����ݽ��д���(��֤��ת��)
			if (!DataValidation.isValidIdCard(id_card_string)) {
				JOptionPane.showMessageDialog(null, "��������ȷ�����֤��Ϣ��");
			} else if ((!DataValidation.isValidAge(age_string))&&(!age_string.equals("0"))) {
				JOptionPane.showMessageDialog(null, "�������뷶ΧΪ1-120֮�䣡");
			} else if ((!DataValidation.isValidPhone(phone_string))&&(!phone_string.equals("��"))) {
				JOptionPane.showMessageDialog(null, "��������ȷ���ֻ��ţ�");
			} else if ((!DataValidation.isValidEmail(email_string))&&(!email_string.equals("��"))) {
				JOptionPane.showMessageDialog(null, "��������ȷ�ĵ���������Ϣ��");
			} else {
				int choose = JOptionPane.showConfirmDialog(null, "ȷ�ϱ���Ա����Ϣ��");
				if(choose==0){
					age_int = Integer.valueOf(age_string);
					// ��ȡ��ѡ��ѡ��
					String gender_string = "";
					if(nan.isSelected()){
						gender_string = "��";
					}else if(nv.isSelected()){
						gender_string = "Ů";
					}
					// ��ȡ��ǰ�������������Ϣ(�������š���ְ��λ)
					Item dept_item = (Item) department.getSelectedItem();
					Item job_item = (Item) job.getSelectedItem();
					// ������������Ϣ��ȡ��ӦҪ��װ��������Ϣ
					String dept_id = dept_item.getKey();
					String job_id = job_item.getKey();
					// �������32charԱ��id��������һ���Ĺ�������Ա�����
					String emp_id = RandomGeneration.getRandom32charSeq();
					String emp_num = RandomGeneration.getEmployeeNum(dept_id);//����ǰ׺
					try {
						emp_num += employeeService.getEmployeeNumNextSeq();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}//����¼��ʧ��Ҳ���ܻ�ʧȥһ����ŵ��±�Ŷ϶�����
					String hire_date_string = hire_date.getText();
					/**
					 * �ɻ����Ա����Ϣ���벻�ɹ����Ǵ������˺���Ϣ
					 * ��˿���Ҫ��һ��������д���
					 * ����ڲ��Թ����г����쳣����Ӧ��������һ�����
					 */
					// ���ݵ�ǰ��Ա�����ΪԱ������һ���Ա��Ϊ�û���������Ĭ��Ϊ00000����δ������˺�
					String account_id = RandomGeneration.getRandom10charSeq();
					Accounts newAccount = new Accounts(account_id, emp_num, "000000", 0);
					try {
						accountsService.addAccounts(newAccount);
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					// ����Ա�����󣬼������ݽ��б���
					Employee emp = new Employee(emp_id,employee_name_string,emp_num,id_card_string,age_int,gender_string,
							phone_string,email_string,address_string,hire_date_string,account_id,job_id,dept_id);
					// ���÷�����������
					try {
						employeeService.addEmployee(emp);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// �����ʾ��Ϣ��������ҳ�棬ˢ�������
					JOptionPane.showMessageDialog(null, "Ա����Ϣ����ɹ���");
					this.setVisible(false);
					parentPanel.refreshTablePanelBySearch();
				}
			}
		} else if (e.getSource() == reset) {
			employee_name.setFont(MyFont.TipFont);
			employee_name.setForeground(MyColor.TipColor);
			employee_name.setText("������");

			id_card.setFont(MyFont.TipFont);
			id_card.setForeground(MyColor.TipColor);
			id_card.setText("������");

			age.setFont(MyFont.TipFont);
			age.setForeground(MyColor.TipColor);
			age.setText("��ѡ��");

			nan.setSelected(true);

			phone.setFont(MyFont.TipFont);
			phone.setForeground(MyColor.TipColor);
			phone.setText("��ѡ��");

			email.setFont(MyFont.TipFont);
			email.setForeground(MyColor.TipColor);
			email.setText("��ѡ��");

			address.setFont(MyFont.TipFont);
			address.setForeground(MyColor.TipColor);
			address.setText("��ѡ��");

			String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
			
			// ��ǰʱ��
			hire_date = new DatePicker(new Date(), DefaultFormat,
					MyFont.JTextFieldFont, new Dimension(177, 24));

			department.setSelectedIndex(0);
			job.setSelectedIndex(0);
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
	 * ��ȡ�����¼�
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == employee_name) {
			if (employee_name.getText().equals("������")) {
				employee_name.setFont(MyFont.JTextFieldFont);
				employee_name.setForeground(MyColor.JTextFieldColor);
				employee_name.setText("");
			}
		} else if (e.getSource() == id_card) {
			if (id_card.getText().equals("������")) {
				id_card.setFont(MyFont.JTextFieldFont);
				id_card.setForeground(MyColor.JTextFieldColor);
				id_card.setText("");
			}
		} else if (e.getSource() == age) {
			if (age.getText().equals("��ѡ��")) {
				age.setFont(MyFont.JTextFieldFont);
				age.setForeground(MyColor.JTextFieldColor);
				age.setText("");
			}
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("��ѡ��")) {
				phone.setFont(MyFont.JTextFieldFont);
				phone.setForeground(MyColor.JTextFieldColor);
				phone.setText("");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("��ѡ��")) {
				email.setFont(MyFont.JTextFieldFont);
				email.setForeground(MyColor.JTextFieldColor);
				email.setText("");
			}
		} else if (e.getSource() == address) {
			if (address.getText().equals("��ѡ��")) {
				address.setFont(MyFont.JTextFieldFont);
				address.setForeground(MyColor.JTextFieldColor);
				address.setText("");
			}
		}
	}

	/**
	 * ʧȥ�����¼�
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == employee_name) {
			if (employee_name.getText().equals("")) {
				employee_name.setFont(MyFont.TipFont);
				employee_name.setForeground(MyColor.TipColor);
				employee_name.setText("������");
			}
		} else if (e.getSource() == id_card) {
			if (id_card.getText().equals("")) {
				id_card.setFont(MyFont.TipFont);
				id_card.setForeground(MyColor.TipColor);
				id_card.setText("������");
			}
		} else if (e.getSource() == age) {
			if (age.getText().equals("")) {
				age.setFont(MyFont.TipFont);
				age.setForeground(MyColor.TipColor);
				age.setText("��ѡ��");
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
		} else if (e.getSource() == address) {
			if (address.getText().equals("")) {
				address.setFont(MyFont.TipFont);
				address.setForeground(MyColor.TipColor);
				address.setText("��ѡ��");
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange()==ItemEvent.SELECTED){
			// ����û�ѡ������Ӧ�Ĳ��ţ��������Ӧ��ְλ������ѡ��
			// ��ȡ��ǰ�û���ѡ��,�Ƴ�job��������������ݣ����¼�������
			job.removeAllItems();
			Item item_select = (Item)department.getSelectedItem();
			String deptId = item_select.getKey();
			List<Job> list_job = null;
			try {
				list_job = jobService.getJobByDeptmentId(deptId);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			for(Job j : list_job){
				String jid = j.getJob_id();
				String jname = j.getJob_name();
				Item item = new Item(jid,jname);
				job.addItem(item);
			}
		}
	}
}