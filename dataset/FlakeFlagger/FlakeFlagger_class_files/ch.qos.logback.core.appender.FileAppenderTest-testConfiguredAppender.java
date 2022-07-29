package ch.qos.logback.core.appender;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class FileAppenderTest {
@Test public void testConfiguredAppender(){
  Appender<E> appender=getConfiguredAppender();
  appender.start();
  assertTrue(appender.isStarted());
  appender.stop();
  assertFalse(appender.isStarted());
}

}