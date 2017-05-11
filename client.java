import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;



public class client
{
public static void main(String[] args)
{
try{
Socket socket = new Socket("127.0.0.1",8080);
DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
DOS.writeUTF("HELLO_WORLD");
socket.close();
}catch(IOException e){}

}
}






