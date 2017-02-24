import java.util.*;
public class Lengthmap{
	Map<Integer,List> mapList;
	public Lengthmap()
	{
		mapList=new HashMap<Integer,List>();
	}
	public void func(String[] a)
	{

		ArrayList<String> al=new ArrayList();
		for(int i=0;i<a.length;i++)
		{
			al.add(a[i]);
		}
		mapList.put(al.size(),al);
		for(Map.Entry<Integer,List> mapEntry:mapList.entrySet()){
			int key=mapEntry.getKey();  
        	List b=mapEntry.getValue();  
        	System.out.println("\n"+key+" Details:");  
        	Iterator itr=b.iterator();  
  			while(itr.hasNext()){  
   				System.out.print(itr.next()+" ");  
  			}    
		}
	}
	public static void main(String[] args) {
		String[] arr={"a","as","asd","asdf"};
		Lengthmap object=new Lengthmap();
		object.func(arr);
		arr=new String[]{"q","qw","qwe","qwer"};
		object.func(arr);
	}
}
