package com.design.sm.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.back.ui.funciton.AddEmployeeJFrame;
import com.design.sm.back.ui.funciton.ShowEmployeeDetailJFrame;
import com.design.sm.back.ui.funciton.UpdateEmployeeJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.model.Department;
import com.design.sm.service.AccountsService;
import com.design.sm.service.DepartmentService;
import com.design.sm.service.EmployeeService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.DepartmentServiceImpl;
import com.design.sm.service.impl.EmployeeServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class EmployeeManagerJPanel implements MouseListener, ItemListener,
		FocusListener {

	// ����ȫ�����
	public JPanel backgroundPanel, topPanel, toolPanel, searchPanel,
			tablePanel, pagePanel;
	// ���������б�
	JComboBox select_department;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_department, label_keyword, tool_add, tool_modify, tool_delete,
			tool_detail, tool_import, tool_export;
	// ������Ӧ���ı�����ϰ�ť
	JTextField keyword;
	JButton search;
	int maxPage;
	int currentPage;
	// ������Ӧ�ķ�ҳ��ϱ�ǩ
	JLabel label_all, label_start, label_end, label_last, label_next;
	JTextField page;
	// ������Ӧ��service
	AccountsService accountsService = new AccountsServiceImpl();
	EmployeeService employeeService = new EmployeeServiceImpl();
	DepartmentService departmentService = new DepartmentServiceImpl();
	Accounts loginUser;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public EmployeeManagerJPanel(Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.loginUser = loginUser;
		try {
			maxPage = employeeService.getEmployeeMaxPage();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initTablePanel();// ��ʼ����ʾ�ı������
		initPagePanel();// �����ҳ���Ұ�ť���
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
		toolPanel = new JPanel(new BorderLayout());
		// �����ɾ�Ĺ���
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("¼��Ա����Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("�޸�Ա����Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_modify.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ��Ա����Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		Icon icon_detail = new ImageIcon("icons/toolImage/detail.png");
		tool_detail = new JLabel(icon_detail);
		tool_detail.setToolTipText("Ա����ϸ��Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_detail.addMouseListener(this);// ���������

		Icon icon_import = new ImageIcon("icons/toolImage/import.png");
		tool_import = new JLabel(icon_import);
		tool_import.setToolTipText("����Ա����Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_import.addMouseListener(this);// ���������

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("����Ա����Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_export.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		JPanel north = new JPanel();
		north.add(tool_add);
		north.add(tool_modify);
		north.add(tool_delete);
		JPanel south = new JPanel();
		south.add(tool_detail);
		south.add(tool_import);
		south.add(tool_export);
		toolPanel.add(north, BorderLayout.NORTH);
		toolPanel.add(south, BorderLayout.SOUTH);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ�� 1.�����������Ų��� 2.Ա�����ƹؼ��ֲ���
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel(new BorderLayout());
		// ����Ա�����������б����������
		label_department = new JLabel("��������");
		select_department = new JComboBox();
		select_department.addItem("��������");
		select_department.addItemListener(this);
		// ��ȡ���ݿ�������Ա��������Ϣ
		List<Department> list_dept = null;
		try {
			list_dept = departmentService.findAllDepartment();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ����ȡ��ÿ��������Ϣ��װΪItem����
		for (Department d : list_dept) {
			// ��װid��name��Ϣ
			String did = d.getDepartment_id();
			String dname = d.getDepartment_name();
			Item item = new Item(did, dname);
			select_department.addItem(item);
		}

		// ���ùؼ��ֲ���
		label_keyword = new JLabel("Ա������");
		keyword = new JTextField(10);
		keyword.setText("�ؼ���");
		keyword.addFocusListener(this);
		search = new JButton("����");
		search.addMouseListener(this);

		// �����������ص�ָ���������
		JPanel north = new JPanel();
		north.add(label_department);
		north.add(select_department);
		JPanel south = new JPanel();
		south.add(label_keyword);
		south.add(keyword);
		south.add(search);
		searchPanel.add(north, BorderLayout.NORTH);
		searchPanel.add(south, BorderLayout.SOUTH);
		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// Ҫ�����������ѡ�����ɸѡ����(Ҫ����employeeService�з��ص��н������ã����ѡ��Ҫ���ص���Ŀ����)
		String[] params = { "Ա��id", "Ա�����", "Ա������", "���֤��", "����", "�Ա�", "��ϵ��ʽ",
				"��������", "��ͥסַ", "��ְ����", "�˺�id", "�˺�����", "ְλid", "����ְλ", "����id",
				"��������","��������","���" };
		Vector<Vector> vec = new Vector<>();
		// ��ȡ��ǰ�������ѡ��
		Object dItem = select_department.getSelectedItem();
		if (!dItem.equals("��������")) {
			try {
				Item item = (Item) dItem;
				vec = employeeService.pack(employeeService
						.getEmployeeByDeptId(item.getKey()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				vec = employeeService.pack(employeeService.findAllEmployee());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// ����ѯ�������ݷ�װ��BbaseTableModule��
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// ��Ⱦ��0�У�������ʾΪ��ѡ�������ʾ
		table.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						JCheckBox ck = new JCheckBox();
						ck.setSelected(isSelected);
						ck.setHorizontalAlignment((int) 0.5f);
						return ck;
					}
				});
		// �����ṩ��Tools���������
		Tools.setTableStyle(table);

		// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// ���أ�3 4 5 8 10 12 14
		dcm.getColumn(3).setMinWidth(0);
		dcm.getColumn(3).setMaxWidth(0);
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(10).setMinWidth(0);
		dcm.getColumn(10).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);
		dcm.getColumn(16).setMinWidth(0);
		dcm.getColumn(16).setMaxWidth(0);
		dcm.getColumn(17).setMinWidth(0);
		dcm.getColumn(17).setMaxWidth(0);
		// ���ù�����
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// ����͸����
		tablePanel.add(jScrollPane);
		// ��������ص�������
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		// ��ˢ�����ݵ�ʱ��ı䴰���С���������ˢ��
		backgroundPanel.validate();
	}

	/**
	 * �����ҳ���Ұ�ť���
	 */
	private void initPagePanel() {
		pagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		label_all = new JLabel("��ʾ����");
		label_all.setFont(MyFont.JLabelFont);
		label_all.setForeground(MyColor.JLabelColor);
		label_all.addMouseListener(this);

		label_start = new JLabel("��ҳ");
		label_start.setFont(MyFont.JLabelFont);
		label_start.setForeground(MyColor.JLabelColor);
		label_start.addMouseListener(this);

		label_end = new JLabel("βҳ");
		label_end.setFont(MyFont.JLabelFont);
		label_end.setForeground(MyColor.JLabelColor);
		label_end.addMouseListener(this);

		page = new JTextField(5);
		page.setFont(MyFont.JLabelFont);
		page.setForeground(MyColor.JLabelColor);
		page.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				currentPage = 1;
				refreshTablePanelByPage();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(page.getText()).matches()) {
					currentPage = Integer.valueOf(page.getText());
					refreshTablePanelByPage();
				} else {
					JOptionPane.showMessageDialog(null, "������Ϸ������֣�");
					currentPage = 1;
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		label_last = new JLabel("��һҳ");
		label_last.setFont(MyFont.JLabelFont);
		label_last.setForeground(MyColor.JLabelColor);
		label_last.addMouseListener(this);

		label_next = new JLabel("��һҳ");
		label_next.setFont(MyFont.JLabelFont);
		label_next.setForeground(MyColor.JLabelColor);
		label_next.addMouseListener(this);

		pagePanel.add(label_all);
		pagePanel.add(label_start);
		pagePanel.add(label_last);
		pagePanel.add(page);
		pagePanel.add(label_next);
		pagePanel.add(label_end);
		// ��������ص�������
		backgroundPanel.add(pagePanel, BorderLayout.SOUTH);
		// ��ˢ�����ݵ�ʱ��ı䴰���С���������ˢ��
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == label_all) {
			refreshTablePanelBySearch();
		} else if (e.getSource() == label_start) {
			page.setText("1");
			refreshTablePanelByPage();
		} else if (e.getSource() == label_end) {
			page.setText(String.valueOf(maxPage));
			refreshTablePanelByPage();
		} else if (e.getSource() == label_last) {
			// ��ȡ��ǰҳ�����бȽ�
			if (currentPage > 1) {
				page.setText((currentPage - 1) + "");
			} else if (currentPage == 1) {
				page.setText("1");
			}
			refreshTablePanelByPage();
		} else if (e.getSource() == label_next) {
			// ��ȡ��ǰҳ�����бȽ�
			if (currentPage < maxPage) {
				page.setText((currentPage + 1) + "");
			} else if (currentPage == maxPage) {
				page.setText(maxPage + "");
			}
			refreshTablePanelByPage();
		} else if (e.getSource() == tool_add) {
			// ���Ա����Ϣ
			 new AddEmployeeJFrame(this, this.loginUser);
		} else if (e.getSource() == tool_modify) {
			// ��ȡ��ǰѡ��Ҫ�޸ĵ�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ�Ա��");
			} else {
				 new UpdateEmployeeJFrame(this, loginUser, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			// ɾ����ǰѡ�е�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ����Ա��");
			} else {
				// ��ȡ��ǰѡ��Ա����id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��������Ϣ��");
				if (result == 0) {
					// ȷ��ɾ������ִ��ɾ��
					try {
						employeeService.deleteEmployee(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Ա����Ϣɾ���ɹ�����");
					// ɾ��������֮��Ҫˢ�����
					refreshTablePanelBySearch();
				}
			}
		} else if (e.getSource() == search) {
			// ���Ƴ���ǰ������������
			tablePanel.removeAll();
			String[] params = { "Ա��id", "Ա�����", "Ա������", "���֤��", "����", "�Ա�",
					"��ϵ��ʽ", "��������", "��ͥסַ", "��ְ����", "�˺�id", "�˺�����", "ְλid",
					"����ְλ", "����id", "��������" ,"��������","���"};
			Vector<Vector> vec = new Vector<>();
			// ��ȡ�ı����Լ���ѡ�������
			String keyword_string = keyword.getText();
			if (keyword_string.equals("�ؼ���")) {
				try {
					vec = employeeService.pack(employeeService
							.findAllEmployee());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else if (!keyword_string.equals("�ؼ���")) {
				String text = "%" + keyword.getText() + "%";
				try {
					vec = employeeService.pack(employeeService
							.getEmployeeByNameKeyword(text));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// ����ѯ�������ݷ�װ��BbaseTableModule��
			baseTableModule = new BaseTableModule(params, vec);
			table = new JTable(baseTableModule);
			// ��Ⱦ��0�У�������ʾΪ��ѡ�������ʾ
			table.getColumnModel().getColumn(0)
					.setCellRenderer(new TableCellRenderer() {
						@Override
						public Component getTableCellRendererComponent(
								JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							JCheckBox ck = new JCheckBox();
							ck.setSelected(isSelected);
							ck.setHorizontalAlignment((int) 0.5f);
							return ck;
						}
					});
			// �����ṩ��Tools���������
			Tools.setTableStyle(table);
			// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
			DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
					.getColumnModel();
			// ���أ�3 4 5 8 10 12 14
			dcm.getColumn(3).setMinWidth(0);
			dcm.getColumn(3).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
			dcm.getColumn(5).setMinWidth(0);
			dcm.getColumn(5).setMaxWidth(0);
			dcm.getColumn(8).setMinWidth(0);
			dcm.getColumn(8).setMaxWidth(0);
			dcm.getColumn(10).setMinWidth(0);
			dcm.getColumn(10).setMaxWidth(0);
			dcm.getColumn(12).setMinWidth(0);
			dcm.getColumn(12).setMaxWidth(0);
			dcm.getColumn(14).setMinWidth(0);
			dcm.getColumn(14).setMaxWidth(0);
			dcm.getColumn(16).setMinWidth(0);
			dcm.getColumn(16).setMaxWidth(0);
			dcm.getColumn(17).setMinWidth(0);
			dcm.getColumn(17).setMaxWidth(0);

			// ���ù�����
			jScrollPane = new JScrollPane(table);
			Tools.setJspStyle(jScrollPane);

			tablePanel = new JPanel(new BorderLayout());
			tablePanel.setOpaque(false);// ����͸����
			tablePanel.add(jScrollPane);
			// ��������ص�������
			backgroundPanel.add(tablePanel, BorderLayout.CENTER);
			// ��ˢ�����ݵ�ʱ��ı䴰���С���������ˢ��
			backgroundPanel.validate();
		} else if (e.getSource() == tool_detail) {
			// �鿴Ա����ϸ��Ϣ
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�鿴��Ա��");
			} else {
				 new ShowEmployeeDetailJFrame(this, loginUser, table, row);
			}
		} else if (e.getSource() == tool_import) {
			// ��������Ա������
			/**
			 * ..........
			 */

		} else if (e.getSource() == tool_export) {
			// ��ȡ��ǰѡ�е�����
			 String[] ids;
			 ArrayList id_list = new ArrayList<>();
			 for (int rowindex : table.getSelectedRows()) {
			 Object obj = table.getValueAt(rowindex, 0);
			 id_list.add(obj);
			 }
			 // ����ת����
			 ids = (String[]) id_list.toArray(new String[id_list.size()]);
			 int result = employeeService.exportData(ids);
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
	public void refreshTablePanelBySearch() {
		// �Ƴ���ǰ��������е���������
		backgroundPanel.removeAll();
		initTopPanel();
		initTablePanel();
		initPagePanel();
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// ��������������ѡ�����ˢ���������,Ҫ�Ƚ��������������Ƴ�֮�������
			tablePanel.removeAll();// �Ƴ���������е���������
			initTablePanel();// ���³�ʼ�����
		}
	}

	/**
	 * �۽��¼�
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("�ؼ���")) {
				keyword.setText("");
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
			}
		}
	}

	/**
	 * ʧȥ�����¼�
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setText("�ؼ���");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}

	/**
	 * ��ҳ��ʾ���÷�����ˢ�±������ �˴���ҳ��Ե�������Ա���ķ�ҳ������������ϲ��ҵ�����
	 */
	public void refreshTablePanelByPage() {
		backgroundPanel.remove(tablePanel);
		// Ҫ�����������ѡ�����ɸѡ����(Ҫ����employeeService�з��ص��н������ã����ѡ��Ҫ���ص���Ŀ����)
		String[] params = { "Ա��id", "Ա�����", "Ա������", "���֤��", "����", "�Ա�", "��ϵ��ʽ",
				"��������", "��ͥסַ", "��ְ����", "�˺�id", "�˺�����", "ְλid", "����ְλ", "����id",
				"��������" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = employeeService.pack(employeeService
					.getAllEmployeeByPage(currentPage));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ����ѯ�������ݷ�װ��BbaseTableModule��
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// ��Ⱦ��0�У�������ʾΪ��ѡ�������ʾ
		table.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						JCheckBox ck = new JCheckBox();
						ck.setSelected(isSelected);
						ck.setHorizontalAlignment((int) 0.5f);
						return ck;
					}
				});
		// �����ṩ��Tools���������
		Tools.setTableStyle(table);

		// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// ���أ�3 4 5 8 10 12 14
		dcm.getColumn(3).setMinWidth(0);
		dcm.getColumn(3).setMaxWidth(0);
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(10).setMinWidth(0);
		dcm.getColumn(10).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);

		// ���ù�����
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// ����͸����
		tablePanel.add(jScrollPane);
		// ��������ص�������
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		// ��ˢ�����ݵ�ʱ��ı䴰���С���������ˢ��
		backgroundPanel.validate();
	}
}
