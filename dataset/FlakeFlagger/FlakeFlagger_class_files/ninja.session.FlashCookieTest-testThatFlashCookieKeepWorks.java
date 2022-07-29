/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ninja.session;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ninja.Context;
import ninja.Cookie;
import ninja.Result;
import ninja.Results;
import ninja.utils.NinjaConstant;
import ninja.utils.NinjaProperties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class FlashCookieTest {

    @Mock
    private Context context;

    @Mock
    private Result result;

    @Captor
    private ArgumentCaptor<Cookie> cookieCaptor;

    @Mock
    private NinjaProperties ninjaProperties;

    @Before
    public void setUp() {

        when(ninjaProperties.getOrDie(NinjaConstant.applicationCookiePrefix))
                .thenReturn("NINJA");

    }

    @Test public void testThatFlashCookieKeepWorks(){Cookie cookie=Cookie.builder("NINJA_FLASH","hello=flashScope").build();when(context.getCookie("NINJA_FLASH")).thenReturn(cookie);FlashCookie flashCookie=new FlashCookieImpl(ninjaProperties);flashCookie.init(context);assertEquals("flashScope",flashCookie.get("hello"));assertEquals(1,((FlashCookieImpl)flashCookie).getCurrentFlashCookieData().size());assertEquals(0,((FlashCookieImpl)flashCookie).getOutgoingFlashCookieData().size());flashCookie.keep();assertEquals(1,((FlashCookieImpl)flashCookie).getCurrentFlashCookieData().size());assertEquals(1,((FlashCookieImpl)flashCookie).getOutgoingFlashCookieData().size());}

}
