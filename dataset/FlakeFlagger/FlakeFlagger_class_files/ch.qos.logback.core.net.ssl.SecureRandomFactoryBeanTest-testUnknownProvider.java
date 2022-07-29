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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.junit.Test;

import ch.qos.logback.core.net.ssl.SSL;
import ch.qos.logback.core.net.ssl.SecureRandomFactoryBean;


/**
 * Unit tests for {@link SecureRandomFactoryBean}.
 *
 * @author Carl Harris
 */
public class SecureRandomFactoryBeanTest {

  private SecureRandomFactoryBean factoryBean = new SecureRandomFactoryBean();
  
  @Test public void testUnknownProvider() throws Exception{factoryBean.setProvider(SSLTestConstants.FAKE_PROVIDER_NAME);try {factoryBean.createSecureRandom();fail("expected NoSuchProviderException");} catch (NoSuchProviderException ex){assertTrue(ex.getMessage().contains(SSLTestConstants.FAKE_PROVIDER_NAME));}}

}
