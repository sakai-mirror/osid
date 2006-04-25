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

import org.w3c.dom.Element;

/**
 * Interface for HTML result rendering
 */
public interface ResultPageInterface 
{

	/**
	 * Initialize (values from the query operation are available)
	 * @param query Search query handler
	 * @param searchResult Search results
	 */
	 public void initialize(QueryBase query, SearchResultBase searchResult);

	/**
	 * Initialize (no query values are available)
	 * @param formType Type of query (form type)
	 * @param css URL for our CSS file
	 */
	 public void initialize(String formType, String css);

	/**
	 * Render the HTML HEAD portion of our response
	 */
	public Element doResultPageHeader();

	/**
	 * Render the initial (starting) page body
	 */
	public Element doInitialQueryBody();

	/**
	 * Render a standard page body
	 */
	public Element doResultPageBody();

	/**
	 * Render an error page body
	 * @param text Error text
	 */
	public Element doResultPageErrorBody(String text);

	/**
	 * Render any "end of page" text
	 */
	public void doResultPageFooter();
}
