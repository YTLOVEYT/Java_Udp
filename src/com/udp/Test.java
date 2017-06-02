package com.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Test
{

	public static void main(String[] args) throws Exception
	{
		String string="hgffsf";
		DatagramSocket  socket= new  DatagramSocket();
		DatagramPacket packet=new DatagramPacket(string.getBytes(), string.length(),InetAddress.getByName("localhost"),8088);
		socket.send(packet);
		socket.close();
	}
	

}
