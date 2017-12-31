import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
class ServerChat extends JFrame implements ActionListener, Runnable
{
	JTextArea msgArea;
	JTextField msgText;
	JButton sendButton;
	DatagramSocket ds;
	int clientport=10,serverport=11;
	Thread receiveThread;
	ServerChat() throws Exception
	{
		msgArea=new JTextArea(10,20);
		msgText=new JTextField(10);
		sendButton=new JButton("Send");
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(msgArea);
		add(msgText);
		add(sendButton);
		ds=new DatagramSocket(serverport);
		receiveThread=new Thread(this);
		receiveThread.start();
		sendButton.addActionListener(this);
		setBounds(10,10,400,400);
		setVisible(true);
	}
public void run()
{
	byte b[]=new byte[1000];
	while(true)
	{
		
		try
		{
		DatagramPacket dp=new DatagramPacket(b,b.length);
		ds.receive(dp);
		String output=new String(b,0,dp.getLength());
		msgArea.append("Others:"+output+"\n");
		}catch(Exception ee){}
	}			

}
public void actionPerformed(ActionEvent e)
{
	try
	{
	String data=msgText.getText();
	DatagramPacket dp=new DatagramPacket(data.getBytes(),data.length(),InetAddress.getLocalHost(),clientport);
	ds.send(dp);
	msgArea.append("You:"+data+"\n");
	}catch(Exception ee){}
}
public static void main(String args[]) throws Exception
{
	ServerChat sc=new ServerChat();
}

}
