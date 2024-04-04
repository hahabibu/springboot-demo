package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.design.sm.fore.ui.control.ProductManagerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.model.Category;
import com.design.sm.model.Units;
import com.design.sm.service.AccountsService;
import com.design.sm.service.CategoryService;
import com.design.sm.service.ProductService;
import com.design.sm.service.UnitsService;
import com.design.sm.service.WarehouseService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.CategoryServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.UnitsServiceImpl;
import com.design.sm.service.impl.WarehouseServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;
import com.design.sm.utils.Tools;

public class UnitsManagerJFrame extends JFrame implements FocusListener,
		MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_keyword, tool_add, tool_modify, tool_delete;
	JTextField keyword;
	JButton search_button;
	// ������Ӧ��service
	UnitsService unitsService = new UnitsServiceImpl();
	ProductManagerJPanel parentPanel;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public UnitsManagerJFrame(ProductManagerJPanel parentPanel) {
		this.parentPanel = parentPanel;
		backgroundPanel = new JPanel(new BorderLayout());
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initTablePanel();// ��ʼ����ʾ�ı������
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("��ӵ�λ��Ϣ");
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
		tool_add.setToolTipText("�½���λ");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_modify = new ImageIcon("icons/toolImage/modify.png");
		tool_modify = new JLabel(icon_modify);
		tool_modify.setToolTipText("�޸ĵ�λ");// ��������ƶ�ʱ����ʾ����
		tool_modify.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ����λ");// ��������ƶ�ʱ����ʾ����
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

		label_keyword = new JLabel("��Ʒ��λ");
		label_keyword.setFont(MyFont.JLabelFont);

		keyword = new JTextField(15);
		keyword.setFont(MyFont.TipFont);
		keyword.setForeground(MyColor.TipColor);
		keyword.setText("�����뵥λ�ؼ���");
		keyword.addFocusListener(this);

		search_button = new JButton("����");
		search_button.setFocusable(false);
		search_button.addMouseListener(this);

		// �����������ص�ָ���������
		searchPanel.add(label_keyword);
		searchPanel.add(keyword);
		searchPanel.add(search_button);
		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ��������ݽ��в���
		String[] params = { "��λid", "��λ����", "��λ��ʶ" };
		Vector<Vector> vec = new Vector<>();
		if (!keyword.getText().equals("�����뵥λ�ؼ���")) {
			try {
				// ƴ�Ӳ��ҵ��ַ���
				String text = "%" + keyword.getText() + "%";
				vec = unitsService.getUnitsByNameKeyword(text);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				vec = unitsService.findAllUnitsVector();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// ����ѯ�������ݷ�װ��BbaseTableModule��
		baseTableModule = new BaseTableModule(params, vec);
		table = new JTable(baseTableModule);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == search_button) {
			// �������˲���ѡ�������ɸѡ
			tablePanel.removeAll();// �Ƴ���������е���������
			initTablePanel();// ���³�ʼ�����
		}
		if (e.getSource() == tool_add) {
			String name = JOptionPane.showInputDialog(null, "�����뵥λ����");
			// ����������ڴ����ָ���쳣
			if (name == null) {
				name = "";
			}
			if (!name.equals("")) {
				int choose = JOptionPane.showConfirmDialog(null, "ȷ������������λ��Ϣ��");
				if (choose == 0) {
					// �������10λ������ַ���Ϊ��λid
					String id = RandomGeneration.getRandom10charSeq();
					Units u = new Units(id, name, 0);
					try {
						unitsService.addUnits(u);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾ��ӳɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "��λ��Ϣ��ӳɹ���");
					this.refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_modify) {
			// ��ȡ��ǰѡ��Ҫ�޸ĵ�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵĵ�λ��Ϣ");
			} else {
				String name = (String) table.getValueAt(row, 1);
				String newName = JOptionPane.showInputDialog(null,
						"�������޸ĺ�ĵ�λ����", name);
				// ����������ڴ����ָ���쳣
				if (newName == null) {
					newName = "";
				}
				if (!newName.equals("")) {
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ���޸�������λ��Ϣ��");
					if (choose == 0) {
						// �����ݱ��л�ȡ��ǰѡ�еĵ�λ��Ϣ�ĵ�λid
						String id = (String) table.getValueAt(row, 0);
						Units u = new Units(id, newName, 0);
						try {
							unitsService.updateUnits(u);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						// ��ʾ�޸ĳɹ���Ϣ��壬��ˢ���������
						JOptionPane.showMessageDialog(null, "��λ��Ϣ�޸ĳɹ���");
						this.refreshTablePanel();
					}
				}
			}
		} else if (e.getSource() == tool_delete) {
			// ɾ����ǰѡ�е�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���ĵ�λ��Ϣ");
			} else {
				// ��ȡ��ǰѡ�е�λ��id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��������λ��Ϣ��");
				if (result == 0) {
					// ȷ��ɾ������ִ��ɾ��
					try {
						unitsService.deleteUnits(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "��λ��Ϣɾ���ɹ���");
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

	// ��ȡ�����¼�
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("�����뵥λ�ؼ���")) {
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
				keyword.setText("�����뵥λ�ؼ���");
			} else {
				keyword.setForeground(MyColor.JTextFieldColor);
				keyword.setFont(MyFont.JTextFieldFont);
			}
		}
	}

}
