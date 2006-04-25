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

package org.sakaiproject.component.osid.registry;

/**
* <p>
 * Id implements the O.K.I. Id service interface.
 * </p>
 * 
 * @author Massachusetts Institute of Technology
 * @version $Id: PresenceTool.java 632 2005-07-14 21:22:50Z janderse@umich.edu $
 */
public class Id
	implements org.osid.shared.Id
{
    private String idString = null;

	/**
	 * Store away the input string
	*/
    protected Id(String idString)
    throws org.osid.shared.SharedException
    {
        if (idString == null)
        {
            throw new org.osid.shared.SharedException(org.osid.id.IdException.NULL_ARGUMENT);    
        }
        this.idString = idString;
    }

	/**
	 * Simply return the input string stored during construction.
	 */
    public String getIdString()
    throws org.osid.shared.SharedException
    {
        return this.idString;
    }

	/**
	 * Two Ids are equal if their string representations are equal.
	 */
    public boolean isEqual(org.osid.shared.Id id)
    throws org.osid.shared.SharedException
    {
        return id.getIdString().equals(this.idString);
    }
}
