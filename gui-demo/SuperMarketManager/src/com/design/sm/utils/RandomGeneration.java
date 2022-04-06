package com.design.sm.utils;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

/**
 * �����������ָ��id�Ĺ�����
 */
public class RandomGeneration {
	// �������32λ�ַ�����
	public static String getRandom32charSeq() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	// �������10λ�ַ�����
	public static String getRandom10charSeq() {
		StringBuffer sb = new StringBuffer();
		char[] ch = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n',
				'o','p','q','r','s','t','u','v','w','x','y','z'};
		for (int i = 0; i < 10; i++) {
			Random r = new Random();
			int temp = r.nextInt(26);
			sb.append(ch[temp]);
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
	 * ����Զ�����Ա�����
	 * ���ɹ���
	 * ��ְ���+��ְ���Ŵ���+������
	 * ��ְ���Ĭ�ϻ�ȡ��ǰϵͳʱ��
	 * ��ְ���Ŵ�������Ӧ����ְ���Ŵ��Ž��д���
	 * ������������Ǹò��ŵĵڼ���Ա��
	 * ���²���01
	 * ���񲿣�02
	 * Ӫ������03
	 * �ͷ�����04
	 * ��������05
	 * ��    �ã�06
	 * �������ݿ��е���ƣ�Ա��������10λ��int���͵�����
	 * ����Ա����Ŀ�����࣬���п��ܳ��ּ�������9999ʱ�����쳣
	 * ������Ӧ��ʵ���߼����˴�����ÿ��6666�㽫��������������
	 */
	public static String getEmployeeNum(String deptId){
		String id = null;
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR));
		if(deptId.equals("8132456543"))
		{
			id = year + "01";
		}else if(deptId.equals("7168462722")){
			id = year + "02";
		}else if(deptId.equals("3153961019")){
			id = year + "03";
		}else if(deptId.equals("1674836044")){
			id = year + "04";
		}else if(deptId.equals("2249164713")){
			id = year + "05";
		}else{
			id = year + "06";
		}
		return id;      
	}
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(RandomGeneration.getRandom10charSeq());
//			System.out.println(RandomGeneration.getRandom10numSeq());
//			System.out.println(RandomGeneration.getRandom32charSeq());
//			System.out.println(RandomGeneration.getRandomCustomerSeq());
//			System.out.println(RandomGeneration.getRandomVendorSeq());
		}
//		System.out.println(RandomGeneration.getEmployeeNum("8132456543"));
//		System.out.println(RandomGeneration.getEmployeeNum("7168462722"));
//		System.out.println(RandomGeneration.getEmployeeNum("3153961019"));
//		System.out.println(RandomGeneration.getEmployeeNum("1674836044"));
//		System.out.println(RandomGeneration.getEmployeeNum("2249164713"));
//		System.out.println(RandomGeneration.getEmployeeNum("2245564713"));
	}
}
