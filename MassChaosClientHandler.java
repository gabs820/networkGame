/**
 * NimClientHandler.java
 *
 * This class handles communication between the client
 * and the server.  It runs in a separate thread but has a
 * link to a common list of sockets to handle broadcast.
 *
 */
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class MassChaosClientHandler implements Runnable
{
	private Socket connectionSock = null;
	private ArrayList<Socket> socketList;


	MassChaosClientHandler(Socket sock, ArrayList<Socket> socketList)
	{
		this.connectionSock = sock;
		this.socketList = socketList;
		//this.game = game;	// Keep reference to master list
	}

	public void run()
	{
    // Get data from a client and send it to everyone else
		try
		{
			System.out.println("Connection made with socket " + connectionSock + "\n");
			BufferedReader clientInput = new BufferedReader(
				new InputStreamReader(connectionSock.getInputStream()));

				DataOutputStream localOutput = new DataOutputStream(connectionSock.getOutputStream());


			while (true)
			{
				// Get data sent from a client

				String clientText = clientInput.readLine();
				if (clientText != null)
				{
					System.out.println(clientText);
				}
				else
				{
				  // Connection was lost
				  System.out.println("Closing connection for socket " + connectionSock);
				   // Remove from arraylist
				   socketList.remove(connectionSock);
				   connectionSock.close();
				   break;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
			// Remove from arraylist
			socketList.remove(connectionSock);
		}
	}
}
