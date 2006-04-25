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

import java.util.Iterator;

import org.w3c.dom.Element;

/**
 * Render results for the New York Public Library Digital Gallery
 */
public class Result 
	extends ResultPageBase 
{

  /**
   * Constructor
   */
  public Result() {
    super();
  }

  /**
   * Render the page body
   * @return The BODY element
   */
  public Element doResultPageBody() {
   	SearchResultBase	searchResult;
    Element   				renderElement;
    Element   				body, table;
		int								recordNumber;
		/*
		 * Set up the search results and do initial page formatting
		 */
		searchResult	= getSearchResult();
		recordNumber	= searchResult.getSearchStart();

    body  	      = super.doResultPageBody();
    table 	      = doStandardTableSetup(body);
    renderElement = table;
		/*
		 * Quit now if nothing matched
		 */
		if (searchResult.getMatchCount() == 0) {
			doNoResultForQuery(table, getQuery());
      return body;
    }
		/*
		 * Render each image
		 */
   	for (Iterator iterator = searchResult.iterator(); iterator.hasNext(); ) {
      renderElement = doImageTable(renderElement,
      														 recordNumber++,
      														 (MatchItem) iterator.next());
    }
    return body;
  }
}
