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
package ch.qos.logback.classic.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.Serializable;
import java.util.Properties;

import javax.jms.ObjectMessage;
import javax.naming.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.net.mock.MockTopic;
import ch.qos.logback.classic.net.mock.MockTopicConnectionFactory;
import ch.qos.logback.classic.net.mock.MockTopicPublisher;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.util.MockInitialContext;
import ch.qos.logback.classic.util.MockInitialContextFactory;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.spi.PreSerializationTransformer;

public class JMSTopicAppenderTest  {

  ch.qos.logback.core.Context context;
  JMSTopicAppender appender;
  PreSerializationTransformer<ILoggingEvent> pst = new LoggingEventPreSerializationTransformer();
  

  @Before
  public void setUp() throws Exception {
    context = new ContextBase();
    appender = new JMSTopicAppender();
    appender.setContext(context);
    appender.setName("jmsTopic");
    appender.tcfBindingName = "topicCnxFactory";
    appender.topicBindingName = "testTopic";
    appender.setProviderURL("url");
    appender.setInitialContextFactoryName(MockInitialContextFactory.class.getName());
    
    MockInitialContext mic = MockInitialContextFactory.getContext();
    mic.map.put(appender.tcfBindingName, new MockTopicConnectionFactory());
    mic.map.put(appender.topicBindingName, new MockTopic(appender.topicBindingName));

  }


  @Test public void testBuildEnvPropertiesWithNullCredentials(){appender.setInitialContextFactoryName("icfn");appender.setProviderURL("url");appender.setURLPkgPrefixes("pkgPref");appender.setSecurityPrincipalName("user");appender.setSecurityCredentials(null);Properties props=appender.buildEnvProperties();assertEquals(4,props.size());assertEquals(appender.getInitialContextFactoryName(),props.getProperty(Context.INITIAL_CONTEXT_FACTORY));assertEquals(appender.getProviderURL(),props.getProperty(Context.PROVIDER_URL));assertEquals(appender.getURLPkgPrefixes(),props.getProperty(Context.URL_PKG_PREFIXES));assertEquals(appender.getSecurityPrincipalName(),props.getProperty(Context.SECURITY_PRINCIPAL));assertEquals(null,props.getProperty(Context.SECURITY_CREDENTIALS));assertEquals(1,context.getStatusManager().getCount());}
  
  private ILoggingEvent createLoggingEvent() {
    LoggingEvent le = new LoggingEvent();
    le.setLevel(Level.DEBUG);
    le.setMessage("test message");
    le.setTimeStamp(System.currentTimeMillis());
    le.setThreadName(Thread.currentThread().getName());
    return le;
  }
}
