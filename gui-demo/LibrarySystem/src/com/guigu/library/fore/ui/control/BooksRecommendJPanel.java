package com.guigu.library.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

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
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.guigu.library.fore.ui.function.MyFavoriteJFrame;
import com.guigu.library.model.Books;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Temp;
import com.guigu.library.model.Users;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.TempService;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.TempServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;
import com.guigu.library.utils.Tools;
/**
 * ���ݵ�ǰʱ���ɸѡ�����Ĵ�������ͼ��
 * ���ͬʱ����������ͳ��ͼ����ʽ��ʾ����Ӧ��������Ϣ
 */
public class BooksRecommendJPanel implements MouseListener,
		ItemListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel tool_refresh, tool_save, tool_favorite;

	// ������Ӧ��service
	BooksService booksService = new BooksServiceImpl();
	TempService tempService = new TempServiceImpl();
	ReaderService readerService = new ReaderServiceImpl();

	Users user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public BooksRecommendJPanel(Users user) {
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
		// �������˵������ص����������
		backgroundPanel.add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ�����������
	 */
	private void initToolPanel() {
		toolPanel = new JPanel(new BorderLayout());

		JPanel jp1 = new JPanel();
		
		Icon icon_refresh = new ImageIcon("icons/toolImage/refresh.png");
		tool_refresh = new JLabel(icon_refresh);
		tool_refresh.setToolTipText("����ˢ��");// ��������ƶ�ʱ����ʾ����
		tool_refresh.addMouseListener(this);// ���������
		
		Icon icon_save = new ImageIcon("icons/toolImage/save.png");
		tool_save = new JLabel(icon_save);
		tool_save.setToolTipText("������ղؼ�");// ��������ƶ�ʱ����ʾ����
		tool_save.addMouseListener(this);// ���������

		Icon icon_favorite = new ImageIcon("icons/toolImage/favorite.png");
		tool_favorite = new JLabel(icon_favorite);
		tool_favorite.setToolTipText("�ҵ��ղ�");// ��������ƶ�ʱ����ʾ����
		tool_favorite.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		jp1.add(tool_save);
		jp1.add(tool_favorite);
		toolPanel.add(jp1, BorderLayout.NORTH);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// ��������ݽ��в���
		String[] params = { "ͼ��id", "������", "ISBN", "�����", "����", "����id", "��������",
				"�洢������", " �洢����", "����", "����", "��������", "������", "�۸�", "���",
				"¼������", "�ϼ�����", "��Ҫ��ժ��ע", "ʹ�ö���ע", "���ı�ʶ", "����״̬", "�ϼܱ�ʶ",
				"�ϼ�״̬", "ɾ����ʶ" };
		Vector<Vector> vec = new Vector<>();
//		vec = booksService.pack(booksService.findBooksUnion(field_int,
//				match_int, keyword.getText()));
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
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_refresh) {
			// ��������ˢ��ѡ������б�����ݵ�ˢ��
			this.refreshTablePanel();
		} else if (e.getSource() == tool_save) {
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ������ղؼе��鼮��Ϣ��");
			} else {
				try {
					// ��ָ���鼮������ҵ��ղؼУ��鼮���ڣ�ISBN��ͬ����Ϊ��ͬһ���飩����ʾ������Ϣ���鼮��Ϣ��������������
					String book_id = table.getValueAt(row, 0).toString();
					Books b = booksService.getBooksById(book_id);
					String reader_id = null;
					// ���ݵ�ǰ��¼�û���ȡReader��Ϣ
					Reader r = readerService.getReaderByUserId(this.user
							.getUser_id());
					if (r != null) {
						reader_id = r.getReader_id();
					}
					Temp t = tempService.getTempByISBN(b.getIsbn(), reader_id);
					if (t != null) {
						JOptionPane.showMessageDialog(null,
								"���鼮��������ղؼУ������ظ�������");
					} else {
						// ��ʱ��¼id��������ɵ�32λchar���͵�����
						String temp_id = RandomGeneration.getRandom32charSeq();
						// ����Temp��¼�������
						Temp newTemp = new Temp(temp_id, book_id, reader_id);
						tempService.addTemp(newTemp);
						JOptionPane.showMessageDialog(null, "���鼮�ѳɹ�������ղؼУ�");
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == tool_favorite) {
			// �鿴�ҵ��ղؼ�
			new MyFavoriteJFrame(this.user);
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

}
