package wjc.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wjc.dao.UserDao;
import wjc.dao.VoteDao;
import wjc.model.User;
import wjc.model.VoteInfor;
import wjc.util.PageUtil;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao=new UserDao();
	Map<String, Object> map;
	private int goPage;
	private int count;
	PageUtil pageUtil=new PageUtil();
	private VoteDao voteDao;
	public UserServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String flag=request.getParameter("flag");

		//登陆
		if("login".equals(flag)){
			String userName=request.getParameter("userName");
			String userPassword=request.getParameter("userPassword");
			User user=new User();
			user=userDao.login(userName, userPassword);
			if(user.getUserName()!=null){
				/*Cookie c=new Cookie("username", user.getUserName());
				c.setMaxAge(1800);
				response.addCookie(c);
				c=new Cookie("password", user.getUserPassword());
				c.setMaxAge(1800);
				response.addCookie(c);
				
				c=new Cookie("power", user.getUserPower());
				c.setMaxAge(1800);
				response.addCookie(c);*/
				HttpSession session=request.getSession();
				session.setAttribute("username", user.getUserName());
				session.setAttribute("password", user.getUserPassword());
				session.setAttribute("power", user.getUserPower());
				voteDao=new VoteDao();
				//投票总数
				int count=voteDao.voteCount();
				pageUtil.setRowPageData(goPage,count);
				//总页数
				int totalpage=pageUtil.getPageNum();
				//当前页
				int nowpage=pageUtil.getGoPage();
				map=new HashMap<String, Object>();
				map.put("pageUtil", pageUtil);
				List<VoteInfor> voInfors=voteDao.showVoteList(map);
				session.setAttribute("voInfors", voInfors);
				session.setAttribute("totalpage", totalpage);
				session.setAttribute("nowpage", nowpage);
				response.sendRedirect("main/mainpage.jsp");
			}else{
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().print("<script>alert('账号密码错误!');history.go(-1);</script>");
			}
		}else if("register".equals(flag)){
			//注册
			String userId=request.getParameter("userId");
			String userName=request.getParameter("userName");
			String userPassword=request.getParameter("userPassword");
			User user=new User();
			user.setUserId(userId);
			user.setUserName(userName);
			user.setUserPassword(userPassword);
			//用户名已存在
			if(userDao.isexist(userName)){
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().print("<script>alert('用户名已存在!');history.go(-1);</script>");
			}
			//注册成功
			else{
				if(userDao.register(user)){
					response.sendRedirect("login.jsp");
				}
				else{
					response.sendRedirect("main/register.jsp");
				}
			}
		}else if("page".equals(flag)){
			//投票列表的上一个，下一页
			String goPage=request.getParameter("gopage");
			voteDao=new VoteDao();
			count=voteDao.voteCount();
			pageUtil.setRowPageData(Integer.parseInt(goPage),count);
			List<VoteInfor> voInfors=voteDao.showVoteList(map);
			request.setAttribute("nowpage", pageUtil.getGoPage());
			request.setAttribute("voInfors", voInfors);
			request.getRequestDispatcher("main/returnlist.jsp").forward(request,response);
		}else if("maintenancepage".equals(flag)){
			//维护页面的上一页，下一页
			String goPage=request.getParameter("gopage");
			voteDao=new VoteDao();
			count=voteDao.voteCount();
			pageUtil.setRowPageData(Integer.parseInt(goPage),count);
			List<VoteInfor> voInfors=voteDao.showVoteList(map);
			request.setAttribute("nowpage", pageUtil.getGoPage());
			request.setAttribute("voInfors", voInfors);
			request.getRequestDispatcher("main/maintenance.jsp").forward(request,response);
		}
	}
}
