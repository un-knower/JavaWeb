package action;

import run.Run;

import com.opensymphony.xwork2.ActionSupport;

public class Runcrawler extends ActionSupport
{
	private String url;
	private String filterTrue;
	private String filterFalse;
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getFilterTrue()
	{
		return filterTrue;
	}
	public void setFilterTrue(String filterTrue)
	{
		this.filterTrue = filterTrue;
	}
	public String getFilterFalse()
	{
		return filterFalse;
	}
	public void setFilterFalse(String filterFalse)
	{
		this.filterFalse = filterFalse;
	}
	
	public void Run()
	{
		System.out.println(getUrl()+url);
		Run run=new Run();
		run.run(getUrl(), getFilterTrue(), getFilterFalse());
	}
}
