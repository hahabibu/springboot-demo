package com.guigu.library.fore.ui.function;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.guigu.library.model.BookClassify;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.TempService;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.TempServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.Tools;

public class MyFavoriteJFrame extends JFrame implements MouseListener,
		FocusListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			downPanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel tool_cancelSave;
	JTextField keyword;
	// ����ּ�������Ҫ�õ������
	JLabel label_level;
	JTextField level;

	// ������Ӧ��service
	TempService tempService = new TempServiceImpl();
	ReaderService readerService = new ReaderServiceImpl();

	Users user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public MyFavoriteJFrame(Users user) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.user = user;
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initTablePanel();// ��ʼ����ʾ�ı������

		this.add(backgroundPanel);
		this.setTitle("�ҵ��ղؼ�");
		this.setSize(600, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// ����������
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// ���ùرշ�ʽ
		// ��ǰ�������أ���Ӱ������ݵ�ʹ�ã������ǹر���������
	}

	/**
	 * ��ʼ�������Ĳ˵���
	 */
	private void initTopPanel() {
		topPanel = new JPanel(new BorderLayout());
		// ��ʼ�����������
		initToolPanel();
		// �������˵������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ�����������
	 */
	private void initToolPanel() {
		toolPanel = new JPanel();

		// �����ɾ�Ĺ���
		Icon icon_cancelSave = new ImageIcon("icons/toolImage/cancelSave.png");
		tool_cancelSave = new JLabel(icon_cancelSave);
		tool_cancelSave.setToolTipText("ȡ���ղ�");// ��������ƶ�ʱ����ʾ����
		tool_cancelSave.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_cancelSave);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ��������ݽ��в���
		String[] params = { "��¼id", "������", "ISBN", "�����", "����" };
		Vector<Vector> vec = new Vector<>();
		// ��ȡʹ�õ�ǰ�˺ŵĶ�����Ϣ
		Reader r = null;
		try {
			r = readerService.getReaderByUserId(this.user.getUser_id());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (r != null) {
			try {
				vec = tempService.pack(tempService.findTempByReaderId(r
						.getReader_id()));
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_cancelSave) {
			// ����ͼ�������Ϣ
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫȡ���ղص�ѡ��");
			} else {
				try {
					tempService.deleteTemp(table.getValueAt(row, 0).toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "�鼮ȡ���ղسɹ���");
				this.refreshTablePanel();
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