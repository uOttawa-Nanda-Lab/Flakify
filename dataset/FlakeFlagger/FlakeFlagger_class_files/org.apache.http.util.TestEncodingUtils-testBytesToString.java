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

package org.apache.http.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link TestEncodingUtils}.
 *
 */
public class TestEncodingUtils {

    private static String constructString(int [] unicodeChars) {
        StringBuilder buffer = new StringBuilder();
        if (unicodeChars != null) {
            for (int i = 0; i < unicodeChars.length; i++) {
                buffer.append((char)unicodeChars[i]);
            }
        }
        return buffer.toString();
    }

    static final int SWISS_GERMAN_HELLO [] = {
            0x47, 0x72, 0xFC, 0x65, 0x7A, 0x69, 0x5F, 0x7A, 0xE4, 0x6D, 0xE4
        };

    @Test public void testBytesToString() throws Exception{String s=constructString(SWISS_GERMAN_HELLO);byte[] utf=s.getBytes("UTF-8");byte[] latin1=s.getBytes("ISO-8859-1");String s1=EncodingUtils.getString(utf,"UTF-8");String s2=EncodingUtils.getString(latin1,"ISO-8859-1");Assert.assertEquals(s,s1);Assert.assertEquals(s,s2);try {EncodingUtils.getString(null,0,0,"UTF-8");Assert.fail("IllegalArgumentException should have been thrown");} catch (IllegalArgumentException ex){}try {EncodingUtils.getString(null,"UTF-8");Assert.fail("IllegalArgumentException should have been thrown");} catch (IllegalArgumentException ex){}try {EncodingUtils.getString(new byte[]{},null);Assert.fail("IllegalArgumentException should have been thrown");} catch (IllegalArgumentException ex){}try {EncodingUtils.getString(new byte[]{},"");Assert.fail("IllegalArgumentException should have been thrown");} catch (IllegalArgumentException ex){}}

}
