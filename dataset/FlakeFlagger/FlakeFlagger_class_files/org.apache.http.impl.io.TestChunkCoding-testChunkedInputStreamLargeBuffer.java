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

package org.apache.http.impl.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.MalformedChunkCodingException;
import org.apache.http.impl.SessionInputBufferMock;
import org.apache.http.impl.SessionOutputBufferMock;
import org.apache.http.io.SessionInputBuffer;
import org.apache.http.util.EncodingUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestChunkCoding {

    private static final String CONTENT_CHARSET = "ISO-8859-1";

    private final static String CHUNKED_INPUT
        = "10;key=\"value\"\r\n1234567890123456\r\n5\r\n12345\r\n0\r\nFooter1: abcde\r\nFooter2: fghij\r\n";

    private final static String CHUNKED_RESULT
        = "123456789012345612345";

    @Test public void testChunkedInputStreamLargeBuffer() throws IOException{ChunkedInputStream in=new ChunkedInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(CHUNKED_INPUT,CONTENT_CHARSET)));byte[] buffer=new byte[300];ByteArrayOutputStream out=new ByteArrayOutputStream();int len;while ((len=in.read(buffer)) > 0){out.write(buffer,0,len);}Assert.assertEquals(-1,in.read(buffer));Assert.assertEquals(-1,in.read(buffer));in.close();String result=EncodingUtils.getString(out.toByteArray(),CONTENT_CHARSET);Assert.assertEquals(result,CHUNKED_RESULT);Header[] footers=in.getFooters();Assert.assertNotNull(footers);Assert.assertEquals(2,footers.length);Assert.assertEquals("Footer1",footers[0].getName());Assert.assertEquals("abcde",footers[0].getValue());Assert.assertEquals("Footer2",footers[1].getName());Assert.assertEquals("fghij",footers[1].getValue());}

}

