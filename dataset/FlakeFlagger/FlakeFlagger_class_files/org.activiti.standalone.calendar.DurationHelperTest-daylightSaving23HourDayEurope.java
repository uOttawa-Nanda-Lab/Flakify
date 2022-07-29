/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.standalone.calendar;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.activiti.engine.impl.calendar.DurationHelper;
import org.activiti.engine.impl.util.DefaultClockImpl;
import org.activiti.engine.runtime.Clock;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class DurationHelperTest {

    @Test public void daylightSaving23HourDayEurope() throws Exception{Clock testingClock=new DefaultClockImpl();testingClock.setCurrentCalendar(parseCalendar("20140330-00:00:00",TimeZone.getTimeZone("Europe/Amsterdam")));DurationHelper dh=new DurationHelper("R2/2014-03-30T00:00:00/P1D",testingClock);assertEquals(parseCalendar("20140331-00:00:00",TimeZone.getTimeZone("Europe/Amsterdam")),dh.getCalendarAfter(testingClock.getCurrentCalendar()));}

    private Date parse(String str) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
        return simpleDateFormat.parse(str);
    }

    private Calendar parseCalendarWithOffset(String str) throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.setTime(DateUtils.parseDate(str, "yyyyMMdd-HH:mm:ss ZZ"));
        return cal;
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
