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

package org.apache.http.impl.nio.reactor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.CharacterCodingException;

import org.apache.http.nio.reactor.SessionInputBuffer;
import org.apache.http.nio.reactor.SessionOutputBuffer;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.CharArrayBuffer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Simple tests for {@link SessionInputBuffer} and {@link SessionOutputBuffer}.
 */
public class TestSessionInOutBuffers {

    private static WritableByteChannel newChannel(final ByteArrayOutputStream outstream) {
        return Channels.newChannel(outstream);
    }

    private static ReadableByteChannel newChannel(final byte[] bytes) {
        return Channels.newChannel(new ByteArrayInputStream(bytes));
    }

    private static ReadableByteChannel newChannel(final String s, final String charset)
            throws UnsupportedEncodingException {
        return Channels.newChannel(new ByteArrayInputStream(s.getBytes(charset)));
    }

    private static ReadableByteChannel newChannel(final String s)
            throws UnsupportedEncodingException {
        return newChannel(s, "US-ASCII");
    }

    @Test public void testBasicReadWriteLine() throws Exception{String[] teststrs=new String[5];teststrs[0]="Hello";teststrs[1]="This string should be much longer than the size of the line buffer " + "which is only 16 bytes for this test";StringBuilder buffer=new StringBuilder();for (int i=0;i < 15;i++){buffer.append("123456789 ");}buffer.append("and stuff like that");teststrs[2]=buffer.toString();teststrs[3]="";teststrs[4]="And goodbye";HttpParams params=new BasicHttpParams();SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,16,params);for (int i=0;i < teststrs.length;i++){outbuf.writeLine(teststrs[i]);}outbuf.writeLine((String)null);outbuf.writeLine((CharArrayBuffer)null);ByteArrayOutputStream outstream=new ByteArrayOutputStream();WritableByteChannel outChannel=newChannel(outstream);outbuf.flush(outChannel);ReadableByteChannel channel=newChannel(outstream.toByteArray());SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,16,params);inbuf.fill(channel);for (int i=0;i < teststrs.length;i++){Assert.assertEquals(teststrs[i],inbuf.readLine(true));}Assert.assertNull(inbuf.readLine(true));Assert.assertNull(inbuf.readLine(true));}

    static final int SWISS_GERMAN_HELLO [] = {
        0x47, 0x72, 0xFC, 0x65, 0x7A, 0x69, 0x5F, 0x7A, 0xE4, 0x6D, 0xE4
    };

    static final int RUSSIAN_HELLO [] = {
        0x412, 0x441, 0x435, 0x43C, 0x5F, 0x43F, 0x440, 0x438,
        0x432, 0x435, 0x442
    };

    private static String constructString(int [] unicodeChars) {
        StringBuilder buffer = new StringBuilder();
        if (unicodeChars != null) {
            for (int i = 0; i < unicodeChars.length; i++) {
                buffer.append((char)unicodeChars[i]);
            }
        }
        return buffer.toString();
    }

}
