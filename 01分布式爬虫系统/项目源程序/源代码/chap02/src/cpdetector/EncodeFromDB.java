package cpdetector;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.DBBean;


public class EncodeFromDB
{
	DBBean db=new DBBean();
	public String findEncode()
	{
		String encode="";
		String sql="select * from encode";
		ResultSet rs=db.executeQuery(sql);
		try
		{
			while(rs.next())
			{
				try
				{
					encode=rs.getString("encode");
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return encode;
	}
}
