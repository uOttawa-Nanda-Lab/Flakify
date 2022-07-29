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

    @Test public void testChunkedConsistence() throws IOException{String input="76126;27823abcd;:q38a-\nkjc\rk%1ad\tkh/asdui\r\njkh+?\\suweb";ByteArrayOutputStream buffer=new ByteArrayOutputStream();OutputStream out=new ChunkedOutputStream(new SessionOutputBufferMock(buffer));out.write(EncodingUtils.getBytes(input,CONTENT_CHARSET));out.flush();out.close();out.close();buffer.close();InputStream in=new ChunkedInputStream(new SessionInputBufferMock(buffer.toByteArray()));byte[] d=new byte[10];ByteArrayOutputStream result=new ByteArrayOutputStream();int len=0;while ((len=in.read(d)) > 0){result.write(d,0,len);}String output=EncodingUtils.getString(result.toByteArray(),CONTENT_CHARSET);Assert.assertEquals(input,output);}

}

