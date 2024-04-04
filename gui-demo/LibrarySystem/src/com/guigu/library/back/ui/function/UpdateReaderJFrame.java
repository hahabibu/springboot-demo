package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.ReaderArchivesManagerJPanel;
import com.guigu.library.model.Reader;
import com.guigu.library.model.ReaderClassify;
import com.guigu.library.model.Users;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.Chooser;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.Item;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class UpdateReaderJFrame extends JFrame implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_academic_num, label_name, label_sex, label_birthday,
			label_idCard, label_phone, label_email, label_descr,
			label_classify;
	JTextField academic_num, name, birthday, idCard, phone, email;
	ButtonGroup sex;
	JRadioButton nan, nv;
	JTextArea descr;
	JComboBox classify;
	Chooser birthday_chooser;

	JButton save, reset;
	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ����service
	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();
	ReaderService readerService = new ReaderServiceImpl();
	UsersService usersService = new UsersServiceImpl();
	LibraryCardService libraryCardService = new LibraryCardServiceImpl();

	// ���常���󡢸����ѡ�е���
	ReaderArchivesManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// ͨ�����췽����ʼ������
	public UpdateReaderJFrame(ReaderArchivesManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�޸Ķ�����Ϣ");
		this.setSize(600, 450);
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
		JLabel title = new JLabel("�޸Ķ�����Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_academic_num = new JLabel("ѧ�����");
		academic_num = new JTextField(15);
		academic_num.setFont(MyFont.JTextFieldFont);
		academic_num.setForeground(MyColor.JTextFieldColor);

		label_name = new JLabel("��������");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_academic_num);
		jp1.add(academic_num);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_sex = new JLabel("�����Ա�");
		sex = new ButtonGroup();
		nan = new JRadioButton("��");
		nv = new JRadioButton("Ů");
		sex.add(nan);
		sex.add(nv);

		label_birthday = new JLabel("��������");
		birthday = new JTextField(15);
		birthday_chooser = Chooser.getInstance();
		birthday_chooser.register(birthday);
		birthday.setFont(MyFont.JTextFieldFont);
		birthday.setForeground(MyColor.JTextFieldColor);

		jp2.add(label_sex);
		jp2.add(nan);
		jp2.add(nv);
		jp2.add(label_birthday);
		jp2.add(birthday);

		JPanel jp3 = new JPanel();
		label_idCard = new JLabel("���֤��");
		idCard = new JTextField(15);
		idCard.setFont(MyFont.JTextFieldFont);
		idCard.setForeground(MyColor.JTextFieldColor);

		label_phone = new JLabel("��ϵ��ʽ");
		phone = new JTextField(15);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setForeground(MyColor.JTextFieldColor);
		jp3.add(label_idCard);
		jp3.add(idCard);
		jp3.add(label_phone);
		jp3.add(phone);

		JPanel jp4 = new JPanel();
		label_email = new JLabel("��������");
		email = new JTextField(15);
		email.setFont(MyFont.JTextFieldFont);
		email.setForeground(MyColor.JTextFieldColor);

		label_classify = new JLabel("��������");
		classify = new JComboBox();
		classify.setPreferredSize(new Dimension(175, 30));
		classify.setFont(MyFont.JTextFieldFont);
		classify.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_email);
		jp4.add(email);
		jp4.add(label_classify);
		jp4.add(classify);

		JPanel jp5 = new JPanel();
		label_descr = new JLabel("���߼���");
		descr = new JTextArea(5, 30);
		descr.setFont(MyFont.JTextFieldFont);
		descr.setForeground(MyColor.JTextFieldColor);
		descr.setLineWrap(true);
		jp5.add(label_descr);
		jp5.add(descr);

		// ���ݻ���
		this.echoData();
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp5);
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
		// ��ȡ������͵�ѡ��ѡ��
		String sex_string = "";
		if (nan.isSelected()) {
			sex_string = "��";
		} else if (nv.isSelected()) {
			sex_string = "Ů";
		}
		int classify_num = 0;
		Item classify_item = (Item) classify.getSelectedItem();
		classify_num = Integer.valueOf(classify_item.getKey());

		// ��ȡ��ǰ�ı��������
		String academic_num_string = academic_num.getText();
		String name_string = name.getText();
		String birthday_string = birthday.getText();
		String idCard_string = idCard.getText();
		String phone_string = phone.getText();
		String email_string = email.getText();
		String descr_string = descr.getText();

		if (e.getSource() == save) {
			if (academic_num_string.equals("")) {
				if (classify_num == 0) {
					// �������ͨ�οͣ����Զ��������8λint���͵����У�������ʾ������Ϣ
					academic_num_string = RandomGeneration.getRandom8numSeq();
				} else {
					JOptionPane.showMessageDialog(null, "����ͨ�ο����֮��Ķ���ѧ����Ų���Ϊ��");
				}
			}
			if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "������������Ϊ�գ�");
			} else if (birthday_string.equals("")) {
				JOptionPane.showMessageDialog(null, "�������ղ���Ϊ�գ�");
			} else if (idCard_string.equals("")) {
				JOptionPane.showMessageDialog(null, "���֤�Ų���Ϊ�գ�");
			} else if (phone_string.equals("")) {
				JOptionPane.showMessageDialog(null, "��ϵ��ʽ����Ϊ�գ�");
			} else if (email_string.equals("")) {
				JOptionPane.showMessageDialog(null, "�������䲻��Ϊ�գ�");
			} else {
				// �ṩĬ��ֵ Ҫע���߼��ж�
				if (descr_string.equals("")) {
					// ָ����Ӧ����Ϣ
					descr_string = "������ߺ�����ʲô��û������";
				}
				// �����ݽ�����֤
				if (!DataValidation.isBigDecimal(academic_num_string)) {
					JOptionPane.showMessageDialog(null, "ѧ������������ݸ�ʽ����");
				} else if (!DataValidation.isValidIdCard(idCard_string)) {
					JOptionPane.showMessageDialog(null, "���֤���������ݸ�ʽ����");
				} else if (!DataValidation.isValidPhone(phone_string)) {
					JOptionPane.showMessageDialog(null, "��ϵ��ʽ�������ݸ�ʽ����");
				} else if (!DataValidation.isValidEmail(email_string)) {
					JOptionPane.showMessageDialog(null, "���������������ݸ�ʽ����");
				} else {
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ���޸Ķ�����Ϣ��");
					if (choose == 0) {
						// ֻ���¶��ߵĻ���������Ϣ������֤��Ϣ�������˺���Ϣ��������
						// �������˺�һ��ʼ���û�������ѧ��������������˴˴��迼��ѧ����ŷ����ı���������û���¼�˺���Ϣ
						String academic_num_string_old = parentTable.getValueAt(selectedRow, 2).toString();
						String user_id = parentTable.getValueAt(selectedRow, 10).toString();
						// �����˺�id��ȡ�˺���Ϣ
						Users user = null;
						try {
							user = usersService.getUsersById(user_id);
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						
						String user_name = user.getUser_name();
						String user_password = user.getUser_password();
						if(!academic_num_string_old.equals(academic_num_string)){
							// �����˺��û���Ϣ����ʼ��Ϊ�û���Ϊѧ����ţ�����Ϊ000000��
							// ����ѧ�������Ϊ��Ӧ�˻����û�������ʼ����Ĭ��Ϊ000000,��ʼĬ��Ϊ����״̬
							user_name = academic_num_string;
							user_password = "000000";
						}
						// ����id�������롢����֤��Ϣ����
						String reader_id = parentTable.getValueAt(selectedRow, 0).toString();
						String barcode = parentTable.getValueAt(selectedRow, 1).toString();
						String libraryCardId = parentTable.getValueAt(selectedRow, 14).toString();
						user.setUser_name(user_name);
						user.setUser_password(user_password);
						user.setUser_limits(0);
						// �������߶��󣬼������ݽ��б���
						Reader r = new Reader(reader_id, barcode,
								academic_num_string, name_string, sex_string,
								birthday_string, idCard_string, phone_string,
								email_string, descr_string, user_id,
								classify_num, libraryCardId);

						// ���÷�����������
						try {
							usersService.updateUsers(user);
							readerService.updateReader(r);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// �����ʾ��Ϣ��������ҳ�棬ˢ��.�����
						JOptionPane.showMessageDialog(null, "ͼ����Ϣ����ɹ�����¼�˺�����Ϊ"+"�˺ţ�"+user_name+"����:"+user_password);
						this.setVisible(false);
						parentPanel.refreshTablePanel();
					} else {
						// ���ˣ�����ع����Ա�֤��������ִ�У��˴���û�����θ��õ�ʵ��
					}
				}
			}
		} else if (e.getSource() == reset) {
			// ����������Ϣ
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
	 * �������ݻ��Է���
	 */
	public void echoData() {
		// ���ݸ������ѡ�е��л�ȡ���ߵ�������Ϣ��
		String read_id = parentTable.getValueAt(selectedRow, 0).toString();
		// ���ݶ���id��ȡ���ߵ�������Ϣ
		Reader r = null;
		try {
			r = readerService.getReaderById(read_id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (r != null) {
			String academic_num_string = r.getAcademic_num();
			String name_string = r.getReader_name();
			String birthday_string = r.getReader_birthday().substring(0, 10);
			String idCard_string = r.getReader_idCard();
			String phone_string = r.getReader_phone();
			String email_string = r.getReader_email();
			String sex_string = r.getReader_sex();
			String descr_string = r.getReader_descr();
			int classify_num_int = r.getClassify_num();
			// ���ݻ���
			academic_num.setText(academic_num_string);
			name.setText(name_string);
			birthday.setText(birthday_string);
			idCard.setText(idCard_string);
			phone.setText(phone_string);
			email.setText(email_string);
			descr.setText(descr_string);
			if (sex_string.equals("��")) {
				nan.setSelected(true);
			} else if (sex_string.equals("Ů")) {
				nv.setSelected(true);
			}

			// ����װ�ط�����Ϣ
			classify.removeAllItems();
			List<ReaderClassify> rc_list = null;
			try {
				rc_list = readerClassifyService.findAllReaderClassify();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < rc_list.size(); i++) {
				int sign = 0;
				int rc_num = rc_list.get(i).getClassify_num();
				String rc_name = rc_list.get(i).getClassify_name();
				Item item = new Item(rc_num + "", rc_name);
				classify.addItem(item);
				if (classify_num_int == rc_num) {
					sign = i;
					classify.setSelectedIndex(sign);
				}
			}
		}
	}
}