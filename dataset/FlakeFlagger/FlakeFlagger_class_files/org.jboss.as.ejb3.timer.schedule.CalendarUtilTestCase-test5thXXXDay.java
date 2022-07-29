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

    @Test public void test5thXXXDay(){int expectedDateOfFirstSunOfJuly2010=4;Calendar july2010=new GregorianCalendar();july2010.set(Calendar.MONTH,Calendar.JULY);july2010.set(Calendar.YEAR,2010);int dateOfFirstSunOfJuly2010=CalendarUtil.getNthDayOfMonth(july2010,1,Calendar.SUNDAY);Assert.assertEquals("Unexpected date for 1st sunday of July 2010",expectedDateOfFirstSunOfJuly2010,dateOfFirstSunOfJuly2010);}

}
