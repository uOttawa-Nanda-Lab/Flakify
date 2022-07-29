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

import java.security.KeyStore;

import org.junit.Test;

import ch.qos.logback.core.net.ssl.KeyStoreFactoryBean;
import ch.qos.logback.core.net.ssl.SSL;


/**
 * Unit tests for {@link KeyStoreFactoryBean}.
 *
 * @author Carl Harris
 */
public class KeyStoreFactoryBeanTest {

  private KeyStoreFactoryBean factoryBean = new KeyStoreFactoryBean();
  
  @Test public void testPKCS12Type() throws Exception{factoryBean.setLocation(SSLTestConstants.KEYSTORE_PKCS12_RESOURCE);factoryBean.setType(SSLTestConstants.PKCS12_TYPE);assertNotNull(factoryBean.createKeyStore());}

}
