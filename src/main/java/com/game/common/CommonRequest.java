package com.game.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonRequest {

	public static String getURI(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/") + 1;
		uri = uri.substring(idx);
		return uri;
	}
}
