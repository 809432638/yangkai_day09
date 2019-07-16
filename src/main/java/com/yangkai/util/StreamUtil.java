package com.yangkai.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.StandardSocketOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @ClassName: StreamUtil 
 * @Description: 工具类
 * @author: 嫌疑人:杨某 
 * @date: 2019年7月16日 上午8:41:18
 */
public class StreamUtil {

	//方法1：批量关闭流，参数能传无限个,例如传入FileInputStream对象、JDBC中Connection对象都可以关闭。
	public static void closeAll(Closeable... closeables) {
		if(closeables!=null) {
			for (Closeable closeable : closeables) {
				try {
					closeable.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
	}

	List l=new ArrayList<>();
	
	/*方法2：拷贝流，将InputStream流拷到OutputStream，可以用于快速读与文件、上传下载，为了提高性能，需要使用缓冲。
	参数1：输入流
	参数2：输出流
	参数3：处理完成后是否关闭输入流
	参数4：处理完成后是否关闭输出流*/
	public static void copy(InputStream src, OutputStream out, boolean isCloseInputStream, boolean isCloseOutputStream)  throws IOException{
		  BufferedInputStream bif=new BufferedInputStream(src);
		  BufferedOutputStream oos=new BufferedOutputStream(out);
			 byte[] b=new byte[1024];
			int i=0;
			while((i=src.read(b))!=-1) {
				oos.write(b);
			}
			
			if(isCloseOutputStream) {
				out.close();
				oos.close();
			}
			
			if(isCloseInputStream) {
				src.close();
				bif.close();
			}
	}

	
	

	//方法3：传入一个文本文件对象，默认为UTF-8编码，返回该文件内容，要求方法内部调用上面第2个方法拷贝流，结束后第1个方法关闭流
	public static String readTextFile(InputStream src) throws IOException{
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		byte[] b=new byte[1024];
		int i=0;
		while((i=src.read(b))!=-1) {
			out.write(b);
		}
		 closeAll();
		return out.toString("UTF-8");
	}
	

	//方法4：传入文本文件对象，返回该文件内容(3分)，并且要求内部调用上面第3个方法
	public static String readTextFile(File txtFile) throws FileNotFoundException, IOException{
		return readTextFile(new FileInputStream(txtFile));
	}
	
	

	/**
	* 方法5：从控制台读取用户输入的字符串。 
	* 参数message：用于控制台提示。例如“请输出您的姓名：”，然后用户输入姓名后回车，* 方法开始执行并读取姓名。
	*/
	public static String readStringFromSystemIn(String message){
		Scanner ac=new Scanner(System.in);
		System.out.println(message);
		String string = ac.next();
		System.out.println("输入的姓名是："+string);
		return string;
	}

	
	
	/**
	* 方法6：从控制台读取用户输入的数字。 
	* 参数message：用于控制台提示。例如“请输出您的年龄：”，然后用户输入年龄后回车，* 方法开始执行并读取年龄，如何用户输出的不是数字，或是空值（直接回车），则继续提示输入。
	*/
	public static int readIntFromSystemIn(String message){
		Scanner sc=new Scanner(System.in);
		System.out.println(message);
		
		//下一个输入的是否是数字
		   //不是数字继续调用方法重新输入
		   if(!sc.hasNextInt()) {
			return  readIntFromSystemIn(message); 
		   }
		   int nextInt = sc.nextInt();
			System.out.println("输入的年龄是："+nextInt);   
		return nextInt;
	}
	
	
	
}
