/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.core.net.ssl;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.net.ssl.mock.MockSSLConfigurable;


/**
 * Unit tests for {@link SSLParametersConfiguration}.
 *
 * @author Carl Harris
 */
public class SSLParametersConfigurationTest {

  private MockSSLConfigurable configurable = new MockSSLConfigurable();
  
  private SSLParametersConfiguration configuration = 
      new SSLParametersConfiguration();
 
  @Before
  public void setUp() throws Exception {
    configuration.setContext(new ContextBase());
  }
  
  @Test public void testSetWantClientAuth() throws Exception{configuration.setWantClientAuth(true);configuration.configure(configurable);assertTrue(configurable.isWantClientAuth());}

}
