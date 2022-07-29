package ch.qos.logback.core.pattern.parser;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class SamplePatternLayoutTest {
/** 
 * This test checks that the pattern layout implementation starts its converters. ExceptionalConverter throws an exception if it's convert method is called before being started.
 */
@Test public void testConverterStart(){
  PatternLayoutBase<E> plb=getPatternLayoutBase();
  plb.setContext(getContext());
  plb.getInstanceConverterMap().put("EX",ExceptionalConverter.class.getName());
  plb.setPattern("%EX");
  plb.start();
  String result=plb.doLayout(getEventObject());
  assertFalse(result.contains("%PARSER_ERROR_EX"));
}

}