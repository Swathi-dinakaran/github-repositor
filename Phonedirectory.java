import java.util.*;
public class Phonedirectory
{
	List<person> PhList;
	class person
	{
		public String name;
		public String address;
		public Map<String,String> phno;
	}
	public Phonedirectory()
	{
		PhList=new ArrayList<person>();
	}
	public void add1(String n,String a,String m,String h,String w)
	{
		person p=new person();
		p.name=n;
		p.address=a;
		p.phno=new HashMap<String,String>();
		p.phno.put("mobile",m);
		p.phno.put("home",h);
		p.phno.put("work",w);
		PhList.add(p);
	} 
	public void retrieve(String n)
	{
		Iterator<person> i=PhList.iterator();
		while(i.hasNext())
		{
			person p=i.next();
			if(p.name==n)
			{
				System.out.println(p.name+" "+p.address+" ");
				for(Map.Entry m:p.phno.entrySet())
				{
					System.out.println(m.getValue());
				}
			}
		}
	}
	public void retrivepartial(String n)
	{
		Iterator<person> i=PhList.iterator();
		while(i.hasNext())
		{
			person p=i.next();
			if(p.name.contains(n))
			{
				System.out.println(p.name+" "+p.address+" ");
				for(Map.Entry m:p.phno.entrySet())
				{
					System.out.println(m.getValue());
				}
			}
		}
	}
	public static void main(String args[])
	{
		Phonedirectory p=new Phonedirectory();
		p.add1("swa","indranagar","972383","32738278","126532");
		p.add1("sag","velachery","32312","21345","45678987");
		p.retrieve("sag");
		p.retrivepartial("s");
	}
}
