package com.yangkai.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class StreamUtilTest {

	//方法1：批量关闭流，参数能传无限个,例如传入FileInputStream对象、JDBC中Connection对象都可以关闭。
	@Test
	public void testCloseAll() {
		fail("Not yet implemented");
	}

	
	/*方法2：拷贝流，将InputStream流拷到OutputStream，可以用于快速读与文件、上传下载，为了提高性能，需要使用缓冲。
	参数1：输入流
	参数2：输出流
	参数3：处理完成后是否关闭输入流
	参数4：处理完成后是否关闭输出流*/
	@Test
	public void testCopy() {
		fail("Not yet implemented");
	}

	
	//方法3：传入一个文本文件对象，默认为UTF-8编码，返回该文件内容，要求方法内部调用上面第2个方法拷贝流，结束后第1个方法关闭流
	@Test
	public void testReadTextFileInputStream() throws IOException {
		FileInputStream ips=new FileInputStream(new File("D:/yxy.txt"));
		String file = StreamUtil.readTextFile(ips);
		System.out.println(file);
	}

	
	//方法4：传入文本文件对象，返回该文件内容(3分)，并且要求内部调用上面第3个方法
	@Test
	public void testReadTextFileFile() throws FileNotFoundException, IOException {
		String file = StreamUtil.readTextFile(new File("D:/yxy.txt"));
		System.out.println(file);

	}

	

	/**
	* 方法5：从控制台读取用户输入的字符串。 
	* 参数message：用于控制台提示。例如“请输出您的姓名：”，然后用户输入姓名后回车，* 方法开始执行并读取姓名。
	*/
	@Test
	public void testReadStringFromSystemIn() {
		StreamUtil.readStringFromSystemIn ("请输入姓名:");
		                        
	}

	
	/**
	* 方法6：从控制台读取用户输入的数字。 
	* 参数message：用于控制台提示。例如“请输出您的年龄：”，然后用户输入年龄后回车，* 方法开始执行并读取年龄，如何用户输出的不是数字，或是空值（直接回车），则继续提示输入。
	*/
	@Test
	public void testReadIntFromSystemIn() {
		StreamUtil.readIntFromSystemIn("请输入年龄：");
	}

}
