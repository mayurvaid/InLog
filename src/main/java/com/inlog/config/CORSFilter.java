package com.inlog.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods",
				"POST, GET, OPTIONS, DELETE");
		response.addHeader("Access-Control-Allow-Headers",
				"x-requested-with,X-AUTH-HEADER,X-AUTH-USER-HEADER");
		response.addHeader("Access-Control-Expose-Headers",
				"x-requested-with,X-AUTH-HEADER,X-AUTH-USER-HEADER");
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
