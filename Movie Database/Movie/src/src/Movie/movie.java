package src.Movie;

import java.util.Scanner;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class movie {
	
	public static void createTable()
	{
		 String url = "jdbc:sqlite:C://Users//shubh//OneDrive//Desktop//Movie Database//Movie//src//src/movies.db";
	       
	        String sql = "CREATE TABLE IF NOT EXISTS Movies (\n"
	                + "	id integer PRIMARY KEY,\n"
	                + "	name text,\n"
	                + "	actor text,\n"
	                + "	actress text,\n"
	                + "	director text,\n"
	                + "	Yor integer\n"
	                + ");";
	        
	        try (Connection conn = DriverManager.getConnection(url);
	                Statement stmt = conn.createStatement()) {
	            
	            stmt.execute(sql);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	}
	
	public static void addData()
	{
		String url = "jdbc:sqlite:C://Users//shubh//OneDrive//Desktop//Movie Database//Movie//src//src/movies.db";
		
		String name,actor,actress,director;
		int id,yor;
		
		Scanner sc=new Scanner(System.in);
		
		
				System.out.print("Enter id");
            	id=sc.nextInt();       
            	System.out.print("Enter name");
            	name=sc.next();
            	System.out.print("Enter actor");
            	actor=sc.next();            	
            	System.out.print("Enter actress");
            	actress=sc.next();
            	System.out.print("Enter director");
            	director=sc.next();
            	System.out.print("Enter yor");
            	yor=sc.nextInt();
            	 String sql = "INSERT INTO movies(id,name,actor,actress,director,yor) VALUES(?,?,?,?,?,?)";
            	 try (Connection conn = DriverManager.getConnection(url);
                         PreparedStatement pstmt = conn.prepareStatement(sql)) {
                     pstmt.setInt(1, id);
                     pstmt.setString(2, name);
                     pstmt.setString(3, actor);
                     pstmt.setString(4, actress);
                     pstmt.setString(5, director);
                     pstmt.setInt(6, yor);
                     pstmt.executeUpdate();
                     System.out.print("Data Inserted");
                 } catch (SQLException e) {
                     System.out.println(e.getMessage());
                 }
            
           
        
		sc.close();
	}
	
	public static void show()
	{
		String url = "jdbc:sqlite:C://Users//shubh//OneDrive//Desktop//Movie Database//Movie//src//src/movies.db";
		String sql = "SELECT * FROM movies";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getString("actor") + "\t" +
                                   rs.getString("actress") + "\t" +
                                   rs.getString("director") + "\t" +
                                   rs.getInt("yor"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	public static void main(String[] args) throws IOException {
		
		
		int a;
		Scanner sc=new Scanner(System.in);
		System.out.print("\nEnter Your Choice:\n1.Add New Data\n2.Show table\n 3.Exit");
		a=sc.nextInt();
		while(a!=3)
		{
			if(a==1) addData();
			if(a==2) show();
			System.out.print("\nEnter Your Choice:\n1.Add New Data\n2.Show table\n 3.Exit");
			a=sc.nextInt();
		}
		sc.close();
		
	}

}
