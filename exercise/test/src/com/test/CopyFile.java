package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public final class CopyFile { // 使用final定义工具类，不用实例化
	private CopyFile() { // 若实例化，则报错
		throw new AssertionError();
	}

	public static void fileCopy(String source, String target) throws IOException {
		try (InputStream in = new FileInputStream(source)) {
			try (OutputStream out = new FileOutputStream(target)) {
				byte[] buffer = new byte[4096];
				int bytesToRead;
				while ((bytesToRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesToRead);
				}
			}
		}
	}

	public static void fileCopyNIO(String source, String target) throws IOException {
		try (FileInputStream in = new FileInputStream(source)) {
			try (FileOutputStream out = new FileOutputStream(target)) {
				FileChannel inChannel = in.getChannel();
				FileChannel outChannel = out.getChannel();
				ByteBuffer buffer = ByteBuffer.allocate(4096); // 申请4096字节缓冲
				while (inChannel.read(buffer) != -1) {
					buffer.flip(); // 反转此缓冲区，设置当前位置指针为0，read读文件后文件指针在缓冲区末尾，需要使用flip重置
					outChannel.write(buffer);
					buffer.clear(); // 清空缓冲区
				}
			}
		}
	}

	public static int countWordInFile(String filename, String word) {
		int counter = 0;
		try (FileReader fr = new FileReader(filename)) {
			try (BufferedReader br = new BufferedReader(fr)) {
				String line = null;
				while ((line = br.readLine()) != null) {
					int index = -1;
					while (line.length() >= word.length() && (index = line.indexOf(word)) >= 0) {
						counter++;
						line = line.substring(index + word.length());
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return counter;
	}

	private static void methodInvokeTest() throws Exception {
		String str = "hello";
		Method m = str.getClass().getMethod("toUpperCase");
		System.out.println(m.invoke(str)); // HELLO
	}

	public static void main(String[] args) throws Exception {
		// CopyFile copyfile = new CopyFile();
		/*
		 * long start = System.currentTimeMillis();
		 * CopyFile.fileCopy("E:\\Desktop.zip", "E:\\testtest"); long end =
		 * System.currentTimeMillis(); System.out.println("耗时：" + (end -
		 * start));
		 */
		// System.out.println(countWordInFile("E:\\a.txt", "c"));
		/*
		 * File f = new File("E:\\"); for(File temp : f.listFiles()) {
		 * if(temp.isDirectory()) { System.out.println(temp.getName()); } }
		 */
		/* methodInvokeTest(); */
		/*
		 * Date date = new Date(); Calendar calendar = Calendar.getInstance();
		 * LocalDateTime dTime = LocalDime.now();
		 * System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		 * System.out.println(1 & 1); int a = 10; float b = 10f;
		 * System.out.println(a == b);
		 */
		int season = 0;
		String strSeason = "spring";
		switch (strSeason.toLowerCase()) {
		case "spring":
			season = 1;
			break;
		case "summer":
			season = 2;
			break;
		case "fall":
			season = 3;
			break;
		case "winter":
			season = 4;
			break;
		default:
			season = -1;// 没找到对应的季节
			break;
		}
		System.out.println(strSeason + "-->" + season);
	}
}
