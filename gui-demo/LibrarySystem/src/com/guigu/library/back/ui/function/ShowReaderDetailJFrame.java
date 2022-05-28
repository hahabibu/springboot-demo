package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.guigu.library.back.ui.control.ReaderArchivesManagerJPanel;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;

public class ShowReaderDetailJFrame extends JFrame implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_id, label_barcode, label_academic_num, label_name, label_sex,
			label_birthday, label_idCard, label_phone, label_email,
			label_descr, label_account, label_classify, label_libraryCard;
	JTextField id, barcode, academic_num, name, sex, birthday, idCard, phone,
			email, account, classify, libraryCard;
	JTextArea descr;
	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ����service
	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();
	ReaderService readerService = new ReaderServiceImpl();
	UsersService usersService = new UsersServiceImpl();
	LibraryCardService libraryCardService = new LibraryCardServiceImpl();

	// ��һ������һ�����˳�
	JLabel front, back, exit;

	// ���常���󡢸����ѡ�е���
	ReaderArchivesManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;
	int currentRow;

	// ͨ�����췽����ʼ������
	public ShowReaderDetailJFrame(ReaderArchivesManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		this.currentRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("���Ķ�����Ϣ");
		this.setSize(600, 550);
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
		JLabel title = new JLabel("���Ķ�����Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_id = new JLabel("����id  ");
		id = new JTextField(15);
		id.setFont(MyFont.JTextFieldFont);
		id.setForeground(MyColor.JTextFieldColor);
		id.setEditable(false);

		label_barcode = new JLabel("������  ");
		barcode = new JTextField(15);
		barcode.setFont(MyFont.JTextFieldFont);
		barcode.setForeground(MyColor.JTextFieldColor);
		barcode.setEditable(false);
		jp1.add(label_id);
		jp1.add(id);
		jp1.add(label_barcode);
		jp1.add(barcode);

		JPanel jp2 = new JPanel();
		label_academic_num = new JLabel("ѧ�����");
		academic_num = new JTextField(15);
		academic_num.setFont(MyFont.JTextFieldFont);
		academic_num.setForeground(MyColor.JTextFieldColor);
		academic_num.setEditable(false);

		label_name = new JLabel("��������");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		name.setEditable(false);
		jp2.add(label_academic_num);
		jp2.add(academic_num);
		jp2.add(label_name);
		jp2.add(name);

		JPanel jp3 = new JPanel();
		label_sex = new JLabel("�����Ա�");
		sex = new JTextField(15);
		sex.setFont(MyFont.JTextFieldFont);
		sex.setForeground(MyColor.JTextFieldColor);
		sex.setEditable(false);

		label_birthday = new JLabel("��������");
		birthday = new JTextField(15);
		birthday.setFont(MyFont.JTextFieldFont);
		birthday.setForeground(MyColor.JTextFieldColor);
		birthday.setEditable(false);
		jp3.add(label_sex);
		jp3.add(sex);
		jp3.add(label_birthday);
		jp3.add(birthday);

		JPanel jp4 = new JPanel();
		label_idCard = new JLabel("���֤��");
		idCard = new JTextField(15);
		idCard.setFont(MyFont.JTextFieldFont);
		idCard.setForeground(MyColor.JTextFieldColor);
		idCard.setEditable(false);

		label_phone = new JLabel("��ϵ��ʽ");
		phone = new JTextField(15);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setForeground(MyColor.JTextFieldColor);
		phone.setEditable(false);
		jp4.add(label_idCard);
		jp4.add(idCard);
		jp4.add(label_phone);
		jp4.add(phone);

		JPanel jp5 = new JPanel();
		label_email = new JLabel("��������");
		email = new JTextField(15);
		email.setFont(MyFont.JTextFieldFont);
		email.setForeground(MyColor.JTextFieldColor);
		email.setEditable(false);

		label_classify = new JLabel("��������");
		classify = new JTextField(15);
		classify.setFont(MyFont.JTextFieldFont);
		classify.setForeground(MyColor.JTextFieldColor);
		classify.setEditable(false);
		jp5.add(label_email);
		jp5.add(email);
		jp5.add(label_classify);
		jp5.add(classify);

		JPanel jp6 = new JPanel();
		label_account = new JLabel("ʹ���˺�");
		account = new JTextField(15);
		account.setFont(MyFont.JTextFieldFont);
		account.setForeground(MyColor.JTextFieldColor);
		account.setEditable(false);

		label_libraryCard = new JLabel("����֤��");
		libraryCard = new JTextField(15);
		libraryCard.setFont(MyFont.JTextFieldFont);
		libraryCard.setForeground(MyColor.JTextFieldColor);
		libraryCard.setEditable(false);
		jp6.add(label_account);
		jp6.add(account);
		jp6.add(label_libraryCard);
		jp6.add(libraryCard);

		JPanel jp7 = new JPanel();
		label_descr = new JLabel("���߼���");
		descr = new JTextArea(7, 30);
		descr.setFont(MyFont.JTextFieldFont);
		descr.setForeground(MyColor.JTextFieldColor);
		descr.setEditable(false);
		descr.setLineWrap(true);
		jp7.add(label_descr);
		jp7.add(descr);

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
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp5);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp6);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp7);
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
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(back);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(exit);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == front) {
			if (currentRow > 0) {
				currentRow = currentRow - 1;
				// ˢ���������
				this.refreshContentPanel();
			} else {
				JOptionPane.showMessageDialog(null, "ǰ��û�������ˣ�");
			}
		} else if (e.getSource() == back) {
			if (currentRow < parentTable.getRowCount() - 1) {
				currentRow = currentRow + 1;
				// ˢ���������
				this.refreshContentPanel();
			} else {
				JOptionPane.showMessageDialog(null, "����û�������ˣ�");
			}
		} else if (e.getSource() == exit) {
			this.setVisible(false);
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
	 * �������ݻ��Է���
	 */
	public void echoData() {
		// ���ݸ�����е�ǰָ�����л�ȡ���ߵ�������Ϣ��
		String read_id_string = parentTable.getValueAt(currentRow, 0)
				.toString();
		String barcode_string = parentTable.getValueAt(currentRow, 1).toString();
		String academic_num_string = parentTable.getValueAt(currentRow, 2).toString();
		String name_string = parentTable.getValueAt(currentRow, 3).toString();
		String sex_string = parentTable.getValueAt(currentRow, 4).toString();
		String birthday_string = parentTable.getValueAt(currentRow, 5).toString().substring(0,10);
		String idCard_string = parentTable.getValueAt(currentRow, 6).toString();
		String phone_string = parentTable.getValueAt(currentRow, 7).toString();
		String email_string = parentTable.getValueAt(currentRow, 8).toString();
		String descr_string = parentTable.getValueAt(currentRow, 9).toString();
		String account_id_string = parentTable.getValueAt(currentRow, 10).toString();
		String classify_num_string = parentTable.getValueAt(currentRow, 12).toString();
		String libraryCard_id_string = parentTable.getValueAt(currentRow, 14).toString();
		
		// ���ݻ���
		id.setText(read_id_string);
		id.setToolTipText(read_id_string);
		barcode.setText(barcode_string);
		barcode.setToolTipText(barcode_string);
		academic_num.setText(academic_num_string);
		academic_num.setToolTipText(academic_num_string);
		name.setText(name_string);
		name.setToolTipText(name_string);
		sex.setText(sex_string);
		sex.setToolTipText(sex_string);
		birthday.setText(birthday_string);
		birthday.setToolTipText(birthday_string);
		idCard.setText(idCard_string);
		idCard.setToolTipText(idCard_string);
		phone.setText(phone_string);
		phone.setToolTipText(phone_string);
		email.setText(email_string);
		email.setToolTipText(email_string);
		descr.setText(descr_string);
		descr.setToolTipText(descr_string);
		// ����id��ȡ��Ӧ����Ϣ
		String account_name;
		String classify_name;
		String libraryCard_num;
		try {
			account_name = usersService.getUsersName(account_id_string);
			classify_name = readerClassifyService.getClassifyNameByum(Integer.valueOf(classify_num_string));
			libraryCard_num = libraryCardService.getLibraryCardNumById(libraryCard_id_string);
			account.setText(account_name);
			account.setToolTipText(account_name);
			classify.setText(classify_name);
			classify.setToolTipText(classify_name);
			libraryCard.setText(libraryCard_num);
			libraryCard.setToolTipText(libraryCard_num);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ˢ���������
	 */
	public void refreshContentPanel() {
		backgroundPanel.remove(contentPanel);
		initContentPanel();
		backgroundPanel.add(contentPanel);
		backgroundPanel.validate();
	}
}