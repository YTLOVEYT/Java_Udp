package com.udp;

import java.io.File;
import java.util.Scanner;

public class UpLoadFile
{
	public static void main(String[] args)
	{
		//这是第二次提交 哈哈哈哈哈哈哈，改变1
		System.out.println("这是一次质的飞跃");
	}

	private static File getFile()
	{
		Scanner scanner = new Scanner(System.in);
		System.err.println("请输入文件路径");
		while (true)
		{
			String line = scanner.nextLine();
			File file = new File(line);
			if (!file.exists())
			{
				System.out.println("文件不存在");

			} else
			{
				if (file.isDirectory())
				{
					System.err.println("是文件夹");
				}
				else if (!file.isFile())
				{
					System.out.println("不是文件");
				}
				else 
				{
					return file;
				}
			}
		}

	}

}
