package PeerToPeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerPartSocket extends Thread{
	private ServerPart ServerPart;
	private Socket socket;
	private PrintWriter printWriter;
	
	public ServerPartSocket(Socket socket , ServerPart ServerPart) {
		this.ServerPart = ServerPart;
		this.socket = socket;
	}
	
	public void run() {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.printWriter = new PrintWriter(socket.getOutputStream() , true);
			while(true) {
				ServerPart.sendMessage(bufferedReader.readLine());
			}
			
		}catch(Exception e) {
			ServerPart.getServerPartSockets().remove(this);
		}
	}
	
	public PrintWriter getPrintWriter() {
		return printWriter;
	}
	
	
}
