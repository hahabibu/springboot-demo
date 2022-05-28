package com.guigu.library.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * �Զ���������֤�� ���������ж��Ƿ�����ת���������Ӷ�������Ӧ���쳣 ʹ��������ʽʵ��
 */
public class DataValidation {
	// �ж��Ƿ�Ϊ������λ��λ�ĵ���С��������0С�ڵ���1��
	public static boolean isSmallDecimal(String str) {
		Pattern pattern = Pattern.compile("^(1|0\\.\\d{1,2})$|(1\\.0{1,2})$");
		Matcher isValid = pattern.matcher(str);
		return isValid.matches();
	}

	// �ж��Ƿ�Ϊ������λ��λ�ĵ���С��(���Դ���1)
	public static boolean isBigDecimal(String str) {
		// ������ʽ_��������С����С��λ��������2λ
		Pattern pattern = Pattern
				.compile("^[1-9][0-9]*(\\.[0-9]{1,2})?$|(0\\.0{1,2})$|0");
		Matcher isValid = pattern.matcher(str);
		return isValid.matches();
	}

	// �ж��Ƿ�Ϊ������
	public static boolean isSignlessInteger(String str) {
		Pattern pattern = Pattern.compile("^([1-9]\\d*|[0]{1,1})$");
		Matcher isValid = pattern.matcher(str);
		return isValid.matches();
	}

	// �ж��Ƿ�Ϊ�Ϸ��ֻ���
	public static boolean isValidPhone(String str) {
		/**
		 * �ֻ����� 
		 * �ƶ���134[0-8],135,136,137,138,139,150,151,157,158,159,182,187,188
		 * ��ͨ��130,131,132,152,155,156,185,186 
		 * ���ţ�133,1349,153,180,189
		 * ��½�����̻���С��ͨ ���ţ�
		 *    010,020,021,022,023,024,025,027,028,029
		 *    ���룺��λ���λ
		 */
		Pattern mobile = Pattern.compile("^1(3[0-9]|5[0-35-9]|8[025-9])\\d{8}$");
		Matcher isValid = mobile.matcher(str);
		return isValid.matches();
	}

	// �ж��Ƿ�Ϊ�Ϸ�����
	public static boolean isValidEmail(String str) {
		Pattern pattern = Pattern
				.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
		Matcher isValid = pattern.matcher(str);
		return isValid.matches();
	}
	
	// �ж����֤���Ƿ�Ϸ���ֻ�Ǽ򵥽����жϣ���������֤��α��
	public static boolean isValidIdCard(String str){
		Pattern pattern = Pattern
				.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])"
						+ "\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))"
						+ "(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
		Matcher isValid = pattern.matcher(str);
		return isValid.matches();
	}
	
	// �ж����������Ƿ�Ϸ�
		public static boolean isValidAge(String str){
			Pattern pattern = Pattern
					.compile("^(?:[1-9][0-9]?|1[01][0-9]|120)$");
			Matcher isValid = pattern.matcher(str);
			return isValid.matches();
		}
	
		// �ж�����������Ƿ�Ϸ�
		public static boolean isValidDate(String str){
			Pattern pattern = Pattern
					.compile("^\\d{4}\\-\\d{2}\\-\\d{2}$");
			Matcher isValid = pattern.matcher(str);
			return isValid.matches();
		}

	public static void main(String[] args) {
//		System.out.println(DataValidation.isBigDecimal("0"));
//		System.out.println(DataValidation.isSmallDecimal("1.00"));
//		System.out.println(DataValidation.isValidEmail("892944741@qq.com"));
//		System.out.println(DataValidation.isValidEmail("892944741@qsssq.cosssm"));
//		System.out.println(DataValidation.isValidPhone("15868154584"));
//		System.out.println(DataValidation.isValidPhone("12s5s525d5s22"));
//		System.out.println(DataValidation.isValidPhone("12032562352"));
//		System.out.println(DataValidation.isValidIdCard("12032562352"));
//		System.out.println(DataValidation.isValidIdCard("441424199803114869"));
	}
}
