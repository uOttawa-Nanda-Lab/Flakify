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

  /**
 * Note: beside locale is optional it must be set in unit testing, otherwise the test might fail in a different machine.
 * @throws IOException
 */@Test public void formatCurrency_en_GB() throws IOException{assertEquals("£34",handlebars.compile("{{formatCurrency this locale=\"en_GB\"}}").apply(34));assertEquals("£1,000",handlebars.compile("{{formatCurrency this locale=\"en_GB\"}}").apply(1000));assertEquals("£12.50",handlebars.compile("{{formatCurrency this locale=\"en_GB\"}}").apply(12.5));}
}
