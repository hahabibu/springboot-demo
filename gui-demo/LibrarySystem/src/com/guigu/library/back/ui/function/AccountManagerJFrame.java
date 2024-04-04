package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.guigu.library.back.ui.control.ReaderArchivesManagerJPanel;
import com.guigu.library.model.LibraryCard;
import com.guigu.library.model.Reader;
import com.guigu.library.model.Users;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class AccountManagerJFrame extends JFrame implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_id, label_name, label_password, label_limits;
	JTextField id, name, password, limits;

	// ����service
	ReaderService readerService = new ReaderServiceImpl();
	UsersService usersService = new UsersServiceImpl();

	JLabel find, active;

	// ���常���󡢸����ѡ�е���
	ReaderArchivesManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// ͨ�����췽����ʼ������
	public AccountManagerJFrame(ReaderArchivesManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("�����˺���Ϣ����");
		this.setSize(600, 350);
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
		JLabel title = new JLabel("�����˺���Ϣ����");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();
		JPanel jp1 = new JPanel();
		label_id = new JLabel("�˺�id  ");
		id = new JTextField(15);
		id.setFont(MyFont.JTextFieldFont);
		id.setForeground(MyColor.JTextFieldColor);
		id.setEditable(false);
		jp1.add(label_id);
		jp1.add(id);
		
		JPanel jp2 = new JPanel();
		label_name = new JLabel("�û�����");
		name = new JTextField(15);
		name.setFont(MyFont.JTextFieldFont);
		name.setForeground(MyColor.JTextFieldColor);
		name.setEditable(false);
		jp2.add(label_name);
		jp2.add(name);

		JPanel jp3 = new JPanel();
		label_password = new JLabel("�˺�����");
		password = new JTextField(15);
		password.setFont(MyFont.JTextFieldFont);
		password.setForeground(MyColor.JTextFieldColor);
		password.setEditable(false);
		jp3.add(label_password);
		jp3.add(password);
		
		JPanel jp4 = new JPanel();
		label_limits = new JLabel("�û�Ȩ��");
		limits = new JTextField(15);
		limits.setFont(MyFont.JTextFieldFont);
		limits.setForeground(MyColor.JTextFieldColor);
		limits.setEditable(false);
		jp4.add(label_limits);
		jp4.add(limits);

		// ���ݻ���
		this.echoData();
		Box ver = Box.createVerticalBox();
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
		// ��������ص�contentPanel�����
		contentPanel.add(ver);
	}

	/**
	 * ��ʼ����ť���
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();
		Icon find_icon = new ImageIcon("icons/toolImage/find.png");
		find = new JLabel(find_icon);
		find.setToolTipText("�һ�����");
		find.addMouseListener(this);

		Icon active_icon = new ImageIcon("icons/toolImage/active.png");
		active = new JLabel(active_icon);
		active.setToolTipText("����/����");
		active.addMouseListener(this);

		buttonPanel.add(find);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(active);
		buttonPanel.add(Box.createHorizontalStrut(10));
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			// ���ݸ�����е�ǰָ�����л�ȡ���ߵ�������Ϣ��
			String account_id_string = parentTable.getValueAt(selectedRow, 10).toString();
			String reader_id = parentTable.getValueAt(selectedRow, 0).toString();
			Reader r = readerService.getReaderById(reader_id);
			// �����˺�id��ȡ��ϸ���˺���Ϣ
			Users user = null;
				user = usersService.getUsersById(account_id_string);
			if (e.getSource() == find) {
				 // �һ�����
				int choose = JOptionPane.showConfirmDialog(null, "ȷ�������˺���Ϣ��");
				if(choose==0){
					String academic_num = r.getAcademic_num();
					user.setUser_name(academic_num);
					user.setUser_password("000000");
					usersService.updateUsers(user);
					JOptionPane.showMessageDialog(null, "�û��˺����������ã��û�����"+user.getUser_name()+"-���룺"+user.getUser_password());	
					this.refreshContentPanel();
				}
			} else if (e.getSource() == active) {
				// ���ݶ��ߵ�ǰ����ݼ����˻�
				int limits_int = user.getUser_limits();
				if(limits_int==0){
					int choose = JOptionPane.showConfirmDialog(null, "ȷ�ϼ�����˺���Ϣ��");
					if(choose==0){
						int classify = r.getClassify_num();
						int newlimit = 0;
						if(classify==0||classify==1||classify==2){
							// ��ʾ��ͨ���ߣ�Ȩ�޵ȼ���Ϊ3
							newlimit=3;
						}else if(classify==3){
							// ��ʾͼ�����Ա��Ȩ�޵ȼ���Ϊ2
							newlimit = 2;
						}else if(classify==4){
							// ��ʾ��������Ա��Ȩ�޵ȼ���Ϊ1
							newlimit=1;
						}
						user.setUser_limits(newlimit);
						usersService.updateUsers(user);
						// ˢ���������
						JOptionPane.showMessageDialog(null, "�˺���Ϣ����ɹ���");
						this.refreshContentPanel();
					}
				}else if(limits_int!=0){
					// �����˻���Ϣ
					int choose = JOptionPane.showConfirmDialog(null, "ȷ�Ͻ��ø��˻���Ϣ��");
					if(choose==0){
						user.setUser_limits(0);
						usersService.updateUsers(user);
						// ˢ���������
						JOptionPane.showMessageDialog(null, "�˺���Ϣ�ѽ��ã�");
						this.refreshContentPanel();
					}
				}
			}
		} catch (HeadlessException | SQLException e1) {
			e1.printStackTrace();
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
	 * �������ݻ��Է���
	 */
	public void echoData() {
		// ���ݸ�����е�ǰָ�����л�ȡ���ߵ�������Ϣ��
		String account_id_string = parentTable.getValueAt(selectedRow, 10)
				.toString();
		// �����˺�id��ȡ��ϸ���˺���Ϣ
		Users user = null;
		try {
			user = usersService.getUsersById(account_id_string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ݻ���
		id.setText(user.getUser_id());
		id.setToolTipText(user.getUser_id());
		name.setText(user.getUser_name());
		name.setToolTipText(user.getUser_name());
		password.setText(user.getUser_password());
		password.setToolTipText(user.getUser_password());
		int limits_int = user.getUser_limits();
		String identity = null;
		if (limits_int == 1) {
			identity = "��������Ա";
		} else if (limits_int == 2) {
			identity = "ͼ��ݹ���Ա";
		} else if (limits_int == 3) {
			identity = "����";
		} else if (limits_int == 0) {
			identity = "�����˻�";
		}
		limits.setText(identity);
		limits.setToolTipText(identity);
	}

	/**
	 * ˢ���������
	 */
	public void refreshContentPanel() {
		backgroundPanel.remove(contentPanel);
		initContentPanel();
		backgroundPanel.add(contentPanel);
		backgroundPanel.validate();
	}
}
