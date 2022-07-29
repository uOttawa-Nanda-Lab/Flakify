package com.github.jknack.handlebars;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ScalarContextTest {
@Test public void quoteHash() throws IOException {
  Handlebars handlebars=new Handlebars();
  handlebars.registerHelper("quote",new Helper<String>(){
    @Override public CharSequence apply(    final String context,    final Options options) throws IOException {
      return (CharSequence)options.hash.get("q");
    }
  }
);
  Template template=handlebars.compile("{{{quote q=\"2\\\"secs\"}}}");
  assertEquals("2\"secs",template.apply(null));
}

}