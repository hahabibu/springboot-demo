package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;

import com.design.sm.model.Accounts;
import com.design.sm.model.Product;
import com.design.sm.model.PurchaseNote;
import com.design.sm.model.StockOrder;
import com.design.sm.service.AccountsService;
import com.design.sm.service.ProductService;
import com.design.sm.service.PurchaseNoteService;
import com.design.sm.service.StockMasterService;
import com.design.sm.service.StockOrderService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.PurchaseNoteServiceImpl;
import com.design.sm.service.impl.StockMasterServiceImpl;
import com.design.sm.service.impl.StockOrderServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.Tools;
import com.eltima.components.ui.DatePicker;

public class PurchaseRecordJPanel implements MouseListener, ItemListener {
	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel, searchPanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel tool_delete, tool_verify, tool_detail;
	ButtonGroup sm_state;
	JRadioButton committed, pass, cancel;
	DatePicker start_time, end_time;
	JButton search;
	// ������Ӧ��service
	StockMasterService stockMasterService = new StockMasterServiceImpl();
	StockOrderService stockOrderService = new StockOrderServiceImpl();
	ProductService productService = new ProductServiceImpl();
	PurchaseNoteService purchaseNoteService = new PurchaseNoteServiceImpl();
	AccountsService accountsService = new AccountsServiceImpl();
	Accounts loginUser;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public PurchaseRecordJPanel(Accounts loginUser) {
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

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ����¼");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		Icon icon_verify = new ImageIcon("icons/toolImage/verify.png");
		tool_verify = new JLabel(icon_verify);
		tool_verify.setToolTipText("״̬�޸�");// ��������ƶ�ʱ����ʾ����
		tool_verify.addMouseListener(this);// ���������

		Icon icon_detail = new ImageIcon("icons/toolImage/detail.png");
		tool_detail = new JLabel(icon_detail);
		tool_detail.setToolTipText("������ϸ");// ��������ƶ�ʱ����ʾ����
		tool_detail.addMouseListener(this);// ���������

		sm_state = new ButtonGroup();
		committed = new JRadioButton("���ύ");
		committed.addItemListener(this);
		pass = new JRadioButton("ͨ�����");
		pass.addItemListener(this);
		cancel = new JRadioButton("���ʧ��");
		cancel.addItemListener(this);
		sm_state.add(committed);
		sm_state.add(pass);
		sm_state.add(cancel);

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_delete);
		toolPanel.add(tool_verify);
		toolPanel.add(tool_detail);
		toolPanel.add(committed);
		toolPanel.add(pass);
		toolPanel.add(cancel);
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
		start_time = new DatePicker();
		start_time.setPreferredSize(new Dimension(150, 30));
		start_time.addMouseListener(this);
		Icon end_icon = new ImageIcon("icons/toolImage/start_time.png");
		JLabel label_end = new JLabel(end_icon);
		end_time = new DatePicker();
		end_time.setPreferredSize(new Dimension(150, 30));
		end_time.addMouseListener(this);

		search = new JButton("����");
		search.addMouseListener(this);

		searchPanel.add(label_start);
		searchPanel.add(start_time);
		searchPanel.add(label_end);
		searchPanel.add(end_time);
		searchPanel.add(search);

		// �����ֺõ�������ص��˵����������
		topPanel.add(searchPanel, BorderLayout.EAST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ������������ɸѡָ����Χ�ڵ����ж�����Ϣ,ˢ���������
		// ��������ݽ��в���
		String[] params = { "��������id", "�������", "������id", "������", "��Ӧ��id", "��Ӧ��",
				"��Ӧ����ϵ��id", "��ϵ��", "����ʱ��", "������ʶ", "��/���", "ɾ����ʶ", "ɾ����ʶ����",
				"״̬��ʶ", "����״̬" };
		Vector<Vector> vec = new Vector<>();
		// �ж�������������ڸ�ʽ�Ƿ�Ϸ�
		String start_time_string = start_time.getText();
		String end_time_string = end_time.getText();
		if (!(start_time_string.equals("") && end_time_string.equals(""))) {
			try {
				if (committed.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, 0));// ���ҵ�ǰ�������ύ��δ����ļ�¼
				} else if (pass.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, 1));// ���ҵ�ǰ����ͨ����˵ļ�¼
				} else if (cancel.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, -1));// ���ҵ�ǰ����δͨ����˵ļ�¼
				} else {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockInList());// ���ҵ�ǰ��������¼
				}
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
			// ���в���
			try {
				if (committed.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, 0));// ���ҵ�ǰ�������ύ��δ����ļ�¼
				} else if (pass.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, 1));// ���ҵ�ǰ����ͨ����˵ļ�¼
				} else if (cancel.isSelected()) {
					vec = stockMasterService.pack(stockMasterService
							.findAllStockListByTimeUnionState(0,
									start_time_string, end_time_string, -1));// ���ҵ�ǰ����δͨ����˵ļ�¼
				} else {
					// �˴���Ĭ�ϲ������е�����
					vec = stockMasterService.pack(stockMasterService
							.findAllStockInList());// ���ҵ�ǰ��������¼
				}
			} catch (SQLException ee) {
				ee.printStackTrace();
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
		// ���� 2 4 6 9 11 13
		dcm.getColumn(2).setMinWidth(0);
		dcm.getColumn(2).setMaxWidth(0);
		dcm.getColumn(4).setMinWidth(0);
		dcm.getColumn(4).setMaxWidth(0);
		dcm.getColumn(6).setMinWidth(0);
		dcm.getColumn(6).setMaxWidth(0);
		dcm.getColumn(9).setMinWidth(0);
		dcm.getColumn(9).setMaxWidth(0);
		dcm.getColumn(11).setMinWidth(0);
		dcm.getColumn(11).setMaxWidth(0);
		dcm.getColumn(12).setMinWidth(0);
		dcm.getColumn(12).setMaxWidth(0);
		dcm.getColumn(13).setMinWidth(0);
		dcm.getColumn(13).setMaxWidth(0);

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
		if (e.getSource() == tool_delete) {
			// ɾ����ǰѡ�е�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���Ķ�����¼");
			} else {
				// ��ȡ��ǰѡ�вֿ��id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��������¼��Ϣ��");
				if (result == 0) {
					// ȷ��ɾ������ִ��ɾ��
					try {
						stockMasterService.joinRecycleBin(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "������¼�ѷ��û���վ��");
					this.refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_verify) {
			// �޸ĵ�ǰѡ�в鿴�Ķ���״̬�����ݲ�ͬ��ҳ�������ͬ����Ϣ��ʾ
			if (committed.isSelected()) {
				// ͨ���޸����ύ�Ķ���״̬���Ӷ�ִ����Ӧ��������:���������޸�״̬��ͨ������ͨ��
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫִ�иò����Ķ�����¼��");
				} else {
					/**
					 *  ֻ�вֿ����߲���Ա�����ǲ�����Ա����Ȩ���ж��Ƿ�ͨ��������ˣ�
					 *  �����ڵ�¼�����Ѿ������˽���ֿ�����Ա��ֻ������������Ա��������
					 *  ϵͳά����Ա������˴˴�ֻ��Ҫͨ���жϵ�ǰ�˺ŵ�Ȩ���Ƿ�Ϊ����������Ա--1��
					 *  �򡰾��������--2�������ܹ���˶�����������ʾû��Ȩ�ޣ�
					 */
					// ��ȡ�˺�Ȩ��
					int limits = 0;
					try {
						limits = Integer.valueOf(String.valueOf(accountsService.getAccountsLimits(this.loginUser.getAccount_id())));
					} catch (NumberFormatException e2) {
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if(limits==1||limits==2){
						try {
							Object[] options = { "ͨ�����", "��˲�ͨ��", "ȡ��" };
							int choose = JOptionPane.showOptionDialog(null,
									"��ѡ����Ӧ�Ĳ���", "����״̬�޸�",
									JOptionPane.DEFAULT_OPTION,
									JOptionPane.INFORMATION_MESSAGE, null, options,
									options[1]);
							if (choose == 0) {
								// ���ݶ��������еĶ�����Ϣ���޸Ŀ����Ϣ����ʾ��Ʒ�������
								String smId = table.getValueAt(row, 0).toString();
								List<StockOrder> list_order = stockOrderService
										.getStockOrderBySMId(smId);
								for (int i = 0; i < list_order.size(); i++) {
									// ������Ʒid�޸���Ʒ�Ŀ����Ŀ
									String prodId = list_order.get(i)
											.getProduct_id();
									int num = list_order.get(i).getQuantity();
									Product p = productService.getProduct(prodId);
									p.setCurrent_stock(p.getCurrent_stock() + num);// �޸ĵ�ǰ��Ʒ���
									productService.updateProduct(p);// ִ���޸Ĳ���
								}
								// �޸Ķ���״̬
								stockMasterService.passStockMaster(smId);
								JOptionPane.showMessageDialog(null,
										"������ͨ����ˣ���Ʒ�������ֿ�");

								// ����ͨ���������ӽ�����¼
								Object[] option = { "�ֽ�֧��", "ת��֧��" };
								int i = JOptionPane.showOptionDialog(null,
										"��ѡ���׷�ʽ��", "����ִ��",
										JOptionPane.DEFAULT_OPTION,
										JOptionPane.INFORMATION_MESSAGE, null,
										option, option[1]);
								String order_num = stockMasterService
										.getSMOrderNumById(table.getValueAt(row, 0)
												.toString());
								// ��ȡ������Ϣ����������ͳ��
								double actual_amount = this.getAmount(row);
								PurchaseNote pn = new PurchaseNote(order_num,
										actual_amount, (i + 1));
								purchaseNoteService.addPurchaseNote(pn);
								refreshTablePanel();
							} else if (choose == 1) {
								// ���ݶ��������еĶ�����Ϣ
								String smId = table.getValueAt(row, 0).toString();
								// �޸Ķ���״̬:������ͨ��
								stockMasterService.cancelStockMaster(smId);
								JOptionPane.showMessageDialog(null, "����δͨ����ˣ�");
								this.refreshTablePanel();
							}
						} catch (HeadlessException | SQLException e1) {
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(null, "��Ǹ��ֻ�о�������Ա������ִ�иò�����");
					}
				}
			} else if (pass.isSelected()) {
				// ���Բ������ͨ���Ķ���������ִ���κβ���
				JOptionPane.showMessageDialog(null, "�޷�ִ�иò�����");
			} else if (cancel.isSelected()) {
				// �������ʧ�ܵĶ������������ٴ��ύ
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫִ�иò����Ķ�����¼��");
				} else {
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ���ٴ��ύ�ö�����");
					if (choose == 0) {
						// �����޸Ķ���״̬���ٴ��ύ����
						String id = table.getValueAt(row, 0).toString();
						try {
							stockMasterService.commitStockMaster(id);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "�����ύ�ɹ���");
						this.refreshTablePanel();
					}
				}
			}
		} else if (e.getSource() == tool_detail) {
			// ��ʾ��������ϸ��Ϣ
			int row = table.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�鿴�Ķ�����Ϣ��");
			} else {
				new ShowStockInMasterDetail(this, this.loginUser, this.table,
						row);
			}
		} else if (e.getSource() == search) {
			// ���Ƴ����ݣ�������ˢ��
			backgroundPanel.remove(tablePanel);
			initTablePanel();
			backgroundPanel.validate();
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// ˢ���������
			tablePanel.removeAll();
			initTablePanel();
			backgroundPanel.validate();
		}
	}

	/**
	 * ���巽����ȡ����������ܶ�
	 */
	public double getAmount(int row) {
		double amount = 0.00;
		List<StockOrder> list = null;
		try {
			list = stockOrderService.getStockOrderBySMId(table.getValueAt(row,
					0).toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			StockOrder so = list.get(i);
			amount = so.getAmount();
		}
		return amount;
	}
}