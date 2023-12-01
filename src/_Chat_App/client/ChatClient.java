package _Chat_App.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ChatClient {
	private int port;
	private String ip;
	
	Socket sockConnect;
	
	ObjectOutputStream os;
	ObjectInputStream is;
	public ArrayList<String> inputs;
	
	public ChatClient(int port, String ip){
		this.port = port;
		this.ip = ip;
	}
	
	public void run() {
		inputs = new ArrayList<String>();
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
		try {
		while (!sockConnect.isClosed()) {
			if(sockConnect.isConnected() && is.readObject()!= null) {
			
				inputs.add((String) is.readObject());
			
			}
		}
		}
		catch (Exception e) {
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
	public String getText() {
		try {
			if(is != null) {
				return (String) is.readObject();
			}
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean getConnected() {
		if(sockConnect != null && sockConnect.isConnected()) {
			return true;
		}
		return false;
	}
}
