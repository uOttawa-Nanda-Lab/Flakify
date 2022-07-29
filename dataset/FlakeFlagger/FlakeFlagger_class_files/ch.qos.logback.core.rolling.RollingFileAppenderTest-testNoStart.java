package ch.qos.logback.core.rolling;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class RollingFileAppenderTest {
@Test public void testNoStart(){
  Appender<E> appender=getAppender();
  appender.setContext(context);
  appender.setName("doh");
  appender.doAppend(null);
  StatusChecker checker=new StatusChecker(context.getStatusManager());
  StatusPrinter.print(context);
  checker.assertContainsMatch("Attempted to append to non started appender \\[doh\\].");
}

}