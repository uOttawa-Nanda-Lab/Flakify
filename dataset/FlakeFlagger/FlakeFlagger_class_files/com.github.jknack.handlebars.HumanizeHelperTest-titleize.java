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

  @Test public void titleize() throws IOException{assertEquals("Handlebars.java Rocks!",handlebars.compile("{{titleize this}}").apply("Handlebars.java rocks!"));}
}
