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
 * ProviderIterator implements a "straw-man" registry interface.
 * </p>
 * 
 * @author Massachusetts Institute of Technology
 * @version $Id: PresenceTool.java 632 2005-07-14 21:22:50Z janderse@umich.edu $
 */
public class ProviderIterator implements edu.mit.osid.registry.ProviderIterator {
    java.util.Iterator mIterator = null;

    /**
	 * Store away the input.
	 */
	protected ProviderIterator(java.util.Vector vector) {
        mIterator = vector.iterator();
    }

    /**
	 * Test if we have any more Providers to return.
	 */
    public boolean hasNextProvider()
		throws edu.mit.osid.registry.RegistryException
	{
        return mIterator.hasNext();
    }

    /**
	 * Return the next Provider from the original input.
	 */
    public edu.mit.osid.registry.Provider nextProvider()
		throws edu.mit.osid.registry.RegistryException
	{
        try {
            return (edu.mit.osid.registry.Provider) mIterator.next();
        } catch (java.util.NoSuchElementException e) {
            throw new edu.mit.osid.registry.RegistryException(org.osid.shared.SharedException.NO_MORE_ITERATOR_ELEMENTS);
        }
    }
}
