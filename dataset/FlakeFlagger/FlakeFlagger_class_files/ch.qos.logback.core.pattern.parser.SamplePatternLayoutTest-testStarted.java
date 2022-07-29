package ch.qos.logback.core.pattern.parser;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class SamplePatternLayoutTest {
@Test public void testStarted(){
  PatternLayoutBase<E> plb=getPatternLayoutBase();
  Context context=new ContextBase();
  plb.setContext(context);
  String s=plb.doLayout(getEventObject());
  assertEquals("",s);
  StatusManager sm=context.getStatusManager();
  StatusPrinter.print(sm);
}

}