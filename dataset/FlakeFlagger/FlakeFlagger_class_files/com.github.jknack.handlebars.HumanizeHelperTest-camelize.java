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

  @Test public void camelize() throws IOException{assertEquals("ThisIsCamelCase",handlebars.compile("{{camelize this}}").apply("This is camel case"));assertEquals("thisIsCamelCase",handlebars.compile("{{camelize this capFirst=false}}").apply("This is camel case"));}
}
