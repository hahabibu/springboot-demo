package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.back.ui.control.DepartmentManagerJPanel;
import com.design.sm.model.Department;
import com.design.sm.service.DepartmentService;
import com.design.sm.service.impl.DepartmentServiceImpl;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;

public class AddDepartmentJFrame extends JFrame implements MouseListener,
		FocusListener {
	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_name, label_manager, label_descr;
	JTextField name;
	JTextArea descr;
	JComboBox manager;
	JButton save_button, reset_button;

	DepartmentService departmentService = new DepartmentServiceImpl();

	// ���常����
	DepartmentManagerJPanel parentPanel;

	// ͨ�����췽����ʼ������
	public AddDepartmentJFrame(DepartmentManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("��Ӳ�����Ϣ");
		this.setSize(420, 370);
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
		JLabel title = new JLabel("��Ӳ�����Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		/**
		 * ��Ӳ��ŵ���Ϣ�� ����id��������ɵ�10int������ �������ơ��������ܡ��������� ֮���ٴ�����ص�����
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_name = new JLabel("��������");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("������");
		name.addFocusListener(this);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_manager = new JLabel("��������");
		manager = new JComboBox();
		manager.setPreferredSize(new Dimension(175, 30));
		// �����½���û��Ա���ģ����ѡ��Ϊ��
		manager.addItem("��");
		jp2.add(label_manager);
		jp2.add(manager);

		JPanel jp3 = new JPanel();
		label_descr = new JLabel("��������");
		descr = new JTextArea(6, 15);
		descr.setFont(MyFont.TipFont);
		descr.setForeground(MyColor.TipColor);
		descr.setText("������ź�����ʲô��û�����£�");
		descr.setLineWrap(true);// �Զ�����
		descr.addFocusListener(this);
		jp3.add(label_descr);
		jp3.add(descr);

		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
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
			String name_string = name.getText();
			String descr_string = descr.getText();
			if (name_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "�������Ʋ���Ϊ��");
			} else {
				// ��ȡ����������ѡ�Ĭ��Ϊ�գ�ֱ�ӻ�ȡ���ɣ�
				Object manager_select = manager.getSelectedItem();
				// ����Department���󣬽����ݱ��浽���ݿ���
				// ����id��������ɵ�10λint���͵��ַ�ƴ�ӵ�����
				String id = RandomGeneration.getRandom10numSeq();
				Department dept = new Department(id, name_string,
						String.valueOf(manager_select), descr_string);
				int choose = JOptionPane.showConfirmDialog(null, "ȷ����Ӳ�����Ϣ��");
				if (choose == 0) {
					try {
						departmentService.addDepartment(dept);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
					JOptionPane.showMessageDialog(null, "������Ϣ����ɹ�");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
				} else {
					this.setVisible(false);
				}
			}
		} else if (e.getSource() == reset_button) {
			// ������е�����
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("������");
			descr.setFont(MyFont.TipFont);
			descr.setForeground(MyColor.TipColor);
			descr.setText("������ź�����ʲôҲû�����£�");

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

	// �۽��¼�
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == name) {
			if (name.getText().equals("������")) {
				name.setForeground(MyColor.JTextFieldColor);
				name.setFont(MyFont.JTextFieldFont);
				name.setText("");
			}
		} else if (e.getSource() == descr) {
			if (descr.getText().equals("������ź�����ʲô��û�����£�")) {
				descr.setForeground(MyColor.JTextFieldColor);
				descr.setFont(MyFont.JTextFieldFont);
				descr.setText("");
			}
		}
	}

	// ʧȥ�����¼�
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == name) {
			if (name.getText().equals("")) {
				name.setFont(MyFont.TipFont);
				name.setForeground(MyColor.TipColor);
				name.setText("������");
			}
		} else if (e.getSource() == descr) {
			if (descr.getText().equals("")) {
				descr.setFont(MyFont.TipFont);
				descr.setForeground(MyColor.TipColor);
				descr.setText("������ź�����ʲô��û�����£�");
			}
		}
	}
}