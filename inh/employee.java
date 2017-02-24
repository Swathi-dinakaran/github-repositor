import java.util.*;
public class employee
{
String name;
float salary;
int age;
static List<employee> emplist;
public employee()
{
emplist.add(this);
}
public void sort()
{
Collections.sort(emplist,new NameComparator());
for(employee st: emplist){  
System.out.println(st.name+" "+st.age+" "+st.salary);  
}  
}
class NameComparator implements Comparator<employee>{  
//why to instantiate comparator
public int compare(employee s1,employee s2){  
return s1.name.compareTo(s2.name);  
}  
}
public static void main(String args[])
{
employee
} 
}
