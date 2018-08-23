package com.test;

import java.util.Arrays;

public class Sort {

	// 插入排序
	private static void insertionSort(int[] arr) {
		int in, out;
		for (out = 1; out < arr.length; out++) {
			int temp = arr[out];
			in = out;
			while (in > 0 && arr[in - 1] >= temp) {
				arr[in] = arr[in - 1];
				--in;
			}
			arr[in] = temp;
		}
	}

	// 快速排序
	private static void quickSort(int[] a, int left, int right) {
		int i, j, temp;
		if (left > right)
			return;
		temp = a[left];
		i = left;
		j = right;
		while (i != j) {
			while (a[j] >= temp && i < j)
				j--;
			while (a[i] <= temp && i < j)
				i++;
			if (i < j) {
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		}
		a[left] = a[i];
		a[i] = temp;
		quickSort(a, left, i - 1);
		quickSort(a, i + 1, right);
	}

	public static String reverse(String originStr) {
		if (originStr == null || originStr.length() <= 1)
			return originStr;
		return reverse(originStr.substring(1)) + originStr.charAt(0);
	}

	private static int binarySerach(int[] array, int key) {
		int left = 0;
		int right = array.length - 1;

		// 这里必须是 <=
		while (left <= right) {
			int mid = (left + right) / 2;
			if (array[mid] == key) {
				return mid;
			} else if (array[mid] < key) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		int a[] = {5,3,2,4,1};
		insertionSort(a);
		/*
		 * quickSort(a, 0, a.length - 1);
		 * System.out.println(Arrays.toString(a));
		 */
		// System.out.println(Math.round(-11.6));
		/*
		 * String aString = "jsdf"; String cString = aString;
		 * System.out.println(aString.hashCode()); aString = "jdf"; String
		 * bString = aString; System.out.println(cString == bString);
		 * System.out.println(aString.getClass());
		 */
		/*
		 * String s1 = "Programming"; String s2 = new String("Programming");
		 * String s3 = "Program" + "ming"; System.out.println(s1 == s2);
		 * System.out.println(s1 == s3); System.out.println(s1 == s2.intern());
		 * String string = reverse("abc"); System.out.println(string);
		 */
		System.out.println(binarySerach(a, 3));

	}
}
