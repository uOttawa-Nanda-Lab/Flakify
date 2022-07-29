package com.github.jknack.handlebars;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class InheritanceTest {
@Test public void inheritance() throws IOException {
  try {
    Template template=handlebars.compile(URI.create(name));
    CharSequence result=template.apply(new Object());
    String expected=FileUtils.readFileToString(new File("src/test/resources/inheritance/" + name + ".expected"));
    assertEquals(expected,result);
  }
 catch (  HandlebarsException ex) {
    Handlebars.error(ex.getMessage());
    throw ex;
  }
}

}