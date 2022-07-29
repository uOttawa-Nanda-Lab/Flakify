package io.undertow.util;

import io.undertow.server.handlers.Cookie;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Stuart Douglas
 */
public class CookiesTestCase {

    @Test public void testParsingSetCookieHeaderV0(){Cookie cookie=Cookies.parseSetCookieHeader("CUSTOMER=WILE_E_COYOTE; path=/; expires=Wednesday, 09-Nov-99 23:12:40 GMT");Assert.assertEquals("CUSTOMER",cookie.getName());Assert.assertEquals("WILE_E_COYOTE",cookie.getValue());Assert.assertEquals("/",cookie.getPath());Assert.assertEquals(date(1999,11,9,23,12,40),cookie.getExpires());cookie=Cookies.parseSetCookieHeader("SHIPPING=FEDEX; path=/foo; secure");Assert.assertEquals("SHIPPING",cookie.getName());Assert.assertEquals("FEDEX",cookie.getValue());Assert.assertEquals("/foo",cookie.getPath());Assert.assertTrue(cookie.isSecure());}


    private static Date date(int year, int month, int day, int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MILLISECOND, 0);
        c.setTimeZone(TimeZone.getTimeZone("GMT"));
        c.set(year, month-1, day, hour, minute, second);
        return c.getTime();
    }
}
