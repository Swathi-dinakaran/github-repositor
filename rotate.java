import java.util.Scanner;

public class rotate
{
int[][] a=new int[10][10];
public rotate(int b[][])
{
a=b;
}
public void left(int m,int n)
{
for(int i=0;i<m;i++)
{
for(int j=0;j<n;j++)
{
System.out.print(a[j][m-1-i]+" ");
}
System.out.println();
}
}
public void right(int m,int n)
{
for(int i=0;i<m;i++)
{
for(int j=0;j<n;j++)
{
System.out.print(a[n-1-j][i]+" ");
}
System.out.println();
}
}
public static void main(String args[])
{
System.out.println("enter the no of rows and columns:");
Scanner s=new Scanner(System.in);
int n=s.nextInt();
int m=s.nextInt();
int[][] a=new int[10][10];
for(int i=0;i<n;i++)
{
for(int j=0;j<m;j++)
{
    a[i][j]=s.nextInt();
}
}
rotate r=new rotate(a);
r.left(n, m);
r.right(n, m);
}
}

