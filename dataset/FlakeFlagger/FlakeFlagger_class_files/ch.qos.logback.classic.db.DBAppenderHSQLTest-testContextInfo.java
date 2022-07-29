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
package ch.qos.logback.classic.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.apache.log4j.MDC;
import org.junit.*;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.db.DriverManagerConnectionSource;
import ch.qos.logback.core.testUtil.RandomUtil;
import ch.qos.logback.core.util.StatusPrinter;

public class DBAppenderHSQLTest  {

  LoggerContext lc;
  Logger logger;
  DBAppender appender;
  DriverManagerConnectionSource connectionSource;

  static DBAppenderHSQLTestFixture DB_APPENDER_HSQL_TEST_FIXTURE;
  int diff = RandomUtil.getPositiveInt();
  int existingRowCount;
  Statement stmt;

  @BeforeClass
  public static void beforeClass() throws SQLException {
    DB_APPENDER_HSQL_TEST_FIXTURE = new DBAppenderHSQLTestFixture();
    DB_APPENDER_HSQL_TEST_FIXTURE.setUp();
  }

  @Before
  public void setUp() throws SQLException {
    lc = new LoggerContext();
    lc.setName("default");
    logger = lc.getLogger("root");
    appender = new DBAppender();
    appender.setName("DB");
    appender.setContext(lc);
    connectionSource = new DriverManagerConnectionSource();
    connectionSource.setContext(lc);
    connectionSource.setDriverClass(DBAppenderHSQLTestFixture.HSQLDB_DRIVER_CLASS);
    connectionSource.setUrl(DB_APPENDER_HSQL_TEST_FIXTURE.url);
    connectionSource.setUser(DB_APPENDER_HSQL_TEST_FIXTURE.user);
    connectionSource.setPassword(DB_APPENDER_HSQL_TEST_FIXTURE.password);
    connectionSource.start();
    appender.setConnectionSource(connectionSource);
    appender.start();

    stmt = connectionSource.getConnection().createStatement();
    existingRowCount = existingRowCount(stmt);

  }
  


  int existingRowCount(Statement stmt) throws SQLException {
    ResultSet rs = stmt.executeQuery("SELECT count(*) FROM logging_event");
    int result = -1;
    if (rs.next()) {
      result = rs.getInt(1);
    }
    rs.close();
    return result;
  }

  @Test
  public void testContextInfo() throws SQLException {
    lc.putProperty("testKey1", "testValue1");
    MDC.put("k"+diff, "v"+diff);
    ILoggingEvent event = createLoggingEvent();
    
    appender.append(event);
    
    Statement stmt = connectionSource.getConnection().createStatement();
    ResultSet rs = null;
    rs = stmt.executeQuery("SELECT * FROM LOGGING_EVENT_PROPERTY  WHERE EVENT_ID = "+ existingRowCount);
    Map<String, String> map = appender.mergePropertyMaps(event);
    System.out.println("ma.size="+map.size());
    int i = 0;
    while (rs.next()) {
      String key = rs.getString(2);
      assertEquals(map.get(key), rs.getString(3));
      i++;
    }
    assertTrue(map.size() != 0);
    assertEquals(map.size(), i);
    rs.close();
  }
  
  private ILoggingEvent createLoggingEvent() {
    return new LoggingEvent(this.getClass().getName(), logger,
        Level.DEBUG, "test message", new Exception("test Ex"), new Integer[]{diff});
  }
}
