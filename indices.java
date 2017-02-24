import java.util.Scanner;
public class indices
{
	int count;
	public int find(String str)
	{
		count=0;
		for(int i=0;i<str.length();i++)
		{
			int a=str.charAt(i);
			count+=a-96;
		}	
		return count;
	}
	public static void main(String args[])
	{
		indices i=new indices();
		Scanner a=new Scanner(System.in);
		System.out.println("Enter the string:");
		String s=a.next();
		int c=i.find(s);
		if(c%2==0)
		{
			System.out.println("even");
		}
		else
		{
			System.out.println("odd");
		}
	}
}
