package _Chat_App.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import _Chat_AppGUI.GUI_For_Chat_App;

public class ChatServer extends Thread {
	private ServerSocket serSock;
	private Socket sock;
	private ObjectOutputStream oOS;
	private ObjectInputStream oIS;
	private int portNum;
	private GUI_For_Chat_App gui;
	
	public ChatServer(int pnum, GUI_For_Chat_App gui) throws IOException {
		
		portNum = pnum;
		serSock = new ServerSocket(portNum);
		this.gui = gui;
	}

	public void run() {
		
		try {
			sock = serSock.accept();

			
			
			oOS = new ObjectOutputStream(sock.getOutputStream());
			oIS = new ObjectInputStream(sock.getInputStream());

			oOS.flush();
			while(sock.isConnected()){	
				gui.textBoxAdd((String)oIS.readObject() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}
	public int getPort() {
		return portNum;
	}
	public void sendText(String text) {
		try {
			if(oOS!=null) {
			oOS.writeObject(text);
			gui.textBoxAdd(text);
			oOS.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
