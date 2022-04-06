package com.design.sm.fore.ui.function;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.design.sm.fore.ui.control.BaseDataJPanel;
import com.design.sm.fore.ui.control.ProductManagerJPanel;
import com.design.sm.fore.ui.control.StockManagerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.utils.MyFont;

public class ProductPurchaseJFrame extends JFrame {

	// ����ȫ�����
	JPanel backgroundPanel;
	// ���常���
	StockManagerJPanel parentPanel;
	JTabbedPane jTabbedPane;
	Accounts loginUser;
	public ProductPurchaseJFrame(StockManagerJPanel parentPanel,Accounts loginUser) {
		this.parentPanel = parentPanel;
		this.loginUser = loginUser;
		initBackgroundPanel();
		this.add(backgroundPanel);
		// ���ô����С
		this.setTitle("��Ʒ�ɹ�");
		this.setSize(1000, 550);
		this.setLocationRelativeTo(null);// ����������
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		// ����Tab������λ��
		jTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ���ò��֡�ͳһ����
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		jTabbedPane.setFont(MyFont.JLabelFont);
		/**
		 *  �����ص�����(�ɹ���Ʒ���ɹ���¼�����Ԥ��������վ���ල����)
		 *  �ɹ���Ʒ���ṩ��Ʒ��Ϣ��ģ����ѯ���ܡ����Ԥ������
		 *  �ɹ��嵥������Ҫ��������Ʒ����ɹ��嵥����ѡ��ť��ϣ�δ����0   �Ѵ���1
		 *  �ɹ���¼����ѡ��ť��ϣ�δ�������ύ����δ��ˣ�   �Ѵ����ύ��ͨ����ˣ�  ��Ч������δͨ����ˣ�
		 *  �˻���
		 *  ����վ��ɾ���ɹ���¼���������վ--ɾ����ʶΪ1������ǳ���ɾ������Ӧ�ĵĲɹ���ϸҲ�Ǽ���ɾ����
		 *  
		 */
		jTabbedPane.addTab("�ɹ���Ʒ", new ProductPurchaseJPanel(loginUser).backgroundPanel);// ��Ӳɹ���Ʒҳ��
		jTabbedPane.addTab("�ɹ��嵥", new PurchaseListJPanel(loginUser).backgroundPanel);// ��Ӳɹ��嵥ҳ��
		jTabbedPane.addTab("�ɹ���¼", new PurchaseRecordJPanel(loginUser).backgroundPanel);// ��Ӳɹ���¼ҳ��
		jTabbedPane.addTab("����վ", new StockInRecycleBinJPanel(loginUser).backgroundPanel);// ��ӻ���վҳ��
		backgroundPanel.add(jTabbedPane, BorderLayout.CENTER);
	}
	
}
