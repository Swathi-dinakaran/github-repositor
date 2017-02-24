//http://www.javatpoint.com/PreparedStatement-interface
import java.sql.*;
class InsertPrepared
{
	public static void main(String args[])
	{
	try
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/first","admin","swathi");
		PreparedStatement stmt=con.prepareStatement("insert into pet values(?,?,\"dog\",null)");
		stmt.setString(1,"billi");
		stmt.setString(2,"ratan");
		int i=stmt.executeUpdate();
		System.out.println(i+"records inserted");
		con.close();
	}
	catch(Exception e)
	{System.out.println(e);
	}
	}
}

