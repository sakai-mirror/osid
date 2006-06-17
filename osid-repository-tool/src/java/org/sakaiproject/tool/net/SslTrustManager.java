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

package org.sakaiproject.tool.net;


/**
 * Create a trust manager that always accepts the certificate chains.
 * Used for testing only.  See <code>Search.java</code> for use.
 *<p>
 * Accessing an HTTPS URL using the URL class results in an
 * exception if the server's certificate chain has not previously been
 * installed in the truststore. For testing, override the default trust
 * manager with one that trusts all certificates.
 */
public class SslTrustManager implements javax.net.ssl.X509TrustManager {

  /**
   * Return an array of certificate authority certificates
   * @return Certificate array (always null in this dummy version)
   */
  public java.security.cert.X509Certificate[] getAcceptedIssuers() {
    return null;
  }

  /**
   * Indicate trust for this client (don't throw an exception)
   */
  public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
                                 String authType) {
  }

  /**
   * Indicate trust for this server (don't throw an exception)
   */
  public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
                                 String authType) {
  }
}
