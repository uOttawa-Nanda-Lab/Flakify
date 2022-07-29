package org.springframework.boot.json;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JacksonParserTests {
@SuppressWarnings("unchecked") @Test public void testListOfMaps(){
  List<Object> list=this.parser.parseList("[{\"foo\":\"bar\",\"spam\":1},{\"foo\":\"baz\",\"spam\":2}]");
  assertEquals(2,list.size());
  assertEquals(2,((Map<String,Object>)list.get(1)).size());
}

}