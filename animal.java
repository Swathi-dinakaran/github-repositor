public class animal
{
 int legs;
 String type;
 public animal(int a,String b)
 {
  legs=a;
  type=b;
System.out.println(a+" "+b);
 }
 public static void main(String args[])
{
animal[] u=new animal[10];
  u[0]=new animal(1,"bird");
 } 
}
