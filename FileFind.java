import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.String;
import java.util.Scanner;
public class FileFind
{
	public int Find(String s,String word)
	{
		int c=s.indexOf(word);
		return c;
	}
	public static void main(String args[]) throws IOException
	{
		System.out.println("Enter search word : ");
		Scanner s=new Scanner(System.in);
		BufferedReader br=null;
		BufferedWriter bw=null;
		String word=s.next();
		try
		{
		br=new BufferedReader(new FileReader("xanadu.txt"));
		bw=new BufferedWriter(new FileWriter(word+".txt"));
		String line;
		int count=0,lineCount=0;
		FileFind ff=new FileFind();
		while((line=br.readLine())!=null)
		{
			System.out.println(line);
			int c=ff.Find(line,word);
			lineCount++;
			if(c!=-1)
			{
				bw.append("<"+lineCount+"> : "+Integer.toString(c)+" ");
				while(c==-1)
				{
					c=ff.Find(line.substring(c),word);
					bw.append(Integer.toString(c)+" ");
				}
			}

		}
	}
	finally {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.close();
            }
	}
}
}