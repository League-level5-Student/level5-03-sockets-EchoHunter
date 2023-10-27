package _Chat_App.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ChatServer extends Thread {
	private ServerSocket serSock;
	private Socket sock;
	private ObjectOutputStream oOS;
	private ObjectInputStream oIS;
	private int portNum;

	public ChatServer(int pnum) throws IOException {
		
		portNum = pnum;
		serSock = new ServerSocket(portNum);
	}

	public void run() {
		try {
			sock = serSock.accept();

			oOS = new ObjectOutputStream(sock.getOutputStream());
			oIS = new ObjectInputStream(sock.getInputStream());

			oOS.flush();
		} catch (IOException e) {
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
			oOS.flush();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getText() {
		try {
			if(oIS != null) {
				return (String) oIS.readObject();
			}
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
