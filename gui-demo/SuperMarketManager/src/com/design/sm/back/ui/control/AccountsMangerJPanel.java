package com.design.sm.back.ui.control;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;

import com.design.sm.back.ui.funciton.AddAccountsJFrame;
import com.design.sm.back.ui.funciton.UpdateAccountsJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.model.Employee;
import com.design.sm.service.AccountsService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.JobService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.service.impl.JobServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class AccountsMangerJPanel implements MouseListener, FocusListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_keyword, tool_add, tool_modify, tool_delete,tool_power,tool_forbidden;
	JTextField keyword;
	JButton search_button;
	// ������Ӧ��service
	AccountsService userService = new AccountsServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	JobService jobService = new JobServiceImpl();
	private Accounts user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public AccountsMangerJPanel(Accounts user) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.user = user;
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initTablePanel();// ��ʼ����ʾ�ı������
	}

	/**
	 * ��ʼ�������Ĳ˵���
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// ��ʼ�����������
		initToolPanel();
		// ��ʼ���������
		initSearchPanel();
		// �������˵������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ�����������
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();

		// �����ɾ�Ĺ���
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("�½��˺�");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("�޸��˺�");// ��������ƶ�ʱ����ʾ����
		tool_modify.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ���˺�");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		Icon icon_power = new ImageIcon("icons/toolImage/power.png");
		tool_power = new JLabel(icon_power);
		tool_power.setToolTipText("Ȩ�޹���");// ��������ƶ�ʱ����ʾ����
		tool_power.addMouseListener(this);// ���������

		Icon icon_forbidden = new ImageIcon("icons/toolImage/forbidden.png");
		tool_forbidden = new JLabel(icon_forbidden);
		tool_forbidden.setToolTipText("�����˺�");// ��������ƶ�ʱ����ʾ����
		tool_forbidden.addMouseListener(this);// ���������
		
		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_add);
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_power);
		toolPanel.add(tool_forbidden);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ���������
	 */
	private void initSearchPanel() {

		searchPanel = new JPanel();

		label_keyword = new JLabel("�û�����");
		label_keyword.setFont(MyFont.JLabelFont);

		keyword = new JTextField(15);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("�������û�����");
		keyword.addFocusListener(this);

		search_button = new JButton("����");
		search_button.setFocusable(false);
		search_button.addMouseListener(this);

		// �����������ص�ָ���������
		searchPanel.add(label_keyword);
		searchPanel.add(keyword);
		searchPanel.add(search_button);
		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ��������ݽ��в���
		String[] params = { "�˺�id", "�û�����", "�û�����", "�û�����" ,"�û�Ȩ��", "�û����" };
		Vector<Vector> vec = new Vector<>();
		if (!keyword.getText().equals("�������û�����")) {
			try {
				// ƴ�Ӳ��ҵ��ַ���
				String text = "%" + keyword.getText() + "%";
				vec = userService.getAccountsByNameKeyword(text);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				vec = userService.findAllAccountsVector();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// ����ѯ�������ݷ�װ��BbaseTableModule��
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		dcm.getColumn(0).setMinWidth(0);// ���ص�1��
		dcm.getColumn(0).setMaxWidth(0);// ���ص�1��
		dcm.getColumn(2).setMinWidth(0);// ���ص�3��
		dcm.getColumn(2).setMaxWidth(0);// ���ص�3��

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
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == search_button) {
			// �������˲���ѡ�������ɸѡ
			tablePanel.removeAll();// �Ƴ���������е���������
			initTablePanel();// ���³�ʼ�����
			backgroundPanel.validate();
		}
		if (e.getSource() == tool_add) {
			// �����û���Ϣ
			 new AddAccountsJFrame(this);
		} else if (e.getSource() == tool_modify) {
			// ��ȡ��ǰѡ��Ҫ�޸ĵ�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ��û�");
			} else {
				// �޸��û���Ϣ
				 new UpdateAccountsJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			// ɾ����ǰѡ�е�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����˺���Ϣ");
			} else {
				// ��ȡ��ǰѡ�з����id
				String id = (String) table.getValueAt(row, 0);
				Employee emp = null;
				try {
					emp = employeeService.getEmployeeById(employeeService.getEmployeeIdByAccountId(table.getValueAt(row,0).toString()));
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				int result = -1;
				if(emp!=null){
					 result = JOptionPane.showConfirmDialog(null, "��ǰ�˺�����Ա����ʹ�ã�ȷ��ɾ�������˺���Ϣ��");
				}else{
					result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ�������˺���Ϣ��");
				}
				if (result == 0) {
					// ȷ��ɾ������ִ��ɾ��
					try {
						userService.deleteAccounts(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "�˺���Ϣɾ���ɹ���");
					refreshTablePanel();
				}
			}
		}else if(e.getSource()==tool_power){
			// Ϊ�����Ա��Ч�ʣ���������Ա���˺Ž���Ȩ�޹�����������Ա���˺ţ�
			int[] rows = table.getSelectedRows();
			if(rows.length==0){
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ������Ա��");
			}else{
				try {
					for(int i = 0;i<rows.length;i++){
						// �û�Ȩ�޹���
						/**
						 * �Ȼ�ȡʹ�ø��˺ŵ�Ա����Ϣ��ͨ�����Ŷ�Ӧ��ְλ�趨��Ӧ��Ȩ�ޣ�
						 * �������ͨ�������ж���ְλ�Ƿ�Ϊ���ܡ�����Ӷ��޸�Ȩ�޴�0->2
						 * �������ͨ���ŵ���ͨԱ�����޸�Ȩ�޴�0-3
						 * �����ϵͳά����Ա����ֱ������Ϊ��������Ա���޸�Ȩ�޴�0->1
						 */
						Employee emp = employeeService.getEmployeeById(employeeService.getEmployeeIdByAccountId(table.getValueAt(rows[i],0).toString()));
						if(emp!=null){
							// ���ж��Ƿ�Ϊϵͳά�޲�
							if(emp.getDepartment_id().equals("0653000153")){
								Accounts newAccount = userService.getAccountsById(table.getValueAt(rows[i], 0).toString());
								newAccount.setLimits(1);
								userService.updateAccounts(newAccount);
							}else{
								//�ж��Ƿ�Ϊ��ͨԱ�������Ǹ߼�Ա��
								// �˴�ͨ��Ա��ְλ�ƺŽ����жϣ����ڱ׶�
								// �����Կ����˹������жϣ���ʾ��ǰԱ����ְλ��ϢȻ���ֶ��޸ģ������ڱ׶ˣ�û�취����һ���Խ��в�����
								String jobName = jobService.getJobName(emp.getJob_id())+"";
								if(jobName.contains("����")||jobName.contains("����")){
									Accounts newAccount = userService.getAccountsById(table.getValueAt(rows[i], 0).toString());
									newAccount.setLimits(2);
									userService.updateAccounts(newAccount);
								}else{
									// �˴�Ĭ�ϴ���߼�Ա��֮�����Ϊ����ͨԱ��
									// ����������ְλ��ʶ�����Ա�ʶ�����ڹ���㻹����ͨԱ����
									Accounts newAccount = userService.getAccountsById(table.getValueAt(rows[i], 0).toString());
									newAccount.setLimits(3);
									userService.updateAccounts(newAccount);
								}
							}
						}else{
							JOptionPane.showMessageDialog(null, "�в����˺�û��Ա����ʹ�ã��޷����в�����");
						}
					}
					JOptionPane.showMessageDialog(null, "Ա���˺���������ɹ���");
					this.refreshTablePanel();
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		}else if(e.getSource()==tool_forbidden){
			// ����ѡ�е�Ա���˺�
			int row = table.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ���õ��˺���Ϣ");
			}else{
				try {
					int choose = JOptionPane.showConfirmDialog(null, "ȷ�Ͻ��ø��˺���Ϣ��");
					if(choose==0){
						Accounts newAccount = userService.getAccountsById(table.getValueAt(row, 0).toString());
						newAccount.setLimits(0);
						userService.updateAccounts(newAccount);
						JOptionPane.showMessageDialog(null, "���˺��ѽ��ã�");
						this.refreshTablePanel();
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
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

	// ��ȡ�����¼�
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("�������û�����")) {
				keyword.setText("");
			}
		}
	}

	// ʧȥ�����¼�
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
				keyword.setText("�������û�����");
			} else {
				keyword.setForeground(MyColor.JTextFieldColor);
				keyword.setFont(MyFont.JTextFieldFont);
			}
		}
	}
}
