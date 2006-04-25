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
public class VueRecordStructure
	implements org.osid.repository.RecordStructure
{
    private String idString = "d5e9eea5301080006d751920168000100";
    private String displayName = "VUE Integration Record";
    private String description = "Holds object properties for display in Tufts Visual Understanding Environment (VUE)";
    private String format = "";
    private String schema = "";
    private org.osid.shared.Type type = new Type("tufts.edu","recordStructure","vue");
    private boolean repeatable = false;
    private org.osid.shared.Id id = null;
	private static VueRecordStructure vueRecordStructure = new VueRecordStructure();
	
	protected static VueRecordStructure getInstance()
	{
		return vueRecordStructure;
	}
	
    public String getDisplayName()
    throws org.osid.repository.RepositoryException
    {
        return this.displayName;
    }

    public String getDescription()
    throws org.osid.repository.RepositoryException
    {
        return this.description;
    }

    public String getFormat()
    throws org.osid.repository.RepositoryException
    {
        return this.format;
    }

    public String getSchema()
    throws org.osid.repository.RepositoryException
    {
        return this.schema;
    }

    public org.osid.shared.Type getType()
    throws org.osid.repository.RepositoryException
    {
        return this.type;
    }

    public boolean isRepeatable()
    throws org.osid.repository.RepositoryException
    {
        return this.repeatable;
    }

    public org.osid.shared.Id getId()
    throws org.osid.repository.RepositoryException
    {
        return this.id;
    }

    public VueRecordStructure()
    {
        try
        {
            this.id = Managers.getIdManager().getId(this.idString);
        }
        catch (Throwable t)
        {
        }
    }

    public void updateDisplayName(String displayName)
    throws org.osid.repository.RepositoryException
    {
        throw new org.osid.repository.RepositoryException(org.osid.OsidException.UNIMPLEMENTED);
    }

    public org.osid.repository.PartStructureIterator getPartStructures()
    throws org.osid.repository.RepositoryException
    {
        java.util.Vector results = new java.util.Vector();
        try
        {
            results.addElement(CreatorPartStructure.getInstance());
            results.addElement(SubjectPartStructure.getInstance());
            results.addElement(PublisherPartStructure.getInstance());
            results.addElement(ContributorPartStructure.getInstance());
            results.addElement(DatePartStructure.getInstance());
            results.addElement(TypePartStructure.getInstance());
            results.addElement(FormatPartStructure.getInstance());
            results.addElement(SourcePartStructure.getInstance());
            results.addElement(LanguagePartStructure.getInstance());
            results.addElement(RelationPartStructure.getInstance());
            results.addElement(CoveragePartStructure.getInstance());
            results.addElement(RightsPartStructure.getInstance());
        }
        catch (Throwable t)
        {
            throw new org.osid.repository.RepositoryException(org.osid.repository.RepositoryException.OPERATION_FAILED);
        }
        return new PartStructureIterator(results);
    }

    public boolean validateRecord(org.osid.repository.Record record)
    throws org.osid.repository.RepositoryException
    {
        return true;
    }
}
