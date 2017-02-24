public class ResultGenerator {
    public static void generateresult(student s)
    {
	Subject sub=s.getsubject();
      float i=sub.getmark1()+sub.getmark2();
	System.out.println("total= "+i); 
      i/=2; 
        System.out.println("average= "+i); 
   }
}
