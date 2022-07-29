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

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.jboss.as.ejb3.timerservice.schedule.util.CalendarUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link CalendarUtil}
 *
 * @author Jaikiran Pai
 * @version $Revision: $
 */
public class CalendarUtilTestCase {

    @Test public void test2ndXXXDay(){final int SECOND_WEEK=2;int expectedDateOfSecondSunOfMay2010=9;Calendar may2010=new GregorianCalendar();may2010.set(Calendar.MONTH,Calendar.MAY);may2010.set(Calendar.YEAR,2010);int dateOfSecondSunOfMay2010=CalendarUtil.getNthDayOfMonth(may2010,SECOND_WEEK,Calendar.SUNDAY);Assert.assertEquals("Unexpected date for 2nd sunday of May 2010",expectedDateOfSecondSunOfMay2010,dateOfSecondSunOfMay2010);int expectedDateOfSecondMonOfFeb2111=9;Calendar feb2111=new GregorianCalendar();feb2111.set(Calendar.MONTH,Calendar.FEBRUARY);feb2111.set(Calendar.YEAR,2111);int dateOfSecondMonFeb2111=CalendarUtil.getNthDayOfMonth(feb2111,SECOND_WEEK,Calendar.MONDAY);Assert.assertEquals("Unexpected date for 2nd monday of Feb 2111",expectedDateOfSecondMonOfFeb2111,dateOfSecondMonFeb2111);int expectedDateOfSecondTueOct2016=11;Calendar oct2016=new GregorianCalendar();oct2016.set(Calendar.MONTH,Calendar.OCTOBER);oct2016.set(Calendar.YEAR,2016);int dateOfSecondTueOct2016=CalendarUtil.getNthDayOfMonth(oct2016,SECOND_WEEK,Calendar.TUESDAY);Assert.assertEquals("Unexpected date for 2nd tuesday of Oct 2016",expectedDateOfSecondTueOct2016,dateOfSecondTueOct2016);int expectedDateOfSecWedApr2010=14;Calendar apr2010=new GregorianCalendar();apr2010.set(Calendar.DAY_OF_MONTH,1);apr2010.set(Calendar.MONTH,Calendar.APRIL);apr2010.set(Calendar.YEAR,2010);int dateOfSecondWedApril2010=CalendarUtil.getNthDayOfMonth(apr2010,SECOND_WEEK,Calendar.WEDNESDAY);Assert.assertEquals("Unexpected date for 2nd wednesday of April 2010",expectedDateOfSecWedApr2010,dateOfSecondWedApril2010);int expectedDateOfSecondThuMar2067=10;Calendar march2067=new GregorianCalendar();march2067.set(Calendar.DAY_OF_MONTH,1);march2067.set(Calendar.MONTH,Calendar.MARCH);march2067.set(Calendar.YEAR,2067);int dateOfSecThuMarch2067=CalendarUtil.getNthDayOfMonth(march2067,SECOND_WEEK,Calendar.THURSDAY);Assert.assertEquals("Unexpected date for 2nd thursday of March 2067",expectedDateOfSecondThuMar2067,dateOfSecThuMarch2067);int expectedDateOfSecFriNov2020=13;Calendar nov2020=new GregorianCalendar();nov2020.set(Calendar.DAY_OF_MONTH,1);nov2020.set(Calendar.MONTH,Calendar.NOVEMBER);nov2020.set(Calendar.YEAR,2020);int dateOfFirstFriDec2058=CalendarUtil.getNthDayOfMonth(nov2020,SECOND_WEEK,Calendar.FRIDAY);Assert.assertEquals("Unexpected date for 2nd friday of November 2020",expectedDateOfSecFriNov2020,dateOfFirstFriDec2058);int expectedDateOfSecSatOfSep2013=14;Calendar aug2000=new GregorianCalendar();aug2000.set(Calendar.DAY_OF_MONTH,1);aug2000.set(Calendar.MONTH,Calendar.SEPTEMBER);aug2000.set(Calendar.YEAR,2013);int dateOfSecSatSep2013=CalendarUtil.getNthDayOfMonth(aug2000,SECOND_WEEK,Calendar.SATURDAY);Assert.assertEquals("Unexpected date for 2nd saturday of September 2013",expectedDateOfSecSatOfSep2013,dateOfSecSatSep2013);}

}
