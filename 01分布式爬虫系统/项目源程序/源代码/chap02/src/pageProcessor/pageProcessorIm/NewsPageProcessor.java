package pageProcessor.pageProcessorIm;

import model.Encode;
import model.Page;
import pageProcessor.PageProcessor;
import pageProcessor.ParserTool.HtmlParserInter;
import pageProcessor.ParserTool.LinkParser.HtmlLinkParserIm;
import pageProcessor.ParserTool.LinkParser.HtmlLinkParserImInURL;
import pageProcessor.ParserTool.fileParser.HtmlFileParser;
import pageProcessor.readerInter.PageReadInter;
import pageProcessor.readerInter.readerIm.PageReadIm;

public class NewsPageProcessor implements PageProcessor
{

	@Override
	public Page run()
	{
		Page page=new Page();
		HtmlFileParser fileParser=new HtmlFileParser();
		PageReadInter pageRead=new PageReadIm();
		HtmlParserInter htmlParser=new  HtmlLinkParserImInURL();
		
		
		//给 pageurl 和document赋值后输出page
		//page=pageRead.getURLPage("http://mini.eastday.com/a/170611173030506.html");
		
		
		page=pageRead.getdbPage();
		//给page DataItems赋值
		fileParser.outLink(page,null);
		
		System.out.println("赋值url************************************************");
		//给page URL[]赋值
		htmlParser.outLink(page,Encode.getFil());
		System.out.println("赋值完成************************************************");
		//htmlParser.outLink("http://mini.eastday.com/a/170611173030506.html");
		return page;
	}

	@Override
	public Page run(Page page1)
	{
		Page page=page1;
		HtmlFileParser fileParser=new HtmlFileParser();
		HtmlParserInter htmlParser=new  HtmlLinkParserImInURL();
		//给page DataItems赋值
		page=fileParser.outLink(page,null);
		
		
		//给page URL[]赋值
		page=htmlParser.outLink(page,Encode.getFil());
		//htmlParser.outLink("http://mini.eastday.com/a/170611173030506.html");
		return page;
	}
	
}
