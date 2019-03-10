package PeerToPeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.SocketException;
import javax.json.Json;



public class Organizer {
	
	
	public static void main (String args[]) throws Exception {
		System.out.println(Inet4Address.getLocalHost().getHostAddress());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("> Enter name & number of this Peer : ");
		String []SetupValues = bufferedReader.readLine().split(" ");
		ServerPart ServerPart = new ServerPart(SetupValues[1]);
		ServerPart.start();
		new Organizer().UpdateListenToPeer(bufferedReader, SetupValues[0], ServerPart);
	}
	
	public void UpdateListenToPeer(BufferedReader bufferedReader , String username, ServerPart ServerPart) throws Exception{
		System.out.println("> Enter (Space Separated) hostname : Port#");
		System.out.println("> Peer to receive messeges from (s to Skip): ");
		String input = bufferedReader.readLine();
		String[] inputValues = input.split(" ");
		if(!input.equals("s")){  
			for(int i=0 ; i < inputValues.length ; i++) {
				String[] address = inputValues[i].split(":");
				Socket socket = null ;
				try {
					socket = new Socket("172.22.144.97", Integer.valueOf(address[1]));
					new Core(socket).start();
				}catch(SocketException e) {
					if(socket !=null)
						socket.close();
					else {
						
						System.out.println("Invalid input press 's' to skip" + "\ne: " +e.toString());
					}
				}
			}
			communicate(bufferedReader , username , ServerPart);
		}
	}
	
	public void communicate(BufferedReader bufferedReader , String username, ServerPart ServerPart) {
		
		try {
			System.out.println("> You can communicate now (e to exit , c to change)");
			boolean flag = true;
			while(flag) {
				String data = bufferedReader.readLine();
				if(data.equals("e")) {
					flag = false;
					break;
				} else if(data.equals("c")){
					UpdateListenToPeer(bufferedReader , username , ServerPart);
				} else {
					StringWriter stringWritter = new StringWriter();
					Json.createWriter(stringWritter).writeObject(Json.createObjectBuilder()
																	 .add("username", username)
																	 .add("data", data)
																	 .build());
					
					
					ServerPart.sendMessage(stringWritter.toString());
				}
			}
			
		}catch(Exception e) {
			
		}
	}
}
