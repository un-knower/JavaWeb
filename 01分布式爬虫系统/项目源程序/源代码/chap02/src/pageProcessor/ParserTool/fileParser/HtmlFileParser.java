package pageProcessor.ParserTool.fileParser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.DataItems;
import model.Encode;
import model.Page;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;
import org.htmlparser.Tag;
import org.htmlparser.Text;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.FrameTag;
import org.htmlparser.tags.HeadingTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.MetaTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.tags.ScriptTag;
import org.htmlparser.tags.StyleTag;
import org.htmlparser.tags.TitleTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.ParserUtils;
import org.htmlparser.visitors.HtmlPage;
import org.htmlparser.visitors.TextExtractingVisitor;

import pageProcessor.ParserTool.HtmlParserInter;
import pageProcessor.hanlp.Hanlp;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
//对html的数据进行处理
public class HtmlFileParser implements HtmlParserInter{
	
    //用于测试
   /* public String test(String str)
    {
    	String html="";
    	html=cleanTag(
    				test1(
    						cleanAHtml(
    							getTagHtml(
    									cleanJsAndStyleHtml(str)
    							)
    					)
    				)
    		);

    	String time=getTime(str);
    	System.out.println("time:"+time);
    	//System.out.println(str);

    	String title=cleanTag( getTitle(str));
    	System.out.println("title:"+title);
    	String h1=cleanTag( getH1(str));
    	System.out.println("h1:"+h1);
    	
    	String p=cleanCha(cleanTag(getP(cleanAHtml(cleanZhuShi(cleanJsAndStyleHtml(str))))));
    	//String p=cleanCha(cleanTag(cleanAHtml(getP(str))));
    	//String p=(getP(cleanZhuShi(str)));
    	// String p=cleanAHtml(cleanZhuShi(str));
    	//String p=cleanZhuShi(str);
    	System.out.println("p:"+p);
    	return html;
    }
    */
    //获取元素************************************************************************************************
    
    //获取时间
    public String getTime(String html)
    {
    	String str="";
    	 Pattern p = Pattern.compile("(\\d{1,4}-\\d{1,2}-\\d{1,2}(\\s)(\\d{1,2}((:)?\\d{1,2}?((:)?\\d{1,2}(秒)?)?)?)?)", Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);  
         Matcher matcher = p.matcher(html);  
         while(matcher.find())
         {
        	 str=matcher.group();
        	 break;
         }
		return str;
    }
    //获取title
    public String getTitle(String html)
    {
    	String str="";
  	  	String regex=
  			  "<title[\\s\\S]*?</title>"
  			  +"|<![\\s\\S]*?>";
  	  Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
  	  Matcher match=pattern.matcher(html);
  	  while(match.find())
  	  {
  		  str+=match.group();
  	  }
  	  //System.out.println(str);
  	  return str;
    }
  //获取h1
    public String getH1(String html)
    {
    	String str="";
  	  	String regex=
  			  "<h1[\\s\\S]*?</h1>"
  			  +"|<![\\s\\S]*?>";
  	  Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
  	  Matcher match=pattern.matcher(html);
  	  while(match.find())
  	  {
  		  str+=match.group();
  	  }
  	  //System.out.println(str);
  	  return str;
    }
  //获取p
    public String getP(String html)
    {
    	String str="";
  	  	String regex=
  			  "<p[\\s\\S]*?</p>"
  			  +"|<![\\s\\S]*?>";
  	  Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
  	  Matcher match=pattern.matcher(html);
  	  while(match.find())
  	  {
  		  str+=match.group();
  	  }
  	  //System.out.println(str);
  	  return str;
    }
    //********************************************************************************************************
 
    //除去所有标签
    public String test1(String str)
    {
    	 Parser parser = null;
		try
		{
			parser = new Parser(str);
		} catch (ParserException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         TextExtractingVisitor visitor = new TextExtractingVisitor();  
         try
		{
			parser.visitAllNodesWith(visitor);
		} catch (ParserException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         //System.out.println(visitor.getExtractedText());
		return visitor.getExtractedText();  
    }

 //去除a标签
    public String cleanAHtml(String html){
  	  String regex="<a[\\s\\S]*?</a>"
  			  +"|<ahref[\\s\\S]*?</a>"
  			  ;
  	  Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
  	  Matcher match=pattern.matcher(html);
  	  html=match.replaceAll("");
  	//  System.out.println(html);
  	  return html;
  	 }
    //获取</p>个数
    public String getPNumber(String html){
    	  String regex="<p[\\s\\S]*?</p>";
    	  Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
    	  Matcher match=pattern.matcher(html);
    	  while(match.find())
    	  {
    		  
    	  }
    	  return html;
    	 }
    //将p标签进行替换为/n和空格
    public String replaceP(String html){
    	  String regex="<p>";
    	  Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
    	  Matcher match=pattern.matcher(html);
    	  html=match.replaceAll("");
    	  String regex2="</p>";
    	  Pattern pattern2=Pattern.compile(regex2,Pattern.CASE_INSENSITIVE);
    	  Matcher match2=pattern2.matcher(html);
    	  html=match2.replaceAll("  ");
    	//  System.out.println(html);
    	  return html;
    	 }
  //去除注释
    public String cleanZhuShi(String html){
    	  String regex="<!--[\\s\\S]*?-->"
    			  ;
    	  Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
    	  Matcher match=pattern.matcher(html);
    	  html=match.replaceAll("");
    	//  System.out.println(html);
    	  return html;
    	 }
    
    //去除特殊字符| &  和空格
    public String cleanCha(String html){
    	  String regex="\\|" 
    			  +"|\\&"
    			  +"|\\s"
    			 // +"|\\┊"
    			  +"|<![\\s\\S]*?>";
    	  Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
    	  Matcher match=pattern.matcher(html);
    	  html=match.replaceAll("");
    	  return html;
    	 }

    //去除标签头
    public String cleanTag(String html){
    	  String regex="<p[\\s\\S]*?>" 
    			  +"|</p>"
    			  +"|<h1[\\s\\S]*?>"
    			  +"|</h1>"
    			  +"|<title[\\s\\S]*?>"
    			  +"|</title>"
    			  +"|<span[\\s\\S]*?>"
    			  +"|</span>"
    			  +"|<strong[\\s\\S]*?>"
    			  +"|</strong>"
    			  +"|<div[\\s\\S]*?>"
    			  +"|</div>"
    			  +"|<input[\\s\\S]*?>"
    			  +"|<img[\\s\\S]*?>"
    			  +"|<![\\s\\S]*?>";
    	  Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
    	  Matcher match=pattern.matcher(html);
    	  html=match.replaceAll("");
    	  return html;
    	 }

    //清除js 和 style
    public String cleanJsAndStyleHtml(String html){
    	  String regex="<script[\\s\\S]*?</script>" +
    	    "|<style[\\s\\S]*?</style>"+
    	    "|<![\\s\\S]*?>";
    	  Pattern pattern=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
    	  Matcher match=pattern.matcher(html);
    	  html=match.replaceAll("");
    	  return html;
    	 }
  //*******************************************************************************************************  
 
	@Override
	public Page outLink(Page page, String fil)
	{
		DataItems items=new DataItems();
		String str=page.getDocument();
		//获取时间
    	String time=getTime(str);
    	items.setTime(time);
    	//获取title
    	String title=cleanTag(cleanAHtml( getTitle(str)));
    	items.setTitle(title);
    	//获取h1
    	String h1=cleanTag(cleanAHtml( getH1(str)));
    	items.setH1(h1);
    	//获取p
    	String p=cleanCha(cleanTag(replaceP(getP(cleanAHtml(cleanZhuShi(cleanJsAndStyleHtml(str)))))));
    	items.setP(p);
    	
    	Hanlp hanlp=new Hanlp();
    	List<String> key=hanlp.getMainIdea(p, 5);
    	String outkey="";
        Iterator<String>it= key.iterator();
        while(it.hasNext())
        {
        	outkey+=it.next()+" ";
        }
        System.out.println("关键字："+outkey);
    	items.setKey(outkey);
        
    	List<String>zhaiyao=hanlp.getZhaiYao(p,3);
    	String outzhaiyao="";
    	Iterator<String>it2= zhaiyao.iterator();
        while(it2.hasNext())
        {
        	outzhaiyao+=it2.next()+" ";
        }
        System.out.println("摘要："+outzhaiyao);
        items.setZhaiyao(outzhaiyao);
        
    	page.setDataItems(items);
		return page;
	}

}
