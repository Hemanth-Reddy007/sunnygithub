package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dbcon.dbcon;
@WebServlet("/Userservlet")
public class Userservlet extends HttpServlet 
{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		Connection c=dbcon.datacon();
		try {
			PreparedStatement p=c.prepareStatement("select * from usertable where username=? and password=?");
			p.setString(1, request.getParameter("user"));
			p.setString(2, request.getParameter("pass"));
			ResultSet rs=p.executeQuery();
			if(rs.next())
			{
				request.getRequestDispatcher("userhome.jsp").forward(request, response);
			}
			else
			{
				response.sendError(1,"Invalid Authentication");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
