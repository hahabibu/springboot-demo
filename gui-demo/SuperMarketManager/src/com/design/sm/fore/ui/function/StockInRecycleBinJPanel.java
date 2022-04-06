package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.model.Accounts;
import com.design.sm.model.Product;
import com.design.sm.model.StockOrder;
import com.design.sm.service.ProductService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class StockInRecycleBinJPanel implements MouseListener {
	// ����ȫ�����
		JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
		// ������������ɱ�����ݵķ�װ
		BaseTableModule baseTableModule;
		JTable table;
		JScrollPane jScrollPane;
		// �����õ��ı�ǩ
		JLabel tool_clean, tool_revoke;
		// ������Ӧ��service
		StockMasterService stockMasterService = new StockMasterServiceImpl();
		StockOrderService stockOrderService = new StockOrderServiceImpl();
		ProductService productService = new ProductServiceImpl();
		Accounts loginUser;

		/**
		 * ͨ�����췽����ɳ�ʼ��
		 */
		public StockInRecycleBinJPanel(Accounts loginUser) {
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
			String[] params = { "��������id", "�������", "������id", "������", "��Ӧ��id", "��Ӧ��",
					"��Ӧ����ϵ��id", "��ϵ��", "����ʱ��", "������ʶ", "��/���", "ɾ����ʶ", "ɾ����ʶ����",
					"״̬��ʶ", "����״̬" };
			Vector<Vector> vec = new Vector<>();
					try {
						// ���ҵ�ǰ�����ڻ���վ����������¼
						vec = stockMasterService.pack(stockMasterService
								.findAllStockListRecycleBin(0));
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
			// ���� 2 4 6 9 11 13
			dcm.getColumn(2).setMinWidth(0);
			dcm.getColumn(2).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
			dcm.getColumn(6).setMinWidth(0);
			dcm.getColumn(6).setMaxWidth(0);
			dcm.getColumn(9).setMinWidth(0);
			dcm.getColumn(9).setMaxWidth(0);
			dcm.getColumn(11).setMinWidth(0);
			dcm.getColumn(11).setMaxWidth(0);
			dcm.getColumn(12).setMinWidth(0);
			dcm.getColumn(12).setMaxWidth(0);
			dcm.getColumn(13).setMinWidth(0);
			dcm.getColumn(13).setMaxWidth(0);

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
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ����ɾ���Ķ�����¼");
				} else {
					// ��ȡ��ǰѡ�вֿ��id
					String id = (String) table.getValueAt(row, 0);
					int result = JOptionPane.showConfirmDialog(null, "ȷ�ϳ���ɾ��������¼��Ϣ��");
					if (result == 0) {
						// ȷ��ɾ������ִ��ɾ��
						try {
							stockMasterService.deleteStockMaster(id);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
						JOptionPane.showMessageDialog(null, "������Ϣ�ѳ���ɾ����");
						refreshTablePanel();
					}
				}
			} else if (e.getSource() == tool_revoke) {
				// ��������ɾ����Ϣ
				int row = table.getSelectedRow();// �õ�ѡ�е���
				if (row < 0) {// û��ѡ���κ��վ�
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ����ɾ���Ķ�����¼");
				} else {
					// ��ȡ��ǰѡ���е�id
					String id = (String) table.getValueAt(row, 0);
					int result = JOptionPane.showConfirmDialog(null, "ȷ�ϳ�������ɾ����¼��");
					if (result == 0) {
						// ȷ�ϳ�����ִ�г�������
						try {
							stockMasterService.revokeRecycleBin(id);
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