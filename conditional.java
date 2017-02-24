import java.util.Scanner;
public class conditional {
    public static void main(String args[])
    {
    Scanner s=new Scanner(System.in);
    String st=s.next();
 
      switch(st) {
          case "true":System.out.println("true case");break;
          case "false":System.out.println("false case");break;
         default :System.out.println("default");
      }
   }
}
