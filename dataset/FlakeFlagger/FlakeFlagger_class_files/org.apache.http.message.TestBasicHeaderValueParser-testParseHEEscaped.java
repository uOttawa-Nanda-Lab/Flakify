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
import org.apache.http.util.CharArrayBuffer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for header value parsing.
 *
 * @version $Id$
 */
public class TestBasicHeaderValueParser {

    @Test public void testParseHEEscaped(){String s="test1 =  \"\\\"stuff\\\"\", test2= \"\\\\\", test3 = \"stuff, stuff\"";HeaderElement[] elements=BasicHeaderValueParser.parseElements(s,null);Assert.assertEquals(3,elements.length);Assert.assertEquals("test1",elements[0].getName());Assert.assertEquals("\\\"stuff\\\"",elements[0].getValue());Assert.assertEquals("test2",elements[1].getName());Assert.assertEquals("\\\\",elements[1].getValue());Assert.assertEquals("test3",elements[2].getName());Assert.assertEquals("stuff, stuff",elements[2].getValue());}

}
