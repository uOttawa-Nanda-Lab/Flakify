/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ambari.server.security.authorization;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.ambari.server.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LdapServerPropertiesTest {

  private final Injector injector;

  private static final String INCORRECT_URL_LIST = "Incorrect LDAP URL list created";
  private static final String INCORRECT_USER_SEARCH_FILTER = "Incorrect search filter";

  protected LdapServerProperties ldapServerProperties;

  @Inject
  Configuration configuration;

  @Before
  public void setUp() throws Exception {
    ldapServerProperties =  new LdapServerProperties();
    ldapServerProperties.setAnonymousBind(true);
    ldapServerProperties.setBaseDN("dc=ambari,dc=apache,dc=org");
    ldapServerProperties.setManagerDn("uid=manager," + ldapServerProperties.getBaseDN());
    ldapServerProperties.setManagerPassword("password");
    ldapServerProperties.setUseSsl(false);
    ldapServerProperties.setPrimaryUrl("1.2.3.4:389");
    ldapServerProperties.setUsernameAttribute("uid");
  }

  @Test public void testGetLdapUrls() throws Exception{List<String> urls=ldapServerProperties.getLdapUrls();assertEquals(INCORRECT_URL_LIST,1,urls.size());assertEquals(INCORRECT_URL_LIST,"ldap://1.2.3.4:389",urls.get(0));ldapServerProperties.setSecondaryUrl("4.3.2.1:1234");urls=ldapServerProperties.getLdapUrls();assertEquals(INCORRECT_URL_LIST,2,urls.size());assertEquals(INCORRECT_URL_LIST,"ldap://4.3.2.1:1234",urls.get(1));ldapServerProperties.setUseSsl(true);urls=ldapServerProperties.getLdapUrls();assertEquals(INCORRECT_URL_LIST,"ldaps://1.2.3.4:389",urls.get(0));assertEquals(INCORRECT_URL_LIST,"ldaps://4.3.2.1:1234",urls.get(1));}
}
