package bean;

public class DBPageSave
{
	DBBean db=new DBBean();
	public boolean  dbinsert(String url,byte[] data )
	{
		boolean b=false;
		String sql="INSERT INTO pagesave  VALUES( '"+url+"','"+data+"')";
		System.out.println(sql);
		b=db.executebyte(sql,url, data);
		return b;
	}
	public int delete()
	{
		int i=0;
		String sql="delete from pagesave";
		System.out.println(sql);
		i=db.executeUpdate(sql);
		return i;
	}
	public void close()
	{
		db.close();
	}
}
