package pageProcessor.readerInter.readerIm;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import cpdetector.CpdetectorInter;
import cpdetector.EncodeFromDB;
import cpdetector.cpdetectorIm.CpdetectorIm;
import downloader.downloaderIm.DownLoadFile;
import model.Encode;
import model.Page;
import pageProcessor.readerInter.PageReadInter;
import bean.DBBean;
public class PageReadIm implements PageReadInter
{
	DBBean db=new DBBean();
	@Override
	public Page getdbPage()
	{
		ResultSet rs=null;
		Page page=new Page();
		byte data[]=null;
		Blob blob=null;
		String document="";
		String sql="";
		String dburl="";
		sql="select * from pagesave limit 1";
		rs=db.executeQuery(sql);
		try
		{
			while(rs.next())
			{
				blob=rs.getBlob("document");
				dburl=rs.getString("pageURL");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		sql="delete from pagesave where pageURL = '"+dburl+"'";
		db.execute(sql);
		if(Encode.getEncode()==""||Encode.getEncode().equals(""))
		{

			EncodeFromDB encodeFromDB =new EncodeFromDB();
			String encode="";
			encode=encodeFromDB.findEncode();
			CpdetectorInter cpdetector=new CpdetectorIm();
			cpdetector.InURLOut(dburl);
			
		}
		//System.out.println("Encode.getEncode()"+Encode.getEncode());//()GB2312

		try
		{;
			document= new String(blob.getBytes(1, (int)blob.length()),Encode.getEncode());
		} catch (SQLException e)
		{
			
			e.printStackTrace();
		}  catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} 
		//System.out.println(document);
		Encode.setURLHead(dburl);
		page.setPageURL(dburl);
		page.setDocument(document);
		return page;
	}
	@Override
	public Page getURLPage(String url)
	{
		Page page=new Page();
		String document="";
		DownLoadFile downLoadFile=new DownLoadFile();
		document=downLoadFile.downToString(url);
		Encode.setURLHead(url);
		page.setPageURL(url);
		page.setDocument(document);
		return page;
	}
	
	

}
