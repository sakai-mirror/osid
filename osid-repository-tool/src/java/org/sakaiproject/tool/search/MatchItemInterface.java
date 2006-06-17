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


/**
 * Encapsulate one matching search item.  The rendering process depends
 * on these.
 */
public interface MatchItemInterface 
{

	/**
	 * Fetch the URL of the "preview" view of the matching item
	 * @return A fully qualified URL value
	 */
	public String getPreviewUrl();

	/**
	 * Fetch the text associated with the "preview" URL
	 * @return Text
	 */
	public String getPreviewText();

	/**
	 * Fetch the image source associated with the "preview" URL
	 * @return IMG tag SRC attribute
	 */
	public String getPreviewImage();

	/**
	 * Fetch any additional, descriptive text
	 * @return Additional text
	 */
	public String getDescription();

	/**
	 * Fetch the URL for the persistent reference
	 * @return A fully qualified URL value (minus any arguments)
	 */
	public String getPersistentUrl();

	/**
	 * Fetch simple parameters for the persistent reference
	 * @return Parameters (<code>?name1=value1&name2=value2</code>)
	 */
	public String getPersistentUrlParameters();

	/**
	 * Fetch any persistent reference parameters that <i>must be</i> URL encoded.
	 *<p>
	 * Note: the HTML editor parses encoded text, replacing entity sequences
	 * and the like with the "real" characters - in rare cases, this can cause
	 * the resultant URL to fail.  As a work around, the query handler can
	 * specifiy any parameters that need to be wrapped in a JavaScript escape()
	 * at URL "click time".
	 *
	 * @return Parameters (<code>?name3=value3&name4=value4</code>)
	 */
	public String getPersistentUrlParametersForEncoding();

	/**
	 * Fetch the link text associated with the persistent reference
	 * @return Anchor text
	 */
	public String getPersistentText();
}