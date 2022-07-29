/*
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
import com.google.inject.persist.PersistService;
import org.apache.ambari.server.AmbariException;
import org.apache.ambari.server.actionmanager.ActionDBAccessorImpl;
import org.apache.ambari.server.actionmanager.ActionManager;
import org.apache.ambari.server.agent.ActionQueue;
import org.apache.ambari.server.orm.GuiceJpaInitializer;
import org.apache.ambari.server.orm.InMemoryDefaultTestModule;
import org.apache.ambari.server.orm.dao.RoleDAO;
import org.apache.ambari.server.orm.dao.UserDAO;
import org.apache.ambari.server.orm.entities.UserEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.Assert.*;

public class TestUsers {
  private Injector injector;

  @Inject
  protected Users users;
  @Inject
  protected UserDAO userDAO;
  @Inject
  protected RoleDAO roleDAO;
  @Inject
  protected PasswordEncoder passwordEncoder;

  @Before
  public void setup() throws AmbariException {
    injector = Guice.createInjector(new InMemoryDefaultTestModule());
    injector.getInstance(GuiceJpaInitializer.class);
    injector.injectMembers(this);
    users.createDefaultRoles();
  }

  @Test(expected=AmbariException.class) public void testModifyPassword() throws Exception{users.createUser("user","user");UserEntity userEntity=userDAO.findLocalUserByName("user");assertNotSame("user",userEntity.getUserPassword());assertTrue(passwordEncoder.matches("user",userEntity.getUserPassword()));users.modifyPassword("user","user","resu");assertNotSame(userEntity.getUserPassword(),userDAO.findLocalUserByName("user").getUserPassword());users.modifyPassword("user","error","new");fail("Exception was not thrown");}
}
