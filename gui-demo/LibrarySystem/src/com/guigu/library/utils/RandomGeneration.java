package com.guigu.library.utils;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

import com.guigu.library.service.BooksService;
import com.guigu.library.service.LibraryCardService;
import com.guigu.library.service.impl.BooksServiceImpl;
import com.guigu.library.service.impl.LibraryCardServiceImpl;

/**
 * �����������ָ��id�Ĺ�����
 */
public class RandomGeneration {
	public static BooksService booksService = new BooksServiceImpl();
	public static LibraryCardService libraryCardService = new LibraryCardServiceImpl();

	// �������32λ�ַ�����
	public static String getRandom32charSeq() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	// �������8λ�����ַ�����
	public static String getRandom8numSeq() {
		StringBuffer sb = new StringBuffer();
		int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i < 8; i++) {
			Random r = new Random();
			int temp = r.nextInt(10);
			sb.append(nums[temp]);
		}
		return sb.toString();
	}

	// �������10λ�����ַ�����
	public static String getRandom10numSeq() {
		StringBuffer sb = new StringBuffer();
		int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			int temp = r.nextInt(10);
			sb.append(nums[temp]);
		}
		return sb.toString();
	}

	/**
	 * �Զ�������ͼ�������� ����ͼ����������� ���ɹ��򣺡�������-��+��ͼ�����кš�
	 */
	public static String getBooksCallno(String classify_num) {
		String callno = "";
		try {
			callno = null;
			String seq = booksService.getBookSeq();
			callno = classify_num + "-" + seq;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return callno;
	}
	
	/**
	 * �Զ������ɽ���֤��ţ����ݶ��߷������� ���ɹ��򣺡���ǰ���4λ+���߷�����2λ+�������кš�
	 */
	public static String getLibraryCardNum(int classify_num) {
		String num = "";
		try {
			num = null;
			String seq = libraryCardService.getLibraryCardSeq();
			Calendar c = Calendar.getInstance();
			String year = String.valueOf(c.get(Calendar.YEAR));
			if(classify_num==0)
			{
				num = year + "00" + seq;
			}else if(classify_num==1){
				num = year + "01" + seq;
			}else if(classify_num==2){
				num = year + "02" + seq;
			}else if(classify_num==3){
				num = year + "03" + seq;
			}else if(classify_num==4){
				num = year + "04" + seq;
			}
			return num;      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			// System.out.println(RandomGeneration.getRandom10charSeq());
			System.out.println(RandomGeneration.getRandom10numSeq());
			// System.out.println(RandomGeneration.getRandom32charSeq());
			// System.out.println(RandomGeneration.getRandomCustomerSeq());
			// System.out.println(RandomGeneration.getRandomVendorSeq());
		}

	}
}
