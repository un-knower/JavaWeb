package setBase;

public class FilterForLink
{
	public static  String[] acceptUrl;
	public static  String[] refuseUrl;
	public boolean accept(String url)
	{
		for(int i=0;i<refuseUrl.length;i++)
		{
			if(url.contains(refuseUrl[i]))
				return false;
		}
		for(int i=0;i<acceptUrl.length;i++)
		{
			if(url.contains(acceptUrl[i]))
				return true;
		}
		return false;
	}
}
