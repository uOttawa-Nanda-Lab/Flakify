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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import ninja.Context;
import ninja.Cookie;
import ninja.Result;
import ninja.utils.Crypto;
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

@RunWith(MockitoJUnitRunner.class)
public class SessionCookieTest {

    @Mock
    private Context context;

    @Mock
    private Result result;

    @Captor
    private ArgumentCaptor<Cookie> cookieCaptor;

    private Crypto crypto;

    @Mock
    NinjaProperties ninjaProperties;

    @Before
    public void setUp() {

        when(
                ninjaProperties
                        .getInteger(NinjaConstant.sessionExpireTimeInSeconds))
                .thenReturn(10000);
        when(
                ninjaProperties.getBooleanWithDefault(
                        NinjaConstant.sessionSendOnlyIfChanged, true))
                .thenReturn(true);
        when(
                ninjaProperties.getBooleanWithDefault(
                        NinjaConstant.sessionTransferredOverHttpsOnly, true))
                .thenReturn(true);
        when(
                ninjaProperties.getBooleanWithDefault(
                        NinjaConstant.sessionHttpOnly, true)).thenReturn(true);

        when(ninjaProperties.getOrDie(NinjaConstant.applicationSecret))
                .thenReturn("secret");

        when(ninjaProperties.getOrDie(NinjaConstant.applicationCookiePrefix))
                .thenReturn("NINJA");

        crypto = new Crypto(ninjaProperties);

    }

    @Test
    public void testThatCorrectMethodOfNinjaPropertiesIsUsedSoThatStuffBreaksWhenPropertyIsAbsent() {

        // we did not set the cookie prefix
        when(ninjaProperties.getOrDie(NinjaConstant.applicationCookiePrefix))
                .thenReturn(null);

        // stuff must break => ...
        SessionCookie sessionCookie = new SessionCookieImpl(crypto,
                ninjaProperties);

        verify(ninjaProperties).getOrDie(NinjaConstant.applicationCookiePrefix);
    }

}
