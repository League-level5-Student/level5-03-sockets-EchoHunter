package _Chat_AppGUI;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;
import _Chat_App.client.ChatClient;
import _Chat_App.server.ChatServer;

public class GUI_For_Chat_App implements KeyListener {
	
	JFrame frame;
	JPanel panel;
	JTextArea inputTextBox;
	JTextField outputTypeBox;
	Server server;
	Client client;
	ChatServer cServ;
	ChatClient cClien;
	int response;
	GUI_For_Chat_App(){
		
	}
	public void runGUI (){
		frame = new JFrame();
		panel = new JPanel();
		inputTextBox = new JTextArea(20,20);
		inputTextBox.setEditable(false);
		outputTypeBox = new JTextField(20);
		outputTypeBox.addKeyListener(this);
		
		panel.add(inputTextBox);
		panel.add(outputTypeBox);
		frame.add(panel);
		 response = JOptionPane.showConfirmDialog(null,"Would you like to host a server?", "Choices: ", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION) {
			server = new Server(Integer.parseInt(JOptionPane.showInputDialog("Enter your chosen port number")));
			try {
				cServ = new ChatServer(server.getPort(), this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			
		}else {
			String ipStr = "192.168.1.220"; //= JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = "8080";//JOptionPane.showInputDialog("Enter the port number");
			client = new Client(ipStr, Integer.parseInt(prtStr));
			cClien = new ChatClient(Integer.parseInt(prtStr), ipStr, this);
				
		}
		frame.pack();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	private void sendInput(){
		if(response == JOptionPane.YES_OPTION) {
			cServ.sendText("Server: " + outputTypeBox.getText()+ "\n");
		//	System.out.println(outputTypeBox.getText());
			outputTypeBox.setText("");
		} else if(response == JOptionPane.NO_OPTION) {
			cClien.sendText("Client: " + outputTypeBox.getText()+"\n");
			outputTypeBox.setText("");
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==Event.ENTER) {
			sendInput();
		}
	}
	public void textBoxAdd(String textAdd) {
			inputTextBox.append(textAdd);
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
