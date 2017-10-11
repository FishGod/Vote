package wjc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wjc.model.User;
import wjc.util.JDBCUtil;

public class UserDao {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	User user;
	public User login(String username,String password){
		String sql="select * from user where username=? and password=?";
		conn = JDBCUtil.getConnection();
		try {
			user=new User();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			rs=pstmt.executeQuery();
			while(rs.next()){
				user.setUserId(rs.getString("id"));
				user.setUserName(rs.getString("username"));
				user.setUserPassword(rs.getString("password"));
				user.setUserPower(rs.getString("power"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return user;
	}
	public boolean register(User user){
		String sql="insert into user(id,username,password) values(?,?,?)";
		conn = JDBCUtil.getConnection();
		int i = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,user.getUserId());
			pstmt.setString(2,user.getUserName());
			pstmt.setString(3,user.getUserPassword());
			i=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return i>0;
	}
	public boolean isexist(String username){
		String sql="select * from user where username=?";
		conn = JDBCUtil.getConnection();
		boolean exist=false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,username);
			rs=pstmt.executeQuery();
			exist=rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.closeResource(conn, pstmt, null, rs);
		}
		return exist;
	}
}
