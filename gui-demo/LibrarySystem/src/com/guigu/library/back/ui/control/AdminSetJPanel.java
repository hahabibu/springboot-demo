package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
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

import com.guigu.library.back.ui.function.AdminSettingJFrame;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.Tools;

public class AdminSetJPanel implements MouseListener, FocusListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_search, tool_setting;
	JComboBox field;// �����ֶ�
	JTextField keyword;

	// ������Ӧ��service
	ReaderService readerService = new ReaderServiceImpl();

	Users user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public AdminSetJPanel(Users user) {
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
		toolPanel = new JPanel();

		JPanel jp1 = new JPanel();
		Icon icon_setting = new ImageIcon("icons/toolImage/setting.png");
		tool_setting = new JLabel(icon_setting);
		tool_setting.setToolTipText("����Ȩ������");// ��������ƶ�ʱ����ʾ����
		tool_setting.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		jp1.add(tool_setting);
		toolPanel.add(jp1);
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
			vec = readerService.pack(this.getAdminByField(readerService
					.findReaderUnion(0)));
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
				vec = readerService.pack(this.getAdminByField(readerService
						.findReaderUnion(field_int, keyword_string)));
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
		} else if (e.getSource() == tool_setting) {
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ����Ȩ�����õĹ���Ա��Ϣ��");
			} else {
				// ���й���ԱȨ������(ֻ�г�������Ա�����ʸ����Ȩ������)
				String classify = table.getValueAt(row, 12).toString();
				if (classify.equals("4")) {
					new AdminSettingJFrame(this, table, row);
				} else {
					JOptionPane.showMessageDialog(null, "��Ǹ������ǰû��Ȩ�޽��й���Ա���ã�");
				}
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

	/**
	 * ͨ����ǰ���������������Ķ�����Ϣ�����ж���ɸѡ��ȡ���Ϊ����Ա���߳�������Ա�Ķ�����Ϣ
	 */
	public List<Reader> getAdminByField(List<Reader> readers) {
		List<Reader> r = new ArrayList<Reader>();
		for (int i = 0; i < readers.size(); i++) {
			int classify = readers.get(i).getClassify_num();
			if (classify == 3 || classify == 4) {
				// ���߷�����Ϊ3��4��ʾͼ�����Ա�����ǳ�������Ա
				r.add(readers.get(i));
			}
		}
		return r;
	}

}
