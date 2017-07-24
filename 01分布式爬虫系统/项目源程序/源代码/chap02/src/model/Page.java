package model;

public class Page
{
	private String pageURL=null;
	private String document=null;
	private String encode=null;
	private URL url=new URL();
    private DataItems dataItems=new DataItems();
	public URL getUrl()
	{
		return url;
	}
	public void setUrl(URL url)
	{
		this.url = url;
	}
	public DataItems getDataItems()
	{
		return dataItems;
	}
	public void setDataItems(DataItems dataItems)
	{
		this.dataItems = dataItems;
	}
	public String getDocument()
	{
		return document;
	}
	public void setDocument(String document)
	{
		this.document = document;
	}
	public String getPageURL()
	{
		return pageURL;
	}
	public void setPageURL(String pageURL)
	{
		this.pageURL = pageURL;
	}
	public String getEncode()
	{
		return encode;
	}
	public void setEncode(String encode)
	{
		this.encode = encode;
	}
	
}
