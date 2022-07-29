/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.ejb3.timer.schedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.ScheduleExpression;

import org.jboss.as.ejb3.timerservice.schedule.CalendarBasedTimeout;
import org.junit.Assert;
import org.junit.Test;

/**
 * CalendarBasedTimeoutTestCase
 *
 * @author Jaikiran Pai
 * @author Eduardo Martins
 * @author "<a href=\"mailto:wfink@redhat.com\">Wolf-Dieter Fink</a>"
 */
public class CalendarBasedTimeoutTestCase {

    /**
     * Logger
     */
//    private static Logger logger = Logger.getLogger(CalendarBasedTimeoutTestCase.class);

    /**
     * The timezone which is in use
     */
    private TimeZone timezone;
    private String timeZoneDisplayName;

    /**
     * This method returns a collection of all available timezones in the system.
     * The tests in this {@link CalendarBasedTimeoutTestCase} will then be run
     * against each of these timezones
     */
    private static List<TimeZone> getTimezones() {
        String[] candidates = TimeZone.getAvailableIDs();
        List<TimeZone> timeZones = new ArrayList<TimeZone>(candidates.length);
        for (String timezoneID : candidates) {
            TimeZone timeZone = TimeZone.getTimeZone(timezoneID);
            boolean different = true;
            for (int i = timeZones.size() - 1; i >= 0; i--)  {
                TimeZone testee = timeZones.get(i);
                if (testee.hasSameRules(timeZone)) {
                    different = false;
                    break;
                }
            }
            if (different) {
                timeZones.add(timeZone);
            }
        }
        return timeZones;
    }

    /**
	 * This test asserts that a timer scheduled to run during the removed hour when the Daylight Savings period starts is executed. It configures a timer to be fired on March 26, 2017 at 03:30:00 in Europe/Helsinki TZ. This hour does not exist in that timezone, this test asserts the timer is fired once during this ambiguous hour.
	 */@Test public void testTimerAtAmbiguousHourEETtoEEST(){Calendar start=new GregorianCalendar(TimeZone.getTimeZone("Europe/Helsinki"));start.clear();start.set(2017,Calendar.MARCH,26,0,0,0);ScheduleExpression schedule=new ScheduleExpression();schedule.hour("3").minute("30").second("0").timezone("Europe/Helsinki").start(start.getTime());CalendarBasedTimeout calendarTimeout=new CalendarBasedTimeout(schedule);Calendar timeout=calendarTimeout.getFirstTimeout();Assert.assertNotNull(timeout);if (timeout.get(Calendar.YEAR) != 2017 || timeout.get(Calendar.MONTH) != Calendar.MARCH || timeout.get(Calendar.DAY_OF_MONTH) != 26 || timeout.get(Calendar.HOUR_OF_DAY) != 3 || timeout.get(Calendar.MINUTE) != 30 || timeout.get(Calendar.SECOND) != 0 || timeout.get(Calendar.DST_OFFSET) != 0){Assert.fail("Time unexpected : " + timeout.toString());}Assert.assertTrue("Schedule is more than 3h 30min hours from " + start.getTime() + " to " + timeout.getTime(),timeout.getTimeInMillis() - start.getTimeInMillis() == 3 * 60 * 60 * 1000 + 30 * 60 * 1000);timeout=calendarTimeout.getNextTimeout(timeout);if (timeout.get(Calendar.YEAR) != 2017 || timeout.get(Calendar.MONTH) != Calendar.MARCH || timeout.get(Calendar.DAY_OF_MONTH) != 27 || timeout.get(Calendar.HOUR_OF_DAY) != 3 || timeout.get(Calendar.MINUTE) != 30 || timeout.get(Calendar.SECOND) != 0 || timeout.get(Calendar.DST_OFFSET) != 3600000){Assert.fail("Time unexpected : " + timeout.toString());}}

    private ScheduleExpression getTimezoneSpecificScheduleExpression() {
        ScheduleExpression scheduleExpression = new ScheduleExpression().timezone(this.timezone.getID());
        GregorianCalendar start = new GregorianCalendar(this.timezone);
        start.clear();
        start.set(2014,0,1,1,0,0);
        return scheduleExpression.start(start.getTime());
    }

    private boolean isLeapYear(Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        return year % 4 == 0;
    }

    private boolean isWeekDay(Calendar cal) {
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case Calendar.SATURDAY:
            case Calendar.SUNDAY:
                return false;
            default:
                return true;
        }
    }

}
