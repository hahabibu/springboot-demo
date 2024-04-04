package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.model.Department;
import com.design.sm.model.Job;
import com.design.sm.service.DepartmentService;
import com.design.sm.service.JobService;
import com.design.sm.service.impl.DepartmentServiceImpl;
import com.design.sm.service.impl.JobServiceImpl;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class UpdateJobJFrame extends JFrame implements MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_base_sal, label_commission, label_descr,
			label_department;
	JTextField name, base_sal, commission;
	JTextArea descr;
	JComboBox department;
	JButton save_button, reset_button;

	DepartmentService departmentService = new DepartmentServiceImpl();
	JobService jobService = new JobServiceImpl();

	// ���常����
	JobManagerJFrame parentPanel;
	JTable table;
	int selectedRow;

	// ͨ�����췽����ʼ������
	public UpdateJobJFrame(JobManagerJFrame parentPanel, JTable table,
			int selectedRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�޸�ְλ��Ϣ");
		this.setSize(420, 480);
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
		JLabel title = new JLabel("�޸�ְλ��Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		/**
		 * ְλ����Ϣ�� ְλid��������ɵ�10int������ ְλ���ơ��������ʡ���ɡ�ְλ��������Ӧ����id��
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("ְλ����");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_base_sal = new JLabel("��������");
		base_sal = new JTextField(15);
		base_sal.setFont(MyFont.JTextFieldFont);
		base_sal.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_base_sal);
		jp2.add(base_sal);

		JPanel jp3 = new JPanel();
		label_commission = new JLabel("���    ");
		commission = new JTextField(15);
		commission.setFont(MyFont.JTextFieldFont);
		commission.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_commission);
		jp3.add(commission);

		JPanel jp4 = new JPanel();
		label_descr = new JLabel("ְλ����");
		descr = new JTextArea(6, 15);
		descr.setFont(MyFont.JTextFieldFont);
		descr.setForeground(MyColor.JTextFieldColor);
		descr.setLineWrap(true);// �Զ�����
		jp4.add(label_descr);
		jp4.add(descr);

		JPanel jp5 = new JPanel();
		label_department = new JLabel("��������");
		department = new JComboBox();
		department.setPreferredSize(new Dimension(175, 30));
		jp5.add(label_department);
		jp5.add(department);

		// ��������
		this.echoData();
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
			String base_sal_string = base_sal.getText();
			String commission_string = commission.getText();
			String descr_string = descr.getText();
			String department_id_string = "";
			if (name_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "ְλ���Ʋ���Ϊ��");
			} else if (base_sal_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "�������ʲ���Ϊ��");
			} else {
				// ��֤�����Ƿ�Ϸ�
				if (!DataValidation.isBigDecimal(base_sal_string)) {
					JOptionPane.showMessageDialog(null, "�������������ʽ�Ƿ�����");
				} else if (!DataValidation.isSmallDecimal(commission_string)) {
					JOptionPane.showMessageDialog(null, "��������ʽ�Ƿ�����");
				} else {
					double base_sal_double = Double.valueOf(base_sal_string);
					double commission_double = Double
							.valueOf(commission_string);
					// ��ȡ����������ѡ��
					Item select_dept = (Item) department.getSelectedItem();
					department_id_string = select_dept.getKey();
					// ����Job���󣬽����ݱ��浽���ݿ���
					// ְλid��������ɵ�10λint���͵��ַ�ƴ�ӵ�����,���޸�ְλ��Ϣ��ʱ���ܹ��޸�ְλ��Ϣ
					String id = table.getValueAt(selectedRow, 0).toString();
					Job job = new Job(id, name_string, base_sal_double,
							commission_double, descr_string,
							department_id_string);
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ���޸�ְλ��Ϣ��");
					if (choose == 0) {
						try {
							jobService.updateJob(job);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
						JOptionPane.showMessageDialog(null, "ְλ��Ϣ����ɹ�");
						this.setVisible(false);
						parentPanel.refreshTablePanel();
					} else {
						this.setVisible(false);
					}
				}
			}
		} else if (e.getSource() == reset_button) {
			// ��������
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
	 * �������ݻ��Եķ���
	 */
	public void echoData() {
		// �ӵ�ǰѡ�еı�����ݼ�¼�л�ȡ��Ӧ��ְλ��Ϣ�������ݻ���
		String name_string = table.getValueAt(selectedRow, 1).toString();
		String base_sal_string = table.getValueAt(selectedRow, 2).toString();
		String commission_string = table.getValueAt(selectedRow, 3).toString();
		String descr_string = table.getValueAt(selectedRow, 4).toString();
		String department_id_string = table.getValueAt(selectedRow, 5)
				.toString();
		// ���ݻ���
		name.setText(name_string);
		base_sal.setText(base_sal_string);
		commission.setText(commission_string);
		descr.setText(descr_string);

		// ���¼��������б�����������
		department.removeAllItems();
		List<Department> list_dept = null;
		try {
			list_dept = departmentService.findAllDepartment();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i=0;i<list_dept.size();i++) {
			int sign = 0;
			String did = list_dept.get(i).getDepartment_id();
			String dname = list_dept.get(i).getDepartment_name();
			Item item = new Item(did, dname);
			department.addItem(item);
			if(did.equals(department_id_string)){
				sign = i;
				department.setSelectedIndex(sign);
			}
		}
	}
}