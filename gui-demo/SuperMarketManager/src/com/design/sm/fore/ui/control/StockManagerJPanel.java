package com.design.sm.fore.ui.control;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.design.sm.fore.ui.function.ProductPurchaseJFrame;
import com.design.sm.fore.ui.function.VendorManagerJFrame;
import com.design.sm.fore.ui.function.WarehouseManagerJFrame;
import com.design.sm.model.Accounts;

public class StockManagerJPanel implements MouseListener{
	// ����ȫ�����
		public JPanel backgroundPanel;
		JLabel purchase, reject, warehouse_manager, vendor_manager;
		Accounts loginUser;

		public StockManagerJPanel(Accounts loginUser) {
			this.loginUser = loginUser;
			initBackgroundPanel();
		}

		/**
		 * ��ʼ��������壬�����ĸ���ť�ֱ����Խ��뵽��ͬ�Ĺ������
		 */
		private void initBackgroundPanel() {
			backgroundPanel = new JPanel();
			Box hor1 = Box.createHorizontalBox();
			purchase = new JLabel();
			purchase.setToolTipText("��Ʒ�ɹ�");
			Icon purchase_icon = new ImageIcon("icons/buttonImage/purchase.png");
			purchase.setIcon(purchase_icon);
			purchase.addMouseListener(this);
			
			reject = new JLabel();
			reject.setToolTipText("��Ʒ�˻�");
			Icon reject_icon = new ImageIcon("icons/buttonImage/reject.png");
			reject.setIcon(reject_icon);
			reject.addMouseListener(this);
			hor1.add(purchase);
			hor1.add(Box.createHorizontalStrut(125));
			hor1.add(reject);

			Box hor2 = Box.createHorizontalBox();
			warehouse_manager = new JLabel();
			warehouse_manager.setToolTipText("�ֿ����");
			Icon warehouse_icon = new ImageIcon("icons/buttonImage/warehouse.png");
			warehouse_manager.setIcon(warehouse_icon);
			warehouse_manager.addMouseListener(this);
			
			vendor_manager = new JLabel();
			vendor_manager.setToolTipText("��Ӧ�̹���");
			Icon vendor_icon = new ImageIcon("icons/buttonImage/vendor.png");
			vendor_manager.setIcon(vendor_icon);
			vendor_manager.addMouseListener(this);

			hor2.add(warehouse_manager);
			hor2.add(Box.createHorizontalStrut(125));
			hor2.add(vendor_manager);

			Box ver = Box.createVerticalBox();
			ver.add(hor1);
			ver.add(Box.createVerticalStrut(125));
			ver.add(hor2);
			
			backgroundPanel.add(ver);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == purchase) {
				new ProductPurchaseJFrame(this,this.loginUser);
			} else if (e.getSource() == reject) {
//				new ProductRejectJFrame(this.loginUser);
				JOptionPane.showMessageDialog(null, "��û׼���ã�������ģ�飡��");
			} else if (e.getSource() == warehouse_manager) {
				new WarehouseManagerJFrame(this);
			} else if (e.getSource() == vendor_manager) {
				new VendorManagerJFrame(this);
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
}
