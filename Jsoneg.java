import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.util.*;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.JSONException;
public class Jsoneg
{
	class Student
	{
		String doj;
		String id;
		String name;
		String std;
		Marks[] m=new Marks[5];
		public Student()
		{
			for ( int i=0; i<5; i++) 
			{
				m[i]=new Marks();
			}
		}
		public void call(int i,Long h,String s)
		{
			m[i].mark=h;
			m[i].subject=s;
		}
		class Marks
		{
			
			public Long mark;
			public String subject;
		}
	}
	class Teacher
	{
		String[] classes=new String[10];
		String doj;
		String id;
		String name;
		Double salary;
	}
	public static void main(String args[]) throws JSONException,IOException,FileNotFoundException
	{
		JSONParser parser=new JSONParser();
		try
		{
			Object ob=parser.parse(new FileReader("/Users/swathi/Downloads/students-teachers.json"));
			JSONObject job=(JSONObject) ob;
			Jsoneg je=new Jsoneg();
			JSONObject stu=(JSONObject) job.get("Student");
			Jsoneg.Student s1=je.new Student();
			s1.doj=(String)stu.get("Date Of Joining");
			s1.id=(String)stu.get("ID");
			JSONArray marks=(JSONArray)stu.get("Marks");
 		    Iterator itr = marks.iterator();
 		    int i=0;
            while (itr.hasNext()) {
                JSONObject markobj=(JSONObject)itr.next();
                //s1.m[i].mark=(Long)markobj.get("Mark");
                Long s=(Long)markobj.get("Mark");

                //s1.m[i].subject=(String)markobj.get("Subject");
                String h=(String)markobj.get("Subject");
                s1.call(i,s,h);
                i++;


            }
 			s1.name=(String)stu.get("Name");
 			s1.std=(String)stu.get("Std");
 			System.out.println(s1.doj+"	"+s1.id+"	"+s1.name+"	"+s1.std);
 			for (i=0;i<5;i++) {
 				String s=Long.toString(s1.m[i].mark);
 				System.out.println(s+"	"+s1.m[i].subject);
 			}
		}
		catch(ParseException pe){
		
         System.out.println("position: " + pe.getPosition());
         System.out.println(pe);
      }
	}
}