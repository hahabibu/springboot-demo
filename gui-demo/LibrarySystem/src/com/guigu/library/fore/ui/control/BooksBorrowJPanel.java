package com.guigu.library.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Books;
import com.guigu.library.model.Borrowing;
import com.guigu.library.model.LibraryCard;
import com.guigu.library.model.Reader;
import com.guigu.library.model.ReaderClassify;
import com.guigu.library.model.StorageArea;
import com.guigu.library.model.Users;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.BorrowingService;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.BorrowingServiceImpl;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class BooksBorrowJPanel implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, centerPanel, readerInfoPanel,
			bookInfoPanel, downPanel, buttonPanel;

	// ����ReaderInfoPanel��������Ϣ��ʹ�õ��ı�ǩ���ı���
	JLabel label_reader_barcode, label_academic_num, label_reader_name,
			label_account, label_reader_classify, label_card_num,
			label_available_num, label_maximum, label_time_limit;
	JTextField reader_barcode, academic_num, reader_name, account,
			reader_classify, card_num, available_num, maximum, time_limit;

	// ����BookInfoPanel���鼮��Ϣ��ʹ�õ��ı�ǩ���ı���
	JLabel label_isbn, label_book_name, label_book_classify, label_area,
			label_author, label_translator, label_publish_date, label_press,
			label_price;
	JTextField isbn, book_name, book_classify, area, author, translator,
			publish_date, press, price;

	// ����������ʹ�õ��ı�ǩ
	JLabel tool_borrow, search_barcode;

	// �����������ı���(ͨ�������鱾��������ʾ��Ӧ���鱾��Ϣ)
	JTextField keyword;

	// ���尴ť����
	JButton reset;

	// ������ߡ������鼮
	Reader r = null;
	Books book = null;

	// ������Ӧ��service
	ReaderService readerService = new ReaderServiceImpl();
	BooksService booksService = new BooksServiceImpl();
	UsersService usersService = new UsersServiceImpl();
	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();
	LibraryCardService libraryCardService = new LibraryCardServiceImpl();
	BorrowingService borrowingService = new BorrowingServiceImpl();
	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();
	StorageAreaService storageAreaService = new StorageAreaServiceImpl();

	Users user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public BooksBorrowJPanel(Users user) {
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
		Icon icon_borrow = new ImageIcon("icons/toolImage/borrow.png");
		tool_borrow = new JLabel(icon_borrow);
		tool_borrow.setToolTipText("ͼ�����");// ��������ƶ�ʱ����ʾ����
		tool_borrow.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		JPanel west = new JPanel();
		west.add(tool_borrow);

		JPanel east = new JPanel();
		search_barcode = new JLabel();
		Icon barcode_icon = new ImageIcon("icons/toolImage/barcode.png");
		search_barcode.setIcon(barcode_icon);
		keyword = new JTextField(20);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("������|");
		keyword.setPreferredSize(new Dimension(150, 30));
		keyword.addMouseListener(this);
		// �ı�������ʵʱ���ݼ�ػ�ȡ��Ʒ��Ϣ
		keyword.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				echoBookData();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				echoBookData();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				echoBookData();
			}
		});
		east.add(search_barcode);
		east.add(keyword);

		toolPanel.add(west, BorderLayout.WEST);
		toolPanel.add(east, BorderLayout.EAST);

		topPanel.add(toolPanel, BorderLayout.SOUTH);

		// �����������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ���м��������ʾ���
	 */
	private void initCenterPanel() {
		centerPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		// ��ʼ��������Ϣ��ʾ���
		initReaderInfoPanel();
		// ��ʼ���鼮��Ϣ��ʾ���
		initBookInfoPanel();
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * ��ʼ��������Ϣ��ʾ���
	 */
	private void initReaderInfoPanel() {
		readerInfoPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_reader_barcode = new JLabel("������  ");
		reader_barcode = new JTextField(25);
		reader_barcode.setText("���������������");
		reader_barcode.addMouseListener(this);
		reader_barcode.setFont(MyFont.TipFont);
		reader_barcode.setForeground(MyColor.TipColor);
		reader_barcode.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent e) {
						echoReaderData();
					}

					@Override
					public void insertUpdate(DocumentEvent e) {
						echoReaderData();
					}

					@Override
					public void changedUpdate(DocumentEvent e) {

					}
				});
		jp1.add(label_reader_barcode);
		jp1.add(reader_barcode);

		JPanel jp2 = new JPanel();
		label_academic_num = new JLabel("ѧ�����");
		academic_num = new JTextField(25);
		academic_num.setFont(MyFont.JTextFieldFont);
		academic_num.setForeground(MyColor.JTextFieldColor);
		academic_num.setEditable(false);
		jp2.add(label_academic_num);
		jp2.add(academic_num);

		JPanel jp3 = new JPanel();
		label_reader_name = new JLabel("��������");
		reader_name = new JTextField(25);
		reader_name.setFont(MyFont.JTextFieldFont);
		reader_name.setForeground(MyColor.JTextFieldColor);
		reader_name.setEditable(false);
		jp3.add(label_reader_name);
		jp3.add(reader_name);

		JPanel jp4 = new JPanel();
		label_account = new JLabel("�����˺�");
		account = new JTextField(25);
		account.setFont(MyFont.JTextFieldFont);
		account.setForeground(MyColor.JTextFieldColor);
		account.setEditable(false);
		jp4.add(label_account);
		jp4.add(account);

		JPanel jp5 = new JPanel();
		label_reader_classify = new JLabel("���߷���");
		reader_classify = new JTextField(25);
		reader_classify.setFont(MyFont.JTextFieldFont);
		reader_classify.setForeground(MyColor.JTextFieldColor);
		reader_classify.setEditable(false);
		jp5.add(label_reader_classify);
		jp5.add(reader_classify);

		JPanel jp6 = new JPanel();
		label_card_num = new JLabel("����֤��");
		card_num = new JTextField(25);
		card_num.setFont(MyFont.JTextFieldFont);
		card_num.setForeground(MyColor.JTextFieldColor);
		card_num.setEditable(false);
		jp6.add(label_card_num);
		jp6.add(card_num);

		JPanel jp7 = new JPanel();
		label_available_num = new JLabel("�ɽ�����");
		available_num = new JTextField(25);
		available_num.setFont(MyFont.JTextFieldFont);
		available_num.setForeground(MyColor.JTextFieldColor);
		available_num.setEditable(false);
		jp7.add(label_available_num);
		jp7.add(available_num);

		JPanel jp8 = new JPanel();
		label_maximum = new JLabel("������");
		maximum = new JTextField(25);
		maximum.setFont(MyFont.JTextFieldFont);
		maximum.setForeground(MyColor.JTextFieldColor);
		maximum.setEditable(false);
		jp8.add(label_maximum);
		jp8.add(maximum);

		JPanel jp9 = new JPanel();
		label_time_limit = new JLabel("��������");
		time_limit = new JTextField(25);
		time_limit.setFont(MyFont.JTextFieldFont);
		time_limit.setForeground(MyColor.JTextFieldColor);
		time_limit.setEditable(false);
		jp9.add(label_time_limit);
		jp9.add(time_limit);

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

		readerInfoPanel.add(ver);
		centerPanel.add(readerInfoPanel, BorderLayout.WEST);
		// ��������ص�������
		backgroundPanel.validate();
	}

	/**
	 * ��ʼ���鼮��Ϣ��ʾ���
	 */
	private void initBookInfoPanel() {
		bookInfoPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_isbn = new JLabel("ISBN    ");
		isbn = new JTextField(25);
		isbn.setFont(MyFont.JTextFieldFont);
		isbn.setForeground(MyColor.JTextFieldColor);
		isbn.setEditable(false);
		jp1.add(label_isbn);
		jp1.add(isbn);

		JPanel jp2 = new JPanel();
		label_book_name = new JLabel("ͼ������");
		book_name = new JTextField(25);
		book_name.setFont(MyFont.JTextFieldFont);
		book_name.setForeground(MyColor.JTextFieldColor);
		book_name.setEditable(false);
		jp2.add(label_book_name);
		jp2.add(book_name);

		JPanel jp3 = new JPanel();
		label_book_classify = new JLabel("��������");
		book_classify = new JTextField(25);
		book_classify.setFont(MyFont.JTextFieldFont);
		book_classify.setForeground(MyColor.JTextFieldColor);
		book_classify.setEditable(false);
		jp3.add(label_book_classify);
		jp3.add(book_classify);

		JPanel jp4 = new JPanel();
		label_area = new JLabel("�洢����");
		area = new JTextField(25);
		area.setFont(MyFont.JTextFieldFont);
		area.setForeground(MyColor.JTextFieldColor);
		area.setEditable(false);
		jp4.add(label_area);
		jp4.add(area);

		JPanel jp5 = new JPanel();
		label_author = new JLabel("ͼ������");
		author = new JTextField(25);
		author.setFont(MyFont.JTextFieldFont);
		author.setForeground(MyColor.JTextFieldColor);
		author.setEditable(false);
		jp5.add(label_author);
		jp5.add(author);

		JPanel jp6 = new JPanel();
		label_translator = new JLabel("ͼ������");
		translator = new JTextField(25);
		translator.setFont(MyFont.JTextFieldFont);
		translator.setForeground(MyColor.JTextFieldColor);
		translator.setEditable(false);
		jp6.add(label_translator);
		jp6.add(translator);

		JPanel jp7 = new JPanel();
		label_publish_date = new JLabel("��������");
		publish_date = new JTextField(25);
		publish_date.setFont(MyFont.JTextFieldFont);
		publish_date.setForeground(MyColor.JTextFieldColor);
		publish_date.setEditable(false);
		jp7.add(label_publish_date);
		jp7.add(publish_date);

		JPanel jp8 = new JPanel();
		label_press = new JLabel("������  ");
		press = new JTextField(25);
		press.setFont(MyFont.JTextFieldFont);
		press.setForeground(MyColor.JTextFieldColor);
		press.setEditable(false);
		jp8.add(label_press);
		jp8.add(press);

		JPanel jp9 = new JPanel();
		label_price = new JLabel("�����ۼ�");
		price = new JTextField(25);
		price.setFont(MyFont.JTextFieldFont);
		price.setForeground(MyColor.JTextFieldColor);
		price.setEditable(false);
		jp9.add(label_price);
		jp9.add(price);

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

		bookInfoPanel.add(ver);
		centerPanel.add(bookInfoPanel, BorderLayout.WEST);
		// ��������ص�������
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

		reset = new JButton("����");
		reset.setSize(75, 30);
		reset.addMouseListener(this);// ���������
		reset.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		// ����ʼ����ɵĹ��߼��ص������������
		buttonPanel.add(reset);
		// ���ս������������ص������˵�����������
		downPanel.add(buttonPanel, BorderLayout.EAST);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_borrow) {
			// ���ݵ�ǰ�������ݽ���ͼ����Ĳ���
			if (r == null) {
				JOptionPane.showMessageDialog(null, "��������Ӧ�Ķ���������...");
			} else if (book == null) {
				JOptionPane.showMessageDialog(null, "��ǰû��Ҫ���ĵ��鼮Ŷ����ȷ�Ϻ����³��ԣ�");
			} else {
				// �жϵ�ǰ���ߵĽ���֤�Ƿ��ڽ���״̬
				LibraryCard lc = null;
				try {
					lc = libraryCardService.getLibraryCardById(r.getCard_id());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (lc.getDisable_state() == 1) {
					JOptionPane.showMessageDialog(null,
							"��Ǹ����ǰ����֤�ѱ�����(�����쳣״̬���޷������鼮���Ĳ���)���뵽����Ա�����д���");
				} else {
					// �жϵ�ǰ�ɽ������Ƿ����0��ֱ�Ӵ��ı����л�ȡ��Ӧ���ݣ�
					String availableNum_string = available_num.getText();
					int availableNum_int = 0;
					if (!availableNum_string.equals("")) {
						availableNum_int = Integer.valueOf(availableNum_string);
					}
					if (availableNum_int > 0) {
						// ִ�н��Ĳ���
						/**
						 * ���Ĳ����� �жϵ�ǰͼ���Ƿ����ϼܡ��������ѱ�����
						 * ����Ϊ�������ʵ�ʵ�����£���δ�ϼܵ��鼮�������ѱ����ĵ��鼮һ����
						 * �����޹صĶ������޷�������ز����ģ���Ϊ�˱�֤ͼ����Ϣ����ȷ�ԣ��ڲ�ͬ��
						 * ���߲�����ʱ����Ҫ��ͼ����쳣������м��飬�������������ظ�������
						 */
						// ���ݵ�ǰҪ���ĵ�ͼ����Ϣ�����ж�
						int borrow_flag_int = book.getBorrow_flag();
						int put_on_flag_int = book.getPut_on_flag();
						if (borrow_flag_int == 1) {
							// �жϵ�ǰ��ͼ����Ϣ�Ƿ񱻽���
							JOptionPane.showMessageDialog(null,
									"�쳣��ʾ����Ǹ����ǰͼ���ѱ����ģ������ظ�������");
						} else if (put_on_flag_int == 0) {
							JOptionPane.showMessageDialog(null,
									"�쳣��ʾ����Ǹ���������ҵ�ͼ����δ�ϼܣ���ȷ�Ϻ���в�����");
						} else {
							// ������Ϣ��ͼ����Ϣ����������״̬�����������鼮���Ĳ���
							int choose = JOptionPane.showConfirmDialog(null,
									"ȷ�����ĸ��鼮��");
							if (choose == 0) {
								// ִ���鼮���Ĳ���
								// ����id��������ɵ�32char���͵����ݡ����ı���Ƕ�Ӧ������
								String borrowing_id = RandomGeneration
										.getRandom32charSeq();
								String borrowing_num = "0000000000";
								try {
									borrowing_num = borrowingService
											.getBorrowingSeq();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								String book_id = book.getBook_id();
								String reader_id = r.getReader_id();
								// ��ȡ��ǰϵͳʱ��
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								String borrowing_date = sdf.format(new Date());
								// ���ݵ�ǰ���ߵĽ��ĵȼ���ȡ���黹���ֹʱ��
								int time_limit_int = Integer.valueOf(time_limit
										.getText());
								Calendar c = Calendar.getInstance();
								c.setTime(new Date());
								c.add(Calendar.DATE, time_limit_int);
								String suggest_return_date = sdf.format(c
										.getTime());
								Borrowing b = new Borrowing(borrowing_id,
										borrowing_num, book_id, reader_id,
										borrowing_date, suggest_return_date, 1,
										0);
								try {
									borrowingService.addBorrowing(b);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								// �޸��鼮����״̬
								book.setBorrow_flag(1);
								try {
									booksService.updateBooks(book);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								JOptionPane
										.showMessageDialog(
												null,
												"ͼ����ĳɹ�����ǰͼ��黹��ֹ����Ϊ"
														+ suggest_return_date
														+ ",�뼰ʱ���ù黹����,�絽�ڲ��ܼ�ʱ�黹����Ӧ�������ö�");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "��Ǹ������ǰ��������Ϊ:"
								+ maximum.getText() + "-��ǰ�ɽ�����Ϊ:"
								+ availableNum_string + "�뽫����鼮�黹���ٽ��в����ɣ�");
					}
				}
			}

		} else if (e.getSource() == reset) {
			// ��Ӧ�ı����ÿ�
			reader_barcode.setText("���������������");
			reader_barcode.setForeground(MyColor.TipColor);
			reader_barcode.setFont(MyFont.TipFont);
			academic_num.setText("");
			reader_name.setText("");
			account.setText("");
			reader_classify.setText("");
			card_num.setText("");
			available_num.setText("");
			maximum.setText("");
			time_limit.setText("");
			// ��Ӧ�ı����ÿ�
			keyword.setText("������|");
			keyword.setForeground(MyColor.TipColor);
			keyword.setFont(MyFont.TipFont);
			isbn.setText("");
			book_name.setText("");
			book_classify.setText("");
			area.setText("");
			author.setText("");
			translator.setText("");
			publish_date.setText("");
			press.setText("");
			price.setText("");
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
		} else if (e.getSource() == reader_barcode) {
			if (reader_barcode.getText().equals("���������������")) {
				reader_barcode.setText("");
				reader_barcode.setFont(MyFont.JTextFieldFont);
				reader_barcode.setForeground(MyColor.JTextFieldColor);
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
		} else if (e.getSource() == reader_barcode) {
			if (reader_barcode.getText().equals("")) {
				reader_barcode.setText("���������������");
				reader_barcode.setFont(MyFont.TipFont);
				reader_barcode.setForeground(MyColor.TipColor);
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

	// ���ݵ�ǰ����Ķ�����������Ϣ���Զ���������Ϣ
	public void echoReaderData() {
		try {
			String reader_barcode_string = reader_barcode.getText();
			if (!reader_barcode_string.equals("���������������")
					&& DataValidation.isSignlessInteger(reader_barcode_string)) {
				r = readerService.getReaderByBarcode(reader_barcode_string);
				if (r != null) {
					// ����������Ϣ
					academic_num.setText(r.getAcademic_num());

					reader_name.setText(r.getReader_name());

					Users findUser = usersService.getUsersById(r.getUser_id());
					account.setText(findUser.getUser_name());

					ReaderClassify rc = readerClassifyService
							.getReaderClassifyBynum(r.getClassify_num());
					reader_classify.setText(rc.getClassify_name());

					LibraryCard lc = libraryCardService.getLibraryCardById(r
							.getCard_id());
					card_num.setText(lc.getCard_num());

					// ���ݵ�ǰ������Ϣͳ�Ƶ�ǰ���ĵ�����
					int borrow_num = borrowingService
							.getBorrowingCountByState();
					int available_num_int = rc.getMaximum() - borrow_num;
					available_num.setText(available_num_int + "");

					maximum.setText(rc.getMaximum() + "");

					time_limit.setText(rc.getTime_limit() + "");
				} else {
					// ��Ӧ�ı����ÿ�
					reader_barcode.setText("���������������");
					reader_barcode.setForeground(MyColor.TipColor);
					reader_barcode.setFont(MyFont.TipFont);
					academic_num.setText("");
					reader_name.setText("");
					account.setText("");
					reader_classify.setText("");
					card_num.setText("");
					available_num.setText("");
					maximum.setText("");
					time_limit.setText("");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ���ݵ�ǰ������鼮��������Ϣ�����鼮������Ϣ
	public void echoBookData() {
		try {
			String book_barcode_string = keyword.getText();
			if (!book_barcode_string.equals("������|")
					&& DataValidation.isSignlessInteger(book_barcode_string)) {
				book = booksService.getBookByBarcode(book_barcode_string);
				if (book != null) {
					// ����������Ϣ
					isbn.setText(book.getIsbn());

					book_name.setText(book.getBook_name());

					BookClassify bc = bookClassifyService
							.getBookClassifyByNum(book.getClassify_num());
					book_classify.setText(bc.getClassify_num() + "-"
							+ bc.getClassify_name());

					StorageArea sa = storageAreaService
							.getStorageAreaByNum(book.getArea_num());
					area.setText(sa.getArea_name());

					author.setText(book.getAuthor());

					translator.setText(book.getTranslator());

					publish_date.setText(book.getPublish_date()
							.substring(0, 10));

					press.setText(book.getPress());

					price.setText(book.getPrice() + "Ԫ");

				} else {
					// ��Ӧ�ı����ÿ�
					isbn.setText("");
					book_name.setText("");
					book_classify.setText("");
					area.setText("");
					author.setText("");
					translator.setText("");
					publish_date.setText("");
					press.setText("");
					price.setText("");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
