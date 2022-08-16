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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.mail.Part;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.dom4j.io.SAXReader;
import org.junit.*;
import org.subethamail.smtp.AuthenticationHandler;
import org.subethamail.smtp.AuthenticationHandlerFactory;
import org.subethamail.smtp.auth.LoginAuthenticationHandler;
import org.subethamail.smtp.auth.LoginFailedException;
import org.subethamail.smtp.auth.PlainAuthenticationHandler;
import org.subethamail.smtp.auth.PluginAuthenticationHandler;
import org.subethamail.smtp.auth.UsernamePasswordValidator;
import org.subethamail.smtp.server.MessageListenerAdapter;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.html.HTMLLayout;
import ch.qos.logback.classic.html.XHTMLEntityResolver;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.util.StatusPrinter;

public class SMTPAppender_SubethaSMTPTest {
  static final String TEST_SUBJECT = "test subject";
  static final String HEADER = "HEADER\n";
  static final String FOOTER = "FOOTER\n";

  static int DIFF = 1024 + new Random().nextInt(30000);
  static Wiser WISER;

  SMTPAppender smtpAppender;
  LoggerContext loggerContext = new LoggerContext();

  int numberOfOldMessages;


  @BeforeClass
  static public void beforeClass() {
    WISER = new Wiser();
    WISER.setPort(DIFF);
    WISER.start();
  }

  @Before
  public void setUp() throws Exception {
    numberOfOldMessages = WISER.getMessages().size();
    buildSMTPAppender();
  }

  void buildSMTPAppender() throws Exception {
    smtpAppender = new SMTPAppender();
    smtpAppender.setContext(loggerContext);
    smtpAppender.setName("smtp");
    smtpAppender.setFrom("user@host.dom");
    smtpAppender.setSMTPHost("localhost");
    smtpAppender.setSMTPPort(DIFF);
    smtpAppender.setSubject(TEST_SUBJECT);
    smtpAppender.addTo("noreply@qos.ch");
  }

  private Layout<ILoggingEvent> buildPatternLayout(LoggerContext lc) {
    PatternLayout layout = new PatternLayout();
    layout.setContext(lc);
    layout.setFileHeader(HEADER);
    layout.setPattern("%-4relative [%thread] %-5level %logger %class - %msg%n");
    layout.setFileFooter(FOOTER);
    layout.start();
    return layout;
  }

  private Layout<ILoggingEvent> buildHTMLLayout(LoggerContext lc) {
    HTMLLayout layout = new HTMLLayout();
    layout.setContext(lc);
    // layout.setFileHeader(HEADER);
    layout.setPattern("%level%class%msg");
    // layout.setFileFooter(FOOTER);
    layout.start();
    return layout;
  }


  private static String getWholeMessage(Part msg) {
    try {
      ByteArrayOutputStream bodyOut = new ByteArrayOutputStream();
      msg.writeTo(bodyOut);
      return bodyOut.toString("US-ASCII").trim();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  void waitUntilEmailIsSent() throws Exception {
    System.out.println("About to wait for sending thread to finish");
    loggerContext.getExecutorService().shutdown();
    loggerContext.getExecutorService().awaitTermination(3000, TimeUnit.MILLISECONDS);
  }

  private static String getBody(Part msg) {
    String all = getWholeMessage(msg);
    int i = all.indexOf("\r\n\r\n");
    return all.substring(i + 4, all.length());
  }

  @Test
  public void smoke() throws Exception {
    smtpAppender.setLayout(buildPatternLayout(loggerContext));
    smtpAppender.start();
    Logger logger = loggerContext.getLogger("test");
    logger.addAppender(smtpAppender);
    logger.debug("hello");
    logger.error("en error", new Exception("an exception"));

    waitUntilEmailIsSent();
    System.out.println("*** " + ((ThreadPoolExecutor) loggerContext.getExecutorService()).getCompletedTaskCount());
    List<WiserMessage> wiserMsgList = WISER.getMessages();

    assertNotNull(wiserMsgList);
    assertEquals(numberOfOldMessages+1, wiserMsgList.size());
    WiserMessage wm = wiserMsgList.get(numberOfOldMessages);
    // http://jira.qos.ch/browse/LBCLASSIC-67
    MimeMessage mm = wm.getMimeMessage();
    assertEquals(TEST_SUBJECT, mm.getSubject());

    MimeMultipart mp = (MimeMultipart) mm.getContent();
    String body = getBody(mp.getBodyPart(0));
    System.out.println("[" + body);
    assertTrue(body.startsWith(HEADER.trim()));
    assertTrue(body.endsWith(FOOTER.trim()));
  }


  public class TrivialAuthHandlerFactory implements AuthenticationHandlerFactory {
    public AuthenticationHandler create() {
      PluginAuthenticationHandler ret = new PluginAuthenticationHandler();
      UsernamePasswordValidator validator = new UsernamePasswordValidator() {
        public void login(String username, String password)
                throws LoginFailedException {
          if (!username.equals(password)) {
            throw new LoginFailedException("username=" + username + ", password=" + password);
          }
        }
      };
      ret.addPlugin(new PlainAuthenticationHandler(validator));
      ret.addPlugin(new LoginAuthenticationHandler(validator));
      return ret;
    }
  }

}
