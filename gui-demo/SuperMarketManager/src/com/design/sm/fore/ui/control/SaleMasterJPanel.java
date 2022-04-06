package com.design.sm.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.dao.impl.SaleOrderServiceImpl;
import com.design.sm.fore.ui.function.ShowSaleMasterDetail;
import com.design.sm.model.Accounts;
import com.design.sm.service.ProductService;
import com.design.sm.service.SaleMasterService;
import com.design.sm.service.SaleOrderService;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.SaleMasterServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;
import com.eltima.components.ui.DatePicker;

public class SaleMasterJPanel implements MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel, searchPanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel tool_delete, tool_detail;
	DatePicker start_time, end_time;
	JButton search;
	// ������Ӧ��service
	SaleMasterService saleMasterService = new SaleMasterServiceImpl();
	SaleOrderService saleOrderService = new SaleOrderServiceImpl();
	ProductService productService = new ProductServiceImpl();

	Accounts loginUser;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public SaleMasterJPanel(Accounts loginUser) {
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

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ����¼");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		Icon icon_detail = new ImageIcon("icons/toolImage/detail.png");
		tool_detail = new JLabel(icon_detail);
		tool_detail.setToolTipText("������ϸ");// ��������ƶ�ʱ����ʾ����
		tool_detail.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_delete);
		toolPanel.add(tool_detail);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ�� ���ݶ������ɵ�ʱ�䷶Χ���в���
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		Icon start_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_start = new JLabel(start_icon);
		start_time = new DatePicker();
		start_time.setPreferredSize(new Dimension(150, 30));
		start_time.addMouseListener(this);
		Icon end_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_end = new JLabel(end_icon);
		end_time = new DatePicker();
		end_time.setPreferredSize(new Dimension(150, 30));
		end_time.addMouseListener(this);

		search = new JButton("����");
		search.addMouseListener(this);

		searchPanel.add(label_start);
		searchPanel.add(start_time);
		searchPanel.add(label_end);
		searchPanel.add(end_time);
		searchPanel.add(search);

		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ��������ݽ��в���
		String[] params = { "��������id", "�������", "������id", "������", "�˿�id", "�˿�",
				"����ʱ��", "ɾ����ʶ", "ɾ����ʶ����","״̬��ʶ", "����״̬" };
		Vector<Vector> vec = new Vector<>();
		// �ж�������������ڸ�ʽ�Ƿ�Ϸ�
		String start_time_string = start_time.getText();
		String end_time_string = end_time.getText();
		if(!(start_time_string.equals("")&&end_time_string.equals("")))
		try {
			vec = saleMasterService.pack(saleMasterService
					.findAllStockListByTimeUnionState(
							start_time_string, end_time_string, 0));
		} catch (SQLException e) {
			e.printStackTrace();
		}else{
			// �����ʼʱ��Ϊ����Ϊ��Сֵ
			if (start_time_string.equals("")) {
				start_time_string = "0000-00-00";
			}
			// �������ʱ��Ϊ����Ϊ���ֵ
			if (end_time_string.equals("")) {
				end_time_string = "9999-99-99";
			}
			// ���ڴ˴���������ֵ���������ʱ��ѡ���Ϊ�������Ӧ������ֵ����ʾ��������е���Ʒ����
			//���в���
			try {
				vec = saleMasterService.pack(saleMasterService
						.findAllStockListByTimeUnionState(
								start_time_string, end_time_string, 0));
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
		if (e.getSource() == tool_delete) {
			// ɾ����ǰѡ�е�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���Ķ�����¼");
			} else {
				// ��ȡ��ǰѡ�����ݼ�¼��id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��������¼��Ϣ��");
				if (result == 0) {
					// ȷ��ɾ������ִ��ɾ��
					try {
						saleMasterService.joinRecycleBin(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "������¼�ѷ��û���վ��");
					this.refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_detail) {
			// ��ʾ��������ϸ��Ϣ
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�鿴�Ķ�����Ϣ��");
			} else {
				new ShowSaleMasterDetail(this, this.loginUser, this.table, row);
			}
		} else if (e.getSource() == search) {
			this.refreshTablePanel();
		}else if(e.getSource()==start_time){
			backgroundPanel.remove(tablePanel);
			initTablePanel();
			backgroundPanel.validate();
		}else if(e.getSource()==end_time){
			backgroundPanel.remove(tablePanel);
			initTablePanel();
			backgroundPanel.validate();
		}
	}

	/**
	 * ˢ�±������
	 */
	public void refreshTablePanel() {
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();
	}

	/**
	 * ˢ���������
	 */
	public void refreshBackgroundPanel() {
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