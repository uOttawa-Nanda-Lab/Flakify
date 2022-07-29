package ch.qos.logback.core.pattern.parser;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class SamplePatternLayoutTest {
@Test public void testNullPattern(){
  PatternLayoutBase<E> plb=getPatternLayoutBase();
  Context context=new ContextBase();
  plb.setContext(context);
  plb.setPattern(null);
  plb.start();
  String s=plb.doLayout(getEventObject());
  assertEquals("",s);
  StatusChecker checker=new StatusChecker(context.getStatusManager());
  checker.assertContainsMatch("Empty or null pattern.");
}

}