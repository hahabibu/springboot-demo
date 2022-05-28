package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;

import com.guigu.library.back.ui.function.UpdateReaderClassifyJFrame;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.utils.BaseTableModule;
import com.guigu.library.utils.Tools;

public class ReaderClassifyManagerJPanel implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel tool_modify;

	// ������Ӧ��service
	ReaderClassifyService readerClassifyService = new ReaderClassifyServiceImpl();

	Users user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public ReaderClassifyManagerJPanel(Users user) {
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
		toolPanel = new JPanel();

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("�޸Ķ��߷���");// ��������ƶ�ʱ����ʾ����
		tool_modify.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_modify);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ��������ݽ��в���
		String[] params = { "������", "��������", "��������", "��������" };
		Vector<Vector> vec = new Vector<>();

		// ��װ���ҵ�������
		try {
			vec = readerClassifyService.pack(readerClassifyService
					.findAllReaderClassify());
		} catch (SQLException e) {
			e.printStackTrace();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tool_modify) {
			// ��ȡ��ǰѡ��Ҫ�޸ĵ�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵĶ��߷����¼");
			} else {
				// �޸Ķ��߷�����Ϣ
				new UpdateReaderClassifyJFrame(this, table, row);
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
}