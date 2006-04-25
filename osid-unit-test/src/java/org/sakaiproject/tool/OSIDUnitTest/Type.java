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

package org.sakaiproject.tool.OSIDUnitTest;

/**
  * Subclass of Type that returns an instance of a specific Type based on input arguments.
  */
public class Type 
	extends org.osid.shared.Type 
{
	
	/**
	  *  Three argument constructor.  The fourth argument, description, is optional and not
	  *  used in tests for equality.
	  */
    protected Type(String authority, String domain, String keyword) {
        super(authority, domain, keyword);
    }

	/**
  	  *  Four argument constructor.
	 */
    protected Type(String authority, String domain, String keyword,
        String description) {
        super(authority, domain, keyword, description);
    }
}
