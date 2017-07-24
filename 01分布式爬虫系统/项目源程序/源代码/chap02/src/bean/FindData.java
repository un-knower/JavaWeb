package bean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DataItems;

public class FindData extends HttpServlet
{

	private static final long serialVersionUID = -632126592735700106L;
	private DBBean dbBean = new DBBean();
	private ResultSet reslut;
	private List<DataItems> reslutset=new ArrayList<DataItems>();
	/**
	 * Constructor of the object.
	 */
	public FindData()
	{
		super();
	}

	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session=request.getSession();
		
		try
		{
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String SQLString = "select * from  page";
			reslut = dbBean.executeQuery(SQLString);
			String encode="GBK";
			if(reslut.next())
			{	
				reslutset.clear();
				while (reslut.next())
				{
					DataItems dataItems =new DataItems();
					String h1=new String(reslut.getBytes("h1"),encode);
					String title=new String(reslut.getBytes("title"),encode);
					String  p=new String(reslut.getBytes("p"),encode);
					String key=new String(reslut.getBytes("keyname"),encode);
					String zhaiyao=new String(reslut.getBytes("zhaiyao"),encode);
					String time=reslut.getString("time");
					String url=reslut.getString("url");
					dataItems.setH1(h1);
					dataItems.setKey(key);
					dataItems.setP(p);
					dataItems.setTime(time);
					dataItems.setUrl(url);
					if(title.length()>=20)
					{
						dataItems.setTitle(title.substring(0,19)+"... ...");
					}
					else if(title.length()==0||title==null||title=="null")
					{
						dataItems.setTitle("暂无数据");
					}
					
					if(zhaiyao.length()>=20)
					{
						dataItems.setZhaiyao(zhaiyao.substring(0,19)+"... ...");
					}
					else if(zhaiyao.length()==0)
					{
						dataItems.setZhaiyao("暂无数据");
					}
					reslutset.add(dataItems);
				}		
				session.setAttribute("SearchReslut", reslutset);
				response.sendRedirect("DataShow.jsp");
			}
			else 
			{
				session.setAttribute("SearchReslut", reslutset);
				response.sendRedirect("DataShow.jsp");
			}
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			session.setAttribute("searcherror","暂无数据");
		}
	
	
	}

	public void init() throws ServletException
	{
		System.out.println("初始化");
	}

}
