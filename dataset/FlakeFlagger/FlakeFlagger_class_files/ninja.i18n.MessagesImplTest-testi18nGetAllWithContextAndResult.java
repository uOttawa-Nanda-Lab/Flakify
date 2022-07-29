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

    @Test public void testi18nGetAllWithContextAndResult(){when(ninjaProperties.getStringArray(NinjaConstant.applicationLanguages)).thenReturn(new String[]{"en","de","fr-FR"});Lang lang=new LangImpl(ninjaProperties);Messages messages=new MessagesImpl(ninjaProperties,lang);result=Results.ok();when(context.getAcceptLanguage()).thenReturn("en-US");Map<Object, Object> map=messages.getAll(context,Optional.of(result));assertEquals(4,map.keySet().size());assertTrue(map.containsKey("language"));assertTrue(map.containsKey("message_with_placeholder"));assertTrue(map.containsKey("a_property_only_in_the_defaultLanguage"));assertTrue(map.containsKey("a_propert_with_commas"));assertEquals("english",map.get("language"));lang.setLanguage("de",result);map=messages.getAll(context,Optional.of(result));assertEquals(4,map.keySet().size());assertTrue(map.containsKey("language"));assertTrue(map.containsKey("message_with_placeholder"));assertTrue(map.containsKey("a_property_only_in_the_defaultLanguage"));assertTrue(map.containsKey("a_propert_with_commas"));assertEquals("deutsch",map.get("language"));assertEquals("das ist der platzhalter: {0}",map.get("message_with_placeholder"));result=Results.ok();when(context.getCookie(Mockito.anyString())).thenReturn(Cookie.builder("name","en").build());map=messages.getAll(context,Optional.of(result));assertEquals(4,map.keySet().size());assertTrue(map.containsKey("language"));assertTrue(map.containsKey("message_with_placeholder"));assertTrue(map.containsKey("a_property_only_in_the_defaultLanguage"));assertTrue(map.containsKey("a_propert_with_commas"));assertEquals("english",map.get("language"));}

}
