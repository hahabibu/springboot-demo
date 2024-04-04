package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.guigu.library.model.Borrowing;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Renew;
import com.guigu.library.model.Returning;
import com.guigu.library.model.Users;
import com.guigu.library.service.BorrowingService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.RenewService;
import com.guigu.library.service.ReturningService;
import com.guigu.library.service.impl.BorrowingServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.RenewServiceImpl;
import com.guigu.library.service.impl.ReturningServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.Chooser;
import com.guigu.library.utils.Tools;

public class BooksBRSearchJPanel implements MouseListener, DocumentListener,
		ItemListener {
	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel, searchPanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	ButtonGroup record;
	JRadioButton tool_borrow, tool_renew, tool_returning, tool_violation;
	JTextField start_time, end_time;
	Chooser start = Chooser.getInstance();
	Chooser end = Chooser.getInstance();

	// ������Ӧ��service
	ReaderService readerService = new ReaderServiceImpl();
	BorrowingService borrowingService = new BorrowingServiceImpl();
	RenewService renewService = new RenewServiceImpl();
	ReturningService returningService = new ReturningServiceImpl();

	Users user;
	Reader reader;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public BooksBRSearchJPanel(Users user) {
		this.user = user;
		backgroundPanel = new JPanel(new BorderLayout());
		// ͨ����ǰ��¼�˺Ż�ȡ������Ϣ
		try {
			reader = readerService.getReaderByUserId(this.user.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
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
		Icon icon_borrow = new ImageIcon("icons/toolImage/borrow.png");
		tool_borrow = new JRadioButton(icon_borrow);
		tool_borrow.setToolTipText("���ļ�¼");// ��������ƶ�ʱ����ʾ����
		tool_borrow.addMouseListener(this);// ���������
		tool_borrow.addItemListener(this);

		Icon icon_renew = new ImageIcon("icons/toolImage/renew.png");
		tool_renew = new JRadioButton(icon_renew);
		tool_renew.setToolTipText("�����¼");// ��������ƶ�ʱ����ʾ����
		tool_renew.addMouseListener(this);// ���������
		tool_renew.addItemListener(this);

		Icon icon_returning = new ImageIcon("icons/toolImage/return.png");
		tool_returning = new JRadioButton(icon_returning);
		tool_returning.setToolTipText("�黹��¼");// ��������ƶ�ʱ����ʾ����
		tool_returning.addMouseListener(this);// ���������
		tool_returning.addItemListener(this);

		Icon icon_violation = new ImageIcon("icons/toolImage/violation.png");
		tool_violation = new JRadioButton(icon_violation);
		tool_violation.setToolTipText("Υ�¼�¼");// ��������ƶ�ʱ����ʾ����
		tool_violation.addMouseListener(this);// ���������
		tool_violation.addItemListener(this);

		record = new ButtonGroup();
		record.add(tool_borrow);
		record.add(tool_renew);
		record.add(tool_returning);
		record.add(tool_violation);
		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_borrow);
		toolPanel.add(Box.createHorizontalStrut(10));
		toolPanel.add(tool_renew);
		toolPanel.add(Box.createHorizontalStrut(10));
		toolPanel.add(tool_returning);
		toolPanel.add(Box.createHorizontalStrut(10));
		toolPanel.add(tool_violation);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ�� ���ݶ������ɵ�ʱ�䷶Χ���в���
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		Icon start_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_start = new JLabel(start_icon);
		start_time = new JTextField(20);
		start.register(start_time);
		start_time.getDocument().addDocumentListener(this);

		Icon end_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_end = new JLabel(end_icon);
		end_time = new JTextField(20);
		end.register(end_time);
		end_time.getDocument().addDocumentListener(this);

		searchPanel.add(label_start);
		searchPanel.add(start_time);
		searchPanel.add(Box.createHorizontalStrut(30));
		searchPanel.add(label_end);
		searchPanel.add(end_time);

		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		tablePanel = new JPanel(new BorderLayout());
		if (tool_borrow.isSelected() || tool_violation.isSelected()) {
			// ��������ݽ��в���
			String[] params = { "����id", "���ı��", "ͼ��id", "ͼ������", "����id", "��������",
					"��������", "Ӧ������", "����״̬��ʶ", "����״̬", "Υ��״̬��ʶ", "Υ��״̬" };
			Vector<Vector> vec = new Vector<>();
			// �ж�������������ڸ�ʽ�Ƿ�Ϸ�
			String start_time_string = start_time.getText();
			String end_time_string = end_time.getText();

			// �����ʼʱ��Ϊ����Ϊ��Сֵ
			if (start_time_string.equals("")) {
				start_time_string = "0000-00-00";
			}
			// �������ʱ��Ϊ����Ϊ���ֵ
			if (end_time_string.equals("")) {
				end_time_string = "9999-99-99";
			}
			// ���ڴ˴���������ֵ���������ʱ��ѡ���Ϊ�������Ӧ������ֵ����ʾ��������е���Ʒ����
			// ���в���
			if (tool_borrow.isSelected()) {
				try {
					// �����ۺ�����ɸѡ������Ϣ(ʱ��+����id)
					List<Borrowing> time_list = borrowingService
							.findBorrowingByTime(start_time_string,
									end_time_string);
					List<Borrowing> all_list = borrowingService
							.findAllBorrowing();
					vec = borrowingService.pack(this.BorrowRecordfilter(
							time_list, all_list));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if ((tool_violation.isSelected())) {
				try {
					// �����ۺ�����ɸѡ������Ϣ(ʱ��+����id)
					List<Borrowing> time_list = borrowingService
							.findViolationBorrowingByTime(start_time_string,
									end_time_string);
					List<Borrowing> all_list = borrowingService
							.findAllBorrowing();
					vec = borrowingService.pack(this.BorrowRecordfilter(
							time_list, all_list));
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
			// ���� 2 4 7 9 10
			dcm.getColumn(2).setMinWidth(0);
			dcm.getColumn(2).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
			dcm.getColumn(7).setMinWidth(0);
			dcm.getColumn(7).setMaxWidth(0);
			dcm.getColumn(8).setMinWidth(0);
			dcm.getColumn(8).setMaxWidth(0);
			dcm.getColumn(10).setMinWidth(0);
			dcm.getColumn(10).setMaxWidth(0);
		} else if (tool_renew.isSelected()) {
			// ��������ݽ��в���
			String[] params = { "����id", "������", "ͼ��id", "ͼ������", "����id", "��������",
					"��������" };
			Vector<Vector> vec = new Vector<>();
			// �ж�������������ڸ�ʽ�Ƿ�Ϸ�
			String start_time_string = start_time.getText();
			String end_time_string = end_time.getText();
			if (start_time_string.equals("") && end_time_string.equals("")) {
				// �������е������¼
				try {
					vec = renewService.pack(renewService
							.findRenewByReaderId(reader.getReader_id()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				// �����ʼʱ��Ϊ����Ϊ��Сֵ
				if (start_time_string.equals("")) {
					start_time_string = "0000-00-00";
				}
				// �������ʱ��Ϊ����Ϊ���ֵ
				if (end_time_string.equals("")) {
					end_time_string = "9999-99-99";
				}
				// ���ڴ˴���������ֵ���������ʱ��ѡ���Ϊ�������Ӧ������ֵ����ʾ��������е���Ʒ����
				// ���в���
				try {
					// �����ۺ�����ɸѡ������Ϣ(ʱ��+����id)
					List<Renew> time_list = renewService.findRenewByTime(
							start_time_string, end_time_string);
					List<Renew> all_list = renewService.findAllRenew();
					vec = renewService.pack(this.RenewRecordfilter(time_list,
							all_list));
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
			// ���� 2 4 7 9 10
			dcm.getColumn(2).setMinWidth(0);
			dcm.getColumn(2).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
		} else if (tool_returning.isSelected()) {
			// ��������ݽ��в���
			String[] params = { "�黹id", "�黹���", "ͼ��id", "ͼ������", "����id", "��������",
					"�黹����" };
			Vector<Vector> vec = new Vector<>();
			// �ж�������������ڸ�ʽ�Ƿ�Ϸ�
			String start_time_string = start_time.getText();
			String end_time_string = end_time.getText();
			if (start_time_string.equals("") && end_time_string.equals("")) {
				// �������еĹ黹��¼
				try {
					vec = returningService.pack(returningService
							.findReturningByReaderId(reader.getReader_id()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				// �����ʼʱ��Ϊ����Ϊ��Сֵ
				if (start_time_string.equals("")) {
					start_time_string = "0000-00-00";
				}
				// �������ʱ��Ϊ����Ϊ���ֵ
				if (end_time_string.equals("")) {
					end_time_string = "9999-99-99";
				}
				// ���ڴ˴���������ֵ���������ʱ��ѡ���Ϊ�������Ӧ������ֵ����ʾ��������е���Ʒ����
				// ���в���
				if (tool_borrow.isSelected()) {
					try {
						// �����ۺ�����ɸѡ������Ϣ(ʱ��+����id)
						List<Returning> time_list = returningService
								.findReturningByTime(start_time_string,
										end_time_string);
						List<Returning> all_list = returningService
								.findAllReturning();
						vec = returningService.pack(this.ReturnRecordfilter(
								time_list, all_list));
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
			// ���� 2 4 7 9 10
			dcm.getColumn(2).setMinWidth(0);
			dcm.getColumn(2).setMaxWidth(0);
			dcm.getColumn(4).setMinWidth(0);
			dcm.getColumn(4).setMaxWidth(0);
		}
		// ���ù�����
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel.setOpaque(false);// ����͸����
		tablePanel.add(jScrollPane);
		// ��������ص�������
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * ˢ�±������
	 */
	public void refreshTablePanel() {
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();
	}

	/**
	 * ˢ���������
	 */
	public void refreshBackgroundPanel() {
		// �Ƴ���ǰ��������е���������
		backgroundPanel.removeAll();
		initTopPanel();
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

	@Override
	public void insertUpdate(DocumentEvent e) {
		// �ı���仯����Ӧˢ�����ݱ��
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// �ı���仯����Ӧˢ�����ݱ��
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == tool_borrow) {
			// ���ı�ǩ������ɫ��ˢ���������
			tool_borrow.setBackground(Color.cyan);
			tool_renew.setBackground(Color.white);
			tool_returning.setBackground(Color.white);
			tool_violation.setBackground(Color.white);
			this.refreshTablePanel();
		} else if (e.getSource() == tool_renew) {
			// ���ı�ǩ������ɫ��ˢ���������
			tool_borrow.setBackground(Color.white);
			tool_renew.setBackground(Color.cyan);
			tool_returning.setBackground(Color.white);
			tool_violation.setBackground(Color.white);
			this.refreshTablePanel();
		} else if (e.getSource() == tool_returning) {
			// ���ı�ǩ������ɫ��ˢ���������
			tool_borrow.setBackground(Color.white);
			tool_renew.setBackground(Color.white);
			tool_returning.setBackground(Color.cyan);
			tool_violation.setBackground(Color.white);
			this.refreshTablePanel();
		} else if (e.getSource() == tool_violation) {
			// ���ı�ǩ������ɫ��ˢ���������
			tool_borrow.setBackground(Color.white);
			tool_renew.setBackground(Color.white);
			tool_returning.setBackground(Color.white);
			tool_violation.setBackground(Color.cyan);
			this.refreshTablePanel();
		}
	}

	/**
	 * ���ݲ��ҵ��Ľ������ݼ�¼ɸѡ���ظ������: ʱ������+����id
	 */
	public List<Borrowing> BorrowRecordfilter(List<Borrowing> time_list,
			List<Borrowing> all_list) {
		List<Borrowing> findlist = new ArrayList<>();
		for (int i = 0; i < time_list.size(); i++) {
			for (int j = 0; j < all_list.size(); j++) {
				if (time_list.get(i).getBorrowing_id()
						.equals(all_list.get(j).getBorrowing_id())) {
					findlist.add(time_list.get(i));
				}
			}
		}
		return findlist;
	}

	public List<Renew> RenewRecordfilter(List<Renew> time_list,
			List<Renew> all_list) {
		List<Renew> findlist = new ArrayList<>();
		for (int i = 0; i < time_list.size(); i++) {
			for (int j = 0; j < all_list.size(); j++) {
				if (time_list.get(i).getRenew_id()
						.equals(all_list.get(j).getRenew_id())) {
					findlist.add(time_list.get(i));
				}
			}
		}
		return findlist;
	}

	public List<Returning> ReturnRecordfilter(List<Returning> time_list,
			List<Returning> all_list) {
		List<Returning> findlist = new ArrayList<>();
		for (int i = 0; i < time_list.size(); i++) {
			for (int j = 0; j < all_list.size(); j++) {
				if (time_list.get(i).getReturning_id()
						.equals(all_list.get(j).getReturning_id())) {
					findlist.add(time_list.get(i));
				}
			}
		}
		return findlist;
	}
}