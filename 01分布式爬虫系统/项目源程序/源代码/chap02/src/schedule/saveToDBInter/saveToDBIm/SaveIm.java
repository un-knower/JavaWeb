package schedule.saveToDBInter.saveToDBIm;

import java.util.Iterator;
import java.util.Set;

import model.URL;
import schedule.saveTodb.SaveInter;

public class SaveIm implements SaveInter
{

	public int saveUrlTodb(URL url)
	{
		System.out.println("插入url************************************************");
		int i=0;
		SaveWaitUrlToDB urlToDB= new SaveWaitUrlToDB();
		Set<String> links=url.getUrl();
		Iterator<String> it=links.iterator();
        while(it.hasNext())
        { 
        	i+=urlToDB.saveUrlTodb(it.next());
        }
        System.out.println("共插入url************************************************"+i+"条");
		return i;
	}

}
