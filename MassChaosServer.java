
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MassChaosServer {
	
	
	public static void main (String args[]) throws Exception {
		
		DatagramSocket serverSocket = null;
		
		
		//byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
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
				System.out.println(sentence);
				sendData = sentence.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
				serverSocket.send(sendPacket);
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress1, port1);
				serverSocket.send(sendPacket);
				
			} 
		}
		
	}
		
}
	
	/*// Maintain list of all client sockets for broadcast
	private ArrayList<Socket> socketList;

	public MassChaosServer()
	{
		socketList = new ArrayList<Socket>();
	}

	private void getConnection()
	{
		// Wait for a connection from the client
		try
		{
			System.out.println("Waiting for client connections on port 7654.");
			ServerSocket serverSock = new ServerSocket(7654);
			// This is an infinite loop, the user will have to shut it down
			// using control-c
			while (true)
			{
				Socket connectionSock = serverSock.accept();
				// Add this socket to the list
				socketList.add(connectionSock);
				// Send to ClientHandler the socket and arraylist of all sockets
				MassChaosClientHandler handler = new MassChaosClientHandler(connectionSock, this.socketList);
				Thread theThread = new Thread(handler);
				theThread.start();
			}
			// Will never get here, but if the above loop is given
			// an exit condition then we'll go ahead and close the socket
			//serverSock.close();
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		MassChaosServer server = new MassChaosServer();
		server.getConnection();
	}
	
	private DatagramSocket socket;
	private Game game;
			
	public MassChaosServer(Game game) {
		this.game = game;
		try {
			this.socket = new DatagramSocket(7654);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
	}
	
	public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }
	
	public void sendData(byte[] data, InetAddress ipAddress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

