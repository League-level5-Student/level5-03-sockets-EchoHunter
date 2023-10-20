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

	ChatServer() throws IOException {
		String pnum = JOptionPane.showInputDialog("Input chosen port number");
		portNum = Integer.parseInt(pnum);
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

}
