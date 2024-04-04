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
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.BooksArchivesManagerJPanel;
import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Books;
import com.guigu.library.model.StorageArea;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.utils.Chooser;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.Item;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

/**
 * ͼ��idΪ�Զ����ɵ�32char���͵����� ͼ�����������Զ����ɵ�10λint���͵ı�� ͼ�����������ɵ�ǰѡ�е�ͼ������Ž�����н���ƴ�ӣ��Զ�����
 * ������Ϣ����Ҫ�ֶ��������
 */
public class AddBooksJFrame extends JFrame implements FocusListener,
		MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_isbn, label_name, label_classify, label_area, label_author,
			label_translator, label_publish_date, label_press, label_price,
			label_format, label_entry_date, label_put_on_date,
			label_abstract_descr, label_proposal_reader;
	JTextField isbn, name, author, translator, press, price, publish_date,
			entry_date, put_on_date;
	JTextArea format, abstract_descr, proposal_reader;
	JComboBox classify, area;
	Chooser publish_date_chooser, entry_date_chooser, put_on_date_chooser;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String current_date = sdf.format(new Date());

	JButton save, reset;
	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ����һ����־flag���Ա�ʾ��ǰ�ĸ���ͼ��isbn���ҵ�ͼ����Ϣ�Ƿ���ڣ��������������������������
	int flag = 0;
	String callno = "";

	// ����service
	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();
	StorageAreaService storageAreaService = new StorageAreaServiceImpl();
	BooksService booksService = new BooksServiceImpl();

	// ���常���󡢵�ǰ��¼Ա�������ѡ����
	BooksArchivesManagerJPanel parentPanel;

	// ͨ�����췽����ʼ������
	public AddBooksJFrame(BooksArchivesManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("���ͼ����Ϣ");
		this.setSize(600, 600);
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
		JLabel title = new JLabel("���ͼ����Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_isbn = new JLabel("ISBN    ");
		isbn = new JTextField(15);
		isbn.setFont(MyFont.TipFont);
		isbn.setForeground(MyColor.TipColor);
		isbn.setText("������");
		isbn.addFocusListener(this);
		isbn.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// �����ı���ı仯
				echoDataByISBN();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// �����ı���ı仯
				echoDataByISBN();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}
		});

		label_name = new JLabel("ͼ������");
		name = new JTextField(15);
		name.setFont(MyFont.TipFont);
		name.setForeground(MyColor.TipColor);
		name.setText("������");
		name.addFocusListener(this);
		jp1.add(label_isbn);
		jp1.add(isbn);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_author = new JLabel("�������");
		author = new JTextField(15);
		author.setFont(MyFont.TipFont);
		author.setForeground(MyColor.TipColor);
		author.setText("������");
		author.addFocusListener(this);

		label_translator = new JLabel("�������");
		translator = new JTextField(15);
		translator.setFont(MyFont.TipFont);
		translator.setForeground(MyColor.TipColor);
		translator.setText("������");
		translator.addFocusListener(this);
		jp2.add(label_author);
		jp2.add(author);
		jp2.add(label_translator);
		jp2.add(translator);

		JPanel jp3 = new JPanel();
		label_press = new JLabel("������  ");
		press = new JTextField(15);
		press.setFont(MyFont.TipFont);
		press.setForeground(MyColor.TipColor);
		press.setText("������");
		press.addFocusListener(this);

		label_price = new JLabel("�����ۼ�");
		price = new JTextField(15);
		price.setFont(MyFont.TipFont);
		price.setForeground(MyColor.TipColor);
		price.setText("�ɱ�����λС��");
		price.addFocusListener(this);
		jp3.add(label_press);
		jp3.add(press);
		jp3.add(label_price);
		jp3.add(price);

		JPanel jp4 = new JPanel();
		label_classify = new JLabel("��������");
		classify = new JComboBox();
		classify.setPreferredSize(new Dimension(175, 30));
		// װ�ط�����Ϣ
		List<BookClassify> bc_list = null;
		try {
			bc_list = bookClassifyService.findBookClassifyUnion("all");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (BookClassify bc : bc_list) {
			String bc_num = bc.getClassify_num();
			String bc_name = bc.getClassify_name();
			// Ϊ�˸��õ����ַ�����Ϣ������������������ƽ����ַ���ƴ�������µ���Ϣ
			String value = bc_num + ":" + bc_name;
			Item item = new Item(bc_num, value);
			classify.addItem(item);
		}
		label_area = new JLabel("�洢����");
		area = new JComboBox();
		area.setPreferredSize(new Dimension(175, 30));
		// װ�ش洢������Ϣ
		List<StorageArea> sa_list = null;
		try {
			sa_list = storageAreaService.findAllStorageArea();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (StorageArea sa : sa_list) {
			int sa_num = sa.getArea_num();
			String sa_name = sa.getArea_name();
			Item item = new Item(sa_num + "", sa_name);
			area.addItem(item);
		}
		jp4.add(label_classify);
		jp4.add(classify);
		jp4.add(label_area);
		jp4.add(area);

		JPanel jp5 = new JPanel();
		label_publish_date = new JLabel("��������");
		publish_date = new JTextField(15);
		publish_date.setText(current_date);
		publish_date_chooser = Chooser.getInstance();
		publish_date_chooser.register(publish_date);

		label_entry_date = new JLabel("¼������");
		entry_date = new JTextField(15);
		entry_date.setText(current_date);
		entry_date_chooser = Chooser.getInstance();
		entry_date_chooser.register(entry_date);

		label_put_on_date = new JLabel("�ϼ�����");
		put_on_date = new JTextField(15);
		put_on_date.setText(current_date);
		put_on_date_chooser = Chooser.getInstance();
		put_on_date_chooser.register(put_on_date);
		jp5.add(label_publish_date);
		jp5.add(publish_date);
		jp5.add(label_entry_date);
		jp5.add(entry_date);
		jp5.add(label_put_on_date);
		jp5.add(put_on_date);

		JPanel jp6 = new JPanel();
		label_format = new JLabel("ͼ����");
		format = new JTextArea(2, 30);
		format.setFont(MyFont.TipFont);
		format.setForeground(MyColor.TipColor);
		format.setBackground(Color.white);
		format.setText("ͼ����������������");
		format.setLineWrap(true);
		format.addFocusListener(this);
		jp6.add(label_format);
		jp6.add(format);

		JPanel jp7 = new JPanel();
		label_abstract_descr = new JLabel("ժҪ��ע");
		abstract_descr = new JTextArea(5, 30);
		abstract_descr.setFont(MyFont.TipFont);
		abstract_descr.setForeground(MyColor.TipColor);
		abstract_descr.setBackground(Color.white);
		abstract_descr.setText("ͼ����Ҫ��ժ��ע����ѡ��");
		abstract_descr.setLineWrap(true);
		abstract_descr.addFocusListener(this);
		jp7.add(label_abstract_descr);
		jp7.add(abstract_descr);

		JPanel jp8 = new JPanel();
		label_proposal_reader = new JLabel("���߸�ע");
		proposal_reader = new JTextArea(5, 30);
		proposal_reader.setFont(MyFont.TipFont);
		proposal_reader.setForeground(MyColor.TipColor);
		proposal_reader.setBackground(Color.white);
		proposal_reader.setText("ͼ��ʹ�ö���ע����ѡ��");
		proposal_reader.setLineWrap(true);
		proposal_reader.addFocusListener(this);
		jp8.add(label_proposal_reader);
		jp8.add(proposal_reader);

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
		// ��ȡ��ǰ�ı��������
		String isbn_string = isbn.getText();
		String name_string = name.getText();
		String author_string = author.getText();
		String translator_string = translator.getText();
		String press_string = press.getText();
		String price_string = price.getText();
		String publish_date_string = publish_date.getText();
		String entry_date_string = entry_date.getText();
		String put_on_date_string = put_on_date.getText();
		String format_string = format.getText();
		String abstract_descr_string = abstract_descr.getText();
		String proposal_reader_string = proposal_reader.getText();
		if (e.getSource() == save) {
			if (isbn_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "����ͳһ��׼���ISBN����Ϊ�գ�");
			} else if (name_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "ͼ�����Ʋ���Ϊ�գ�");
			} else if (author_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "ͼ��������߲���Ϊ�գ�");
			} else if (translator_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "ͼ��������߲���Ϊ�գ�");
			} else if (press_string.equals("������")) {
				JOptionPane.showMessageDialog(null, "ͼ�����ڳ����粻��Ϊ�գ�");
			} else if (price_string.equals("�ɱ�����λС��")) {
				JOptionPane.showMessageDialog(null, "ͼ�齨���ۼ۲���Ϊ�գ�");
			} else if (format_string.equals("ͼ����������������")) {
				JOptionPane.showMessageDialog(null, "ͼ������������Ϊ�գ�");
			} else {
				// �ṩĬ��ֵ Ҫע���߼��ж�
				if (abstract_descr_string.equals("ͼ����Ҫ��ժ��ע����ѡ��")) {
					abstract_descr_string = "�޾�������";
				}
				if (proposal_reader_string.equals("ͼ��ʹ�ö���ע����ѡ��")) {
					proposal_reader_string = "�޾�������";
				}
				double price_double;
				// �����ݽ��д���(��֤��ת��)
				if (!DataValidation.isBigDecimal(price_string)) {
					JOptionPane.showMessageDialog(null, "ͼ���ۼ��������ݸ�ʽ����");
				} else {
					price_double = Double.valueOf(price_string);
					// ��ȡ��ǰ������ѡ�е�������Ϣ
					Item classify_item = (Item) classify.getSelectedItem();
					Item area_item = (Item) area.getSelectedItem();
					String classify_num = classify_item.getKey();
					int area_num = Integer.valueOf(area_item.getKey());

					// ����ͼ��id�������롢������(������ƴ����������)
					String book_id = RandomGeneration.getRandom32charSeq();
					String barcode = RandomGeneration.getRandom10numSeq();
					if (flag == 0) {
						// ���flagΪ0��˵����ǰ�İ汾ͼ����Ϣ�����ڣ����Զ�������Ӧ���������Ϣ
						callno = RandomGeneration.getBooksCallno(classify_num);
					}
					// ����ͼ����󣬼������ݽ��б���
					Books book = new Books(book_id, barcode, isbn_string,
							callno, name_string, classify_num, area_num,
							author_string, translator_string,
							publish_date_string, press_string, price_double,
							format_string, entry_date_string,
							put_on_date_string, abstract_descr_string,
							proposal_reader_string, 0, 0, 0);
					// ���÷�����������
					try {
						booksService.addBooks(book);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// �����ʾ��Ϣ��������ҳ�棬ˢ��.�����
					JOptionPane.showMessageDialog(null, "ͼ����Ϣ����ɹ���");
					this.setVisible(false);
					parentPanel.refreshTablePanel();
				}
			}
		} else if (e.getSource() == reset) {
			// ����������Ϣ
			isbn.setFont(MyFont.TipFont);
			isbn.setForeground(MyColor.TipColor);
			isbn.setText("������");
			name.setFont(MyFont.TipFont);
			name.setForeground(MyColor.TipColor);
			name.setText("������");
			author.setFont(MyFont.TipFont);
			author.setForeground(MyColor.TipColor);
			author.setText("������");
			translator.setFont(MyFont.TipFont);
			translator.setForeground(MyColor.TipColor);
			translator.setText("������");
			press.setFont(MyFont.TipFont);
			press.setForeground(MyColor.TipColor);
			press.setText("������");
			price.setFont(MyFont.TipFont);
			price.setForeground(MyColor.TipColor);
			price.setText("�ɱ�����λС��");
			format.setFont(MyFont.TipFont);
			format.setForeground(MyColor.TipColor);
			format.setText("ͼ����������������");
			abstract_descr.setFont(MyFont.TipFont);
			abstract_descr.setForeground(MyColor.TipColor);
			abstract_descr.setText("ͼ����Ҫ��ժ��ע����ѡ��");
			proposal_reader.setFont(MyFont.TipFont);
			proposal_reader.setForeground(MyColor.TipColor);
			proposal_reader.setText("ͼ��ʹ�ö���ע����ѡ��");

			// ʱ��Ĭ��Ϊ��ǰϵͳ����
			publish_date.setText(current_date);
			entry_date.setText(current_date);
			put_on_date.setText(current_date);

			// ������ѡ��Ĭ��Ϊ��һ��
			classify.setSelectedIndex(0);
			area.setSelectedIndex(0);
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
		if (e.getSource() == isbn) {
			if (isbn.getText().equals("������")) {
				isbn.setFont(MyFont.JTextFieldFont);
				isbn.setForeground(MyColor.JTextFieldColor);
				isbn.setText("");
			}
		} else if (e.getSource() == name) {
			if (name.getText().equals("������")) {
				name.setFont(MyFont.JTextFieldFont);
				name.setForeground(MyColor.JTextFieldColor);
				name.setText("");
			}
		} else if (e.getSource() == author) {
			if (author.getText().equals("������")) {
				author.setFont(MyFont.JTextFieldFont);
				author.setForeground(MyColor.JTextFieldColor);
				author.setText("");
			}
		} else if (e.getSource() == translator) {
			if (translator.getText().equals("������")) {
				translator.setFont(MyFont.JTextFieldFont);
				translator.setForeground(MyColor.JTextFieldColor);
				translator.setText("");
			}
		} else if (e.getSource() == press) {
			if (press.getText().equals("������")) {
				press.setFont(MyFont.JTextFieldFont);
				press.setForeground(MyColor.JTextFieldColor);
				press.setText("");
			}
		} else if (e.getSource() == price) {
			if (price.getText().equals("�ɱ�����λС��")) {
				price.setFont(MyFont.JTextFieldFont);
				price.setForeground(MyColor.JTextFieldColor);
				price.setText("");
			}
		} else if (e.getSource() == format) {
			if (format.getText().equals("ͼ����������������")) {
				format.setFont(MyFont.JTextFieldFont);
				format.setForeground(MyColor.JTextFieldColor);
				format.setText("");
			}
		} else if (e.getSource() == abstract_descr) {
			if (abstract_descr.getText().equals("ͼ����Ҫ��ժ��ע����ѡ��")) {
				abstract_descr.setFont(MyFont.JTextFieldFont);
				abstract_descr.setForeground(MyColor.JTextFieldColor);
				abstract_descr.setText("");
			}
		} else if (e.getSource() == proposal_reader) {
			if (proposal_reader.getText().equals("ͼ��ʹ�ö���ע����ѡ��")) {
				proposal_reader.setFont(MyFont.JTextFieldFont);
				proposal_reader.setForeground(MyColor.JTextFieldColor);
				proposal_reader.setText("");
			}
		}
	}

	/**
	 * ʧȥ�����¼�
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == isbn) {
			if (isbn.getText().equals("")) {
				isbn.setFont(MyFont.TipFont);
				isbn.setForeground(MyColor.TipColor);
				isbn.setText("������");
			}
		} else if (e.getSource() == name) {
			if (name.getText().equals("")) {
				name.setFont(MyFont.TipFont);
				name.setForeground(MyColor.TipColor);
				name.setText("������");
			}
		} else if (e.getSource() == author) {
			if (author.getText().equals("")) {
				author.setFont(MyFont.TipFont);
				author.setForeground(MyColor.TipColor);
				author.setText("������");
			}
		} else if (e.getSource() == translator) {
			if (translator.getText().equals("")) {
				translator.setFont(MyFont.TipFont);
				translator.setForeground(MyColor.TipColor);
				translator.setText("������");
			}
		} else if (e.getSource() == press) {
			if (press.getText().equals("")) {
				press.setFont(MyFont.TipFont);
				press.setForeground(MyColor.TipColor);
				press.setText("������");
			}
		} else if (e.getSource() == price) {
			if (price.getText().equals("")) {
				price.setFont(MyFont.TipFont);
				price.setForeground(MyColor.TipColor);
				price.setText("�ɱ�����λС��");
			}
		} else if (e.getSource() == format) {
			if (format.getText().equals("")) {
				format.setFont(MyFont.TipFont);
				format.setForeground(MyColor.TipColor);
				format.setText("ͼ����������������");
			}
		} else if (e.getSource() == abstract_descr) {
			if (abstract_descr.getText().equals("")) {
				abstract_descr.setFont(MyFont.TipFont);
				abstract_descr.setForeground(MyColor.TipColor);
				abstract_descr.setText("ͼ����Ҫ��ժ��ע����ѡ��");
			}
		} else if (e.getSource() == proposal_reader) {
			if (proposal_reader.getText().equals("")) {
				proposal_reader.setFont(MyFont.TipFont);
				proposal_reader.setForeground(MyColor.TipColor);
				proposal_reader.setText("ͼ��ʹ�ö���ע����ѡ��");
			}
		}
	}

	/**
	 * ���������ISBN�Զ��������ݣ������ͬһ���汾�������Զ���ʾ����
	 */
	public void echoDataByISBN() {
		String isbn_string = isbn.getText();
		if (isbn_string != "������") {
			// ����isbn����ͼ����Ϣ
			List<Books> books = null;
			try {
				books = booksService.findBooksUnion(2, 1, isbn_string);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (books.size() != 0) {
				// �޸ı�ʶ
				flag = 1;
				// �����ǰ�����isbn������Ӧ�鼮��ѡ����������һ���Ĳ������ݽ������ݻ���
				// ��ͼ��汾��ͬ���Ӧ�Ļ�����Ϣ��Ȼһ�£����������������Զ����ɣ���ɽ�����Ӧ���޸ģ�
				Books book = books.get(0);
				name.setText(book.getBook_name());
				author.setText(book.getAuthor());
				translator.setText(book.getTranslator());
				press.setText(book.getPress());
				price.setText(book.getPrice() + "");
				publish_date.setText(book.getPublish_date().substring(0, 10));
				entry_date.setText(book.getEntry_date().substring(0, 10));
				put_on_date.setText(book.getPut_on_date().substring(0, 10));
				format.setText(book.getFormat());
				abstract_descr.setText(book.getAbstract_descr());
				proposal_reader.setText(book.getProposal_reader());

				// ������Ѵ��ڵ�ͼ��汾����������Ϊ��ǰ�汾��Ӧ������
				callno = book.getCallno();

				String classify_num_string = book.getClassify_num();
				int area_num_int = book.getArea_num();
				// ����װ�ط�������
				classify.removeAllItems();
				List<BookClassify> bc_list = null;
				try {
					bc_list = bookClassifyService.findBookClassifyUnion("all");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < bc_list.size(); i++) {
					int sign = 0;
					String bc_num = bc_list.get(i).getClassify_num();
					String bc_name = bc_list.get(i).getClassify_name();
					// Ϊ�˸��õ����ַ�����Ϣ������������������ƽ����ַ���ƴ�������µ���Ϣ
					String value = bc_num + ":" + bc_name;
					Item item = new Item(bc_num, value);
					classify.addItem(item);
					if (bc_num.equals(classify_num_string)) {
						sign = i;
						classify.setSelectedIndex(sign);
					}
				}
				// ����װ�ش洢������Ϣ
				area.removeAllItems();
				List<StorageArea> sa_list = null;
				try {
					sa_list = storageAreaService.findAllStorageArea();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < sa_list.size(); i++) {
					int sign = 0;
					int sa_num = sa_list.get(i).getArea_num();
					String sa_name = sa_list.get(i).getArea_name();
					Item item = new Item(sa_num + "", sa_name);
					area.addItem(item);
					if (sa_num == area_num_int) {
						sign = i;
						area.setSelectedIndex(sign);
					}
				}
			} else {
				// �������
				if (!isbn.getText().equals("")) {
					// ����������Ϣ
					name.setFont(MyFont.TipFont);
					name.setForeground(MyColor.TipColor);
					name.setText("������");
					author.setFont(MyFont.TipFont);
					author.setForeground(MyColor.TipColor);
					author.setText("������");
					translator.setFont(MyFont.TipFont);
					translator.setForeground(MyColor.TipColor);
					translator.setText("������");
					press.setFont(MyFont.TipFont);
					press.setForeground(MyColor.TipColor);
					press.setText("������");
					price.setFont(MyFont.TipFont);
					price.setForeground(MyColor.TipColor);
					price.setText("�ɱ�����λС��");
					format.setFont(MyFont.TipFont);
					format.setForeground(MyColor.TipColor);
					format.setText("ͼ����������������");
					abstract_descr.setFont(MyFont.TipFont);
					abstract_descr.setForeground(MyColor.TipColor);
					abstract_descr.setText("ͼ����Ҫ��ժ��ע����ѡ��");
					proposal_reader.setFont(MyFont.TipFont);
					proposal_reader.setForeground(MyColor.TipColor);
					proposal_reader.setText("ͼ��ʹ�ö���ע����ѡ��");

					// ʱ��Ĭ��Ϊ��ǰϵͳ����
					publish_date.setText(current_date);
					entry_date.setText(current_date);
					put_on_date.setText(current_date);

					// ������ѡ��Ĭ��Ϊ��һ��
					classify.setSelectedIndex(0);
					area.setSelectedIndex(0);
				}
			}
		}
	}
}
