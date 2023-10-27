package _Chat_AppGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;
import _Chat_App.server.ChatServer;

public class GUI_For_Chat_App implements KeyListener {
	
	JFrame frame;
	JPanel panel;
	JTextField inputTextBox;
	JTextField outputTypeBox;
	Server server;
	Client client;
	int response;
	GUI_For_Chat_App(){
		
	}
	public void runGUI (){
		frame = new JFrame();
		panel = new JPanel();
		inputTextBox = new JTextField();
		inputTextBox.setEditable(false);
		outputTypeBox = new JTextField();
		
		frame.addKeyListener(this);
		
		 response = JOptionPane.showConfirmDialog(null,"Would you like to host a server?", "Choices: ", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION) {
			server = new Server(Integer.parseInt(JOptionPane.showInputDialog("Enter your chosen port number")));
			try {
				ChatServer c = new ChatServer(server.getPort());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			
		}else {
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			client = new Client(ipStr, Integer.parseInt(prtStr));
			
		}
	}
	private void sendInput(){
		if(response == JOptionPane.YES_OPTION) {
			inputTextBox.getText();
		} else if(response == JOptionPane.NO_OPTION) {
			
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == 13) {
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
