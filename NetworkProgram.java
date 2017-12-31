import java.net.*;
class NetworkProgram
{
	public static void main(String args[]) throws Exception
	{
		InetAddress i=InetAddress.getLocalHost();
		System.out.println(i);	
		/*InetAddress x[]=InetAddress.getAllByName("www.rediff.com");
		for(int i=0;i<x.length;i++)
		{
			System.out.println(x[i]);
		}*/
	}
}