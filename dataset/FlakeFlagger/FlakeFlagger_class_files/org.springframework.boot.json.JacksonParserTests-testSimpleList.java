package org.springframework.boot.json;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JacksonParserTests {
@Test public void testSimpleList(){
  List<Object> list=this.parser.parseList("[\"foo\",\"bar\",1]");
  assertEquals(3,list.size());
  assertEquals("bar",list.get(1));
}

}