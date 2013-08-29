package com.proquest.interview.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.proquest.interview.phonebook.Person;

/**
 * This class is just a utility class, you should not have to change anything here
 * @author rconklin
 */
public class DatabaseUtil {
	public static void initDB() {
		try {
			Connection cn = getConnection();
			Statement stmt = cn.createStatement();
			stmt.execute("CREATE TABLE PHONEBOOK (NAME varchar(255), PHONENUMBER varchar(255), ADDRESS varchar(255))");
			stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Chris Johnson','(321) 231-7876', '452 Freeman Drive, Algonac, MI')");
			stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('Dave Williams','(231) 502-1236', '285 Huron St, Port Austin, MI')");
			//cn.commit();
			cn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		//Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:hsqldb:mem", "sa", "");
		//return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
	}
	/*
	 * Desc: Below class uses to insert the person details in DB.
	 * Param: Person object
	 * Return type:int
	 */
	public static int insertInfoDB(Person person) {
		Connection cn =null;
		PreparedStatement stmt=null;
		int cnt=0;
		try {
			cn=getConnection();
			final StringBuffer SQL=new StringBuffer();
			SQL.append("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES(?,?,?)");
			stmt= cn.prepareStatement(SQL.toString());
			stmt.setString(1, person.getName());
			stmt.setString(2, person.getPhoneNumber());
			stmt.setString(3, person.getAddress());
			cnt=stmt.executeUpdate();
			//cn.commit();
			if(stmt!=null) stmt.close();
			if(cn !=null)cn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			try{
				if(stmt!=null) stmt.close();
				if(cn !=null)cn.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return cnt;
	}
	/*
	 * Desc: Below class uses to retrieve the person details from DB. 
	 *        If there is no return result, then it will return empty person object
	 * Param: Person object
	 * Return type:Person object
	 */
	public static Person retrieveInfoFromDB(Person person) {
		Person searchResult=new Person();
		Connection cn =null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			cn=getConnection();
			final StringBuffer SQL=new StringBuffer();
			SQL.append("SELECT NAME, PHONENUMBER, ADDRESS FROM PHONEBOOK ");
			SQL.append(" WHERE NAME = '");
			SQL.append(person.getName()+"'");
			System.out.println("SQL Query to find"+SQL.toString());
			stmt= cn.prepareStatement(SQL.toString());
			rs=stmt.executeQuery(SQL.toString());
			 while (rs.next() == true) {
				searchResult.setName(rs.getString(1));
				searchResult.setPhoneNumber(rs.getString(2));
				searchResult.setAddress(rs.getString(3));
			}
			//cn.commit();
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(cn !=null)cn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			try{
				if(stmt!=null) stmt.close();
				if(cn !=null)cn.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return searchResult;
	}
}
