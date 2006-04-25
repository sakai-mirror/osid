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

package org.sakaiproject.component.osid.repository.registry;

/**
 * <p>
 * AssetIterator implements an interface with that name in the Repository OSID.
 * </p>
 * 
 * @author Massachusetts Institute of Technology
 * @version $Id: PresenceTool.java 632 2005-07-14 21:22:50Z janderse@umich.edu $
 */
public class AssetIterator implements org.osid.repository.AssetIterator {
    java.util.Iterator mIterator = null;

	/**
	 * Store away what we will return piecemeal.
	 */
    protected AssetIterator(java.util.Vector vector)
	throws org.osid.repository.RepositoryException {
        mIterator = vector.iterator();
    }

	/**
	 * Return whether there is another Asset or not.
	 */
    public boolean hasNextAsset()
	throws org.osid.repository.RepositoryException {
		try {
			return mIterator.hasNext();
		} catch (Exception ex) {
			throw new org.osid.repository.RepositoryException(org.osid.OsidException.OPERATION_FAILED);
		}
    }

	/**
	 * Return the next Asset or throw an exception if there are no more Assets.
	 * The exception's message is: org.osid.shared.SharedException.NO_MORE_ITERATOR_ELEMENTS
	 */
    public org.osid.repository.Asset nextAsset()
	throws org.osid.repository.RepositoryException {
		try {
            return (org.osid.repository.Asset) mIterator.next();
        } catch (java.util.NoSuchElementException e) {
            throw new org.osid.repository.RepositoryException(org.osid.shared.SharedException.NO_MORE_ITERATOR_ELEMENTS);
        }
    }
}
