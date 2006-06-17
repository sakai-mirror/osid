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

/**
 * Search characteristics, all matching items
 */
public interface SearchResultInterface 
{

	/**
	 * Save various attributes of the general search request
	 * @param query The QueryBase extension that sent the search request
	 */
  public void initialize(QueryBase query);

	/**
	 * Add a MatchItem object
	 * @param item MatchItem to add
	 */
	public void addItem(MatchItem item);

	/**
	 * Fetch the original query text
	 * @return Search string
	 */
	public String getQuery();

	/**
	 * Return search results as a String
	 * @return Result Document
	 */
	public String getSearchResponseString();

	/**
	 * Return the starting item number for this search (one based)
	 * @return Starting item number
	 */
	public int getSearchStart();

	/**
	 * Return the count of matching items found
	 * @return Item count
	 */
	public int getMatchCount();

	/**
	 * Get an iterator to the result list
	 * @return SearchResult Iterator
	 */
	public Iterator iterator();

	/**
	 * Return the MatchItem list as a simple array
	 * @return MatchItem array
	 */
	public MatchItem[] toArray();
}