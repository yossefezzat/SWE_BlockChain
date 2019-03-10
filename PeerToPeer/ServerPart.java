package PeerToPeer;

import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

public class ServerPart extends Thread{
	private ServerSocket serverSocket;
	private Set<ServerPartSocket> ServerPartSockets = new HashSet<ServerPartSocket>();
	
	public ServerPart(String PortNo) throws Exception {
		serverSocket = new ServerSocket(Integer.valueOf(PortNo));
	}
	
	public void run() {
		try {
			while(true){
				ServerPartSocket ServerPartSocket = new ServerPartSocket(serverSocket.accept() , this);
				ServerPartSockets.add(ServerPartSocket);
				 ServerPartSocket.start();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	void sendMessage(String message) {
		try {
			ServerPartSockets.forEach(t-> t.getPrintWriter().println(message));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Set<ServerPartSocket> getServerPartSockets(){
	
		return ServerPartSockets;
	}
}
