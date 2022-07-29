package org.activiti.standalone.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.activiti.engine.impl.calendar.AdvancedCycleBusinessCalendar;
import org.activiti.engine.impl.test.AbstractTestCase;
import org.activiti.engine.impl.util.DefaultClockImpl;
import org.activiti.engine.runtime.Clock;

public class AdvancedCycleBusinessCalendarTest extends AbstractTestCase {

  private static final Clock testingClock = new DefaultClockImpl();

  public void testDaylightSavingFallIso() throws Exception {
	AdvancedCycleBusinessCalendar businessCalendar = new AdvancedCycleBusinessCalendar(testingClock);
	testingClock.setCurrentCalendar(parseCalendar("20131103-04:00:00", TimeZone.getTimeZone("UTC")));
	assertEquals(parseCalendar("20131104-05:00:00", TimeZone.getTimeZone("UTC")).getTime(),
			businessCalendar.resolveDuedate("R2/2013-11-03T00:00:00-04:00/P1D DSTZONE:US/Eastern"));
}

  private Calendar parseCalendar(String str, TimeZone timeZone) throws Exception {
    return parseCalendar(str, timeZone, "yyyyMMdd-HH:mm:ss");
  }

  private Calendar parseCalendar(String str, TimeZone timeZone, String format) throws Exception {
    Calendar date = new GregorianCalendar(timeZone);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    simpleDateFormat.setTimeZone(timeZone);
    date.setTime(simpleDateFormat.parse(str));
    return date;
  }

}
