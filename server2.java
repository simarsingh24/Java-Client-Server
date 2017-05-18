import javax.swing.*;
import java.net.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.*;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileWriter;
import java.awt.Desktop;

public class server2 extends JFrame{
	
	public static DefaultListModel<String> l1 = new DefaultListModel<>();
	public static String mobile_ip="192.168.1.35";
	public static int mobile_port=8080;
	
	public static JFrame frame= new JFrame("Server");
	
	public static JLabel connectLabel=new JLabel("(Mobile IP) Click connect : ");
	public static JTextField mobileIPTextField=new JTextField("192.168.1.35");
	public static JTextField portTextField=new JTextField("8080");
	public static JButton connectButton=new JButton("Connect");
	public static JLabel localIPLabel=new JLabel("Please enter Local IP and Port 8080 in APP ");
	public static JButton fetchButton =new JButton("Fetch");
	public static JLabel fetchedResultLabel=new JLabel("Please select the most appropriate item :");
	public static JList<String> list = new JList<>(l1);  
	public static JButton writeButton =new JButton("Add To File");
	public static JButton clearButton =new JButton("Clear File");
	public static JButton showFile=new JButton("Show File");
	
	public static void main(String[] args){
		
		connectLabel.setBounds(20,20,300,20);
		mobileIPTextField.setBounds(20,40,100,20);		
		portTextField.setBounds(130,40,50,20);		
		connectButton.setBounds(190,40,100,20);		
		localIPLabel.setBounds(20,70,400,20);		
		fetchButton.setBounds(20,100,95,30);
		fetchedResultLabel.setBounds(20,130,300,20);				
		list.setBounds(30,160, 400,200);  
		writeButton.setBounds(325,380,120,30);
		clearButton.setBounds(20,380,100,30);
		showFile.setBounds(20,450,120,30);
		
		frame.add(connectButton);
		frame.add(portTextField);
		frame.add(connectLabel);
		frame.add(mobileIPTextField);
		
		
		frame.setSize(500,500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		connectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				frame.add(localIPLabel);
				frame.add(fetchButton);
				SwingUtilities.updateComponentTreeUI(frame);
				
				mobile_ip=mobileIPTextField.getText();
				mobile_port=Integer.parseInt(portTextField.getText());
				
				
			}
		});
		
		fetchButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.add(clearButton);
				frame.add(writeButton);
				frame.add(list);
				frame.add(showFile);
				frame.add(fetchedResultLabel);
				SwingUtilities.updateComponentTreeUI(frame);
				sendTrigger();
				fetchData();
			}
		});
		
		writeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				writeToFile(l1);	
			}
		});	
		
		clearButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clearFileData();
			}
		});
		
		showFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				openFile();
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
			ServerSocket socket=new ServerSocket(mobile_port);
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
		//FileWriter writer =new FileWriter("output.txt");
		Writer output;
		output = new BufferedWriter(new FileWriter("Cart.txt", true));
        	//writer.write(l1.get(i));
			output.append(list.getSelectedValue());
			output.append("\n");
			//writer.write("\n");
		output.close();
		//writer.close();
		}catch(IOException e){}
	}
	public static void clearFileData(){
	
		try{
		FileWriter writer = new FileWriter("Cart.txt", false); 
        PrintWriter printer = new PrintWriter(writer, false);
        printer.flush();
        printer.close();
        writer.close();
        }catch(IOException e){}
	}
	public static void openFile(){
		try{
			File file = new File("Cart.txt");
	        if(!Desktop.isDesktopSupported()){
	            System.out.println("Desktop is not supported");
	            return;
	        }
	        Desktop desktop = Desktop.getDesktop();
	        if(file.exists()) desktop.open(file);
	        
		}catch(IOException e){}
	}
}

