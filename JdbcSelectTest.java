import java.sql.*;
public class Jdbcselecttest
{
	public static void main(String args[])
	{
		try
		{
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookshop?useSSL=false", "admin", "swathi");
			Statement stmt=conn.createStatement();
			String str="select title,price,qty from books"; 
			System.out.println("The query is "+str);
			System.out.println();
			ResultSet rs=stmt.executeQuery(str);
			System.out.println("records selected are :");
			Integer row=0;
			while(rs.next())
			{
				String title=rs.getString(1);
				float price=rs.getFloat(2);
				Integer qty=rs.getInt(3);
				row++;
				System.out.println(title +", "+price + ", " + qty);
			}
			System.out.println("no of rows : "+row);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}