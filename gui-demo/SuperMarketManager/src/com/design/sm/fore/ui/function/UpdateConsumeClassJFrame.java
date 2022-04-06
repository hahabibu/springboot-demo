package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.fore.ui.control.ConsumeClassManagerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.ConsumeClass;
import com.design.sm.service.AccountsService;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;

public class UpdateConsumeClassJFrame extends JFrame implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_class_id, label_class_name, label_class_off,
			label_class_discount;
	JTextField class_id, class_name, class_off, class_discount;
	JButton save_button, reset_button;
	AccountsService userService = new AccountsServiceImpl();

	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ���常���󡢱��ѡ����
	ConsumeClassManagerJPanel parentPanel;
	JTable table;
	int selectedRow;

	ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();

	// ͨ�����췽����ʼ������
	public UpdateConsumeClassJFrame(ConsumeClassManagerJPanel parentPanel,
			JTable table, int selectedRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("���ѵȼ��޸�");
		this.setSize(425, 325);
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
		JLabel title = new JLabel("�����˺��޸�");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_class_id = new JLabel("���ѵȼ�");
		class_id = new JTextField(15);
		class_id.setFont(MyFont.JTextFieldFont);
		class_id.setForeground(MyColor.JTextFieldColor);
		class_id.setEditable(false);
		jp1.add(label_class_id);
		jp1.add(class_id);

		JPanel jp2 = new JPanel();
		label_class_name = new JLabel("�ȼ�����");
		class_name = new JTextField(15);
		class_name.setFont(MyFont.JTextFieldFont);
		class_name.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_class_name);
		jp2.add(class_name);

		JPanel jp3 = new JPanel();
		label_class_off = new JLabel("�ȼ��Ż�");
		class_off = new JTextField(15);
		class_off.setFont(MyFont.JTextFieldFont);
		class_off.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_class_off);
		jp3.add(class_off);

		JPanel jp4 = new JPanel();
		label_class_discount = new JLabel("�ȼ��ۿ�");
		class_discount = new JTextField(15);
		class_discount.setFont(MyFont.JTextFieldFont);
		class_discount.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_class_discount);
		jp4.add(class_discount);

		// ����������Ϣ
		this.echoData();

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);

		// ��������ص�contentPanel�����
		contentPanel.add(ver);
	}

	/**
	 * ��ʼ����ť���
	 */
	private void initButtonPanel() {
		Box hor = Box.createHorizontalBox();
		buttonPanel = new JPanel();
		save_button = new JButton("����");
		save_button.setForeground(Color.white);
		save_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.blue));

		reset_button = new JButton("����");
		reset_button.setForeground(Color.white);
		reset_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));

		// ��Ӱ�ť����
		save_button.addMouseListener(this);
		reset_button.addMouseListener(this);

		// ����ť���ص������
		hor.add(save_button);
		hor.add(Box.createHorizontalStrut(20));
		hor.add(reset_button);
		buttonPanel.add(hor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == save_button) {
			// ��ȡ��ǰ�ı����������Ϣ
			String id_string = table.getValueAt(selectedRow, 0).toString();
			String name_string = class_name.getText();
			String off_string = class_off.getText();
			String discount_string = class_discount.getText();
			if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "���ѵȼ����Ʋ���Ϊ��");
			} else if (off_string.equals("")) {
				JOptionPane.showMessageDialog(null, "�ȼ��Żݲ���Ϊ�գ���������������");
			} else if (discount_string.equals("")) {
				JOptionPane.showMessageDialog(null, "�ȼ��ۿ۲���Ϊ�գ��ɱ�����λС��");
			} else {
				// �ж�����ĵȼ��Żݺ͵ȼ��ۿ������Ƿ�Ϸ�
				if (!DataValidation.isSignlessInteger(off_string)) {
					JOptionPane.showMessageDialog(null, "�ȼ��Ż������ʽ����");
				} else if (!DataValidation.isSmallDecimal(discount_string)) {
					JOptionPane.showMessageDialog(null, "�ȼ��ۿ������ʽ����");
				} else {
					// �������ѵȼ�id�޸ĵȼ���Ϣ
					int id_int = Integer.valueOf(id_string);
					int off_int = Integer.valueOf(off_string);
					double discount_double = Double.valueOf(discount_string);
					ConsumeClass cc = new ConsumeClass(id_int, name_string,
							off_int, discount_double);
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ���޸����ѵȼ���Ϣ��");
					if (choose == 0) {
						try {
							consumeClassService.updateConsumeClass(cc);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
						JOptionPane.showMessageDialog(null, "�ȼ���Ϣ�޸ĳɹ�!");
						parentPanel.refreshTablePanel();
						this.setVisible(false);
					} 
				}
			}
		} else if (e.getSource() == reset_button) {
			// ������е����ݣ�����������Ϣ
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
	 * �������ݵķ���
	 */
	public void echoData() {
		// ����ѡ�еı�����ݷ�����Ӧ����Ϣ
		String class_id_string = table.getValueAt(selectedRow, 0).toString();
		String class_name_string = table.getValueAt(selectedRow, 1).toString();
		String class_off_string = table.getValueAt(selectedRow, 2).toString();
		String class_discount_string = table.getValueAt(selectedRow, 3)
				.toString();
		class_id.setText(class_id_string);
		class_id.setToolTipText(class_id_string);
		class_name.setText(class_name_string);
		class_name.setToolTipText(class_name_string);
		class_off.setText(class_off_string);
		class_off.setToolTipText(class_off_string);
		class_discount.setText(class_discount_string);
		class_discount.setToolTipText(class_discount_string);
	}

}
