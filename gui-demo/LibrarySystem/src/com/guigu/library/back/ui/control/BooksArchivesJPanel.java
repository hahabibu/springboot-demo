package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.guigu.library.model.Books;
import com.guigu.library.model.Users;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.StorageAreaService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.StorageAreaServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.Tools;

public class BooksArchivesJPanel implements MouseListener, FocusListener,
		ItemListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_search;
	JComboBox field, match;// �����ֶΡ�ƥ�䷽ʽ
	JTextField keyword;

	// ��ѡ��ť
	ButtonGroup searchWay;
	JRadioButton all;
	JRadioButton section;

	// ������Ӧ��service
	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();
	StorageAreaService storageAreaService = new StorageAreaServiceImpl();
	BooksService booksService = new BooksServiceImpl();

	int maxPage = 0;
	int currentPage;
	// ������Ӧ�ķ�ҳ��ϱ�ǩ
	JLabel label_all, label_start, label_end, label_last, label_next;
	JTextField page;

	// �����ҳ��ʶ
	int flag = 0;
	JTable temp_table;

	Users user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public BooksArchivesJPanel(Users user) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.user = user;
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initPagePanel();// �����ҳ���Ұ�ť���
		initTablePanel();// ��ʼ����ʾ�ı������
	}

	/**
	 * ��ʼ�������Ĳ˵���
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// ��ʼ���������
		initSearchPanel();
		// �������˵������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ���������
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel(new BorderLayout());
		JPanel jp1 = new JPanel();
		searchWay = new ButtonGroup();
		all = new JRadioButton("����ͼ��");
		all.addItemListener(this);
		section = new JRadioButton("��������");
		section.addItemListener(this);
		searchWay.add(all);
		searchWay.add(section);
		jp1.add(all);
		jp1.add(Box.createVerticalStrut(10));
		jp1.add(section);

		JPanel jp2 = new JPanel();
		/**
		 * ���ü����ֶε����������� 0.�������� 1.���� 2.isbn 3.����� 4.ͼ����� 5.���� 6.���� 7.������
		 */
		field = new JComboBox();
		field.addItem("��������");
		field.addItem("����");
		field.addItem("isbn");
		field.addItem("�����");
		field.addItem("ͼ�����");
		field.addItem("����");
		field.addItem("����");
		field.addItem("������");

		// ����ƥ�䷽ʽ�����������ԣ� 0.ǰ��һ�� 1.��ȫƥ�� 2.����ƥ��
		match = new JComboBox();
		match.addItem("ǰ��һ��");
		match.addItem("��ȫƥ��");
		match.addItem("����ƥ��");

		keyword = new JTextField(15);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("�ؼ��ֲ���");
		keyword.addFocusListener(this);

		Icon icon_search = new ImageIcon("icons/toolImage/search.png");
		label_search = new JLabel(icon_search);
		label_search.setToolTipText("����");
		label_search.addMouseListener(this);

		// �����������ص�ָ���������
		jp2.add(field);
		jp2.add(match);
		jp2.add(keyword);
		jp2.add(label_search);

		searchPanel.add(jp1, BorderLayout.SOUTH);
		searchPanel.add(jp2, BorderLayout.NORTH);

		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// �޸ı�ʶ
		flag = 0;
		// ��������ݽ��в���
		String[] params = { "ͼ��id", "������", "ISBN", "�����", "����", "����id", "��������",
				"�洢������", " �洢����", "����", "����", "��������", "������", "�۸�", "���",
				"¼������", "�ϼ�����", "��Ҫ��ժ��ע", "ʹ�ö���ע", "���ı�ʶ", "����״̬", "�ϼܱ�ʶ",
				"�ϼ�״̬", "ɾ����ʶ" };
		Vector<Vector> vec = new Vector<>();
		if (all.isSelected()) {
			// ��ʾ���е�����
			try {
				vec = booksService.pack(booksService.findBooksUnion(0, 0));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (section.isSelected()) {
			// ��������ɸѡ����
			// �����ۺ�������������ɸѡ:�ֱ��ȡ��������ı����ѡ������
			int field_int = field.getSelectedIndex();
			int match_int = match.getSelectedIndex();
			try {
				if (field_int == 0) {
					// Ĭ�ϲ������е����ݣ����Ժ󷽵Ĳ�������
					vec = booksService.pack(booksService.findBooksUnion(
							field_int, match_int));
				} else {
					// ��ϼ����ֶκ�ƥ�䷽ʽ���м���
					if (!keyword.getText().equals("�ؼ��ֲ���")) {
						vec = booksService.pack(booksService.findBooksUnion(
								field_int, match_int, keyword.getText()));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// ����ѯ�������ݷ�װ��BbaseTableModule��
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);

		// ��Ⱦ��0�У�������ʾΪ��ѡ�������ʾ
		table.getColumnModel().getColumn(0)
				.setCellRenderer(new TableCellRenderer() {
					@Override
					public Component getTableCellRendererComponent(
							JTable table, Object value, boolean isSelected,
							boolean hasFocus, int row, int column) {
						JCheckBox ck = new JCheckBox();
						ck.setSelected(isSelected);
						ck.setHorizontalAlignment((int) 0.5f);
						return ck;
					}
				});
		// �����ṩ��Tools���������
		Tools.setTableStyle(table);

		// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// ����5 7 11 13 14 15 16 17 18 19 21 23
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(7).setMinWidth(0);
		dcm.getColumn(7).setMaxWidth(0);
		dcm.getColumn(8).setMinWidth(0);
		dcm.getColumn(8).setMaxWidth(0);
		dcm.getColumn(11).setMinWidth(0);
		dcm.getColumn(11).setMaxWidth(0);
		dcm.getColumn(13).setMinWidth(0);
		dcm.getColumn(13).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);
		dcm.getColumn(15).setMinWidth(0);
		dcm.getColumn(15).setMaxWidth(0);
		dcm.getColumn(16).setMinWidth(0);
		dcm.getColumn(16).setMaxWidth(0);
		dcm.getColumn(17).setMinWidth(0);
		dcm.getColumn(17).setMaxWidth(0);
		dcm.getColumn(18).setMinWidth(0);
		dcm.getColumn(18).setMaxWidth(0);
		dcm.getColumn(19).setMinWidth(0);
		dcm.getColumn(19).setMaxWidth(0);
		dcm.getColumn(21).setMinWidth(0);
		dcm.getColumn(21).setMaxWidth(0);
		dcm.getColumn(23).setMinWidth(0);
		dcm.getColumn(23).setMaxWidth(0);

		// ���ù�����
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// ����͸����
		tablePanel.add(jScrollPane);
		// ��������ص�������
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
	}

	/**
	 * ��ҳ���ҹ������
	 */
	public void initPagePanel() {
		pagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		Icon icon_all = new ImageIcon("icons/pageImage/all.png");
		label_all = new JLabel(icon_all);
		label_all.setToolTipText("��ʾ����");
		label_all.addMouseListener(this);

		Icon icon_start = new ImageIcon("icons/pageImage/start.png");
		label_start = new JLabel(icon_start);
		label_start.setToolTipText("��ҳ");
		label_start.addMouseListener(this);

		Icon icon_end = new ImageIcon("icons/pageImage/end.png");
		label_end = new JLabel(icon_end);
		label_end.setToolTipText("βҳ");
		label_end.addMouseListener(this);

		page = new JTextField(5);
		page.setFont(MyFont.JLabelFont);
		page.setForeground(MyColor.JLabelColor);
		page.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				if (page.getText().equals("")) {
					refreshTablePanel();
				} else {
					currentPage = 1;
					refreshTablePanelByPage();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				Pattern pattern = Pattern.compile("[0-9]*");
				if (pattern.matcher(page.getText()).matches()) {
					currentPage = Integer.valueOf(page.getText());
					refreshTablePanelByPage();
				} else {
					JOptionPane.showMessageDialog(null, "������Ϸ������֣�");
					currentPage = 1;
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		Icon icon_last = new ImageIcon("icons/pageImage/last.png");
		label_last = new JLabel(icon_last);
		label_last.setToolTipText("��һҳ");
		label_last.addMouseListener(this);

		Icon icon_next = new ImageIcon("icons/pageImage/next.png");
		label_next = new JLabel(icon_next);
		label_next.setToolTipText("��һҳ");
		label_next.addMouseListener(this);

		pagePanel.add(label_all);
		pagePanel.add(label_start);
		pagePanel.add(label_last);
		pagePanel.add(page);
		pagePanel.add(label_next);
		pagePanel.add(label_end);
		// ��������ص�������
		backgroundPanel.add(pagePanel, BorderLayout.SOUTH);
		// ��ˢ�����ݵ�ʱ��ı䴰���С���������ˢ��
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// �жϱ�ʶ�����flagΪ0˵���ǿ�ʼִ�з�ҳ�������и�ֵ
		if (flag == 0) {
			// ��ʼ�����ҳ��
			maxPage = this.getMaxPage(table.getRowCount());
			// ��¼��ʱ�������
			temp_table = table;
		}
		if (e.getSource() == label_all) {
			page.setText("");
			this.refreshTablePanel();
		} else if (e.getSource() == label_start) {
			page.setText("1");
			this.refreshTablePanelByPage();
		} else if (e.getSource() == label_end) {
			page.setText(String.valueOf(maxPage));
			this.refreshTablePanelByPage();
		} else if (e.getSource() == label_last) {
			// ��ȡ��ǰҳ�����бȽ�
			if (currentPage > 1) {
				page.setText((currentPage - 1) + "");
			} else if (currentPage == 1) {
				page.setText("1");
			}
			this.refreshTablePanelByPage();
		} else if (e.getSource() == label_next) {
			// ��ȡ��ǰҳ�����бȽ�
			if (maxPage != 0) {
				if (currentPage < maxPage) {
					page.setText((currentPage + 1) + "");
				} else if (currentPage == maxPage) {
					page.setText(maxPage + "");
				}
			}
			this.refreshTablePanelByPage();
		} else if (e.getSource() == label_search) {
			// �������˲���ѡ�������ɸѡ
			this.refreshTablePanel();
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

	// ��ȡ�����¼�
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("�ؼ��ֲ���")) {
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
				keyword.setText("");
			}
		}
	}

	// ʧȥ�����¼�
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
				keyword.setText("�ؼ��ֲ���");
			}
		}
	}

	/**
	 * ��ȡ��ǰ������ݵ�����ҳ����Ĭ����ÿ10�����ݽ��з�ҳ
	 */
	public int getMaxPage(int rows) {
		// ����ҳ����
		BigDecimal i = BigDecimal.valueOf(rows);
		BigDecimal i2 = BigDecimal.valueOf(10);
		return i.divide(i2).intValue() + 1;
	}

	/**
	 * ˢ�±���������
	 */
	public void refreshTablePanel() {
		// �Ƴ���ǰ��������е���������
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();// ��֤
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// �����ѡ��ť״̬�����仯��ˢ�����ݱ����Ϣ
			this.refreshTablePanel();
		}
	}

	/**
	 * ��ҳ����
	 */
	public void refreshTablePanelByPage() {
		// �޸ı�ʶ
		flag = 1;
		// �Ƴ��������ı������
		backgroundPanel.remove(tablePanel);
		// ��������ݽ��в���
		String[] params = { "ͼ��id", "������", "ISBN", "�����", "����", "����id", "��������",
				"�洢������", " �洢����", "����", "����", "��������", "������", "�۸�", "���",
				"¼������", "�ϼ�����", "��Ҫ��ժ��ע", "ʹ�ö���ע", "���ı�ʶ", "����״̬", "�ϼܱ�ʶ",
				"�ϼ�״̬", "ɾ����ʶ" };
		Vector<Vector> vec = new Vector<>();
		if (all.isSelected()) {
			// ��ʾ���е�����
			try {
				vec = booksService.pack(booksService.findBooksUnion(0, 0));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (section.isSelected()) {
			// ��������ɸѡ����
			// �����ۺ�������������ɸѡ:�ֱ��ȡ��������ı����ѡ������
			int field_int = field.getSelectedIndex();
			int match_int = match.getSelectedIndex();
			try {
				if (field_int == 0) {
					// Ĭ�ϲ������е����ݣ����Ժ󷽵Ĳ�������
					vec = booksService.pack(booksService.findBooksUnion(
							field_int, match_int));
				} else {
					// ��ϼ����ֶκ�ƥ�䷽ʽ���м���
					if (!keyword.getText().equals("�ؼ��ֲ���")) {
						vec = booksService.pack(booksService.findBooksUnion(
								field_int, match_int, keyword.getText()));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (!page.getText().equals("")) {
			// �����ҳ����ѡ���Ϊ������Ϊ���ڵ�ǰ���ҵ����ݵĻ����Ͻ��з�ҳ����
			// ����vec������temp_table���ݽ��з�ҳ����
			vec = this.findBookByPageTable(temp_table);
			// vec = this.findBookByPageVec(vec);

			baseTableModule = new BaseTableModule(params, vec);
			table = new JTable(baseTableModule);

			// ��Ⱦ��0�У�������ʾΪ��ѡ�������ʾ
			table.getColumnModel().getColumn(0)
					.setCellRenderer(new TableCellRenderer() {
						@Override
						public Component getTableCellRendererComponent(
								JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							JCheckBox ck = new JCheckBox();
							ck.setSelected(isSelected);
							ck.setHorizontalAlignment((int) 0.5f);
							return ck;
						}
					});
			// �����ṩ��Tools���������
			Tools.setTableStyle(table);

			// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
			DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
					.getColumnModel();
			// ����5 7 11 13 14 15 16 17 18 19 21 23
			dcm.getColumn(5).setMinWidth(0);
			dcm.getColumn(5).setMaxWidth(0);
			dcm.getColumn(7).setMinWidth(0);
			dcm.getColumn(7).setMaxWidth(0);
			dcm.getColumn(11).setMinWidth(0);
			dcm.getColumn(11).setMaxWidth(0);
			dcm.getColumn(13).setMinWidth(0);
			dcm.getColumn(13).setMaxWidth(0);
			dcm.getColumn(14).setMinWidth(0);
			dcm.getColumn(14).setMaxWidth(0);
			dcm.getColumn(15).setMinWidth(0);
			dcm.getColumn(15).setMaxWidth(0);
			dcm.getColumn(16).setMinWidth(0);
			dcm.getColumn(16).setMaxWidth(0);
			dcm.getColumn(17).setMinWidth(0);
			dcm.getColumn(17).setMaxWidth(0);
			dcm.getColumn(18).setMinWidth(0);
			dcm.getColumn(18).setMaxWidth(0);
			dcm.getColumn(19).setMinWidth(0);
			dcm.getColumn(19).setMaxWidth(0);
			dcm.getColumn(20).setMinWidth(0);
			dcm.getColumn(20).setMaxWidth(0);
			dcm.getColumn(21).setMinWidth(0);
			dcm.getColumn(21).setMaxWidth(0);
			dcm.getColumn(22).setMinWidth(0);
			dcm.getColumn(22).setMaxWidth(0);
			dcm.getColumn(23).setMinWidth(0);
			dcm.getColumn(23).setMaxWidth(0);

			// ���ù�����
			jScrollPane = new JScrollPane(table);
			Tools.setJspStyle(jScrollPane);

			tablePanel = new JPanel(new BorderLayout());
			tablePanel.setOpaque(false);// ����͸����
			tablePanel.add(jScrollPane);
			// ��������ص�������
			backgroundPanel.add(tablePanel, BorderLayout.CENTER);
			backgroundPanel.validate();
		}
	}

	public Vector<Vector> findBookByPageVec(Vector<Vector> vec) {
		Vector<Vector> vecByPage = new Vector<Vector>();
		if (vec.size() != 0) {
			// ��ȡ��ǰָ����ҳ��
			currentPage = Integer.valueOf(page.getText());
			if (currentPage != 0 && maxPage != 0) {
				// ÿ10�����ݼ�¼���з�ҳ,��Ϊ�µ����ݼ�¼����
				int start = (Integer.valueOf(currentPage) - 1) * 10 + 1;
				int end = (Integer.valueOf(currentPage) - 1) * 10 + 10;
				// ����Խ�磬�������ļ�¼������Ҫ���д���
				// �˴�ȡ��Ӧ���ǵ�ǰ�����������ҵ��ļ�¼��������¼��ȡ��
				int last_page_count = vec.size() % 10;
				// �жϵ�ǰҳ���Ƿ�Ϊ���һҳ,���Ϊ���һҳ������end����Ϊ��Ӧ��last_page_count
				if (currentPage == maxPage) {
					end = (Integer.valueOf(currentPage) - 1) * 10
							+ last_page_count;
				}
				for (int i = start; i <= end; i++) {
					// �����Ǵ�0��ʼ
					vecByPage.addElement(vec.get(i - 1));// ��Ӽ�¼
				}
			}
		}
		// �����µ����ݼ�¼
		return vecByPage;
	}

	/**
	 * �Ե�ǰ�����ݼ�¼�н��з�ҳ���� ��������ͨ��Vector<Vector> vec���в��ң����淢������ӵĹ����д�������
	 * ��˴˴�����table������ݴ���
	 */
	public Vector<Vector> findBookByPageTable(JTable page_table) {
		Vector<Vector> vecByPage = new Vector<Vector>();
		if (page_table != null) {
			if (page_table.getRowCount() != 0) {
				// ��ȡ��ǰָ����ҳ��
				currentPage = Integer.valueOf(page.getText());
				if (currentPage != 0) {
					// ÿ10�����ݼ�¼���з�ҳ,��Ϊ�µ����ݼ�¼����
					int start = (Integer.valueOf(currentPage) - 1) * 10 + 1;
					int end = (Integer.valueOf(currentPage) - 1) * 10 + 10;
					// ����Խ�磬�������ļ�¼������Ҫ���д���
					// �˴�ȡ��Ӧ���ǵ�ǰ�����������ҵ��ļ�¼��������¼��ȡ��
					int last_page_count = (page_table.getRowCount()) % 10;
					// �жϵ�ǰҳ���Ƿ�Ϊ���һҳ,���Ϊ���һҳ������end����Ϊ��Ӧ��last_page_count
					if (currentPage == maxPage) {
						end = (Integer.valueOf(currentPage) - 1) * 10
								+ last_page_count;
					}
					List<Books> findBooks = new ArrayList<Books>();
					for (int i = start; i <= end; i++) {
						// ��Ӽ�¼
						Books find_book = null;
						try {
							find_book = booksService.getBooksById(page_table
									.getValueAt(i - 1, 0).toString());
						} catch (SQLException e) {
							e.printStackTrace();
						}
						findBooks.add(find_book);
					}
					// ��װ�µ�vec��Ϣ
					try {
						vecByPage = booksService.pack(findBooks);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		// �����µ����ݼ�¼
		return vecByPage;
	}
}