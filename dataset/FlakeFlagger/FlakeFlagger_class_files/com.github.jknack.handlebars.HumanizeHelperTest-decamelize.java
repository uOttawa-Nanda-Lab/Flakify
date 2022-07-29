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

  @Test public void decamelize() throws IOException{assertEquals("this Is Camel Case",handlebars.compile("{{decamelize this}}").apply("thisIsCamelCase"));assertEquals("This Is Camel Case",handlebars.compile("{{decamelize this}}").apply("ThisIsCamelCase"));assertEquals("ThisxIsxCamelxCase",handlebars.compile("{{decamelize this replacement=\"x\"}}").apply("ThisIsCamelCase"));}
}
