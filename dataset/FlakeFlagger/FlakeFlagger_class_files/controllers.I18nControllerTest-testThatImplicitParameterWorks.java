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

package controllers;

import static org.junit.Assert.assertTrue;

import java.net.URLEncoder;
import java.util.Map;

import ninja.NinjaTest;
import ninja.utils.NinjaTestBrowser;

import org.junit.Test;

import com.google.common.collect.Maps;

public class I18nControllerTest extends NinjaTest {
    
    String TEXT_EN = "Hello - this is an i18n message in the templating engine with placeholder: Yea!";
    String TEXT_DE = "Hallo - das ist eine internationalisierte Nachricht in der Templating Engine mit placeholder: Yea!";

    @Test public void testThatImplicitParameterWorks(){Map<String, String> headers=Maps.newHashMap();headers.put("Accept-Language","de-DE");String result=ninjaTestBrowser.makeRequest(getServerAddress() + "/i18n",headers);assertTrue(result.contains("Implicit language is: de-DE"));}

}
