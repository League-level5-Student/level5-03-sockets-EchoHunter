package _Chat_App.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	private int port;
	private String ip;
	
	Socket sockConnect;
	
	ObjectOutputStream os;
	ObjectInputStream is;
	
	ChatClient(int port, String ip){
		this.port = port;
		this.ip = ip;
	}
	
	public void run() {
		try {
			sockConnect = new Socket(ip,port);
			
			os = new ObjectOutputStream(sockConnect.getOutputStream());
			is = new ObjectInputStream(sockConnect.getInputStream());
			
			os.flush();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendText(String text) {
		try {
			if(os != null) {
				os.writeObject(text);
				os.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
