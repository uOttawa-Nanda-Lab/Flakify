/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * [Additional notices, if required by prior licensing conditions]
 *
 */

package org.apache.http.message;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for header value formatting.
 *
 *
 */
public class TestBasicHeaderValueFormatter {

    @Test public void testParamsFormatting() throws Exception{NameValuePair param1=new BasicNameValuePair("param","regular_stuff");NameValuePair param2=new BasicNameValuePair("param","this\\that");NameValuePair param3=new BasicNameValuePair("param","this,that");NameValuePair[] params=new NameValuePair[]{param1,param2,param3};Assert.assertEquals("param=regular_stuff; param=\"this\\\\that\"; param=\"this,that\"",BasicHeaderValueFormatter.formatParameters(params,false,null));Assert.assertEquals("param=\"regular_stuff\"; param=\"this\\\\that\"; param=\"this,that\"",BasicHeaderValueFormatter.formatParameters(params,true,null));}


}
