package schoolManagement.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schoolManagement.dto.teachers;

@WebServlet("/deleteTeacher")
public class deleteTeacher  extends HttpServlet{




	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String i=req.getParameter("id");
		int id=Integer.parseInt(i);
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Ravindra");
		EntityManager em1=emf.createEntityManager();
		EntityTransaction et=em1.getTransaction();
		
		teachers t=em1.find(teachers.class, id);
		
		if(!(t.equals(null))) {
		
			et.begin();
			em1.remove(t);
			et.commit();
			
			PrintWriter pw=resp.getWriter();
			pw.write("STUDENT PROFILE DELETED");
			RequestDispatcher rd=req.getRequestDispatcher("manageHomepgae.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
		}
		else {
			PrintWriter pw=resp.getWriter();
			pw.write("ID DOES NOT EXISTS");
			RequestDispatcher rd=req.getRequestDispatcher("manageHomepgae.html");
			rd.include(req, resp);
			resp.setContentType("text/html");
		}
		
	}

}
