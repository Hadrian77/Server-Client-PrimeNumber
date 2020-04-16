package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PrimeNumberServer extends Thread {
	
	String output;
	ServerSocket serverSocket;
	Socket socket;
	
	public void run() {
		
		
		 try {
			serverSocket = new ServerSocket(4141);
			socket = serverSocket.accept();
			
			DataInputStream fromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
			
			
			
			
			while(true) {
				
				output = checkPrime(fromClient.readDouble());
				toClient.writeUTF(output);
				System.out.println(output);
					
				}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		
	}
	
	 public String checkPrime(double n){    
		 
		 
		 double i,m=0,flag=0;      
		  m=n/2;      
		  if(n==0||n==1){  
		   return (n+" is not prime number");      
		  }
		  else{  
		   for(i=2;i<=m;i++){      
		    if(n%i==0){      
		     return (n+" is not prime number");      
		           
		    }      
		   }      
		   if(flag==0)  { return (n+" is a prime number"); }  
		  }//end of else  
		return null;
		}    
	 
	 public void close() {
		 
		 try {
			serverSocket.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }

}
