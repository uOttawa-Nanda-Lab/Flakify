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

package org.apache.http.impl.nio.codecs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

import org.apache.http.Header;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.ReadableByteChannelMock;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.impl.nio.reactor.SessionInputBufferImpl;
import org.apache.http.nio.reactor.SessionInputBuffer;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.junit.Assert;
import org.junit.Test;

/**
 * Simple tests for {@link ChunkDecoder}.
 */
public class TestChunkDecoder {

    private static String convert(final ByteBuffer src) {
        src.flip();
        StringBuilder buffer = new StringBuilder(src.remaining());
        while (src.hasRemaining()) {
            buffer.append((char)(src.get() & 0xff));
        }
        return buffer.toString();
    }

    @Test public void testIncompleteChunkDecoding() throws Exception{String[] chunks={"10;","key=\"value\"\r","\n123456789012345","6\r\n5\r\n12","345\r\n6\r","\nabcdef\r","\n0\r\nFoot","er1: abcde\r\nFooter2: f","ghij\r\n\r\n"};ReadableByteChannel channel=new ReadableByteChannelMock(chunks,"US-ASCII");HttpParams params=new BasicHttpParams();SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();ByteBuffer dst=ByteBuffer.allocate(1024);ChunkDecoder decoder=new ChunkDecoder(channel,inbuf,metrics);int bytesRead=0;while (dst.hasRemaining() && !decoder.isCompleted()){int i=decoder.read(dst);if (i > 0){bytesRead+=i;}}Assert.assertEquals(27,bytesRead);Assert.assertEquals("123456789012345612345abcdef",convert(dst));Assert.assertTrue(decoder.isCompleted());Header[] footers=decoder.getFooters();Assert.assertEquals(2,footers.length);Assert.assertEquals("Footer1",footers[0].getName());Assert.assertEquals("abcde",footers[0].getValue());Assert.assertEquals("Footer2",footers[1].getName());Assert.assertEquals("fghij",footers[1].getValue());dst.clear();bytesRead=decoder.read(dst);Assert.assertEquals(-1,bytesRead);Assert.assertTrue(decoder.isCompleted());}

}
