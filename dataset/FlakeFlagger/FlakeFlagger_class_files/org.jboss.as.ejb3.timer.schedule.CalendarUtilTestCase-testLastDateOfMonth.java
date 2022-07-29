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

    /**
	 * Tests that the   {@link CalendarUtil#getLastDateOfMonth(java.util.Calendar)}  returns the correctdate for various months.
	 */@Test public void testLastDateOfMonth(){Calendar march=new GregorianCalendar();march.set(Calendar.MONTH,Calendar.MARCH);march.set(Calendar.DAY_OF_MONTH,1);int lastDateOfMarch=CalendarUtil.getLastDateOfMonth(march);Assert.assertEquals("Unexpected last date for march",31,lastDateOfMarch);Calendar april=new GregorianCalendar();april.set(Calendar.MONTH,Calendar.APRIL);april.set(Calendar.DAY_OF_MONTH,1);int lastDateOfApril=CalendarUtil.getLastDateOfMonth(april);Assert.assertEquals("Unexpected last date for april",30,lastDateOfApril);Calendar nonLeapFebruary=new GregorianCalendar();nonLeapFebruary.set(Calendar.MONTH,Calendar.FEBRUARY);nonLeapFebruary.set(Calendar.YEAR,2010);nonLeapFebruary.set(Calendar.DAY_OF_MONTH,1);int lastDateOfNonLeapFebruary=CalendarUtil.getLastDateOfMonth(nonLeapFebruary);Assert.assertEquals("Unexpected last date for non-leap february",28,lastDateOfNonLeapFebruary);Calendar leapFebruary=new GregorianCalendar();leapFebruary.set(Calendar.MONTH,Calendar.FEBRUARY);leapFebruary.set(Calendar.YEAR,2012);leapFebruary.set(Calendar.DAY_OF_MONTH,1);int lastDateOfLeapFebruary=CalendarUtil.getLastDateOfMonth(leapFebruary);Assert.assertEquals("Unexpected last date for leap february",29,lastDateOfLeapFebruary);}

}
