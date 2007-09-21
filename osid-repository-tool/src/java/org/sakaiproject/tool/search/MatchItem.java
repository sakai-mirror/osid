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

import org.sakaiproject.tool.util.*;

/**
 * Encapsulate one matching search item
 */
public class MatchItem 
	implements MatchItemInterface 
{

	private String			_previewUrl;
	private String			_previewImage;
	private String			_previewText;
	private String			_description;
	private String			_persistentUrl;
	private String			_persistentParameters;
	private String			_persistentParametersForEncoding;
	private String			_persistentText;

	/**
	 * Constructor
	 */
	public MatchItem() {
	}

	/**
	 * Fetch the URL of the "preview" view of the matching item
	 * @return A fully qualified URL value
	 */
	public String getPreviewUrl() {
		return _previewUrl;
	}

	/**
	 * Set the "preview" URL
	 * @param url Preview URL string
	 */
	public void setPreviewUrl(String url) {
		_previewUrl = url;
	}

	/**
	 * Fetch the image associated with the "preview" URL
	 * @return Image source specification
	 */
	public String getPreviewImage(){
		return _previewImage;
	}

	/**
	 * Set "preview" URL image
	 * @param source Image source specification
	 */
	public void setPreviewImage(String source) {
		_previewImage = source;
	}

	/**
	 * Fetch the text associated with the "preview" URL
	 * @return Text
	 */
	public String getPreviewText(){
		return _previewText;
	}

	/**
	 * Set "preview" URL text
	 * @param text Preview text
	 */
	public void setPreviewText(String text) {
		_previewText = text;
	}

	/**
	 * Fetch any additional, descriptive text
	 * @return Additional text
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * Set description text
	 * @param text Description text
	 */
	public void setDescription(String text) {
		_description = text;
	}

	/**
	 * Fetch the URL for the persistent reference
	 * @return A fully qualified URL value (minus any arguments)
	 */
	public String getPersistentUrl() {
		return _persistentUrl;
	}

	/**
	 * Set the persistent URL
	 * @param url Persistent URL
	 */
	public void setPersistentUrl(String url) {
		_persistentUrl = url;
	}

	/**
	 * Set the persistent URL and simple parameters
	 * @param persistentHref Persistent URL
	 */
	public void setPersistentUrlAndParameters(String persistentHref) {
		String url = persistentHref;

		if (!StringUtils.isNull(url)) {
			int index;

			if ((index = url.indexOf('?')) != -1) {
				if (++index < url.length()) {
					_persistentParameters = url.substring(index);
				}
				url = url.substring(0, index);
			}
		}
		_persistentUrl = url;
	}

	/**
	 * Fetch simple parameters for the persistent reference
	 * @return Parameters (<code>?name1=value1&name2=value2</code>)
	 */
	public String getPersistentUrlParameters() {
		return _persistentParameters;
	}

	/**
	 * Set persistent URL parameter values
	 * @param parameters Parameter text (name=value pairs)
	 */
	public void setPersistentUrlParameters(String parameters) {
		_persistentParameters = parameters;
	}

	/**
	 * Fetch any persistent reference parameters that <i>must be</i> URL encoded.
	 *<p>
	 * Note: When used with IE, the HTML editor parses encoded text, replacing
	 * entity sequences and the like with "real" characters - in rare cases, this
	 * will cause the resultant URL to fail.  As a work around, the query handler
	 * can specifiy parameters that need to be wrapped in a JavaScript escape()
	 * at URL "click time".
	 *
	 * @return Parameters (<code>?name3=value3&name4=value4</code>)
	 */
	public String getPersistentUrlParametersForEncoding() {
		return _persistentParametersForEncoding;
	}

	/**
	 * Set any persistent URL parameter values that require JavaScript encoding
	 * @param parameters Parameter text (name=value pairs)
	 */
	public void setPersistentUrlParametersForEncoding(String parameters) {
		_persistentParametersForEncoding = parameters;
	}

	/**
	 * Fetch the link text associated with the persistent reference
	 * @return Anchor text
	 */
	public String getPersistentText() {
		return _persistentText;
	}

	/**
	 * Set persistent URL text
	 * @param text Anchor text
	 */
	public void setPersistentText(String text) {
		_persistentText = text;
	}
}