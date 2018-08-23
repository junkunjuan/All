package com.test;

public class FinallyTest {
	public static int testFinally() {
		try {
			return 1;
		} catch (Exception e) {
			return 0;
		} finally {
			System.out.println("execute finally");
		}
	}

	public static String test1() {
		String str = new String("test");
		try {
			return str;
		} catch (Exception e) {
			return null;
		} finally {
			str = "hello";
		}
	}

	public static void main(String[] args) {
		int result = testFinally();
		System.out.println(result);
	}

}
