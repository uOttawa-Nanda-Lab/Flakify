package org.springframework.boot.json;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JacksonParserTests {
@SuppressWarnings("unchecked") @Test public void testMapOfLists(){
  Map<String,Object> map=this.parser.parseMap("{\"foo\":[{\"foo\":\"bar\",\"spam\":1},{\"foo\":\"baz\",\"spam\":2}]}");
  assertEquals(1,map.size());
  assertEquals(2,((List<Object>)map.get("foo")).size());
}

}