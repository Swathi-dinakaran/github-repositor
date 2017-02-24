
import java.util.Scanner;
import java.lang.Math;
public class interest
{
	double si,ci;
	public void calc(float p,float n,float r)
	{
		si=((p*n*r)/100);
		r=r/100;
		ci=p*Math.pow((1+r),n)-p;
		System.out.println("coumpound interest :"+ci+" simple interest :"+si);
	}
	public static void main(String args[])
	{
				interest i=new interest();
                Scanner a=new Scanner(System.in);
                System.out.println("Enter the principle,no of years and rate of interest:");
                float p=a.nextFloat();
                int n=a.nextInt();
                Float r=a.nextFloat();
                i.calc(p,n,r);
	}
}
