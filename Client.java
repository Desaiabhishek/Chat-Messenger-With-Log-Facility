import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

class ChatFrame extends WindowAdapter implements ActionListener
{
	public JFrame frame;
	public JButton bSend;
	public JTextField text;
	public JLabel label;
	
	String str1,str2;
	public Socket s;
	public PrintStream ps;
	public BufferedReader br1;
	
	public ChatFrame(int width,int height,String Title) //throws Exception
	{	
		
		frame = new JFrame(Title);
		frame.setSize(width,height);
		
		frame.addWindowListener(this);
		
		bSend = new JButton("Send");
		bSend.setBounds(350,380,80,40);		
		frame.add(bSend);
		
		text = new JTextField();
		text.setBounds(20,380,300,40);
		frame.add(text);
		
		label = new JLabel();
		label.setBounds(180,20,200,120);
		frame.add(label);
		
		bSend.addActionListener(this);
		
		frame.setLayout(null);
		frame.setVisible(true);
		
		try
		{
			//System.out.println("Client application is running...");

			s = new Socket("localhost",2100);	
			//System.out.println("Client is waiting for the server to accept the request");
		
			ps = new  PrintStream(s.getOutputStream());
		
			br1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
			while((str1 = br1.readLine()) != null)
			{
				//System.out.println("Server says : "+str1);
				label.setText(str1);
			}
			
		}
		catch(Exception eobj)
		{
			System.out.println(eobj);
		}
		
	}
	
	public void windowClosing(WindowEvent obj)
	{
		System.exit(0);
	}
	
	public void actionPerformed(ActionEvent obj)
	{	
		try
		{
			str2 = text.getText();
			
			if(obj.getSource() == bSend)
			{
				//label.setText("Text : "+str1);
				ps.println(str2);				
				text.setText(null);
			}
		}
		catch(Exception eobj)
		{}
	}
	
}

class Client
{
	public static void main(String A[]) throws Exception
	{
		ChatFrame obj = new ChatFrame(500,500,"Client Side");
	}
}