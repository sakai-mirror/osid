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

package org.sakaiproject.component.osid.logging;

/**
 * 
 * @inheritDoc
 *
 */
public class StringIterator implements org.osid.shared.StringIterator
{
  private java.util.Vector vector = new java.util.Vector();
  private int i = 0;

  public StringIterator(java.util.Vector vector)
      throws org.osid.shared.SharedException
  {
    this.vector = vector;
  }

  public String toString() 
  {
    String retval = super.toString();
    for ( int j = 0; j < vector.size(); j++ )
    {
	retval = retval + " " + vector.elementAt(j);
    }
    return retval;
  }

  public boolean hasNextString() throws org.osid.shared.SharedException
  {
    return i < vector.size();
  }

  public String nextString() throws org.osid.shared.SharedException
  {
    if (i < vector.size())
    {
      return (String) vector.elementAt(i++);
    }
    else
    {
      throw new org.osid.shared.SharedException(
          org.osid.shared.SharedException.NO_MORE_ITERATOR_ELEMENTS);
    }
  }
}
