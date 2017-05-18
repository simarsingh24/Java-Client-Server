import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
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
import java.io.FileWriter;

public class server1 extends JFrame{
	
	public static DefaultListModel<String> l1 = new DefaultListModel<>();
	public static final String mobile_ip="192.168.1.35";
	public static final int mobile_port=8080;
	
	public static void main(String[] args){
		
		JFrame frame= new JFrame("Server");
		
		JLabel connectLabel=new JLabel("Click connect to connect to this IP: ");
		connectLabel.setBounds(20,20,300,20);
		
		JTextField mobileIPTextField=new JTextField("192.168.1.35");
		mobileIPTextField.setBounds(20,40,100,20);
		
		JTextField portTextField=new JTextField("8080");
		portTextField.setBounds(130,40,50,20);
		
		JButton connectButton=new JButton("Connect");
		connectButton.setBounds(190,40,100,20);
		
		JButton fetchButton =new JButton("Fetch");
		fetchButton.setBounds(20,70,95,30);

		JButton writeButton =new JButton("Print");
		writeButton.setBounds(20,340,95,30);

		
		JList<String> list = new JList<>(l1);  
		list.setBounds(50,120, 200,200);  
		
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Data Received");
		panel1.add(label1);
		
		frame.add(connectButton);
		frame.add(portTextField);
		frame.add(connectLabel);
		frame.add(mobileIPTextField);
		frame.add(writeButton);
		frame.add(list);
		frame.add(panel1);	
		frame.add(fetchButton);
		frame.setSize(500,500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fetchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sendTrigger();
				fetchData();
				//fetchData();
			}
		});
		
		writeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				writeToFile(l1);	
			}
		});	
	
	}
	
	public static void sendTrigger(){
		String s="transmit_data";
		try{
			Socket socket = new Socket(mobile_ip,mobile_port);
			DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
			DOS.writeUTF(s);
			socket.close();
			}
		catch(IOException e){}
		l1.clear();
	}
	
	public static void fetchData(){
		String msg_received;
		try{
			ServerSocket socket=new ServerSocket(8080);
			Socket clientSocket=socket.accept();
			DataInputStream DIS = new DataInputStream(clientSocket.getInputStream());
			msg_received = DIS.readUTF();
			clientSocket.close();
			socket.close();
		
		}
		catch(IOException e){	
			msg_received=e.toString();
		}
		if(msg_received==null){
			msg_received="harsimar";
		}
		convertToList(msg_received);
//		l1.addElement(msg_received);
	
	}
	public static void convertToList(String s){
		String temp="";
		int i=0;
		while((i+1)<s.length()){
			temp+=s.charAt(i);
			if(s.charAt(i+1)=='*'){
				l1.addElement(temp);
				temp="";
				i++;
			}
			i++;
		}
	}
	public static void writeToFile(DefaultListModel<String> l1){
		try{
		FileWriter writer =new FileWriter("output.txt");
        
		for(int i=0;i<l1.getSize();i++) {
			writer.write(l1.get(i));
			writer.write("\n");
		}
		writer.close();
		}catch(IOException e){}
	}
}

