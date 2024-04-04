package com.design.sm.fore.ui.function;

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

import com.design.sm.model.VendorContact;
import com.design.sm.service.VendorContactService;
import com.design.sm.service.VendorService;
import com.design.sm.service.impl.VendorContactServiceImpl;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;

public class VendorContactManagerJFrame extends JFrame implements
		MouseListener, ItemListener {
	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel, searchPanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel tool_add, tool_update, tool_delete, tool_leadChange;
	// ������Ӧ���Ұ�ť���˴���Ҫ�Ը����˺���ͨ�����˽��з��ࣩ
	ButtonGroup lead_identity;
	JRadioButton all, lead, normal;
	// ������Ӧ��service
	VendorService vendorService = new VendorServiceImpl();
	VendorContactService contactService = new VendorContactServiceImpl();
	VendorManagerJFrame parentPanel;
	JTable parentTable;
	int selectedRow;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public VendorContactManagerJFrame(VendorManagerJFrame parentPanel,
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
		this.setTitle("ͨѶ¼����");
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
		tool_add.setToolTipText("�����������Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_update = new ImageIcon("icons/toolImage/modify.png");
		tool_update = new JLabel(icon_update);
		tool_update.setToolTipText("�޸���������Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_update.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ����������Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		Icon icon_lead = new ImageIcon("icons/toolImage/lead.png");
		tool_leadChange = new JLabel(icon_lead);
		tool_leadChange.setToolTipText("��ݹ���");// ��������ƶ�ʱ����ʾ����
		tool_leadChange.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_add);
		toolPanel.add(tool_update);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_leadChange);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ��������������ݱ�ʶ����ɸѡ
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		lead_identity = new ButtonGroup();
		all = new JRadioButton("����");
		all.addItemListener(this);
		lead = new JRadioButton("������");
		lead.addItemListener(this);
		normal = new JRadioButton("��ͨ����Ա");
		normal.addItemListener(this);
		lead_identity.add(all);
		lead_identity.add(lead);
		lead_identity.add(normal);
		searchPanel.add(all);
		searchPanel.add(lead);
		searchPanel.add(normal);
		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// Ҫ�����������ѡ�����ɸѡ����(Ҫ����productService�з��ص��н������ã����ѡ��Ҫ���ص���Ŀ����)
		String[] params = { "������id", "������", "��ϵ��ʽ", "��������", "������Ӧ��id", "������Ӧ��",
				"��ݱ�ʶ", "���" };
		Vector<Vector> vec = new Vector<>();
		// ��ȡ��ǰѡ�еĹ�Ӧ�̵Ĺ�Ӧ��id
		String vendorId = parentTable.getValueAt(selectedRow, 0).toString();
		try {
			if (all.isSelected()) {
				vec = contactService.pack(this.getVendorContactByIdentity(vendorId,
						-1));
			} else if (lead.isSelected()) {
				vec = contactService.pack(this
						.getVendorContactByIdentity(vendorId, 1));
			} else if (normal.isSelected()) {
				vec = contactService.pack(this
						.getVendorContactByIdentity(vendorId, 0));
			}
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
		// ���أ�0 4 6
		// dcm.getColumn(0).setMinWidth(0);
		// dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);
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
			// �����������Ϣ
			 new AddVendorContactJFrame(this);
		} else if (e.getSource() == tool_update) {
			// ��ȡ��ǰѡ��Ҫ����������
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���������Ϣ");
			} else {
				// �޸���������Ϣ
				 new UpdateVendorContactJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������������Ϣ");
			} else {
				// ɾ����������Ϣ
				int choose = JOptionPane.showConfirmDialog(null, "ȷ��ɾ������������Ϣ��");
				if (choose == 0) {
					try {
						contactService.deleteVendorContact(table.getValueAt(
								row, 0).toString());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "��������Ϣɾ���ɹ���");
					// ˢ���������
					this.refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_leadChange) {
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ���в�������ϵ�ˣ�");
			} else {
				VendorContact vc = null;
				try {
					vc = contactService.getVendorContactByContactId(table.getValueAt(row, 0).toString());
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				if(vc.getOwner_flag()==0){
					int choose = JOptionPane.showConfirmDialog(null, "ȷ��ָ����Ϊ�����ˣ�");
					if(choose==0){
						try {
							vc.setOwner_flag(1);
							contactService.updateVendorContact(vc);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// ˢ�����������Ϣ
						parentPanel.refreshTablePanel();
					}
				}else if(vc.getOwner_flag()==1){
					int choose = JOptionPane.showConfirmDialog(null, "ȷ��ȡ���为������ݣ�");
					if(choose==0){
						try {
							vc.setOwner_flag(0);
							contactService.updateVendorContact(vc);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// ˢ�����������Ϣ
						parentPanel.refreshTablePanel();
					}
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

	// ���ݵ�ѡ��ťѡ���ȡ��Ӧ����ϵ����Ϣ
	public List<VendorContact> getVendorContactByIdentity(String vendorId,
			int choose) {
		List<VendorContact> all_list = null;
		try {
			all_list = contactService.getVendorContactByVendorId(vendorId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<VendorContact> vc_list = new ArrayList<VendorContact>();
		if (choose == -1) {
			// ��ʾ���е���ϵ����Ϣ
			vc_list = all_list;
		} else if (choose == 0) {
			// ��ʾ��ͨ����Ա����Ϣ
			for (VendorContact vc : all_list) {
				if (vc.getOwner_flag() == 0) {
					vc_list.add(vc);
				}
			}
		} else if (choose == 1) {
			// ��ʾ�����˵���Ϣ
			for (VendorContact vc : all_list) {
				if (vc.getOwner_flag() == 1) {
					vc_list.add(vc);
				}
			}
		}
		return vc_list;
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
