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
 */

package org.apache.http.message;

import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.util.CharArrayBuffer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link BasicLineParser}.
 *
 */
public class TestBasicLineParser {

    @Test public void testInvalidHttpVersionParsing() throws Exception{try {BasicLineParser.parseProtocolVersion((String)null,null);Assert.fail("IllegalArgumentException should have been thrown");} catch (IllegalArgumentException e){}try {BasicLineParser.parseProtocolVersion("    ",null);Assert.fail("ParseException should have been thrown");} catch (ParseException e){}try {BasicLineParser.parseProtocolVersion("HTT",null);Assert.fail("ParseException should have been thrown");} catch (ParseException e){}try {BasicLineParser.parseProtocolVersion("crap",null);Assert.fail("ParseException should have been thrown");} catch (ParseException e){}try {BasicLineParser.parseProtocolVersion("HTTP/crap",null);Assert.fail("ParseException should have been thrown");} catch (ParseException e){}try {BasicLineParser.parseProtocolVersion("HTTP/1",null);Assert.fail("ParseException should have been thrown");} catch (ParseException e){}try {BasicLineParser.parseProtocolVersion("HTTP/1234   ",null);Assert.fail("ParseException should have been thrown");} catch (ParseException e){}try {BasicLineParser.parseProtocolVersion("HTTP/1.",null);Assert.fail("ParseException should have been thrown");} catch (ParseException e){}try {BasicLineParser.parseProtocolVersion("HTTP/whatever.whatever whatever",null);Assert.fail("ParseException should have been thrown");} catch (ParseException e){}try {BasicLineParser.parseProtocolVersion("HTTP/1.whatever whatever",null);Assert.fail("ParseException should have been thrown");} catch (ParseException e){}}

}
