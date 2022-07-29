package com.walmartlabs.ollie.config;

/*-
 * *****
 * Ollie
 * -----
 * Copyright (C) 2018 Takari
 * -----
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =====
 */

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ConfigurationProcessorTest {

  @Rule 
  public ExpectedException exception = ExpectedException.none();

  private String basedir;

  @Before
  public void setUp() {
    basedir = System.getProperty("basedir", new File("").getAbsolutePath());
  }

  @Test
  public void validateConfigurationProcessor() {
    ConfigurationProcessor processor = new ConfigurationProcessor("gatekeeper");
    com.typesafe.config.Config config = processor.process();
    assertEquals("dev-settle-token", config.getString("approver.settle.token"));    
    assertEquals("dev-jira-username", config.getString("jira.username"));
    assertEquals("dev-jira-password", config.getString("jira.password"));    
    assertEquals("caring", config.getString("sharing"));
    assertEquals(123, config.getInt("y"));
  }  

  @Test
  public void validateConfigurationProcessorWhereConfigurationFileIsOverridenWithASystemProperty() {
    System.setProperty(ConfigurationProcessor.CONFIG_FILE, "src/test/resources/different.conf");
    ConfigurationProcessor processor = new ConfigurationProcessor("gatekeeper");
    com.typesafe.config.Config config = processor.process();
    assertEquals("for different folks", config.getString("different-strokes"));    
    System.clearProperty(ConfigurationProcessor.CONFIG_FILE);
  }    
  
  @Test
  public void validateConfigurationProcessorUsingOverridesFile() {
    File overridesFile = new File(basedir, "src/test/resources/overrides.conf");
    ConfigurationProcessor processor = new ConfigurationProcessor("gatekeeper", overridesFile);
    com.typesafe.config.Config config = processor.process();
    assertEquals("http://adapter.server.com:8080/adapter/rest/dj/simple/approvals", config.getString("approver.settle.url"));
    assertEquals("https://jira.server.com", config.getString("jira.server"));
    //
    // These come from the local overrides file because we don't want this sensitive information checked into source control
    //
    assertEquals("settletoken", config.getString("approver.settle.token"));    
    assertEquals("username", config.getString("jira.username"));
    assertEquals("password", config.getString("jira.password"));    
  }

  @Test
  public void validateConfigurationProcessorUsingNonExistentOverrides() {
    File overridesFile = new File(basedir, "src/test/resources/overrides-non-existent.conf");
    ConfigurationProcessor processor = new ConfigurationProcessor("gatekeeper", overridesFile);
    exception.expect(RuntimeException.class);
    exception.expectMessage(containsString("The specified overrides configuration doesn't exist:"));
    processor.process();
  }    

  @Test
  public void validateConfigurationProcessorUsingOverridesFileReportsWrongStructureNoApplication() {
    File overridesFile = new File(basedir, "src/test/resources/overrides-wrong-structure-no-application.conf");
    ConfigurationProcessor processor = new ConfigurationProcessor("gatekeeper", overridesFile);
    exception.expect(RuntimeException.class);
    exception.expectMessage(containsString("The specified application 'gatekeeper' is not present"));
    processor.process();
  }    

  @Test
  public void validateConfigurationProcessorUsingOverridesFileReportsWrongStructureNoEnvironment() {
    File overridesFile = new File(basedir, "src/test/resources/overrides-wrong-structure-no-environment.conf");
    ConfigurationProcessor processor = new ConfigurationProcessor("gatekeeper", overridesFile);
    exception.expect(RuntimeException.class);
    exception.expectMessage(containsString("The specified environment 'development' is not present"));
    processor.process();
  }

  @Test
  public void validateConfigurationProcessorUsingWithSecrets() {
    File secretsProperties = new File(basedir, "src/test/resources/secrets.properties");
    ConfigurationProcessor processor = new ConfigurationProcessor("secrets", Environment.DEVELOPMENT, null, secretsProperties);
    com.typesafe.config.Config config = processor.process();
    assertEquals("secretValue0", config.getString("secretKey0"));
    assertEquals("super-secret", config.getString("jira.password"));
  }
}

