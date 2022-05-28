package com.guigu.library.back.ui.function;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.ReaderClassifyService;
import com.guigu.library.service.ReaderService;
import com.guigu.library.service.UsersService;
import com.guigu.library.service.impl.LibraryCardServiceImpl;
import com.guigu.library.service.impl.ReaderClassifyServiceImpl;
import com.guigu.library.service.impl.ReaderServiceImpl;
import com.guigu.library.service.impl.UsersServiceImpl;
import com.guigu.library.utils.MyColor;
import com.guigu.library.utils.MyFont;
import com.guigu.library.utils.RandomGeneration;

public class LibraryCardManagerJFrame extends JFrame implements MouseListener {

	// ����ȫ�����
	JPanel backgroundPanel, titlePanel, contentPanel, buttonPanel;
	JLabel label_id, label_num, label_handle_date, label_valid_till,
			label_loss_state, label_disable_state;
	JTextField id, num, handle_date, valid_till, loss_state, disable_state;

	// ����service
	ReaderService readerService = new ReaderServiceImpl();
	LibraryCardService libraryCardService = new LibraryCardServiceImpl();

	JLabel loss, active, replace;

	// ���常���󡢸����ѡ�е���
	ReaderArchivesManagerJPanel parentPanel;
	JTable parentTable;
	int selectedRow;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String current_date = sdf.format(new Date());

	// ͨ�����췽����ʼ������
	public LibraryCardManagerJFrame(ReaderArchivesManagerJPanel parentPanel,
			JTable parentTable, int selectedRow) {
		this.parentPanel = parentPanel;
		this.parentTable = parentTable;
		this.selectedRow = selectedRow;
		// ��ʼ������
		initBackgroundPanel();
		// �����������ӵ�������
		this.add(backgroundPanel);
		this.setTitle("����֤��Ϣ����");
		this.setSize(600, 300);
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
		JLabel title = new JLabel("����֤��Ϣ����");
		title.setFont(MyFont.JTitleFont);
		titlePanel.add(title);
	}

	/**
	 * ��ʼ���������
	 */
	private void initContentPanel() {

		contentPanel = new JPanel();

		JPanel jp1 = new JPanel();
		label_id = new JLabel("����֤id");
		id = new JTextField(15);
		id.setFont(MyFont.JTextFieldFont);
		id.setForeground(MyColor.JTextFieldColor);
		id.setEditable(false);
		label_num = new JLabel("���ı��");
		num = new JTextField(15);
		num.setFont(MyFont.JTextFieldFont);
		num.setForeground(MyColor.JTextFieldColor);
		num.setEditable(false);
		jp1.add(label_id);
		jp1.add(id);
		jp1.add(label_num);
		jp1.add(num);

		JPanel jp2 = new JPanel();
		label_handle_date = new JLabel("��������");
		handle_date = new JTextField(15);
		handle_date.setFont(MyFont.JTextFieldFont);
		handle_date.setForeground(MyColor.JTextFieldColor);
		handle_date.setEditable(false);

		label_valid_till = new JLabel("��Ч����");
		valid_till = new JTextField(15);
		valid_till.setFont(MyFont.JTextFieldFont);
		valid_till.setForeground(MyColor.JTextFieldColor);
		valid_till.setEditable(false);
		jp2.add(label_handle_date);
		jp2.add(handle_date);
		jp2.add(label_valid_till);
		jp2.add(valid_till);

		JPanel jp3 = new JPanel();
		label_loss_state = new JLabel("��ʧ״̬");
		loss_state = new JTextField(15);
		loss_state.setFont(MyFont.JTextFieldFont);
		loss_state.setForeground(MyColor.JTextFieldColor);
		loss_state.setEditable(false);

		label_disable_state = new JLabel("����״̬");
		disable_state = new JTextField(15);
		disable_state.setFont(MyFont.JTextFieldFont);
		disable_state.setForeground(MyColor.JTextFieldColor);
		disable_state.setEditable(false);
		jp3.add(label_loss_state);
		jp3.add(loss_state);
		jp3.add(label_disable_state);
		jp3.add(disable_state);

		// ���ݻ���
		this.echoData();
		Box ver = Box.createVerticalBox();
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
		buttonPanel = new JPanel();
		Icon loss_icon = new ImageIcon("icons/toolImage/loss.png");
		loss = new JLabel(loss_icon);
		loss.setToolTipText("��ʧ/ȡ����ʧ");
		loss.addMouseListener(this);

		Icon active_icon = new ImageIcon("icons/toolImage/active.png");
		active = new JLabel(active_icon);
		active.setToolTipText("����/����");
		active.addMouseListener(this);

		Icon replace_icon = new ImageIcon("icons/toolImage/replace.png");
		replace = new JLabel(replace_icon);
		replace.setToolTipText("����");
		replace.addMouseListener(this);

		buttonPanel.add(loss);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(active);
		buttonPanel.add(Box.createHorizontalStrut(10));
		buttonPanel.add(replace);
		backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);
		backgroundPanel.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			// ���ݸ�����е�ǰָ�����л�ȡ���ߵ�������Ϣ��
			String libraryCard_id_string = parentTable.getValueAt(selectedRow,
					14).toString();
			// ���ݽ���֤id��ȡ��ϸ�Ľ���֤��Ϣ
			LibraryCard lc = null;
			lc = libraryCardService.getLibraryCardById(libraryCard_id_string);
			int loss_state_int = lc.getLoss_state();
			int disable_state_int = lc.getDisable_state();
			if (e.getSource() == loss) {
				if (loss_state_int == 0) {
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ�Ϲ�ʧ�ý���֤��");
					if (choose == 0) {
						// ��ʧ��������,ˢ���������
						lc.setLoss_state(1);
						lc.setDisable_state(1);
						libraryCardService.updateLibraryCard(lc);
						JOptionPane.showMessageDialog(null, "��ǰ����֤�ѹ�ʧ�����޷�ʹ�ã�");
						this.refreshContentPanel();
					}
				} else if (loss_state_int == 1) {
					int choose = JOptionPane.showConfirmDialog(null, "ȷ��ȡ����ʧ��");
					if (choose == 0) {
						// ȡ����ʧ��������,ˢ���������
						lc.setLoss_state(0);
						lc.setDisable_state(0);
						libraryCardService.updateLibraryCard(lc);
						JOptionPane.showMessageDialog(null, "��ǰ����֤��ȡ����ʧ��");
						this.refreshContentPanel();
					}
				}
			} else if (e.getSource() == active) {
				if (disable_state_int == 0) {
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ�Ͻ��øý���֤��");
					if (choose == 0) {
						// ����,ˢ���������
						lc.setDisable_state(1);
						libraryCardService.updateLibraryCard(lc);
						JOptionPane.showMessageDialog(null, "��ǰ����֤�ѽ��ã�");
						this.refreshContentPanel();
					}
				} else if (disable_state_int == 1) {
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ�ϼ���ý���֤��");
					if (choose == 0) {
						// ����,ˢ���������
						lc.setDisable_state(0);
						libraryCardService.updateLibraryCard(lc);
						JOptionPane.showMessageDialog(null, "��ǰ����֤�ѳɹ����");
						this.refreshContentPanel();
					}
				}
			} else if (e.getSource() == replace) {
				// ���ж��Ƿ�Ϊ��ʧ״̬����Ϊ��ʧ״̬���ɽ��в��죬������ɾ����ǰ����֤��Ϣ������¼�����֤��Ϣ
				if (loss_state_int == 1) {
					int choose = JOptionPane.showConfirmDialog(null,
							"ȷ�Ͻ��в������֤��");
					if (choose == 0) {
						// ��ȡ��ǰʹ�øý���֤�Ķ���id
						String reader_id = parentTable.getValueAt(selectedRow,
								0).toString();
						Reader r = readerService.getReaderById(reader_id);
						// ɾ����ǰ����֤��Ϣ
						libraryCardService.deleteLibraryCard(lc.getCard_id());
						// ������������֤��Ϣ
						String libraryCardId = RandomGeneration
								.getRandom32charSeq();
						String libraryCardNum = RandomGeneration
								.getLibraryCardNum(r.getClassify_num());
						LibraryCard newlc = new LibraryCard();
						newlc.setCard_id(libraryCardId);
						newlc.setCard_num(libraryCardNum);
						newlc.setHandle_date(current_date);
						Calendar c = Calendar.getInstance();
						c.setTime(new Date());
						c.add(Calendar.YEAR, 1);
						String valid_till_date = sdf.format(c.getTime());
						newlc.setValid_till(valid_till_date);
						libraryCardService.addLibraryCard(newlc);
						// ����ƥ����Ӧ����
						r.setCard_id(newlc.getCard_id());
						readerService.updateReader(r);
						// ˢ���������
						JOptionPane.showMessageDialog(null, "����֤����ɹ���");
						this.setVisible(false);
						parentPanel.refreshTablePanel();
					}
				} else if (loss_state_int == 0) {
					JOptionPane
							.showMessageDialog(null, "��ǰ����֤��Ϊ����ʧ״̬�����޷����в��죡");
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
		String libraryCard_id_string = parentTable.getValueAt(selectedRow, 14)
				.toString();
		// ���ݽ���֤id��ȡ��ϸ�Ľ���֤��Ϣ
		LibraryCard lc = null;
		try {
			lc = libraryCardService.getLibraryCardById(libraryCard_id_string);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ݻ���
		id.setText(lc.getCard_id());
		id.setToolTipText(lc.getCard_id());
		num.setText(lc.getCard_num());
		num.setToolTipText(lc.getCard_num());
		handle_date.setText(lc.getHandle_date().substring(0, 10));
		handle_date.setToolTipText(lc.getHandle_date().substring(0, 10));
		valid_till.setText(lc.getValid_till().substring(0, 10));
		valid_till.setToolTipText(lc.getValid_till().substring(0, 10));
		int loss_state_int = lc.getLoss_state();
		if (loss_state_int == 0) {
			loss_state.setText("����ʹ��");
			loss_state.setToolTipText("����ʹ��");
		} else if (loss_state_int == 1) {
			loss_state.setText("��ʧ״̬");
			loss_state.setToolTipText("��ʧ״̬");
		}
		int disable_state_int = lc.getDisable_state();
		if (disable_state_int == 0) {
			disable_state.setText("����ʹ��");
			disable_state.setToolTipText("����ʹ��");
		} else if (disable_state_int == 1) {
			disable_state.setText("����״̬");
			disable_state.setToolTipText("����״̬");
		}
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
