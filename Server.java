import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

class Frame extends WindowAdapter implements ActionListener
{
	public JFrame frame;
	public JButton bSend;
	public JTextField text;
	public JLabel label;
	
	public String str1,str2;
	public ServerSocket ss;
	public Socket s;
	public PrintStream ps;
	public BufferedReader br1;
	
	public Frame(int width,int height,String Title) 
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
		label.setBounds(200,100,200,100);
		frame.add(label);
		
		bSend.addActionListener(this);
		
		frame.setLayout(null);
		frame.setVisible(true);
		
		
		try
		{
			ss = new ServerSocket(2100);
			//System.out.println("Server is running at port no 2100 and waiting for client request");
		
			s = ss.accept();
			//System.out.println("Request of client gets accepted");
		
			ps = new  PrintStream(s.getOutputStream());
		
			br1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
			while((str1 = br1.readLine()) != null)
			{
				//System.out.println("Client says : "+str1);
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

class Server
{
	public static void main(String A[])
	{
		Frame obj = new Frame(500,500,"Server Side");
	}
}