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
		Map<String, String> userInfo = new HashMap<>();
//		userInfo.put("uiName", request.getParameter("uiName"));
//		userInfo.put("uiId", request.getParameter("uiId"));
//		userInfo.put("uiPwd", request.getParameter("uiPwd"));
//		userInfo.put("uiDesc", request.getParameter("uiDesc"));
		String json = "";
		UserInfoVO uiVO = new UserInfoVO();
		if("insert".equals(uri)) {
			BufferedReader br = request.getReader();
			StringBuffer sb = new StringBuffer();
			String str = null;
			while((str=br.readLine()) != null) {
				sb.append(str);
			}
			Map<String, String> map = gson.fromJson(sb.toString(), Map.class);
			uiVO.setUiName(map.get("uiName"));
			uiVO.setUiId(map.get("uiId"));
			uiVO.setUiPwd(map.get("uiPwd"));
			uiVO.setUiDesc(map.get("uiDesc"));
			uiVO.setUiBirth(map.get("uiBirth").replace("-", ""));
			System.out.println(uiVO.toString());
			if(uiService.insertUserInfo(uiVO) == 1) {
				json = "1";
			}
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
//			userInfo.put("uiBirth", request.getParameter("uiBirth").replace("-", ""));
//			int result = uiService.insertUserInfo(userInfo);
//			request.setAttribute("msg", "유저 등록 실패");
//			request.setAttribute("url", "/user-info/insert");
//			if(result == 1) {
//				request.setAttribute("msg", "유저 등록 성공");
//				request.setAttribute("url", "/user-info/list");				
//			}
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
//		RequestDispatcher rd = request.getRequestDispatcher(path);
//		rd.forward(request, response);
	}

}
