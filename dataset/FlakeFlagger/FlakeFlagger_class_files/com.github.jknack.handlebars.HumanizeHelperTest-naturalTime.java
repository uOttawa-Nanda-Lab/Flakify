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

  @Test public void naturalTime() throws IOException,InterruptedException{Calendar calendar=Calendar.getInstance();Date now=calendar.getTime();Thread.sleep(1000);assertEquals("moments ago",handlebars.compile("{{naturalTime this locale=\"en_US\"}}").apply(now));}
}
