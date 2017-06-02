package com.udp;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test_Chat extends Frame
{
	private static final long serialVersionUID = 1L;
	private Button bt1, bt2, bt3, bt4;
	private TextArea area1, area2;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private BufferedWriter bw;
	private BufferedReader br;
	private DatagramSocket socketR, socketS;

	public Test_Chat()
	{
		System.out.println("这是第四次改变");
		Init();
		DownPannel();
		TopPannel();
		Event();
		new Thread()
		{
			public void run()
			{
				Receive();
			};
		}.start();
	}

	private void Event()
	{
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				socketS.close();//
				System.exit(0);
			}
		});
		bt1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Send();
			}
		});

		bt2.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				Record();
			}
		});
		bt3.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				area1.setText("");
			}
		});

		bt4.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Shake();
			}
		});

	}

	protected void Shake()
	{
		int x = this.getLocation().x;
		int y = this.getLocation().y;
		for (int i = 0; i < 1000; i++)
		{
			this.setLocation(x + 5, y);
			this.setLocation(x, y);
			this.setLocation(x - 5, y);

		}
		this.setLocation(x, y);
	}

	/** 记录 */
	protected void Record()
	{
		try
		{
			area1.setText("");
			bw.flush();
			br = new BufferedReader(new FileReader("config.txt"));
			while(br.read()!=-1)
			{
				area1.append(br.readLine());
				area1.append("\n");
			}
			br.close();
		} catch (Exception e)
		{
		}
	}

	protected void Send()
	{
		String str = area2.getText();
		if (str.length() <= 0 || str.length() > 1024)
		{
			return;
		}
		try
		{
			DatagramPacket packet = new DatagramPacket(str.getBytes("utf-8"),
					str.getBytes().length, InetAddress.getByName("localhost"),
					8888);
			String msg = "发送出：" + sdf.format(new Date()) + "\n   "
					+ area2.getText() + "\n";
			area1.append(msg);
			socketS.send(packet);
			bw.write(msg);
			area2.setText("");
		} catch (Exception e)
		{
		}
	}

	public void Receive()
	{
		boolean ing = true;
		byte[] b;
		try
		{
			socketR = new DatagramSocket(8888);
			while (ing)
			{
				b = new byte[1024];
				DatagramPacket packet = new DatagramPacket(b, 1024);
				socketR.receive(packet);
				String msg = "接收到：" + sdf.format(new Date()) + "\n" + "   "
						+ new String(b, 0, b.length, "utf-8")+"\n";
				area1.append(msg);
				area1.append("\n");
				bw.write(msg+"\n");
			}
			socketR.close();
		} catch (Exception e)
		{
		}
	}

	private void DownPannel()
	{
		Panel down = new Panel();
		TextField ip = new TextField(15);// ip
		bt1 = new Button("发送");
		bt2 = new Button("记录");
		bt3 = new Button("清屏");
		bt4 = new Button("震动");
		down.add(ip);
		down.add(bt1);
		down.add(bt2);
		down.add(bt3);
		down.add(bt4);
		this.add(down, BorderLayout.SOUTH);//
	}

	private void TopPannel()
	{
		Panel top = new Panel();
		area1 = new TextArea();
		area2 = new TextArea(5, 1);
		top.setLayout(new BorderLayout());
		top.add(area2, BorderLayout.SOUTH);
		top.add(area1, BorderLayout.CENTER);
		area1.setEditable(false);
		area1.setBackground(Color.WHITE);
		area2.setFont(new Font("xxx", Font.PLAIN, 20));
		this.add(top, BorderLayout.CENTER);
	}

	private void Init()
	{
		try
		{
			socketS = new DatagramSocket();
			this.setLocation(50, 50);
			this.setSize(400, 600);
			this.setVisible(true);

			bw = new BufferedWriter(new FileWriter("config.txt",true));
		} catch (Exception e)
		{

		}
	}

	public static void main(String[] args)
	{
		Test_Chat chat = new Test_Chat();

	}

}
