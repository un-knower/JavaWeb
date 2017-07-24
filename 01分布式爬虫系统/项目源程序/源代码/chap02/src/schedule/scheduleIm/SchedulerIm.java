package schedule.scheduleIm;

import bean.DBPage;
import bean.DBPageSave;
import bean.DBWaitUrl;
import model.Page;
import model.URL;
import schedule.Scheduler;
import schedule.saveToDBInter.saveToDBIm.SaveIm;
import schedule.saveToDBInter.saveToDBIm.SavePageIm;
import schedule.saveTodb.SaveInter;
import schedule.saveTodb.SavePageInter;

public class SchedulerIm implements Scheduler
{
	public void pageAndUrlToDB(Page page)
	{
		SaveInter save=new SaveIm();
		save.saveUrlTodb(page.getUrl());
		if(page.getDataItems().getP()!=""&&page.getDataItems().getH1()!="")
		{
			SavePageInter pageSave=new SavePageIm();
			pageSave.savePage(page);
		}
	}
	public Page findWaitUrl()
	{
		String url="";
		Page page=new Page();
		DBWaitUrl dbWaitUrl =new DBWaitUrl();
		
		url=dbWaitUrl.findFrist();
		
		dbWaitUrl.delete(url);
		dbWaitUrl.close();
		page.setPageURL(url);
		return page;
	}
	public boolean cleanDB()
	{
		DBWaitUrl dbWaitUrl =new DBWaitUrl();
		dbWaitUrl.delete();
		
		DBPageSave dbPageSave=new DBPageSave();
		dbPageSave.delete();
		dbPageSave.close();
		DBPage dbPage=new DBPage();
		dbPage.delete();
		dbPage.close();
		return false;
	}
}
