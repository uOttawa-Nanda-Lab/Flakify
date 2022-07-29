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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import org.apache.http.ConnectionClosedException;
import org.apache.http.ReadableByteChannelMock;
import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.impl.nio.reactor.SessionInputBufferImpl;
import org.apache.http.nio.reactor.SessionInputBuffer;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Simple tests for {@link LengthDelimitedDecoder}.
 */
public class TestLengthDelimitedDecoder {

    private File tmpfile;

    protected File createTempFile() throws IOException {
        this.tmpfile = File.createTempFile("testFile", ".txt");
        return this.tmpfile;
    }

    private static String convert(final ByteBuffer src) {
        src.flip();
        StringBuilder buffer = new StringBuilder(src.remaining());
        while (src.hasRemaining()) {
            buffer.append((char)(src.get() & 0xff));
        }
        return buffer.toString();
    }

    private static String readFromFile(final File file) throws Exception {
        FileInputStream filestream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(filestream);
        try {
            StringBuilder buffer = new StringBuilder();
            char[] tmp = new char[2048];
            int l;
            while ((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toString();
        } finally {
            reader.close();
        }
    }

    @Test public void testCodingBeyondContentLimitFile() throws Exception{ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"stuff;","more stuff; and a lot more stuff"},"US-ASCII");HttpParams params=new BasicHttpParams();SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();LengthDelimitedDecoder decoder=new LengthDelimitedDecoder(channel,inbuf,metrics,16);createTempFile();RandomAccessFile testfile=new RandomAccessFile(this.tmpfile,"rw");try {FileChannel fchannel=testfile.getChannel();long bytesRead=decoder.transfer(fchannel,0,6);Assert.assertEquals(6,bytesRead);Assert.assertFalse(decoder.isCompleted());Assert.assertEquals(6,metrics.getBytesTransferred());bytesRead=decoder.transfer(fchannel,0,10);Assert.assertEquals(10,bytesRead);Assert.assertTrue(decoder.isCompleted());Assert.assertEquals(16,metrics.getBytesTransferred());bytesRead=decoder.transfer(fchannel,0,1);Assert.assertEquals(-1,bytesRead);Assert.assertTrue(decoder.isCompleted());Assert.assertEquals(16,metrics.getBytesTransferred());}  finally {testfile.close();}}

}
