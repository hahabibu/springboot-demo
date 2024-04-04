package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.fore.ui.control.BaseDataJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.utils.MyFont;

public class ShowVendorProvideProductDetailJFrame extends JFrame implements
		MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_prod_id, label_flow_id, label_prod_name, label_prod_cost,
			label_prod_price, label_putaway_stock, label_current_stock,
			label_safe_stock, label_prod_unit, label_prod_origin,
			label_prod_date, label_prod_descr, label_prod_discount,
			label_promotion_flag, label_promotion_price, label_category,
			label_vendor, label_warehouse;
	JTextField prod_id, flow_id, prod_name, prod_cost, prod_price,
			putaway_stock, current_stock, safe_stock, prod_unit, prod_origin,
			prod_date, prod_descr, prod_discount, promotion_flag,
			promotion_price, category, vendor, warehouse;
	JLabel front, back, exit;
	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ���常���󡢵�ǰ��¼Ա��\���ѡ����
	ShowVendorProvideProductJFrame parentPanel;
	JTable table;
	int selectRow;
	int currentRow;

	// ͨ�����췽����ʼ������
	public ShowVendorProvideProductDetailJFrame(
			ShowVendorProvideProductJFrame parentPanel, JTable table,
			int selectRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectRow = selectRow;
		this.currentRow = selectRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�鿴��Ʒ��ϸ��Ϣ");
		this.setSize(600, 540);
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
		JLabel title = new JLabel("��Ʒ����");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {

		/**
		 * �ӱ���л�ȡ��ǰѡ����Ʒ��������Ϣ
		 */
		String prod_id_string = table.getValueAt(currentRow, 0).toString();
		String flow_id_string = table.getValueAt(currentRow, 1).toString();
		String prod_name_string = table.getValueAt(currentRow, 2).toString();
		String prod_cost_string = table.getValueAt(currentRow, 3).toString();
		String prod_price_string = table.getValueAt(currentRow, 4).toString();
		String putaway_stock_string = table.getValueAt(currentRow, 5)
				.toString();
		String current_stock_string = table.getValueAt(currentRow, 6)
				.toString();
		String safe_stock_string = table.getValueAt(currentRow, 7).toString();
		String prod_unit_string = table.getValueAt(currentRow, 9).toString();
		String prod_origin_string = table.getValueAt(currentRow, 10).toString();
		String prod_date_string = table.getValueAt(currentRow, 11).toString();
		String prod_descr_string = table.getValueAt(currentRow, 12).toString();
		String prod_discount_string = table.getValueAt(currentRow, 13)
				.toString();
		String promotion_flag_string = table.getValueAt(currentRow, 15)
				.toString();
		String promotion_price_string = table.getValueAt(currentRow, 16)
				.toString();
		String category_string = table.getValueAt(currentRow, 19).toString();
		String vendor_string = table.getValueAt(currentRow, 21).toString();
		String warehouse_string = table.getValueAt(currentRow, 23).toString();

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_prod_id = new JLabel("��Ʒid  ");
		prod_id = new JTextField(15);
		prod_id.setFont(MyFont.JTextFieldFont);
		prod_id.setText(prod_id_string);
		prod_id.setToolTipText(prod_id_string);
		prod_id.setEditable(false);
		label_flow_id = new JLabel("������  ");
		flow_id = new JTextField(15);
		flow_id.setFont(MyFont.JTextFieldFont);
		flow_id.setText(flow_id_string);
		flow_id.setToolTipText(flow_id_string);
		flow_id.setEditable(false);
		jp1.add(label_prod_id);
		jp1.add(prod_id);
		jp1.add(label_flow_id);
		jp1.add(flow_id);

		JPanel jp2 = new JPanel();
		label_prod_name = new JLabel("��Ʒ����");
		prod_name = new JTextField(15);
		prod_name.setFont(MyFont.JTextFieldFont);
		prod_name.setText(prod_name_string);
		prod_name.setToolTipText(prod_name_string);
		prod_name.setEditable(false);
		label_prod_cost = new JLabel("��Ʒ�ɱ�");
		prod_cost = new JTextField(15);
		prod_cost.setFont(MyFont.JTextFieldFont);
		prod_cost.setText(prod_cost_string);
		prod_cost.setToolTipText(prod_cost_string);
		prod_cost.setEditable(false);
		jp2.add(label_prod_name);
		jp2.add(prod_name);
		jp2.add(label_prod_cost);
		jp2.add(prod_cost);

		JPanel jp3 = new JPanel();
		label_prod_price = new JLabel("���ۼ۸�");
		prod_price = new JTextField(15);
		prod_price.setFont(MyFont.JTextFieldFont);
		prod_price.setText(prod_price_string);
		prod_price.setToolTipText(prod_price_string);
		prod_price.setEditable(false);
		label_putaway_stock = new JLabel("�ϼܿ��");
		putaway_stock = new JTextField(15);
		putaway_stock.setFont(MyFont.JTextFieldFont);
		putaway_stock.setText(putaway_stock_string);
		putaway_stock.setToolTipText(putaway_stock_string);
		putaway_stock.setEditable(false);
		jp3.add(label_prod_price);
		jp3.add(prod_price);
		jp3.add(label_putaway_stock);
		jp3.add(putaway_stock);

		JPanel jp4 = new JPanel();
		label_current_stock = new JLabel("�ֿ���");
		current_stock = new JTextField(15);
		current_stock.setFont(MyFont.JTextFieldFont);
		current_stock.setText(current_stock_string);
		current_stock.setToolTipText(current_stock_string);
		current_stock.setEditable(false);
		label_safe_stock = new JLabel("��ȫ���");
		safe_stock = new JTextField(15);
		safe_stock.setFont(MyFont.JTextFieldFont);
		safe_stock.setText(safe_stock_string);
		safe_stock.setToolTipText(safe_stock_string);
		safe_stock.setEditable(false);
		jp4.add(label_current_stock);
		jp4.add(current_stock);
		jp4.add(label_safe_stock);
		jp4.add(safe_stock);

		JPanel jp5 = new JPanel();
		label_prod_unit = new JLabel("��Ʒ��λ");
		prod_unit = new JTextField(15);
		prod_unit.setFont(MyFont.JTextFieldFont);
		prod_unit.setText(prod_unit_string);
		prod_unit.setToolTipText(prod_unit_string);
		prod_unit.setEditable(false);
		label_prod_origin = new JLabel("��Ʒ����");
		prod_origin = new JTextField(15);
		prod_origin.setFont(MyFont.JTextFieldFont);
		prod_origin.setText(prod_origin_string);
		prod_origin.setToolTipText(prod_origin_string);
		prod_origin.setEditable(false);
		jp5.add(label_prod_unit);
		jp5.add(prod_unit);
		jp5.add(label_prod_origin);
		jp5.add(prod_origin);

		JPanel jp6 = new JPanel();
		label_prod_date = new JLabel("��������");
		prod_date = new JTextField(15);
		prod_date.setFont(MyFont.JTextFieldFont);
		prod_date.setText(prod_date_string);
		prod_date.setToolTipText(prod_date_string);
		prod_date.setEditable(false);
		label_prod_descr = new JLabel("��Ʒ����");
		prod_descr = new JTextField(15);
		prod_descr.setFont(MyFont.JTextFieldFont);
		prod_descr.setText(prod_descr_string);
		prod_descr.setToolTipText(prod_descr_string);
		prod_descr.setEditable(false);
		jp6.add(label_prod_date);
		jp6.add(prod_date);
		jp6.add(label_prod_descr);
		jp6.add(prod_descr);

		JPanel jp7 = new JPanel();
		label_prod_discount = new JLabel("��Ʒ�ۿ�");
		prod_discount = new JTextField(15);
		prod_discount.setFont(MyFont.JTextFieldFont);
		prod_discount.setText(prod_discount_string);
		prod_discount.setToolTipText(prod_discount_string);
		prod_discount.setEditable(false);
		label_promotion_flag = new JLabel("����״̬");
		promotion_flag = new JTextField(15);
		promotion_flag.setFont(MyFont.JTextFieldFont);
		promotion_flag.setText(promotion_flag_string);
		promotion_flag.setToolTipText(promotion_flag_string);
		promotion_flag.setEditable(false);
		jp7.add(label_prod_discount);
		jp7.add(prod_discount);
		jp7.add(label_promotion_flag);
		jp7.add(promotion_flag);

		JPanel jp8 = new JPanel();
		label_promotion_price = new JLabel("�����۸�");
		promotion_price = new JTextField(15);
		promotion_price.setFont(MyFont.JTextFieldFont);
		promotion_price.setText(promotion_price_string);
		promotion_price.setToolTipText(promotion_price_string);
		promotion_price.setEditable(false);
		label_category = new JLabel("��������");
		category = new JTextField(15);
		category.setFont(MyFont.JTextFieldFont);
		category.setText(category_string);
		category.setToolTipText(category_string);
		category.setEditable(false);
		jp8.add(label_promotion_price);
		jp8.add(promotion_price);
		jp8.add(label_category);
		jp8.add(category);

		JPanel jp9 = new JPanel();
		label_vendor = new JLabel("��Ӧ��  ");
		vendor = new JTextField(15);
		vendor.setFont(MyFont.JTextFieldFont);
		vendor.setText(vendor_string);
		vendor.setToolTipText(vendor_string);
		vendor.setEditable(false);
		label_warehouse = new JLabel("�����ֿ�");
		warehouse = new JTextField(15);
		warehouse.setFont(MyFont.JTextFieldFont);
		warehouse.setText(warehouse_string);
		warehouse.setToolTipText(warehouse_string);
		warehouse.setEditable(false);
		jp9.add(label_vendor);
		jp9.add(vendor);
		jp9.add(label_warehouse);
		jp9.add(warehouse);

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
		Icon front_icon = new ImageIcon("icons/toolImage/front.png");
		front = new JLabel(front_icon);
		front.setToolTipText("��һ��");
		front.addMouseListener(this);

		Icon back_icon = new ImageIcon("icons/toolImage/back.png");
		back = new JLabel(back_icon);
		back.setToolTipText("��һ��");
		back.addMouseListener(this);

		Icon exit_icon = new ImageIcon("icons/toolImage/exit.png");
		exit = new JLabel(exit_icon);
		exit.setToolTipText("�˳��鿴");
		exit.addMouseListener(this);
		
		buttonPanel.add(front);
		buttonPanel.add(back);
		buttonPanel.add(exit);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == front) {
			if (currentRow > 0) {
				currentRow = currentRow - 1;
				// ˢ���������
				refreshPanel();
			} else {
				JOptionPane.showMessageDialog(null, "ǰ��û�������ˣ�");
			}
		} else if (e.getSource() == back) {
			if (currentRow < table.getRowCount() - 1) {
				currentRow = currentRow + 1;
				// ˢ���������
				refreshPanel();
			} else {
				JOptionPane.showMessageDialog(null, "����û�������ˣ�");
			}
		} else if (e.getSource() == exit) {
			this.setVisible(false);
			;
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

	private void refreshPanel() {
		// ˢ����ʾ�������
		backgroundPanel.remove(contentPanel);
		initContentPanel();
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}
}
