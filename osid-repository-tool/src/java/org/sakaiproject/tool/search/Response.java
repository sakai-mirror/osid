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

package org.sakaiproject.tool.search;

import org.sakaiproject.component.cover.ComponentManager;

/**
 *  SRW
 */
public class Response 
	extends SearchResultBase 
{
	private String criteria = null;
  /**
   * Constructor
   */
    public Response() 
    {
        super();
    }

    public void initialize(QueryBase query) 
    {
        try
        {			
			this.criteria = query.getSearchString();
        }
        catch (Exception ex) {}
    }

  /**
   * Parse the response
   */
    public void doParse(String database) 
    {
        try
        {
			org.osid.shared.Type thumbnailType = new Type("mit.edu","partStructure","thumbnail");
			org.osid.shared.Type urlType = new Type("mit.edu","partStructure","URL");

			ComponentManager.getInstance();		
			Object o = ComponentManager.get("org.osid.repository.RepositoryManager");
			if (o != null) {
				// initialize the repository manager and get its repositories
				org.osid.repository.RepositoryManager repositoryManager = (org.osid.repository.RepositoryManager)o;
				repositoryManager.assignOsidContext(new org.osid.OsidContext());
				repositoryManager.assignConfiguration(new java.util.Properties());
				org.osid.repository.RepositoryIterator repositoryIterator = repositoryManager.getRepositories();
				while (repositoryIterator.hasNextRepository()) {
					
					// only search the Repository (database) asked for
					org.osid.repository.Repository nextRepository = repositoryIterator.nextRepository();
					//System.out.println("testing " + nextRepository.getDisplayName() + " vs " + database);
					if (nextRepository.getDisplayName().equals(database)) {
						org.osid.repository.AssetIterator assetIterator = nextRepository.getAssetsBySearch(this.criteria,
																										   new Type("mit.edu","search","keyword"),
																										   new SharedProperties());
						System.out.println("Query complete");
						while (assetIterator.hasNextAsset())
						{
							org.osid.repository.Asset asset = assetIterator.nextAsset();
							MatchItem item = new MatchItem();
							String displayName = asset.getDisplayName();
							String description = asset.getDescription();
							String content = null;
							try {
								content = (String)asset.getContent();
								item.setPreviewUrl(content);
								item.setPersistentUrl(content);
							} catch (Throwable th) {
								// ignore
							}
							
							// Look for a thumbnail or url part and show any.  We only want one and their might be more than one in
							// different records.  To minimize what we know about the data source, we don't want to limit which record types we search.
							boolean foundThumbnail = false;
							boolean foundURL = false;
							org.osid.repository.RecordIterator recordIterator = asset.getRecords();
							while (recordIterator.hasNextRecord()) {
								org.osid.repository.PartIterator partIterator = recordIterator.nextRecord().getParts();
								while (partIterator.hasNextPart()) {
									org.osid.repository.Part part = partIterator.nextPart();
									org.osid.shared.Type partStructureType = part.getPartStructure().getType();
									if ( (!foundThumbnail) && (partStructureType.isEqual(thumbnailType)) ) {
										item.setPreviewUrl((String)part.getValue());
										item.setPreviewImage((String)part.getValue());
										foundThumbnail = true;
									} else if ( (!foundURL) && (partStructureType.isEqual(urlType)) ) {
										item.setPersistentUrl((String)part.getValue());
										foundURL = true;
									}
								}
							}
							item.setPreviewText(displayName);
							item.setDescription(description);
							item.setPersistentText(displayName);
							addItem(item);
						}
					}					
				}
            }
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
    }
}
