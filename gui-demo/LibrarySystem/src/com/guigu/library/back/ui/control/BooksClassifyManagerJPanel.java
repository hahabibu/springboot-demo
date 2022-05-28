package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;

import com.guigu.library.back.ui.function.AddBookClassifyJFrame;
import com.guigu.library.back.ui.function.UpdateBookClassifyJFrame;
import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Users;
import com.guigu.library.service.BookClassifyService;
import com.guigu.library.service.impl.BookClassifyServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.DataValidation;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.Tools;

public class BooksClassifyManagerJPanel implements MouseListener, FocusListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			downPanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_search,label_keyword, tool_add, tool_modify, tool_delete, tool_lastLevel,
			tool_nextLevel;
	JTextField keyword;
	// ����ּ�������Ҫ�õ������
	JLabel label_level;
	JTextField level;

	// ������Ӧ��service
	BookClassifyService bookClassifyService = new BookClassifyServiceImpl();

	Users user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public BooksClassifyManagerJPanel(Users user) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.user = user;
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initDownPanel();// ��ʼ���ּ����ҵĹ�����
		initTablePanel();// ��ʼ����ʾ�ı������
	}

	/**
	 * ��ʼ�������Ĳ˵���
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// ��ʼ�����������
		initToolPanel();
		// ��ʼ���������
		initSearchPanel();
		// �������˵������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ�����������
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();

		// �����ɾ�Ĺ���
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("�½�ͼ�����");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("�޸�ͼ�����");// ��������ƶ�ʱ����ʾ����
		tool_modify.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ��ͼ�����");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_add);
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ���������
	 */
	private void initSearchPanel() {

		searchPanel = new JPanel();

		label_keyword = new JLabel("�ؼ���");
		label_keyword.setFont(MyFont.JLabelFont);

		keyword = new JTextField(15);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("������ͼ������Ż�����");
		keyword.addFocusListener(this);

		Icon icon_search = new ImageIcon("icons/toolImage/search.png");
		label_search = new JLabel(icon_search);
		label_search.setToolTipText("����");
		label_search.addMouseListener(this);

		// �����������ص�ָ���������
		searchPanel.add(label_keyword);
		searchPanel.add(keyword);
		searchPanel.add(label_search);
		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ��������ݽ��в���
		String[] params = { "������", "��������", "��ǰ�ȼ�", "�������" };
		Vector<Vector> vec = new Vector<>();

		// �����������
		if (!level.getText().equals("�������0������") && !level.getText().equals("")
				&& !keyword.getText().equals("������ͼ������Ż�����")) {
			try {
				List<BookClassify> listByLevel = null;
				List<BookClassify> listByKeyWord = null;
				listByLevel = bookClassifyService.findBookClassifyUnion(
						"level", level.getText());
				// ƴ�Ӳ��ҵ��ַ���
				String text = "%" + keyword.getText() + "%";
				listByKeyWord = bookClassifyService.findBookClassifyUnion(
						"keyword", text);
				List<BookClassify> findList = this.screenData(listByLevel,
						listByKeyWord);
				vec = bookClassifyService.pack(findList);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!keyword.getText().equals("������ͼ������Ż�����")) {
			try {
				List<BookClassify> listByKeyWord = null;
				// ƴ�Ӳ��ҵ��ַ���
				String text = "%" + keyword.getText() + "%";
				listByKeyWord = bookClassifyService.findBookClassifyUnion(
						"keyword", text);
				vec = bookClassifyService.pack(listByKeyWord);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (!level.getText().equals("�������0������")
				&& !level.getText().equals("")) {
			try {
				List<BookClassify> listByLevel = null;
				listByLevel = bookClassifyService.findBookClassifyUnion(
						"level", level.getText());
				vec = bookClassifyService.pack(listByLevel);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				vec = bookClassifyService.pack(bookClassifyService
						.findBookClassifyUnion("all"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// ����ѯ�������ݷ�װ��BbaseTableModule��
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);

		// table�������������¼�
		table.addMouseListener(this);

		// �����ṩ��Tools���������
		Tools.setTableStyle(table);

		// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// dcm.getColumn(2).setMinWidth(0);
		// dcm.getColumn(2).setMaxWidth(0);

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
	 * ����ּ����ҵ����
	 */
	private void initDownPanel() {
		downPanel = new JPanel();
		Icon icon_lastLevel = new ImageIcon("icons/toolImage/lastLevel.png");
		tool_lastLevel = new JLabel(icon_lastLevel);
		tool_lastLevel.setToolTipText("������һ��");// ��������ƶ�ʱ����ʾ����
		tool_lastLevel.addMouseListener(this);// ���������

		label_level = new JLabel("�ּ�����");
		label_level.setFont(MyFont.JLabelFont);

		level = new JTextField(10);
		level.setFont(MyFont.TipFont);
		level.setForeground(MyColor.TipColor);
		level.setText("�������0������");
		level.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				backgroundPanel.remove(tablePanel);
				initTablePanel();
				backgroundPanel.validate();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// ˢ���������
				String level_string = level.getText();
				// �ж������Ƿ�Ϊ������
				if (DataValidation.isSignlessInteger(level_string)) {
					int level_int = Integer.valueOf(level_string);
					if (level_int > 10) {
						JOptionPane.showMessageDialog(null, "������ֵ���ܳ���10��");
					} else {
						backgroundPanel.remove(tablePanel);
						initTablePanel();
						backgroundPanel.validate();
					}
				} else if (!level.getText().equals("�������0������")) {
					JOptionPane.showMessageDialog(null, "��������ڵ���0��������");
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		level.addFocusListener(this);

		Icon icon_nextLevel = new ImageIcon("icons/toolImage/nextLevel.png");
		tool_nextLevel = new JLabel(icon_nextLevel);
		tool_nextLevel.setToolTipText("��һ��");// ��������ƶ�ʱ����ʾ����
		tool_nextLevel.addMouseListener(this);// ���������

		downPanel.add(tool_lastLevel);
		downPanel.add(label_level);
		downPanel.add(level);
		downPanel.add(tool_nextLevel);
		backgroundPanel.add(downPanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == label_search) {
			// �������˲���ѡ�������ɸѡ
			tablePanel.removeAll();// �Ƴ���������е���������
			initTablePanel();// ���³�ʼ�����
			backgroundPanel.validate();
		}
		if (e.getSource() == tool_add) {
			// ����ͼ�������Ϣ
			int row = table.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(null, "��ѡ����Ӧ�ĸ���Ŀ¼");
			}else{
				new AddBookClassifyJFrame(this,table,row);
			}
		} else if (e.getSource() == tool_modify) {
			// ��ȡ��ǰѡ��Ҫ�޸ĵ�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ�ͼ������¼");
			} else {
				// �޸�ͼ�������Ϣ
				 new UpdateBookClassifyJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			// ɾ����ǰѡ�е�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ����ͼ�������Ϣ");
			} else {
				// ��ȡ��ǰѡ�з�����
				String classify_num = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null,
						"ȷ��ɾ������ͼ�������Ϣ��");
				if (result == 0) {
					// ȷ��ɾ������ִ��ɾ��
					try {
						bookClassifyService.deleteBookClassify(classify_num);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "ͼ�������Ϣɾ���ɹ���");
					refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_lastLevel) {
			String level_string = level.getText();
			if (!level_string.equals("") && !level_string.equals("�������0������")) {
				int level_int = Integer.valueOf(level_string);
				if (level_int > 0) {
					level_int -= 1;
					level.setText(level_int + "");
				}
				backgroundPanel.remove(tablePanel);
				initTablePanel();
				backgroundPanel.validate();
			} else {
				level.setText("0");
				backgroundPanel.remove(tablePanel);
				initTablePanel();
				backgroundPanel.validate();
			}
		} else if (e.getSource() == tool_nextLevel) {
			// �жϵ�ǰ�Ƿ���ѡ�����ݣ����ѡ������ʾ��ǰѡ�����ݵ���һ��Ŀ¼�����û��ѡ��������Ĭ������һ������������
			// �����ı��������⣬���ݻ��˻��е�Сë��
			int row = table.getSelectedRow();
			if (row < 0) {
				String level_string = level.getText();
				if (!level_string.equals("")
						&& !level_string.equals("�������0������")) {
					int level_int = Integer.valueOf(level_string);
					if (level_int < 10) {
						level_int += 1;
						level.setText(level_int + "");
					}
					backgroundPanel.remove(tablePanel);
					initTablePanel();
					backgroundPanel.validate();
				} else {
					level.setText("1");
					backgroundPanel.remove(tablePanel);
					initTablePanel();
					backgroundPanel.validate();
				}
			} else {
				// ��ʾ��һ��Ŀ¼
				backgroundPanel.remove(tablePanel);
				// ��������ݽ��в���
				String[] params = { "������", "��������", "��ǰ�ȼ�", "�������" };
				Vector<Vector> vec = new Vector<>();
				try {
					String parent_num = table.getValueAt(row, 0).toString();
					List<BookClassify> list = bookClassifyService
							.findBookClassifyUnion("parent_classify_num",
									parent_num);
					vec = bookClassifyService.pack(list);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				// ����ѯ�������ݷ�װ��BbaseTableModule��
				baseTableModule = new BaseTableModule(params, vec);
				table = new JTable(baseTableModule);

				// table�������������¼�
				table.addMouseListener(this);

				// �����ṩ��Tools���������
				Tools.setTableStyle(table);

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
			if (keyword.getText().equals("������ͼ������Ż�����")) {
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
				keyword.setText("");
			}
		} else if (e.getSource() == level) {
			if (level.getText().equals("�������0������")) {
				level.setFont(MyFont.JTextFieldFont);
				level.setForeground(MyColor.JTextFieldColor);
				level.setText("");
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
				keyword.setText("������ͼ������Ż�����");
			}
		} else if (e.getSource() == level) {
			if (level.getText().equals("")) {
				level.setFont(MyFont.TipFont);
				level.setForeground(MyColor.TipColor);
				level.setText("�������0������");
			}
		}
	}

	/**
	 * ���level�ͱ�š������ۺ�ɸѡ������Ϣ
	 */
	public List<BookClassify> screenData(List<BookClassify> listByLevel,
			List<BookClassify> listByKeyWord) {
		List<BookClassify> findList = new ArrayList<BookClassify>();
		for (int i = 0; i < listByLevel.size(); i++) {
			for (int j = 0; j < listByKeyWord.size(); j++) {
				// ��������ͬĬ����ͬһ������
				if (listByLevel.get(i).getClassify_num()
						.equals(listByKeyWord.get(j).getClassify_num())) {
					findList.add(listByLevel.get(i));
				}
			}
		}
		return findList;
	}
}