import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.InputStream;

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

/*
  JTextField t1;  
    t1=new JTextField(msg_received);  
    t1.setBounds(50,100, 200,30);  
this.add(t1);
*/	
  
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

