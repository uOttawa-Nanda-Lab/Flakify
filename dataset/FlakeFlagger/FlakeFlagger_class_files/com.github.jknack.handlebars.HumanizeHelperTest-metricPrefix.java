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

  @Test public void metricPrefix() throws IOException{assertEquals("200",handlebars.compile("{{metricPrefix this}}").apply(200));assertEquals("1k",handlebars.compile("{{metricPrefix this}}").apply(1000));assertEquals("3.5M",handlebars.compile("{{metricPrefix this}}").apply(3500000));}
}
