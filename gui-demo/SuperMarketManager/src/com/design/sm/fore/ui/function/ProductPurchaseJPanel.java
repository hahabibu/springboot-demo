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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.xml.crypto.KeySelector.Purpose;

import com.design.sm.model.Accounts;
import com.design.sm.model.Category;
import com.design.sm.model.Product;
import com.design.sm.model.StockMaster;
import com.design.sm.model.StockOrder;
import com.design.sm.model.Temp;
import com.design.sm.model.Warehouse;
import com.design.sm.service.AccountsService;
import com.design.sm.service.CategoryService;
import com.design.sm.service.ProductService;
import com.design.sm.service.TempService;
import com.design.sm.service.WarehouseService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.CategoryServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.TempServiceImpl;
import com.design.sm.service.impl.WarehouseServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class ProductPurchaseJPanel implements MouseListener, ItemListener,
		FocusListener {

	// ����ȫ�����
	public JPanel backgroundPanel, topPanel, toolPanel, searchPanel,
			tablePanel, pagePanel;
	// ���������б�
	JComboBox select_category, select_warehouse;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_catagory, label_warehouse, label_keyword, tool_add;

	// ������Ӧ���ı�����ϰ�ť
	JTextField keyword;
	JButton search;
	ButtonGroup warn_state;
	JRadioButton all, warn;
	// ������Ӧ��service
	AccountsService accountsService = new AccountsServiceImpl();
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	WarehouseService warehouseService = new WarehouseServiceImpl();
	TempService tempService = new TempServiceImpl();

	Accounts loginUser;
	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public ProductPurchaseJPanel(Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.loginUser = loginUser;
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
		toolPanel = new JPanel(new BorderLayout());
		// �����ɾ�Ĺ���
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("�ɹ���Ʒ");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		warn_state = new ButtonGroup();
		all = new JRadioButton("������Ʒ");
		all.addItemListener(this);
		warn = new JRadioButton("���Ԥ��");
		warn.addItemListener(this);
		warn_state.add(all);
		warn_state.add(warn);
		// ����ʼ����ɵĹ��߼��ص������������
		JPanel north = new JPanel();
		north.add(tool_add);
		JPanel south = new JPanel();
		south.add(all);
		south.add(warn);
		toolPanel.add(north, BorderLayout.NORTH);
		toolPanel.add(south, BorderLayout.SOUTH);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ�� 1.�ֿ��������ϲ��� 2.��Ʒ������������Ʒ���ƹؼ��ֲ���
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

		// ������������Ʒ���ƹؼ��ֲ���
		label_keyword = new JLabel("��Ʒ����");
		keyword = new JTextField(18);
		keyword.setText("��������Ʒ���ƹؼ���");
		keyword.addFocusListener(this);
		search = new JButton("����");
		search.addMouseListener(this);

		// �����������ص�ָ���������
		JPanel north = new JPanel();
		north.add(label_catagory);
		north.add(select_category);
		north.add(label_warehouse);
		north.add(select_warehouse);
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
				if(all.isSelected()){
					vec = productService.findAllProductUnion(item1.getKey(), item2.getKey());
				}else if(warn.isSelected()){
					List<Product> list = productService.findAllProductListUnion(item1.getKey(),
							item2.getKey());
					vec = productService.pack(this.getStockWarnProudctByUnion(list));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!cItem.equals("��������")) {
			try {
				Item item = (Item) cItem;
				if(all.isSelected()){
					vec = productService.findAllProductByCategoryId(item.getKey());
				}else if(warn.isSelected()){
					List<Product> list = productService.findAllProductListByCategoryId(item.getKey());
					vec = productService.pack(this.getStockWarnProudctByUnion(list));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!wItem.equals("��������")) {
			try {
				Item item = (Item) wItem;
				if(all.isSelected()){
					vec = productService.findAllProductByWarehouseId(item.getKey());
				}else if(warn.isSelected()){
					List<Product> list = productService.findAllProductListByWarehouseId(item.getKey());
					vec = productService.pack(this.getStockWarnProudctByUnion(list));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				if(all.isSelected()){
					vec = productService.findAllProductVector();
				}else if(warn.isSelected()){
					List<Product> list = productService.findAllProductList();
					vec = productService.pack(this.getStockWarnProudctByUnion(list));
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
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
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
		if (e.getSource() == tool_add) {
			// �ӹ���Ʒ
			int row = table.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�ӹ�����Ʒ");
			}else{
				// ��ȡ��ǰѡ���е���Ʒ��Ϣ
				int current_stock_int = Integer.valueOf(table.getValueAt(row, 6).toString());
				int safe_stock_int = Integer.valueOf(table.getValueAt(row, 7).toString());
				String tip = "��ǰ��Ʒ--�����Ŀ��"+current_stock_int+" ��ȫ�����Ŀ:"+safe_stock_int;
				String value = JOptionPane.showInputDialog(null,tip);
				// �����ָ���쳣
				if(value==null){
					value="";
				}
				Product prod = null;
				try {
					prod = productService.getProduct(table.getValueAt(row, 0).toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}if(value.equals("0")){
					JOptionPane.showMessageDialog(null, "������Ŀ����Ϊ0����");
				}else if(!DataValidation.isSignlessInteger(value)){
					JOptionPane.showMessageDialog(null, "�����ʽ������������������");
				}else{
					int num = Integer.valueOf(value);
					int count = current_stock_int + num;
					String product_id = table.getValueAt(row, 0).toString();
					double unit_price = Double.valueOf(table.getValueAt(row, 3).toString());
					double amount = ((int)(unit_price*num*100))/100;// ������λС��
					if(count>safe_stock_int){
						int choose = JOptionPane.showConfirmDialog(null, "��ǰ��Ʒ�ӹ���ᳬ����ȫ�����Ŀ��ȷ�ϼ������в�����");
						if(choose==0){
							// ȷ�ϼ������в���������Ӷ�����ϸ����ʱ�Ĺ����嵥
							Temp t = new Temp(product_id,num,unit_price,amount);
							try {
								tempService.addTemp(t);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "��Ʒ����ӵ��ӹ��嵥��");
						}
					}else{
						Temp t = new Temp(product_id,num,unit_price,amount);
						try {
							tempService.addTemp(t);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "��Ʒ����ӵ��ӹ��嵥��");
					}
				}
			}
		} else if (e.getSource() == search) {
			// ���Ƴ���ǰ������������
			tablePanel.removeAll();
			String[] params = { "��Ʒid", "������", "��Ʒ����", "�ɱ�", "�ۼ�", "���ϼ�",
					"�ֿ���", "��ȫ���", "��λid", "��λ", "����", "��������", "��Ʒ����", "�ۿ�",
					"������ʶ", "����״̬", "�����۸�", "ɾ����ʶ", "����id", "����", "��Ӧ��id",
					"��Ӧ��", "�ֿ�id", "�ֿ�" };
			Vector<Vector> vec = new Vector<>();
			// ��ȡ�ı����Լ���ѡ�������
			String keyword_string = keyword.getText();
			if (!keyword_string.equals("��������Ʒ���ƹؼ���")) {
				String text = "%" + keyword.getText() + "%";
				if(all.isSelected()){
					try {
						vec = productService.findAllProductByNameKeyword(text);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else if(warn.isSelected()){
					try {
						// ��ȡ����Ʒ������������Ʒ���ƹؼ��ֹ��˺��������Ʒ��Ϣ
						List list = productService.findAllProductListByNameKeyword(text);
						// ���ι��˷��Ͽ��Ԥ����������Ʒ��Ϣ
						vec = productService.pack(this.getStockWarnProudctByUnion(list));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}else if(keyword_string.equals("��������Ʒ���ƹؼ���")) {
				if(all.isSelected()){
					try {
						vec = productService.findAllProductVector();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else if(warn.isSelected()){
					try {
						// ��ȡ����Ʒ������������Ʒ���ƹؼ��ֹ��˺��������Ʒ��Ϣ
						List list = productService.findAllProductList();
						// ���ι��˷��Ͽ��Ԥ����������Ʒ��Ϣ
						vec = productService.pack(this.getStockWarnProudctByUnion(list));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
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
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
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
			if (keyword.getText().equals("��������Ʒ���ƹؼ���")) {
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
				keyword.setText("��������Ʒ���ƹؼ���");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}
	
	/**
	 * ���ݲ��ҵ��б����ɸѡ���Ԥ������Ʒ����ǰ�����Ŀ���ڲֿⰲȫ�����Ŀ��20%����Ʒ��Ϣ��
	 */
	public List<Product> getStockWarnProudctByUnion(List<Product> list){
		List<Product> find_list = new ArrayList<Product>();
		for(Product p : list){
			if(p.getCurrent_stock()<(int) (p.getSafe_stock()*0.20)){
				find_list.add(p);
			}
		}
		return find_list;
	}
}
