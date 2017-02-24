import java.util.List;
import java.util.ArrayList;
public class mobile
{
String name="m";
int remainingCharge;
static List<mobile> li;
public mobile()
{
li=new ArrayList();
}
public void remainingCharge()
{
System.out.println("charge :"+remainingCharge);
}
public void name()
{
System.out.println("name :"+name);
}
public static void add(mobile m1)
{
li.add(m1);
}
public void hello()
{
mobile mobile1=new mobile()
{
int charge=10;
public void remainingCharge()
{
System.out.println("charge :"+charge);
}};
mobile1.name();
mobile1.remainingCharge();
mobile.add(mobile1);
mobile mobile2=new mobile()
{
int charge=90;
public void remainingCharge()
{
System.out.println("charge :"+charge);
}};
mobile2.name();
mobile2.remainingCharge();
mobile.add(mobile2);
}

public static void main(String args[])
{

mobile m=new mobile();
m.hello();
}
}
