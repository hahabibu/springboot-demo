package com.guigu.library.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.model.LibraryCard;
import com.guigu.library.model.Reader;
import com.guigu.library.model.ReaderClassify;
import com.guigu.library.model.Users;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.Chooser;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.ImagePanel;
import com.guigu.library.utils.Item;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class PersonalAccountJPanel implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, centerPanel, showPanel,
			imagePanel, cardPanel, infoPanel, downPanel, buttonPanel;

	// ����imagePanel���ʹ�õ��ı�ǩ����ť
	JLabel label_image, label_change;
	ImageIcon icon_image;
	JTextField image_field;

	// ���������Ϣ���ʹ�õ��ı�ǩ
	JLabel label_academic_num, label_name, label_sex, label_birthday,
			label_idCard, label_phone, label_email, label_descr,
			label_classify;
	JTextField academic_num, name, birthday, idCard, phone, email;
	ButtonGroup sex;
	JRadioButton nan, nv;
	JTextArea descr;
	JComboBox classify;
	Chooser birthday_chooser;

	// ������߽���֤�����Ϣ���ʹ�õ��ı�ǩ
	JLabel label_id, label_num, label_handle_date, label_valid_till,
			label_loss_state, label_disable_state;
	JTextField id, num, handle_date, valid_till, loss_state, disable_state;

	JButton save, reset;

	JLabel tool_loss;

	// �����������ı���(ͨ�������鱾��������ʾ��Ӧ���鱾��Ϣ)
	JTextField keyword;

	// ������Ӧ��service
	ReaderService readerService = new ReaderServiceImpl();
	BooksService booksService = new BooksServiceImpl();
	UsersService usersService = new UsersServiceImpl();
	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();
	LibraryCardService libraryCardService = new LibraryCardServiceImpl();

	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();
	StorageAreaService storageAreaService = new StorageAreaServiceImpl();

	Users user;
	// ���嵱ǰʹ�ø��˺ŵĶ���
	Reader r;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public PersonalAccountJPanel(Users user) {
		this.user = user;
		initBackgroundPanel();
	}

	/**
	 * ��ʼ���������
	 */
	public void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵�����������
		initCenterPanel();// ��ʼ���м��������ʾ���
		initDownPanel();// ��ʼ���·��İ�ť���
	}

	/**
	 * ��ʼ�������Ĳ˵���
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		initToolPanel();
	}

	/**
	 * ��ʼ�����������
	 */
	public void initToolPanel() {

		toolPanel = new JPanel(new BorderLayout());
		// �����ɾ�Ĺ���
		Icon loss_icon = new ImageIcon("icons/toolImage/loss.png");
		tool_loss = new JLabel(loss_icon);
		tool_loss.setToolTipText("����֤��ʧ");
		tool_loss.addMouseListener(this);

		// ����ʼ����ɵĹ��߼��ص������������
		JPanel west = new JPanel();
		west.add(tool_loss);

		toolPanel.add(west, BorderLayout.WEST);

		topPanel.add(toolPanel, BorderLayout.SOUTH);

		// �����������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ���м��������ʾ���
	 */
	private void initCenterPanel() {
		centerPanel = new JPanel(new BorderLayout());

		initShowPanel();
		initInfoPanel();

		backgroundPanel.add(centerPanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	private void initShowPanel() {
		showPanel = new JPanel(new BorderLayout());
		initImagePanel();
		initCardPanel();
		centerPanel.add(showPanel, BorderLayout.WEST);
		backgroundPanel.validate();
	}

	/**
	 * ��ʼ��ͼ�����
	 */
	private void initImagePanel() {
		imagePanel = new JPanel(new BorderLayout());

		JPanel jp = new JPanel();
		image_field = new JTextField(25);
		image_field.setForeground(MyColor.TipColor);
		image_field.setFont(MyFont.TipFont);
		image_field.setEditable(false);
		label_change = new JLabel("����ͼƬ");
		label_change.addMouseListener(this);

		// ����ͷ����Ϣ���˴�����Ϊ��̬ͼƬ���������
		icon_image = new ImageIcon("icons/toolImage/static_icon.png");
		label_image = new JLabel(icon_image);

		jp.add(image_field);
		jp.add(label_change);

		imagePanel.add(label_image, BorderLayout.CENTER);
		imagePanel.add(jp, BorderLayout.SOUTH);
		showPanel.add(imagePanel, BorderLayout.CENTER);
	}

	/**
	 * ��ʼ������֤��Ϣ���
	 */
	private void initCardPanel() {
		cardPanel = new JPanel();
		// ���ؽ���֤��Ϣ
		JPanel jp1 = new JPanel();
		label_id = new JLabel("����֤id");
		id = new JTextField(15);
		id.setFont(MyFont.JTextFieldFont);
		id.setForeground(MyColor.JTextFieldColor);
		id.setEditable(false);
		label_num = new JLabel("���ı��");
		num = new JTextField(15);
		num.setFont(MyFont.JTextFieldFont);
		num.setForeground(MyColor.JTextFieldColor);
		num.setEditable(false);
		jp1.add(label_id);
		jp1.add(id);
		jp1.add(label_num);
		jp1.add(num);

		JPanel jp2 = new JPanel();
		label_handle_date = new JLabel("��������");
		handle_date = new JTextField(15);
		handle_date.setFont(MyFont.JTextFieldFont);
		handle_date.setForeground(MyColor.JTextFieldColor);
		handle_date.setEditable(false);

		label_valid_till = new JLabel("��Ч����");
		valid_till = new JTextField(15);
		valid_till.setFont(MyFont.JTextFieldFont);
		valid_till.setForeground(MyColor.JTextFieldColor);
		valid_till.setEditable(false);
		jp2.add(label_handle_date);
		jp2.add(handle_date);
		jp2.add(label_valid_till);
		jp2.add(valid_till);

		JPanel jp3 = new JPanel();
		label_loss_state = new JLabel("��ʧ״̬");
		loss_state = new JTextField(15);
		loss_state.setFont(MyFont.JTextFieldFont);
		loss_state.setForeground(MyColor.JTextFieldColor);
		loss_state.setEditable(false);

		label_disable_state = new JLabel("����״̬");
		disable_state = new JTextField(15);
		disable_state.setFont(MyFont.JTextFieldFont);
		disable_state.setForeground(MyColor.JTextFieldColor);
		disable_state.setEditable(false);
		jp3.add(label_loss_state);
		jp3.add(loss_state);
		jp3.add(label_disable_state);
		jp3.add(disable_state);

		// ���ݻ���
		this.echoCardData();
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

		cardPanel.add(ver);
		showPanel.add(cardPanel, BorderLayout.SOUTH);
	}

	/**
	 * ��ʼ����Ϣ���
	 */
	private void initInfoPanel() {
		infoPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_academic_num = new JLabel("ѧ�����");
		academic_num = new JTextField(25);
		academic_num.setEditable(false);
		academic_num.setFont(MyFont.JTextFieldFont);
		academic_num.setForeground(MyColor.JTextFieldColor);
		jp1.add(label_academic_num);
		jp1.add(academic_num);

		JPanel jp2 = new JPanel();
		label_name = new JLabel("��������");
		name = new JTextField(25);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		jp2.add(label_name);
		jp2.add(name);

		JPanel jp3 = new JPanel();
		label_sex = new JLabel("�����Ա�");
		sex = new ButtonGroup();
		nan = new JRadioButton("��");
		nv = new JRadioButton("Ů");
		sex.add(nan);
		sex.add(nv);
		jp3.add(label_sex);
		jp3.add(Box.createHorizontalStrut(50));
		jp3.add(nan);
		jp3.add(Box.createHorizontalStrut(50));
		jp3.add(nv);

		JPanel jp4 = new JPanel();
		label_birthday = new JLabel("��������");
		birthday = new JTextField(25);
		birthday_chooser = Chooser.getInstance();
		birthday_chooser.register(birthday);
		birthday.setFont(MyFont.JTextFieldFont);
		birthday.setForeground(MyColor.JTextFieldColor);
		jp4.add(label_birthday);
		jp4.add(birthday);

		JPanel jp5 = new JPanel();
		label_idCard = new JLabel("���֤��");
		idCard = new JTextField(25);
		idCard.setFont(MyFont.JTextFieldFont);
		idCard.setForeground(MyColor.JTextFieldColor);
		jp5.add(label_idCard);
		jp5.add(idCard);

		JPanel jp6 = new JPanel();
		label_phone = new JLabel("��ϵ��ʽ");
		phone = new JTextField(25);
		phone.setFont(MyFont.JTextFieldFont);
		phone.setForeground(MyColor.JTextFieldColor);
		jp6.add(label_phone);
		jp6.add(phone);

		JPanel jp7 = new JPanel();
		label_email = new JLabel("��������");
		email = new JTextField(25);
		email.setFont(MyFont.JTextFieldFont);
		email.setForeground(MyColor.JTextFieldColor);
		jp7.add(label_email);
		jp7.add(email);

		JPanel jp8 = new JPanel();
		label_classify = new JLabel("��������");
		classify = new JComboBox();
		classify.setPreferredSize(new Dimension(280, 30));
		classify.setFont(MyFont.JTextFieldFont);
		classify.setForeground(MyColor.JTextFieldColor);
		classify.setEnabled(false);
		jp8.add(label_classify);
		jp8.add(classify);

		JPanel jp9 = new JPanel();
		label_descr = new JLabel("���߼���");
		descr = new JTextArea(5, 25);
		descr.setFont(MyFont.JTextFieldFont);
		descr.setForeground(MyColor.JTextFieldColor);
		descr.setLineWrap(true);
		jp9.add(label_descr);
		jp9.add(descr);

		// ���ݻ���
		this.echoInfoData();
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
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp6);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp7);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp8);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp9);

		infoPanel.add(ver);
		centerPanel.add(infoPanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	/**
	 * ��ʼ���·����������
	 */
	private void initDownPanel() {
		downPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		// ��ʼ����ť���
		initButtonPanel();
		backgroundPanel.add(downPanel, BorderLayout.SOUTH);
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
		buttonPanel.add(Box.createHorizontalStrut(30));
		buttonPanel.add(reset);
		downPanel.add(buttonPanel);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == label_change) {
			// ����ͼƬ·��
			// �����ļ�ѡ���
			JFileChooser chooser = new JFileChooser();
			// ��׺��������
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"����ļ�(*.png)", "png");
			chooser.setFileFilter(filter);
			// ����ķ�����������ֱ�����û����±��水ť�ҡ��ļ������ı���Ϊ�ա����û�����ȡ����ť��
			int option = chooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) { // �����û�ѡ���˱���
				File file = chooser.getSelectedFile();
				String fname = chooser.getName(file); // ���ļ���������л�ȡ�ļ���
				// �����û���д���ļ������������ƶ��ĺ�׺������ô���Ǹ������Ϻ�׺
				if (fname.indexOf(".png") == -1) {
					file = new File(chooser.getCurrentDirectory(), fname
							+ ".png");
				}
				icon_image = new ImageIcon(file.getAbsolutePath());
				image_field.setText(file.getAbsolutePath());
				// ����ͼƬ��С���ǩ��С����Ӧ
				int width = 200,height = 200;	//����ͼƬ��JLable�Ŀ�Ⱥ͸߶�
				/**
				 * ����ͼƬ��С���ǩ���õĴ�С����Ӧ
				 * �õ���ͼ��� Image��icon_image.getImage()����
				 * �ڴ˻����ϴ����������Ű汾�����Ű汾�Ŀ�ȣ��߶���JLbleһ��
				 * ��getScaledInstance(width, height,Image.SCALE_DEFAULT )��
				 * ����ͼ�������Ϊ�õ������Ű汾��image.setImage��
				*/
				icon_image.setImage(icon_image.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
				//����������Ĵ���������
				// ImageIcon image = new ImageIcon(String filename);
				// Image img = image.getImage();
				// img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
				// image.setImage(img);
				label_image.setIcon(icon_image);
				label_image.setSize(width, height);
				backgroundPanel.validate();
			}

		} else if (e.getSource() == tool_loss) {
			try {
				if (r != null) {
					// ����֤��ʧ
					// ���ݽ���֤id��ȡ��ϸ�Ľ���֤��Ϣ
					LibraryCard lc = null;
					lc = libraryCardService.getLibraryCardById(r.getCard_id());
					int loss_state_int = lc.getLoss_state();
					int disable_state_int = lc.getDisable_state();
					if (loss_state_int == 0) {
						int choose = JOptionPane.showConfirmDialog(null,
								"ȷ�Ϲ�ʧ�ý���֤��");
						if (choose == 0) {
							// ��ʧ��������,ˢ���������
							lc.setLoss_state(1);
							lc.setDisable_state(1);
							libraryCardService.updateLibraryCard(lc);
							JOptionPane.showMessageDialog(null,
									"��ǰ����֤�ѹ�ʧ�����޷�ʹ�ã�");
						}
					} else if (loss_state_int == 1) {
						JOptionPane.showMessageDialog(null,
								"��ǰ����֤�ѹ�ʧ�������ظ�����������ȡ���뵽����Ա��������ز�����");
					}
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == save) {
			// ���ݱ���
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
						academic_num_string = RandomGeneration
								.getRandom8numSeq();
					} else {
						JOptionPane.showMessageDialog(null,
								"����ͨ�ο����֮��Ķ���ѧ����Ų���Ϊ��");
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
							String academic_num_string_old = r
									.getAcademic_num();
							String user_id = r.getUser_id();
							// �����˺�id��ȡ�˺���Ϣ
							Users user = null;
							try {
								user = usersService.getUsersById(user_id);
							} catch (SQLException e2) {
								e2.printStackTrace();
							}

							String user_name = user.getUser_name();
							String user_password = user.getUser_password();
							if (!academic_num_string_old
									.equals(academic_num_string)) {
								// �����˺��û���Ϣ����ʼ��Ϊ�û���Ϊѧ����ţ�����Ϊ000000��
								// ����ѧ�������Ϊ��Ӧ�˻����û�������ʼ����Ĭ��Ϊ000000,��ʼĬ��Ϊ����״̬
								user_name = academic_num_string;
								user_password = "000000";
							}
							// ����id�������롢����֤��Ϣ����
							String reader_id = r.getReader_id();
							String barcode = r.getBarcode();
							String libraryCardId = r.getCard_id();
							user.setUser_name(user_name);
							user.setUser_password(user_password);
							user.setUser_limits(0);
							// �������߶��󣬼������ݽ��б���
							Reader r = new Reader(reader_id, barcode,
									academic_num_string, name_string,
									sex_string, birthday_string, idCard_string,
									phone_string, email_string, descr_string,
									user_id, classify_num, libraryCardId);

							// ���÷�����������
							try {
								usersService.updateUsers(user);
								readerService.updateReader(r);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							// �����ʾ��Ϣ��������ҳ�棬ˢ��.�����
							JOptionPane.showMessageDialog(null,
									"ͼ����Ϣ����ɹ�����¼�˺�����Ϊ" + "�˺ţ�" + user_name
											+ "����:" + user_password);
							// ˢ�µ�ǰ�������
							this.refreshBackgroundPanel();
						} else {
							// ���ˣ�����ع����Ա�֤��������ִ�У��˴���û�����θ��õ�ʵ��
						}
					}
				}
			}
		} else if (e.getSource() == reset) {
			// ���ݻ���
			this.echoInfoData();
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
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("������|")) {
				keyword.setText("");
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
			}
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setText("������|");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}

	// ˢ�������������
	public void refreshBackgroundPanel() {
		backgroundPanel.removeAll();
		initTopPanel();
		initCenterPanel();
		initDownPanel();
		backgroundPanel.validate();
	}

	// ���Խ���֤��Ϣ
	public void echoCardData() {
		// ���ݵ�ǰ�ĵ�¼���˺�id��ȡ���������Ϣ
		try {
			r = readerService.getReaderByUserId(this.user.getUser_id());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (r != null) {
			// ���ݸ�����е�ǰָ�����л�ȡ���ߵ�������Ϣ��
			String libraryCard_id_string = r.getCard_id();
			// ���ݽ���֤id��ȡ��ϸ�Ľ���֤��Ϣ
			LibraryCard lc = null;
			try {
				lc = libraryCardService
						.getLibraryCardById(libraryCard_id_string);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// ���ݻ���
			id.setText(lc.getCard_id());
			id.setToolTipText(lc.getCard_id());
			num.setText(lc.getCard_num());
			num.setToolTipText(lc.getCard_num());
			handle_date.setText(lc.getHandle_date().substring(0, 10));
			handle_date.setToolTipText(lc.getHandle_date().substring(0, 10));
			valid_till.setText(lc.getValid_till().substring(0, 10));
			valid_till.setToolTipText(lc.getValid_till().substring(0, 10));
			int loss_state_int = lc.getLoss_state();
			if (loss_state_int == 0) {
				loss_state.setText("����ʹ��");
				loss_state.setToolTipText("����ʹ��");
			} else if (loss_state_int == 1) {
				loss_state.setText("��ʧ״̬");
				loss_state.setToolTipText("��ʧ״̬");
			}
			int disable_state_int = lc.getDisable_state();
			if (disable_state_int == 0) {
				disable_state.setText("����ʹ��");
				disable_state.setToolTipText("����ʹ��");
			} else if (disable_state_int == 1) {
				disable_state.setText("����״̬");
				disable_state.setToolTipText("����״̬");
			}
		}
	}

	// ���ݵ�ǰ����Ķ�����������Ϣ���Զ���������Ϣ
	public void echoInfoData() {
		// ���ݵ�ǰ�ĵ�¼���˺�id��ȡ���������Ϣ
		try {
			r = readerService.getReaderByUserId(this.user.getUser_id());
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
