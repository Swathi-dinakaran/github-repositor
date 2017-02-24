import java.util.Scanner;
public class fibo
{
	
	public void calc(int n)
	{
		int i3=0,i1=1,i2=1;
		System.out.print("1 1 ");
		for(;i3<=n;)
		{
		i3=i1+i2;
		i1=i2;
		i2=i3;
		System.out.print(i3+" ");
		}
	}
	public static void main(String args[])
	{
				fibo i=new fibo();
                Scanner a=new Scanner(System.in);
                System.out.println("Enter n:");
                int n=a.nextInt();
                i.calc(n);
	}}
