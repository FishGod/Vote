package wjc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wjc.model.VoteDetail;
import wjc.model.VoteInfor;
import wjc.util.JDBCUtil;
import wjc.util.PageUtil;;


public class VoteDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//投票主题数量
	public int voteCount() {
		int count = 0;
		String sql = "select count(vid) c from tb_vote";
		conn = JDBCUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count= (rs.getInt("c")); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return count;
	}
	
	//分页查询数据
	public List<VoteInfor> showVoteList(Map map){
		String sql="select * from tb_vote LIMIT ?,?";
		conn = JDBCUtil.getConnection();
		List<VoteInfor> voInfors=new ArrayList<VoteInfor>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,((PageUtil)map.get("pageUtil")).getMinNum()-1);
            pstmt.setInt(2, PageUtil.ROWNUM);
			rs = pstmt.executeQuery();
			while(rs.next()){
				VoteInfor voteInfor=new VoteInfor();
				voteInfor.setVoteId(rs.getInt("vid"));
				voteInfor.setVoteName(rs.getString("vname"));
				voteInfor.setVoteType(rs.getString("vtype"));
				voInfors.add(voteInfor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return voInfors;
	}
	
	//根据投票ID获取投票信息
	public VoteInfor getInfor(int vid){
		VoteInfor voteInfor=new VoteInfor();
		String sql="select * from tb_vote where vid=?";
		conn = JDBCUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,vid);
			rs = pstmt.executeQuery();
			while(rs.next()){
				voteInfor.setVoteId(rs.getInt("vid"));
				voteInfor.setVoteName(rs.getString("vname"));
				voteInfor.setVoteType(rs.getString("vtype"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return voteInfor;
	}
	//根据投票ID获取每个选项
	public List<VoteDetail> showVoteDetail(int voteId){
		String sql="select * from tb_option where vid=?";
		conn = JDBCUtil.getConnection();
		List<VoteDetail> voteDetails=new ArrayList<VoteDetail>();
		try {
			pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,voteId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				VoteDetail detail=new VoteDetail();
				detail.setVoteId(rs.getInt("vid"));
				detail.setOptName(rs.getString("oname"));
				detail.setOptNumber(rs.getInt("number"));
				voteDetails.add(detail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return voteDetails;
	}
	//查询投票
	public List<VoteInfor> searchVote(String str){
		String sql="select * from tb_vote where vname like ?";
		String str2="%"+str+"%";
		conn = JDBCUtil.getConnection();
		List<VoteInfor> voInfors=new ArrayList<VoteInfor>();
		try {
			pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,str2);
			rs = pstmt.executeQuery();
			while(rs.next()){
				VoteInfor voteInfor=new VoteInfor();
				voteInfor.setVoteId(rs.getInt("vid"));
				voteInfor.setVoteName(rs.getString("vname"));
				voInfors.add(voteInfor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return voInfors;
	}
	//添加投票
	public boolean addVote(String vname,String vtype){
		boolean b=false;
		int i = 0;
		String sql = "insert into tb_vote(vname,vtype) value ('"+vname+"','"+vtype+"');";
		conn = JDBCUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			if (i>0) {
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return b;
	}
	//获取刚添加投票的id
	public int getInsertId(){
		int id = 0;
		String sql="SELECT max(vid) from tb_vote;";
		conn = JDBCUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				id=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return id;
	}
	//添加投票选项
	public boolean addOption(int vid,String []optnames){
		boolean b=false;
		String sql;
		int i = 0;
		conn = JDBCUtil.getConnection();
		try {
			for(int j=0;j<optnames.length;j++){
				sql = "insert into tb_option(vid,oname,number) value ('"+vid+"','"+optnames[j]+"',"+0+");";
				pstmt = conn.prepareStatement(sql);
				i += pstmt.executeUpdate();
			}
			if (i==optnames.length) {
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return b;
	}
	//根据ID修改投票信息
	public boolean voteModify(int id,String vname,String vtype){
		boolean b=false;
		int i = 0;
		String sql = "update tb_vote set vname=? , vtype=? where vid=?";
		conn = JDBCUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,vname);
			pstmt.setString(2,vtype);
			pstmt.setInt(3,id);
			i = pstmt.executeUpdate();
			if (i>0) {
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return b;
	}
	//根据ID删除投票选项信息
	public void removeOption(int vid){
		String sql="delete from tb_option where vid=?";
		boolean b=false;
		int i = 0;
		conn = JDBCUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,vid);
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
	}
	//投票
	public boolean vote(String str[]){
		boolean b=false;
		int i=0;
		String sql="update tb_option set number=number+1 where oname=?";
		conn = JDBCUtil.getConnection();
		try {
			for(int j=0;j<str.length;j++){
				pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1,str[i]);
	            i += pstmt.executeUpdate();
			}
			if(i==str.length){
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return b;
	}
}
