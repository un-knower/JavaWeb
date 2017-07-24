package cpdetector.cpdetectorIm;

import cpdetector.CpdetectorInter;
import cpdetector.EncodeToDB;
import cpdetector.UrlToEncode;

public class CpdetectorTodbIm implements CpdetectorInter
{
	
//赋值给数据库
	@Override
	public void InURLOut(String url)
	{

		UrlToEncode urlToEncode=new UrlToEncode();
		EncodeToDB encodeToDB=new EncodeToDB();
		encodeToDB.saveEncode(urlToEncode.toEncode(url));
		
	}
}
