import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	ServerSocket myServerSocket;
	   boolean ServerOn = true;
	   public Server() { 
	      try {
	         myServerSocket = new ServerSocket(25569);
	      } catch(IOException ioe) { 
	         System.out.println("Could not create server socket on port 25569. Quitting.");
	         System.exit(-1);
	      } 
	      
	      while(ServerOn) { 
	         try { 
	            Socket clientSocket = myServerSocket.accept();
	            ClientServiceThread cliThread = new ClientServiceThread(clientSocket);
	            cliThread.start(); 
	         } catch(IOException ioe) { 
	            System.out.println("Exception found on accept. Ignoring. Stack Trace :"); 
	            ioe.printStackTrace(); 
	         }  
	      } 
	      try { 
	         myServerSocket.close(); 
	         System.out.println("Server Stopped"); 
	      } catch(Exception ioe) { 
	         System.out.println("Error Found stopping server socket"); 
	         System.exit(-1); 
	      } 
	   }
}
