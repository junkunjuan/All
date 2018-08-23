package com.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

class AA {
	int i = 0;
}

public class TryFinally {

	public static void main(String[] args) {
		AA aa = new AA();
		add(aa);
		System.out.println(aa.i);
		int[] count = {1, 2, 3, 4, 5};
		change(count);
		System.out.println(count[0]);
		
		//System.out.println(testFinally2());
		/*
		 * int n = 7000000;
		 * 
		 * List<Integer> intListEnd = new ArrayList<Integer>(); long start1 =
		 * (new Date()).getTime();
		 * 
		 * for (int i = 0; i < n; i++) { intListEnd.add(i); } long end1 = (new
		 * Date()).getTime(); long mid1 = end1 - start1;
		 * System.out.println("ArrayList在数组尾端插入数据量为" + n + "时，所用时间毫秒是=" + mid1);
		 */
		/*
		 * List<Integer> linkListEnd = new LinkedList<Integer>(); long start2 =
		 * System.currentTimeMillis();
		 * 
		 * for (int j = 0; j < n; j++) { linkListEnd.add(j); } long end2 =
		 * System.currentTimeMillis(); long mid2 = end2 - start2;
		 * System.out.println("LinkList在数组尾端插入数据量为" + n + "时，所用时间毫秒是=" + mid2);
		 */
		/*
		 * List<Integer> intListStart = new ArrayList<Integer>(); long start3 =
		 * (new Date()).getTime();
		 * 
		 * for (int k = 0; k < n; k++) { intListStart.add(0, k); } long end3 =
		 * (new Date()).getTime(); long mid3 = end3 - start3;
		 * System.out.println("ArrayList在数组首端插入数据量为" + n + "时，所用时间毫秒是=" + mid3);
		 * 
		 * List<Integer> linkListStart = new LinkedList<Integer>(); long start4
		 * = (new Date()).getTime();
		 * 
		 * for (int l = 0; l < n; l++) { linkListStart.add(0, l); } long end4 =
		 * (new Date()).getTime(); long mid4 = end4 - start4;
		 * System.out.println("LinkList在数组首端插入数据量为" + n + "时，所用时间毫秒是=" + mid4);
		 */

	}

	public static int tt() {
		int b = 23;
		try {
			System.out.println("yes");
			return b = 88;
		} catch (Exception e) {
			System.out.println("error : " + e);
		} finally {
			if (b > 25) {
				System.out.println("b>25 : " + b);
			}
			System.out.println("finally");
		}
		return b;
	}

	public static String testFinally2() {
		String s = new String("Hello");
		try {
			return s;
		} catch (Exception e) {
			return null;
		} finally {
			s = s + " World";
			System.out.println(s);
		}
	}
	
	public static void change(int[] counts) {
		counts[0] = 6;
		System.out.println(counts[0]);
	}
	
	public static void add(AA a) {
		a.i++;
	}
	
}

