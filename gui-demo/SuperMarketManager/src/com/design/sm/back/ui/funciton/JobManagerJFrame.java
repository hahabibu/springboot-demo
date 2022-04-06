package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.back.ui.control.DepartmentManagerJPanel;
import com.design.sm.fore.ui.function.AddVendorContactJFrame;
import com.design.sm.fore.ui.function.UpdateVendorContactJFrame;
import com.design.sm.model.VendorContact;
import com.design.sm.service.JobService;
import com.design.sm.service.impl.JobServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;

public class JobManagerJFrame extends JFrame implements MouseListener,
		ItemListener {
	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel tool_add, tool_update, tool_delete;
	// ������Ӧ��service
	JobService jobService = new JobServiceImpl();
	
	DepartmentManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public JobManagerJFrame(DepartmentManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initTablePanel();// ��ʼ����ʾ�ı������
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("ְλ����");
		this.setSize(1000, 550);
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
		// �����ɾ�Ĺ���
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("���ְλ��Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_update = new ImageIcon("icons/toolImage/modify.png");
		tool_update = new JLabel(icon_update);
		tool_update.setToolTipText("�޸�ְλ��Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_update.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ��ְλ��Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_add);
		toolPanel.add(tool_update);
		toolPanel.add(tool_delete);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// Ҫ�����������ѡ�����ɸѡ����(Ҫ����productService�з��ص��н������ã����ѡ��Ҫ���ص���Ŀ����)
		String[] params = { "ְλid","ְλ����","��������","���","ְλ����","��������id","��������"};
		Vector<Vector> vec = new Vector<>();
		// ��ȡ��ǰѡ�еĲ��ŵĲ���id
		String deptId = parentTable.getValueAt(selectedRow, 0).toString();
		// ͨ������id��ȡ��ǰѡ�в��ŵ�����ְλ��Ϣ
		try {
			vec = jobService.pack(jobService.getJobByDeptmentId(deptId));
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
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_add) {
			// ���ְλ��Ϣ
			new AddJobJFrame(this);
		} else if (e.getSource() == tool_update) {
			// ��ȡ��ǰѡ��Ҫ����������
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ�ְλ��Ϣ");
			} else {
				// �޸�ְλ��Ϣ
				new UpdateJobJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ����ְλ��Ϣ");
			} else {
				// ɾ��ְλ��Ϣ
				int choose = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����ְλ��Ϣ��");
				if (choose == 0) {
					try {
						jobService.deleteJob(table.getValueAt(
								row, 0).toString());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "ְλ��Ϣɾ���ɹ���");
					// ˢ���������
					this.refreshTablePanel();
				}
			}
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
	 * ˢ���������
	 */
	public void refreshTablePanel() {
		tablePanel.removeAll();
		initTablePanel();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// ��������������ѡ�����ˢ���������,Ҫ�Ƚ��������������Ƴ�֮�������
			tablePanel.removeAll();// �Ƴ���������е���������
			initTablePanel();// ���³�ʼ�����
		}
	}
}