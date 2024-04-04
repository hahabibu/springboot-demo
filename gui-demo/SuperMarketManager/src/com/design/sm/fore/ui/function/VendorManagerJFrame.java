package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

import com.design.sm.fore.ui.control.StockManagerJPanel;
import com.design.sm.service.VendorService;
import com.design.sm.service.impl.VendorServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;

public class VendorManagerJFrame extends JFrame implements MouseListener,FocusListener {

	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, searchPanel, tablePanel,
			pagePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel  label_keyword,tool_add, tool_update, tool_delete,
	tool_provide,tool_contact,tool_export,tool_transaction;
	// ������Ӧ���ı�����ϰ�ť
	JTextField keyword;
	JButton search;
	// ������Ӧ��service
	VendorService vendorService = new VendorServiceImpl();
	
	StockManagerJPanel parentPanel;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public VendorManagerJFrame(StockManagerJPanel parentPanel) {
		backgroundPanel = new JPanel(new BorderLayout());
		this.parentPanel = parentPanel;
		// ��ʼ������
		initTopPanel();// ��ʼ�������˵���
		initTablePanel();// ��ʼ����ʾ�ı������
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("��Ӧ�̹���");
		this.setSize(1000, 550);
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
		tool_add.setToolTipText("��ӹ�Ӧ����Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_add.addMouseListener(this);// ���������

		Icon icon_update = new ImageIcon("icons/toolImage/modify.png");
		tool_update = new JLabel(icon_update);
		tool_update.setToolTipText("�޸Ĺ�Ӧ����Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_update.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ����Ӧ����Ϣ");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		
		Icon icon_provide = new ImageIcon("icons/toolImage/provide.png");
		tool_provide = new JLabel(icon_provide);
		tool_provide.setToolTipText("��Ӧ��Ʒ");// ��������ƶ�ʱ����ʾ����
		tool_provide.addMouseListener(this);// ���������
		
		Icon icon_contact = new ImageIcon("icons/toolImage/contact.png");
		tool_contact = new JLabel(icon_contact);
		tool_contact.setToolTipText("��ϵ��");// ��������ƶ�ʱ����ʾ����
		tool_contact.addMouseListener(this);// ���������
		
		Icon icon_export = new ImageIcon("icons/toolImage/export.png");
		tool_export = new JLabel(icon_export);
		tool_export.setToolTipText("��������");// ��������ƶ�ʱ����ʾ����
		tool_export.addMouseListener(this);// ���������
		
		Icon icon_transaction = new ImageIcon("icons/toolImage/transaction.png");
		tool_transaction = new JLabel(icon_transaction);
		tool_transaction.setToolTipText("���׼�¼");// ��������ƶ�ʱ����ʾ����
		tool_transaction.addMouseListener(this);// ���������
		
		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_add);
		toolPanel.add(tool_update);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_provide);
		toolPanel.add(tool_contact);
		toolPanel.add(tool_export);
		toolPanel.add(tool_transaction);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ��������� ���ò��ҷ�ʽ�����ݹ�Ӧ�����ƹؼ��ֲ���
	 */
	private void initSearchPanel() {
		searchPanel = new JPanel();
		// ���ùؼ��ֲ���
		label_keyword = new JLabel("��Ӧ�̲���");
		keyword = new JTextField(20);
		keyword.setText("�����빩Ӧ������");
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
		String[] params = { "��Ӧ��id", "��Ӧ��", "��ϵ��ʽ", "��������", "�����", "��˾��ַ"};
		Vector<Vector> vec = new Vector<>();
		// ��ȡ��ǰ�ı����������Ϣ�������ı�����������
		String keyword_string = keyword.getText();
		if(!keyword_string.equals("�����빩Ӧ������")){
			String text = "%"+keyword_string+"%";
			try {
				vec = vendorService.getVendorByNameKeyword(text);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(keyword_string.equals("�����빩Ӧ������")){
			try {
				vec = vendorService.findAllVendorVector();
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
		// dcm.getColumn(0).setMinWidth(0);
		// dcm.getColumn(0).setMaxWidth(0);

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
			// ��ӹ�Ӧ����Ϣ
			new AddVendorJFrame(this);
		} else if (e.getSource() == tool_update) {
			// ��ȡ��ǰѡ��Ҫ����������
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵĹ�Ӧ����Ϣ");
			} else {
				// �޸Ĺ�Ӧ����Ϣ
				new UpdateVendorJFrame(this,table,row);
			}
		} else if (e.getSource() == tool_delete) {
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���Ĺ�Ӧ����Ϣ");
			} else {
				// ɾ����Ӧ����Ϣ
				int choose = JOptionPane.showConfirmDialog(null, "ȷ��ɾ���ù�Ӧ����Ϣ��");
				if(choose==0){
					try {
						vendorService.deleteVendor(table.getValueAt(row, 0).toString());
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "��Ӧ����Ϣɾ���ɹ���");
					//ˢ���������
					this.refreshTablePanel();
				}
			}
		} else if(e.getSource()==tool_provide){
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�鿴�Ĺ�Ӧ��");
			} else {
				new ShowVendorProvideProductJFrame(this,table,row);
			}
		}else if(e.getSource()==tool_contact){
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�鿴�Ĺ�Ӧ��");
			} else {
				// ���������Ϲ���
				new VendorContactManagerJFrame(this,table,row);
			}
		}else if (e.getSource() == tool_export) {
			// ��ȡ��ǰѡ�е�����
			String[] ids;
			ArrayList id_list = new ArrayList<>();
			for (int rowindex : table.getSelectedRows()) {
				Object obj = table.getValueAt(rowindex, 0);
				id_list.add(obj);
			}
			// ����ת����
			ids = (String[]) id_list.toArray(new String[id_list.size()]);
			int result=0;
			try {
				result = vendorService.exportData(ids);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "���ݵ����ɹ���");
			} else if (result == -1) {
				JOptionPane.showMessageDialog(null, "��Ǹ�����ݵ���ʧ�ܣ�����һ��ɣ�");
			} else if (result == 0) {
				JOptionPane.showMessageDialog(null, "�û�ȡ���˲�����");
			}
		}else if(e.getSource()==tool_transaction){
			// �鿴��Ӧ�̽��׼�¼
			int row = table.getSelectedRow();
			if(row<0){
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�鿴�Ĺ�Ӧ����Ϣ��");
			}else{
				new ShowVendorTransactionJFrame(this,this.table,row);
			}
			
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
			if (keyword.getText().equals("�����빩Ӧ������")) {
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
				keyword.setText("�����빩Ӧ������");
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
