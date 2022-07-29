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

import java.util.NoSuchElementException;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.junit.Assert;
import org.junit.Test;


/**
 * Tests for {@link BasicHeaderIterator}.
 *
 */
public class TestBasicHeaderIterator {

    @Test public void testRemaining(){Header[] headers=new Header[]{new BasicHeader("Name","value0"),new BasicHeader("nAme","value1, value1.1"),new BasicHeader("naMe","value2=whatever"),new BasicHeader("namE","value3;tag=nil")};HeaderIterator hit=new BasicHeaderIterator(headers,null);Assert.assertTrue(hit.hasNext());Assert.assertEquals("0",headers[0],hit.next());Assert.assertTrue(hit.hasNext());Assert.assertEquals("1",headers[1],hit.next());Assert.assertTrue(hit.hasNext());Assert.assertEquals("2",headers[2],hit.next());Assert.assertTrue(hit.hasNext());Assert.assertEquals("3",headers[3],hit.next());Assert.assertFalse(hit.hasNext());hit=new BasicHeaderIterator(headers,null);Assert.assertTrue(hit.hasNext());try {hit.remove();Assert.fail("remove not detected");} catch (UnsupportedOperationException uox){}Assert.assertTrue("no next",((BasicHeaderIterator)hit).findNext(-3) < 0);}
}
