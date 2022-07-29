package ch.qos.logback.core.rolling;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class RollingFileAppenderTest {
@Test public void testConfiguredAppender(){
  Appender<E> appender=getConfiguredAppender();
  appender.start();
  assertTrue(appender.isStarted());
  appender.stop();
  assertFalse(appender.isStarted());
}

}