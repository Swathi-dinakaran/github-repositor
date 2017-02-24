import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;

public class Convertcsv
{
	public static void main(String args[]) throws IOException,FileNotFoundException
	{
	BufferedReader br=null;
	FileWriter fw=null,fw1=null;
	
	try{
		br=new BufferedReader(new FileReader("Input.csv"));
		fw=new FileWriter("jsonconfig.json");
		String line="";
		JSONObject ob=new JSONObject();
		while((line=br.readLine())!=null)
		{
			//System.out.println(line);
			String[] words=line.split(",");
			JSONArray company = new JSONArray();
			JSONObject obj = new JSONObject();
			if(words[12]=="")
			obj.put("firstname", words[12]);
			if(words[13]!="")
			obj.put("lastname", words[13]);
			if(words[14]!="")
			obj.put("email", words[14]);
			if(words[15]!="")
			obj.put("company", words[15]);
			company.add(obj);
			fw.write("\n"+company.toJSONString());
		}

		fw1=new FileWriter("output.csv");
		JSONParser parser=new JSONParser();
		try
		{
			Object ob1=parser.parse(new FileReader("jsonconfig.json"));
			JSONObject job=(JSONObject) ob1;
			Iterator<JSONArray> iterator=job.values().iterator();
			while(iterator.hasNext())
			{
			JSONArray ja=iterator.next();
			JSONObject joo=(JSONObject)ja.getJSONObject(0);
			String s=joo.getString("firstname");
			System.out.println(s);
			}
		}
		finally{
			fw1.close();
		}
	}
	finally
	{
		br.close();
		fw.close();
	}
}

}