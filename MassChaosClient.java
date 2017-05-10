import java.util.Scanner;
import java.net.*;
import java.io.*;
import javax.swing.JFrame;
import java.util.*;

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
				sendData = toSend.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
				clientSocket.send(sendPacket);
				
				String bullets = listener.getBullets();
				sendData = bullets.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
				clientSocket.send(sendPacket);
				
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				clientSocket.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData());
				String[] mS = modifiedSentence.split(",");
				
								if(mS[0].equals("B"))
				{
					ArrayList<int[]> bArr = new ArrayList<int[]>();
					for (int i = 1; i < mS.length-1; i+=2) {
						int[] tempB = new int[2];
						tempB[0] = Integer.parseInt(mS[i]);
						tempB[1] = Integer.parseInt(mS[i+1]);
						System.out.println(mS[i]+" "+mS[i+1]);
						bArr.add(tempB);
					}
					listener.bullPos(bArr);
				}
					
				else
				{
					int xP = Integer.parseInt(mS[0]);
					int yP = Integer.parseInt(mS[1]);
					listener.newPos(xP,yP);
				}
		
				if (listener.isGameOver == true) {
					//System.out.println("it works");
					toSend = "BYE";
					System.out.println(toSend);
					sendData = toSend.getBytes();
					sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
					clientSocket.send(sendPacket);
				}
			}
	}

}

