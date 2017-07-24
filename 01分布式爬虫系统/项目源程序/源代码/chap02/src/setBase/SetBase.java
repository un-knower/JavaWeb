package setBase;

import model.Encode;
import cpdetector.CpdetectorInter;
import cpdetector.cpdetectorIm.CpdetectorIm;

public class SetBase
{
	public void setBase(String url,String[] accept,String[] refuseUrl)
	{
		CpdetectorInter cpdetector=new CpdetectorIm();
		cpdetector.InURLOut(url);
		//Encode.setFil(fil);
		FilterForLink.acceptUrl=accept;
		FilterForLink.refuseUrl=refuseUrl;
	}
	
}
