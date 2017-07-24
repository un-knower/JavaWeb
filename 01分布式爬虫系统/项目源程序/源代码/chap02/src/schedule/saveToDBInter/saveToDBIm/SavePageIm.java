package schedule.saveToDBInter.saveToDBIm;

import schedule.saveTodb.SavePageInter;
import bean.DBPage;
import model.Page;

public class SavePageIm implements SavePageInter
{
	public boolean savePage(Page page)
	{
		DBPage dbPage=new DBPage();
		if(dbPage.add(page)==0)
		{
            dbPage.close();
			return false;
		}
		else
		{   dbPage.close();
			return true;
		}
		
	}
}
