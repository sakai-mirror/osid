/**********************************************************************************
 * $URL: $
 * $Id:  $
 ***********************************************************************************
 *
 * Copyright (c) 2003, 2004, 2005, 2006 The Sakai Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *      http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 *
 **********************************************************************************/

package org.sakaiproject.tool.OSIDRepository;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sakaiproject.tool.util.DomUtils;

/*
 * public class Search extends ComponentServlet {
 */
public class OSIDSearch 
	extends HttpServlet 
{
  /**
   * Servlet context
   */
  protected ServletContext    _context;

	/**
   * Cleanup
   */
  public void destroy() {
    super.destroy();
  }

  /**
   * Initialize
   */
  /*
  public void init(ServletConfig servletConfig) throws ServletException {
		String resource;

    super.init(servletConfig);
    _context = servletConfig.getServletContext();

 try {
	    resource = "WEB-INF/" + getInitParameter("source-configuration-file");
//	    SearchSource.populate(_context.getResourceAsStream(resource));
	    resource = "WEB-INF/" + getInitParameter("security-configuration-file");
	    SecuritySetup.initialize(_context.getResourceAsStream(resource));

	  } catch (Exception exception) {
	  	throw new ServletException(exception.toString());
	  }
  }
   */

  /**
   * Service a GET request
   * @param request Standard HTTP request object
   * @param response Standard HTTP response
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
                                       throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * Service a POST request
   * @param request Standard HTTP request object
   * @param response Standard HTTP response
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
                                        throws ServletException, IOException {
    String  criteria = request.getParameter("criteria");

    System.out.println("in OSID servlet " + criteria);

    PrintWriter       writer;

    try {
        response.setContentType("text/html; charset=" + DomUtils.ENCODING);
        writer = response.getWriter();
        writer.println("<html>");
        writer.println("foo");
        writer.println("</html>");
      try { writer.flush(); } catch (Exception ignore) { }
      try { writer.close(); } catch (Exception ignore) { }
    }
    catch (Exception ignore) {}
    }
}

