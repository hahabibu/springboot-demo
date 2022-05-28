package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.guigu.library.back.ui.function.AccountManagerJFrame;
import com.guigu.library.back.ui.function.AddReaderJFrame;
import com.guigu.library.back.ui.function.LibraryCardManagerJFrame;
import com.guigu.library.back.ui.function.ShowReaderDetailJFrame;
import com.guigu.library.back.ui.function.UpdateReaderJFrame;
import com.guigu.library.export.ui.ReaderExportCheckBoxJFrame;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.Tools;

public class ReaderArchivesManagerJPanel implements MouseListener,
		FocusListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_search, tool_add, tool_modify, tool_delete, tool_detail,
			tool_import, tool_export, tool_library_card, tool_account;
	JComboBox field;// �����ֶ�
	JTextField keyword;

	// ������Ӧ��service
	ReaderService readerService = new ReaderServiceImpl();

	Users user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public ReaderArchivesManagerJPanel(Users user) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.user = user;
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
		backgroundPanel.validate();
	}

	/**
	 * ��ʼ�����������
	 */
	private void initToolPanel() {
		toolPanel = new JPanel(new BorderLayout());

		JPanel jp1 = new JPanel();
		// �����ɾ�Ĺ���
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("¼�������Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("�޸Ķ�����Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_modify.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ��������Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		JPanel jp2 = new JPanel();
		Icon icon_detail = new ImageIcon("icons/toolImage/detail.png");
		tool_detail = new JLabel(icon_detail);
		tool_detail.setToolTipText("�鿴������Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_detail.addMouseListener(this);// ���������

		Icon icon_import = new ImageIcon("icons/toolImage/import.png");
		tool_import = new JLabel(icon_import);
		tool_import.setToolTipText("���������Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_import.addMouseListener(this);// ���������

		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("����������Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_export.addMouseListener(this);// ���������

		Icon icon_library_card = new ImageIcon(
				"icons/toolImage/library_card.png");
		tool_library_card = new JLabel(icon_library_card);
		tool_library_card.setToolTipText("����֤��Ϣ����");// ��������ƶ�ʱ����ʾ����
		tool_library_card.addMouseListener(this);// ���������

		Icon icon_account = new ImageIcon("icons/toolImage/account.png");
		tool_account = new JLabel(icon_account);
		tool_account.setToolTipText("�˺���Ϣ����");// ��������ƶ�ʱ����ʾ����
		tool_account.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		jp1.add(tool_add);
		jp1.add(tool_modify);
		jp1.add(tool_delete);
		jp1.add(tool_detail);
		jp2.add(tool_import);
		jp2.add(tool_export);
		jp2.add(tool_library_card);
		jp2.add(tool_account);
		toolPanel.add(jp1, BorderLayout.NORTH);
		toolPanel.add(jp2, BorderLayout.SOUTH);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ���������
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		JPanel jp = new JPanel();
		/**
		 * ���ü����ֶε����������� 0.�������� 1.������ 2.ѧ����� 3.���֤�� 4.�����˺� 5.����֤���
		 */
		field = new JComboBox();
		field.addItem("��������");
		field.addItem("������");
		field.addItem("ѧ�����");
		field.addItem("���֤��");
		field.addItem("�����˺�");
		field.addItem("����֤���");

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
		jp.add(field);
		jp.add(keyword);
		jp.add(label_search);

		searchPanel.add(jp);

		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// ��������ݽ��в���
		String[] params = { "����id", "������", "ѧ�����", "����", "�Ա�", "��������", "���֤��",
				"��ϵ��ʽ", "��������", "��ע", "�����˺�id", "�����˺�", "���߷�����", "��������",
				"����֤id", "����֤" };
		Vector<Vector> vec = new Vector<>();
		// Ĭ����ʾ���е�����
		try {
			vec = readerService.pack(readerService.findReaderUnion(0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���������� 0.�������� 1.������ 2.ѧ����� 3.���֤�� 4.�����˺� 5.����֤���
		String keyword_string = keyword.getText();
		if (!keyword_string.equals("�ؼ��ֲ���")) {
			// ����������ѡ���Լ��ı����ж�Ӧ������ɸѡ������Ϣ
			int field_int = field.getSelectedIndex();
			// ��������ɸѡ������Ϣ
			try {
				vec = readerService.pack(readerService.findReaderUnion(
						field_int, keyword_string));
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
		// ����4 5 9 10 12 14
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);
		dcm.getColumn(9).setMinWidth(0);
		dcm.getColumn(9).setMaxWidth(0);
		dcm.getColumn(10).setMinWidth(0);
		dcm.getColumn(10).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(14).setMinWidth(0);
		dcm.getColumn(14).setMaxWidth(0);

		// ���ù�����
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// ����͸����
		tablePanel.add(jScrollPane);
		// ��������ص�������
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == label_search) {
			// �������˲���ѡ�������ɸѡ
			this.refreshTablePanel();
		} else if (e.getSource() == tool_add) {
			// ¼�������Ϣ
			new AddReaderJFrame(this);
		} else if (e.getSource() == tool_modify) {
			// ��ȡ��ǰѡ��Ҫ�޸ĵ�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵĶ�����Ϣ");
			} else {
				// �޸Ķ�����Ϣ
				new UpdateReaderJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			// ɾ����ǰѡ�е�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���Ķ�����Ϣ");
			} else {
				// ��ȡ��ǰѡ�ж���id
				String reader_id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ������������Ϣ��");
				if (result == 0) {
					// ȷ��ɾ������ִ��ɾ��
					try {
						readerService.deleteReader(reader_id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "������Ϣɾ���ɹ���");
					this.refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_detail) {
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ���ĵĶ�����Ϣ");
			} else {
				// �鿴������ϸ��Ϣ
				new ShowReaderDetailJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_import) {
			// �����������
			// ����
		} else if (e.getSource() == tool_export) {
			// ���������ݣ�����ѡ�е��С�ѡ�е��е���������Ϣ��
			// ��ȡ��ǰѡ�е�����
			int[] selectedRows = table.getSelectedRows();
			new ReaderExportCheckBoxJFrame(this, table, selectedRows);
		} else if(e.getSource()==tool_library_card){
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ���н���֤����Ķ�����Ϣ");
			} else {
				// ����֤��Ϣ����
				new LibraryCardManagerJFrame(this, table, row);
			}
		} else if(e.getSource()==tool_account){
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�����˺Ź���Ķ�����Ϣ");
			} else {
				// �˺���Ϣ����
				new AccountManagerJFrame(this, table, row);
			}
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
	 * ˢ�±���������
	 */
	public void refreshTablePanel() {
		// �Ƴ���ǰ��������е���������
		backgroundPanel.remove(tablePanel);
		initTablePanel();
		backgroundPanel.validate();// ��֤
	}

}
