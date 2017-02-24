import java.util.Scanner;
public class pattern {
    public void fn(int n)
    {
        int i=1;
    for(i=1;i<n;i++)
    {
        System.out.print(i+" ");
    }
    
    for(;i>0;i--)
    {
        System.out.print(i+" ");
    }
    System.out.println();
    }
    public static void main(String args[])
    {
        pattern p=new pattern();
        System.out.println("Enter the number of lines : ");
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        for(int i=1;i<=n;i++)
        {
            int j=i;
        while(n-j>0)
        {
            System.out.print("  ");
            j++;
        }
        p.fn(i);
        }
    }
}

