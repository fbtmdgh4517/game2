package com.game.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/input/*")
public class FormInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String str = null;
		while((str = br.readLine()) != null) {
			sb.append(str);
		}
//		System.out.println(sb);
//		System.out.println(sb.toString());
		Map<String, String> map = gson.fromJson(sb.toString(), Map.class);
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("name : " + map.get("name"));
		out.print("id : " + map.get("id"));
		out.print("pwd : " + map.get("pwd"));
		out.print("desc : " + map.get("desc"));
		out.print("trans : " + map.get("trans"));
		out.print("job : " + map.get("job"));
	}

}
