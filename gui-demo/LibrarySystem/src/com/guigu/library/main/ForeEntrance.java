package com.guigu.library.main;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.guigu.library.fore.ui.control.ForeLoginJFrame;

public class ForeEntrance {
	public static void main(String[] args) {
		try
	    {
			// ��ֹ��������
			System.setProperty("sun.java2d.noddraw", "true");
	    	 //���ñ����Խ��ı䴰�ڱ߿���ʽ����
	        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			//�������ð�ť
			UIManager.put("RootPane.setupButtonVisible", false);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    new ForeLoginJFrame();
	}
}
