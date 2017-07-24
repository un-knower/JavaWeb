package downloader.downloaderIm;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import model.Encode;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import bean.DBBean;
import bean.DBPageSave;

import cpdetector.CpdetectorInter;
import cpdetector.cpdetectorIm.CpdetectorIm;

public class DownLoadFile
{

	//*********************************************************************************
	
	//*********************************************************************************
	public String byteToString(byte[] data,String url) throws UnsupportedEncodingException
	{
		if(Encode.getEncode()=="")
		{
			CpdetectorInter cpdetector=new CpdetectorIm();
			cpdetector.InURLOut(url);
		}
		
		//System.out.println("Encode.getEncode()"+Encode.getEncode());//()GB2312
		
		String res = null;
		res = new String(data,Encode.getEncode());
		return res;
	}
	
	//下载URL指向的网页
	public String downloadFile(String url)
	{
		DBPageSave dbPageSave =new DBPageSave();
		String html =null;
		//1.生成HttpClinet对象并设置参数
		HttpClient httpclient =new HttpClient();
		//设置http连接超时5秒
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		//2.生成GetMethod对象并设置参数
		GetMethod getMethod=new GetMethod(url);
		//设置get请求超时5s
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		//设置请求重试处理
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//执行http get 请求
		try{
			int statusCode =httpclient.executeMethod(getMethod);
			//判断访问的状态码
			if(statusCode!=HttpStatus.SC_OK)
			{
				System.err.println("Method failed:"+getMethod.getStatusLine());
			}
			
			//4.处理Http 响应内容
			byte[] responseBody= getMethod.getResponseBody();//读取为字节数组
			html=byteToString(responseBody,url);
			if(dbPageSave.dbinsert(url,responseBody)==false)
				{
				System.out.println("Please check your provided http address!");
				};
			//byteToString(responseBody);
		}
		catch (HttpException e)
		{
			//发生异常，协议不对或返回内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
			
		}
		catch(IOException e)
		{
			//网络异常
			e.printStackTrace();
		}finally
		{
			//释放链接
			getMethod.releaseConnection();
		}
		return html;
	}
	public String  downToString(String url)
	{
		String html="";
		//1.生成HttpClinet对象并设置参数
		HttpClient httpclient =new HttpClient();
		//设置http连接超时5秒
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
		//2.生成GetMethod对象并设置参数
		GetMethod getMethod=new GetMethod(url);
		//设置get请求超时5s
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
		//设置请求重试处理
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		//执行http get 请求
		try{
			int statusCode =httpclient.executeMethod(getMethod);
			//判断访问的状态码
			if(statusCode!=HttpStatus.SC_OK)
			{
				System.err.println("Method failed:"+getMethod.getStatusLine());
			}
			
			//4.处理Http 响应内容
			byte[] responseBody= getMethod.getResponseBody();//读取为字节数组
		
			html=byteToString(responseBody,url);
			
			
		}
		catch (HttpException e)
		{
			//发生异常，协议不对或返回内容有问题
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
			
		}
		catch(IOException e)
		{
			//网络异常
			e.printStackTrace();
		}finally
		{
			//释放链接
			getMethod.releaseConnection();
		}
		return html;
	}
}
