package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.ReaderArchivesManagerJPanel;
import com.guigu.library.model.LibraryCard;
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

/**
 * ��Ӷ�����Ϣ ����idΪ�Զ����ɵ�32char���͵��ַ����� ������Ϊ�Զ�������ɵ�10λΨһ��int���͵�����
 * ѧ�������Ϊָ�����룬�������ͨ�ο����������룬��ϵͳָ���������8λint���͵����� ������Ϣ���ֶ�¼��
 * ��¼�������Ϣ��ʱ����Ӧ��Ϊ�䴴������֤���¼ͼ�����ϵͳ���˺���Ϣ
 */
public class AddReaderJFrame extends JFrame implements FocusListener,
		MouseListener {

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

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String current_date = sdf.format(new Date());

	JButton save, reset;
	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ����service
	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();
	ReaderService readerService = new ReaderServiceImpl();
	UsersService usersService = new UsersServiceImpl();
	LibraryCardService libraryCardService = new LibraryCardServiceImpl();

	// ���常����
	ReaderArchivesManagerJPanel parentPanel;

	// ͨ�����췽����ʼ������
	public AddReaderJFrame(ReaderArchivesManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("¼�������Ϣ");
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
		JLabel title = new JLabel("¼�������Ϣ");
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
		academic_num.setFont(MyFont.TipFont);
		academic_num.setForeground(MyColor.TipColor);
		academic_num.setText("������-��ͨ�οͿ�ѡ��");
		academic_num.addFocusListener(this);

		label_name = new JLabel("��������");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("������");
		name.addFocusListener(this);
		jp1.add(label_academic_num);
		jp1.add(academic_num);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_sex = new JLabel("�����Ա�");
		sex = new ButtonGroup();
		nan = new JRadioButton("��");
		nan.setSelected(true);
		nv = new JRadioButton("Ů");
		sex.add(nan);
		sex.add(nv);

		label_birthday = new JLabel("��������");
		birthday = new JTextField(15);
		birthday.setText(current_date);
		birthday_chooser = Chooser.getInstance();
		birthday_chooser.register(birthday);
		jp2.add(label_sex);
		jp2.add(nan);
		jp2.add(nv);
		jp2.add(label_birthday);
		jp2.add(birthday);

		JPanel jp3 = new JPanel();
		label_idCard = new JLabel("���֤��");
		idCard = new JTextField(15);
		idCard.setFont(MyFont.TipFont);
		idCard.setForeground(MyColor.TipColor);
		idCard.setText("������");
		idCard.addFocusListener(this);

		label_phone = new JLabel("��ϵ��ʽ");
		phone = new JTextField(15);
		phone.setFont(MyFont.TipFont);
		phone.setForeground(MyColor.TipColor);
		phone.setText("������");
		phone.addFocusListener(this);
		jp3.add(label_idCard);
		jp3.add(idCard);
		jp3.add(label_phone);
		jp3.add(phone);

		JPanel jp4 = new JPanel();
		label_email = new JLabel("��������");
		email = new JTextField(15);
		email.setFont(MyFont.TipFont);
		email.setForeground(MyColor.TipColor);
		email.setText("������");
		email.addFocusListener(this);

		label_classify = new JLabel("��������");
		classify = new JComboBox();
		classify.setPreferredSize(new Dimension(175, 30));
		// װ�ط�����Ϣ
		List<ReaderClassify> rc_list = null;
		try {
			rc_list = readerClassifyService.findAllReaderClassify();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (ReaderClassify rc : rc_list) {
			int rc_num = rc.getClassify_num();
			String rc_name = rc.getClassify_name();
			Item item = new Item(rc_num + "", rc_name);
			classify.addItem(item);
		}
		jp4.add(label_email);
		jp4.add(email);
		jp4.add(label_classify);
		jp4.add(classify);

		JPanel jp5 = new JPanel();
		label_descr = new JLabel("���߼���");
		descr = new JTextArea(5, 30);
		descr.setFont(MyFont.TipFont);
		descr.setForeground(MyColor.TipColor);
		descr.setBackground(Color.white);
		descr.setText("���߼�������ѡ��");
		descr.setLineWrap(true);
		descr.addFocusListener(this);
		jp5.add(label_descr);
		jp5.add(descr);

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
			if (academic_num_string.equals("������-��ͨ�οͿ�ѡ��")) {
				if (classify_num == 0) {
					// �������ͨ�οͣ����Զ��������8λint���͵����У�������ʾ������Ϣ
					academic_num_string = RandomGeneration.getRandom8numSeq();
				} else {
					JOptionPane.showMessageDialog(null, "����ͨ�ο����֮��Ķ���ѧ����Ų���Ϊ��");
				}
			}
			if (name_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "������������Ϊ�գ�");
			} else if (birthday_string.equals("")) {
				JOptionPane.showMessageDialog(null, "�������ղ���Ϊ�գ�");
			} else if (idCard_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "���֤�Ų���Ϊ�գ�");
			} else if (phone_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "��ϵ��ʽ����Ϊ�գ�");
			} else if (email_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "�������䲻��Ϊ�գ�");
			} else {
				// �ṩĬ��ֵ Ҫע���߼��ж�
				if (descr_string.equals("���߼�������ѡ��")) {
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
							"ȷ�ϱ��������Ϣ��");
					if (choose == 0) {
						// ������ɽ���֤id��������ָ���Ĺ������ɽ���֤���
						String libraryCardId = RandomGeneration
								.getRandom32charSeq();
						String libraryCardNum = RandomGeneration
								.getLibraryCardNum(classify_num);
						LibraryCard lc = new LibraryCard();
						lc.setCard_id(libraryCardId);
						lc.setCard_num(libraryCardNum);
						lc.setHandle_date(current_date);
						Calendar c = Calendar.getInstance();
						c.setTime(new Date());
						c.add(Calendar.YEAR, 1);
						String valid_till_date = sdf.format(c.getTime());
						lc.setValid_till(valid_till_date);
						// ����ѧ�������Ϊ��Ӧ�˻����û�������ʼ����Ĭ��Ϊ000000,��ʼĬ��Ϊ����״̬
						String user_id = RandomGeneration.getRandom32charSeq();
						String user_name = academic_num_string;
						String user_password = "000000";
						Users user = new Users(user_id, user_name,
								user_password, 0);

						// ���ɶ���id��������
						String reader_id = RandomGeneration
								.getRandom32charSeq();
						String barcode = RandomGeneration.getRandom10numSeq();

						// �������߶��󣬼������ݽ��б���
						Reader r = new Reader(reader_id, barcode,
								academic_num_string, name_string, sex_string,
								birthday_string, idCard_string, phone_string,
								email_string, descr_string, user_id,
								classify_num, libraryCardId);

						// ���÷�����������
						try {
							libraryCardService.addLibraryCard(lc);
							usersService.addUsers(user);
							readerService.addReader(r);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// �����ʾ��Ϣ��������ҳ�棬ˢ��.�����
						JOptionPane.showMessageDialog(null, "������Ϣ����ɹ���");
						this.setVisible(false);
						parentPanel.refreshTablePanel();
					} else {
						// ���ˣ�����ع����Ա�֤��������ִ�У��˴���û�����θ��õ�ʵ��
					}
				}
			}
		} else if (e.getSource() == reset) {
			// ����������Ϣ
			academic_num.setFont(MyFont.TipFont);
			academic_num.setForeground(MyColor.TipColor);
			academic_num.setText("������-��ͨ�οͿ�ѡ��");
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("������");
			idCard.setFont(MyFont.TipFont);
			idCard.setForeground(MyColor.TipColor);
			idCard.setText("������");
			phone.setFont(MyFont.TipFont);
			phone.setForeground(MyColor.TipColor);
			phone.setText("������");
			email.setFont(MyFont.TipFont);
			email.setForeground(MyColor.TipColor);
			email.setText("������");
			descr.setFont(MyFont.TipFont);
			descr.setForeground(MyColor.TipColor);
			descr.setText("���߼�������ѡ��");

			birthday.setText(current_date);
			nan.setSelected(true);
			classify.setSelectedIndex(0);
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
	 * ��ȡ�����¼�
	 */

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == academic_num) {
			if (academic_num.getText().equals("������-��ͨ�οͿ�ѡ��")) {
				academic_num.setFont(MyFont.JTextFieldFont);
				academic_num.setForeground(MyColor.JTextFieldColor);
				academic_num.setText("");
			}
		} else if (e.getSource() == name) {
			if (name.getText().equals("������")) {
				name.setFont(MyFont.JTextFieldFont);
				name.setForeground(MyColor.JTextFieldColor);
				name.setText("");
			}
		} else if (e.getSource() == idCard) {
			if (idCard.getText().equals("������")) {
				idCard.setFont(MyFont.JTextFieldFont);
				idCard.setForeground(MyColor.JTextFieldColor);
				idCard.setText("");
			}
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("������")) {
				phone.setFont(MyFont.JTextFieldFont);
				phone.setForeground(MyColor.JTextFieldColor);
				phone.setText("");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("������")) {
				email.setFont(MyFont.JTextFieldFont);
				email.setForeground(MyColor.JTextFieldColor);
				email.setText("");
			}
		} else if (e.getSource() == descr) {
			if (descr.getText().equals("���߼�������ѡ��")) {
				descr.setFont(MyFont.JTextFieldFont);
				descr.setForeground(MyColor.JTextFieldColor);
				descr.setText("");
			}
		}
	}

	/**
	 * ʧȥ�����¼�
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == academic_num) {
			if (academic_num.getText().equals("")) {
				academic_num.setFont(MyFont.TipFont);
				academic_num.setForeground(MyColor.TipColor);
				academic_num.setText("������-��ͨ�οͿ�ѡ��");
			}
		} else if (e.getSource() == name) {
			if (name.getText().equals("")) {
				name.setFont(MyFont.TipFont);
				name.setForeground(MyColor.TipColor);
				name.setText("������");
			}
		} else if (e.getSource() == idCard) {
			if (idCard.getText().equals("")) {
				idCard.setFont(MyFont.TipFont);
				idCard.setForeground(MyColor.TipColor);
				idCard.setText("������");
			}
		} else if (e.getSource() == phone) {
			if (phone.getText().equals("")) {
				phone.setFont(MyFont.TipFont);
				phone.setForeground(MyColor.TipColor);
				phone.setText("������");
			}
		} else if (e.getSource() == email) {
			if (email.getText().equals("")) {
				email.setFont(MyFont.TipFont);
				email.setForeground(MyColor.TipColor);
				email.setText("������");
			}
		} else if (e.getSource() == descr) {
			if (descr.getText().equals("")) {
				descr.setFont(MyFont.TipFont);
				descr.setForeground(MyColor.TipColor);
				descr.setText("���߼�������ѡ��");
			}
		}
	}
}