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
import com.design.sm.model.Accounts;
import com.design.sm.model.Product;
import com.design.sm.model.Temp;
import com.design.sm.model.Warehouse;
import com.design.sm.service.ProductService;
import com.design.sm.service.TempService;
import com.design.sm.service.WarehouseService;
import com.design.sm.service.impl.ProductServiceImpl;
import com.design.sm.service.impl.TempServiceImpl;
import com.design.sm.service.impl.WarehouseServiceImpl;
import com.design.sm.utils.BaseTableModule;
import com.design.sm.utils.DataValidation;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;
import com.design.sm.utils.RandomGeneration;
import com.design.sm.utils.Tools;

public class PurchaseListJPanel implements MouseListener {
	// ����ȫ�����
	JPanel backgroundPanel, topPanel, toolPanel, tablePanel;
	// ������������ɱ�����ݵķ�װ
	BaseTableModule baseTableModule;
	JTable table;
	JScrollPane jScrollPane;
	// �����õ��ı�ǩ
	JLabel label_keyword, tool_modify, tool_delete, tool_commit;
	// ������Ӧ��service
	ProductService productService = new ProductServiceImpl();
	TempService tempService = new TempServiceImpl();
	Accounts loginUser;

	/**
	 * ͨ�����췽����ɳ�ʼ��
	 */
	public PurchaseListJPanel(Accounts loginUser) {
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
		tool_modify.setToolTipText("�޸ļ�¼");// ��������ƶ�ʱ����ʾ����
		tool_modify.addMouseListener(this);// ���������

		Icon icon_delete = new ImageIcon("icons/toolImage/delete.png");
		tool_delete = new JLabel(icon_delete);
		tool_delete.setToolTipText("ɾ����¼");// ��������ƶ�ʱ����ʾ����
		tool_delete.addMouseListener(this);// ���������

		Icon icon_commit = new ImageIcon("icons/toolImage/commit.png");
		tool_commit = new JLabel(icon_commit);
		tool_commit.setToolTipText("�ύ����");// ��������ƶ�ʱ����ʾ����
		tool_commit.addMouseListener(this);// ���������

		// ����ʼ����ɵĹ��߼��ص������������
		toolPanel.add(tool_modify);
		toolPanel.add(tool_delete);
		toolPanel.add(tool_commit);
		// ���ս������������ص������˵�����������
		topPanel.add(toolPanel, BorderLayout.WEST);
	}

	/**
	 * ��ʼ����ʾ�ı������
	 */
	private void initTablePanel() {

		// ��������ݽ��в���
		String[] params = { "��Ʒid", "��Ʒ����", "��������", "��Ʒ����", "�ܶ�", "��Ӧ��id",
				"��Ӧ��" };
		Vector<Vector> vec = new Vector<>();
		try {
			vec = tempService.pack(tempService.findAllTempList());
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
		// ͨ�������еĴ�С������ĳһ�����ݣ�ֻ��ʾ��Ҫ��ʾ�����ݣ�
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) table
				.getColumnModel();
		// ���أ�5
		dcm.getColumn(5).setMinWidth(0);
		dcm.getColumn(5).setMaxWidth(0);

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
		if (e.getSource() == tool_modify) {
			// ��ȡ��ǰѡ��Ҫ�޸ĵ�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵļ�¼��Ϣ");
			} else {
				Product prod = null;
				try {
					prod = productService.getProduct(table.getValueAt(row, 0)
							.toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				double unit_price = Double.valueOf(table.getValueAt(row, 3)
						.toString());
				int current_stock_int = prod.getCurrent_stock();
				int safe_stock = prod.getSafe_stock();
				String value = JOptionPane.showInputDialog(null, "�������޸ĺ����Ŀ");
				// �����ָ���쳣
				if (value == null) {
					value = "";
				}
				if (DataValidation.isSignlessInteger(value)) {
					int num = Integer.valueOf(value);
					if (num <= 0) {
						JOptionPane.showMessageDialog(null, "��������������");
					} else if ((current_stock_int + num) > safe_stock) {
						int choose = JOptionPane.showConfirmDialog(null,
								"��ǰ��Ʒ�ӹ���ᳬ����ȫ�����Ŀ��ȷ�ϼ������в�����");
						if (choose == 0) {
							// ȷ�ϼ������в��������޸ļ�¼��Ϣ
							int amount = ((int) (unit_price * num * 100)) / 100;
							Temp t = new Temp(prod.getProd_id(), num,
									unit_price, amount);
							try {
								tempService.update(t);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							// ˢ���������
							this.refreshTablePanel();
						} else {
							// ȷ�ϼ������в��������޸ļ�¼��Ϣ
							int amount = ((int) (unit_price * num * 100)) / 100;
							Temp t = new Temp(prod.getProd_id(), num,
									unit_price, amount);
							try {
								tempService.update(t);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							// ˢ���������
							this.refreshTablePanel();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "��������������");
				}
			}
		} else if (e.getSource() == tool_delete) {
			// ɾ����ǰѡ�е�����
			int row = table.getSelectedRow();// �õ�ѡ�е���
			if (row < 0) {// û��ѡ���κ��վ�
				JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ���ļ�¼��Ϣ");
			} else {
				// ��ȡ��ǰѡ�м�¼��id
				String id = (String) table.getValueAt(row, 0);
				int result = JOptionPane.showConfirmDialog(null, "ȷ��ɾ��������¼��Ϣ��");
				if (result == 0) {
					// ȷ��ɾ������ִ��ɾ��
					try {
						tempService.deleteTemp(id);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					// ��ʾɾ���ɹ���Ϣ��壬��ˢ���������
					JOptionPane.showMessageDialog(null, "��¼ɾ���ɹ���");
					refreshTablePanel();
				}
			}
		} else if (e.getSource() == tool_commit) {
			/**
			 * ѡ����Ӧ�ļ�¼��Ϣ���˴��������ƣ�����Ϊͬһ����Ӧ���ṩ����Ʒ ������Ϊһ����Ч�Ķ��������ύ�����ܡ��ύ������ύ�ɹ����Ƴ�
			 * ��ǰѡ�еļ�¼�������ݼ�¼�����µĶ�����Ϣ ������Ҫ�������֮����ܹ����뵽��һ�׶ε���⣨������Ʒ�������У�
			 */
			// ��ȡ��ǰ�û�ѡ�е���,���鳤��Ϊ0˵���û�û��ѡ������
			 int[] selectRow = table.getSelectedRows();
			 if(selectRow.length==0){
				 JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�ύ�Ķ�����Ϣ��");
			 }else{
				 if(this.isValid()){
						new EnsurePurchaseOrderJFrame(this, this.loginUser,table,selectRow);
					}else{
						JOptionPane.showMessageDialog(null, "���Ĳ������Ϸ���������ͬһ����Ӧ�̣���");
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

	/**
	 * ��ȡ��ǰѡ�еļ�¼��Ϣ���ж��Ƿ�Ϊͬһ����Ӧ���ṩ����Ʒ
	 */
	public boolean isValid() {
		// ��ȡ��ǰѡ�е�����
		String[] ids;
		ArrayList id_list = new ArrayList<>();
		for (int rowindex : table.getSelectedRows()) {
			Object obj = table.getValueAt(rowindex, 0);
			id_list.add(obj);
		}
		// ����ת����
		ids = (String[]) id_list.toArray(new String[id_list.size()]);
		/**
		 *  ÿ����Ӧ�̵���Ϣ����һһ��ͬ����˴˴�ѡ���һ����Ʒid��Ϊ�ο����������һ����ͬ����Ϊ���Ϸ��Ĳ���
		 */
		try {
			Product prod = productService.getProduct(ids[0]);
			String key = prod.getVendor_id();
			for(String id : ids){
				Product p =productService.getProduct(id);
				if(!p.getVendor_id().equals(key))//�ַ������бȽ�
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
