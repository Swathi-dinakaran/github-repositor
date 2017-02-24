import java.util.*;
public class Prefixmap
{
        HashMap<String,List> l;
        public Prefixmap()
        {       
                l=new HashMap<String,List>();
        }
        public void func(String[] a)
        {       
                for(String t:a)
                {
                        String subs=t.substring(0,3);
                        List<String> al=new ArrayList();
                        if(l.get(subs)!=null)
                        {
                        al.addAll(l.get(subs));
                        }
                        al.add(t);
                        l.put(subs,al);
                }
                for(Map.Entry<String,List> mapEntry:l.entrySet()){
                String key=mapEntry.getKey();
                List b=mapEntry.getValue();
                System.out.println("\n"+key+" Details:");
               Iterator itr=b.iterator();
                        while(itr.hasNext()){
                                System.out.print(itr.next()+" ");
                        }
                }
        }
        public static void main(String[] args) {
                String[] arr={"asdr","asde","asdy","asdf","qweq","qweo","qwei","qwem"};
                Prefixmap object=new Prefixmap();
                object.func(arr);
        }
}

