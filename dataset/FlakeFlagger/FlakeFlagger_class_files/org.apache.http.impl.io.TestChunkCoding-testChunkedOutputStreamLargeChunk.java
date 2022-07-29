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

    @Test public void testChunkedOutputStreamLargeChunk() throws IOException{SessionOutputBufferMock buffer=new SessionOutputBufferMock();ChunkedOutputStream out=new ChunkedOutputStream(buffer,2);out.write(new byte[]{'1','2','3','4'});out.finish();out.close();byte[] rawdata=buffer.getData();Assert.assertEquals(14,rawdata.length);Assert.assertEquals('4',rawdata[0]);Assert.assertEquals('\r',rawdata[1]);Assert.assertEquals('\n',rawdata[2]);Assert.assertEquals('1',rawdata[3]);Assert.assertEquals('2',rawdata[4]);Assert.assertEquals('3',rawdata[5]);Assert.assertEquals('4',rawdata[6]);Assert.assertEquals('\r',rawdata[7]);Assert.assertEquals('\n',rawdata[8]);Assert.assertEquals('0',rawdata[9]);Assert.assertEquals('\r',rawdata[10]);Assert.assertEquals('\n',rawdata[11]);Assert.assertEquals('\r',rawdata[12]);Assert.assertEquals('\n',rawdata[13]);}

}

