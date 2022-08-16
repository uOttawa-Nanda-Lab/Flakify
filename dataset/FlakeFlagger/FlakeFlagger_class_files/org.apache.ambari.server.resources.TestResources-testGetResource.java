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

package org.apache.ambari.server.resources;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.ambari.server.configuration.Configuration;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class TestResources extends TestCase {
	
  private static ResourceManager resMan;
  private static final String RESOURCE_FILE_NAME = "resources.ext";
  private static final String RESOURCE_FILE_CONTENT = "CONTENT";
  Injector injector;
  private TemporaryFolder tempFolder = new TemporaryFolder();
  private File resourceFile;
  
  protected Properties buildTestProperties() {
	   
	Properties properties = new Properties();
    try {
		tempFolder.create();
		
		properties.setProperty(Configuration.SRVR_KSTR_DIR_KEY, tempFolder.getRoot().getAbsolutePath());
		properties.setProperty(Configuration.RESOURCES_DIR_KEY, tempFolder.getRoot().getAbsolutePath());

	    resourceFile = tempFolder.newFile(RESOURCE_FILE_NAME);
	    FileUtils.writeStringToFile(resourceFile, RESOURCE_FILE_CONTENT);
	} catch (IOException e) {
		e.printStackTrace();
	}
    return properties;
  }
  
  protected Constructor<Configuration> getConfigurationConstructor() {
    try {
	  return Configuration.class.getConstructor(Properties.class);
    } catch (NoSuchMethodException e) {
	  throw new RuntimeException("Expected constructor not found in Configuration.java", e);
	}
  }

  private class ResourceModule extends AbstractModule {
  @Override
    protected void configure() {
      bind(Properties.class).toInstance(buildTestProperties());
      bind(Configuration.class).toConstructor(getConfigurationConstructor());
	  requestStaticInjection(TestResources.class);
	}
  }

  @Inject
  static void init(ResourceManager instance) {
    resMan = instance;
  }

  @Before
  public void setUp() throws IOException {
    injector = Guice.createInjector(new ResourceModule());
    resMan = injector.getInstance(ResourceManager.class);
  }
	
  @Test public void testGetResource() throws Exception{File resFile=resMan.getResource(resourceFile.getName());assertTrue(resFile.exists());String resContent=FileUtils.readFileToString(resFile);assertEquals(resContent,RESOURCE_FILE_CONTENT);}

}
