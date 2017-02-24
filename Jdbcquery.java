import java.sql.*;
import java.util.Scanner;
public class Jdbcquery
{
	public static void main(String args[])
	{
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","admin","swathi");
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from pet");
		while(rs.next())
		{
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
		}	
		System.out.println("insert:\nenter values:");
		Scanner s=new Scanner(System.in);
		String a=s.next();
		String b= s.next();
		String c= s.next();
		String d= s.next();
		stmt.executeUpdate("insert into pet values(\""+a+"\",\""+b+"\",\""+c+"\",\""+d+"\");");
		System.out.println("inserted");
		System.out.println();
		con.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
}
}