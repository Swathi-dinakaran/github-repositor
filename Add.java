
import java.util.Scanner;
public class Add
{  
    int x;
    int y;
    int z;
    public Add(int a, int b)
    {
        x=a;
        y=b;
    }
    public int fn()
    {
        z=x+y;
        return z;
    }
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int x=s.nextInt();
        int y=s.nextInt();
        Add a=new Add(x,y);
        int c=a.fn();
        System.out.println(c);
    }
}

