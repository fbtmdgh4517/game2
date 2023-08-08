package com.game.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.common.CommonView;
import com.game.service.BoardInfoService;
import com.game.service.impl.BoardInfoServiceImpl;
import com.game.vo.BoardInfoVO;

@WebServlet("/board-info/*")
public class BoardInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardInfoService biService = new BoardInfoServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = CommonView.getCmd(request);
		String path = "/WEB-INF/views/board-info";
		if("list".equals(uri)) {
			path += "/list.jsp";
//			List<BoardInfoVO> list = biService.selectBoardInfoList(null);				
			if(request.getParameter("searchType") != null && request.getParameter("searchStr") != null) {
				Map<String, String> param = new HashMap<>();
				String key = request.getParameter("searchType");
				String value = request.getParameter("searchStr");
				param.put("key", key);
				param.put("value", value);				
			}
			request.setAttribute("boardInfoList", biService.selectBoardInfoList(null));
		} else if("insert".equals(uri)) {
			path += "/insert.jsp";
		} else if("view".equals(uri)) {
			path += "/view.jsp";
			request.setAttribute("boardInfo", biService.selectBoardInfo(request.getParameter("biNum")));
		} else if("update".equals(uri)) {
			path += "/update.jsp";
			request.setAttribute("boardInfo", biService.selectBoardInfo(request.getParameter("biNum")));
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = CommonView.getCmd(request);
		String path = "/WEB-INF/views/message.jsp";
		Map<String, String> boardInfo = new HashMap<>();
		boardInfo.put("biTitle", request.getParameter("biTitle"));
		boardInfo.put("biContent", request.getParameter("biContent"));
		
		if("insert".equals(uri)) {
			boardInfo.put("uiNum", request.getParameter("uiNum"));
			int result = biService.insertBoardInfo(boardInfo);
			request.setAttribute("msg", "작성 실패");
			request.setAttribute("url", "/board-info/list");
			if(result == 1) {
				request.setAttribute("msg", "작성 성공");
				request.setAttribute("url", "/board-info/list");				
			}
		} else if("update".equals(uri)) {
			boardInfo.put("biNum", request.getParameter("biNum"));
			int result = biService.updateBoardInfo(boardInfo);
			request.setAttribute("msg", "수정 실패");
			request.setAttribute("url", "/board-info/view?biNum=" + request.getParameter("biNum"));
			if(result == 1) {
				request.setAttribute("msg", "수정 성공");
				request.setAttribute("url", "/board-info/list");				
			}
		} else if("delete".equals(uri)) {
			int result = biService.deleteBoardInfo(request.getParameter("biNum"));
			request.setAttribute("msg", "삭제 실패");
			request.setAttribute("url", "/board-info/list");
			if(result == 1) {
				request.setAttribute("msg", "삭제 성공");
				request.setAttribute("url", "/board-info/list");				
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
