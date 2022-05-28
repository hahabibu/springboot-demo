package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;

import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.AdminSetJPanel;
import com.guigu.library.model.Setting;
import com.guigu.library.service.SettingService;
import com.guigu.library.service.impl.SettingServiceImpl;
import com.guigu.library.utils.MyFont;

public class AdminSettingJFrame extends JFrame implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;

	ButtonGroup select;
	JRadioButton all, cancel;
	JCheckBox infoSearch, booksManager, readerManager, systemSetup;

	JCheckBox[] checkBox = { infoSearch, booksManager, readerManager, systemSetup};

	String[] names = { "��Ϣ��ѯ", "ͼ�����", "���߹���", "ϵͳ����" };
	int[] flags = { 0, 0, 0, 0 };

	JButton ensure, reset;

	// ���常���󡢸����ѡ������
	AdminSetJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	// ����service
	SettingService settingService = new SettingServiceImpl();

	// ͨ�����췽����ʼ������
	public AdminSettingJFrame(AdminSetJPanel parentPanel, JTable parentTable,
			int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("����ԱȨ������");
		this.setSize(450, 300);
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
		backgroundPanel.validate();
	}

	/**
	 * ��ʼ���������
	 */
	private void initTitlePanel() {
		titlePanel = new JPanel();
		JLabel title = new JLabel("����ԱȨ������");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
		backgroundPanel.add(titlePanel, BorderLayout.NORTH);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel selectPanel = new JPanel();
		select = new ButtonGroup();
		all = new JRadioButton("ȫѡ");
		all.addMouseListener(this);
		cancel = new JRadioButton("ȡ��");
		cancel.addMouseListener(this);
		select.add(all);
		select.add(cancel);
		selectPanel.add(all);
		selectPanel.add(cancel);

		// ��ʼ����ѡ��(һһ��Ӧ)
		for (int i = 0; i < checkBox.length; i++) {
			checkBox[i] = new JCheckBox(names[i]);
		}

		// ÿ2������һ��
		JPanel jp1 = new JPanel();
		jp1.add(checkBox[0]);
		jp1.add(checkBox[1]);

		JPanel jp2 = new JPanel();
		jp2.add(checkBox[2]);
		jp2.add(checkBox[3]);

		// ���ݻ���
		this.echoData();
		Box ver = Box.createVerticalBox();
		ver.add(selectPanel);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		// ��������ص�contentPanel�����
		contentPanel.add(ver);
		backgroundPanel.add(contentPanel, BorderLayout.CENTER);
	}

	/**
	 * ��ʼ����ť���
	 */
	private void initButtonPanel() {
		buttonPanel = new JPanel();

		ensure = new JButton("ȷ��");
		ensure.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		ensure.setForeground(Color.white);
		ensure.setFont(MyFont.JButtonFont);
		ensure.addMouseListener(this);

		reset = new JButton("����");
		reset.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		reset.setForeground(Color.white);
		reset.setFont(MyFont.JButtonFont);
		reset.addMouseListener(this);

		buttonPanel.add(ensure);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(reset);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == all) {
			// Ĭ��ѡ�����е�����
			for (int i = 0; i < checkBox.length; i++) {
				checkBox[i].setSelected(true);
			}
		} else if (e.getSource() == cancel) {
			// Ĭ��ȡ������ѡ��
			for (int i = 0; i < checkBox.length; i++) {
				checkBox[i].setSelected(false);
			}
		} else if (e.getSource() == ensure) {
			// ��ȡ�û�ѡ���޸ı�ʶ
			for (int i = 0; i < checkBox.length; i++) {
				if (checkBox[i].isSelected()) {
					// �޸ı�ʶ
					flags[i] = 1;
				}
			}
			int choose = JOptionPane.showConfirmDialog(null, "ȷ���޸Ĺ���ԱȨ�ޣ�");
			if (choose == 0) {
				try {
					// ����ָ���Ķ���id�޸Ĺ���ԱȨ��
					String reader_id = parentTable.getValueAt(selectedRow, 0)
							.toString();
					Setting s = settingService.getSettingByReaderId(reader_id);
					Setting newSetting = new Setting(reader_id, flags[0],
							flags[1], flags[2], flags[3]);
					// �жϵ�ǰ�Ƿ��Ѿ�����������Ϣ������������������ӣ�������������
					if (s == null) {
						// ���ݵ�ǰ�û�ѡ�����������Ϣ
						settingService.addSetting(newSetting);
					} else if (s != null) {
						// ���ݵ�ǰ�û�ѡ�����������Ϣ
						settingService.updateSetting(newSetting);
					}
					JOptionPane.showMessageDialog(null, "����ԱȨ��������ɣ�");
				} catch (HeadlessException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == reset) {
			// ��������
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
	 * �������ݻ��Եķ���
	 */
	public void echoData() {
		String reader_id = parentTable.getValueAt(selectedRow, 0).toString();
		Setting s = null;
		try {
			s = settingService.getSettingByReaderId(reader_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (s == null) {
			// û��ָ����������Ϣ��Ĭ��Ϊȫ����ѡ
			for (int i = 0; i < checkBox.length; i++) {
				checkBox[i].setSelected(false);
			}
		} else if (s != null) {
			// ���ݻ���
			if (s.getInfoSearch() == 1) {
				checkBox[0].setSelected(true);
			}
			if (s.getBooksManager() == 1) {
				checkBox[1].setSelected(true);
			} 
			if (s.getReaderManager() == 1) {
				checkBox[2].setSelected(true);
			} 
			if (s.getSystemSetup() == 1) {
				checkBox[3].setSelected(true);
			}
		}
	}
}