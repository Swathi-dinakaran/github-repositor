public class emp
{
String name;
float income;
boolean gender;
float tax;
static float calc(boolean g,float i)
{
float t;
if(g==true)
{
t=i/100;
t*=0.4;
}
else
{
t=i/104;
t*=0.7;
}
return t;
}
public static void main(String args[])
{
emp e1=new emp();
e1.name="swa";
e1.income=20000;
e1.gender=true;
e1.tax=emp.calc(e1.gender,e1.income);
System.out.println(e1.name+" "+e1.income+" "+e1.gender+" "+e1.tax);
}
}
