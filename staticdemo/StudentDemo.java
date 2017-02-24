public class StudentDemo
{
 public void storeStudentData(student s)
{
 s.setstid(1);
s.setname("swathi");
s.setgender(true);
Subject su=new Subject();
su.setmark1(99);
su.setmark2(100);
s.setsubject(su);
}
public static void main(String args[])
{
StudentDemo sd=new StudentDemo();
student st=new student();
sd.storeStudentData(st);
ResultGenerator r=new ResultGenerator();
r.generateresult(st);
}
} 
