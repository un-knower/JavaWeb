package pageProcessor.ParserTool.LinkParser;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Encode;
import model.Page;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import pageProcessor.ParserTool.HtmlParserInter;
import setBase.LinkFilter;

public class HtmlLinkParserIm implements HtmlParserInter
{
	//获取子链接，url为网页url，filter是链接过滤器，返回该页面子链接的HashSet
    public static Set<String> extracLinks(String document, LinkFilter filter) {
        Set<String> links = new HashSet<String>();
        try {
            Parser parser = new Parser();
            parser.setInputHTML(document);
            parser.setEncoding(Encode.getEncode());
            // 过滤 <frame >标签的 filter，用来提取 frame 标签里的 src 属性所表示的链接
            NodeFilter frameFilter = new NodeFilter() {
                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public boolean accept(Node node) {
                    if (node.getText().startsWith("frame src=")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            };
            // OrFilter 接受<a>标签或<frame>标签，注意NodeClassFilter()可用来过滤一类标签，linkTag对应<标签>
            OrFilter linkFilter = new OrFilter(new NodeClassFilter(
                    LinkTag.class), frameFilter);
            // 得到所有经过过滤的标签，结果为NodeList
            NodeList list = parser.extractAllNodesThatMatch(linkFilter);
            for (int i = 0; i < list.size(); i++) {
                Node tag = list.elementAt(i);
                if (tag instanceof LinkTag)// <a> 标签
                {
                    LinkTag link = (LinkTag) tag;
                    String linkUrl = link.getLink();// 调用getLink()方法得到<a>标签中的链接
                    if (filter.accept(linkUrl))//将符合filter过滤条件的链接加入链接表
                    {
                    	if(panfilter(Encode.getFil(), linkUrl))
                    	{
                   
                    		links.add(sethead(linkUrl, Encode.getURLHead()));
                		}
                    }
                
                } else{// <frame> 标签
                    // 提取 frame 里 src 属性的链接如 <frame src="test.html"/>
                    String frame = tag.getText();
                    int start = frame.indexOf("src=");
                    frame = frame.substring(start);
                    int end = frame.indexOf(" ");
                    if (end == -1)
                        end = frame.indexOf(">");
                    String frameUrl = frame.substring(5, end - 1);
                    if (filter.accept(frameUrl))
                    {
                    	if(panfilter(Encode.getFil(), frameUrl))
                    	{
                    		links.add(sethead(frameUrl, Encode.getURLHead()));
                		}
                    }
                }
            }
        } catch (ParserException e) {//捕捉parser的异常
            e.printStackTrace();
        }
        return links;
    }
    
    //输出原始数据
    public  void outLink(String document)
	{
    	
	}
    
    //给全局变量head赋值
    public void setheadToModel(String url)
    {
    	Pattern p=Pattern.compile("[\\s\\S]*?:",Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);  
	    while(matcher.find())
	    {
	       	// System.out.println(matcher.group());
	       	 Encode.setURLHead(matcher.group());
	    }
    }
    //设置网址头
    public static  String sethead(String str,String head)
	{
    	String http=head+str.substring(0,str.indexOf("/"));
    	if(http.equals("http:")||http.equals("https:"))
    	{
    		return str;
    	}
    	
	    Pattern p2=Pattern.compile("^//[\\w]*?",Pattern.CASE_INSENSITIVE);
	    Matcher matcher2 = p2.matcher(str);
		if(matcher2.find(0))
		{
		 str=head+str;
		 System.out.println(str);
		 return str;
		}
		
		
		Pattern p3=Pattern.compile("[\\w]*://[\\w]*?",Pattern.CASE_INSENSITIVE);
	    Matcher matcher3 = p3.matcher(str);
		if(matcher3.find(0))
		{
			//String str1=str.substring(str.indexOf(".")+1, str.length());
		 str=head+str.substring(str.indexOf("/")+1, str.length());
		 System.out.println(str);
		 return str;
		}
		return str;
	}
    //过滤url，filter为含有的字符串，url为要过滤的url，返回是否含
    public static  boolean panfilter(String filter,String url)
	{
		Pattern p=Pattern.compile("//[\\s\\S]*?"+filter+"/",Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);  
	        while(matcher.find())
	        {
	       	 System.out.println("url:"+matcher.group());
	       	 return true;
	        }
		return false;
	}
    

	@Override
	public Page outLink(Page page, String fil)
	{
		String url=page.getPageURL();
		String document=page.getDocument();
		Encode.setFil(fil);
		setheadToModel(url);
		//定义过滤器，提取以http://www.lietu.com开头的链接
				LinkFilter filter=new LinkFilter()
				{
					public boolean accept(String url)
					{
						if(url.startsWith(""))
							return true;
						else
							return false;
					}
				};
		    	Set<String> links=HtmlLinkParserIm.extracLinks(document,filter);
		    	Iterator<String> it=links.iterator();
		        while(it.hasNext())
		        {
		            String o=it.next();
		            sethead(o,url);
		            System.out.println(o);
		        }

		        System.out.println("完成");
		return null;
	}



}
