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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.sakaiproject.tool.util.DomUtils;
import org.sakaiproject.tool.util.SearchException;
import org.sakaiproject.tool.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Result rendering - base class and helpers
 */
public abstract class SearchResultBase 
	implements SearchResultInterface 
{
	/**
	 * Parse the search engine response and expose pertinent results
	 */
	private	ArrayList				_itemList;
	private int							_start;
	private int							_count;

  protected String  			_searchQuery;
  protected String				_searchResponseString;
  protected Document			_searchResponseDocument;

	private String					_baseUrl;

	/**
	 * Constructor
	 */
  public SearchResultBase() {
    super();

    _itemList = new ArrayList();
    _start		= 1;
  }

	/**
	 * Populate the search result list (each subclass should implement this)
	 */
	public abstract void doParse(String database);

	/*
	 * Interface methods
	 */

	/**
	 * Save various attributes of the general search request
	 * @param query The QueryBase extension that sent the search request
	 */
  public void initialize(QueryBase query) {

  	_searchQuery						= query.getSearchString();
  	_searchResponseString		= query.getResponseString();
    _searchResponseDocument	= parseResponseString();

    saveBaseUrl(query.getUrl());
	}

	/**
	 * Add a MatchItem object
	 * @param item MatchItem to add
	 */
	public void addItem(MatchItem item) {
		_itemList.add(item);
	}

	/**
	 * Fetch the original query text
	 * @return Search string
	 */
	public String getQuery() {
		return _searchQuery;
	}

	/**
	 * Return the starting item number for this search (one based)
	 * @return Starting item number
	 */
	public int getSearchStart() {
		return _start;
	}

	/**
	 * Return the count of matching items returned
	 * @return Item count
	 */
	public int getMatchCount() {
		return _itemList.size();
	}

	/**
	 * Get an iterator to the result list
	 * @return SearchResult Iterator
	 */
	public Iterator iterator() {
		return _itemList.iterator();
	}

	/**
	 * Return the MatchItem list as a simple array
	 * @return MatchItem array
	 */
	public MatchItem[] toArray() {
		return (MatchItem[]) _itemList.toArray(new MatchItem[_itemList.size()]);
	}

	/**
	 * Return search results as a String
	 * @return Result Document
	 */
	public String getSearchResponseString() {
		return _searchResponseString;
	}

	/*
	 * Helpers
	 */

	/**
	 * Return search results as a Document
	 * @return Result Document
	 */
	public Document getSearchResponseDocument() {
		return _searchResponseDocument;
	}

	/**
	 * Parse the search engine response as HTML.
	 * See <code>initialize()</code> (override as reqired)
	 * @return Response as a DOM Document
	 */
	public Document parseResponseString() throws SearchException {
	  try {
		  return DomUtils.parseHtmlString(getSearchResponseString());
		} catch (Exception exception) {
		  throw new SearchException(exception.toString());
		}
	}

  /**
   * Save the request URL base (the server portion only)
   * @param url Request URL (with or without parameters)
   */
  public void saveBaseUrl(String url) {
    String  server  = url;
    int     protocol, slash;

    if ((protocol = server.indexOf("//")) != -1) {
      if ((slash = server.substring(protocol + 2).indexOf("/")) != -1) {
        server = server.substring(0, protocol + 2 + slash);
      }
    }
    _baseUrl = server;
  }

	/**
	 * Form a full URL (protocol, server, arguments) from a base URL and
	 * provided parameters
	 * @param baseUrl The base (or template) URL
	 * @param urlFragment The (possibly) relative URL to be combined with the base
	 * @return A full URL (as a String)
	 */
  public String getFullUrl(String baseUrl, String urlFragment) {
  	String thisUrl = baseUrl;

  	if (thisUrl == null) {
  		thisUrl = _baseUrl;
  	}

		if (thisUrl != null) {
			try {
	 			URL	base = new URL(thisUrl);
	 			return new URL(base, urlFragment).toString();

	 		} catch (MalformedURLException exception) {
	 			throw new SearchException(exception.toString());
	 		}
	 	}
		return urlFragment;
	}

	/**
	 * Form a full URL (protocol, server, arguments) from a provided URL
	 * and a previously provided base URL
	 * @param urlFragment The (possibly) relative URL to be combined with the base
	 * @return A full URL (as a String)
	 */
  public String getFullUrl(String urlFragment) {
		return getFullUrl(null, urlFragment);
	}

	/**
	 * Verify we have the expected number of Elements in a Node list
	 * @param nodeList List of collected Elements
	 * @param expected Number of Elements we expect to see
	 * @return true If we have the expected number Elements
	 */
	public boolean expectedNodeCount(NodeList nodeList, int expected) {
		String	tag;
		int			length;

		if ((length = nodeList.getLength()) == expected) {
			return true;
		}

		tag = "Element";

		if (length > 0) {
			tag = nodeList.item(0).getNodeName();
		}

    System.out.println("Unexpected "
    								+		tag
    								+ 	" count: "
    								+ 	length
    								+ 	" (ignoing entry)");
		return false;
	}

 	/**
	 * Locate select attribute of the first matching image
	 * @param parent Parent element (look here for IMG tag)
	 * @param name Attribute name (src, alt, etc)
	 * @return Image name value (null if none)
	 */
  public String getImageAttribute(Element parent, String name) {
    Element image;
    String	value;

 		if ((image = DomUtils.getElement(parent, "IMG")) == null) {
 		  return null;
 		}

    value = image.getAttribute(name);
    return StringUtils.isNull(value) ? null : value;
	}
}
