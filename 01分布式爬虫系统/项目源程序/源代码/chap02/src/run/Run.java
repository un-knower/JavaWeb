package run;

import bean.DBWaitUrl;
import model.Encode;
import model.Page;
import downloader.DownloadInter;
import downloader.downloaderIm.DownLoadFile;
import downloader.downloaderIm.FromDBUrlDownload;
import pageProcessor.PageProcessor;
import pageProcessor.pageProcessorIm.NewsPageProcessor;
import schedule.Scheduler;
import schedule.scheduleIm.SchedulerIm;
import setBase.SetBase;
public class Run
{
	public static void main(String[] args)
	{ 
		Scheduler scheduler=new SchedulerIm();
		scheduler.cleanDB();
		String url ="http://www.sina.com.cn/";
		String fil=".sina.com.cn/";
		String[] accept={".sina.com.cn"};
		String[] refuse={"#"};
		Page page=new Page();
		SetBase base=new SetBase();
		System.out.println(Encode.getEncode());		
		DBWaitUrl dbWaitUrl=new DBWaitUrl();
		dbWaitUrl.add(url);
		dbWaitUrl.close();
		/*
		DownLoadFile downLoadFile=new DownLoadFile();
		downLoadFile.downloadFile(url);*/
		for(int i=0;i<2000;i++)
		{
			//获取url
			page=scheduler.findWaitUrl();
			System.out.println(page.getPageURL());	
			base.setBase(page.getPageURL(),accept, refuse);
			
			//获取编码
			page.setEncode(Encode.getEncode());
			
			DownloadInter download=new FromDBUrlDownload();
			try
			{
				//获取原始数据document
				page=download.downloadFile(page);
			}
			catch (Exception e) {
				continue;
			}
			System.out.println(page.getDocument());		
			PageProcessor newsPageProcessor=new NewsPageProcessor();
			//获取解析之后的结果
			page=newsPageProcessor.run(page);
			System.out.println(page.getDataItems().getP());	
			
			//储存结果
			scheduler.pageAndUrlToDB(page);
			DBWaitUrl dbWaitUrl2=new DBWaitUrl();
			if(dbWaitUrl2.Empty()==true)
				{
					dbWaitUrl2.close();
				break;
				}
				else
				{
					dbWaitUrl2.close();
				}
		}
		
	}
	public void run(String url1,String accept1,String refuse1)
	{
		System.out.println(url1+accept1+refuse1);
		
		String url =url1;
		//String fil=".sina.com.cn/";
		String[] accept={accept1};
		String[] refuse={refuse1};
		Page page=new Page();
		SetBase base=new SetBase();
		System.out.println(Encode.getEncode());	
		if(url1!=""||url1!=null)
		{
			Scheduler scheduler=new SchedulerIm();
			scheduler.cleanDB();
			DBWaitUrl dbWaitUrl=new DBWaitUrl();
			dbWaitUrl.add(url);
			/*
			DownLoadFile downLoadFile=new DownLoadFile();
			downLoadFile.downloadFile(url);*/
			for(int i=0;i<2000;i++)
			{
				//获取url
				page=scheduler.findWaitUrl();
				System.out.println("page.getPageURL():"+page.getPageURL());	
				base.setBase(page.getPageURL(),accept, refuse);
				
				//获取编码
				page.setEncode(Encode.getEncode());
				
				DownloadInter download=new FromDBUrlDownload();
				try
				{
					//获取原始数据document
					page=download.downloadFile(page);
				}
				catch (Exception e) {
					continue;
				}
				//System.out.println(page.getDocument());		
				PageProcessor newsPageProcessor=new NewsPageProcessor();
				//获取解析之后的结果
				page=newsPageProcessor.run(page);
				System.out.println("page.getDataItems().getP():"+page.getDataItems().getP());	
				System.out.println("page.getDataItems().getP():"+page.getUrl());	
				//储存结果
				scheduler.pageAndUrlToDB(page);
				
				/*if(dbWaitUrl.Empty()==true)
					{
					System.out.println("数据库没有url了");
					break;
					
					}*/
			}
		}
		
	}
}
