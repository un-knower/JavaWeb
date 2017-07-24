package pageProcessor.ParserTool.LinkParser;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.Encode;
import model.Page;
import model.URL;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import pageProcessor.ParserTool.HtmlParserInter;
import setBase.FilterForLink;
import setBase.LinkFilter;

public class HtmlLinkParserImInURL implements HtmlParserInter
{
	//获取子链接，url为网页url，filter是链接过滤器，返回该页面子链接的HashSet
    public static Set<String> extracLinks(String url, LinkFilter filter) {

        Set<String> links = new HashSet<String>();
        try {
            Parser parser = new Parser(url);
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
                        links.add(linkUrl);
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
                        links.add(frameUrl);
                }
            }
        } catch (ParserException e) {//捕捉parser的异常
            e.printStackTrace();
        }
        return links;
        }
	
	@Override
	public Page outLink(Page page, final String fil)
	{
		String url=page.getPageURL();
		URL surl=new URL();
		//定义过滤器，提取以http://www.lietu.com开头的链接
		LinkFilter filter=new LinkFilter()
		{
			public boolean accept(String url)
			{
				for(int i=0;i<FilterForLink.refuseUrl.length;i++)
				{
					if(FilterForLink.refuseUrl[i]=="")
						{
							continue;
						}
					if(url.contains(FilterForLink.refuseUrl[i]))
						return false;
				}
				for(int i=0;i<FilterForLink.acceptUrl.length;i++)
				{
					if(url.contains(FilterForLink.acceptUrl[i]))
						return true;
				}
				return false;
			}
			
		};
    	Set<String> links=HtmlLinkParserImInURL.extracLinks(url,filter);
    	Iterator<String> it=links.iterator();
        while(it.hasNext())
        {
            Object o=it.next();
            System.out.println(o);
        }
        surl.setUrl(links);
        page.setUrl(surl);
        //System.out.println("完成");
		return page;
		
	}
}
