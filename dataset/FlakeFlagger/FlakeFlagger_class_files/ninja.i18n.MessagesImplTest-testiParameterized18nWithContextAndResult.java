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

package ninja.i18n;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Map;

import ninja.Context;
import ninja.Cookie;
import ninja.Result;
import ninja.Results;
import ninja.utils.NinjaConstant;
import ninja.utils.NinjaProperties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MessagesImplTest {

    @Mock
    private NinjaProperties ninjaProperties;
    
    @Mock
    Context context;
    
    Result result;

    @Test public void testiParameterized18nWithContextAndResult(){when(ninjaProperties.getStringArray(NinjaConstant.applicationLanguages)).thenReturn(new String[]{"en","de","fr-FR"});Lang lang=new LangImpl(ninjaProperties);Messages messages=new MessagesImpl(ninjaProperties,lang);result=Results.ok();when(context.getAcceptLanguage()).thenReturn("en-US");assertEquals("this is the placeholder: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());when(context.getAcceptLanguage()).thenReturn("en-CA");assertEquals("this is the placeholder: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());when(context.getAcceptLanguage()).thenReturn("en-UK");assertEquals("this is the placeholder: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());lang.setLanguage("de",result);assertEquals("das ist der platzhalter: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());lang.setLanguage("de-DE",result);assertEquals("das ist der platzhalter: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());result=Results.ok();when(context.getCookie(Mockito.anyString())).thenReturn(Cookie.builder("name","fr-FR").build());assertEquals("cest le placeholder: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());result=Results.ok();lang.setLanguage("de-DE",result);assertEquals("das ist der platzhalter: test_parameter",messages.get("message_with_placeholder",context,Optional.of(result),"test_parameter").get());}

}
