package com.game.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.game.common.CommonView;
import com.game.common.JSON;
import com.game.service.UserInfoService;
import com.game.service.impl.UserInfoServiceImpl;
import com.game.vo.UserInfoVO;
import com.google.gson.Gson;

@WebServlet("/user-info/*")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoService uiService = new UserInfoServiceImpl();
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = CommonView.getCmd(request);
		String json = "";
		if("list".equals(cmd)) {
			json = gson.toJson(uiService.selectUserInfoList(null));
		} else if("view".equals(cmd) || "update".equals(cmd)) {
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = CommonView.getCmd(request);
		String path = "/WEB-INF/views/message.jsp";
		String json = "";
		UserInfoVO uiVO = new UserInfoVO();
		Map<String, String> userInfo = JSON.parse(request, Map.class);
		if(userInfo.get("uiBirth") != null) {
			userInfo.put("uiBirth", userInfo.get("uiBirth").replace("-", ""));
		}
		int result = 0;
		if("insert".equals(uri)) {
			//result = uiService.insertUserInfo(userInfo);
		} else if("delete".equals(uri)) {
			result = uiService.deleteUserInfo(request.getParameter("uiNum"));
			request.setAttribute("msg", "삭제 실패");
			request.setAttribute("url", "/user-info/view?uiNum=" + request.getParameter("uiNum"));
			if(result == 1) {
				request.setAttribute("msg", "삭제 성공");
				request.setAttribute("url", "/user-info/list");				
			}
		} else if("update".equals(uri)) {
			userInfo.put("uiBirth", request.getParameter("uiBirth").replace("-", ""));
			userInfo.put("uiNum", request.getParameter("uiNum"));
			result = uiService.updateUserInfo(userInfo);
			request.setAttribute("msg", "수정 실해");
			request.setAttribute("url", "/user-info/view?uiNum=" + request.getParameter("uiNum"));
			if(result == 1) {
				request.setAttribute("msg", "수정 성공");
				request.setAttribute("url", "/user-info/view?uiNum=" + request.getParameter("uiNum"));
			}
		} else if("login".equals(uri)) {
			HttpSession session = request.getSession();
			String uiId = request.getParameter("uiId");
			String uiPwd = request.getParameter("uiPwd");
			Map<String, String> user = uiService.selectUserInfoById(uiId);
			request.setAttribute("msg", "로그인 실해");
			request.setAttribute("url", "/");
			if(user != null) {
				if(user.get("uiPwd").equals(uiPwd)) {
					session.setAttribute("user", user);
					request.setAttribute("msg", "로그인 성공");
					request.setAttribute("url", "/");
				}
			}
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}

}
