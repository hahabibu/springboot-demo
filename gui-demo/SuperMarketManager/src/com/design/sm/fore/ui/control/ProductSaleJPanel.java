package com.design.sm.fore.ui.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.design.sm.fore.ui.function.CashierJFrame;
import com.design.sm.model.Accounts;

public class ProductSaleJPanel implements MouseListener{
	// ����ȫ�����
	public JPanel backgroundPanel;
	JLabel cashier,shift;
	Accounts loginUser;

	public ProductSaleJPanel(Accounts loginUser) {
		this.loginUser = loginUser;
		initBackgroundPanel();
	}

	/**
	 * ��ʼ��������壬������������ť�ֱ����Խ��뵽��ͬ�Ĺ������
	 */
	private void initBackgroundPanel() {
		backgroundPanel = new JPanel();
		Box hor = Box.createHorizontalBox();
		Icon cashier_icon = new ImageIcon("icons/toolImage/cashier.png");
		cashier = new JLabel(cashier_icon);
		cashier.setToolTipText("����̨");
		cashier.addMouseListener(this);
		
		Icon shift_icon = new ImageIcon("icons/toolImage/shift.png");
		shift = new JLabel(shift_icon);
		shift.setToolTipText("����");
		shift.addMouseListener(this);
		hor.add(cashier);
		hor.add(Box.createHorizontalStrut(125));
		hor.add(shift);

		backgroundPanel.add(hor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == cashier) {
			new CashierJFrame(this,this.loginUser);
		}
		 else if (e.getSource() == shift) {
			 // Ա����¼�л�
			new ForeLoginJFrame();
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
