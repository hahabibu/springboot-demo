package com.guigu.library.back.ui.control;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.guigu.library.model.Users;
import com.guigu.library.utils.ImagePanel;

public class LibraryInfoJPanel {
	// ����ȫ�����
	JPanel backgroundPanel;

	Users user;

	public LibraryInfoJPanel(Users user) {
		this.user = user;
		initBackgroundPanel();
	}

	/**
	 * ��ʼ���������
	 */
	private void initBackgroundPanel() {
		backgroundPanel = new JPanel(new BorderLayout());
		try {
			Image backgroundImage = ImageIO.read(new File(
					"icons/toolImage/message.png"));
			ImagePanel centerBackground = new ImagePanel(backgroundImage);
			// ����ʼ����ɵ������ص����������
			backgroundPanel.add(centerBackground, BorderLayout.CENTER);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
