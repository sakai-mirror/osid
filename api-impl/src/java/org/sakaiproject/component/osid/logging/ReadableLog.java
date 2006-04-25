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
public class ReadableLog implements org.osid.logging.ReadableLog
{
  org.osid.OsidContext context = null;
  java.util.Map configuration = null;
  java.io.BufferedReader file = null;
  private String fileExtension = null;
  private String displayName = null;
  private org.osid.shared.Id id = null;

  public String getDisplayName() throws org.osid.logging.LoggingException
  {
    return this.displayName;
  }

  public org.osid.shared.Id getId() throws org.osid.logging.LoggingException
  {
    return this.id;
  }

  public void initialize() throws org.osid.logging.LoggingException
  {
    if (configuration == null)
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.CONFIGURATION_ERROR);
    }
    fileExtension = (String) configuration.get("FileExtension");
    if (fileExtension == null)
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.CONFIGURATION_ERROR);
    }
  }

  public ReadableLog(org.osid.OsidContext context, java.util.Map configuration,
      String logName) throws org.osid.logging.LoggingException
  {
    if (logName == null)
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.NULL_ARGUMENT);
    }
    this.context = context;
    this.configuration = configuration;
    initialize();
    try
    {
      this.displayName = logName;
      openFile();
    }
    catch (org.osid.OsidException oex)
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.OPERATION_FAILED);
    }
  }

  public org.osid.logging.EntryIterator getEntries(
      org.osid.shared.Type formatType, org.osid.shared.Type priorityType)
      throws org.osid.logging.LoggingException
  {
    try
    {
      java.util.Vector v = new java.util.Vector();
      String line = "";
      openFile();

      while ((line = file.readLine()) != null)
      {
        org.osid.logging.Entry entry = new Entry(line);
        if ((formatType.isEqual(entry.getFormatType()))
            && (priorityType.isEqual(entry.getPriorityType())))
        {
          v.addElement(entry);
        }
      }
      file.close();
      return (new EntryIterator(v));
    }
    catch (Exception oex)
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.OPERATION_FAILED);
    }
  }

  private void openFile() throws org.osid.logging.LoggingException
  {
    try
    {
      try
      {
        file = new java.io.BufferedReader(new java.io.FileReader(
            this.displayName + fileExtension));
      }
      catch (java.io.FileNotFoundException fnfex)
      {
        throw new org.osid.logging.LoggingException(
            org.osid.logging.LoggingException.UNKNOWN_NAME);
      }
    }
    catch (org.osid.OsidException oex)
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.OPERATION_FAILED);
    }
  }
}
