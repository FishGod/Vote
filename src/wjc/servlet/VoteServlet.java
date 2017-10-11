package wjc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wjc.dao.VoteDao;
import wjc.model.VoteDetail;
import wjc.model.VoteInfor;
import wjc.util.PageUtil;



public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    VoteDao voteDao;
	private int count;
	private int gopage;
    Map<String, Object> map;
    PageUtil pageUtil;
    List<VoteInfor> infors;
    List<VoteDetail> details;
    public VoteServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String flag=request.getParameter("flag");
		if(flag.equals("join")){
			//参与投票
			voteDao=new VoteDao();
			details=new ArrayList<VoteDetail>();
			int vid=Integer.parseInt(request.getParameter("vid"));
			details=voteDao.showVoteDetail(vid);
			VoteInfor voteInfor=new VoteInfor();
			voteInfor=voteDao.getInfor(vid);
			request.setAttribute("voteInfor", voteInfor);
			request.setAttribute("details", details);
			request.setAttribute("voteId", vid);
			System.out.println(voteInfor.getVoteType());
			request.getRequestDispatcher("main/joinvote.jsp").forward(request,response);
		}else if(flag.equals("votesearch")){
			//投票查询
			String str=request.getParameter("search");
			//如果查询输入为空
			if(str.equals("")||str==null){
				request.getRequestDispatcher("main/returnlist.jsp").forward(request,response);
			}else{
			//不为空
				voteDao=new VoteDao();
				infors=new ArrayList<VoteInfor>();
				infors=voteDao.searchVote(str);
				request.setAttribute("infors", infors);
				request.getRequestDispatcher("main/search.jsp").forward(request,response);
			}
		}else if(flag.equals("addnew")){
			//添加新投票
			if(request.getParameter("vname")==null||request.getParameter("vname").equals("")){
				response.sendRedirect("main/returnlist.jsp");
			}else{
				boolean t1,t2;
				voteDao=new VoteDao();
				String vname=request.getParameter("vname");
				String vtype=request.getParameter("votetype");
				//获取添加投票的选项
				String []opt=request.getParameterValues("voteoption");
				t1=voteDao.addVote(vname, vtype);
				int vid=voteDao.getInsertId();
				t2=voteDao.addOption(vid, opt);
				if(t1&&t2){
					response.sendRedirect("main/returnlist.jsp");
				}
			}
		}else if(flag.equals("maintenance")){
			//维护，修改投票信息
			int vid=Integer.parseInt(request.getParameter("vid"));
			VoteInfor infor=new VoteInfor();
			voteDao=new VoteDao();
			infor=voteDao.getInfor(vid);
			details=new ArrayList<VoteDetail>();
			details=voteDao.showVoteDetail(vid);
			request.setAttribute("infor", infor);
			request.setAttribute("details", details);
			request.getRequestDispatcher("main/maintenancepage.jsp").forward(request,response);
		}else if(flag.equals("maintenancesubmit")){
			int vid=Integer.parseInt(request.getParameter("vid"));
			String vname=request.getParameter("vname");
			String vtype=request.getParameter("votetype");
			String []opt=request.getParameterValues("voteoption");
			voteDao=new VoteDao();
			details=new ArrayList<VoteDetail>();
			//删除已存在选项
			voteDao.removeOption(vid);
			//添加修改后的选项，修改投票主题信息
			boolean b1=voteDao.voteModify(vid, vname, vtype);
			boolean b2=voteDao.addOption(vid, opt);
			if(b1&&b2){
				HttpSession session=request.getSession();
				voteDao=new VoteDao();
				//投票总数
				pageUtil=new PageUtil();
				count=voteDao.voteCount();
				pageUtil.setRowPageData(gopage,count);
				//总页数
				int totalpage=pageUtil.getPageNum();
				//当前页
				int nowpage=pageUtil.getGoPage();
				map=new HashMap<String, Object>();
				map.put("pageUtil", pageUtil);
				List<VoteInfor> voInfors=voteDao.showVoteList(map);
				
				session.setAttribute("totalpage", totalpage);
				session.setAttribute("nowpage", nowpage);
				session.setAttribute("voInfors",voInfors);
				request.getRequestDispatcher("main/maintenance.jsp").forward(request,response);
			}
		}else if(flag.equals("checkvote")){
			//查看投票
			
			int vid=Integer.parseInt(request.getParameter("vid"));
			voteDao=new VoteDao();
			details=new ArrayList<VoteDetail>();
			details=voteDao.showVoteDetail(vid);
			VoteInfor voteInfor=new VoteInfor();
			voteInfor=voteDao.getInfor(vid);
			request.setAttribute("voteInfor", voteInfor);
			//总票数
			int totalNumber=0;
			for(VoteDetail v:details){
				totalNumber+=v.getOptNumber();
			}
			if(totalNumber==0){
				totalNumber=1;
			}
			request.setAttribute("details", details);
			request.setAttribute("totalNumber", totalNumber);
			request.getRequestDispatcher("main/showvote.jsp").forward(request,response);
		}else if(flag.equals("vote")){
			//投票
			if(request.getParameterValues("voteoption")==null||request.getParameterValues("voteoption").equals("")){
				request.getRequestDispatcher("main/returnlist.jsp").forward(request,response);
			}else{
				String []a=request.getParameterValues("voteoption");
				voteDao=new VoteDao();
				boolean b=voteDao.vote(a);
				if(b){
					response.sendRedirect("main/returnlist.jsp");
				}else{
					response.setCharacterEncoding("utf-8");
					response.setContentType("text/html; charset=utf-8");
					response.getWriter().print("<script>alert('投票失败!');history.go(-1);</script>");
				}
			}
		}
	}

}
