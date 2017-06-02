package com.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Test2
{
	public static void main(String[] args) throws Exception
	{
		byte[] b=new byte[1024];
		DatagramSocket socket=new DatagramSocket(8088);
		DatagramPacket packet=new DatagramPacket(b, 1024);
		socket.receive(packet);
		int length = packet.getLength();
		String string=new String(b, 0, length);
		System.out.println(string);
	}

}
