import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.HashSet;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
public class CsvDuplicate
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=null;
		BufferedWriter fw=null;
		//HashSet<String> set=new HashSet<String>();
		ArrayList<String> set1=new ArrayList<String>();
		try
		{
			br=new BufferedReader(new FileReader("csvfile.csv"));
			fw=new BufferedWriter(new FileWriter("nodups.csv"));
			String s;
			while((s=br.readLine())!=null)
			{
				//set.add(s);
				if(!set1.contains(s))
				{
					set1.add(s);
				}
			}
			for(String no:set1)
			{
				fw.append(no+"\n");
			}
		}
		finally
		{
			br.close();
			fw.flush();
			fw.close();
		}
	}
}