package bean;

import java.sql.*;

public class DBBean {
	private String driverStr = "com.mysql.jdbc.Driver";
	private String connStr = "jdbc:mysql://localhost:3306/crawler?characterEncoding=utf8&useSSL=true";
	private String dbusername = "root";
	private String dbpassword = "root";
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null; 
	public DBBean() {
		try {
			Class.forName(driverStr);
			conn = DriverManager.getConnection(connStr, dbusername, dbpassword);
			stmt = conn.createStatement();
		} catch (Exception ex) {
                        System.out.println("无法同数据库建立连接！");
			
		}
	}

	public int executeUpdate(String s) {
		int result = 0;
		try {
			result = stmt.executeUpdate(s);
		} catch (Exception ex) {
		        System.out.println("执行更新错误！");	
		}
		return result;
	}

	public ResultSet executeQuery(String s) {
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(s);
		} catch (Exception ex) {
			System.out.println("执行查询错误！");
		}
		return rs;
	}
	public boolean execute(String s) {
		boolean rs = false;
		try {
			rs = stmt.execute(s);
		} catch (Exception ex) {
			System.out.println("执行查询错误！");
		}
		return rs;
	}
	public boolean executebyte(String s,String url,byte[] data)
	{
		try
		{
			pstmt = conn.prepareStatement("insert into pagesave (pageURL,document) values(?,?)");
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try
		{
			pstmt.setString(1,url);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //把传过来的第一个参数设为文件名 
		//pstmt.setBinaryStream(2,fis,(int)file.length()); //这种方法原理上会丢数据，因为file.length()返回的是long型 
		//pstmt.setBinaryStream(2,data,data.available()); //第二个参数为文件的内容 
		try
		{
			pstmt.setBytes(2,data);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean executeUpdatePage(String url,String time,byte[]title,byte[] h1,byte[] p,byte[] key,byte[] zhaiyao)
	{
		try
		{
			pstmt = conn.prepareStatement("insert into page (url,time,title,h1,p,keyname,zhaiyao) values(?,?,?,?,?,?,?)");
		} catch (SQLException e)
		{
			e.printStackTrace();
		} 
		try
		{
			pstmt.setString(1,url);
			pstmt.setString(2,time);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} 
		try
		{
			pstmt.setBytes(3,title);
			pstmt.setBytes(4,h1);
			pstmt.setBytes(5,p);
			pstmt.setBytes(6,key);
			pstmt.setBytes(7,zhaiyao);
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		}
		try
		{
			pstmt.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
		
	}
	public void close() {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
		}
	}
}
