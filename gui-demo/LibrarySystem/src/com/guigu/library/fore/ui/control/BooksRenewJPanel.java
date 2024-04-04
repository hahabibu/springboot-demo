package com.guigu.library.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
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
import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;
import com.guigu.library.model.StorageArea;
import com.guigu.library.model.Users;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.BorrowingService;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.RenewService;
import com.guigu.library.service.ReturningService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.BorrowingServiceImpl;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.RenewServiceImpl;
import com.guigu.library.service.impl.ReturningServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class BooksRenewJPanel implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, centerPanel, bookInfoPanel,
			downPanel, buttonPanel;

	// ����BookInfoPanel���鼮��Ϣ��ʹ�õ��ı�ǩ���ı���
	JLabel label_isbn, label_book_name, label_book_classify, label_area,
			label_author, label_translator, label_publish_date, label_press,
			label_price;
	JTextField isbn, book_name, book_classify, area, author, translator,
			publish_date, press, price;

	// ����������ʹ�õ��ı�ǩ
	JLabel tool_renew, tool_return, search_barcode;

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
	RenewService renewService = new RenewServiceImpl();
	ReturningService returningService = new ReturningServiceImpl();

	Users user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public BooksRenewJPanel(Users user) {
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

		Icon icon_renew = new ImageIcon("icons/toolImage/renew.png");
		tool_renew = new JLabel(icon_renew);
		tool_renew.setToolTipText("ͼ������");// ��������ƶ�ʱ����ʾ����
		tool_renew.addMouseListener(this);// ���������

		Icon icon_return = new ImageIcon("icons/toolImage/return.png");
		tool_return = new JLabel(icon_return);
		tool_return.setToolTipText("ͼ��黹");// ��������ƶ�ʱ����ʾ����
		tool_return.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		JPanel west = new JPanel();
		west.add(tool_renew);
		west.add(Box.createHorizontalStrut(20));
		west.add(tool_return);

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
		// ��ʼ���鼮��Ϣ��ʾ���
		initBookInfoPanel();
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);
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
		centerPanel.add(bookInfoPanel, BorderLayout.CENTER);
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
		// ��Ӱ�ť����
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
		if (e.getSource() == tool_renew) {
			try {
				if (keyword.getText().equals("������|")) {
					JOptionPane.showMessageDialog(null,
							"��Ǹ����ǰû�м�⵽Ҫ�������鼮�������³��ԣ�");
				} else {
					// ͼ�����裺�жϵ�ǰ�û�����֤�Ƿ�����ʹ�ã��Ƿ����Υ�ͼ�¼
					if (book == null) {
						JOptionPane.showMessageDialog(null,
								"��ǰû��Ҫ���ĵ��鼮Ŷ����ȷ�Ϻ����³��ԣ�");
					} else {
						// ���ݵ�ǰ��ͼ����Ϣ��ȡ��Ӧ�Ľ�����Ϣ
						Borrowing b = borrowingService
								.getBorrowingByBookId(book.getBook_id());
						// �жϵ�ǰ�Ƿ������Ӧ�ļ�¼
						if (b == null) {
							JOptionPane.showMessageDialog(null,
									"��ǰͼ����Ϣ�޽��ļ�¼����ȷ�Ϻ����ԣ�");
						} else {
							// �жϵ�ǰͼ���Ƿ��ѹ黹������黹��������
							if (b.getBorrow_state() == 0) {
								JOptionPane.showMessageDialog(null,
										"��ǰͼ���ѹ黹���޷�ִ�����������");
							} else {
								// �жϵ�ǰ���ߵĽ���֤�Ƿ��ڽ���״̬
								r = readerService.getReaderById(b
										.getReader_id());
								LibraryCard lc = libraryCardService
										.getLibraryCardById(r.getCard_id());
								if (lc.getDisable_state() == 1) {
									JOptionPane
											.showMessageDialog(null,
													"��Ǹ����ǰ����֤�ѱ�����(�����쳣״̬���޷������鼮�������)���뽫����鼮��ʱ�黹��������Ա�����д���");
								} else {
									// �жϵ�ǰʱ���Ƿ񳬳�����黹���ڣ������������Ϊ������Υ��״̬�����ܹ���������ǽ���ͼ��
									SimpleDateFormat sdf = new SimpleDateFormat(
											"yyyy-MM-dd");
									String current_date = sdf
											.format(new Date());
									if (current_date.compareTo(b
											.getSuggest_return_date()) > 0
											|| b.getViolation_state() == 1) {
										JOptionPane.showMessageDialog(null,
												"��ǰ����Υ�������뽫����鼮�黹�󵽹���Ա�����д���");
									} else {
										// ��������ִ������������������Ĭ�����ڵ�ǰʱ��Ļ����ϸ����û��ȼ����ָ��������������
										ReaderClassify rc = readerClassifyService
												.getReaderClassifyBynum(r
														.getClassify_num());
										if (rc != null) {
											int choose = JOptionPane
													.showConfirmDialog(null,
															"ȷ��������鼮��");
											if (choose == 0) {
												// ͼ�����裺�漰��������ı�ͼ���
												int time_limit_int = rc
														.getTime_limit();
												// ��������¼
												// ����id��������ɵ�32char���У����������Զ�������
												String renew_id = RandomGeneration
														.getRandom32charSeq();
												String renew_num = renewService
														.getRenewSeq();
												String book_id = book
														.getBook_id();
												String reader_id = r
														.getReader_id();
												Renew renew = new Renew(
														renew_id, renew_num,
														book_id, reader_id,
														current_date);
												// �޸���Ӧ�Ľ��Ľ���黹����
												Calendar c = Calendar
														.getInstance();
												c.setTime(new Date());
												c.add(Calendar.DATE,
														time_limit_int);
												String suggest_return_date = sdf
														.format(c.getTime());
												b.setSuggest_return_date(suggest_return_date);
												renewService.addRenew(renew);
												borrowingService
														.updateBorrowing(b);
												JOptionPane
														.showMessageDialog(
																null,
																"��ǰͼ������ɹ����黹���ڽ�ֹ��"
																		+ suggest_return_date);
											}
										}
									}
								}
							}
						}
					}
				}

			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == tool_return) {
			try {
				if (keyword.getText().equals("������|")) {
					JOptionPane.showMessageDialog(null,
							"��Ǹ����ǰû�м�⵽Ҫ�������鼮�������³��ԣ�");
				} else {
					if (book == null) {
						JOptionPane.showMessageDialog(null,
								"��ǰû��Ҫ���ĵ��鼮Ŷ����ȷ�Ϻ����³��ԣ�");
					} else {
						// ���ݵ�ǰ��ͼ����Ϣ��ȡ��Ӧ�Ľ�����Ϣ
						Borrowing b = borrowingService
								.getBorrowingByBookId(book.getBook_id());
						// �жϵ�ǰͼ���Ƿ��ѹ黹������黹��������
						if (b.getBorrow_state() == 0) {
							JOptionPane.showMessageDialog(null,
									"��ǰͼ���ѹ黹�������ظ����в�����");
						} else {
							int choose = JOptionPane.showConfirmDialog(null,
									"ȷ�Ϲ黹��ͼ�飿");
							if (choose == 0) {
								// �黹ͼ��
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								String current_date = sdf.format(new Date());
								if (current_date.compareTo(b
										.getSuggest_return_date()) > 0) {
									b.setViolation_state(1);
								}
								// �黹ͼ�飺���ͼ��黹��¼������ͼ�������Ϣ���漰�黹�����ı�ͼ���
								String returning_id = RandomGeneration
										.getRandom32charSeq();
								String returning_num = returningService
										.getReturningSeq();
								String book_id = book.getBook_id();
								String reader_id = r.getReader_id();
								Returning returning = new Returning(
										returning_id, returning_num, book_id,
										reader_id, current_date);
								// �޸Ľ��ı��еĽ��ı�ʶ
								b.setBorrow_state(0);
								// �޸Ķ�Ӧͼ��Ľ��ı�ʶ
								book.setBorrow_flag(0);
								// ִ�в���
								returningService.addReturning(returning);
								borrowingService.updateBorrowing(b);
								booksService.updateBooks(book);
								if (b.getViolation_state() == 0) {
									JOptionPane.showMessageDialog(null,
											"��ǰͼ��黹�ɹ�");
								} else if (b.getViolation_state() == 1) {
									JOptionPane.showMessageDialog(null,
											"��ǰͼ��黹�ɹ������ڳ��ڹ黹����");
								}
							}
						}
					}
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		} else if (e.getSource() == reset) {
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
