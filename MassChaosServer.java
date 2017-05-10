
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MassChaosServer {
	
	
	public static void main (String args[]) throws Exception {
		
		DatagramSocket serverSocket = null;
		
		
		//byte[] receiveData = new byte[1024];
		//byte[] sendData = new byte[1024];
		InetAddress IPAddress = null, IPAddress1 = null, IPAddress2 = null;
		DatagramPacket receivePacket = null;
		DatagramPacket sendPacket = null;
		int port = 0, port1 = 0, port2 = 0;
		
		try
		{
			serverSocket = new DatagramSocket(9876);
		}
	
	catch(Exception e)
		{
			System.out.println("Failed to open UDP socket");
			System.exit(0);
		}

		
		
		while(true)
		{
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			
			if (port == 0)
			{
				System.out.println("Server is running. Waiting for players to connect.");
				receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				IPAddress = receivePacket.getAddress();
				port = receivePacket.getPort();
				//IPAddress1 = receivePacket.getAddress();
				//port1 = receivePacket.getPort();
				
				 
				//int port = receivePacket.getPort();
				//String capitalizedSentence = sentence.toUpperCase();
				String capitalizedSentence = "NO";
				sendData = capitalizedSentence.getBytes();
				
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
			}
			else if (port != 0 && port1 == 0)
			{
				System.out.println("Player 1 has connected.");
				receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				IPAddress1 = receivePacket.getAddress();
				port1 = receivePacket.getPort();
				//IPAddress1 = receivePacket.getAddress();
				//port1 = receivePacket.getPort();
				
				 
				//int port = receivePacket.getPort();
				//String capitalizedSentence = sentence.toUpperCase();
				String capitalizedSentence = "YES";
				sendData = capitalizedSentence.getBytes();
				
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress1, port1);
				serverSocket.send(sendPacket);
			}
			else
			{	
				System.out.println("Player 2 has connected.");
				receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				
				//if isGameOver is true then BYE is sent and server socket is closed
				if (sentence.trim().equals("BYE")) {
					serverSocket.close();
					break;
				}
				
				System.out.println(sentence);
				sendData = sentence.getBytes();
				int portTemp = receivePacket.getPort();
				
				if(portTemp == port1)
				{
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
					serverSocket.send(sendPacket);
				}
				else if(portTemp == port)
				{
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress1, port1);
					serverSocket.send(sendPacket);
				}
				
				
			} 
		}
		
	}
}
	
