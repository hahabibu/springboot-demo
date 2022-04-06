package com.design.sm.fore.ui.control;

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

import com.design.sm.fore.ui.function.AddProductJFrame;
import com.design.sm.fore.ui.function.ShowProductDetailJFrame;
import com.design.sm.fore.ui.function.UpdateProductJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.model.Category;
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

public class BaseDataJPanel implements MouseListener, ItemListener,
		FocusListener {

	// 定义全局组件
	public JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// 定义下拉列表
	JComboBox select_category, select_warehouse;
	// 借助工具类完成表格数据的封装
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// 定义用到的标签
	JLabel label_catagory, label_warehouse, label_keyword, label_state,
			tool_add, tool_modify, tool_delete, tool_detail, tool_import,
			tool_export;
	// 定义相应的文本框、组合按钮
	JTextField keyword;
	ButtonGroup state;
	JRadioButton all, promotion;
	JButton search;
	int maxPage;
	int currentPage;
	// 定义相应的分页组合标签
	JLabel label_all,label_start, label_end, label_last, label_next;
	JTextField page;
	// 定义相应的service
	AccountsService accountsService = new AccountsServiceImpl();
	ProductService productService = new ProductServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	WarehouseService warehouseService = new WarehouseServiceImpl();

	Accounts loginUser;

	/**
	 * 通过构造方法完成初始化
	 */
	public BaseDataJPanel(Accounts loginUser) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.loginUser = loginUser;
		try {
			maxPage = productService.getProductMaxPage();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 初始化布局
		initTopPanel();// 初始化顶部菜单条
		initTablePanel();// 初始化显示的表格数据
		initPagePanel();// 定义分页查找按钮组合
	}

	/**
	 * 初始化顶部的菜单条
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// 初始化工具条面板
		initToolPanel();
		// 初始化查找面板
		initSearchPanel();
		// 将顶部菜单栏加载到背景面板中
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * 初始化工具条面板
	 */
	private void initToolPanel() {
		toolPanel = new JPanel(new BorderLayout());
		// 添加增删改工具
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("新建商品");// 设置鼠标移动时的显示内容
		tool_add.addMouseListener(this);// 添加鼠标监听

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("修改商品");// 设置鼠标移动时的显示内容
		tool_modify.addMouseListener(this);// 添加鼠标监听

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("删除商品");// 设置鼠标移动时的显示内容
		tool_delete.addMouseListener(this);// 添加鼠标监听

		Icon icon_detail = new ImageIcon("icons/toolImage/detail.png");
		tool_detail = new JLabel(icon_detail);
		tool_detail.setToolTipText("商品详情");// 设置鼠标移动时的显示内容
		tool_detail.addMouseListener(this);// 添加鼠标监听

		Icon icon_import = new ImageIcon("icons/toolImage/import.png");
		tool_import = new JLabel(icon_import);
		tool_import.setToolTipText("导入数据");// 设置鼠标移动时的显示内容
		tool_import.addMouseListener(this);// 添加鼠标监听

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("导出数据");// 设置鼠标移动时的显示内容
		tool_export.addMouseListener(this);// 添加鼠标监听

		// 将初始化完成的工具加载到工具条面板中
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
		// 最终将工具条面板加载到顶部菜单条的最西面
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * 初始化查找面板 设置查找方式： 1.仓库与分类组合查找 2.商品名称关键字、销售状态组合查找
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel(new BorderLayout());
		// 设置商品分类下拉列表的所有属性
		label_catagory = new JLabel("所属分类");
		select_category = new JComboBox();
		select_category.addItem("所有内容");
		select_category.addItemListener(this);
		// 获取数据库中所有商品分类信息
		List<Category> list_category = null;
		try {
			list_category = categoryService.findAllCategoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将获取的每项数据信息封装为Item对象
		for (Category c : list_category) {
			// 封装id、name信息
			String id = c.getCategory_id();
			String name = c.getCategory_name();
			Item item = new Item(id, name);
			select_category.addItem(item);
		}

		// 设置所属仓库的下拉列表的所有属性
		label_warehouse = new JLabel("所属仓库");
		select_warehouse = new JComboBox();
		select_warehouse.addItem("所有内容");
		select_warehouse.addItemListener(this);
		List<Warehouse> list_warehouse = null;
		try {
			list_warehouse = warehouseService.findAllWarehouseList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将数据封装为Item对象，并加载到下拉列表中
		for (Warehouse w : list_warehouse) {
			// 封装id和name
			String id = w.getWarehouse_id();
			String name = w.getWarehouse_name();
			Item item = new Item(id, name);
			select_warehouse.addItem(item);
		}

		// 设置关键字查找
		label_keyword = new JLabel("商品名称");
		keyword = new JTextField(8);
		keyword.setText("关键字");
		keyword.addFocusListener(this);
		state = new ButtonGroup();
		all = new JRadioButton("所有");
		promotion = new JRadioButton("促销");
		state.add(all);
		state.add(promotion);
		all.setSelected(true);
		search = new JButton("查找");
		search.addMouseListener(this);

		// 将相关组件加载到指定的面板中
		JPanel north = new JPanel();
		north.add(label_catagory);
		north.add(select_category);
		north.add(label_warehouse);
		north.add(select_warehouse);
		JPanel south = new JPanel();
		south.add(label_keyword);
		south.add(keyword);
		south.add(all);
		south.add(promotion);
		south.add(search);
		searchPanel.add(north, BorderLayout.NORTH);
		searchPanel.add(south, BorderLayout.SOUTH);
		// 将布局好的组件加载到菜单栏面板的最东面
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * 初始化显示的表格数据
	 */
	private void initTablePanel() {
		// 要根据下拉框的选项进行筛选数据(要根据productService中返回的行进行设置，随后选择要隐藏的项目即可)
		String[] params = { "商品id", "条形码", "商品名称", "成本", "售价", "已上架", "仓库库存",
				"安全库存", "单位id", "单位", "产地", "生产日期", "商品描述", "折扣", "促销标识",
				"促销状态", "促销价格", "删除标识", "分类id", "分类", "供应商id", "供应商", "仓库id",
				"仓库" };
		Vector<Vector> vec = new Vector<>();
		// 获取当前下拉框的选择
		Object cItem = select_category.getSelectedItem();
		Object wItem = select_warehouse.getSelectedItem();
		if (!cItem.equals("所有内容") && !wItem.equals("所有内容")) {
			try {
				Item item1 = (Item) cItem;
				Item item2 = (Item) wItem;
				vec = productService.findAllProductUnion(item1.getKey(),
						item2.getKey());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!cItem.equals("所有内容")) {
			try {
				Item item = (Item) cItem;
				vec = productService.findAllProductByCategoryId(item.getKey());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!wItem.equals("所有内容")) {
			try {
				Item item = (Item) wItem;
				vec = productService.findAllProductByWarehouseId(item.getKey());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				vec = productService.findAllProductVector();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 将查询到的数据封装到BbaseTableModule中
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// 渲染第0列，将其显示为多选框进行显示
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
		// 利用提供的Tools类美化表格
		Tools.setTableStyle(table);

		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// 隐藏：0 1 6 7 8 12 13 14 16 17 18 19 21
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

		// 设置滚动条
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// 设置透明度
		tablePanel.add(jScrollPane);
		// 将组件加载到背景中
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		backgroundPanel.validate();
	}

	/**
	 * 定义分页查找按钮组合
	 */
	private void initPagePanel() {
		pagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		label_all = new JLabel("显示所有");
		label_all.setFont(MyFont.JLabelFont);
		label_all.setForeground(MyColor.JLabelColor);
		label_all.addMouseListener(this);
		
		label_start = new JLabel("首页");
		label_start.setFont(MyFont.JLabelFont);
		label_start.setForeground(MyColor.JLabelColor);
		label_start.addMouseListener(this);

		label_end = new JLabel("尾页");
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
			    if(pattern.matcher(page.getText()).matches()){
			    	currentPage = Integer.valueOf(page.getText());
			    	refreshTablePanelByPage();
			    }else{
			    	JOptionPane.showMessageDialog(null, "请输入合法的数字！");
			    	currentPage = 1;
			    }
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});

		label_last = new JLabel("上一页");
		label_last.setFont(MyFont.JLabelFont);
		label_last.setForeground(MyColor.JLabelColor);
		label_last.addMouseListener(this);

		label_next = new JLabel("下一页");
		label_next.setFont(MyFont.JLabelFont);
		label_next.setForeground(MyColor.JLabelColor);
		label_next.addMouseListener(this);

		pagePanel.add(label_all);
		pagePanel.add(label_start);
		pagePanel.add(label_last);
		pagePanel.add(page);
		pagePanel.add(label_next);
		pagePanel.add(label_end);
		// 将组件加载到背景中
		backgroundPanel.add(pagePanel, BorderLayout.SOUTH);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==label_all){
			refreshTablePanelBySearch();
		}else if (e.getSource() == label_start) {
			page.setText("1");
			refreshTablePanelByPage();
		} else if (e.getSource() == label_end) {
			page.setText(String.valueOf(maxPage));
			refreshTablePanelByPage();
		} else if (e.getSource() == label_last) {
			// 获取当前页数进行比较
			if (currentPage > 1) {
				page.setText((currentPage - 1) + "");
			}else if(currentPage==1){
				page.setText("1");
			}
			refreshTablePanelByPage();
		} else if (e.getSource() == label_next) {
			// 获取当前页数进行比较
			if (currentPage < maxPage) {
				page.setText((currentPage + 1) + "");
			}else if(currentPage==maxPage){
				page.setText(maxPage+"");
			}
			refreshTablePanelByPage();
		} else if (e.getSource() == tool_add) {
			// 添加商品信息
			 new AddProductJFrame(this,this.loginUser);
		} else if (e.getSource() == tool_modify) {
			// 获取当前选中要修改的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要修改的商品");
			} else {
				 new UpdateProductJFrame(this,loginUser, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			// 删除当前选中的数据
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要删除的商品");
			} else {
				// 获取当前选中商品的id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "确认删除这条信息？");
				if (result == 0) {
					// 确认删除，则执行删除
					try {
						productService.deleteProduct(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "商品信息删除成功！！");
					// 删除完数据之后要刷新面板
					refreshTablePanelBySearch();
				}
			}
		} else if (e.getSource() == search) {
			// 先移除当前面板的所有数据
			tablePanel.removeAll();
			String[] params = { "商品id", "条形码", "商品名称", "成本", "售价", "已上架",
					"仓库库存", "安全库存", "单位id", "单位", "产地", "生产日期", "商品描述", "折扣",
					"促销标识", "促销状态", "促销价格", "删除标识", "分类id", "分类", "供应商id",
					"供应商", "仓库id", "仓库" };
			Vector<Vector> vec = new Vector<>();
			// 获取文本框以及单选框的内容
			String keyword_string = keyword.getText();
			if (keyword_string.equals("关键字")) {
				if (all.isSelected()) {
					try {
						vec = productService.findAllProductByPromotionState(0);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else if (promotion.isSelected()) {
					try {
						vec = productService.findAllProductByPromotionState(1);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			} else {
				if (all.isSelected()) {
					String text = "%" + keyword.getText() + "%";
					try {
						vec = productService.findAllProductByNameKeyword(text);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else if (promotion.isSelected()) {
					String text = "%" + keyword.getText() + "%";
					try {
						vec = productService.findAllProductionUnionKP(text, 1);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
			// 将查询到的数据封装到BbaseTableModule中
			baseTableModule = new BaseTableModule(params, vec);
			table = new JTable(baseTableModule);
			// 渲染第0列，将其显示为多选框进行显示
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
			// 利用提供的Tools类美化表格
			Tools.setTableStyle(table);
			// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
			DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
					.getColumnModel();
			// 隐藏：0 1 6 7 8 12 13 14 16 17 18 19 21
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

			// 设置滚动条
			jScrollPane = new JScrollPane(table);
			Tools.setJspStyle(jScrollPane);

			tablePanel = new JPanel(new BorderLayout());
			tablePanel.setOpaque(false);// 设置透明度
			tablePanel.add(jScrollPane);
			// 将组件加载到背景中
			backgroundPanel.add(tablePanel, BorderLayout.CENTER);
			// 在刷新数据的时候改变窗体大小，完成数据刷新
			backgroundPanel.validate();
		} else if (e.getSource() == tool_detail) {
			// 查看商品详细信息
			int row = table.getSelectedRow();// 得到选中的行
			if (row < 0) {// 没有选中任何收据
				JOptionPane.showMessageDialog(null, "请选择要查看的商品");
			} else {
				new ShowProductDetailJFrame(this, loginUser, table, row);
			}
		} else if (e.getSource() == tool_import) {
			// 批量导入商品数据
			/**
			 * ..........
			 */

		} else if (e.getSource() == tool_export) {
			// 获取当前选中的数据
			String[] ids;
			ArrayList id_list = new ArrayList<>();
			for (int rowindex : table.getSelectedRows()) {
				Object obj = table.getValueAt(rowindex, 0);
				id_list.add(obj);
			}
			// 集合转数组
			ids = (String[]) id_list.toArray(new String[id_list.size()]);
			int result = productService.exportData(ids);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "数据导出成功！");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "抱歉！数据导出失败，再试一遍吧！");
			} else if (result == 0) {
				JOptionPane.showMessageDialog(null, "用户取消了操作！");
			}
		}
	}

	/**
	 * 刷新数据面板
	 */
	public void refreshTablePanelBySearch() {
		// 移除当前数据面板中的所有数据
		backgroundPanel.removeAll();
		initTopPanel();
		initTablePanel();
		initPagePanel();
		backgroundPanel.validate();// 验证
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
			// 如果点击了下拉框选项，仅仅刷新数据面板,要先将数据面板的内容移除之后再添加
			tablePanel.removeAll();// 移除数据面板中的所有数据
			initTablePanel();// 重新初始化面板
		}
	}

	/**
	 * 聚焦事件
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("关键字")) {
				keyword.setText("");
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
			}
		}
	}

	/**
	 * 失去焦点事件
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setText("关键字");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}

	/**
	 * 分页显示调用方法：刷新表格数据 此处分页针对的是所有商品的分页，忽略条件组合查找的限制
	 */
	public void refreshTablePanelByPage() {
		backgroundPanel.remove(tablePanel);
		// 要根据下拉框的选项进行筛选数据(要根据productService中返回的行进行设置，随后选择要隐藏的项目即可)
		String[] params = { "商品id", "条形码", "商品名称", "成本", "售价", "已上架", "仓库库存",
				"安全库存", "单位id", "单位", "产地", "生产日期", "商品描述", "折扣", "促销标识",
				"促销状态", "促销价格", "删除标识", "分类id", "分类", "供应商id", "供应商", "仓库id",
				"仓库" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = productService.getAllProductByPage(currentPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 将查询到的数据封装到BbaseTableModule中
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
		// 渲染第0列，将其显示为多选框进行显示
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
		// 利用提供的Tools类美化表格
		Tools.setTableStyle(table);

		// 通过设置行的大小，隐藏某一列内容（只显示想要显示的数据）
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// 隐藏：0 1 6 7 8 12 13 14 16 17 18 19 21
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

		// 设置滚动条
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// 设置透明度
		tablePanel.add(jScrollPane);
		// 将组件加载到背景中
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		// 在刷新数据的时候改变窗体大小，完成数据刷新
		backgroundPanel.validate();
	}
}
