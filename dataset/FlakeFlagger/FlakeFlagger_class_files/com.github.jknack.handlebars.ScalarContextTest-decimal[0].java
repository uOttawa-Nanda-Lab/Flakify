package com.github.jknack.handlebars;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ScalarContextTest {
@Test public void decimal() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("var d = {{" + selector + "}};");
  assertEquals("var d = 1.34;",template.apply(1.34));
}

}