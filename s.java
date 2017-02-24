package d;
import java.util.Scanner;
public class smallbig
{
        public void calc(int[] a)
        {
                int small=a[0],large=a[0];
                for(int i=0;a[i]!='\0';i++)
                {
                        if(a[i]<small)
                                small=a[i];
                        if(a[i]>large)
                                large=a[i];
                }
                System.out.println("small: "+small+" big: "+large);
        }
        public static void main(String args[])
        {
                smallbig sb=new smallbig();
                Scanner s=new Scanner(System.in);
                int[] a=new int[11];
                System.out.println("array:");
                for(int i=0;i<10;i++)
                {
                        a[i]=s.nextInt();
                }
                sb.calc(a);
        }
}

