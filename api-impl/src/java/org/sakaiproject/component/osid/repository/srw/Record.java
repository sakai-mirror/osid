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

package org.sakaiproject.component.osid.repository.srw;

/**
 * @author Massachusetts Institute of Techbology, Sakai Software Development Team
 * @version
 */
public class Record
	implements org.osid.repository.Record
{
    private java.util.Vector partVector = new java.util.Vector();
    private org.osid.id.IdManager idManager = null;
    private org.osid.logging.WritableLog log = null;
    private org.osid.shared.Id recordStructureId = null;
    private String displayName = null;
    private org.osid.shared.Id id = null;

    public String getDisplayName()
    throws org.osid.repository.RepositoryException
    {
        return this.displayName;
    }

    public org.osid.shared.Id getId()
    throws org.osid.repository.RepositoryException
    {
        return this.id;
    }

    private void log(String entry)
    throws org.osid.repository.RepositoryException
    {
        if (log != null)
        {
            try
            {
                log.appendLog(entry);
            }
            catch (org.osid.logging.LoggingException lex) 
            {
                // swallow exception since logging is a best attempt to log an exception anyway
            }   
        }
    }

    protected Record(org.osid.shared.Id recordStructureId
                   , org.osid.id.IdManager idManager
                   , org.osid.logging.WritableLog log)
    throws org.osid.repository.RepositoryException
    {
        try
        {
            this.idManager = idManager;
            this.log = log;
            this.recordStructureId = recordStructureId;
            this.id = idManager.createId();
        }
        catch (Throwable t)
        {
            log(t.getMessage());
            throw new org.osid.repository.RepositoryException(org.osid.OsidException.OPERATION_FAILED);
        }
    }

    public org.osid.repository.Part createPart(org.osid.shared.Id partStructureId
                                             , java.io.Serializable value)
    throws org.osid.repository.RepositoryException
    {
        try
        {
            org.osid.repository.Part part = new Part(partStructureId,value,this.idManager,this.log);                    
            this.partVector.addElement(part);
            return part;
        }
        catch (Throwable t)
        {
            log(t.getMessage());
            throw new org.osid.repository.RepositoryException(org.osid.OsidException.OPERATION_FAILED);
        }
    }

    public void deletePart(org.osid.shared.Id partId)
    throws org.osid.repository.RepositoryException
    {
        try
        {
            for (int i=0, size = this.partVector.size(); i < size; i++)
            {
                org.osid.repository.Part part = (org.osid.repository.Part)this.partVector.elementAt(i);
                if (part.getId().isEqual(partId))
                {
                    this.partVector.removeElementAt(i);
                    return;
                }
            }
            throw new org.osid.repository.RepositoryException(org.osid.shared.SharedException.UNKNOWN_ID);
        }
        catch (Throwable t)
        {
            log(t.getMessage());
            throw new org.osid.repository.RepositoryException(org.osid.OsidException.OPERATION_FAILED);
        }
    }

    public void updateDisplayName(String displayName)
    throws org.osid.repository.RepositoryException
    {
        throw new org.osid.repository.RepositoryException(org.osid.OsidException.UNIMPLEMENTED);
    }

    public org.osid.repository.PartIterator getParts()
    throws org.osid.repository.RepositoryException
    {
        return new PartIterator(this.partVector);
    }

    public org.osid.repository.RecordStructure getRecordStructure()
    throws org.osid.repository.RepositoryException
    {
        try
        {
            if (this.recordStructureId.isEqual(RecordStructure.getInstance().getId()))
            {
                return new RecordStructure();
            }
            else if (this.recordStructureId.isEqual(DCRecordStructure.getInstance().getId()))
            {
                return new DCRecordStructure();
            }
            else if (this.recordStructureId.isEqual(VueRecordStructure.getInstance().getId()))
            {
                return new VueRecordStructure();
            }
            else
            {
                return null;
            }
        }
        catch (Throwable t)
        {
            log(t.getMessage());
            throw new org.osid.repository.RepositoryException(org.osid.OsidException.OPERATION_FAILED);
        }
    }

    public boolean isMultivalued()
    throws org.osid.repository.RepositoryException
    {
        return false;
    }
}
