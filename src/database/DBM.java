package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class DBM {

	static String url = "jdbc:sqlite:Billing.db";
	static String driver = "org.sqlite.JDBC";
	public DBM ()
	{
		System.out.println("i am here");
		createSTOCK();
		createTRANS();
		createUSER();}
	public HashMap<String,HashMap<String,String>> getStock()
	{
		try {
			ResultSet rs;
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			rs=s.executeQuery("select * from stock");
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			HashMap<String,HashMap<String,String>> stock=new HashMap<String,HashMap<String,String>>();
			while (rs.next())
			{
				HashMap<String,String> qp = new HashMap<String,String>();
				for(int columnIndex = 2; columnIndex <= columnCount; columnIndex++) 
				{
					qp.put(metaData.getColumnName(columnIndex),rs.getString(columnIndex));
				}
				stock.put(rs.getString(1), qp);
			}
			rs.close();
			s.close();
			conn.close();
			return stock;
			}
		catch(Exception e){
			System.out.println(e);}
	return null;
		
	}
	public void reStock(HashMap<String,HashMap<String,String>> stock)
	{
		try {
			ResultSet rs;
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			Statement s1=conn.createStatement();
			rs=s.executeQuery("select bag_type from stock");
			while(rs.next())
			{
			s1.executeUpdate("update stock set quantity ='"+stock.get(rs.getString(1)).get("quantity")+"' where bag_type ='"+rs.getString(1)+"'");	
		
			}
			rs.close();
			s.close();
			s1.close();
			conn.close();

			}
		catch(Exception e){
			System.out.println(e);}

		
	}
	
	public HashMap<String,String> RowInfo(String stmnt)
	{
		HashMap<String,String> rowdetail=new HashMap<String,String>();
		ResultSet rs;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			rs=s.executeQuery(stmnt);
			if(rs.next())
			{
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			for(int i=1;i<=columnCount;i++)
			{
				rowdetail.put(metaData.getColumnName(i),rs.getString(i));
			}}
			s.close();
			conn.close();
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return rowdetail;
	}
	public boolean loginCheck(String uname,String pass)
	{
		boolean i=false;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			ResultSet rs=s.executeQuery("select * from user where username = '"+uname+"' and password = '"+pass+"'");
			if(rs.next())
			{
				i=true;
			}
			s.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public void createUSER()
	{
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS `user` (`username`	TEXT NOT NULL PRIMARY KEY ,`password`	TEXT NOT NULL)");
			s.executeUpdate("insert into user values('admin','123')");
			s.close();
			conn.close();
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	public String getTransactionId()
	{
		
		try {
		String id;	
			ResultSet rs;
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			rs=s.executeQuery("select seq from sqlite_sequence where name='trans'");
			rs.next();
			id=rs.getString("seq");
			rs.close();
			s.close();
			conn.close();
		return id;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return null;
		
	}
	public void createSTOCK()
	{
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS `stock` (	`bag_type`	TEXT PRIMARY KEY NOT NULL,	`quantity`	INTEGER NOT NULL,	`price`	INTEGER NOT NULL)");
			s.close();
			conn.close();
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		
	}
	
	public void createTRANS()
	{
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			s.executeUpdate("CREATE TABLE IF NOT EXISTS  `trans` (	`trans_id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,	`name`	TEXT NOT NULL,	`phone_no`	TEXT NOT NULL,	`trans_date`	DATE,  `total_price`	INTEGER NOT NULL)");
			s.executeUpdate("CREATE TABLE IF NOT EXISTS  `transdetail` (	`detail_id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,	`bag_type`	TEXT NOT NULL,	`quantity`	INTEGER NOT NULL,	`price`	INTEGER NOT NULL,`trans_id`	INTEGER NOT NULL)");
			s.close();
			conn.close();
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	
	}
	public int updateQuery(String stmnt) 
	{
		int i=0;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			i=s.executeUpdate(stmnt);
			s.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	public Vector<String> selectQueryForList(String stmnt,String column,String upper)
    {

		try {
			ResultSet rs;
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			rs=s.executeQuery(stmnt);
			Vector<String> data = new Vector<String>();
			data.add(upper);
			while (rs.next())
			{
				data.add(rs.getString(column));
			}
			rs.close();
			s.close();
			conn.close();
			return data;
			}catch(Exception e){
				System.out.println(e);}
		return null;

    }
	public DefaultTableModel selectQueryForTable(String stmnt)
	{
	
		try {
			ResultSet rs;
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url);
			Statement s=conn.createStatement();
			rs=s.executeQuery(stmnt);
			ResultSetMetaData metaData = rs.getMetaData();
			Vector<String> columnNames = new Vector<String>();
			int columnCount = metaData.getColumnCount();
			for(int column = 1; column <= columnCount; column++) 
			{
				columnNames.add(metaData.getColumnName(column));
			}
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next())
			{
				Vector<Object> vector = new Vector<Object>();
				for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++) 
				{
					vector.add(rs.getObject(columnIndex));
				}
				data.add(vector);
			}
			rs.close();
			s.close();
			conn.close();
			return new DefaultTableModel(data, columnNames);
			}
		catch(Exception e){
			System.out.println(e);}
	return null;
}

}
