package com.game.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.game.common.CommonRequest;
import com.game.service.UserInfoService;
import com.game.service.impl.UserInfoServiceImpl;

@WebServlet("/user-info/*")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoService uiService = new UserInfoServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = CommonRequest.getURI(request, response);
		String path = "/WEB-INF/views";
		if("list".equals(uri)) {
			path += "/user-info/list.jsp";
			request.setAttribute("userInfoList", uiService.selectUserInfoList(null));
		} else if("view".equals(uri)) {
			path += "/user-info/view.jsp";
			String uiNum = request.getParameter("uiNum");
			request.setAttribute("userInfo", uiService.selectUserInfo(uiNum));
		} else if("insert".equals(uri)) {
			path += "/user-info/insert.jsp";
		} else if("update".equals(uri)) {
			path += "/user-info/update.jsp";
			request.setAttribute("userInfo", uiService.selectUserInfo(request.getParameter("uiNum")));
		} else if("logout".equals(uri)) {
			path = "/WEB-INF/views/message.jsp";
			HttpSession session = request.getSession();
			session.invalidate();
			request.setAttribute("msg", "로그아웃 되었습니다.");
			request.setAttribute("url", "/");
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = CommonRequest.getURI(request, response);
		String path = "/WEB-INF/views/message.jsp";
		Map<String, String> userInfo = new HashMap<>();
		userInfo.put("uiName", request.getParameter("uiName"));
		userInfo.put("uiId", request.getParameter("uiId"));
		userInfo.put("uiPwd", request.getParameter("uiPwd"));
		userInfo.put("uiDesc", request.getParameter("uiDesc"));
		
		if("insert".equals(uri)) {
			userInfo.put("uiBirth", request.getParameter("uiBirth").replace("-", ""));
			int result = uiService.insertUserInfo(userInfo);
			request.setAttribute("msg", "유저 등록 실패");
			request.setAttribute("url", "/user-info/insert");
			if(result == 1) {
				request.setAttribute("msg", "유저 등록 성공");
				request.setAttribute("url", "/user-info/list");				
			}
		} else if("delete".equals(uri)) {
			int result = uiService.deleteUserInfo(request.getParameter("uiNum"));
			request.setAttribute("msg", "삭제 실패");
			request.setAttribute("url", "/user-info/view?uiNum=" + request.getParameter("uiNum"));
			if(result == 1) {
				request.setAttribute("msg", "삭제 성공");
				request.setAttribute("url", "/user-info/list");				
			}
		} else if("update".equals(uri)) {
			userInfo.put("uiBirth", request.getParameter("uiBirth").replace("-", ""));
			userInfo.put("uiNum", request.getParameter("uiNum"));
			int result = uiService.updateUserInfo(userInfo);
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
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}