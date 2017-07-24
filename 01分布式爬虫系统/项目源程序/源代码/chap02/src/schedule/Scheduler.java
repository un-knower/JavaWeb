package schedule;

import model.Page;
import model.URL;


public interface Scheduler
{
	public void pageAndUrlToDB(Page page);
	public Page findWaitUrl();
	public boolean cleanDB();
}
