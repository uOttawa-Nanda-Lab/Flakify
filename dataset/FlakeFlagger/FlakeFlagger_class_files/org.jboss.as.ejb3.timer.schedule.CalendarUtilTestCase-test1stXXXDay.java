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

    @Test public void test1stXXXDay(){final int FIRST_WEEK=1;int expectedDateOfFirstSunOfJuly2010=4;Calendar july2010=new GregorianCalendar();july2010.set(Calendar.DAY_OF_MONTH,1);july2010.set(Calendar.MONTH,Calendar.JULY);july2010.set(Calendar.YEAR,2010);int dateOfFirstSunOfJuly2010=CalendarUtil.getNthDayOfMonth(july2010,FIRST_WEEK,Calendar.SUNDAY);Assert.assertEquals("Unexpected date for 1st sunday of July 2010",expectedDateOfFirstSunOfJuly2010,dateOfFirstSunOfJuly2010);int expectedDateOfFirstMonOfJune2009=1;Calendar june2009=new GregorianCalendar();june2009.set(Calendar.DAY_OF_MONTH,1);june2009.set(Calendar.MONTH,Calendar.JUNE);june2009.set(Calendar.YEAR,2009);int dateOfFirstMonJune2009=CalendarUtil.getNthDayOfMonth(june2009,FIRST_WEEK,Calendar.MONDAY);Assert.assertEquals("Unexpected date for 1st monday of June 2009",expectedDateOfFirstMonOfJune2009,dateOfFirstMonJune2009);int expectedDateOfFirstTueOfFeb2012=7;Calendar feb2012=new GregorianCalendar();feb2012.set(Calendar.DAY_OF_MONTH,1);feb2012.set(Calendar.MONTH,Calendar.FEBRUARY);feb2012.set(Calendar.YEAR,2012);int dateOfFirstTueFeb2012=CalendarUtil.getNthDayOfMonth(feb2012,FIRST_WEEK,Calendar.TUESDAY);Assert.assertEquals("Unexpected date for 1st tuesday of Feb 2012",expectedDateOfFirstTueOfFeb2012,dateOfFirstTueFeb2012);int expectedDateOfFirstMonOfJan2006=4;Calendar jan2006=new GregorianCalendar();jan2006.set(Calendar.DAY_OF_MONTH,1);jan2006.set(Calendar.MONTH,Calendar.JANUARY);jan2006.set(Calendar.YEAR,2006);int dateOfFirstWedJan2006=CalendarUtil.getNthDayOfMonth(jan2006,FIRST_WEEK,Calendar.WEDNESDAY);Assert.assertEquals("Unexpected date for 1st wednesday of Jan 2006",expectedDateOfFirstMonOfJan2006,dateOfFirstWedJan2006);int expectedDateOfFirstThuOfSep1999=2;Calendar sep1999=new GregorianCalendar();sep1999.set(Calendar.DAY_OF_MONTH,1);sep1999.set(Calendar.MONTH,Calendar.SEPTEMBER);sep1999.set(Calendar.YEAR,1999);int dateOfFirstThuSep1999=CalendarUtil.getNthDayOfMonth(sep1999,FIRST_WEEK,Calendar.THURSDAY);Assert.assertEquals("Unexpected date for 1st thursday of September 1999",expectedDateOfFirstThuOfSep1999,dateOfFirstThuSep1999);int expectedDateOfFirstFriOfDec2058=6;Calendar dec2058=new GregorianCalendar();dec2058.set(Calendar.DAY_OF_MONTH,1);dec2058.set(Calendar.MONTH,Calendar.DECEMBER);dec2058.set(Calendar.YEAR,2058);int dateOfFirstFriDec2058=CalendarUtil.getNthDayOfMonth(dec2058,FIRST_WEEK,Calendar.FRIDAY);Assert.assertEquals("Unexpected date for 1st friday of December 2058",expectedDateOfFirstFriOfDec2058,dateOfFirstFriDec2058);int expectedDateOfFirstSatOfAug2000=5;Calendar aug2000=new GregorianCalendar();aug2000.set(Calendar.DAY_OF_MONTH,1);aug2000.set(Calendar.MONTH,Calendar.AUGUST);aug2000.set(Calendar.YEAR,2000);int dateOfFirstSatAug2000=CalendarUtil.getNthDayOfMonth(aug2000,FIRST_WEEK,Calendar.SATURDAY);Assert.assertEquals("Unexpected date for 1st saturday of August 2000",expectedDateOfFirstSatOfAug2000,dateOfFirstSatAug2000);}

}
