package com.design.sm.fore.ui.control;

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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.fore.ui.function.AddCustomerJFrame;
import com.design.sm.fore.ui.function.UpdateCustomerJFrame;
import com.design.sm.model.Accounts;
import com.design.sm.service.CustomerService;
import com.design.sm.service.impl.CustomerServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class CustomerManagerJPanel implements MouseListener,FocusListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel  label_keyword,tool_add, tool_update, tool_delete,tool_transaction;
	// ������Ӧ���ı�����ϰ�ť
	JTextField keyword;
	JButton search;
	// ������Ӧ��service
	CustomerService customerService = new CustomerServiceImpl();
	
	Accounts loginUser;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public CustomerManagerJPanel(Accounts loginUser) {
		this.loginUser = loginUser;
		backgroundPanel = new JPanel(new BorderLayout());
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
		// �����ɾ�Ĺ���
		Icon icon_add = new ImageIcon("icons/toolImage/add.png");
		tool_add = new JLabel(icon_add);
		tool_add.setToolTipText("��ӹ˿���Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_update = new ImageIcon("icons/toolImage/modify.png");
		tool_update = new JLabel(icon_update);
		tool_update.setToolTipText("�޸Ĺ˿���Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_update.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ���˿���Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������
		
		Icon icon_transaction = new ImageIcon("icons/toolImage/transaction.png");
		tool_transaction = new JLabel(icon_transaction);
		tool_transaction.setToolTipText("���׼�¼");// ��������ƶ�ʱ����ʾ����
		tool_transaction.addMouseListener(this);// ���������
		
		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_add);
		toolPanel.add(tool_update);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_transaction);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ�����ݹ˿����ƹؼ��ֲ���
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		// ���ùؼ��ֲ���
		label_keyword = new JLabel("�˿Ͳ���");
		keyword = new JTextField(20);
		keyword.setText("������˿����ƻ��ֻ���");
		keyword.addFocusListener(this);
		search = new JButton("����");
		search.addMouseListener(this);
		searchPanel.add(label_keyword);
		searchPanel.add(keyword);
		searchPanel.add(search);
		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {
		// Ҫ�����������ѡ�����ɸѡ����(Ҫ����productService�з��ص��н������ã����ѡ��Ҫ���ص���Ŀ����)
		String[] params = { "�˿�id", "�˿�", "��ַ","��ϵ��ʽ", "��������", "����", "���","���ѵȼ�id","���ѵȼ�"};
		Vector<Vector> vec = new Vector<>();
		// ��ȡ��ǰ�ı����������Ϣ�������ı�����������
		String keyword_string = keyword.getText();
		if(!keyword_string.equals("������˿����ƻ��ֻ���")){
			String text = "%"+keyword_string+"%";
			try {
				vec = customerService.pack(customerService.getCustomerByKeyword(text));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(keyword_string.equals("������˿����ƻ��ֻ���")){
			try {
				vec = customerService.pack(customerService.findAllCustomer());
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
		// ���أ�0 
		 dcm.getColumn(7).setMinWidth(0);
		 dcm.getColumn(7).setMaxWidth(0);

		// ���ù�����
		jScrollPane = new JScrollPane(table);
		Tools.setJspStyle(jScrollPane);

		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setOpaque(false);// ����͸����
		tablePanel.add(jScrollPane);
		// ��������ص�������
		backgroundPanel.add(tablePanel, BorderLayout.CENTER);
		// ��ˢ�����ݵ�ʱ��ı䴰���С���������ˢ��
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==search){
			// ˢ�±���������
			tablePanel.removeAll();
			initTablePanel();
			backgroundPanel.validate();
		}else if (e.getSource() == tool_add) {
			// ��ӹ˿���Ϣ
			new AddCustomerJFrame(this);
		} else if (e.getSource() == tool_update) {
			// ��ȡ��ǰѡ��Ҫ����������
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵĹ˿���Ϣ");
			} else {
				// �޸Ĺ˿���Ϣ
				new UpdateCustomerJFrame(this,table,row);
			}
		} else if (e.getSource() == tool_delete) {
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���Ĺ˿���Ϣ");
			} else {
				// ɾ���˿���Ϣ
				int choose = JOptionPane.showConfirmDialog(null, "ȷ��ɾ���ù˿���Ϣ��");
				if(choose==0){
					try {
						customerService.deleteCustomer(table.getValueAt(row, 0).toString());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "�˿���Ϣɾ���ɹ���");
					//ˢ���������
					this.refreshTablePanel();
				}
			}
		} else if(e.getSource()==tool_transaction){
			// �鿴�˿ͽ��׼�¼
		}
	}

	/**
	 * ˢ���������
	 */
	public void refreshTablePanelBySearch() {
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

	/**
	 * �۽��¼�
	 */
	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("������˿����ƻ��ֻ���")) {
				keyword.setText("");
				keyword.setFont(MyFont.JTextFieldFont);
				keyword.setForeground(MyColor.JTextFieldColor);
			}
		}
	}

	/**
	 * ʧȥ�����¼�
	 */
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == keyword) {
			if (keyword.getText().equals("")) {
				keyword.setText("������˿����ƻ��ֻ���");
				keyword.setFont(MyFont.TipFont);
				keyword.setForeground(MyColor.TipColor);
			}
		}
	}
	
	/**
	 * ˢ���������
	 */
	public void refreshTablePanel(){
		tablePanel.removeAll();
		initTablePanel();
	}

}
