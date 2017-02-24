import java.util.Scanner;
import static java.lang.Math.pow;
public class power {
    int x;int y;
    public power(int a,int b)
    {
        x=a;y=b;
    }
    public int fn()
    {
        double ans=x;
        int i=1;
        while(ans<y)
        {
            ans*=x;
            i++;
        }
        return i;
    }
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int x=s.nextInt();
        int y=s.nextInt();
        power a=new power(x,y);
        int c=a.fn();
        System.out.println(c);
    }
}

