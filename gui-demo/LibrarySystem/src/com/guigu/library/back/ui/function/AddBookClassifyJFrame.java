package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.BooksClassifyManagerJPanel;
import com.guigu.library.model.BookClassify;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.utils.ImagePanel;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;

public class AddBookClassifyJFrame extends JFrame implements MouseListener,
		FocusListener {
	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_num, label_name, label_level, label_parent_num;
	JTextField num, name, level, parent_num;
	JButton save_button, reset_button;

	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();

	// ���常����
	BooksClassifyManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// ͨ�����췽����ʼ������
	public AddBookClassifyJFrame(BooksClassifyManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("���ͼ�������Ϣ");
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
		JLabel title = new JLabel("���ͼ�������Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		/**
		 * ���ͼ��������Ϣ�� ͼ������Ÿ��ݸ�����Զ������� ͼ��������ơ�����ȼ���������������ɵ�ǰ��ѡ�е�����Ĭ�ϴ�����һ������
		 */
		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_num = new JLabel("������");
		num = new JTextField(15);
		num.setFont(MyFont.TipFont);
		num.setForeground(MyColor.TipColor);
		num.setText("������");
		num.addFocusListener(this);
		jp1.add(label_num);
		jp1.add(num);

		JPanel jp2 = new JPanel();
		label_name = new JLabel("��������");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("������");
		name.addFocusListener(this);
		jp2.add(label_name);
		jp2.add(name);

		JPanel jp3 = new JPanel();
		label_level = new JLabel("����ȼ�");
		level = new JTextField(15);
		level.setFont(MyFont.JTextFieldFont);
		level.setForeground(MyColor.JTextFieldColor);
		level.addFocusListener(this);
		level.setEditable(false);
		jp3.add(label_level);
		jp3.add(level);

		JPanel jp4 = new JPanel();
		label_parent_num = new JLabel("�������");
		parent_num = new JTextField(15);
		parent_num.setFont(MyFont.JTextFieldFont);
		parent_num.setForeground(MyColor.JTextFieldColor);
		parent_num.addFocusListener(this);
		parent_num.setEditable(false);
		jp4.add(label_parent_num);
		jp4.add(parent_num);

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
			int level_int = Integer.valueOf(level.getText());
			String parent_num_string = parent_num.getText();
			if (num_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "ͼ������Ų���Ϊ��");
			} else if (name_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "ͼ��������Ʋ���Ϊ��");
			} else {
				try {
					BookClassify findBC = bookClassifyService
							.getBookClassifyByNum(num_string);
					if (findBC != null) {
						JOptionPane.showMessageDialog(null, "��ǰ����Ѵ��ڣ���һ�����԰ɣ�");
					} else {
						// ����BookClassify���󣬽����ݱ��浽���ݿ���
						BookClassify bc = new BookClassify(num_string,
								name_string, level_int, parent_num_string);
						int choose = JOptionPane.showConfirmDialog(null,
								"ȷ�����ͼ�������Ϣ��");
						if (choose == 0) {
							bookClassifyService.addBookClassify(bc);
							// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
							JOptionPane.showMessageDialog(null, "ͼ�������Ϣ����ɹ�");
							this.setVisible(false);
							parentPanel.refreshTablePanel();
						} else {
							this.setVisible(false);
						}
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == reset_button) {
			// ������е�����
			num.setFont(MyFont.TipFont);
			num.setForeground(MyColor.TipColor);
			num.setText("������");
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("������");
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

	// �۽��¼�
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == num) {
			if (num.getText().equals("������")) {
				num.setForeground(MyColor.JTextFieldColor);
				num.setFont(MyFont.JTextFieldFont);
				num.setText("");
			}
		} else if (e.getSource() == name) {
			if (name.getText().equals("������")) {
				name.setForeground(MyColor.JTextFieldColor);
				name.setFont(MyFont.JTextFieldFont);
				name.setText("");
			}
		}
	}

	// ʧȥ�����¼�
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == num) {
			if (num.getText().equals("")) {
				num.setFont(MyFont.TipFont);
				num.setForeground(MyColor.TipColor);
				num.setText("������");
			}
		} else if (e.getSource() == name) {
			if (name.getText().equals("")) {
				name.setFont(MyFont.TipFont);
				name.setForeground(MyColor.TipColor);
				name.setText("������");
			}
		}
	}

	/**
	 * �������ݻ��Է���echoData
	 */
	public void echoData() {
		// ���ݵ�ǰ�ĸ���Ŀ¼������һ��Ŀ¼��Ϣ
		// ��ǰѡ�еļ�¼�ķ�������Ҫ�������µķ���ĸ������
		String parent_num_string = parentTable.getValueAt(selectedRow, 0)
				.toString();
		parent_num.setText(parent_num_string);
		parent_num.setToolTipText(parent_num_string);
		int parent_level = Integer.valueOf(parentTable.getValueAt(selectedRow, 2).toString());
		level.setText((parent_level + 1) + "");
		level.setToolTipText((parent_level + 1) + "");
	}
}