package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class PrimeNumberClient {
	
private DataInputStream fromServer = null;	
private DataOutputStream toServer = null;
Socket socket;
	
	public void connect(){
	
	try {
		
		// Attempts Connection to Server on User Computer
		socket = new Socket("localhost",4141);
		fromServer = new DataInputStream(socket.getInputStream());
		toServer = new DataOutputStream(socket.getOutputStream());
		
		
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}
	
	public String calculate(double numberToBeCheckedPrime) {
		
		try {
			toServer.writeDouble(numberToBeCheckedPrime);
			toServer.flush();
			
			return fromServer.readUTF();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	
	public void close() {
		
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}