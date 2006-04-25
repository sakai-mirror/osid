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
public class Entry implements org.osid.logging.Entry
{
  private java.io.Serializable item = null;

  public java.io.Serializable getItem()
      throws org.osid.logging.LoggingException
  {
    return this.item;
  }

  public Entry(String line) throws org.osid.logging.LoggingException
  {
    this.item = line;
  }

  public long getTimestamp() throws org.osid.logging.LoggingException
  {
    return 0;
  }

  public org.osid.shared.Type getFormatType()
      throws org.osid.logging.LoggingException
  {
      return new Type("sakaiproject.org","log","plain","Plain Logging Type");
  }

  public org.osid.shared.Type getPriorityType()
      throws org.osid.logging.LoggingException
  {
      return new Type("sakaiproject.org","log","priority","Priority Logging Type");
  }
}
