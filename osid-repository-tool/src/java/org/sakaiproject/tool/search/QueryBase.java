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


/**
 * This module provides a single, core implementation of SearchQueryInterface.
 * It's referenced in the search servlet, Search.java, to obtain "lowest
 * common denominator" functionality.
 *
 * In reality, each search application should extend QueryBase
 * and implement appropriate methods.  See HttpTransactionQueryBase.java
 * for an example.
 */
public abstract class QueryBase 
	implements QueryInterface 
{

	private	Map			_parameterMap;

	/**
	 * Do a query - there is no base implementation.
	 */
  public abstract void doQuery(String baseURL);

	/**
	 * Populate user request parameters
	 * @param parameterMap Request details as a map (name=value pairs)
	 */
	protected void populateRequestParameters(Map parameterMap) {
	 	_parameterMap = parameterMap;
	}

	/**
	 * Parse and save user request parameters.  This base method supports only
	 * the standard, simple query format.  Override if necessary.
	 * @param parameterMap Request details (name=value pairs)
	 */
  public void parseRequest(Map parameterMap) {

		populateRequestParameters(parameterMap);

  	if (getRequestParameter("database") == null) {
      throw new IllegalArgumentException("Missing database name");
    }

  	if (getRequestParameter("searchEngine") == null) {
      throw new IllegalArgumentException("Missing search engine");
    }

  	if (getRequestParameter("searchString") == null) {
      throw new IllegalArgumentException("Missing search text");
    }

    setSearchString(getRequestParameter("searchString"));
  }

	/**
	 * Fetch a request parameter by name
	 * @param name Parameter name
	 * @return Parameter value
	 */
  public String	getRequestParameter(String name) {
  	return (String) _parameterMap.get(name);
	}

	/**
	 * Fetch the entire request parameter Map
	 * @return Parameter Map
	 */
  public Map getRequestParameterMap() {
  	return _parameterMap;
  }

	/**
	 * Fetch the desired image count
	 * @return The desired number of images
	 */
  public String	getImageCount() {
  	return ResultPageBase.IMAGES_PER_PAGE;

  }

	/*
	 * The following methods are required by the interface.  Any that you wish
	 * to use should be overridden in your application.
	 */
  public void setImageCount(String count) {
  	throw new UnsupportedOperationException();
  }

  public void setUrl(String url) {
  	throw new UnsupportedOperationException();
  }

  public String getUrl() {
  	throw new UnsupportedOperationException();
 }

  public void setSearchString(String searchString) {
  	throw new UnsupportedOperationException();
  }

  public String getSearchString() {
  	throw new UnsupportedOperationException();
  }

  public void setParameter(String name, String value) {
  	throw new UnsupportedOperationException();
  }

  public String getParameter(String name, String value) {
  	throw new UnsupportedOperationException();
  }

  public int submit() {
  	throw new UnsupportedOperationException();
  }

	public String getResponseString() {
  	throw new UnsupportedOperationException();
  }
}