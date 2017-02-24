public class factorial
{
int lowerlimit;
int upperlimit;
public factorial(int i,int j)
{
lowerlimit=i;
upperlimit=j;
}

public String toString()
{
String h="";
int k=1;
for(int i=lowerlimit;i<=upperlimit;i++)
{
k=1;
for(int j=1;j<=i;j++)
{
k=k*j;
}
h=h+" "+Integer.toString(k);
}
return h;
}
public static void main(String args[])
{
factorial f=new factorial(2,4);
String s=f.toString();
System.out.println(s);
}
}
