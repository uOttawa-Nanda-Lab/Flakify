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

  @Test public void formatPercent() throws IOException{assertEquals("50%",handlebars.compile("{{formatPercent this}}").apply(0.5));assertEquals("100%",handlebars.compile("{{formatPercent this}}").apply(1));assertEquals("56%",handlebars.compile("{{formatPercent this}}").apply(0.564));}
}
