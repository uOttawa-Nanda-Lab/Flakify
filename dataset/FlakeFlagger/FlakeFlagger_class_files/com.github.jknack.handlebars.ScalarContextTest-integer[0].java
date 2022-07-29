package com.github.jknack.handlebars;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ScalarContextTest {
@Test public void integer() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("var i = {{" + selector + "}};");
  assertEquals("var i = 10;",template.apply(10));
}

}