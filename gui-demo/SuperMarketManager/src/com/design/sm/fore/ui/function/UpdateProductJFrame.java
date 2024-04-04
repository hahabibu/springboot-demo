package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.fore.ui.control.BaseDataJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.Category;
import com.design.sm.model.Product;
import com.design.sm.model.Units;
import com.design.sm.model.Vendor;
import com.design.sm.model.Warehouse;
import com.design.sm.service.CategoryService;
import com.design.sm.service.ProductService;
import com.design.sm.service.UnitsService;
import com.design.sm.service.VendorService;
import com.design.sm.service.WarehouseService;
import com.design.sm.service.impl.CategoryServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.UnitsServiceImpl;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.service.impl.WarehouseServiceImpl;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.Item;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;
import com.eltima.components.ui.DatePicker;

public class UpdateProductJFrame extends JFrame implements MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_prod_name, label_prod_cost, label_prod_price,
			label_putaway_stock, label_current_stock, label_safe_stock,
			label_prod_unit, label_prod_origin, label_prod_date,
			label_prod_descr, label_prod_discount, label_promotion_flag,
			label_promotion_price, label_category, label_vendor,
			label_warehouse;
	JTextField prod_name, prod_cost, prod_price, putaway_stock, current_stock,
			safe_stock, prod_origin, prod_discount, promotion_price;
	JTextArea prod_descr;
	JComboBox prod_unit, promotion_flag, category, vendor, warehouse;
	DatePicker prod_date;
	JButton save, reset;
	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ����service
	UnitsService unitsService = new UnitsServiceImpl();
	CategoryService categoryService = new CategoryServiceImpl();
	VendorService vendorService = new VendorServiceImpl();
	WarehouseService warehouseService = new WarehouseServiceImpl();
	ProductService productService = new ProductServiceImpl();

	// ���常���󡢵�ǰ��¼Ա�������ѡ����
	Accounts loginUser;
	BaseDataJPanel parentPanel;
	JTable table;
	int selectedRow;

	// ͨ�����췽����ʼ������
	public UpdateProductJFrame(BaseDataJPanel parentPanel, Accounts loginUser,
			JTable table, int selectedRow) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		this.table = table;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�޸���Ʒ��Ϣ");
		this.setSize(600, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// ����������
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// ���ùرշ�ʽ
		// ��ǰ�������أ���Ӱ������ݵ�ʹ�ã������ǹر���������
	}

	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		initTitlePanel();
		initContentPanel();
		initButtonPanel();
		backgroundPanel.add(titlePanel, BorderLayout.NORTH);
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * ��ʼ���������
	 */
	private void initTitlePanel() {
		titlePanel = new JPanel();
		JLabel title = new JLabel("�޸���Ʒ��Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_prod_name = new JLabel("��Ʒ����");
		prod_name = new JTextField(15);
		prod_name.setFont(MyFont.JTextFieldFont);
		label_prod_cost = new JLabel("��Ʒ�ɱ�");
		prod_cost = new JTextField(15);
		prod_cost.setFont(MyFont.JTextFieldFont);
		jp1.add(label_prod_name);
		jp1.add(prod_name);
		jp1.add(label_prod_cost);
		jp1.add(prod_cost);

		JPanel jp2 = new JPanel();
		label_prod_price = new JLabel("���ۼ۸�");
		prod_price = new JTextField(15);
		prod_price.setFont(MyFont.JTextFieldFont);
		label_putaway_stock = new JLabel("�ϼܿ��");
		putaway_stock = new JTextField(15);
		putaway_stock.setFont(MyFont.JTextFieldFont);
		jp2.add(label_prod_price);
		jp2.add(prod_price);
		jp2.add(label_putaway_stock);
		jp2.add(putaway_stock);

		JPanel jp3 = new JPanel();
		label_current_stock = new JLabel("�ֿ���");
		current_stock = new JTextField(15);
		current_stock.setFont(MyFont.JTextFieldFont);
		label_safe_stock = new JLabel("��ȫ���");
		safe_stock = new JTextField(15);
		safe_stock.setFont(MyFont.JTextFieldFont);
		jp3.add(label_current_stock);
		jp3.add(current_stock);
		jp3.add(label_safe_stock);
		jp3.add(safe_stock);

		JPanel jp4 = new JPanel();
		label_prod_unit = new JLabel("��Ʒ��λ");
		prod_unit = new JComboBox();
		prod_unit.setPreferredSize(new Dimension(175, 30));
		label_prod_origin = new JLabel("��Ʒ����");
		prod_origin = new JTextField(15);
		prod_origin.setFont(MyFont.JTextFieldFont);
		jp4.add(label_prod_unit);
		jp4.add(prod_unit);
		jp4.add(label_prod_origin);
		jp4.add(prod_origin);

		JPanel jp5 = new JPanel();
		label_prod_date = new JLabel("��������");
		String prod_date_string = table.getValueAt(selectedRow, 11).toString();
		String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		Date date = new Date();
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.parse(prod_date_string);
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		prod_date = new DatePicker(date, DefaultFormat, MyFont.JTextFieldFont,
				new Dimension(177, 24));
		prod_date.setSize(new Dimension(180, 50));
		label_prod_discount = new JLabel("��Ʒ�ۿ�");
		prod_discount = new JTextField(15);
		prod_discount.setFont(MyFont.JTextFieldFont);
		jp5.add(label_prod_date);
		jp5.add(prod_date);
		jp5.add(label_prod_discount);
		jp5.add(prod_discount);

		JPanel jp6 = new JPanel();
		label_promotion_flag = new JLabel("����״̬");
		promotion_flag = new JComboBox();
		promotion_flag.setPreferredSize(new Dimension(175, 30));
		label_promotion_price = new JLabel("�����۸�");
		promotion_price = new JTextField(15);
		promotion_price.setFont(MyFont.JTextFieldFont);
		jp6.add(label_promotion_flag);
		jp6.add(promotion_flag);
		jp6.add(label_promotion_price);
		jp6.add(promotion_price);

		JPanel jp7 = new JPanel();
		label_category = new JLabel("��������");
		category = new JComboBox();
		category.setPreferredSize(new Dimension(175, 30));
		label_warehouse = new JLabel("�����ֿ�");
		warehouse = new JComboBox();
		warehouse.setPreferredSize(new Dimension(175, 30));
		jp7.add(label_category);
		jp7.add(category);
		jp7.add(label_warehouse);
		jp7.add(warehouse);

		JPanel jp8 = new JPanel();
		label_vendor = new JLabel("��Ӧ��  ");
		vendor = new JComboBox();
		vendor.setPreferredSize(new Dimension(250, 30));
		jp8.add(label_vendor);
		jp8.add(vendor);

		JPanel jp9 = new JPanel();
		label_prod_descr = new JLabel("��Ʒ����");
		prod_descr = new JTextArea(5, 30);
		prod_descr.setFont(MyFont.TipFont);
		prod_descr.setBackground(new Color(130, 160, 240));
		jp9.add(label_prod_descr);
		jp9.add(prod_descr);

		// ���÷����������ݵĻ���
		this.echoData();

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp5);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp6);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp7);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp8);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp9);
		// ��������ص�contentPanel�����
		contentPanel.add(ver);
	}

	/**
	 * ��ʼ����ť���
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		save = new JButton("����");
		save.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		save.setForeground(Color.white);
		save.setFont(MyFont.JButtonFont);
		save.addMouseListener(this);

		reset = new JButton("����");
		reset.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		reset.setForeground(Color.white);
		reset.setFont(MyFont.JButtonFont);
		reset.addMouseListener(this);

		buttonPanel.add(save);
		buttonPanel.add(reset);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// ��ȡ��ǰ�ı��������
		String prod_name_string = prod_name.getText();
		String prod_cost_string = prod_cost.getText();
		String prod_price_string = prod_price.getText();
		String putaway_stock_string = putaway_stock.getText();
		String current_stock_string = current_stock.getText();
		String safe_stock_string = safe_stock.getText();
		String prod_origin_string = prod_origin.getText();
		String prod_discount_string = prod_discount.getText();
		String promotion_price_string = promotion_price.getText();
		String prod_descr_string = prod_descr.getText();
		if (e.getSource() == save) {
			if (prod_name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "��Ʒ���Ʋ���Ϊ�գ�");
			} else if (prod_cost_string.equals("�ɱ�����λС��")) {
				// �ṩĬ��ֵ
				JOptionPane.showMessageDialog(null, "��Ʒ���Ʋ���Ϊ�գ�");
			} else if (prod_price_string.equals("")) {
				prod_cost_string = "0.00";
			} else if (putaway_stock_string.equals("")) {
				putaway_stock_string = "0";
			} else if (current_stock_string.equals("")) {
				current_stock_string = "0";
			} else if (safe_stock_string.equals("")) {
				safe_stock_string = "0";
			} else if (prod_origin_string.equals("")) {
				JOptionPane.showMessageDialog(null, "��Ʒ��Դ����Ϊ�գ�");
			} else if (prod_discount_string.equals("")) {
				prod_discount_string = "0.00";
			} else if (promotion_price_string.equals("")) {
				promotion_price_string = "0.00";
			} else if (prod_descr_string.equals("")) {
				prod_descr_string = "�޾�����Ʒ����";
			}
			double prod_cost_double, prod_price_double, prod_discount_double, promotion_price_double;
			int putaway_stock_int, current_stock_int, safe_stock_int;
			// �����ݽ��д���(��֤��ת��)
			if (!DataValidation.isBigDecimal(prod_cost_string)) {
				JOptionPane.showMessageDialog(null, "��Ʒ�ɱ��������ݸ�ʽ����");
			} else if (!DataValidation.isBigDecimal(prod_price_string)) {
				JOptionPane.showMessageDialog(null, "��Ʒ�ۼ��������ݸ�ʽ����");
			} else if (!DataValidation.isSmallDecimal(prod_discount_string)) {
				JOptionPane.showMessageDialog(null, "��Ʒ�ۿ��������ݸ�ʽ����");
			} else if (!DataValidation.isBigDecimal(promotion_price_string)) {
				JOptionPane.showMessageDialog(null, "��Ʒ�����۸��������ݸ�ʽ����");
			} else if (!DataValidation.isBigDecimal(putaway_stock_string)) {
				JOptionPane.showMessageDialog(null, "��Ʒ�ϼܿ���������ݸ�ʽ����");
			} else if (!DataValidation.isBigDecimal(current_stock_string)) {
				JOptionPane.showMessageDialog(null, "��Ʒ��ǰ����������ݸ�ʽ����");
			} else if (!DataValidation.isBigDecimal(safe_stock_string)) {
				JOptionPane.showMessageDialog(null, "��Ʒ��ȫ����������ݸ�ʽ����");
			} else {
				prod_cost_double = Double.valueOf(prod_cost_string);
				prod_price_double = Double.valueOf(prod_price_string);
				prod_discount_double = Double.valueOf(prod_discount_string);
				promotion_price_double = Double.valueOf(promotion_price_string);
				putaway_stock_int = Integer.valueOf(putaway_stock_string);
				current_stock_int = Integer.valueOf(current_stock_string);
				safe_stock_int = Integer.valueOf(safe_stock_string);
				// ��ȡ��ǰ�������������Ϣ
				Object promotion_state_item = promotion_flag.getSelectedItem();
				Item units_item = (Item) prod_unit.getSelectedItem();
				Item category_item = (Item) category.getSelectedItem();
				Item vendor_item = (Item) vendor.getSelectedItem();
				Item warehouse_item = (Item) warehouse.getSelectedItem();
				// ������������Ϣ��ȡ��ӦҪ��װ��������Ϣ
				int promotion_flag_int = 0;
				if (String.valueOf(promotion_state_item).equals("���")) {
					promotion_flag_int = 0;
				} else if (String.valueOf(promotion_state_item).equals("����")) {
					promotion_flag_int = 1;
				}
				/**
				 * �޸ĵĹ����в��ܹ�����Ʒ��id������������޸� ֻ�ܹ��޸���Ʒ�Ļ�����Ϣ
				 */
				String units_id = units_item.getKey();
				String category_id = category_item.getKey();
				String vendor_id = vendor_item.getKey();
				String warehouse_id = warehouse_item.getKey();
				String prod_id = table.getValueAt(selectedRow, 0).toString();
				String flow_id = table.getValueAt(selectedRow, 1).toString();
				String prod_date_string = prod_date.getText();
				// ������Ʒ���󣬼������ݽ��б���
				Product prod = new Product(prod_id, flow_id, prod_name_string,
						prod_cost_double, prod_price_double, putaway_stock_int,
						current_stock_int, safe_stock_int, units_id,
						prod_origin_string, prod_date_string,
						prod_descr_string, prod_discount_double,
						promotion_flag_int, promotion_price_double, 0,
						category_id, vendor_id, warehouse_id);
				int choose = JOptionPane.showConfirmDialog(null, "ȷ���޸���Ʒ��Ϣ��");
				if (choose == 0) {
					// ���÷����޸�����
					try {
						productService.updateProduct(prod);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// �����ʾ��Ϣ��������ҳ�棬ˢ�������
					JOptionPane.showMessageDialog(null, "��Ʒ��Ϣ����ɹ���");
					this.setVisible(false);
					parentPanel.refreshTablePanelBySearch();
				}
			}
		} else if (e.getSource() == reset) {
			this.echoData();
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
	 * ����������ݵķ���
	 */
	public void echoData() {
		// �ӱ���л�ȡ��Ӧ������
		String prod_name_string = table.getValueAt(selectedRow, 2).toString();
		String prod_cost_string = table.getValueAt(selectedRow, 3).toString();
		String prod_price_string = table.getValueAt(selectedRow, 4).toString();
		String putaway_stock_string = table.getValueAt(selectedRow, 5)
				.toString();
		String current_stock_string = table.getValueAt(selectedRow, 6)
				.toString();
		String safe_stock_string = table.getValueAt(selectedRow, 7).toString();
		String prod_unit_id_string = table.getValueAt(selectedRow, 8)
				.toString();
		String prod_origin_string = table.getValueAt(selectedRow, 10)
				.toString();
		String prod_date_string = table.getValueAt(selectedRow, 11).toString();
		String prod_descr_string = table.getValueAt(selectedRow, 12).toString();
		String prod_discount_string = table.getValueAt(selectedRow, 13)
				.toString();
		String promotion_flag_string = table.getValueAt(selectedRow, 14)
				.toString();
		String promotion_price_string = table.getValueAt(selectedRow, 16)
				.toString();
		String category_id_string = table.getValueAt(selectedRow, 18)
				.toString();
		String vendor_id_string = table.getValueAt(selectedRow, 20).toString();
		String warehouse_id_string = table.getValueAt(selectedRow, 22)
				.toString();

		prod_name.setText(prod_name_string);
		prod_cost.setText(prod_cost_string);
		prod_price.setText(prod_price_string);
		putaway_stock.setText(putaway_stock_string);
		current_stock.setText(current_stock_string);
		safe_stock.setText(safe_stock_string);
		prod_origin.setText(prod_origin_string);
		prod_discount.setText(prod_discount_string);
		promotion_price.setText(promotion_price_string);
		prod_descr.setText(prod_descr_string);
		// DatePicker prod_date;ʱ���޷��޸ĵ�����
//		String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		// ����������Ϣ�����¼��ز��,�޷����¼�������
//		Date date = new Date();
//		try {
//			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//					.parse(prod_date_string);
//		} catch (ParseException e2) {
//			e2.printStackTrace();
//		}
//		System.out.println(date);
//		
//		prod_date = new DatePicker(date, DefaultFormat, MyFont.JTextFieldFont,
//				new Dimension(177, 24));
//		prod_date.setSize(new Dimension(180, 50));
		
		// ����װ�ص�λ��Ϣ
		prod_unit.removeAllItems();
		List<Units> units_list = null;
		try {
			units_list = unitsService.findAllUnitsList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < units_list.size(); i++) {
			int sign = 0;
			String units_id = units_list.get(i).getUnits_id();
			String units_name = units_list.get(i).getUnits_name();
			Item item = new Item(units_id, units_name);
			prod_unit.addItem(item);
			if (units_id.equals(prod_unit_id_string)) {
				sign = i;
				prod_unit.setSelectedIndex(sign);
			}
		}
		// ����װ�ش���״̬��Ϣ
		promotion_flag.removeAllItems();
		promotion_flag.addItem("���");
		promotion_flag.addItem("����");
		if (promotion_flag_string.equals("0")) {
			promotion_flag.setSelectedIndex(0);
		} else if (promotion_flag_string.equals("1")) {
			promotion_flag.setSelectedIndex(1);
		}

		// ���¼��ط�����Ϣ
		category.removeAllItems();
		List<Category> category_list = null;
		try {
			category_list = categoryService.findAllCategoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < category_list.size(); i++) {
			int sign = 0;
			String cid = category_list.get(i).getCategory_id();
			String cname = category_list.get(i).getCategory_name();
			Item item = new Item(cid, cname);
			category.addItem(item);
			if (cid.equals(category_id_string)) {
				sign = i;
				category.setSelectedIndex(sign);
			}
		}
		// ���¼��زֿ���Ϣ
		warehouse.removeAllItems();
		List<Warehouse> warehouse_list = null;
		try {
			warehouse_list = warehouseService.findAllWarehouseList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < warehouse_list.size(); i++) {
			int sign = 0;
			String wid = warehouse_list.get(i).getWarehouse_id();
			String wname = warehouse_list.get(i).getWarehouse_name();
			Item item = new Item(wid, wname);
			warehouse.addItem(item);
			if (wid.equals(warehouse_id_string)) {
				sign = i;
				warehouse.setSelectedIndex(sign);
			}
		}
		// ���¼��ع�Ӧ����Ϣ
		vendor.removeAllItems();
		List<Vendor> vendor_list = null;
		try {
			vendor_list = vendorService.findAllVendorList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < vendor_list.size(); i++) {
			int sign = 0;
			String vid = vendor_list.get(i).getVendor_id();
			String vname = vendor_list.get(i).getVendor_name();
			Item item = new Item(vid, vname);
			vendor.addItem(item);
			if (vid.equals(vendor_id_string)) {
				sign = i;
				vendor.setSelectedIndex(sign);
			}
		}
	}
}
