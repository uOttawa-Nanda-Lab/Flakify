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

import ch.qos.logback.classic.ClassicTestConstants;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.html.HTMLLayout;
import ch.qos.logback.classic.html.XHTMLEntityResolver;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.testUtil.EnvUtilForTests;
import ch.qos.logback.core.testUtil.RandomUtil;
import ch.qos.logback.core.util.CoreTestConstants;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class SMTPAppender_GreenTest {

  static final String HEADER = "HEADER\n";
  static final String FOOTER = "FOOTER\n";
  static final String DEFAULT_PATTERN = "%-4relative %mdc [%thread] %-5level %class - %msg%n";

  static final boolean SYNCHRONOUS = false;
  static final boolean ASYNCHRONOUS = true;

  int port = RandomUtil.getRandomServerPort();
  // GreenMail cannot be static. As a shared server induces race conditions
  GreenMail greenMailServer;

  SMTPAppender smtpAppender;
  LoggerContext loggerContext = new LoggerContext();
  Logger logger = loggerContext.getLogger(this.getClass());


  @Before
  public void setUp() throws Exception {

    OnConsoleStatusListener.addNewInstanceToContext(loggerContext);
    MDC.clear();
    ServerSetup serverSetup = new ServerSetup(port, "localhost",
            ServerSetup.PROTOCOL_SMTP);
    greenMailServer = new GreenMail(serverSetup);
    greenMailServer.start();
    // give the server a head start
    if (EnvUtilForTests.isRunningOnSlowJenkins()) {
      Thread.currentThread().sleep(2000);
    } else {
      Thread.currentThread().sleep(50);
    }
  }

  void buildSMTPAppender(String subject, boolean synchronicity) throws Exception {
    smtpAppender = new SMTPAppender();
    smtpAppender.setContext(loggerContext);
    smtpAppender.setName("smtp");
    smtpAppender.setFrom("user@host.dom");
    smtpAppender.setSMTPHost("localhost");
    smtpAppender.setSMTPPort(port);
    smtpAppender.setSubject(subject);
    smtpAppender.addTo("nospam@qos.ch");
    smtpAppender.setAsynchronousSending(synchronicity);
  }

  private Layout<ILoggingEvent> buildPatternLayout(String pattern) {
    PatternLayout layout = new PatternLayout();
    layout.setContext(loggerContext);
    layout.setFileHeader(HEADER);
    layout.setOutputPatternAsHeader(false);
    layout.setPattern(pattern);
    layout.setFileFooter(FOOTER);
    layout.start();
    return layout;
  }

  private Layout<ILoggingEvent> buildHTMLLayout() {
    HTMLLayout layout = new HTMLLayout();
    layout.setContext(loggerContext);
    layout.setPattern("%level%class%msg");
    layout.start();
    return layout;
  }

  private void waitForServerToReceiveEmails(int emailCount) throws InterruptedException {
    greenMailServer.waitForIncomingEmail(5000, emailCount);
  }

  private MimeMultipart verifyAndExtractMimeMultipart(String subject) throws MessagingException,
          IOException, InterruptedException {
    int oldCount = 0;
    int expectedEmailCount = 1;
    // wait for the server to receive the messages
    waitForServerToReceiveEmails(expectedEmailCount);
    MimeMessage[] mma = greenMailServer.getReceivedMessages();
    assertNotNull(mma);
    assertEquals(expectedEmailCount, mma.length);
    MimeMessage mm = mma[oldCount];
    // http://jira.qos.ch/browse/LBCLASSIC-67
    assertEquals(subject, mm.getSubject());
    return (MimeMultipart) mm.getContent();
  }

  void waitUntilEmailIsSent() throws InterruptedException {
    loggerContext.getExecutorService().shutdown();
    loggerContext.getExecutorService().awaitTermination(1000, TimeUnit.MILLISECONDS);
  }

  private byte[] getAsByteArray(InputStream inputStream) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    byte[] buffer = new byte[1024];
    int n = -1;
    while ((n = inputStream.read(buffer)) != -1) {
      baos.write(buffer, 0, n);
    }
    return baos.toByteArray();
  }

  private void configure(String file) throws JoranException {
    JoranConfigurator jc = new JoranConfigurator();
    jc.setContext(loggerContext);
    loggerContext.putProperty("port", "" + port);
    jc.doConfigure(file);
  }

  @Test public void testMultipleTo() throws Exception{buildSMTPAppender("testMultipleTo",SYNCHRONOUS);smtpAppender.setLayout(buildPatternLayout(DEFAULT_PATTERN));smtpAppender.addTo("Test <test@example.com>, other-test@example.com");smtpAppender.start();logger.addAppender(smtpAppender);logger.debug("testMultipleTo hello");logger.error("testMultipleTo en error",new Exception("an exception"));Thread.yield();int expectedEmailCount=3;waitForServerToReceiveEmails(expectedEmailCount);MimeMessage[] mma=greenMailServer.getReceivedMessages();assertNotNull(mma);assertEquals(expectedEmailCount,mma.length);}
}
