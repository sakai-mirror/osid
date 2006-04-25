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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @inheritDoc
 */
public class WritableLog implements org.osid.logging.WritableLog
{
  private static final Log LOG = LogFactory.getLog(WritableLog.class);

  org.osid.shared.Type formatType = null;
  org.osid.shared.Type priorityType = null;
  private String logName = null;
  private org.osid.shared.Id id = null;

  public String getDisplayName() throws org.osid.logging.LoggingException
  {
    return this.logName;
  }

  public org.osid.shared.Id getId() throws org.osid.logging.LoggingException
  {
    return this.id;
  }

  public void initialize() throws org.osid.logging.LoggingException
  {
  }

  public WritableLog(org.osid.OsidContext context, java.util.Map configuration,
      String logName) throws org.osid.logging.LoggingException
  {
    if (logName == null)
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.NULL_ARGUMENT);
    }

    initialize();
    this.logName = logName;
  }

  public void appendLog(java.io.Serializable entryItem)
      throws org.osid.logging.LoggingException
  {

    try {
	if ( "trace".equalsIgnoreCase(logName) ) {
		if ( LOG.isTraceEnabled() ) LOG.trace(entryItem.toString());
	} else if ( "debug".equalsIgnoreCase(logName) ) {
		if ( LOG.isDebugEnabled() ) LOG.debug(entryItem.toString());
	} else if ( "info".equalsIgnoreCase(logName) ) {
		LOG.info(entryItem.toString());
	} else if ( "warn".equalsIgnoreCase(logName) ) {
		LOG.warn(entryItem.toString());
	} else if ( "error".equalsIgnoreCase(logName) ) {
		LOG.error(entryItem.toString());
	} else if ( "fatal".equalsIgnoreCase(logName) ) {
		LOG.fatal(entryItem.toString());
	} else {
		LOG.info(entryItem.toString());
	}

    } catch(Throwable t) {
	// Ignore - not much to do...
        System.out.println("Sakai OSID Log:"+t.toString());
        System.out.println(this+"Entry="+entryItem);
    }

  }

  public void appendLogWithTypes(java.io.Serializable entryItem,
      org.osid.shared.Type formatType, org.osid.shared.Type priorityType)
      throws org.osid.logging.LoggingException
  {
    if (formatType == null)
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.FORMAT_TYPE_NOT_SET);
    }
    if (priorityType == null)
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.PRIORITY_TYPE_NOT_SET);
    }
    if (entryItem == null)
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.NULL_ARGUMENT);
    }
    appendLog(entryItem);
  }

  public void assignPriorityType(org.osid.shared.Type priorityType)
      throws org.osid.logging.LoggingException
  {
    this.priorityType = priorityType;
  }

  public void assignFormatType(org.osid.shared.Type formatType)
      throws org.osid.logging.LoggingException
  {
    this.formatType = formatType;
  }
}
