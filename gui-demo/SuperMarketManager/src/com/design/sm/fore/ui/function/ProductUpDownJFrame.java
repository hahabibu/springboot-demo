package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
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
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

/**
 * ��Ʒ���¼ܴ���
 */
public class ProductUpDownJFrame extends JFrame implements MouseListener,
		ItemListener, FocusListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// ���������б�
	JComboBox select_category, select_warehouse;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_catagory, label_warehouse, label_keyword, label_state,
			tool_put_on, tool_pull_off, tool_warn, tool_export;
	// ������Ӧ���ı�����ϰ�ť
	JTextField keyword;
	ButtonGroup put_on_state;
	JRadioButton put_on, pull_off;
	// ������Ӧ��service
	AccountsService accountsService = new AccountsServiceImpl();
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	WarehouseService warehouseService = new WarehouseServiceImpl();

	Accounts loginUser;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public ProductUpDownJFrame(Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.loginUser = loginUser;
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initTablePanel();// ��ʼ����ʾ�ı������
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("��Ʒ���¼�");
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
		toolPanel = new JPanel(new BorderLayout());
		// �����ɾ�Ĺ���
		Icon put_on = new ImageIcon("icons/toolImage/put_on.png");
		tool_put_on = new JLabel(put_on);
		tool_put_on.setToolTipText("��Ʒ�ϼ�");// ��������ƶ�ʱ����ʾ����
		tool_put_on.addMouseListener(this);// ���������

		Icon pull_off = new ImageIcon("icons/toolImage/pull_off.png");
		tool_pull_off = new JLabel(pull_off);
		tool_pull_off.setToolTipText("��Ʒ�¼�");// ��������ƶ�ʱ����ʾ����
		tool_pull_off.addMouseListener(this);// ���������

		Icon icon_warn = new ImageIcon("icons/toolImage/warn.png");
		tool_warn = new JLabel(icon_warn);
		tool_warn.setToolTipText("�ϼ�Ԥ��");// ��������ƶ�ʱ����ʾ����
		tool_warn.addMouseListener(this);// ���������

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("��������");// ��������ƶ�ʱ����ʾ����
		tool_export.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		JPanel north = new JPanel();
		north.add(tool_put_on);
		north.add(tool_pull_off);
		JPanel south = new JPanel();
		south.add(tool_warn);
		south.add(tool_export);
		toolPanel.add(north, BorderLayout.NORTH);
		toolPanel.add(south, BorderLayout.SOUTH);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ�� 1.�ֿ��������ϲ������¼���Ʒ��Ϣ 2.��Ʒ���ƹؼ��֡�����״̬��ϲ������¼���Ʒ��Ϣ
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel(new BorderLayout());
		// ������Ʒ���������б����������
		label_catagory = new JLabel("��������");
		select_category = new JComboBox();
		select_category.addItem("��������");
		select_category.addItemListener(this);
		// ��ȡ���ݿ���������Ʒ������Ϣ
		List<Category> list_category = null;
		try {
			list_category = categoryService.findAllCategoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ����ȡ��ÿ��������Ϣ��װΪItem����
		for (Category c : list_category) {
			// ��װid��name��Ϣ
			String id = c.getCategory_id();
			String name = c.getCategory_name();
			Item item = new Item(id, name);
			select_category.addItem(item);
		}

		// ���������ֿ�������б����������
		label_warehouse = new JLabel("�����ֿ�");
		select_warehouse = new JComboBox();
		select_warehouse.addItem("��������");
		select_warehouse.addItemListener(this);
		List<Warehouse> list_warehouse = null;
		try {
			list_warehouse = warehouseService.findAllWarehouseList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// �����ݷ�װΪItem���󣬲����ص������б���
		for (Warehouse w : list_warehouse) {
			// ��װid��name
			String id = w.getWarehouse_id();
			String name = w.getWarehouse_name();
			Item item = new Item(id, name);
			select_warehouse.addItem(item);
		}

		// ���ùؼ��ֲ���
		label_keyword = new JLabel("��Ʒ����");
		keyword = new JTextField(8);
		keyword.setText("�ؼ���");
		keyword.addFocusListener(this);
		keyword.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// ���Ƴ���ǰ������������
				tablePanel.removeAll();
				String[] params = { "��Ʒid", "������", "��Ʒ����", "�ɱ�", "�ۼ�", "���ϼ�",
						"�ֿ���", "��ȫ���", "��λid", "��λ", "����", "��������", "��Ʒ����",
						"�ۿ�", "������ʶ", "����״̬", "�����۸�", "ɾ����ʶ", "����id", "����",
						"��Ӧ��id", "��Ӧ��", "�ֿ�id", "�ֿ�" };
				Vector<Vector> vec = new Vector<>();
				// ��ȡ�ı����Լ���ѡ�������
				String keyword_string = keyword.getText();
				if (keyword_string.equals("�ؼ���")) {
					try {
						// ��ȡ������Ʒ��Ϣ
						List<Product> list = productService
								.findAllProductList();
						// ����Ʒ��Ϣ���ж��ι���
						if (put_on.isSelected()) {
							vec = productService.pack(getProductPutOn(list));
						} else if (pull_off.isSelected()) {
							vec = productService.pack(getProductPullOff(list));
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					// ��ȡ���о�����Ʒ���ƹ��˺����Ʒ��Ϣ
					try {
						String text = "%" + keyword.getText() + "%";
						List<Product> list = productService
								.findAllProductListByNameKeyword(text);
						// ����Ʒ��Ϣ���ж��ι���
						if (put_on.isSelected()) {
							vec = productService.pack(getProductPutOn(list));
						} else if (pull_off.isSelected()) {
							vec = productService.pack(getProductPullOff(list));
						}
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
									JTable table, Object value,
									boolean isSelected, boolean hasFocus,
									int row, int column) {
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
				dcm.getColumn(1).setMinWidth(0);
				dcm.getColumn(1).setMaxWidth(0);
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
				dcm.getColumn(16).setMinWidth(0);
				dcm.getColumn(16).setMaxWidth(0);
				dcm.getColumn(17).setMinWidth(0);
				dcm.getColumn(17).setMaxWidth(0);
				dcm.getColumn(18).setMinWidth(0);
				dcm.getColumn(18).setMaxWidth(0);
				dcm.getColumn(20).setMinWidth(0);
				dcm.getColumn(20).setMaxWidth(0);
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
			public void changedUpdate(DocumentEvent e) {

			}
		});
		put_on_state = new ButtonGroup();
		put_on = new JRadioButton("�ϼ���Ʒ");
		pull_off = new JRadioButton("�¼���Ʒ");
		put_on_state.add(put_on);
		put_on_state.add(pull_off);
		put_on.addItemListener(this);
		pull_off.addItemListener(this);

		// �����������ص�ָ���������
		JPanel north = new JPanel();
		north.add(label_catagory);
		north.add(select_category);
		north.add(label_warehouse);
		north.add(select_warehouse);
		JPanel south = new JPanel();
		south.add(label_keyword);
		south.add(keyword);
		south.add(put_on);
		south.add(pull_off);
		searchPanel.add(north, BorderLayout.NORTH);
		searchPanel.add(south, BorderLayout.SOUTH);
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
		// ��ȡ��ǰ�������ѡ��
		Object cItem = select_category.getSelectedItem();
		Object wItem = select_warehouse.getSelectedItem();
		if (!cItem.equals("��������") && !wItem.equals("��������")) {
			try {
				Item item1 = (Item) cItem;
				Item item2 = (Item) wItem;
				// ��ȡ�����ֿ�ͷ�����Ϣ���˵���Ʒ��Ϣ
				List<Product> listByUnion = productService
						.findAllProductListUnion(item1.getKey(), item2.getKey());
				// ����Ʒ��Ϣ���ж��ι���
				if (put_on.isSelected()) {
					vec = productService
							.pack(this.getProductPutOn(listByUnion));
				} else if (pull_off.isSelected()) {
					vec = productService.pack(this
							.getProductPullOff(listByUnion));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!cItem.equals("��������")) {
			try {
				Item item = (Item) cItem;
				// ��ȡ����������Ϣ���˵���Ʒ��Ϣ
				List<Product> listByCid = productService
						.findAllProductListByCategoryId(item.getKey());
				// ����Ʒ��Ϣ���ж��ι���
				if (put_on.isSelected()) {
					vec = productService.pack(this.getProductPutOn(listByCid));
				} else if (pull_off.isSelected()) {
					vec = productService
							.pack(this.getProductPullOff(listByCid));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!wItem.equals("��������")) {
			try {
				Item item = (Item) wItem;
				// ��ȡ�����ֿ���Ϣ���˵���Ʒ��Ϣ
				List<Product> listByWid = productService
						.findAllProductListByWarehouseId(item.getKey());
				// ����Ʒ��Ϣ���ж��ι���
				if (put_on.isSelected()) {
					vec = productService.pack(this.getProductPutOn(listByWid));
				} else if (pull_off.isSelected()) {
					vec = productService
							.pack(this.getProductPullOff(listByWid));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				// ��ȡ������Ʒ��Ϣ
				List<Product> list = productService.findAllProductList();
				// ����Ʒ��Ϣ���ж��ι���
				if (put_on.isSelected()) {
					vec = productService.pack(this.getProductPutOn(list));
				} else if (pull_off.isSelected()) {
					vec = productService.pack(this.getProductPullOff(list));
				}
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
		dcm.getColumn(1).setMinWidth(0);
		dcm.getColumn(1).setMaxWidth(0);
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
		dcm.getColumn(16).setMinWidth(0);
		dcm.getColumn(16).setMaxWidth(0);
		dcm.getColumn(17).setMinWidth(0);
		dcm.getColumn(17).setMaxWidth(0);
		dcm.getColumn(18).setMinWidth(0);
		dcm.getColumn(18).setMaxWidth(0);
		dcm.getColumn(20).setMinWidth(0);
		dcm.getColumn(20).setMaxWidth(0);
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
		if (e.getSource() == tool_put_on) {
			// ��Ʒ�ϼ�
			// ��ȡ��ǰѡ��Ҫ����������
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�ϼܵ���Ʒ");
			} else {
				/**
				 * ������������ݽ��в������ϼܳɹ����޸���Ʒ���������� 1.���ϼܿ�����ӣ�֮ǰ���ϼܿ����Ŀ+�������ϼ���Ŀ
				 * 2.�ֿ�ʣ������٣�֮ǰ�Ĳֿ�����Ŀ-�������ϼ���Ŀ
				 */
				// ��ȡѡ��Ҫ�ϼܵ���Ʒ�ĵ�ǰ�ϼܿ�桢�ֿ�����Ŀ
				int current_put_on_stock = Integer.valueOf(table.getValueAt(
						row, 5).toString());
				int current_stock = Integer.valueOf(table.getValueAt(row, 6)
						.toString());
				String tip = "��ǰ��Ʒ--�ϼܿ�棺" + current_put_on_stock + "  �ֿ��棺"
						+ current_stock;
				String input = JOptionPane.showInputDialog(null, tip);
				// �����ָ���쳣
				if(input==null){
					input="";
				}
				if (DataValidation.isSignlessInteger(input)) {
					int num = Integer.valueOf(input);
					// �ж����������Ƿ�����Ҫ��
					if(num<=current_stock&&num>0){
						int choose = JOptionPane.showConfirmDialog(null, "��Ʒ�ϼ���ĿΪ"
								+ num + ",ȷ���ϼܸ���Ʒ��");
						if (choose == 0) {
							// �޸���Ʒ�ϼ���Ŀ�������Ŀ
							try {
								Product p = productService.getProduct(table
										.getValueAt(row, 0).toString());
								p.setPutaway_stock(current_put_on_stock + num);
								p.setCurrent_stock(current_stock - num);
								productService.updateProduct(p);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "��Ʒ�ϼܳɹ���");
						}
					}else if(num>current_stock){
						JOptionPane.showMessageDialog(null, "�������ݲ��ܳ�����Ʒ��ǰ��棡");
					}else if(num==0){
						JOptionPane.showMessageDialog(null, "�������ݲ���Ϊ0��");
					}
				}
			}
		} else if (e.getSource() == tool_pull_off) {
			// ��ȡ��ǰѡ��Ҫ����������
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�¼ܵ���Ʒ");
			} else {
				/**
				 * ������������ݽ��в������ϼܳɹ����޸���Ʒ���������� 1.���ϼܿ����٣�֮ǰ���ϼܿ����Ŀ-�¼���Ʒ��Ŀ
				 * 2.�ֿ�ʣ�������ӣ�֮ǰ�Ĳֿ�����Ŀ+�ϼ���Ʒ��Ŀ
				 */
				// ��ȡѡ��Ҫ�ϼܵ���Ʒ�ĵ�ǰ�ϼܿ�桢�ֿ�����Ŀ
				int current_put_on_stock = Integer.valueOf(table.getValueAt(
						row, 5).toString());
				int current_stock = Integer.valueOf(table.getValueAt(row, 6)
						.toString());
				String tip = "��ǰ��Ʒ--�ϼܿ�棺" + current_put_on_stock + "  �ֿ��棺"
						+ current_stock;
				String input = JOptionPane.showInputDialog(null, tip);
				// �����ָ���쳣
				if(input==null){
					input="";
				}
				if (DataValidation.isSignlessInteger(input)) {
					int num = Integer.valueOf(input);
					if(num<=current_put_on_stock&&num>0){
						int choose = JOptionPane.showConfirmDialog(null, "��Ʒ�¼���ĿΪ"
								+ num + ",ȷ���¼ܸ���Ʒ��");
						if (choose == 0) {
							// �޸���Ʒ�ϼ���Ŀ�������Ŀ
							try {
								Product p = productService.getProduct(table
										.getValueAt(row, 0).toString());
								p.setPutaway_stock(current_put_on_stock - num);
								p.setCurrent_stock(current_stock + num);
								productService.updateProduct(p);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "��Ʒ�¼ܳɹ���");
						}
					}else if(num>current_put_on_stock){
						JOptionPane.showMessageDialog(null, "�������ݲ��ܳ�����Ʒ��ǰ���ϼ���Ŀ��");
					}else if(num==0){
						JOptionPane.showMessageDialog(null, "�������ݲ���Ϊ0��");
					}
				}

			}
		} else if (e.getSource() == tool_warn) {
			// �ϼ�Ԥ���������ϼܵ���Ʒ���ڲֿ����20%�������ʾ����ʾ����Ʒ���ݣ�
			// ��ȡ�����ϼܵ���Ʒ��Ϣ
			List<Product> list = null;
			try {
				list = productService.findAllProductList();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// ����ɸѡ�ϼ���Ʒ��Ϣ
			List<Product> put_on_list = this.getProductPutOn(list);
			// ��һ����ȡ�ϼ�Ԥ������Ʒ��Ϣ
			List<Product> warn_list  = this.getProductWarn(put_on_list);
			
			// ���Ƴ���ǰ������������
			tablePanel.removeAll();
			String[] params = { "��Ʒid", "������", "��Ʒ����", "�ɱ�", "�ۼ�", "���ϼ�",
					"�ֿ���", "��ȫ���", "��λid", "��λ", "����", "��������", "��Ʒ����",
					"�ۿ�", "������ʶ", "����״̬", "�����۸�", "ɾ����ʶ", "����id", "����",
					"��Ӧ��id", "��Ӧ��", "�ֿ�id", "�ֿ�" };
			Vector<Vector> vec = new Vector<>();
			try {
				vec = productService.pack(warn_list);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// ����ѯ�������ݷ�װ��BbaseTableModule��
			baseTableModule = new BaseTableModule(params, vec);
			table = new JTable(baseTableModule);
			// ��Ⱦ��0�У�������ʾΪ��ѡ�������ʾ
			table.getColumnModel().getColumn(0)
					.setCellRenderer(new TableCellRenderer() {
						@Override
						public Component getTableCellRendererComponent(
								JTable table, Object value,
								boolean isSelected, boolean hasFocus,
								int row, int column) {
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
			dcm.getColumn(1).setMinWidth(0);
			dcm.getColumn(1).setMaxWidth(0);
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
			dcm.getColumn(16).setMinWidth(0);
			dcm.getColumn(16).setMaxWidth(0);
			dcm.getColumn(17).setMinWidth(0);
			dcm.getColumn(17).setMaxWidth(0);
			dcm.getColumn(18).setMinWidth(0);
			dcm.getColumn(18).setMaxWidth(0);
			dcm.getColumn(20).setMinWidth(0);
			dcm.getColumn(20).setMaxWidth(0);
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
	 * ͨ�����뾭������ɸѡ�����Ʒ���ж��ι��� ɸѡ�ϼ���Ʒ���ϼܿ�����0����Ʒ��Ϊ���ϼܣ� ��Ʒ�ϼ��ǽ���Ʒ�Ӳֿ��з��볬��
	 */
	public List<Product> getProductPutOn(List<Product> list) {
		List<Product> put_on_list = new ArrayList<Product>();
		for (Product p : list) {
			if (p.getPutaway_stock() > 0) {
				put_on_list.add(p);
			}
		}
		return put_on_list;
	}

	/**
	 * ͨ�����뾭������ɸѡ�����Ʒ���ж��ι��� ɸѡ�¼���Ʒ���ϼܿ��=0����Ʒ��Ϊ���¼ܣ� ��Ʒ�¼���ֱ�ӽ���Ʒ���ֻ�ȫ���˻زֿ���
	 */
	public List<Product> getProductPullOff(List<Product> list) {
		List<Product> put_off_list = new ArrayList<Product>();
		for (Product p : list) {
			if (p.getPutaway_stock() == 0) {
				put_off_list.add(p);
			}
		}
		return put_off_list;
	}
	
	/**
	 * ���ϼ���Ʒ���ڲֿ����20%����ʾ��Ʒ���ݽ�������
	 */
	public List<Product> getProductWarn(List<Product> list){
		List<Product> warn_list = new ArrayList<Product>();
		for (Product p : list) {
			if (p.getPutaway_stock()<Integer.valueOf((int) (p.getCurrent_stock()*0.2))) {
				warn_list.add(p);
			}
		}
		return warn_list;
	}

}
