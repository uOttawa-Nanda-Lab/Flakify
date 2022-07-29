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
import org.apache.http.ParseException;
import org.apache.http.TokenIterator;
import org.junit.Assert;
import org.junit.Test;


/**
 * Tests for {@link BasicTokenIterator}.
 *
 */
public class TestBasicTokenIterator {

    @Test
    public void testInvalid() {
        Header[] headers = new Header[]{
            new BasicHeader("in", "token0=token1"),
            new BasicHeader("no", "token0 token1"),
            new BasicHeader("pre", "<token0,token1"),
            new BasicHeader("post", "token0,token1="),
        };
        HeaderIterator hit = new BasicHeaderIterator(headers, "in");
        TokenIterator  ti  = new BasicTokenIterator(hit);

        // constructor located token0
        Assert.assertTrue(ti.hasNext());
        try {
            ti.nextToken();
            Assert.fail("invalid infix character not detected");
        } catch (ParseException px) {
            // expected
        }


        // constructor located token0
        hit = new BasicHeaderIterator(headers, "no");
        ti  = new BasicTokenIterator(hit);
        Assert.assertTrue(ti.hasNext());
        try {
            ti.nextToken();
            Assert.fail("missing token separator not detected");
        } catch (ParseException px) {
            // expected
        }


        // constructor seeks for the first token
        hit = new BasicHeaderIterator(headers, "pre");
        try {
            new BasicTokenIterator(hit);
            Assert.fail("invalid prefix character not detected");
        } catch (ParseException px) {
            // expected
        }


        hit = new BasicHeaderIterator(headers, "post");
        ti  = new BasicTokenIterator(hit);

        Assert.assertTrue(ti.hasNext());
        Assert.assertEquals("token0", "token0", ti.nextToken());
        Assert.assertTrue(ti.hasNext());
        // failure after the last must not go unpunished
        try {
            ti.nextToken();
            Assert.fail("invalid postfix character not detected");
        } catch (ParseException px) {
            // expected
        }
    }

}
