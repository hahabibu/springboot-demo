package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.design.sm.back.ui.control.WageSettlementJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.service.DepartmentService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.impl.DepartmentServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;

/**
 * ���и����Ź��ʽ���
 */
public class SalaryBalanceJFrame extends JFrame implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel tool_export;
	// ������Ӧ��service
	EmployeeService employeeService = new EmployeeServiceImpl();
	DepartmentService departmentService = new DepartmentServiceImpl();

	WageSettlementJPanel parentPanel;
	Accounts loginUser;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public SalaryBalanceJFrame(WageSettlementJPanel parentPanel,
			Accounts loginUser) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		backgroundPanel = new JPanel(new BorderLayout());
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initTablePanel();// ��ʼ����ʾ�ı������
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("���ʽ���");
		this.setSize(600, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// ����������
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// ���ùرշ�ʽ
		// ��ǰ�������أ���Ӱ������ݵ�ʹ�ã������ǹر���������
	}

	/**
	 * ��ʼ�������Ĳ˵���
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// ��ʼ�����������
		initToolPanel();
		// �������˵������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ�����������
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("���ݵ���");// ��������ƶ�ʱ����ʾ����
		tool_export.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_export);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ��������ݽ��в���
		String[] params = { "��������", "��������", "�ܹ���", "ƽ������" };
		Vector<Vector> vec = new Vector<>();
		// ���ݲ���idһһ��Ӧ
		Map<String, Integer> emp_count = null;
		Map<String, Double> salary_count = null;
		try {
			emp_count = employeeService.getEmployeeSumByDeptId();
			salary_count = employeeService.getSalarySumByDeptId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ��װ���ҵ��Ľ��
		// ��ȡid
		Object[] key1 = emp_count.keySet().toArray();
		Object[] key2 = salary_count.keySet().toArray();
		for (int i = 0; i < key1.length; i++) {
			for (int j = 0; j < key2.length; j++) {
				if (key1[i].equals(key2[j])) {
					Vector temp = new Vector<String>();
					for (int k = 0; k < 4; k++) {
						try {
							temp.add(departmentService
									.getDepartmentName(key1[i].toString()));
						} catch (SQLException e) {
							e.printStackTrace();
						}// ��������
						temp.add(emp_count.get(key1[i].toString()));// ����������
						temp.add(salary_count.get(key1[i].toString()));// �ܹ���
						// ����ƽ������(����BigDecimal���д�������)
						BigDecimal b1 = BigDecimal.valueOf(salary_count
								.get(key1[i].toString()));
						BigDecimal b2 = BigDecimal.valueOf(emp_count
								.get(key1[i].toString()));
						double avg_sal = Double.valueOf(b1.divide(b2, 2)
								.toString());
						temp.add(avg_sal);
					}
					vec.add(temp);
				}
			}
		}

		// ����ѯ�������ݷ�װ��BbaseTableModule��
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// �����ṩ��Tools���������
		Tools.setTableStyle(table);
		// ���ù�����
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// ����͸����
		tablePanel.add(jScrollPane);
		// ��������ص�������
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_export) {
			// ����ͳ������
			int result = employeeService.exportBalanceData(this.table);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "���ݵ����ɹ���");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "��Ǹ�����ݵ���ʧ�ܣ�����һ��ɣ�");
			} else if (result == 0) {
				JOptionPane.showMessageDialog(null, "�û�ȡ���˲�����");
			}
		}
	}

	/**
	 * ˢ���������
	 */
	public void refreshTablePanel() {
		// �Ƴ���ǰ��������е���������
		backgroundPanel.removeAll();
		initTopPanel();
		initTablePanel();
		backgroundPanel.validate();// ��֤
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