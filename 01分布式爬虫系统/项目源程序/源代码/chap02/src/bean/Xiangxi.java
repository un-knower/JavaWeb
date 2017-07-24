package bean;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DataItems;

public class Xiangxi extends HttpServlet
{

	private DBBean db=new DBBean();
	private ResultSet reslut=null;
	public Xiangxi()
	{
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{

		this.doPost(request, response);
			}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
			{

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String url=request.getParameter("url");
		System.out.println(url);
		if(url!=null)
		{
			String encode="GBK";
			String SQl="select * from page where url="+url+"";
			HttpSession session=request.getSession();
			reslut=db.executeQuery(SQl);
			try
			{
				while(reslut.next())
				{
					DataItems dataItems =new DataItems();
					String h1=new String(reslut.getBytes("h1"),encode);
					String title=new String(reslut.getBytes("title"),encode);
					String  p=new String(reslut.getBytes("p"),encode);
					String key=new String(reslut.getBytes("keyname"),encode);
					String zhaiyao=new String(reslut.getBytes("zhaiyao"),encode);
					String time=reslut.getString("time");
					String url2=reslut.getString("url");
					dataItems.setH1(h1);
					dataItems.setKey(key);
					dataItems.setP(p);
					dataItems.setTime(time);
					dataItems.setUrl(url2);
					if(title.length()==0||title==null||title=="null")
					{
						dataItems.setTitle("暂无数据");
					}

					if(zhaiyao.length()==0)
					{
						dataItems.setZhaiyao("暂无数据");
					}
					session.setAttribute("data",dataItems);
					response.sendRedirect("Xiangxi.jsp");
				}
				
			} catch (SQLException e)
			{
				out.write("查询失败");
			}
		}
		out.flush();
		out.close();
	}

}
