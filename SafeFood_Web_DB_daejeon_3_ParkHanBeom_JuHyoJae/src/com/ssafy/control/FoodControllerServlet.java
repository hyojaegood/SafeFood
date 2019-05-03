package com.ssafy.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ssafy.edu.dto.Allergy;
import com.ssafy.edu.dto.Ingredient;
import com.ssafy.edu.dto.MemberDto;
import com.ssafy.edu.model.Service;


@WebServlet("/main.do")
public class FoodControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public  void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess( request,  response);
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess( request,  response);
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		boolean isRedirect=false;
		String path="";

		Service service=Service.getInstance();// 싱글톤으로 객체 오직 한 번

		String action=request.getParameter("action");
		
		request.setAttribute("ingredients", service.findAllIngredient());
		//System.out.println(service.findAllIngredient());
		if(action.equalsIgnoreCase("list")) {


		}else if(action.equalsIgnoreCase("login")) {

			String id=request.getParameter("id");
			String pwd=request.getParameter("pwd");
			MemberDto member=service.findMemberById(id);
			System.out.println(id+" "+pwd);
			System.out.println(member.toString());
			System.out.println(member.getPassword());
			
				if(member.getPassword().equals(pwd.trim())) {
					isRedirect=true; //forward
					HttpSession session=request.getSession(true);
					session.setAttribute("id", member.getId());
					session.setAttribute("password", member.getPassword());
					session.setAttribute("name", member.getName());
					session.setAttribute("address", member.getAddress());
					session.setAttribute("email", member.getEmail());
					session.setAttribute("phoneNumber", member.getPhoneNumber());
					session.setAttribute("allergys", service.getAllergy(member.getId()));
					//session.setAttribute("alias", "ydg");
					request.setAttribute("member", member.getId());
					
					System.out.println("성공함");
					path = "./main.do?action=index";
					response.sendRedirect(path);
					
					return;
				}
			System.out.println("실패함");
			path="./main.do?action=index";
			isRedirect=true; //forward
			HttpSession session=request.getSession(true);
			session.invalidate();

		}else if(action.equalsIgnoreCase("logout")) {
			path="./main.do?action=index";
			isRedirect=true; //forward
			HttpSession session=request.getSession(true);
			session.invalidate();
		}
		else if(action.equalsIgnoreCase("signin")) {
			String id=request.getParameter("id");
			String password=request.getParameter("password");
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			String email=request.getParameter("email");
			String phoneNumber=request.getParameter("phoneNumber");
			String[] allergy=request.getParameterValues("allergy");
			String tmp="";

			
			
			System.out.println(id+" "+password+" "+name+" "+address+" "+email+" "+phoneNumber+" "+allergy);
			service.insertMember(new MemberDto(id,password,name,address,email,phoneNumber));
			if(allergy!=null) {
				
				for (int i = 0; i < allergy.length; i++) {
					Ingredient in=service.findIngredientbyName(allergy[i]);
					service.addAllergy(new Allergy(id,in.getInum()));
				}
			}
			request.setAttribute("member", service.findMemberById(id));
			System.out.println(id+"AAA");
			path="./main.do?action=index";
			isRedirect=true; //forward

		}
		else if(action.equalsIgnoreCase("memberinfo")) {
			isRedirect=false;
			String ttid=request.getParameter("id");
			System.out.println(service.findMemberById(ttid));
			request.setAttribute("member", service.findMemberById(ttid));
			
		}else if(action.equalsIgnoreCase("change")) {

			String id=request.getParameter("id").trim();
			String password=request.getParameter("password");
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			String email=request.getParameter("email");
			String phoneNumber=request.getParameter("phoneNumber");
			String[] allergy=request.getParameterValues("allergy");
			HttpSession session=request.getSession(true);
			session.setAttribute("id", id);
			session.setAttribute("password", password);
			session.setAttribute("name", name);
			session.setAttribute("address", address);
			session.setAttribute("email", email);
			session.setAttribute("phoneNumber", phoneNumber);
			
			String tmp="";
			System.out.println(id+" "+password+" "+name+" "+address+" "+email+" "+phoneNumber+" "+allergy);
			if(service.findAllergyById(id)!=null) {
				service.deleteAllergy(id);	
			}
			if(allergy!=null) {
				for (int i = 0; i < allergy.length; i++) {
					Ingredient in=service.findIngredientbyName(allergy[i]);
					System.out.println(in.toString());
					service.addAllergy(new Allergy(id,in.getInum()));
				}
			}
			session.setAttribute("allergys", service.getAllergy(id));
			isRedirect=false;
			service.updateMember(new MemberDto(id,password,name,address,email,phoneNumber));
			path="./main.do?action=list";
		}else if(action.equalsIgnoreCase("delete")) {
			String id=request.getParameter("id");
			isRedirect=true;
			service.deleteMember(id);
			HttpSession session=request.getSession(true);
			session.invalidate();
			path="./main.do?action=list";
		}else if(action.equalsIgnoreCase("index")) {
			path="./index.jsp";
			isRedirect=false; //forward
			request.setAttribute("foods", service.findAllFoods());
		}else if(action.equalsIgnoreCase("detail")) {
			System.out.println("Fdfdfddfd");
			String name=request.getParameter("name");
			System.out.println(name);
			request.setAttribute("food", service.findByName(name));
			path="./details.jsp";
			System.out.println(service.findByName(name));
			isRedirect=false; //redirect
		}else if(action.equalsIgnoreCase("service")) {
			path="./service.jsp";
			isRedirect=false; //forward
			request.setAttribute("foods", service.findAllFoods());
		}else if(action.equalsIgnoreCase("search")) {
			String keyword=request.getParameter("keyword");
			request.setAttribute("foods", service.findByMaterial(keyword));
			path="./service.jsp";
			System.out.println(service.findByMaterial(keyword));
			isRedirect=false; //redirect
		}
		
		if(!isRedirect) { //forward
			RequestDispatcher patch=request.getRequestDispatcher(path);
			patch.forward(request, response);
		}else {          // 
			response.sendRedirect(path);
		}
	}
}







