import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Jdbcphonedir
{
	public static void main(String args[])
	{
		BufferedReader br;
		String s;
		Scanner sc=new Scanner(System.in);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","admin","swathi");
			Statement stmt=con.createStatement();
			//stmt.executeUpdate("drop table Jdbcphonedir");
			//System.out.println("outside if!");

			if(stmt.executeUpdate("show tables like \"Jdbcphonedir\"")==0)
			{
				System.out.println(stmt.executeUpdate("show tables like \"Jdbcphonedir\""));
			//				System.out.println("inside if!");

			stmt.executeUpdate("create table if not exists Jdbcphonedir (id integer primary key,firstname varchar(20),lastname varchar(20),gender enum('M','F'),age integer)");
			br=new BufferedReader(new FileReader("/Users/swathi/Training_projects/nodups.csv"));
			s=br.readLine();
			while((s=br.readLine())!=null)
			{
				String items[]=s.split(",");
				items[1]=items[1].replaceAll("\\s+","");
				items[2]=items[2].replaceAll("\\s+","");
				items[3]=items[3].replaceAll("\\s+","");
			//System.out.println("insert into Jdbcphonedir values("+items[0]+",\'"+items[1]+"\',\'"+items[2]+"\',\'"+items[3]+"\',"+items[4]+")");
			stmt.executeUpdate("insert into Jdbcphonedir values("+items[0]+",\'"+items[1]+"\',\'"+items[2]+"\',\'"+items[3]+"\',"+items[4]+")");
			}
			}
			System.out.println("Enter 1st name to search for the record :");
			String n=sc.next();
			ResultSet rs=stmt.executeQuery("select * from Jdbcphonedir where firstname = \'"+n+"\'");
			while(rs.next())
			{
				System.out.println(rs.getInt("id")+"\t"+rs.getString("firstname")+"\t"+rs.getString("lastname")+"\t"+rs.getString("gender")+"\t"+rs.getInt("age"));
			}
			rs.close();
			System.out.println("Update 1.lastname or 2.age");
			Integer opt=sc.nextInt();
			Integer id=0;
			if(opt==1)
			{
				System.out.println("\nEnter Lastname and id: ");
				String lastName=sc.next();
				id=sc.nextInt();
				//System.out.println("update Jdbcphonedir set lastname=\'"+lastName+"\' where id="+id);
				String query = "update Jdbcphonedir set lastname = ? where id = ?";
      			PreparedStatement preparedStmt = con.prepareStatement(query);
    		 	preparedStmt.setString   (1, lastName);
      			preparedStmt.setInt(2, id);
      			Integer i=preparedStmt.executeUpdate();
      			/*ResultSet rs1=stmt.executeQuery("select * from Jdbcphonedir where id = \'"+id+"\'");
				while(rs1.next())
				{
					System.out.println(rs1.getInt("id")+"\t"+rs1.getString("firstname")+"\t"+rs1.getString("lastname")+"\t"+rs1.getString("gender")+"\t"+rs1.getInt("age"));
				}*/
			}
			if(opt==2)
			{
				System.out.println("\nEnter age and id: ");
				Integer age=sc.nextInt();
				id=sc.nextInt();
				//System.out.println("update Jdbcphonedir set lastname=\'"+lastName+"\' where id="+id);
				String query = "update Jdbcphonedir set age = ? where id = ?";
      			PreparedStatement preparedStmt = con.prepareStatement(query);
    		 	preparedStmt.setInt(1, age);
      			preparedStmt.setInt(2, id);
      			Integer i=preparedStmt.executeUpdate();
      			/*ResultSet rs1=stmt.executeQuery("select * from Jdbcphonedir where id = \'"+id+"\'");
				while(rs1.next())
				{
					System.out.println(rs1.getInt("id")+"\t"+rs1.getString("firstname")+"\t"+rs1.getString("lastname")+"\t"+rs1.getString("gender")+"\t"+rs1.getInt("age"));
				}*/
			}
			ResultSet rs1=stmt.executeQuery("select * from Jdbcphonedir where id = \'"+id+"\'");
			while(rs1.next())
			{
					System.out.println(rs1.getInt("id")+"\t"+rs1.getString("firstname")+"\t"+rs1.getString("lastname")+"\t"+rs1.getString("gender")+"\t"+rs1.getInt("age"));
			}
			rs1.close();
			con.close();
			stmt.close();
			System.out.println("done!");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}