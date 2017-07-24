package cpdetector.cpdetectorIm;

import cpdetector.CpdetectorInter;
import cpdetector.EncodeToGlobal;
import cpdetector.UrlToEncode;

public class CpdetectorIm implements CpdetectorInter
{
	
//赋值给全局变量
	@Override
	public void InURLOut(String url)
	{

		UrlToEncode urlToEncode=new UrlToEncode();
		EncodeToGlobal encodeToGlobal=new EncodeToGlobal();
		encodeToGlobal.setGlobleEncode(urlToEncode.toEncode(url));
		
	}
}
