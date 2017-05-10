import java.util.Scanner;
import java.net.*;
import java.io.*;
import javax.swing.JFrame;

public class MassChaosClient
{

	public static void main (String args[]) throws Exception 
	{
		//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("localhost");
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
		  //String sentence = inFromUser.readLine();
		  String sentence = "Connection established!";
		  sendData = sentence.getBytes();
		  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		  clientSocket.send(sendPacket);
		  while (true)
		  {
			  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			  clientSocket.receive(receivePacket);
			  String modifiedSentence = new String(receivePacket.getData());
			  System.out.println(modifiedSentence);
			  if (modifiedSentence.trim().equals("YES"))
			  {
				  break;
			  }
			  else
			  {
				  continue;
			  }
		  }
		  //System.out.println("FROM SERVER:" + modifiedSentence);
		  System.out.println("FROM SERVER: CONNECTION ESTABLISHED!");
		  //MoveBall game = new MoveBall();
			JFrame frame = new JFrame();
			MyKeyListener listener = new MyKeyListener();
			frame.add(listener);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800,600);		  
			while(true)
			{
				int[] getPosition = new int[2];
				getPosition[0] = listener.x;
				getPosition[1] = listener.y;
				String toSend = getPosition[0]+","+getPosition[1]+",";
				//System.out.println(toSend);
				sendData = toSend.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
				clientSocket.send(sendPacket);
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData());
				String[] mS = modifiedSentence.split(",");
				int xP = Integer.parseInt(mS[0]);
				int yP = Integer.parseInt(mS[1]);
				listener.newPos(xP,yP);
				//System.out.println(xP+ "  "+yP);
				//clientSocket.receive(receivePacket);
				//modifiedSentence = new String(receivePacket.getData());
				//System.out.println(modifiedSentence);
				
				if (listener.isGameOver == true) {
				System.out.println("it works");
				//toSend = "BYE";
				//System.out.println(toSend);
				//sendData = toSend.getBytes();
				//sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
				//clientSocket.send(sendPacket);
				}
			}
			
			
		  //clientSocket.close();
	}
	
	/*public static void main(String[] args)
	{
		try
		{
			String hostname = "localhost";
			int port = 7654;

			System.out.println("Connecting to server on port " + port);
			Socket connectionSock = new Socket(hostname, port);

			DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());

			System.out.println("Connection made.");

			MassChaosClientListener listener = new MassChaosClientListener(connectionSock);
			Thread theThread = new Thread(listener);
			theThread.start();
			
			
			// Read input from the keyboard and send it to everyone else.
			// The only way to quit is to hit control-c, but a quit command
			// could easily be added.
			Scanner keyboard = new Scanner(System.in);
			while (true)
			{
				String data = keyboard.nextLine();
				serverOutput.writeBytes(data + "\n");
			}
			
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
			
	public MassChaosClient(Game game, String ipAddress) {
		this.game = game;
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName (ipAddress);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
	}
	
	public void run(){
		while (true) {
			byte [] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String message = new String(packet.getData());
			System.out.println("SERVER > " + message); 
		}
	}
	
	public void sendData(byte [] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 7654);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}

