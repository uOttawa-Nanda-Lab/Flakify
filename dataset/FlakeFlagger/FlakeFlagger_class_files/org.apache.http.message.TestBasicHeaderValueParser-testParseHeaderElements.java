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

    @Test public void testParseHeaderElements() throws Exception{String headerValue="name1 = value1; name2; name3=\"value3\" , name4=value4; " + "name5=value5, name6= ; name7 = value7; name8 = \" value8\"";HeaderElement[] elements=BasicHeaderValueParser.parseElements(headerValue,null);Assert.assertEquals(3,elements.length);Assert.assertEquals("name1",elements[0].getName());Assert.assertEquals("value1",elements[0].getValue());Assert.assertEquals(2,elements[0].getParameters().length);Assert.assertEquals("name2",elements[0].getParameters()[0].getName());Assert.assertEquals(null,elements[0].getParameters()[0].getValue());Assert.assertEquals("name3",elements[0].getParameters()[1].getName());Assert.assertEquals("value3",elements[0].getParameters()[1].getValue());Assert.assertEquals("name4",elements[1].getName());Assert.assertEquals("value4",elements[1].getValue());Assert.assertEquals(1,elements[1].getParameters().length);Assert.assertEquals("name5",elements[1].getParameters()[0].getName());Assert.assertEquals("value5",elements[1].getParameters()[0].getValue());Assert.assertEquals("name6",elements[2].getName());Assert.assertEquals("",elements[2].getValue());Assert.assertEquals(2,elements[2].getParameters().length);Assert.assertEquals("name7",elements[2].getParameters()[0].getName());Assert.assertEquals("value7",elements[2].getParameters()[0].getValue());Assert.assertEquals("name8",elements[2].getParameters()[1].getName());Assert.assertEquals(" value8",elements[2].getParameters()[1].getValue());}

}
