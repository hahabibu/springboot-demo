package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
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

import com.guigu.library.back.ui.control.ReaderClassifyManagerJPanel;
import com.guigu.library.model.ReaderClassify;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;

public class UpdateReaderClassifyJFrame extends JFrame implements MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_num, label_name, label_maximum, label_time_limit;
	JTextField num, name, maximum, time_limit;
	JButton save_button, reset_button;

	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();
	// ���常����
	ReaderClassifyManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// ͨ�����췽����ʼ������
	public UpdateReaderClassifyJFrame(ReaderClassifyManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�޸Ķ��߷�����Ϣ");
		this.setSize(420, 350);
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
		JLabel title = new JLabel("�޸Ķ��߷�����Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_num = new JLabel("������");
		num = new JTextField(15);
		num.setFont(MyFont.JTextFieldFont);
		num.setForeground(MyColor.JTextFieldColor);
		num.setEditable(false);
		jp1.add(label_num);
		jp1.add(num);

		JPanel jp2 = new JPanel();
		label_name = new JLabel("��������");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		name.setEditable(false);
		jp2.add(label_name);
		jp2.add(name);

		JPanel jp3 = new JPanel();
		label_maximum = new JLabel("�������");
		maximum = new JTextField(15);
		maximum.setFont(MyFont.JTextFieldFont);
		maximum.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_maximum);
		jp3.add(maximum);

		JPanel jp4 = new JPanel();
		label_time_limit = new JLabel("��������");
		time_limit = new JTextField(15);
		time_limit.setFont(MyFont.JTextFieldFont);
		time_limit.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_time_limit);
		jp4.add(time_limit);

		// ���ݻ���
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
			String num_string = num.getText();
			String name_string = name.getText();
			String maximum_string = maximum.getText();
			String time_limit_string = time_limit.getText();
			if (num_string.equals("")) {
				JOptionPane.showMessageDialog(null, "ͼ������Ų���Ϊ��");
			} else if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "ͼ��������Ʋ���Ϊ��");
			} else if (maximum_string.equals("")) {
				JOptionPane.showMessageDialog(null, "ͼ��������������Ϊ��");
			} else if (time_limit_string.equals("")) {
				JOptionPane.showMessageDialog(null, "ͼ��������޲���Ϊ��");
			} else {
				// �����ݽ�����֤
				if (maximum_string.length() > 3
						|| time_limit_string.length() > 3) {
					JOptionPane.showMessageDialog(null, "�������������Ȳ��ܳ���3��");
				} else if (!DataValidation.isSignlessInteger(maximum_string)
						|| !DataValidation.isSignlessInteger(time_limit_string)) {
					JOptionPane
							.showMessageDialog(null, "�����ʽ���������벻������λ������������");
				} else {
					int num_int = Integer.valueOf(num_string);
					int maximum_int = Integer.valueOf(maximum_string);
					int time_limit_int = Integer.valueOf(time_limit_string);
					// ����ReaderClassify���󣬽����ݱ��浽���ݿ���
					ReaderClassify rc = new ReaderClassify(num_int,
							name_string, maximum_int, time_limit_int);
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ���޸Ķ��߷�����Ϣ��");
					if (choose == 0) {
						try {
							readerClassifyService.updateReaderClassify(rc);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
						JOptionPane.showMessageDialog(null, "ͼ�������Ϣ����ɹ�");
						this.setVisible(false);
						parentPanel.refreshTablePanel();
					} else {
						this.setVisible(false);
					}
				}
			}
		} else if (e.getSource() == reset_button) {
			// ��������
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
	 * �������ݻ��Է���echoData
	 */
	public void echoData() {
		// ���ݵ�ǰѡ�е����ݼ�¼������Ϣ����
		String num_string = parentTable.getValueAt(selectedRow, 0).toString();
		String name_string = parentTable.getValueAt(selectedRow, 1).toString();
		String maximum_string = parentTable.getValueAt(selectedRow, 2)
				.toString();
		String time_limit_string = parentTable.getValueAt(selectedRow, 3)
				.toString();
		num.setText(num_string);
		num.setToolTipText(num_string);
		name.setText(name_string);
		name.setToolTipText(name_string);
		maximum.setText(maximum_string);
		maximum.setToolTipText(maximum_string);
		time_limit.setText(time_limit_string);
		time_limit.setToolTipText(time_limit_string);
	}
}