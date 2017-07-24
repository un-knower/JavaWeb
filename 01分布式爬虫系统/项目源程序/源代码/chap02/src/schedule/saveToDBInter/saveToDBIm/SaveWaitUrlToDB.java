package schedule.saveToDBInter.saveToDBIm;

import bean.DBBean;
import bean.DBWaitUrl;

public class SaveWaitUrlToDB
{
	public int saveUrlTodb(String url)
	{
		int i=0;
		SaveToBloom bloom=new SaveToBloom();
		//布隆过滤判断是否重复
		if(bloom.savetobloom(url)==false)
		{
			return 0;
		}
		else
		{
			DBWaitUrl dbWaitUrl= new DBWaitUrl();
			i=dbWaitUrl.add(url);
			dbWaitUrl.close();
		}
		return i;
		
	}
}
