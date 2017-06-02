package com.udp;

import java.io.File;
import java.util.Scanner;

public class UpLoadFile
{
	public static void main(String[] args)
	{

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
