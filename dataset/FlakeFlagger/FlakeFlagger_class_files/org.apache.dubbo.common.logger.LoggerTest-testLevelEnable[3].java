package org.apache.dubbo.common.logger;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class LoggerTest {
@Test public void testLevelEnable(){
  assertThat(logger.isWarnEnabled(),not(nullValue()));
  assertThat(logger.isTraceEnabled(),not(nullValue()));
  assertThat(logger.isErrorEnabled(),not(nullValue()));
  assertThat(logger.isInfoEnabled(),not(nullValue()));
  assertThat(logger.isDebugEnabled(),not(nullValue()));
}

}