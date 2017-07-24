package downloader.downloaderIm;

import model.Page;
import downloader.DownloadInter;
import bean.DBWaitUrl;

public class FromDBUrlDownload implements DownloadInter
{
	public Page downloadFile(Page page1)
	{
		Page page=page1;
		String url=page.getPageURL();
		System.out.println(url);
		DownLoadFile loadFile=new DownLoadFile();
		String document= loadFile.downloadFile(url);
		page.setDocument(document);
		return page;
	}
}
