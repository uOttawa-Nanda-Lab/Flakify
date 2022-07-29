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

  @Test public void naturalDay() throws IOException{Calendar calendar=Calendar.getInstance();Date now=calendar.getTime();calendar.add(Calendar.HOUR,-24);Date yesterday=calendar.getTime();calendar.add(Calendar.HOUR,24 * 2);Date tomorrow=calendar.getTime();assertEquals("yesterday",handlebars.compile("{{naturalDay this locale=\"en_US\"}}").apply(yesterday));assertEquals("today",handlebars.compile("{{naturalDay this locale=\"en_US\"}}").apply(now));assertEquals("tomorrow",handlebars.compile("{{naturalDay this locale=\"en_US\"}}").apply(tomorrow));}
}
