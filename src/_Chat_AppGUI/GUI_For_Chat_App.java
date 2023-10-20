package _Chat_AppGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

public class GUI_For_Chat_App implements KeyListener {
	
	JFrame frame;
	JPanel panel;
	JTextField inputTextBox;
	JTextField outputTypeBox;
	Server server;
	Client client;
	GUI_For_Chat_App(){
		
	}
	public void runGUI (){
		frame = new JFrame();
		panel = new JPanel();
		inputTextBox = new JTextField();
		inputTextBox.setEditable(false);
		outputTypeBox = new JTextField();
		
		frame.addKeyListener(this);
		
		int response = JOptionPane.showConfirmDialog(null,"Would you like to host a server?", "Choices: ", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION) {
			server = new Server(Integer.parseInt(JOptionPane.showInputDialog("Enter your chosen port number")));
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
		}
	}
	private void sendInput(){
		
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
