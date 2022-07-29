/**
 * Copyright (c) 2012 Edgar Espina
 * This file is part of Handlebars.java.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jknack.handlebars;

import static java.util.Arrays.asList;

import java.io.IOException;
import java.util.Collections;

import org.junit.Test;

public class IfBlockTest extends AbstractTest {

  @Test public void truthy() throws IOException{shouldCompileTo("{{#if value}}true{{else}}false{{/if}}",$("value","x"),"true");shouldCompileTo("{{#value}}true{{^}}false{{/value}}",$("value","x"),"true");shouldCompileTo("{{^value}}false{{/value}}",$("value","x"),"");shouldCompileTo("{{#if value}}true{{else}}false{{/if}}",$("value",$),"true");shouldCompileTo("{{#value}}true{{^}}false{{/value}}",$("value",$),"true");shouldCompileTo("{{^value}}false{{/value}}",$("value",$),"");shouldCompileTo("{{#if value}}true{{else}}false{{/if}}",$("value",true),"true");shouldCompileTo("{{#value}}true{{^}}false{{/value}}",$("value",true),"true");shouldCompileTo("{{^value}}false{{/value}}",$("value",true),"");shouldCompileTo("{{#if value}}true{{else}}false{{/if}}",$("value",asList("0")),"true");shouldCompileTo("{{#value}}true{{^}}false{{/value}}",$("value",asList("0")),"true");shouldCompileTo("{{^value}}false{{/value}}",$("value",asList(0)),"");}

}
