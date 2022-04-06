package com.design.sm.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

/**
 * �Զ��幤����
 * ���ͼƬ�ļ���
 */
public class ImagePanel extends JPanel{
	//a.���屳��ͼƬ
	Image image;
	//b.��ʼ������ͼƬ
	public ImagePanel(Image image)
	{
		this.image = image;
		//��ȡ��ǰ��Ļ�Ŀ�Ⱥ͸߶�
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		//���û���ͼƬ�Ĵ�С
		this.setSize(width,height);
	}
	//c.����ͼƬ
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//����ͼƬ��Դ������λ�á����ƴ�С�����Ƶ�ǰ����this��
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
