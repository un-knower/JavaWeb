package cpdetector;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlToEncode
{
	UseCpdetector cpdetector=new UseCpdetector();
	public String toEncode (String thisurl)
	{
		URL url=null;
		String encode="";
		try
		{
			System.out.println(thisurl);
			 url=new URL(thisurl);
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		encode=	cpdetector.getUrlEncode(url);
		return encode;
	}
}
