package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.guigu.library.back.ui.function.ShowLRSCartogramJFrame;
import com.guigu.library.model.Users;

public class InfoStatisticsJPanel implements MouseListener {
	// ����ȫ�����
	public JPanel backgroundPanel;
	JLabel monthlyLRS, quarterlyLRS, historyLRS, more;
	
	Users user;

	public InfoStatisticsJPanel(Users user) {
		this.user = user;
		initBackgroundPanel();
	}

	/**
	 * ��ʼ��������壬�����ĸ���ť�ֱ�������ʾ��ͬ����Ϣͳ������
	 */
	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		Box hor1 = Box.createHorizontalBox();
		monthlyLRS = new JLabel();
		monthlyLRS.setToolTipText("���½軹ͳ��");// ����ͳ��ͼ
		Icon monthlyLRS_icon = new ImageIcon("icons/buttonImage/monthlyLRS.png");
		monthlyLRS.setIcon(monthlyLRS_icon);
		monthlyLRS.addMouseListener(this);

		quarterlyLRS = new JLabel();
		quarterlyLRS.setToolTipText("���Ƚ軹ͳ��");// ����ͳ��ͼ
		Icon quarterlyLRS_icon = new ImageIcon(
				"icons/buttonImage/quarterlyLRS.png");
		quarterlyLRS.setIcon(quarterlyLRS_icon);
		quarterlyLRS.addMouseListener(this);
		hor1.add(monthlyLRS);
		hor1.add(Box.createHorizontalStrut(125));
		hor1.add(quarterlyLRS);

		Box hor2 = Box.createHorizontalBox();
		historyLRS = new JLabel();
		historyLRS.setToolTipText("��Ƚ軹ͳ��");// ����ͳ��ͼ
		Icon historyLRS_icon = new ImageIcon("icons/buttonImage/historyLRS.png");
		historyLRS.setIcon(historyLRS_icon);
		historyLRS.addMouseListener(this);

		more = new JLabel();
		more.setToolTipText("��������");
		Icon more_icon = new ImageIcon("icons/buttonImage/more.png");
		more.setIcon(more_icon);
		more.addMouseListener(this);

		hor2.add(historyLRS);
		hor2.add(Box.createHorizontalStrut(125));
		hor2.add(more);

		Box ver = Box.createVerticalBox();
		ver.add(hor1);
		ver.add(Box.createVerticalStrut(125));
		ver.add(hor2);

		backgroundPanel.add(ver, BorderLayout.CENTER);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == monthlyLRS) {
			new ShowLRSCartogramJFrame(this,1);
		} else if (e.getSource() == quarterlyLRS) {
			new ShowLRSCartogramJFrame(this,2);
		} else if (e.getSource() == historyLRS) {
			new ShowLRSCartogramJFrame(this,3);
		} else if (e.getSource() == more) {
			JOptionPane.showMessageDialog(null, "��û׼���ã�������ģ�飡��");
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
