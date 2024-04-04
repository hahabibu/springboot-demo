package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.back.ui.control.EmployeeManagerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.utils.MyFont;

public class ShowEmployeeDetailJFrame extends JFrame implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_employee_id, label_employee_num, label_employee_name,
			label_id_card, label_age, label_gender, label_phone, label_email,
			label_address, label_account, label_hire_date, label_job,
			label_department;
	JTextField employee_id, employee_num, employee_name, id_card, age, gender,
			phone, email, address, account, hire_date, job, department;

	JButton front, back, exit;
	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ���常���󡢵�ǰ��¼Ա��\���ѡ����
	EmployeeManagerJPanel parentPanel;
	Accounts loginUser;
	JTable table;
	int selectRow;
	int currentRow;

	// ͨ�����췽����ʼ������
	public ShowEmployeeDetailJFrame(EmployeeManagerJPanel parentPanel,
			Accounts loginUser,JTable table, int selectRow) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.table = table;
		this.selectRow = selectRow;
		this.currentRow = selectRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�鿴Ա����ϸ��Ϣ");
		this.setSize(600, 470);
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
		JLabel title = new JLabel("Ա����ϸ��Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {

		/**
		 * �ӱ���л�ȡ��ǰѡ��Ա����������Ϣ
		 */
		String employee_id_string = table.getValueAt(currentRow, 0).toString();
		String employee_num_string = table.getValueAt(currentRow, 1).toString();
		String employee_name_string = table.getValueAt(currentRow, 2)
				.toString();
		String id_card_string = table.getValueAt(currentRow, 3).toString();
		String age_string = table.getValueAt(currentRow, 4).toString();
		String gender_string = table.getValueAt(currentRow, 5).toString();
		String phone_string = table.getValueAt(currentRow, 6).toString();
		String email_string = table.getValueAt(currentRow, 7).toString();
		String address_string = table.getValueAt(currentRow, 8).toString();
		String hire_date_string = table.getValueAt(currentRow, 9).toString();
		String account_string = table.getValueAt(currentRow, 11).toString();
		String job_string = table.getValueAt(currentRow, 13).toString();
		String department_string = table.getValueAt(currentRow, 15).toString();

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_employee_id = new JLabel("Ա��id  ");
		employee_id = new JTextField(15);
		employee_id.setFont(MyFont.JTextFieldFont);
		employee_id.setText(employee_id_string);
		employee_id.setToolTipText(employee_id_string);
		employee_id.setEditable(false);
		jp1.add(label_employee_id);
		jp1.add(employee_id);
		label_employee_num = new JLabel("Ա�����");
		employee_num = new JTextField(15);
		employee_num.setFont(MyFont.JTextFieldFont);
		employee_num.setText(employee_num_string);
		employee_num.setToolTipText(employee_num_string);
		employee_num.setEditable(false);
		jp1.add(label_employee_num);
		jp1.add(employee_num);

		JPanel jp2 = new JPanel();
		label_employee_name = new JLabel("Ա������");
		employee_name = new JTextField(15);
		employee_name.setFont(MyFont.JTextFieldFont);
		employee_name.setText(employee_name_string);
		employee_name.setToolTipText(employee_name_string);
		employee_name.setEditable(false);
		jp2.add(label_employee_name);
		jp2.add(employee_name);
		label_id_card = new JLabel("���֤��");
		id_card = new JTextField(15);
		id_card.setFont(MyFont.JTextFieldFont);
		id_card.setText(id_card_string);
		id_card.setToolTipText(id_card_string);
		id_card.setEditable(false);
		jp2.add(label_id_card);
		jp2.add(id_card);

		JPanel jp3 = new JPanel();
		label_age = new JLabel("Ա������");
		age = new JTextField(15);
		age.setFont(MyFont.JTextFieldFont);
		age.setText(age_string);
		age.setToolTipText(age_string);
		age.setEditable(false);
		jp3.add(label_age);
		jp3.add(age);
		label_gender = new JLabel("Ա���Ա�");
		gender = new JTextField(15);
		gender.setFont(MyFont.JTextFieldFont);
		gender.setText(gender_string);
		gender.setToolTipText(gender_string);
		gender.setEditable(false);
		jp3.add(label_gender);
		jp3.add(gender);

		JPanel jp4 = new JPanel();
		label_phone = new JLabel("��ϵ��ʽ");
		phone = new JTextField(15);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setText(phone_string);
		phone.setToolTipText(phone_string);
		phone.setEditable(false);
		jp4.add(label_phone);
		jp4.add(phone);
		label_email = new JLabel("��������");
		email = new JTextField(15);
		email.setFont(MyFont.JTextFieldFont);
		email.setText(email_string);
		email.setToolTipText(email_string);
		email.setEditable(false);
		jp4.add(label_email);
		jp4.add(email);

		JPanel jp5 = new JPanel();
		label_address = new JLabel("��ͥסַ");
		address = new JTextField(15);
		address.setFont(MyFont.JTextFieldFont);
		address.setText(address_string);
		address.setToolTipText(address_string);
		address.setEditable(false);
		jp5.add(label_address);
		jp5.add(address);
		label_account = new JLabel("ʹ���˺�");
		account = new JTextField(15);
		account.setFont(MyFont.JTextFieldFont);
		account.setText(account_string);
		account.setToolTipText(account_string);
		account.setEditable(false);
		jp5.add(label_account);
		jp5.add(account);

		JPanel jp6 = new JPanel();
		label_hire_date = new JLabel("��Ӷ����");
		hire_date = new JTextField(15);
		hire_date.setFont(MyFont.JTextFieldFont);
		hire_date.setText(hire_date_string);
		hire_date.setToolTipText(hire_date_string);
		hire_date.setEditable(false);
		jp6.add(label_hire_date);
		jp6.add(hire_date);
		label_job = new JLabel("��ְ��λ");
		job = new JTextField(15);
		job.setFont(MyFont.JTextFieldFont);
		job.setText(job_string);
		job.setToolTipText(department_string);
		job.setEditable(false);
		jp6.add(label_job);
		jp6.add(job);

		JPanel jp7 = new JPanel();
		label_department = new JLabel("��������");
		department = new JTextField(15);
		department.setFont(MyFont.JTextFieldFont);
		department.setText(department_string);
		department.setToolTipText(department_string);
		department.setEditable(false);
		jp7.add(label_department);
		jp7.add(department);

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
		// ��������ص�contentPanel�����
		contentPanel.add(ver);
	}

	/**
	 * ��ʼ����ť���
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		front = new JButton("��һ��");
		front.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		front.setForeground(Color.white);
		front.setFont(MyFont.JButtonFont);
		front.addMouseListener(this);

		back = new JButton("��һ��");
		back.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		back.setForeground(Color.white);
		back.setFont(MyFont.JButtonFont);
		back.addMouseListener(this);

		exit = new JButton("�˳�");
		exit.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		exit.setForeground(Color.white);
		exit.setFont(MyFont.JButtonFont);
		exit.addMouseListener(this);

		buttonPanel.add(front);
		buttonPanel.add(back);
		buttonPanel.add(exit);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == front) {
			if (currentRow > 0) {
				currentRow = currentRow - 1;
				// ˢ���������
				refreshPanel();
			} else {
				JOptionPane.showMessageDialog(null, "ǰ��û�������ˣ�");
			}
		} else if (e.getSource() == back) {
			if (currentRow < table.getRowCount() - 1) {
				currentRow = currentRow + 1;
				// ˢ���������
				refreshPanel();
			} else {
				JOptionPane.showMessageDialog(null, "����û�������ˣ�");
			}
		} else if (e.getSource() == exit) {
			this.setVisible(false);
			;
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

	private void refreshPanel() {
		// ˢ����ʾ�������
		backgroundPanel.remove(contentPanel);
		initContentPanel();
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}
}
