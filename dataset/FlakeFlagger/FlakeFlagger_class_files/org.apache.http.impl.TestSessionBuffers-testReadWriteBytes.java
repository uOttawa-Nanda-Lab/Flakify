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

package org.apache.http.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.io.HttpTransportMetrics;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;
import org.junit.Assert;
import org.junit.Test;

public class TestSessionBuffers {

    @Test public void testReadWriteBytes() throws Exception{byte[] out=new byte[40];for (int i=0;i < out.length;i++){out[i]=(byte)('0' + i);}SessionOutputBufferMock outbuffer=new SessionOutputBufferMock();int off=0;int remaining=out.length;while (remaining > 0){int chunk=10;if (chunk > remaining){chunk=remaining;}outbuffer.write(out,off,chunk);off+=chunk;remaining-=chunk;}outbuffer.flush();long bytesWritten=outbuffer.getMetrics().getBytesTransferred();Assert.assertEquals(out.length,bytesWritten);byte[] tmp=outbuffer.getData();Assert.assertEquals(out.length,tmp.length);for (int i=0;i < out.length;i++){Assert.assertEquals(out[i],tmp[i]);}SessionInputBufferMock inbuffer=new SessionInputBufferMock(tmp);Assert.assertEquals(0,inbuffer.read(null,0,10));Assert.assertEquals(0,inbuffer.read(null));long bytesRead=inbuffer.getMetrics().getBytesTransferred();Assert.assertEquals(0,bytesRead);byte[] in=new byte[40];off=0;remaining=in.length;while (remaining > 0){int chunk=10;if (chunk > remaining){chunk=remaining;}int l=inbuffer.read(in,off,chunk);if (l == -1){break;}off+=l;remaining-=l;}for (int i=0;i < out.length;i++){Assert.assertEquals(out[i],in[i]);}Assert.assertEquals(-1,inbuffer.read(tmp));Assert.assertEquals(-1,inbuffer.read(tmp));bytesRead=inbuffer.getMetrics().getBytesTransferred();Assert.assertEquals(out.length,bytesRead);}

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

