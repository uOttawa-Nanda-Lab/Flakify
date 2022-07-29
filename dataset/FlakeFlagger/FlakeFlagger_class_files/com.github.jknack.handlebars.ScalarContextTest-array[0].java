package com.github.jknack.handlebars;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ScalarContextTest {
@Test public void array() throws IOException {
  Handlebars handlebars=new Handlebars();
  Template template=handlebars.compile("{{#" + selector + "}}{{"+ selector+ "}} {{/"+ selector+ "}}");
  assertEquals("1 2 3 ",template.apply(new Object[]{1,2,3}));
}

}