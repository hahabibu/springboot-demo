package com.design.sm.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.design.sm.fore.ui.function.UpdateConsumeClassJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.service.ConsumeClassService;
import com.design.sm.service.impl.ConsumeClassServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.Tools;

public class ConsumeClassManagerJPanel implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel tool_add, tool_modify, tool_delete;
	// ������Ӧ��service
	private ConsumeClassService consumeClassService = new ConsumeClassServiceImpl();

	private Accounts user;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public ConsumeClassManagerJPanel(Accounts user) {
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

		// �����ɾ�Ĺ���
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("�������ѵȼ�");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("�޸����ѵȼ�");// ��������ƶ�ʱ����ʾ����
		tool_modify.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ�����ѵȼ�");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_add);
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// ��������ݽ��в���
		String[] params = { "�ȼ�id", "�ȼ�����", "�ȼ��Ż�", "�ȼ��ۿ�" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = consumeClassService.pack(consumeClassService
					.findAllConsumeClassList());
		} catch (SQLException e) {
			e.printStackTrace();
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
		if (e.getSource() == tool_add) {
			// �������ѵȼ���Ϣ
			/**
			 * ���ڴ˴���������ѵȼ�������id����Ϊnumber(1),�������Ƶ�ʱ��
			 * �㲻������ӷ�����Ĭ��Ϊ��������ѵȼ�����ͨ����Ӧ���龰�����޸Ĳ���
			 */
		} else if (e.getSource() == tool_modify) {
			// ��ȡ��ǰѡ��Ҫ�޸ĵ�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ����ѵȼ���¼");
			} else {
				// �޸����ѵȼ���Ϣ
				 new UpdateConsumeClassJFrame(this, table, row);
			}
		} else if (e.getSource() == tool_delete) {
			// ɾ����ǰѡ�е�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�������ѵȼ���Ϣ");
			} else {
				// ��ȡ��ǰѡ�з����id
				String id = table.getValueAt(row, 0).toString();
				int result = JOptionPane.showConfirmDialog(null,
						"ȷ��ɾ���������ѵȼ���Ϣ��");
				if (result == 0) {
					// ȷ��ɾ������ִ��ɾ��
					try {
						consumeClassService.deleteConsumeClass(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "���ѵȼ���Ϣɾ���޸ĳɹ���");
					refreshTablePanel();
				}
			}
		}
	}

	/**
	 * ˢ���������
	 */
	public void refreshTablePanel() {
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

}
