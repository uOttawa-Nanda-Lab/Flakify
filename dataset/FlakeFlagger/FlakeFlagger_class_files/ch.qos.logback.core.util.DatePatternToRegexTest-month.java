/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.core.util;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.qos.logback.core.CoreConstants;

public class DatePatternToRegexTest {
  static Calendar CAL_2009_08_3_NIGHT = Calendar.getInstance();
  static Calendar CAL_2009_08_3_MORNING = Calendar.getInstance();

  @BeforeClass
  public static void setUpCalendars() {
    CAL_2009_08_3_NIGHT.set(2009, 8, 3, 21, 57, 16);
    CAL_2009_08_3_NIGHT.set(Calendar.MILLISECOND, 333);

    CAL_2009_08_3_MORNING.set(2009, 8, 3, 10, 24, 37);
    CAL_2009_08_3_MORNING.set(Calendar.MILLISECOND, 333);
  }

  @Test public void month(){doTest("yyyy-MMM-dd",CAL_2009_08_3_NIGHT);doTest("yyyy-MMMM-dd",CAL_2009_08_3_NIGHT);}

  void doTest(String datePattern, Calendar calendar, boolean slashified) {
    SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
    DateTokenConverter dtc = makeDTC(datePattern);
    verify(sdf, calendar, dtc, slashified);
  }

  void doTest(String datePattern, Calendar calendar) {
    doTest(datePattern, calendar, false);
  }


  void verify(SimpleDateFormat sdf, Calendar calendar, DateTokenConverter dtc, boolean slashified) {
    String expected = sdf.format(calendar.getTime());
    if (slashified) {
      expected = expected.replace('\\', '/');
    }
    String regex = dtc.toRegex();
    //System.out.println("expected="+expected);
    //System.out.println(regex);
    assertTrue("[" + expected + "] does not match regex [" + regex + "]",
            expected.matches(regex));
  }

  private DateTokenConverter makeDTC(String datePattern) {
    DateTokenConverter dtc = new DateTokenConverter();
    List<String> optionList = new ArrayList<String>();
    optionList.add(datePattern);
    dtc.setOptionList(optionList);
    dtc.start();
    return dtc;
  }
}
