package com.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class ReflectTest {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception{
		/*Student stu1 = new Student();
		Class stuClass = stu1.getClass(); //获取class对象
		System.out.println(stuClass.getName());
		
		Class stuClass2 = Student.class;
		System.out.println(stuClass == stuClass2);
		
		try {
			Class stuClass3 = Class.forName("com.test.Student");
			System.out.println(stuClass == stuClass3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
		/**
		 * 测试获取构造方法
		 */
		//加载Class对象
		/*Class clazz = Class.forName("com.test.Student");
		
		//获取所有公有构造器方法
		Constructor[] conArray = clazz.getConstructors();
		for(Constructor con : conArray) {
			System.out.println(con);
		}
		
		//获取所有构造方法
		System.out.println("-----------------");
		conArray = clazz.getDeclaredConstructors();
		for(Constructor con : conArray) {
			System.out.println(con);
		}
		
		System.out.println("*****************获取公有、无参的构造方法*******************************");
		Constructor con = clazz.getConstructor(null);
		//1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
		//2>、返回的是描述这个无参构造函数的类对象。
	
		System.out.println("con = " + con);
		//调用构造方法
		Object object = con.newInstance();
		//System.out.println(object);
		System.out.println("******************获取私有构造方法，并调用*******************************");
		con = clazz.getDeclaredConstructor(int.class);
		System.out.println(con);*/
		
		//获取Class对象
		Class stuClass = Class.forName("com.test.Student");
		//获取字段
		System.out.println("*********获取所有公有字段**********");
		Field[] fieldArray = stuClass.getFields();
		for(Field field : fieldArray) {
			System.out.println(field);
		}
		System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
		fieldArray = stuClass.getDeclaredFields();
		for(Field field : fieldArray) {
			System.out.println(field);
		}
		System.out.println("*************获取公有字段**并调用***********************************");
		Field field = stuClass.getField("name");
		System.out.println(field);
		//获取一个对象
		Object object = stuClass.getConstructor().newInstance(); //产生Student对象 ：Student stu = new Student()
		//为字段设值
		field.set(object, "刘德华"); //为Student对象中的name属性赋值 ：stu.name = "刘德华";
		//验证
		Student student = (Student)object;
		System.out.println(student.name);
		
		System.out.println("**************获取私有字段****并调用********************************");
		field = stuClass.getDeclaredField("phoneNum");
		System.out.println(field);
		field.setAccessible(true);  //暴力反射，解除私有限定
		field.set(object, "13318529225");
		System.out.println(student);
	}
}
