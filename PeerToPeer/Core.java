package PeerToPeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.json.Json;
import javax.json.JsonObject;

public class Core extends Thread{
	private BufferedReader bufferedReader ;

	public Core(Socket socket) throws IOException {
		bufferedReader = new BufferedReader(new  InputStreamReader(socket.getInputStream()));
	}
	
	public void run() {
		boolean flag = true;
		while(flag) {
			try {
				JsonObject json = Json.createReader(bufferedReader).readObject(); 
				if(json.containsKey("username"))
					System.out.println("["+json.getString("username")+"]: "+json.getString("data"));
			}catch(Exception e) {
				flag = false ;
				interrupt();
			}
		}
	}
}
