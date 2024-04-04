package com.design.sm.fore.ui.function;

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

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.model.Accounts;
import com.design.sm.model.Category;
import com.design.sm.model.Product;
import com.design.sm.model.Warehouse;
import com.design.sm.service.AccountsService;
import com.design.sm.service.CategoryService;
import com.design.sm.service.ProductService;
import com.design.sm.service.WarehouseService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.CategoryServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.WarehouseServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class ShowVendorProvideProductJFrame extends JFrame implements
		MouseListener, ItemListener, FocusListener {
	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_keyword, tool_detail, tool_export;
	// ������Ӧ���ı�����ϰ�ť
	JTextField keyword;
	JButton search;
	// ������Ӧ��service
	AccountsService accountsService = new AccountsServiceImpl();
	ProductService productService = new ProductServiceImpl();

	VendorManagerJFrame parentPanel;
	JTable parentTable;
	int selectedRow;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public ShowVendorProvideProductJFrame(VendorManagerJFrame parentPanel,
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
		this.setTitle("�鿴��Ӧ���ṩ��Ʒ");
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
		// ��Ӹ�������
		Icon icon_detail = new ImageIcon("icons/toolImage/detail.png");
		tool_detail = new JLabel(icon_detail);
		tool_detail.setToolTipText("��Ʒ����");// ��������ƶ�ʱ����ʾ����
		tool_detail.addMouseListener(this);// ���������

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("��������");// ��������ƶ�ʱ����ʾ����
		tool_export.addMouseListener(this);// ���������

		toolPanel.add(tool_detail);
		toolPanel.add(tool_export);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ�� ͨ����Ʒ�ؼ��ֲ���
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		// ���ùؼ��ֲ���
		label_keyword = new JLabel("��Ʒ����");
		keyword = new JTextField(20);
		keyword.setText("�ؼ���");
		keyword.addFocusListener(this);
		search = new JButton("����");
		search.addMouseListener(this);

		// �����������ص�ָ���������
		searchPanel.add(label_keyword);
		searchPanel.add(keyword);
		searchPanel.add(search);
		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// Ҫ�����������ѡ�����ɸѡ����(Ҫ����productService�з��ص��н������ã����ѡ��Ҫ���ص���Ŀ����)
		String[] params = { "��Ʒid", "������", "��Ʒ����", "�ɱ�", "�ۼ�", "���ϼ�", "�ֿ���",
				"��ȫ���", "��λid", "��λ", "����", "��������", "��Ʒ����", "�ۿ�", "������ʶ",
				"����״̬", "�����۸�", "ɾ����ʶ", "����id", "����", "��Ӧ��id", "��Ӧ��", "�ֿ�id",
				"�ֿ�" };
		Vector<Vector> vec = new Vector<>();
		// ��ȡ�ı����Լ���ѡ�������
		String keyword_string = keyword.getText();
		// ��ȡ��ǰ��Ӧ��id
		String vendorId = parentTable.getValueAt(selectedRow,0).toString();
		if (keyword_string.equals("�ؼ���")) {
			try {
				vec = productService.pack(productService
						.getProductByVendorId(vendorId));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			String text = "%" + keyword.getText() + "%";
			try {
				vec = productService.pack(this.getProductByNameUnionVendorId(text, vendorId));
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
		// ���أ�0 1 6 7 8 12 13 14 16 17 18 19 21
		// dcm.getColumn(0).setMinWidth(0);
		// dcm.getColumn(0).setMaxWidth(0);
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(13).setMinWidth(0);
		dcm.getColumn(13).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);
		dcm.getColumn(15).setMinWidth(0);
		dcm.getColumn(15).setMaxWidth(0);
		dcm.getColumn(16).setMinWidth(0);
		dcm.getColumn(16).setMaxWidth(0);
		dcm.getColumn(17).setMinWidth(0);
		dcm.getColumn(17).setMaxWidth(0);
		dcm.getColumn(18).setMinWidth(0);
		dcm.getColumn(18).setMaxWidth(0);
		dcm.getColumn(20).setMinWidth(0);
		dcm.getColumn(20).setMaxWidth(0);
		dcm.getColumn(21).setMinWidth(0);
		dcm.getColumn(21).setMaxWidth(0);
		dcm.getColumn(22).setMinWidth(0);
		dcm.getColumn(22).setMaxWidth(0);

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
		if (e.getSource() == search) {
			// ˢ���������
			tablePanel.removeAll();
			initTablePanel();
			backgroundPanel.validate();
		} else if (e.getSource() == tool_detail) {
			// �鿴��Ʒ��ϸ��Ϣ
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�鿴����Ʒ");
			} else {
				 new ShowVendorProvideProductDetailJFrame(this, table, row);
			}
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
			int result = productService.exportData(ids);
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
	 * ͨ����Ʒ���ƹؼ��ֲ�����Ʒ��Ϣ��������Ʒ��Ӧ����Ϣ���ж���ɸѡ
	 */
	public List<Product> getProductByNameUnionVendorId(String text,
			String vendorId) {
		List<Product> list = new ArrayList<Product>();
		// ��ȡͨ�����ƹؼ���ɸѡ�Ĳ�Ʒ��Ϣ
		List<Product> list_keyword = null;
		try {
			list_keyword = productService.findAllProductListByNameKeyword(text);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ͨ����ȡ��ǰѡ�еĹ�Ӧ�̵���ϸ��Ϣ����Ӧ��id�����ж���ɸѡ
		for (Product p : list_keyword) {
			if (p.getVendor_id().equals(vendorId)) {
				list.add(p);
			}
		}
		return list;
	}
}
