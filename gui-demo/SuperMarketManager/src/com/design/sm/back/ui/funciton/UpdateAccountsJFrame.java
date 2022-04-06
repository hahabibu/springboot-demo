package com.design.sm.back.ui.funciton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.design.sm.back.ui.control.AccountsMangerJPanel;
import com.design.sm.model.Accounts;
import com.design.sm.service.AccountsService;
import com.design.sm.service.impl.AccountsServiceImpl;
import com.design.sm.utils.MyColor;
import com.design.sm.utils.MyFont;

public class UpdateAccountsJFrame extends JFrame implements MouseListener{

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_id,label_name, label_password, label_limits;
	JTextField id,name, password;
	JComboBox limits;
	JButton save_button, reset_button;

	int user_limits = 0;
	AccountsService userService = new AccountsServiceImpl();

	// �õ���Ļ��С
	int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	// ���常���󡢵�ǰ������ݡ�ѡ�е�������
	AccountsMangerJPanel parentPanel;
	JTable table;
	int selectedRow;
	
	// ͨ�����췽����ʼ������
	public UpdateAccountsJFrame(AccountsMangerJPanel parentPanel,JTable table,int selectedRow) {
		this.parentPanel = parentPanel;
		this.table = table;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�޸��˺���Ϣ");
		this.setSize(425, 325);
		this.setVisible(true);
		this.setLocationRelativeTo(null);// ����������
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);// ���ùرշ�ʽ
		// ��ǰ�������أ���Ӱ������ݵ�ʹ�ã������ǹر���������
	}

	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		initTitlePanel();
		initContentPanel();
		initButtonPanel();
		backgroundPanel.add(titlePanel, BorderLayout.NORTH);
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * ��ʼ���������
	 */
	private void initTitlePanel() {
		titlePanel = new JPanel();
		JLabel title = new JLabel("�޸��˺���Ϣ");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {
		/**
		 * �޸��˺ŵ����ݣ� 
		 * �˺�id���������10λ���ַ����ݣ� ���ɸĶ�
		 * �û������û����롢�û�Ȩ��
		 */
		contentPanel = new JPanel();
		
		JPanel jp = new JPanel();
		label_id = new JLabel("�û�id  ");
		id = new JTextField(15);
		id.setFont(MyFont.TipFont);
		id.setForeground(MyColor.TipColor);
		id.setText(table.getValueAt(selectedRow, 0).toString());
		id.setEditable(false);
		jp.add(label_id);
		jp.add(id);
		
		JPanel jp1 = new JPanel();
		label_name = new JLabel("�û��ǳ�");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		jp1.add(label_name);
		jp1.add(name);

		JPanel jp2 = new JPanel();
		label_password = new JLabel("�û�����");
		password = new JTextField(15);
		password.setFont(MyFont.JTextFieldFont);
		jp2.add(label_password);
		jp2.add(password);

		JPanel jp3 = new JPanel();
		label_limits = new JLabel("�û�Ȩ��");
		limits = new JComboBox();
		limits.setPreferredSize(new Dimension(175, 30));
		// ��ʼ��������
		limits.addItem("����");
		limits.addItem("��������Ա");
		limits.addItem("���������");
		limits.addItem("��ͨԱ��");
		limits.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == limits) {
					if (limits.getSelectedIndex() == 0) {
						user_limits = 0;
					} else if (limits.getSelectedIndex() == 1) {
						user_limits = 1;
					} else if (limits.getSelectedIndex() == 2) {
						user_limits = 2;
					} else if (limits.getSelectedIndex() == 3) {
						user_limits = 3;
					}
				}
			}
		});
		jp3.add(label_limits);
		jp3.add(limits);

		//����������Ϣ
		this.echoData();
		
		Box ver = Box.createVerticalBox();
		ver.add(jp);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);

		// ��������ص�contentPanel�����
		contentPanel.add(ver);
	}

	/**
	 * ��ʼ����ť���
	 */
	private void initButtonPanel() {
		Box hor = Box.createHorizontalBox();
		buttonPanel = new JPanel();
		save_button = new JButton("����");
		save_button.setForeground(Color.white);
		save_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.blue));

		reset_button = new JButton("����");
		reset_button.setForeground(Color.white);
		reset_button.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.red));

		// ��Ӱ�ť����
		save_button.addMouseListener(this);
		reset_button.addMouseListener(this);

		// ����ť���ص������
		hor.add(save_button);
		hor.add(Box.createHorizontalStrut(20));
		hor.add(reset_button);
		buttonPanel.add(hor);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == save_button) {
			// ��ȡ��ǰ�ı����������Ϣ
			String name_string = name.getText();
			String password_string = password.getText();
			if (name_string.equals("")) {
				JOptionPane.showMessageDialog(null, "�û��ǳƲ���Ϊ��");
			} else if (password_string.equals("")) {
				JOptionPane.showMessageDialog(null, "�û����벻��Ϊ��");
			} else {
				try {
					if(userService.isValidAccountsname(name_string)){
						// ����Accounts���󣬽����ݱ��浽���ݿ���
						// �û�id�������׸ı䣬ͨ�������Ϣ��ȡ��ǰѡ������û�id
						String id = (String) table.getValueAt(selectedRow, 0);
						Accounts user = new Accounts(id, name_string, password_string,
								user_limits);
						int choose = JOptionPane.showConfirmDialog(null, "ȷ���޸��û���Ϣ��");
						if (choose == 0) {
							try {
								userService.updateAccounts(user);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							// ������ݵı���֮�������ʾ��Ϣ��������ҳ�����أ�ˢ������������
							JOptionPane.showMessageDialog(null, "�û���Ϣ�޸ĳɹ�");
							this.setVisible(false);
							parentPanel.refreshTablePanel();
						} else {
							this.setVisible(false);
						}
					}else{
						JOptionPane.showMessageDialog(null, "��ǰ�û��ǳ��Ѵ��ڣ���һ�����԰ɣ�");
					}
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == reset_button) {
			// ������е����ݣ�����������Ϣ
			this.echoData();
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

	/**
	 * �������ݵķ���
	 */
	public void echoData(){
		id.setText(table.getValueAt(selectedRow, 0).toString());
		name.setText(table.getValueAt(selectedRow, 1).toString());
		password.setText(table.getValueAt(selectedRow, 2).toString());
		limits.setSelectedIndex(Integer.valueOf(table.getValueAt(selectedRow, 4).toString()));
	}
}
