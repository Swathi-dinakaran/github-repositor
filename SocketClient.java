import java.net.*;
import java.io.*;
public class SocketClient
{
	public static void main(String [] args)
	{
		String servername=args[0];
		int port=Integer.parseInt(args[1]);
		try
		{
			System.out.println("connecting to "+servername+" on port "+port);
			Socket client=new Socket(servername,port);
			System.out.println("client: remote socket address : "+client.getRemoteSocketAddress());
			OutputStream out=client.getOutputStream();
			DataOutputStream outstream=new DataOutputStream(out);

			outstream.writeUTF("hwllo from "+client.getLocalSocketAddress());
			InputStream in=client.getIutputStream();
			DataInputStream instream=new DataInputStream(in);

			System.out.println("From server : "+instream.readUTF());
			client.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}