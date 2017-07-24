package bean;

import java.sql.ResultSet;

import model.Page;

public class DBPage
{
	DBBean db =new DBBean();
	public int add(Page page)
	{
		int i=0;
		/*String sql="insert into page values ('"+page.getPageURL()+"','"
		+page.getDataItems().getTime()+"','"
		+page.getDataItems().getTitle()+"','"
		+page.getDataItems().getH1()+"','"
		+page.getDataItems().getP()
		+"')";*/
		byte[] byteTitle = page.getDataItems().getTitle().getBytes();
		byte[] byteH1 = page.getDataItems().getH1().getBytes();
		byte[] byteP = page.getDataItems().getP().getBytes();
		byte[] byteKey = page.getDataItems().getKey().getBytes();
		byte[] byteZhaiyao = page.getDataItems().getZhaiyao().getBytes();
		//System.out.println(sql);
		//db.executeUpdate(sql);
		db.executeUpdatePage(page.getPageURL(), page.getDataItems().getTime(),byteTitle,byteH1 , byteP,byteKey,byteZhaiyao);
		return i;
	}
	public int delete(String url)
	{
		int i=0;
		String sql="delete from page where url='"+url+"'";
		System.out.println(sql);
		db.executeUpdate(sql);
		return i;
	}
	public int delete()
	{
		int i=0;
		String sql="delete from page";
		System.out.println(sql);
		i=db.executeUpdate(sql);
		return i;
	}
	public boolean Empty()
	{
		ResultSet rs=null;
		String sql="select * from page limit 1";
		rs=db.executeQuery(sql);
		if(rs==null)
		{
			return false;
		}
		
		else
		{
		return true;}
			
	}
	public void close()
	{
		db.close();
	}
}
