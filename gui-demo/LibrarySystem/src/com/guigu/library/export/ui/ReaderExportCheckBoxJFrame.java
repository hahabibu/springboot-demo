package com.guigu.library.export.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.guigu.library.back.ui.control.ReaderArchivesManagerJPanel;
import com.guigu.library.service.BooksService;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.utils.MyFont;

public class ReaderExportCheckBoxJFrame extends JFrame implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;

	ButtonGroup select;
	JRadioButton all, cancel;
	JCheckBox barcode, academic_num, name, sex, birthday, idCard, phone, email,
			account, classify, libraryCard, descr;

	JCheckBox[] checkBox = { barcode, academic_num, name, sex, birthday,
			idCard, phone, email, account, classify, libraryCard, descr };

	String[] names = { "������  ", "ѧ�����", "��������", "�����Ա�", "��������", "���֤��", "��ϵ��ʽ",
			"��������", "��������", "ʹ���˺�", "����֤��", "���߼���" };
	int[] flags = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	int[] column = { 1, 2, 3, 4, 5, 6, 7, 8, 13, 11, 15, 9 };// ��Ӧ����е���
	JButton ensure, exit;

	// ���常���󡢸����ѡ������
	ReaderArchivesManagerJPanel parentPanel;
	JTable parentTable;
	int[] selectedRows;

	// ����service
	BooksService booksService = new BooksServiceImpl();

	// ͨ�����췽����ʼ������
	public ReaderExportCheckBoxJFrame(ReaderArchivesManagerJPanel parentPanel,
			JTable parentTable, int[] selectedRows) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRows = selectedRows;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("���ݵ���ѡ��");
		this.setSize(450, 500);
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
		JLabel title = new JLabel("���ݵ���ѡ��");
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

		// ÿ3������һ��
		JPanel jp1 = new JPanel();
		jp1.add(checkBox[0]);
		jp1.add(checkBox[1]);
		jp1.add(checkBox[2]);

		JPanel jp2 = new JPanel();
		jp2.add(checkBox[3]);
		jp2.add(checkBox[4]);
		jp2.add(checkBox[5]);

		JPanel jp3 = new JPanel();
		jp3.add(checkBox[6]);
		jp3.add(checkBox[7]);
		jp3.add(checkBox[8]);

		JPanel jp4 = new JPanel();
		jp4.add(checkBox[9]);
		jp4.add(checkBox[10]);
		jp4.add(checkBox[11]);

		Box ver = Box.createVerticalBox();
		ver.add(selectPanel);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp1);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp2);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp3);
		ver.add(Box.createVerticalStrut(3));
		ver.add(jp4);
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

		exit = new JButton("ȡ��");
		exit.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		exit.setForeground(Color.white);
		exit.setFont(MyFont.JButtonFont);
		exit.addMouseListener(this);

		buttonPanel.add(ensure);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(exit);
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
			int choose = JOptionPane.showConfirmDialog(null, "ȷ�ϵ���ѡ�����ݣ�");
			if (choose == 0) {
				// ����ת����
				String[] ids;
				ArrayList id_list = new ArrayList<>();
				for (int rowindex : parentTable.getSelectedRows()) {
					Object obj = parentTable.getValueAt(rowindex, 0);
					id_list.add(obj);
				}
				// ����ת����
				ids = (String[]) id_list.toArray(new String[id_list.size()]);
				int result = this.exportData(ids);
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "���ݵ����ɹ���");
					this.setVisible(false);
				} else if (result == -1) {
					JOptionPane.showMessageDialog(null, "��Ǹ�����ݵ���ʧ�ܣ�����һ��ɣ�");
				} else if (result == 0) {
					JOptionPane.showMessageDialog(null, "�û�ȡ���˲�����");
				}
			}
		} else if (e.getSource() == exit) {
			this.setVisible(false);
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
	 * ���嵼�����ݵķ���
	 */
	public int exportData(String[] ids) {
		try {
			// ��������Ŀ¼
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("data");
			// ����������
			XSSFRow row = sheet.createRow((short) 0);
			XSSFCell cell = null;

			// ��װѡ�е�������Ϣ(һһ��Ӧ)
			List<String> name_string = new ArrayList<String>();
			List<Integer> column_int = new ArrayList<Integer>();
			for (int i = 0; i < flags.length; i++) {
				if (flags[i] == 1) {
					name_string.add(names[i]);
					column_int.add(column[i]);
				}
			}

			// ��ȡѡ�е��У�ͨ��ѡ�е���ִ�����ݵ���
			for (int i = 0; i < name_string.size(); i++) {
				// ������һ�б�ͷ����
				cell = row.createCell((short) i);
				cell.setCellValue(name_string.get(i));
			}

			// ����ѡ�е��С��е������ݣ����û��ѡ������Ĭ���ǵ�����ǰ�����������ݵ�����
			if (selectedRows.length != 0) {
				// ����ѡ�е�����
				for (int i = 0; i < selectedRows.length; i++) {
					row = sheet.createRow(i + 1);
					for (int j = 0; j < name_string.size(); j++) {
						// ����ÿһ������
						cell = row.createCell((short) j);
						cell.setCellValue(parentTable.getValueAt(
								selectedRows[i], column_int.get(j)).toString());
					}
				}
			} else {
				// ������ǰ������������
				for (int i = 0; i < parentTable.getRowCount(); i++) {
					row = sheet.createRow(i + 1);
					for (int j = 0; j < name_string.size(); j++) {
						// ����ÿһ������
						cell = row.createCell((short) j);
						cell.setCellValue(parentTable.getValueAt(i,
								column_int.get(j)).toString());
					}
				}
			}
			// �����ļ�ѡ���
			JFileChooser chooser = new JFileChooser();
			// ��׺��������
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"����ļ�(*.xlsx)", "xlsx");
			chooser.setFileFilter(filter);
			// ����ķ�����������ֱ�����û����±��水ť�ҡ��ļ������ı���Ϊ�ա����û�����ȡ����ť��
			int option = chooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) { // �����û�ѡ���˱���
				File file = chooser.getSelectedFile();
				String fname = chooser.getName(file); // ���ļ���������л�ȡ�ļ���
				// �����û���д���ļ������������ƶ��ĺ�׺������ô���Ǹ������Ϻ�׺
				if (fname.indexOf(".xlsx") == -1) {
					file = new File(chooser.getCurrentDirectory(), fname
							+ ".xlsx");
				}
				try {
					FileOutputStream FOut = new FileOutputStream(file);
					workbook.write(FOut);
					FOut.flush();
					FOut.close();
					workbook.close();
					return 1;
				} catch (IOException e) {
					System.err.println("IO�쳣");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
}