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

package org.sakaiproject.tool.search;

import java.util.Map;

import org.sakaiproject.tool.OSIDRepository.SessionContext;
import org.sakaiproject.tool.util.HttpTransactionUtils;

/**
 * 
 * @inheritDoc
 *
 */
public interface QueryInterface 
{
	/**
	 * Default character set
	 */
	public final static String	CS_DEFAULT		= HttpTransactionUtils.DEFAULTCS;
	/**
	 * ISO 8859
	 */
	public final static String	CS_ISO				= "ISO-8859-1";
	/**
	 * UTF 8 encoding
	 */
	public final static String	CS_UTF8				= "UTF-8";

	/**
	 * Parse and save request parameters
	 * @param parameterMap A map of request details (name=value pairs)
	 */
  public void parseRequest(Map parameterMap);

	/**
	 * Fetch a request parameter by name
	 * @param name Parameter name
	 * @return Parameter value
	 */
  public String getRequestParameter(String name);

	/**
	 * Fetch the entire request parameter Map
	 * @return Parameter Map
	 */
  public Map getRequestParameterMap();

  /**
   * Request a character set
   * @param characterSet Desired character set (UTF-8, ISO-8859-1, etc)
   */
  public void setDefaultCharacterSet(String characterSet);
  /**
   * Set the URL for this query
   * @param url The requested URL
   */
  public void setUrl(String url);
  /**
   * Get the URL for this query
   * @return The requested URL
   */
  public String getUrl();
	/**
	 * Specify search text
	 * @param searchString Text to search for
	 */
  public void setSearchString(String searchString);
	/**
	 * Fetch the requested search text
	 * @return The search text
	 */
  public String	getSearchString();
	/**
	 * Specify desired number of images
	 * @param count Count of images requested from the search engine
	 */
  public void setImageCount(String count);
	/**
	 * Fetch the desired image count
	 * @return The desired number of images
	 */
  public String	getImageCount();
	/**
	 * Set a named parameter related to this search.  Sent to the search server,
	 * this is usually a modifier for the search (return ## matches, etc)
	 * @param name Parameter name
	 * @param value Parameter value
	 */
  public void setParameter(String name, String value);
	/**
	 * Get a named parameter
	 * @return Parameter value
	 */
  public String getParameter(String name);
  /**
   * Get query results
   * @return The results page (as a String)
   */
  public String getResponseString();
	/**
	 * Common initialization
	 * @param session Session context for this query
	 */
  public void initialize(SessionContext session);
	/**
	 * Submit a search request to the server
	 * @return Server status (ala HTTP, 200 is success)
	 */
  public int 			submit();
}
