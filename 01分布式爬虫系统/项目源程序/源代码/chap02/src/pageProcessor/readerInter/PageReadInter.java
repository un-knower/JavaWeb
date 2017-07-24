package pageProcessor.readerInter;

import model.Page;

public interface PageReadInter
{
	public Page getdbPage();
	public Page getURLPage(String url);
}
