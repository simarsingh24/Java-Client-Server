import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class server1 extends JFrame{
	public static void main(String[] args){
		
		JFrame frame= new JFrame("Server");
		JButton fetchButton =new JButton("Fetch");
		fetchButton.setBounds(20,10,95,30);
		
		  final JTextField tf=new JTextField();  
		    tf.setBounds(50,50, 150,20);  
		    
		frame.add(fetchButton);
		frame.setSize(500,500);
		frame.setLayout(null);
		frame.setVisible(true);
		
		fetchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tf.setText("Welcome to Javatpoint.");  
				
			}
		});
	
		frame.add(tf);
		
	
	}
}


/*
public class Server extends JFrame
{
public static void main(String[] args)
{
new Server();
}


public Server()
{

String msg_received;
DefaultListModel<String> l1 = new DefaultListModel<>();
JList<String> list = new JList<>(l1);  
JPanel panel1 = new JPanel();
JLabel label1 = new JLabel("Data Received");
panel1.add(label1);
while(true){          
try{
ServerSocket socket = new ServerSocket(8080);
Socket clientSocket = socket.accept();
DataInputStream DIS = new DataInputStream(clientSocket.getInputStream());
msg_received = DIS.readUTF();
clientSocket.close();
socket.close();}
catch(IOException e){msg_received=e.toString();}

if(msg_received==null){
msg_received="harsimar";
}

  
          l1.addElement(msg_received);  
          list.setBounds(50,50, 200,200);  
          this.add(list);  
          
this.add(panel1);
this.setTitle("Server");
this.setSize(500,500);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setVisible(true);
}
}
}
*/
