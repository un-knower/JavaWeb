package cpdetector;


import bean.DBBean;

public class EncodeToDB
{
	DBBean db=new DBBean();
	public boolean saveEncode(String encode)
	{
		String sql="insert into encode values  '"+encode+"'";
		int n= db.executeUpdate(sql);
		if(n==0)
		{
			return false;
		}
		else {
			return true;
			
		}
		
	}
}
