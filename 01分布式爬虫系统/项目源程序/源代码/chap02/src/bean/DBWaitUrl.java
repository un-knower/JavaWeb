package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBWaitUrl
{
	DBBean db =new DBBean();
	public int add(String url)
	{
		int i=0;
		String sql="insert into urlwait values ('"+url+"')";
		System.out.println("插入数据仓库waiturl："+sql);
		db.executeUpdate(sql);
		return i;
	}
	public int delete(String url)
	{
		int i=0;
		String sql="delete from urlwait where url='"+url+"'";
		System.out.println(sql);
		db.executeUpdate(sql);
		return i;
	}
	public int delete()
	{
		int i=0;
		String sql="delete from urlwait";
		System.out.println(sql);
		i=db.executeUpdate(sql);
		return i;
	}
	public boolean Empty()
	{
		
		String sql="select * from urlwait limit 1";
		ResultSet rs=db.executeQuery(sql);
		if(rs!=null)
		{
			return true;
		}
		
		else
		{
			return false;
		}
		
	}
	public String findFrist()
	{
		String url="";
		ResultSet rs=null;
		String sql="select * from urlwait limit 1";
		rs=db.executeQuery(sql);
		try
		{
			while(rs.next())
			{
				url=rs.getString("url");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public void close()
	{
		db.close();
	}
}
