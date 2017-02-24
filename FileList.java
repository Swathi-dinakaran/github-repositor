import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.lang.String;
public class FileList {
    public static void main(String[] args) throws IOException {

        BufferedReader inputStream = null;
        BufferedWriter outputStream = null;
        ArrayList<String> Lists=new ArrayList<String>();
        try {
            inputStream = new BufferedReader(new FileReader("xanadu.txt"));
            outputStream = new BufferedWriter(new FileWriter("output.txt"));
            String l;
            while ((l = inputStream.readLine()) != null) {
                String[] WordsLine=l.split(" ");
                for(int i=0;i<WordsLine.length;i++)
                {
                Lists.add(WordsLine[i]);
                }
            }
            Collections.sort(Lists);
            Iterator<String> n=Lists.iterator();
            String previous="";
            int i=0;
            while(n.hasNext())
            {
                String current=n.next();
                System.out.println(current+previous);
                if(current.compareToIgnoreCase(previous)==0)
                {
                    i++;
                }
                else
                {
                    if(i>0)
                    {
                    String s=previous+" "+Integer.toString(i);
                    outputStream.append(s+"\n");
                    }
                    i=1;
                }
                previous=current;
            }
            String s=previous+" "+Integer.toString(i);
                    outputStream.append(s+"\n");    
            }
         finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}





