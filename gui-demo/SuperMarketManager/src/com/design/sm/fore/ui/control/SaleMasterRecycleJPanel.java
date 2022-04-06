package com.design.sm.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.dao.impl.SaleOrderServiceImpl;
import com.design.sm.model.Accounts;
import com.design.sm.service.ProductService;
import com.design.sm.service.SaleMasterService;
import com.design.sm.service.SaleOrderService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.SaleMasterServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Tools;

public class SaleMasterRecycleJPanel implements MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel tool_clean, tool_revoke;
	// ������Ӧ��service
	SaleMasterService saleMasterService = new SaleMasterServiceImpl();
	SaleOrderService saleOrderService = new SaleOrderServiceImpl();
	ProductService productService = new ProductServiceImpl();
	Accounts loginUser;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public SaleMasterRecycleJPanel(Accounts loginUser) {
		this.loginUser = loginUser;
		backgroundPanel = new JPanel(new BorderLayout());
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
		// �������˵������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ�����������
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();

		Icon icon_clean = new ImageIcon("icons/toolImage/clean.png");
		tool_clean = new JLabel(icon_clean);
		tool_clean.setToolTipText("����ɾ��");// ��������ƶ�ʱ����ʾ����
		tool_clean.addMouseListener(this);// ���������

		Icon icon_revoke = new ImageIcon("icons/toolImage/revoke.png");
		tool_revoke = new JLabel(icon_revoke);
		tool_revoke.setToolTipText("����ɾ��");// ��������ƶ�ʱ����ʾ����
		tool_revoke.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_clean);
		toolPanel.add(tool_revoke);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ��������ݽ��в���
		String[] params = { "��������id", "�������", "������id", "������", "�˿�id", "�˿�",
				"����ʱ��", "ɾ����ʶ", "ɾ����ʶ����", "״̬��ʶ", "����״̬" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = saleMasterService.pack(saleMasterService.findAllSaleListRecycleBin());
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
		// ���� 2 4 7 9 10
		dcm.getColumn(2).setMinWidth(0);
		dcm.getColumn(2).setMaxWidth(0);
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);
		dcm.getColumn(9).setMinWidth(0);
		dcm.getColumn(9).setMaxWidth(0);
		dcm.getColumn(10).setMinWidth(0);
		dcm.getColumn(10).setMaxWidth(0);

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
		if (e.getSource() == tool_clean) {
			// ɾ����ǰѡ�е�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ����ɾ�������ۼ�¼");
			} else {
				// ��ȡ��ǰѡ�вֿ��id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null,
						"ȷ�ϳ���ɾ��������¼��Ϣ��");
				if (result == 0) {
					// ȷ��ɾ������ִ��ɾ��
					try {
						saleMasterService.deleteSaleMaster(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "�����ۼ�¼��ɾ����");
					refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_revoke) {
			// ��������ɾ����Ϣ
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ����ɾ�������ۼ�¼");
			} else {
				// ��ȡ��ǰѡ���е�id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "ȷ�ϳ�������ɾ����¼��");
				if (result == 0) {
					// ȷ�ϳ�����ִ�г�������
					try {
						saleMasterService.revokeRecycleBin(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "��ɾ����¼�ѳ�����");
					refreshTablePanel();
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
}