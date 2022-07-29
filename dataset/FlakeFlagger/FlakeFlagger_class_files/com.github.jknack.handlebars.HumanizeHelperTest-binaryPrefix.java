package com.github.jknack.handlebars;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class HumanizeHelperTest {
  private static final Handlebars handlebars = new Handlebars();

  static {
    HumanizeHelper.register(handlebars);
  }

  @Test public void binaryPrefix() throws IOException{assertEquals("2 bytes",handlebars.compile("{{binaryPrefix this}}").apply(2));assertEquals("1.5 kB",handlebars.compile("{{binaryPrefix this}}").apply(1536));assertEquals("5 MB",handlebars.compile("{{binaryPrefix this}}").apply(5242880));}
}
